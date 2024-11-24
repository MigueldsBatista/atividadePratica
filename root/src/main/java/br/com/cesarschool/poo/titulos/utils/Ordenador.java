package br.com.cesarschool.poo.titulos.utils;

public class Ordenador {
/*É uma classe que implementa o algoritmo de ordenação de
arrays de comparáveis. Tem dois métodos estáticos:
public static void ordenar(Comparavel[] ents, Comparador
comp)
public static void ordenar(Comparavel[] comps)
O primeiro ordena o array de entrada perguntando ao
comparador se dois elementos são maiores, menores ou
iguais.
O segundo ordena o array de entrada perguntando a um
elemento se ele é maior, menor ou igual a outro elemento. */

    public static void ordenar(Comparavel[] ents, Comparador comp) {
        for (int i = 0; i < ents.length; i++) {
            for (int j = i + 1; j < ents.length; j++) {
                if (comp.comparar(ents[i], ents[j]) > 0) {
                    Comparavel temp = ents[i];
                    ents[i] = ents[j];
                    ents[j] = temp;
                }
            }
        }
    }

    public static void ordenar(Comparavel[] comps) {
        for (int i = 0; i < comps.length; i++) {
            for (int j = i + 1; j < comps.length; j++) {
                if (comps[i].comparar(comps[j]) > 0) {
                    Comparavel temp = comps[i];
                    comps[i] = comps[j];
                    comps[j] = temp;
                }
            }
        }
    }
}
