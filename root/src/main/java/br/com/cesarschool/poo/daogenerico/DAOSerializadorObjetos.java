package br.com.cesarschool.poo.daogenerico;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Field;

public class DAOSerializadorObjetos<T extends Entidade> {
    private String nomeDiretorio;
    private Class<T> tipoEntidade;

    private Map<String, T> cache;

    public DAOSerializadorObjetos(Class<T> tipoEntidade) {
        this.tipoEntidade = tipoEntidade;
        this.nomeDiretorio = tipoEntidade.getSimpleName() + ".txt";
        this.cache = new HashMap<>();
    }

    // Métodos de serialização e deserialização permanecem iguais
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeDiretorio, true))) {
            writer.write(serialize(entidade));
            writer.newLine();
            // Atualiza o cache com o novo objeto
            cache.put(entidade.getIdUnico(), entidade);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método alterar atualizado para usar cache
    public boolean alterar(T objeto) {
        boolean found = false;
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeDiretorio))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String id = parts[0];
                String idUnicoObjeto = objeto.getIdUnico();

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

        if (!found) {
            return false;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeDiretorio))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            // Atualiza o cache após confirmação da alteração no arquivo
            cache.put(objeto.getIdUnico(), objeto);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método excluir atualizado para usar cache
    public boolean excluir(String idUnico) {
        List<String> lines = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeDiretorio))) {
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

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeDiretorio))) {
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
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeDiretorio))) {
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

    // Método buscarTodos atualizado para usar cache
    public T[] buscarTodos() {
        List<T> lista = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeDiretorio))) {
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
        
        return lista.toArray((T[]) java.lang.reflect.Array.newInstance(tipoEntidade, lista.size()));
    }

    //Getters e Setters
    public String getNomeDiretorio() {
        return nomeDiretorio;
    }
    
    public void setNomeDiretorio(String nomeDiretorio) {
        this.nomeDiretorio = nomeDiretorio;
    }

    // Método adicional para limpar o cache se necessário
    public void limparCache() {
        cache.clear();
    }
}