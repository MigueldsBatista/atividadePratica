
import java.io.File;
import java.io.Serializable;

import org.junit.jupiter.api.Test;

import br.com.cesarschool.poo.titulos.utils.ComparadoraObjetosSerial;
import br.com.cesarschool.poo.daogenerico.DAOSerializadorObjetos;
import br.com.cesarschool.poo.daogenerico.Entidade;

import org.junit.jupiter.api.Assertions;


public class TestesDAOSerializador {
	private static final String NOME_2 = "ENT NEW";
	private static final String ID_2 = "2";
	private static final String NOME_1 = "ENT 1";
	private static final String ID_1 = "1";
	
	public static class EntidadeTeste extends Entidade {
		private static final long serialVersionUID = 1L;
		private String id;
		private String nome;
		EntidadeTeste(String id, String nome) {
			this.id = id;
			this.nome = nome;
		}
		public String getIdUnico() {			
			return id;
		}		
		String getId() {
			return id;
		}
		String getNome() {
			return nome; 
		}
	}
	private static final String SEP_ARQUIVO = System.getProperty("file.separator");
	private static final String PONTO = ".";
	private static final String NOME_DIR = PONTO + SEP_ARQUIVO + EntidadeTeste.class.getSimpleName();  

	private String obterNomeArquivo(EntidadeTeste ent) {
		return NOME_DIR + SEP_ARQUIVO + ent.getIdUnico();
	}

	private static final DAOSerializadorObjetos<EntidadeTeste> DAO = new DAOSerializadorObjetos<EntidadeTeste>(EntidadeTeste.class);
	private void excluirArquivosDiretorio() {
		//Exclui todos os arquivos do diretório
		File dir = new File(NOME_DIR);
		File[] arqs = dir.listFiles();
		if (arqs != null && arqs.length > 0) {
			for (File file : arqs) {
				file.delete();
			}
			
		}

		//Adicionamos essa linha de código pra limpar o cache e garantir que os testes não falhem
		DAO.limparCache();
	}
	private int obterQtdArquivosDir(String caminhoDir) {
		File[] files = (new File(caminhoDir)).listFiles();
		if (files == null) {
			return 0;
		} else {
			return files.length;
		}
	}

