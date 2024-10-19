package br.com.cesarschool.poo.titulos.repositorios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.Acao;

/*
 * Deve gravar em e ler de um arquivo texto chamado Transacao.txt os dados dos objetos do tipo
 * Transacao. Seguem abaixo exemplos de linhas 
 * De entidadeCredito: identificador, nome, autorizadoAcao, saldoAcao, saldoTituloDivida.
 * De entidade Debito: identificador, nome, autorizadoAcao, saldoAcao, saldoTituloDivida.
 * De acao: identificador, nome, dataValidade, valorUnitario OU null
 * De transacao: identificador, nome, dataValidade, taxaJuros OU null. 
 * valorOperacao, dataHoraOperacao
 */

public class RepositorioTransacao {
    private Path path;
    private final Path BASE_PATH = Paths.get("").toAbsolutePath(); // Caminho para a pasta pai

    public RepositorioTransacao() {
        // Inicializa o caminho do arquivo baseado no diretório de trabalho atual
        this.path = BASE_PATH.resolve("root").resolve("database").resolve("Transacao.txt");
        criarArquivoSeNaoExistir(); // Verifica e cria o arquivo se necessário
    }

    // Método para verificar e criar o arquivo, se não existir
    private boolean criarArquivoSeNaoExistir() {
        try {
            // Verifica se o arquivo existe; se não, cria um novo
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent()); // Cria os diretórios pai, se necessário
                Files.createFile(path); // Cria o arquivo
                System.out.println("Arquivo criado em: " + path.toAbsolutePath()); // Imprime o caminho absoluto
                return true; // Arquivo criado
            }
            System.out.println("Arquivo já existe em: " + path.toAbsolutePath()); // Imprime se o arquivo já existe
            return false; // Arquivo já existe
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Retorna false em caso de erro
        }
    }	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public void incluir(Transacao transacao) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile(), true))) {
			String linha = transacao.getEntidadeCredito().getIdentificador() + ";" +
					transacao.getEntidadeCredito().getNome() + ";" +
					transacao.getEntidadeCredito().getAutorizacao() + ";" +
					transacao.getEntidadeCredito().getSaldoAcao() + ";" +
					transacao.getEntidadeCredito().getSaldoTituloDivida() + ";" +
					transacao.getEntidadeDebito().getIdentificador() + ";" +
					transacao.getEntidadeDebito().getNome() + ";" +
					transacao.getEntidadeDebito().getAutorizacao() + ";" +
					transacao.getEntidadeDebito().getSaldoAcao() + ";" +
					transacao.getEntidadeDebito().getSaldoTituloDivida() + ";";

			if (transacao.getAcao() != null) {
				linha += transacao.getAcao().getIdentificador() + ";" +
						transacao.getAcao().getNome() + ";" +
						transacao.getAcao().getDataValidade() + ";" +
						transacao.getAcao().getValorUnitario() + ";";
			} else {
				linha += "null;null;null;null;";
			}

			linha += transacao.getValorOperacao() + ";" +
					transacao.getDataHoraOperacao().format(formatter) + "\n";

			writer.write(linha);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Transacao[] buscarPorEntidadeCredora(long identificadorEntidadeCredito) {
		List<Transacao> transacoes = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
			String linha;
			while ((linha = reader.readLine()) != null) {
				String[] partes = linha.split(";");
				if (partes.length < 13) {
					continue; // Ignora linhas inválidas
				}

				long idCredito = Long.parseLong(partes[0]);
				if (idCredito == identificadorEntidadeCredito) {
					EntidadeOperadora entidadeCredito = new EntidadeOperadora(idCredito, partes[1], Double.parseDouble(partes[2]));
					EntidadeOperadora entidadeDebito = new EntidadeOperadora(Long.parseLong(partes[5]), partes[6], Double.parseDouble(partes[7]));

					Acao acao = null;
					if (!partes[10].equals("null")) {
						acao = new Acao(Long.parseLong(partes[10]), partes[11], LocalDateTime.parse(partes[12], formatter).toLocalDate(), Double.parseDouble(partes[13]));
					}

					double valorOperacao = Double.parseDouble(partes[14]);
					LocalDateTime dataHoraOperacao = LocalDateTime.parse(partes[15], formatter);

					Transacao transacao = new Transacao(entidadeCredito, entidadeDebito, acao, null, valorOperacao, dataHoraOperacao);
					transacoes.add(transacao);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return transacoes.toArray(new Transacao[transacoes.size()]);
	}

	public Transacao[] buscarPorEntidadeDebito(long identificadorEntidadeDebito) {
		List<Transacao> transacoes = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
			String linha;
			while ((linha = reader.readLine()) != null) {
				String[] partes = linha.split(";");
				if (partes.length < 13) {
					continue; // Ignora linhas inválidas
				}
	
				long idDebito = Long.parseLong(partes[5]);
				if (idDebito == identificadorEntidadeDebito) {
					EntidadeOperadora entidadeCredito = new EntidadeOperadora(Long.parseLong(partes[0]), partes[1], Double.parseDouble(partes[2]));
					EntidadeOperadora entidadeDebito = new EntidadeOperadora(idDebito, partes[6], Double.parseDouble(partes[7]));
	
					Acao acao = null;
					if (!partes[10].equals("null")) {
						acao = new Acao(Long.parseLong(partes[10]), partes[11], LocalDateTime.parse(partes[12], formatter).toLocalDate(), Double.parseDouble(partes[13]));
					}
	
					double valorOperacao = Double.parseDouble(partes[14]);
					LocalDateTime dataHoraOperacao = LocalDateTime.parse(partes[15], formatter);
	
					Transacao transacao = new Transacao(entidadeCredito, entidadeDebito, acao, null, valorOperacao, dataHoraOperacao);
					transacoes.add(transacao);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return transacoes.toArray(new Transacao[transacoes.size()]);
	}
	
}
