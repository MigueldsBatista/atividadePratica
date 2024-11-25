package br.com.cesarschool.poo.daogenerico;


import java.io.*;
import java.time.LocalDateTime;
import java.util.*;


public class DAOSerializadorObjetos<T extends Entidade> {
    private static final String SEP_ARQUIVO = System.getProperty("file.separator");
    private static final String PONTO = ".";
    private String NOME_DIR;  // Alterado para ser variável

    private Class<T> tipoEntidade;
    private Map<String, T> cache;

    public DAOSerializadorObjetos(Class<T> tipoEntidade) {
        this.tipoEntidade = tipoEntidade;
        this.cache = new HashMap<>();
        this.NOME_DIR = PONTO + SEP_ARQUIVO + tipoEntidade.getSimpleName();  // Usando o nome da classe específica
        createIfNotExist();
    }

    private void createIfNotExist() {
        File dir = new File(NOME_DIR);
        if (!dir.exists()) {
            dir.mkdirs();  // Cria o diretório, incluindo quaisquer diretórios pai
        }
    }


    // Verifica e garante que o arquivo existe
    private File obterArquivo(T entidade) {
        File arquivo = new File(NOME_DIR + SEP_ARQUIVO + entidade.getIdUnico());
        try {
            if (!arquivo.exists()) {
                arquivo.createNewFile();  // Cria o arquivo se ele não existir
            }
        } catch (IOException e) {
            System.out.println("Erro ao garantir a criação do arquivo: " + e.getMessage());
        }
        return arquivo;
    }
    // Método incluir atualizado para usar FileInputStream e FileOutputStream com Object Streams
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

            // Garante que o arquivo exista
            File arquivo = obterArquivo(entidade);

            // Usa ObjectOutputStream para escrever objetos no arquivo
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo, true))) {
                entidade.setDataHoraInclusao(LocalDateTime.now());
                entidade.setUsuarioInclusao(System.getProperty("user.name"));
                entidade.setUsuarioUltimaAlteracao(System.getProperty("user.name"));
                entidade.setDataHoraUltimaAlteracao(LocalDateTime.now());
                oos.writeObject(entidade);
                cache.put(entidade.getIdUnico(), entidade);  // Atualiza o cache
                return true;
            } catch (IOException e) {
                // Trata a exceção se houver erro ao escrever o arquivo
                System.out.println("Erro ao escrever o arquivo: " + e.getMessage());
                return false;
            }

        } catch (Exception e) {
            // Captura qualquer exceção genérica
            System.out.println("Erro inesperado: " + e.getMessage());
            return false;
        }
    }

    // Método alterar atualizado para usar ObjectInputStream e ObjectOutputStream
    public boolean alterar(T entidade) {
        boolean found = false;
        List<T> objectsList = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(obterNomeArquivo(entidade)))) {
            T existingObject;
            while ((existingObject = tipoEntidade.cast(ois.readObject())) != null) {
                if (existingObject.getIdUnico().equals(entidade.getIdUnico())) {
                    entidade.setUsuarioUltimaAlteracao(System.getProperty("user.name"));
                    entidade.setDataHoraUltimaAlteracao(LocalDateTime.now());
                    objectsList.add(entidade);  // Substitui o entidade
                    found = true;
                } else {
                    objectsList.add(existingObject);  // Mantém os outros objetos
                }
            }
        } catch (EOFException e) {
            // Fim do arquivo, podemos continuar
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        if (!found) return false;

        // Reescreve o arquivo com a lista atualizada de objetos
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(obterNomeArquivo(entidade)))) {
            for (T entity : objectsList) {
                oos.writeObject(entity); // Serializa os objetos novamente
            }
            cache.put(entidade.getIdUnico(), entidade);  // Atualiza o cache
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // Método excluir atualizado para remover o arquivo
    public boolean excluir(String idUnico) {
        File arquivo = new File(obterNomeArquivo(idUnico));

        if (!arquivo.exists()) {
            return false;  // Arquivo não encontrado
        }

        if (arquivo.delete()) {
            cache.remove(idUnico);  // Remove o entidade do cache
            return true;
        }

        return false;
    }
    // Método buscar atualizado para usar FileInputStream e ObjectInputStream
    public T buscar(String idUnico) {
        // Primeiro verifica se o entidade está no cache
        T objetoCache = cache.get(idUnico);
        if (objetoCache != null) {
            return objetoCache;
        }

        // Se não estiver no cache, busca no arquivo
        try {
            File arquivo = new File(NOME_DIR + SEP_ARQUIVO + idUnico);
            if (arquivo.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
                    T entidade = tipoEntidade.cast(ois.readObject());
                    // Atualiza o cache antes de retornar
                    cache.put(idUnico, entidade);
                    return entidade;
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // Método buscarTodos atualizado para usar ObjectInputStream
    public T[] buscarTodos() {
        List<T> lista = new ArrayList<>();
        File[] arquivos = listarArquivosDir(NOME_DIR);

        // Verifica se o diretório contém arquivos
        if (arquivos != null) {
            Arrays.sort(arquivos, Comparator.comparing(File::getName));
            for (File arquivo : arquivos) {
                if (arquivo.isFile()) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
                        T entidade;
                        Object obj;
                        while ((obj = ois.readObject()) != null) {
                            entidade = tipoEntidade.cast(obj);
                            lista.add(entidade);
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // Retorna todos os objetos encontrados
        @SuppressWarnings("unchecked")
        T[] array = (T[]) java.lang.reflect.Array.newInstance(tipoEntidade, lista.size());
        return lista.toArray(array);
    }

    private File[] listarArquivosDir(String caminhoDir) {
        return new File(caminhoDir).listFiles();
    }

    private String obterNomeArquivo(T entidade) {
        return NOME_DIR + SEP_ARQUIVO + entidade.getIdUnico();
    }

    private String obterNomeArquivo(String idUnico) {
        return NOME_DIR + SEP_ARQUIVO + idUnico;
    }


    public void limparCache() {
        cache.clear();
    }
}