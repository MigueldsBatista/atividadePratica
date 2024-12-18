import org.junit.jupiter.api.*;

import br.com.cesarschool.poo.titulos.repositorios.*;

public class TesteRepositorioLinhaObjetoTest extends TesteGeral{

    private static final String NOME_DIR = PONTO + SEP_ARQUIVO + EntidadeModelo.class.getSimpleName();
    private static final RepositorioLinhaObjeto<EntidadeModelo> repositorio = new RepositorioLinhaObjeto<>(EntidadeModelo.class);

/*
 * Testes de unidade extras pra testar as funcionaldiades da classe RepositorioLinhaObjeto
 * 
 */

    @Override
    protected void excluirArquivosDiretorio(String diretorio) {
        repositorio.limparCache();
        super.excluirArquivosDiretorio(diretorio);
    }

    @Test
    public void DAO00() {
        this.excluirArquivosDiretorio(NOME_DIR);
        EntidadeModelo entidade = new EntidadeModelo(1, "Teste");
        boolean res = repositorio.incluir(entidade);
        Assertions.assertTrue(res);
        Assertions.assertEquals(1, obterQtdArquivosDir(NOME_DIR));
    }

    @Test
    public void DAO01() {
        this.excluirArquivosDiretorio(NOME_DIR);
        EntidadeModelo entidade = new EntidadeModelo(1, "Teste");
        repositorio.incluir(entidade);
        EntidadeModelo entidadeBuscada = repositorio.buscar("1");
        Assertions.assertNotNull(entidadeBuscada);
        Assertions.assertEquals(entidade.getIdUnico(), entidadeBuscada.getIdUnico());
    }

    @Test
    public void DAO02() {
        this.excluirArquivosDiretorio(NOME_DIR);

        EntidadeModelo entidade = new EntidadeModelo(1, "Teste");
        repositorio.incluir(entidade);
        entidade.setNome("Teste Alterado");
        Assertions.assertTrue(repositorio.alterar(entidade));
        EntidadeModelo entidadeAlterada = repositorio.buscar("1");
        Assertions.assertEquals("Teste Alterado", entidadeAlterada.getNome());
    }

    @Test
    public void DAO03() {
        this.excluirArquivosDiretorio(NOME_DIR);

        EntidadeModelo entidade = new EntidadeModelo(1, "Teste");
        repositorio.incluir(entidade);
        Assertions.assertTrue(repositorio.excluir("1"));
        Assertions.assertNull(repositorio.buscar("1"));
    }

    @Test
    public void DAO04() {
        this.excluirArquivosDiretorio(NOME_DIR);

        EntidadeModelo entidade1 = new EntidadeModelo(1, "Teste1");
        EntidadeModelo entidade2 = new EntidadeModelo(2, "Teste2");
        repositorio.incluir(entidade1);
        repositorio.incluir(entidade2);
        EntidadeModelo[] todasEntidades = repositorio.buscarTodos();
        Assertions.assertEquals(2, todasEntidades.length);
    }

}