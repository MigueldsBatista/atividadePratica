
import java.io.File;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioAcao;
import br.com.cesarschool.poo.daogenerico.RepositorioGeral;
import br.com.cesarschool.poo.titulos.utils.ComparadoraObjetosSerial;
import br.com.cesarschool.poo.daogenerico.DAOSerializadorObjetos;


public class TesteRepositorioAcao extends TesteGeral {
	private static final RepositorioAcao DAO_ACAO = new RepositorioAcao();
	private static final String NOME_DIR_ACAO = PONTO + SEP_ARQUIVO + Acao.class.getSimpleName();
	

	@Test
	public void testDAO00() {
		Assertions.assertTrue(DAO_ACAO instanceof RepositorioGeral);
		DAOSerializadorObjetos dao = DAO_ACAO.getDao();
		Assertions.assertNotNull(dao);
	}
	@Test
	public void testDAO01() {
		excluirArquivosDiretorio(NOME_DIR_ACAO);
		Acao acao = new Acao(1, "A1", LocalDate.now(), 100.0);
		Assertions.assertTrue(DAO_ACAO.incluir(acao));		
		Assertions.assertEquals(obterQtdArquivosDir(NOME_DIR_ACAO), 1); 
		Assertions.assertTrue(new File(obterNomeArquivo(NOME_DIR_ACAO, acao)).exists());
		Acao acao1 = DAO_ACAO.buscar(""+acao.getIdentificador());
		Assertions.assertNotNull(acao1);
		Assertions.assertNotNull(acao1.getDataHoraInclusao()); 
		Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(acao, acao1));								
	}
	@Test
	public void testDAO02() {
		excluirArquivosDiretorio(NOME_DIR_ACAO);
		Acao acao = new Acao(2, "A2", LocalDate.now(), 101.0);
		Assertions.assertTrue(DAO_ACAO.incluir(acao));
		Assertions.assertFalse(DAO_ACAO.incluir(acao));
		Assertions.assertEquals(obterQtdArquivosDir(NOME_DIR_ACAO), 1); 
	}
	@Test
	public void testDAO03() {
		excluirArquivosDiretorio(NOME_DIR_ACAO);
		int id = 3;
		Acao acao = new Acao(id, "A3", LocalDate.now(), 102.0);
		Acao acaoAlt = new Acao(id, "A3Alt", LocalDate.now().minusDays(10), 103.0);
		Assertions.assertTrue(DAO_ACAO.incluir(acao));		
		Assertions.assertTrue(DAO_ACAO.alterar(acaoAlt));
		Acao acao1 = DAO_ACAO.buscar(""+acao.getIdentificador());
		Assertions.assertNotNull(acao1);
		Assertions.assertNotNull(acao1.getDataHoraUltimaAlteracao()); 
		Assertions.assertTrue(ComparadoraObjetosSerial.compareObjectsSerial(acaoAlt, acao1));								
	}
	@Test
	public void testDAO04() {
		excluirArquivosDiretorio(NOME_DIR_ACAO);
		Acao acao = new Acao(4, "A4", LocalDate.now(), 104.0);
		Acao acaoAlt = new Acao(5, "A5", LocalDate.now().minusDays(11), 105.0);
		Assertions.assertTrue(DAO_ACAO.incluir(acao));
		Assertions.assertFalse(DAO_ACAO.alterar(acaoAlt));
		Assertions.assertEquals(obterQtdArquivosDir(NOME_DIR_ACAO), 1); 
	}
	@Test
	public void testDAO05() {
		excluirArquivosDiretorio(NOME_DIR_ACAO);
		int id = 6;
		Acao acao = new Acao(id, "A6", LocalDate.now(), 106.0);		
		Assertions.assertTrue(DAO_ACAO.incluir(acao));
		Assertions.assertTrue(DAO_ACAO.excluir(""+id));
		Assertions.assertEquals(obterQtdArquivosDir(NOME_DIR_ACAO), 0);
		Acao acaoBusc = DAO_ACAO.buscar(""+id);
		Assertions.assertNull(acaoBusc);
	}
	@Test
	public void testDAO06() {
		excluirArquivosDiretorio(NOME_DIR_ACAO);		
		Acao acao = new Acao(7, "A7", LocalDate.now(), 107.0);		
		Assertions.assertTrue(DAO_ACAO.incluir(acao));
		Assertions.assertFalse(DAO_ACAO.excluir(""+8));
		Assertions.assertEquals(obterQtdArquivosDir(NOME_DIR_ACAO), 1);
		
	}


}