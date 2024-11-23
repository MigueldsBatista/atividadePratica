package br.com.cesarschool.poo.daogenerico;

import java.time.LocalDateTime;

/*
 * Esta classe representa uma superclasse de todas as entidades. 
 * 
 * Deve ter os seguintes atributos (com respectivos set e get): 
 *	LocalDateTime dataHoraInclusao, 
 *  LocalDateTime dataHoraUltimaAlteracao,
 *	String usuarioInclusao e 
 *	String usuarioUltimaAlteracao
 *
 * Deve ter um único construtor sem parâmetros.
 * 
 * Deve ser abstrata.
 * 
 * Deve ter um método abstrato getIdUnico().
 * 
 * Deve implementar a interface Serializable do JAVA
 */

public abstract class Entidade implements java.io.Serializable {
    
    LocalDateTime dataHoraInclusao;
    LocalDateTime dataHoraUltimaAlteracao;
    String usuarioInclusao;
    String usuarioUltimaAlteracao;

    public Entidade() {
    }


    public abstract int getIdUnico();

    public LocalDateTime getDataHoraInclusao() {
        return dataHoraInclusao;
    }

    public LocalDateTime getDataHoraUltimaAlteracao() {
        return dataHoraUltimaAlteracao;
    }

    public String getUsuarioInclusao() {
        return usuarioInclusao;
    }

    public String getUsuarioUltimaAlteracao() {
        return usuarioUltimaAlteracao;
    }

    public void setDataHoraInclusao(LocalDateTime dataHoraInclusao) {
        this.dataHoraInclusao = dataHoraInclusao;
    }

    public void setDataHoraUltimaAlteracao(LocalDateTime dataHoraUltimaAlteracao) {
        this.dataHoraUltimaAlteracao = dataHoraUltimaAlteracao;
    }

    public void setUsuarioInclusao(String usuarioInclusao) {
        this.usuarioInclusao = usuarioInclusao;
    }

    public void setUsuarioUltimaAlteracao(String usuarioUltimaAlteracao) {
        this.usuarioUltimaAlteracao = usuarioUltimaAlteracao;
    }

}

