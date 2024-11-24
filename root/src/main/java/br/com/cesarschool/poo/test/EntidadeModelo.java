package br.com.cesarschool.poo.test;

import br.com.cesarschool.poo.titulos.utils.Comparavel;
import br.com.cesarschool.poo.daogenerico.Entidade;

//Vamos usar EntidadeModelo para testar o DAO genérico
class EntidadeModelo extends Entidade implements Comparavel {
	private int id; 
	private String nome;
	EntidadeModelo(int id, String nome) {
		this.id = id;
		this.nome = nome; 
	}

	int getId() {
		return id;
	}
	String getNome() {
		return nome;
	}
	public int comparar(Comparavel c1) {
		EntidadeModelo em = (EntidadeModelo)c1;			
		return nome.compareTo(em.nome);
	}
    
    @Override
	public int getIdUnico() {
		return this.id;
	}

	public String toString() {
		return nome;
	}
}
