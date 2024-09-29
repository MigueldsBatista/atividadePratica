package br.com.cesarschool.poo.titulos.entidades;

/*
 * Esta classe deve ter os seguintes atributos:
 * identificador, do tipo long
 * nome, do tipo String
 * autorizadoAcao, do tipo double
 * saldoAcao, do tipo double
 * saldoTituloDivida, do tipo double
 * 
 * Deve ter um construtor p�blico que inicializa os atributos identificador, nome
 * e autorizadoAcao. Deve ter m�todos set/get p�blicos para os atributos identificador, nome
 * e autorizadoAcao. O atributo identificador � read-only fora da classe.
 * 
 * Os atributos saldoAcao e saldoTituloDivida devem ter apenas m�todos get p�blicos.
 * 
 * Outros m�todos p�blicos:
 * 
 *  void creditarSaldoAcao(double valor): deve adicionar valor ao saldoAcao
 *  void debitarSaldoAcao(double valor): deve diminuir valor de saldoAcao
 *  void creditarSaldoTituloDivida(double valor): deve adicionar valor ao saldoTituloDivida
 *  void debitarSaldoTituloDivida(double valor): deve diminuir valor de saldoTituloDivida  
 */

public class EntidadeOperadora {
private int identificador;
private double autorizadoAcao;
private double saldoAcao;
private double saldoTituloDivida;

    public EntidadeOperadora(int identificador, double autorizadoAcao, double saldoAcao, double saldoTituloDivida){
        this.identificador=identificador;
        this.autorizadoAcao=autorizadoAcao;
        this.saldoAcao=saldoAcao;
        this.saldoTituloDivida=saldoTituloDivida;
    }

    public double getSaldoAcao() {
        return saldoAcao;
    }

    public double getAutorizadoAcao() {
        return autorizadoAcao;
    }
    
    public double getSaldoTituloDivida() {
        return saldoTituloDivida;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void creditarSaldoAcao(double valor){
        saldoAcao=saldoAcao-valor;
    }

    public void debitarSaldoAcao(double valor){
        saldoAcao=saldoAcao-valor;

    }
    public void creditarSaldoTituloDivida(double valor){
        saldoTituloDivida=saldoTituloDivida-valor;
    }

    public void debitarSaldoTituloDivida(double valor){
        saldoTituloDivida=saldoTituloDivida-valor;
    }
}
