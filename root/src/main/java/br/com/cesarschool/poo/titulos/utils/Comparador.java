package br.com.cesarschool.poo.titulos.utils;

public class Comparador{
/*É uma interface similar a Comparator do JAVA Core. Tem
um único método que prevê a comparação de dois
Comparavel recebidos como parâmetro. int
comparar(Comparavel c1, Comparavel c2).

Se o primeiro parâmetro for maior que o segundo
parâmetro, o método deve retornar um valor maior que
zero, se for menor, o método deve retornar um valor menor
que zero, e se for igual, zero. */

    public int compare(Comparavel c1, Comparavel c2){
            return c1.comparar(c2);
    }
}
