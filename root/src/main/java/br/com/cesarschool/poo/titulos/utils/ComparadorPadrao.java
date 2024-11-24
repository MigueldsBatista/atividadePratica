package br.com.cesarschool.poo.titulos.utils;

public class ComparadorPadrao {
    public static <T extends Comparavel> int compare(T c1, T c2){
        return c1.comparar(c2);
    }
}
