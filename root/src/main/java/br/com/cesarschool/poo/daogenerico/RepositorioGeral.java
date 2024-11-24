package br.com.cesarschool.poo.daogenerico;

import java.util.List;
/*
 * RepositorioGeral é a mãe dos repositórios específicos. É 
abstrata, tem um método abstrato Class<?> getClasseEntidade() 
e um atributo dao, do tipo DAOSerializadorObjetos. Por herança, 
os repositórios específicos podem usar este atributo dao para 
realizar as operações de inclusão, exclusão, alteração e busca. 
Os repositórios específicos devem implementar o método 
abstrato getClasseEntidade(), retornado o class da entidade 
(exemplo: Transacao.class). 
O construtor desta classe deve ser vazio, e inicializar o atributo 
dao  com uma instância de DAOSerializadorObjetos. Como o 
construtor de DAOSerializadorObjetos recebe um Class, a 
chamada dele deve passar o retorno do método 
getClasseEntidade().  
 * 
 */
// Classe abstrata base para repositórios específicos
public abstract class RepositorioGeral<T extends Entidade> {

    // Atributo dao do tipo DAOSerializadorObjetos
    protected DAOSerializadorObjetos<T> dao;

    // Construtor vazio
    public RepositorioGeral() {
        // Inicializa o dao passando a classe da entidade
        this.dao = new DAOSerializadorObjetos<T>(getClasseEntidade());
    }

    // Método abstrato que será implementado pelos repositórios específicos
    protected abstract Class<T> getClasseEntidade();

    // Método para buscar um objeto pelo ID único
    public T buscar(String idUnico) {
        return (T) dao.buscar(idUnico);
    }

    // Método para buscar todos os objetos
    public List<T> buscarTodos() {
        return dao.buscarTodos();
    }

    // Método para incluir uma entidade
    public boolean incluir(T entidade) {
        return dao.incluir(entidade);
    }

    // Método para alterar uma entidade
    public boolean alterar(T entidade) {
        return dao.alterar(entidade);
    }

    // Método para excluir uma entidade
    public boolean excluir(String idUnico) {
        return dao.excluir(idUnico);
    }
    public boolean excluir(T entidade) {
        return dao.excluir(entidade.getIdUnico());
    }

}