package br.com.cesarschool.poo.titulos.entidades;

/*
 * Esta classe deve ter os seguintes atributos:
 * identificador, do tipo int
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
    public String nome;
    private boolean autorizadoAcao;
    private double saldoAcao=0.0;
    private double saldoTituloDivida=0.0;

    public EntidadeOperadora(int identificador,String nome, boolean autorizadoAcao, double saldoTituloDivida, double saldoAcao){
        this.identificador=identificador;
        this.nome=nome;
        this.autorizadoAcao=true;
        this.saldoAcao=saldoAcao;
        this.saldoTituloDivida=saldoTituloDivida;

    }

    public int getIdentificador(){
        return identificador;
    }

    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome=nome;
    }

    public boolean getAutorizacao(){
        return autorizadoAcao;
    }
    public void setAutorizacao(boolean autorizadoAcao){
        this.autorizadoAcao=autorizadoAcao;
    }

    public double getSaldoAcao() {
        return saldoAcao;
    }

    public double getSaldoTituloDivida() {
        return saldoTituloDivida;
    }

    public void creditarSaldoAcao(double valor) {
        saldoAcao += valor;
    }

    public void debitarSaldoAcao(double valor) {
        if (saldoAcao >= valor ) {
            saldoAcao -= valor;
        } else {
            System.out.println("Saldo de ação insuficiente.");
        }
    }

    public void creditarSaldoTituloDivida(double valor) {
        saldoTituloDivida += valor;
    }

    public void debitarSaldoTituloDivida(double valor) {
        if (saldoTituloDivida >= valor) {
            saldoTituloDivida -= valor;
        } else {
            System.out.println("Saldo de título de dívida insuficiente.");
        }
    }
    @Override
    public String toString() {
        return this.getNome();
    }
}
