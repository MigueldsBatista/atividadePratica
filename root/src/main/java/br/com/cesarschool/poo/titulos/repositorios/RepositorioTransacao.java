package br.com.cesarschool.poo.titulos.repositorios;
/*
 * Deve gravar em e ler de um arquivo texto chamado Transacao.txt os dados dos objetos do tipo
 * Transacao. Seguem abaixo exemplos de linhas 
 * De entidadeCredito: identificador, nome, autorizadoAcao, saldoAcao, saldoTituloDivida.
 * De entidade Debito: identificador, nome, autorizadoAcao, saldoAcao, saldoTituloDivida.
 * De acao: identificador, nome, dataValidade, valorUnitario OU null
 * De transacao: identificador, nome, dataValidade, taxaJuros OU null. 
 * valorOperacao, dataHoraOperacao
 * 
 *   002192;BCB;true;0.00;1890220034.0;001112;BOFA;true;12900000210.00;3564234127.0;1;PETROBRAS;2024-12-12;30.33;null;100000.0;2024-01-01 12:22:21 
 *   002192;BCB;false;0.00;1890220034.0;001112;BOFA;true;12900000210.00;3564234127.0;null;3;FRANCA;2027-11-11;2.5;100000.0;2024-01-01 12:22:21
 *
 * A inclusão deve adicionar uma nova linha ao arquivo. 
 * 
 * A busca deve retornar um array de transações cuja entidadeCredito tenha identificador igual ao
 * recebido como parâmetro.  
 */

 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.time.LocalDateTime;
 import java.util.ArrayList;
 import java.util.List;
 import br.com.cesarschool.poo.titulos.entidades.Transacao;
 import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
 
 public class RepositorioTransacao {
 
	 private final String fileName = "Transacao.txt";
 
	 public void incluir(Transacao transacao) {
		 try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
			 String linha = transacao.getEntidadeCredito().getIdentificador() + ";" +
							transacao.getEntidadeDebito().getIdentificador() + ";" +
							transacao.getValorOperacao() + ";" +
							transacao.getDataHoraOperacao() + "\n";
			 writer.write(linha);
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
	 }
 
	 public Transacao[] buscarPorEntidadeCredora(int identificadorEntidadeCredito) {
		 List<Transacao> transacoes = new ArrayList<>();
		 try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			 String linha;
			 while ((linha = reader.readLine()) != null) {
				 String[] partes = linha.split(";");
				 int idCredito = Integer.parseInt(partes[0]);
 
				 if (idCredito == identificadorEntidadeCredito) {
					 EntidadeOperadora entidadeCredito = new EntidadeOperadora(idCredito, 0, 0, 0);
					 EntidadeOperadora entidadeDebito = new EntidadeOperadora(Integer.parseInt(partes[1]), 0, 0, 0);
					 double valorOperacao = Double.parseDouble(partes[2]);
					 LocalDateTime dataHoraOperacao = LocalDateTime.parse(partes[3]);
 
					 Transacao transacao = new Transacao(entidadeCredito, entidadeDebito, null, null, valorOperacao, dataHoraOperacao);
					 transacoes.add(transacao);
				 }
			 }
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
 
		 return transacoes.toArray(new Transacao[transacoes.size()]);
	 }
 }
 