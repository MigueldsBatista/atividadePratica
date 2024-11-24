package br.com.cesarschool.poo.titulos.utils;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ComparadoraObjetosSerial {

	//retorna true se os objetos serializados s1 e s2 forem iguais
	//iguais no sentido de que os arrays de bytes gerados pela serialização dos objetos são iguais
	public static boolean compareObjectsSerial(Serializable s1, Serializable s2) {
		ByteArrayOutputStream  bos1 = null;
		ByteArrayOutputStream  bos2 = null;
		ObjectOutputStream oos1 = null;
		ObjectOutputStream oos2 = null;
		boolean ret = true;
		try {
			//cria um array de bytes para cada objeto serializado
			bos1 = new ByteArrayOutputStream();
			bos2 = new ByteArrayOutputStream();
			oos1 = new ObjectOutputStream(bos1);
			oos2 = new ObjectOutputStream(bos2);
			oos1.writeObject(s1);
			oos2.writeObject(s2);
			byte[] b1 = bos1.toByteArray();
			byte[] b2 = bos2.toByteArray();			
			for (int i=0; i<b1.length; i++) {
				if (b2[i] != b1[i]) {
					ret = false;
					break;
				}
				i++;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return ret; 
	}

}