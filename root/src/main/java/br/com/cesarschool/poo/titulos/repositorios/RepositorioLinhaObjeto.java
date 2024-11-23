package br.com.cesarschool.poo.titulos.repositorios;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.com.cesarschool.poo.titulos.entidades.Acao;;

public class RepositorioLinhaObjeto<T>{
    private final Path path;
    public RepositorioLinhaObjeto(String fileName){
        this.path = Paths.get("root/database", fileName);
        criarArquivoSeNaoExistir();
    }

    private void criarArquivoSeNaoExistir(){
        try{
            if(!Files.exists(path)){
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private String serialize(T objeto) throws IllegalAccessException {
        StringBuilder stringBuilder = new StringBuilder();
        for (Field field : objeto.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(objeto);
            stringBuilder.append(value != null ? value.toString() : "null").append(";");
        }
        return stringBuilder.toString();
    }

    public boolean incluir(T objeto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile(), true))) {
            writer.write(serialize(objeto));
            writer.newLine();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean alterar(T objeto) {
        boolean found = false;
        List<String> lines = new ArrayList<>();
    
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                int id = Integer.parseInt(parts[0]);
                Field field = objeto.getClass().getDeclaredField("identificador"); 
                field.setAccessible(true);
                int objetoId = (int) field.get(objeto); 
                if (id == objetoId) {
                    line = serialize(objeto);
                    found = true;
                }
    
                lines.add(line);
            }
        } catch (IOException | NoSuchFieldException | IllegalAccessException e) {
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
    
    public boolean excluir(int id) {
        List<String> lines = new ArrayList<>();
        boolean found = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                int lineId = Integer.parseInt(parts[0]); 
                if (lineId != id) {
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
    
    public T buscar(int id) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                int lineId = Integer.parseInt(parts[0]);
                if (lineId == id) {
                    T objeto = (T) Object.class.getDeclaredConstructor().newInstance();
                    Field fieldNome = objeto.getClass().getDeclaredField("nome");
                    fieldNome.setAccessible(true);
                    fieldNome.set(objeto, parts[1]);
                    Field fieldDataValidade = objeto.getClass().getDeclaredField("dataValidade");
                    fieldDataValidade.setAccessible(true);
                    LocalDate dataValidade = LocalDate.parse(parts[2], formatter);
                    fieldDataValidade.set(objeto, dataValidade);
                    Field fieldValorUnitario = objeto.getClass().getDeclaredField("valorUnitario");
                    fieldValorUnitario.setAccessible(true);
                    fieldValorUnitario.set(objeto, Double.parseDouble(parts[3]));
                    return objeto; 
                }
            }
        } catch (IOException | NoSuchFieldException | IllegalAccessException | InstantiationException |
                NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}