package br.com.cesarschool.poo.daogenerico;
/*
* Esta classe representa um DAO genérico, que inclui, exclui, altera, busca por identificador 
* único e busca todos, qualquer objeto(s) cujo tipo é subtipo de Entidade.
* 
 * Sugerimos o uso da API de serialização do JAVA, que grava e lê objetos em arquvos. 
 * Lembrar sempre de fechar (em qualquer circunstância) streams JAVA abertas.
 * 
 * As nuances mais detalhadas do funcionamento desta classe está especificada na classe de testes
 * automatizados br.gov.cesarschool.poo.testes.TestesDAOSerializador.    
 * 
 * A classe deve ter a estrutura (métodos e construtores) dada abaixo.
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.lang.reflect.Field;
// Classe que serializa e desserializa objetos

public class DAOSerializadorObjetos<T extends Entidade> {
    //estou impondo um contrato que o tipo T deve ser uma subclasse de Entidade

    private String nomeDiretorio;
    private Class<T> tipoEntidade;

    // Construtor que usa o tipo genérico T para gerar o nome do arquivo
    public DAOSerializadorObjetos(Class<T> tipoEntidade) {
        this.tipoEntidade = tipoEntidade;
        this.nomeDiretorio = tipoEntidade.getSimpleName() + ".txt";
    }

    // Serialização de um objeto para String
    public String serialize(T objeto) throws IllegalAccessException {
        StringBuilder stringBuilder = new StringBuilder();

        // Aqui chamamos o método getIdUnico() para gerar o identificador único
        String idUnico = objeto.getIdUnico();
        if (idUnico == null) {
            throw new IllegalArgumentException("ID único não pode ser nulo!");
        }
        stringBuilder.append(idUnico).append(";");

        // Itera sobre os campos da classe e adiciona seus valores à string
        for (Field field : objeto.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(objeto);
            stringBuilder.append(value != null ? value.toString() : "null").append(";");
        }

        return stringBuilder.toString();
    }

    // Desserialização de uma String para Objeto
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

    // Incluir objeto no arquivo
    public boolean incluir(T entidade) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeDiretorio, true))) {
            writer.write(serialize(entidade));
            writer.newLine();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Alterar um objeto no arquivo
    public boolean alterar(T objeto) {
        boolean found = false;
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeDiretorio))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String id = parts[0]; // O ID único fica na primeira posição

                String idUnicoObjeto = objeto.getIdUnico(); // Pega o id único do objeto

                if (idUnicoObjeto.equals(id)) {
                    line = serialize(objeto); // Substitui a linha pelo objeto atualizado
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
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // Excluir um objeto pelo ID único
    public boolean excluir(String idUnico) {
        List<String> lines = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeDiretorio))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 0) continue;

                String linhaIdUnico = parts[0].trim(); // Primeiro campo é o ID único
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
            System.out.println("ID único não encontrado: " + idUnico);
            return false;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeDiretorio))) {
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

    // Buscar um objeto pelo ID único
    public T buscar(String idUnico) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeDiretorio))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 0) continue;

                String linhaIdUnico = parts[0].trim(); // O primeiro campo é o ID único
                if (linhaIdUnico.equals(idUnico)) {
                    return deserialize(line); // Reconstrói o objeto com base na linha
                }
            }
        } catch (IOException | ReflectiveOperationException e) {
            e.printStackTrace();
        }

        return null; // Retorna null caso o ID não seja encontrado
    }

    // Buscar todos os objetos
    public List<T> buscarTodos() {
        List<T> lista = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeDiretorio))) {
            String line;
            while ((line = reader.readLine()) != null) {
                T objeto = deserialize(line);
                lista.add(objeto);
            }
        } catch (IOException | ReflectiveOperationException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public String getNomeDiretorio() {
        return nomeDiretorio;
    }

    public void setNomeDiretorio(String nomeDiretorio) {
        this.nomeDiretorio = nomeDiretorio;
    }
}
