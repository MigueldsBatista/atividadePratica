package br.com.cesarschool.poo.titulos.repositorios;

import java.util.*;

import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.daogenerico.RepositorioGeral;

public class RepositorioTransacao extends RepositorioGeral<Transacao> {


    @Override
    public Class<Transacao> getClasseEntidade() {
        return Transacao.class;
    }
    
    //Metodo refatorado pra usar a nova estrutura do DAO genérico
    public Transacao[] buscarPorEntidadeCredora(int identificadorEntidadeCredito) {
        // Recupera todas as transações do DAO
        List<Transacao> transacoes = Arrays.asList(dao.buscarTodos());
        List<Transacao> transacoesFiltradas = new ArrayList<>();

        // Filtra as transações que têm a entidadeCredora com o id especificado
        for (Transacao transacao : transacoes) {
            if (transacao.getEntidadeCredito().getIdentificador() == identificadorEntidadeCredito) {
                transacoesFiltradas.add(transacao);
            }
        }

        // Retorna as transações filtradas
        return transacoesFiltradas.toArray(new Transacao[0]);
    }

}