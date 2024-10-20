package br.com.cesarschool.poo.titulos.entidades;

import java.time.LocalDate;

/*
 * Esta classe deve ter os seguintes atributos:
 * identificador, do tipo int
 * nome, do tipo String
 * data de validade, do tipo LocalDate
 * 
 * Deve ter um construtor p�blico que inicializa os atributos, 
 * e m�todos set/get p�blicos para os atributos. O atributo identificador
 * � read-only fora da classe.
 */

public class Ativo {
private int identificador;
private String nome;
private LocalDate dataValidade;

public Ativo(int identificador, String nome, LocalDate dataValidade) {
    this.identificador = identificador;
    this.nome = nome;
    this.dataValidade = dataValidade;
}

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdentificador() {
        return identificador;
    }

    

}
