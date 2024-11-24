//wtf?!

import java.io.File;

import br.com.cesarschool.poo.daogenerico.Entidade;


public class TesteGeral {//file.separator é uma constante que retorna o separador de arquivos do sistema operacional
	protected static final String SEP_ARQUIVO = System.getProperty("file.separator");
	protected static final String PONTO = ".";					   //tinha aq entidadeTeste mas mudei 
																   //pra EntidaeModelo	
	protected static final String NOME_DIR = PONTO + SEP_ARQUIVO + EntidadeModelo.class.getSimpleName();  	
	protected void excluirArquivosDiretorio() {
		excluirArquivosDiretorio(NOME_DIR); 	
	}

	protected boolean excluirArquivosDiretorio(String diretorio) {
		File dir = new File(diretorio);
		File[] arqs = dir.listFiles();
		boolean sucesso = true;
		if (arqs != null && arqs.length > 0) {
			for (File file : arqs) {
				if (!file.delete()) {
					sucesso = false;
				}
			}
		}
		return sucesso;
	}
	protected int obterQtdArquivosDir(String caminhoDir) {
		File[] files = (new File(caminhoDir)).listFiles();
		if (files == null) {
			return 0;
		} else {
			return files.length;
		}
	}
	protected String obterNomeArquivo(Entidade ent) {
		return obterNomeArquivo(NOME_DIR, ent);
	}
	protected String obterNomeArquivo(String dir, Entidade ent) {
		return dir + SEP_ARQUIVO + ent.getIdUnico();
	}

}