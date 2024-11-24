package br.com.cesarschool.poo.titulos.utils;

public interface Comparavel {
    /**
     * Compara o objeto atual com outro objeto Comparavel
     * 
     * @param c O objeto Comparavel a ser comparado com o objeto atual
     * @return  Um valor maior que zero se o objeto atual for maior que o parâmetro
     *          Um valor menor que zero se o objeto atual for menor que o parâmetro
     *          Zero se os objetos forem iguais
     */
    int comparar(Comparavel c);
}