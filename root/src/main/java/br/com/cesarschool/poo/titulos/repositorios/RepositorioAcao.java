package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.daogenerico.RepositorioGeral;
import br.com.cesarschool.poo.titulos.entidades.Acao;

public class RepositorioAcao extends RepositorioGeral{
    
    @Override
    public Class<?> getClasseEntidade() {
        return Acao.class;
    }
}
