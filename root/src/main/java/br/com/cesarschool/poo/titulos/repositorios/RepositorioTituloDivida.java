package br.com.cesarschool.poo.titulos.repositorios;

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
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
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
    private Path path;
    private final Path BASE_PATH = Paths.get("").toAbsolutePath(); // Caminho para a pasta pai, em tese vai ser atividadePratica, parece que toda vez que o programa executa ele sai do caminho atual e vai pra atividadePratica

    public RepositorioTituloDivida() {
        // Inicializa o caminho do arquivo baseado no diretório de trabalho atual
        this.path = BASE_PATH.resolve("root").resolve("database").resolve("TituloDivida.txt");
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

		private static final Logger LOGGER = Logger.getLogger(RepositorioTituloDivida.class.getName());
	
		public boolean incluir(TituloDivida tituloDivida) {
			try(BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))){
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
			try(BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile(), true))){
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
		try(BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))){
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
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()))){
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
		try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
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
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()))) {
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
		try(BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))){
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

	public List<TituloDivida> listar() {
		List<TituloDivida> titulos = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		try(BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))){
			String line;
			while((line = reader.readLine()) != null){
				String[] parts = line.split(";");
				int id = Integer.parseInt(parts[0]);
				String nome = parts[1];
				LocalDate dataValidade = LocalDate.parse(parts[2], formatter);
				double taxaJuros = Double.parseDouble(parts[3]);
				titulos.add(new TituloDivida(id, nome, dataValidade, taxaJuros));
			}
	} 
catch(IOException e){
	e.printStackTrace();
}
return titulos;
	}
}

