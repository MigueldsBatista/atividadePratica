package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Deve gravar em e ler de um arquivo texto chamado entidadeOperadora.txt os dados dos objetos do tipo
 * entidadeOperadora. Seguem abaixo exemplos de linhas.
 *
    1;PETROBRAS;2024-12-12;30.33
    2;BANCO DO BRASIL;2026-01-01;21.21
    3;CORREIOS;2027-11-11;6.12 
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
public class RepositorioEntidadeOperadora {
	private final String fileName = "entidadeOperadora.txt";
   public boolean incluir(EntidadeOperadora entidadeOperadora) {
      try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
          String line;
          while ((line = reader.readLine()) != null){
              String[] parts = line.split(";");
              int id = Integer.parseInt(parts[0]);
              if(id == entidadeOperadora.getIdentificador()){
                  return false;
              }
          }
      } catch (IOException e){
          e.printStackTrace();
          return false;
      }
  
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
        writer.write(entidadeOperadora.getIdentificador() + ";" + entidadeOperadora.getNome() + ";" + entidadeOperadora.getAutorizacao() + ";" 
                     + entidadeOperadora.getSaldoAcao() + ";" + entidadeOperadora.getSaldoTituloDivida());
        writer.newLine();
        } catch (IOException e) {
        e.printStackTrace();
        return false;
        }
  
      return true;
  }
	public boolean alterar(EntidadeOperadora entidadeOperadora) {
		List<String> lines = new ArrayList<>();
		boolean found = false;

		try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
			String line;
			while((line = reader.readLine()) != null){
				String[] parts = line.split(";");
				int id = Integer.parseInt(parts[0]);
				if(id == entidadeOperadora.getIdentificador()){
					line = entidadeOperadora.getIdentificador() + ";" + entidadeOperadora.getAutorizacao() + ";" + entidadeOperadora.getSaldoAcao() + ";" + entidadeOperadora.getSaldoTituloDivida();
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
	public boolean excluir(long identificador) {
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
	public EntidadeOperadora buscar(long identificador) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                long id = Long.parseLong(parts[0]);
                if (id == identificador) {
                    String nome = parts[1];
                    double autorizadoAcao = Double.parseDouble(parts[2]);
                    double saldoAcao = Double.parseDouble(parts[3]);
                    double saldoTituloDivida = Double.parseDouble(parts[4]);
                    
                    EntidadeOperadora entidade = new EntidadeOperadora(identificador, nome, autorizadoAcao);
                    entidade.creditarSaldoAcao(saldoAcao);
                    entidade.creditarSaldoTituloDivida(saldoTituloDivida);
                    
                    return entidade;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; 
    }    
}
