package br.com.cesarschool.poo.titulos.relatorios;

import br.com.cesarschool.poo.titulos.repositorios.RepositorioTransacao;


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

//TODO  Implementar método que retorna um array de transações ordenado por nome da entidade credora
public Transacao[] relatorioTransacaoPorNomeEntidadeCredora(){
    return null;
    //compara aq
}

//TODO  Implementar método que retorna um array de transações ordenado por data hora da operação

public Transacao[] relatorioTransacaoPorDataHora(){

    return null;
    //compara aq
    
    }
}
    