package br.com.cesarschool.poo.titulos.entidades;

import java.time.LocalDateTime;

import br.com.cesarschool.poo.daogenerico.Entidade;
import br.com.cesarschool.poo.titulos.utils.Comparavel;

/*
 * Esta classe deve ter os seguintes atributos:
 * entidadeCredito, do tipo EntidadeOperadora
 * entidadeDebito, do tipo EntidadeOperadora
 * acao, do tipo Acao
 * tituloDivida, do tipo TituloDivida
 * valorOperacao, do tipo double
 * dataHoraOperacao, do tipo LocalDateTime
 *  
 * Deve ter um construtor público que inicializa os atributos.
 * Deve ter métodos get/set públicos para todos os atributos, que 
 * são read-only fora da classe.
 * 
 * 
 * Transacao é a concatenação de:  
Id único da entidade de crédito + “_” + 
Id único da entidade de débito + “_” + 
Id único da ação OU id único de título da dívida + “_” + Data e hora da operação formatada como yyyymmddhhmmss 
 */ 

/*Deve implementar Comparavel, e o critério de comparação
que ela deve usar é o de data e hora da operação. */
public class Transacao extends Entidade implements Comparavel{
    private EntidadeOperadora entidadeCredito;
    private EntidadeOperadora entidadeDebito;
    private Acao acao;
    private TituloDivida tituloDivida;
    private double valorOperacao;
    private LocalDateTime dataHoraOperacao;

    public Transacao(EntidadeOperadora entidadeCredito, EntidadeOperadora entidadeDebito, Acao acao, TituloDivida tituloDivida, double valorOperacao, LocalDateTime dataHoraOperacao) {
        this.entidadeCredito = entidadeCredito;
        this.entidadeDebito = entidadeDebito;
        this.acao = acao;
        this.tituloDivida = tituloDivida;
        this.valorOperacao = valorOperacao;
        this.dataHoraOperacao = dataHoraOperacao;
    }


    public int comparar(Comparavel t){
            return this.dataHoraOperacao.compareTo(((Transacao)t).dataHoraOperacao);
        }

    @Override
    public String getIdUnico() {
    //lembra da data e hora da operação formatada como yyyymmddhhmmss
    return entidadeCredito.getIdUnico()+"_"+
    entidadeDebito.getIdUnico()+"_"+
    (acao!=null?acao.getIdUnico():tituloDivida.getIdUnico())+"_"+
    dataHoraOperacao.toString()
    .replace("T", "")
    .replace(":", "")
    .replace("-", "")
    .substring(0, 14);
    //Exemplo: 1_2_3_20210101120000
    }
    
    public EntidadeOperadora getEntidadeDebito(){
        return entidadeDebito;
    }

    public EntidadeOperadora getEntidadeCredito(){
        return entidadeCredito;
    }

    public Acao getAcao(){
        return acao;
    }

    public TituloDivida getTituloDivida(){
        return tituloDivida;
    }

    public double getValorOperacao(){
        return valorOperacao;
    }

    public LocalDateTime getDataHoraOperacao(){
        return dataHoraOperacao;
    }

    public void setEntidadeCredito(EntidadeOperadora entidadeCredito) {
        this.entidadeCredito = entidadeCredito;
    }

    public void setEntidadeDebito(EntidadeOperadora entidadeDebito) {
        this.entidadeDebito = entidadeDebito;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    public void setTituloDivida(TituloDivida tituloDivida) {
        this.tituloDivida = tituloDivida;
    }

    public void setValorOperacao(double valorOperacao) {
        this.valorOperacao = valorOperacao;
    }

    public void setDataHoraOperacao(LocalDateTime dataHoraOperacao) {
        this.dataHoraOperacao = dataHoraOperacao;
    }
}
