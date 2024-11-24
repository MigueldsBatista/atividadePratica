

import br.com.cesarschool.poo.titulos.utils.Comparavel;
import br.com.cesarschool.poo.daogenerico.Entidade;

//Vamos usar EntidadeModelo para testar o DAO genérico
public class EntidadeModelo extends Entidade implements Comparavel {
	private int identificador; 
	private String nome;

	EntidadeModelo(int identificador, String nome) {
		this.identificador = identificador;
		this.nome = nome; 
	}

	int getId() {
		return identificador;
	}
	String getNome() {
		return nome;
	}
	public int comparar(Comparavel c1) {
		EntidadeModelo em = (EntidadeModelo)c1;			
		return nome.compareTo(em.nome);
	}
    
    @Override
	public String getIdUnico() {
		return ""+this.identificador;
	}

	public String toString() {
		return nome;
	}
}
