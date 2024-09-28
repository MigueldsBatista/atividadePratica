package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/*
 * Deve gravar em e ler de um arquivo texto chamado Acao.txt os dados dos objetos do tipo
 * Acao. Seguem abaixo exemplos de linhas (identificador, nome, dataValidade, valorUnitario)
 * 
    1;PETROBRAS;2024-12-12;30.33
    2;BANCO DO BRASIL;2026-01-01;21.21
    3;CORREIOS;2027-11-11;6.12 
 * 
 * A inclusão deve adicionar uma nova linha ao arquivo. Não é permitido incluir 
 * identificador repetido. Neste caso, o método deve retornar false. Inclusão com 
 * sucesso, retorno true.
 * 
 * A alteração deve substituir a linha atual por uma nova linha. A linha deve ser 
 * localizada por identificador que, quando não encontrado, enseja retorno false. 
 * Alteração com sucesso, retorno true.  
 *   
 * A exclusão deve apagar a linha atual do arquivo. A linha deve ser 
 * localizada por identificador que, quando não encontrado, enseja retorno false. 
 * Exclusão com sucesso, retorno true.
 * 
 * A busca deve localizar uma linha por identificador, materializar e retornar um 
 * objeto. Caso o identificador não seja encontrado no arquivo, retornar null.   
 */

public class RepositorioAcao {
	public boolean incluir(Acao acao) {
		String fileName = "Acao.txt";
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
			String line;
			while ((line = reader.readLine()) != null){
				String[] parts = line.split(";");
				int id = Integer.parseInt(parts[0]);
				if(id == acao.getIdentificador()){
					return false;
				}
			}
		}catch (IOException e){
			e.printStackTrace();
			return false;
		}

		try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
			writer.write(acao.getIdentificador() + ";" + acao.getNome() + ";" + acao.getDataValidade() + ";" + acao.getValorUnitario());
			writer.newLine();

		} catch(IOException e){
			e.printStackTrace();
			return false;
		}

		return true;
		
	}
	public boolean alterar(Acao acao) {
		String fileName = "Acao.txt";
		List<String> lines = new ArrayList<>();
		boolean found = false;

		try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
			String line;
			while((line = reader.readLine()) != null){
				String[] parts = line.split(";");
				int id = Integer.parseInt(parts[0]);
				if(id == acao.getIdentificador()){
					line = acao.getIdentificador() + ";" + acao.getNome() + ";" + acao.getDataValidade() + ";" + acao.getValorUnitario();
					found = true;
				}
				lines.add(line);
			}
			}catch(IOException e){
				e.printStackTrace();
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
				e.printStackTrace();
				return false;
			}
			return true;
	}
	public boolean excluir(int identificador) {

		String fileName = "Acao.txt";
		List<String> lines = new ArrayList<>();
		boolean found = false;

		try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
			String line;
			while((line = reader.readLine()) != null){
				String[] parts = line.split(";");
				int id = Integer.parseInt(parts[0]);
				if(id == identificador){
					found = true;
					continue;
				}
				lines.add(line);
			}

		} catch (IOException e) {
            e.printStackTrace();
            return false;
        }

		if (!found) {
			return false;
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;

	}
	public Acao buscar(int identificador) {
		return null;
	}
}
