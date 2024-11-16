package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private Path path;
    private final Path BASE_PATH = Paths.get("").toAbsolutePath(); // Caminho para a pasta pai

    public RepositorioAcao() {
        // Inicializa o caminho do arquivo baseado no diretório de trabalho atual
        this.path = BASE_PATH.resolve("root").resolve("database").resolve("Acao.txt");
        criarArquivoSeNaoExistir(); // Verifica e cria o arquivo se necessário
    }

    // Método para verificar e criar o arquivo, se não existir
    private boolean criarArquivoSeNaoExistir() {
        try {
            // Verifica se o arquivo existe; se não, cria um novo
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent()); // Cria os diretórios pai, se necessário
                Files.createFile(path); // Cria o arquivo
                System.out.println("Arquivo criado em: " + path.toAbsolutePath()); // Imprime o caminho absoluto
                return true; // Arquivo criado
            }
            System.out.println("Arquivo já existe em: " + path.toAbsolutePath()); // Imprime se o arquivo já existe
            return false; // Arquivo já existe
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Retorna false em caso de erro
        }
    }

	/*public boolean incluir(Acao acao) {
			// Verifica se a ação já existe
			try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
				String line;
				while ((line = reader.readLine()) != null) {
					String[] parts = line.split(";");
					int id = Integer.parseInt(parts[0]);
					if (id == acao.getIdentificador()) {
						return false; // Ação já existe, não incluir
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}

		try(BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile(), true))){
			writer.write(acao.getIdentificador() + ";" + acao.getNome() + ";" + acao.getDataValidade() + ";" + acao.getValorUnitario());
			writer.newLine();

		} catch(IOException e){
			e.printStackTrace();
			return false;
		}

		return true;
		
	}
	*/

	/*public boolean alterar(Acao acao) {
		List<String> lines = new ArrayList<>();
		boolean found = false;

		try(BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))){
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

			try(BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()))){
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
		List<String> lines = new ArrayList<>();
		boolean found = false;

		try(BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))){
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

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()))) {
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		try(BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))){
			String line;
			while((line = reader.readLine()) != null){
				String[] parts = line.split(";");
				int id = Integer.parseInt(parts[0]);
				if(id == identificador){
					String nome = parts[1];
					LocalDate dataValidade = LocalDate.parse(parts[2], formatter);
					double valorUnitario = Double.parseDouble(parts[3]);
					return new Acao(id, nome, dataValidade, valorUnitario);
				}
			}
		}catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}
	*/
	public List<Acao> listar() {
		List<Acao> acoes = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		try(BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))){
			String line;
			while((line = reader.readLine()) != null){
				String[] parts = line.split(";");
				int id = Integer.parseInt(parts[0]);
				String nome = parts[1];
				LocalDate dataValidade = LocalDate.parse(parts[2], formatter);
				double valorUnitario = Double.parseDouble(parts[3]);
				acoes.add(new Acao(id, nome, dataValidade, valorUnitario));
			}
		} catch(IOException e){
			e.printStackTrace();
		}
		return acoes;
	}
}