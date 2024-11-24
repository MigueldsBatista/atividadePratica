package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.daogenerico.RepositorioGeral;
import br.com.cesarschool.poo.titulos.entidades.Acao;

public class RepositorioAcao extends RepositorioGeral<Acao>{
    
    @Override
    public Class<Acao> getClasseEntidade() {
        return Acao.class;
    }
}
