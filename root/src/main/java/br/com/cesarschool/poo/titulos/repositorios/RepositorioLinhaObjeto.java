package br.com.cesarschool.poo.titulos.repositorios;

import java.lang.reflect.Field;
import java.time.LocalDate;

public class RepositorioLinhaObjeto {}
    //professor em nossos commits passados a gente 
    //chegou a implementar porém passamos todo o codigo
    //para o DAOserializadorObjetos onde está lá todo o codigo
    //que estava aqui e mais um pouco ;) 
    // Método serialize atualizado para usar reflexão
    /* 
   package br.com.cesarschool.poo.daogenerico;
import java.util.Arrays;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Field;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class DAOSerializadorObjetos<T extends Entidade> {
	private static final String SEP_ARQUIVO = System.getProperty("file.separator");
	private static final String PONTO = ".";
	private static final String NOME_DIR = PONTO + SEP_ARQUIVO + Entidade.class.getSimpleName();  
    private Class<T> tipoEntidade;

    // Cache adicionado para armazenar objetos já lidos do arquivo
    private Map<String, T> cache;

    public DAOSerializadorObjetos(Class<T> tipoEntidade) {
        this.tipoEntidade = tipoEntidade;
        this.cache = new HashMap<>();
        criarDiretorioSeNaoExistir();  // Garantir que o diretório exista

    }
    private void criarDiretorioSeNaoExistir() {
        File diretorio = new File(NOME_DIR);
        if (!diretorio.exists()) {
            diretorio.mkdir();  // Cria o diretório se ele não existir
        }
    }
	private String obterNomeArquivo(Entidade ent) {
		return NOME_DIR + SEP_ARQUIVO + ent.getIdUnico();
	}

	private String obterNomeArquivo(String idUnico) {
		return NOME_DIR + SEP_ARQUIVO + idUnico;
	}
    // Método serialize atualizado para usar reflexão
    public String serialize(T objeto) throws IllegalAccessException {
        StringBuilder stringBuilder = new StringBuilder();
        String idUnico = objeto.getIdUnico();
        if (idUnico == null) {
            throw new IllegalArgumentException("ID único não pode ser nulo!");
        }
        stringBuilder.append(idUnico).append(";");

        for (Field field : objeto.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(objeto);
            stringBuilder.append(value != null ? value.toString() : "null").append(";");
        }
        return stringBuilder.toString();
    }

    // Método deserialize atualizado para usar reflexão
    public T deserialize(String serialized) throws ReflectiveOperationException {
        String[] parts = serialized.split(";");
        T objeto = tipoEntidade.getDeclaredConstructor().newInstance();

        Field[] fields = tipoEntidade.getDeclaredFields();
        for (int i = 0; i < fields.length && i < parts.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);

            String value = parts[i].trim();
            if (!value.equals("null")) {
                if (field.getType().equals(int.class) || field.getType().equals(Integer.class)) {
                    field.set(objeto, Integer.parseInt(value));
                } else if (field.getType().equals(double.class) || field.getType().equals(Double.class)) {
                    field.set(objeto, Double.parseDouble(value));
                } else if (field.getType().equals(LocalDate.class)) {
                    field.set(objeto, LocalDate.parse(value));
                } else {
                    field.set(objeto, value);
                }
            }
        }
        return objeto;
    }




    // Método incluir atualizado para usar cache
public boolean incluir(T entidade) {
    try {
        // Verifica se já está no cache
        if (cache.containsKey(entidade.getIdUnico())) {
            return false;  // Já existe no cache
        }

        // Verifica se já existe no arquivo
        if (this.buscar(entidade.getIdUnico()) != null) {
            return false;  // Já existe no arquivo
        }

        // Tenta escrever no arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(obterNomeArquivo(entidade), true))) {
            writer.write(serialize(entidade));
            writer.newLine();
            cache.put(entidade.getIdUnico(), entidade);  // Atualiza o cache
            return true;
        } catch (IOException e) {
            // Trata a exceção se houver erro ao escrever o arquivo
            System.out.println("Erro ao escrever o arquivo: " + e.getMessage());
            return false;  // Retorna falso em caso de erro
        }

    } catch (IllegalAccessException e) {
        // Captura a exceção se não for possível acessar o campo/membro
        System.out.println("Erro de acesso: " + e.getMessage());
        return false;  // Retorna falso em caso de erro de acesso
    } catch (Exception e) {
        // Captura qualquer outra exceção genérica
        System.out.println("Erro inesperado: " + e.getMessage());
        return false;  // Retorna falso em caso de erro inesperado
    }
}


    // Método alterar atualizado para usar cache
    public boolean alterar(T objeto) {
        boolean found = false;
        List<String> lines = new ArrayList<>();
    
        try (BufferedReader reader = new BufferedReader(new FileReader(obterNomeArquivo(objeto)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String id = parts[0];
                String idUnicoObjeto = objeto.getIdUnico();
    
                // Se o id do objeto corresponder, substituímos a linha
                if (idUnicoObjeto.equals(id)) {
                    line = serialize(objeto);
                    found = true;
                }
                lines.add(line);
            }
        } catch (IOException | IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
    
        if (!found) return false;
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(obterNomeArquivo(objeto)))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            // Atualiza o cache após alteração
            cache.put(objeto.getIdUnico(), objeto);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    
        return found;
    }
    // Método excluir atualizado para usar cache
    public boolean excluir(String idUnico) {
        List<String> lines = new ArrayList<>();
        boolean found = false;
        if(this.buscar(idUnico)==null){
            return false;
        }
        Entidade entidade = this.buscar(idUnico);

        try (BufferedReader reader = new BufferedReader(new FileReader(obterNomeArquivo(entidade)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 0) continue;

                String linhaIdUnico = parts[0].trim();
                if (!linhaIdUnico.equals(idUnico)) {
                    lines.add(line);
                } else {
                    found = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (!found) {
            return false;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(obterNomeArquivo(entidade)))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            // Remove o objeto do cache após confirmação da exclusão no arquivo
            cache.remove(idUnico);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método buscar atualizado para usar cache
    public T buscar(String idUnico) {
        // Primeiro verifica se o objeto está no cache
        T objetoCache = cache.get(idUnico);
        if (objetoCache != null) {
            return objetoCache;
        }
    
        // Se não estiver no cache, busca no arquivo
        try (BufferedReader reader = new BufferedReader(new FileReader(obterNomeArquivo(idUnico)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 0) continue;
    
                String linhaIdUnico = parts[0].trim();
                if (linhaIdUnico.equals(idUnico)) {
                    T objeto = deserialize(line);
                    // Atualiza o cache antes de retornar
                    cache.put(idUnico, objeto);
                    return objeto;
                }
            }
        } catch (IOException | ReflectiveOperationException e) {
            e.printStackTrace();
        }
    
        return null;
    }
    
	private File[] listarArquivosDir(String caminhoDir) {
		return new File(caminhoDir).listFiles();
    }
    public T[] buscarTodos() {
        List<T> lista = new ArrayList<>();
        
        // Listando todos os arquivos no diretório
        File[] arquivos = listarArquivosDir(NOME_DIR);
        
        // Verifica se o diretório contém arquivos
        if (arquivos != null) {
            for (File arquivo : arquivos) {
                // Processa apenas arquivos e não diretórios
                if (arquivo.isFile()) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            String[] parts = line.split(";");
                            if (parts.length == 0) continue;
                            
                            String idUnico = parts[0].trim();
                            // Verifica se o objeto já está no cache
                            T objeto = cache.get(idUnico);
                            if (objeto == null) {
                                // Se não estiver no cache, deserializa e adiciona
                                objeto = deserialize(line);
                                cache.put(idUnico, objeto);
                            }
                            lista.add(objeto);
                        }
                    } catch (IOException | ReflectiveOperationException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    
        // Retorna todos os objetos encontrados em todos os arquivos
        return lista.toArray((T[]) java.lang.reflect.Array.newInstance(tipoEntidade, lista.size()));
    }
    //Getters e Setters
    public String getNomeDiretorio() {
        return NOME_DIR;
    }
    
    
    // Método adicional para limpar o cache se necessário
    public void limparCache() {
        cache.clear();
    }
}
*/
