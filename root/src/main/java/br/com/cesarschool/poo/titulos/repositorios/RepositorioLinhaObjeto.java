package br.com.cesarschool.poo.titulos.repositorios;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.*;
import java.util.*;

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

    private String serialize(T objeto) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        for (Field field : objeto.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(objeto);
            sb.append(value != null ? value.toString() : "null").append(";");
        }
        return sb.toString();
    }
    
}