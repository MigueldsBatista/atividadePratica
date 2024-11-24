package br.com.cesarschool.poo.titulos.utils;

import br.com.cesarschool.poo.titulos.entidades.Transacao;

public class ComparadorTransacaoPorNomeCredora extends ComparadorPadrao implements Comparador{
/*
 * Herda de ComparadorPadrao. Deve implementar
Comparador, e o critério de comparação que ela deve usar
é o de *nome* da entidade credora.
 */
    public int comparar(Comparavel c1, Comparavel c2){
        Transacao t1 = (Transacao) c1;
        Transacao t2 = (Transacao) c2;
        return t1.getEntidadeCredito().getNome().compareTo(t2.getEntidadeCredito().getNome());
    }
}
