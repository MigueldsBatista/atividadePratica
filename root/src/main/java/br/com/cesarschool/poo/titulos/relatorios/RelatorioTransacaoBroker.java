package br.com.cesarschool.poo.titulos.relatorios;

import br.com.cesarschool.poo.titulos.repositorios.RepositorioTransacao;

import java.util.ArrayList;
import java.util.Arrays;

import br.com.cesarschool.poo.titulos.entidades.Transacao;

public class RelatorioTransacaoBroker {
    private final RepositorioTransacao rep;
/*Tem os dois métodos mencionados acima, que devem ler
do repositório de transações todas as transações e...

1. METODO = ordenar por nome da entidade credora
2. METODO = ordenar por data hora da operação

*/

public RelatorioTransacaoBroker(RepositorioTransacao rep){
    this.rep=rep;
}

public Transacao[] relatorioTransacaoPorNomeEntidadeCredora(){
    ArrayList<Transacao> transacoes = new ArrayList<>();
    transacoes.addAll(Arrays.asList(rep.buscarTodos()));
    transacoes.sort((t1,t2)->t1.getEntidadeCredito().getNome().compareTo(t2.getEntidadeCredito().getNome()));
    
    return transacoes.toArray(new Transacao[0]);//converte para array 
}


public Transacao[] relatorioTransacaoPorDataHora(){
    ArrayList<Transacao> transacoes = new ArrayList<>();
    transacoes.addAll(Arrays.asList(rep.buscarTodos()));
    transacoes.sort((t1,t2)->t2.getDataHoraOperacao().compareTo(t1.getDataHoraOperacao()));
    return transacoes.toArray(new Transacao[0]);

    }
}
    