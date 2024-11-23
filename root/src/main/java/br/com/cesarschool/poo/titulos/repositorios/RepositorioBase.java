package br.com.cesarschool.poo.titulos.repositorios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class RepositorioBase<T> {
    protected Path path;
    protected String file_name;
    public RepositorioBase() {
        Path BASE_PATH = Paths.get("").toAbsolutePath();
        this.path = BASE_PATH.resolve("root").resolve("database").resolve(file_name+".txt");
        criarArquivoSeNaoExistir(); // Verifica e cria o arquivo se necessário
    }

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

        /**
     * Excludes an entry from a file based on the given identifier.
     *
     * @param identificador the identifier of the entry to be excluded
     * @return true if the entry was found and successfully excluded, false otherwise
     *
     * This method reads the file line by line, checks if the identifier matches the given one,
     * and if so, excludes that line from the list of lines to be written back to the file.
     * If the identifier is not found, the method returns false.
     * If the identifier is found, the method writes the remaining lines back to the file.
     *
     * @throws IOException if an I/O error occurs during reading or writing the file
     */
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
					continue; //ignora a linha que vai ser excluida
				}
				lines.add(line);//adiciona a linha ao array de linhas que vai ser reescrito no arquivo
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
                writer.write(line);//reescreve as linhas que não foram excluidas
                writer.newLine(); //adiciona uma nova linha
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;

	}
}
