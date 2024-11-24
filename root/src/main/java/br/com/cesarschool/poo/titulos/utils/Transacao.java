package br.com.cesarschool.poo.titulos.utils;

import java.util.Date;

public class Transacao implements Comparavel{       
/*Deve implementar Comparavel, e o critério de comparação
que ela deve usar é o de data e hora da operação. */
    private Date dataHora;
    public int comparar(Comparavel t){
        Transacao outra = (Transacao) t;
        return this.dataHora.compareTo(outra.dataHora); 
    }
}
