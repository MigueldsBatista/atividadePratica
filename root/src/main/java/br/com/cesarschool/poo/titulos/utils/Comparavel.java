package br.com.cesarschool.poo.titulos.utils;

public interface Comparavel {
    /*É uma interface similar a Comparable do JAVA Core. Tem
um único método que prevê a comparação do Comparavel
atual com outro recebido como parâmetro. int
comparar(Comparavel c).
Se o elemento atual for maior que o parâmetro, o método
deve retornar um valor maior que zero, se for menor, o
método deve retornar um valor menor que zero, e se for
igual, zero. */

    int comparar(Comparavel c);

} 