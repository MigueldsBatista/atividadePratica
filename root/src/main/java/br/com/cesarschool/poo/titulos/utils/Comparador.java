package br.com.cesarschool.poo.titulos.utils;

public interface Comparador {
    /**
     * Compara dois objetos Comparavel
     * 
     * @param c1 O primeiro objeto Comparavel
     * @param c2 O segundo objeto Comparavel
     * @return  Um valor maior que zero se c1 for maior que c2
     *          Um valor menor que zero se c1 for menor que c2
     *          Zero se os objetos forem iguais
     */
    int comparar(Comparavel c1, Comparavel c2);
}