	@Test
	public void testEntidade() {
		Class<Entidade> classe = Entidade.class;
		Class<?>[] interfaces = classe.getInterfaces();
		for (Class<?> class1 : interfaces) {
			if (class1.equals(Serializable.class)) {
				return;
			}
		}
		Assertions.fail();
	}
	@Test
	public void testDAO01() {
		excluirArquivosDiretorio();
		String id = ID_1;
		String nome = NOME_1;
		EntidadeTeste e1 = new EntidadeTeste(id, nome);
		Assertions.assertTrue(DAO.incluir(e1));//Incluido com sucesso
		Assertions.assertEquals(obterQtdArquivosDir(NOME_DIR), 1); //verificou certo
		Assertions.assertTrue(new File(obterNomeArquivo(e1)).exists());
		EntidadeTeste eb = (EntidadeTeste)DAO.buscar(id);
		Assertions.assertNotNull(eb);
		Assertions.assertNotNull(eb.getDataHoraInclusao()); 
		Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(e1, eb));								
	}

	@Test
	public void testDAO02() {
		excluirArquivosDiretorio();
		String id = ID_1;
		String nome = NOME_1;
		EntidadeTeste e1 = new EntidadeTeste(id, nome);
		boolean res = DAO.incluir(e1);
		Assertions.assertTrue(res);
		Assertions.assertFalse(DAO.incluir(e1));
		Assertions.assertEquals(obterQtdArquivosDir(NOME_DIR), 1); 
	}

	@Test
	public void testDAO03() {
		excluirArquivosDiretorio();
		String id = ID_1;
		String nome = NOME_1;
		String nomeNew = NOME_2;
		EntidadeTeste e1 = new EntidadeTeste(id, nome);
		Assertions.assertTrue(DAO.incluir(e1));
		EntidadeTeste ealt = new EntidadeTeste(id, nomeNew);
		Assertions.assertTrue(DAO.alterar(ealt));
		Assertions.assertEquals(obterQtdArquivosDir(NOME_DIR), 1); 
		Assertions.assertTrue(new File(obterNomeArquivo(e1)).exists());
		EntidadeTeste eb = (EntidadeTeste)DAO.buscar(id);
		Assertions.assertNotNull(eb);
		Assertions.assertNotNull(eb.getDataHoraUltimaAlteracao());
		Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(ealt, eb));		
	}		
	@Test
	public void testDAO04() {
		excluirArquivosDiretorio();
		String id = ID_1;
		String idNew = ID_2;
		String nome = NOME_1;
		String nomeNew = NOME_2;
		EntidadeTeste e1 = new EntidadeTeste(id, nome);
		Assertions.assertTrue(DAO.incluir(e1));
		EntidadeTeste ealt = new EntidadeTeste(idNew, nomeNew);
		Assertions.assertFalse(DAO.alterar(ealt));
		Assertions.assertEquals(obterQtdArquivosDir(NOME_DIR), 1); 
		Assertions.assertTrue(new File(obterNomeArquivo(e1)).exists());
		EntidadeTeste eb = (EntidadeTeste)DAO.buscar(id);
		Assertions.assertNotNull(eb);
		Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(e1, eb));		
	}		
	@Test
	public void testDAO05() {
		excluirArquivosDiretorio();
		String id = ID_1;
		String nome = NOME_1;
		EntidadeTeste e1 = new EntidadeTeste(id, nome);
		Assertions.assertTrue(DAO.incluir(e1));
		EntidadeTeste eb = (EntidadeTeste)DAO.buscar(id);
		Assertions.assertNotNull(eb);
		Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(e1, eb));		
	}
	@Test
	public void testDAO06() {
		excluirArquivosDiretorio();
		String id = ID_1;
		String nome = NOME_1;
		String idNew = ID_2;
		EntidadeTeste e1 = new EntidadeTeste(id, nome);
		Assertions.assertTrue(DAO.incluir(e1));
		EntidadeTeste eb = (EntidadeTeste)DAO.buscar(idNew);
		Assertions.assertNull(eb);				
	}
	@Test
	public void testDAO07() {
		excluirArquivosDiretorio();
		String id = ID_1;
		String nome = NOME_1;
		EntidadeTeste e1 = new EntidadeTeste(id, nome);
		Assertions.assertTrue(DAO.incluir(e1));
		Assertions.assertEquals(obterQtdArquivosDir(NOME_DIR), 1);
		boolean res = DAO.excluir(id);
		Assertions.assertTrue(res);
		Assertions.assertEquals(obterQtdArquivosDir(NOME_DIR), 0);
		EntidadeTeste eb = (EntidadeTeste)DAO.buscar(id);
		Assertions.assertNull(eb);						
	}
	@Test
	public void testDAO08() {
		excluirArquivosDiretorio();
		String id = ID_1;
		String nome = NOME_1;
		String idNew = ID_2;
		EntidadeTeste e1 = new EntidadeTeste(id, nome);
		Assertions.assertTrue(DAO.incluir(e1));
		Assertions.assertEquals(obterQtdArquivosDir(NOME_DIR), 1);
		Assertions.assertFalse(DAO.excluir(idNew));
		Assertions.assertEquals(obterQtdArquivosDir(NOME_DIR), 1);
		EntidadeTeste eb = (EntidadeTeste)DAO.buscar(id);
		Assertions.assertNotNull(eb);
		Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(e1, eb));		
	}
	@Test
	public void testDAO09() {
		excluirArquivosDiretorio();
		String id = ID_1;
		String nome = NOME_1;
		String idNew = ID_2;
		String nomeNew = NOME_2;
		EntidadeTeste e1 = new EntidadeTeste(id, nome);
		EntidadeTeste e2 =new EntidadeTeste(idNew, nomeNew);
		Assertions.assertTrue(DAO.incluir(e1));
		Assertions.assertTrue(DAO.incluir(e2));
		Entidade[] res = DAO.buscarTodos();
		Assertions.assertNotNull(res);

		Assertions.assertEquals(res.length, 2);
		Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(e1, res[0]));
		
		Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(e2, res[1]));
		
	}

	@Test
	public void testDAO10() {
		excluirArquivosDiretorio();
		Entidade[] res = DAO.buscarTodos();
		Assertions.assertNotNull(res);
		Assertions.assertEquals(res.length, 0);
	}
}