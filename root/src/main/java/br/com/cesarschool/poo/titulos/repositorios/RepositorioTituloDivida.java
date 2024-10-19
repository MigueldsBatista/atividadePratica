package br.com.cesarschool.poo.titulos.repositorios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
/*
 * Deve gravar em e ler de um arquivo texto chamado TituloDivida.txt os dados dos objetos do tipo
 * TituloDivida. Seguem abaixo exemplos de linhas (identificador, nome, dataValidade, taxaJuros).
 *
    1;BRASIL;2024-12-12;10.5
    2;EUA;2026-01-01;1.5
    3;FRANCA;2027-11-11;2.5 
 * 
 * A inclus�o deve adicionar uma nova linha ao arquivo. N�o � permitido incluir 
 * identificador repetido. Neste caso, o m�todo deve retornar false. Inclus�o com 
 * sucesso, retorno true.
 * 
 * A altera��o deve substituir a linha atual por uma nova linha. A linha deve ser 
 * localizada por identificador que, quando n�o encontrado, enseja retorno false. 
 * Altera��o com sucesso, retorno true.  
 *   
 * A exclus�o deve apagar a linha atual do arquivo. A linha deve ser 
 * localizada por identificador que, quando n�o encontrado, enseja retorno false. 
 * Exclus�o com sucesso, retorno true.
 * 
 * A busca deve localizar uma linha por identificador, materializar e retornar um 
 * objeto. Caso o identificador n�o seja encontrado no arquivo, retornar null.   
 */
	public class RepositorioTituloDivida {
		private final String  fileName = "TituloDivida.txt";
		private static final Logger LOGGER = Logger.getLogger(RepositorioTituloDivida.class.getName());
	
		public boolean incluir(TituloDivida tituloDivida) {
			try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
				String line;
				while((line=reader.readLine())!=null){
					String[] parts = line.split(";");
					int id = Integer.parseInt(parts[0]);
					if (id == tituloDivida.getIdentificador()){
						return false;
					}
				}
			}catch(IOException e){
				LOGGER.log(Level.SEVERE, "Error reading file", e);
				return false;
			}
			try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
				writer.write(tituloDivida.getIdentificador() + ";" + tituloDivida.getNome() + ";" + tituloDivida.getDataValidade() + ";" + tituloDivida.getTaxaJuros());
				writer.newLine();
			}catch(IOException e){
				LOGGER.log(Level.SEVERE, "Error writing to file", e);
				return false;
			}
			return true;
		}
	
	public boolean alterar(TituloDivida tituloDivida) {
		List<String> lines = new ArrayList<>();
		boolean found = false;
		try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
			String line;
			while((line = reader.readLine())!=null){
				String[] parts =line.split(";");
				int id = Integer.parseInt(parts[0]);
				if(id==tituloDivida.getIdentificador()){
					line = tituloDivida.getIdentificador() +";"+tituloDivida.getNome()+";"+tituloDivida.getDataValidade()+";"+tituloDivida.getTaxaJuros();
					found = true;
				}
				lines.add(line);
			}
		}catch(IOException e){
			LOGGER.log(Level.SEVERE, "Error reading file", e);
			return false;
		}
		if(!found){
			return false;
		}
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
			for(String line : lines){
				writer.write(line);
				writer.newLine();
			}
		}catch(IOException e){
			LOGGER.log(Level.SEVERE, "Error writing to file", e);
			return false;
		}
		return true;
	}
	public boolean excluir(int identificador) {
		List<String> lines = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(";");
				int id = Integer.parseInt(parts[0]);
				if (id != identificador) {
					lines.add(line);
				}
			}
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Error reading file", e);
			return false;
		}
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
			for (String line : lines) {
				writer.write(line);
				writer.newLine();
			}
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Error writing to file", e);
			return false;
		}
		return true;
	}
	public TituloDivida buscar(int identificador) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
			String line;
			while((line = reader.readLine())!=null){
				String[] parts = line.split(";");
				int id = Integer.parseInt(parts[0]);
				if(id==identificador){
					String nome = parts[1];
					LocalDate dataValidade = LocalDate.parse(parts[2], formatter);
					double taxaJuros = Double.parseDouble(parts[3]);
					return new TituloDivida(identificador, nome, dataValidade, taxaJuros);
				}
			}
		}catch(IOException e){
			LOGGER.log(Level.SEVERE, "Error reading file", e);
		}
		return null;
	}
}
