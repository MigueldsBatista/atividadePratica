package br.com.cesarschool.poo.titulos.repositorios;
import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.entidades.Transacao;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProgramaTeste {
    public static void main(String[] args) {
        System.out.println("Teste");


        RepositorioAcao repositorioAcao = new RepositorioAcao();
        Acao acao = new Acao(1, "Ação 1", LocalDate.parse("2021-12-31"), 100.0);
        repositorioAcao.incluir(acao);

        RepositorioEntidadeOperadora repositorioEntidadeOperadora = new RepositorioEntidadeOperadora();
        EntidadeOperadora entidadeOperadoraCredito = new EntidadeOperadora(1, "Entidade 1", 100.0);
        repositorioEntidadeOperadora.incluir(entidadeOperadoraCredito);
        EntidadeOperadora entidadeOperadoraDebito = new EntidadeOperadora(2, "Entidade 2", 100.0);  
        repositorioEntidadeOperadora.incluir(entidadeOperadoraDebito);

        RepositorioTituloDivida repositorioTituloDivida = new RepositorioTituloDivida();
        TituloDivida tituloDivida = new TituloDivida(1, "Título 1", LocalDate.parse("2021-12-31"), 100.0);
        repositorioTituloDivida.incluir(tituloDivida);

        RepositorioTransacao repositorioTransacao = new RepositorioTransacao();

        Transacao transacao = new Transacao(entidadeOperadoraCredito, entidadeOperadoraDebito, acao, tituloDivida, 100.0, LocalDateTime.now());
        repositorioTransacao.incluir(transacao);

    }
}
