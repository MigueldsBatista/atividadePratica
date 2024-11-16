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

public class RepositorioTransacao {
    private Path path;
    private final Path BASE_PATH = Paths.get("").toAbsolutePath();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public RepositorioTransacao() {
        this.path = BASE_PATH.resolve("root").resolve("database").resolve("Transacao.txt");
        criarArquivoSeNaoExistir();
    }

    private boolean criarArquivoSeNaoExistir() {
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
                System.out.println("Arquivo criado em: " + path.toAbsolutePath());
                return true;
            }
            System.out.println("Arquivo já existe em: " + path.toAbsolutePath());
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*public boolean incluir(Transacao transacao) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile(), true))) {
            String linha = transacao.getEntidadeCredito().getIdentificador() + ";" +
                           transacao.getEntidadeCredito().getNome() + ";" +
                           transacao.getEntidadeCredito().getAutorizacao() + ";" +
                           transacao.getEntidadeCredito().getSaldoAcao() + ";" +
                           transacao.getEntidadeCredito().getSaldoTituloDivida() + ";" +

                           transacao.getEntidadeDebito().getIdentificador() + ";" +
                           transacao.getEntidadeDebito().getNome() + ";" +
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

            if (transacao.getTituloDivida() != null) {
                linha += transacao.getTituloDivida().getIdentificador() + ";" +
                         transacao.getTituloDivida().getNome() + ";" +
                         transacao.getTituloDivida().getDataValidade() + ";" +
                         transacao.getTituloDivida().getTaxaJuros() + ";";
            } else {
                linha += "null;null;null;null;";
            }

            linha += transacao.getValorOperacao() + ";" +
                     transacao.getDataHoraOperacao().format(formatter) + "\n";

            writer.write(linha);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    */
    public Transacao[] buscarPorEntidadeCredora(int identificadorEntidadeCredito) {
        List<Transacao> transacoes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length < 17) { // Ajustado para considerar todos os campos
                    continue; // Ignora linhas inválidas
                }

                int idCredito = Integer.parseInt(partes[0]);
                if (idCredito == identificadorEntidadeCredito) {
                    EntidadeOperadora entidadeCredito = new EntidadeOperadora(
                        idCredito, //id
                        partes[1], //nome
                        Boolean.parseBoolean(partes[2]), // Autorizacao
                        Double.parseDouble(partes[4]), // Saldo em titulos
                        Double.parseDouble(partes[3])  // Saldo em acoes

                    );

                    EntidadeOperadora entidadeDebito = new EntidadeOperadora(
                        Integer.parseInt(partes[5]), //id
                        partes[6],//nome
						Boolean.parseBoolean(partes[7]),
                        Double.parseDouble(partes[9]), // Saldo em titulos
                        Double.parseDouble(partes[8])  // Saldo em acoes
                    );

                    Acao acao = null;
                    if (!partes[10].equals("null")) {
                        acao = new Acao(
                            Integer.parseInt(partes[10]), 
                            partes[11], 
                            LocalDateTime.parse(partes[12], formatter).toLocalDate(), 
                            Double.parseDouble(partes[13])
                        );
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

        return transacoes.toArray(new Transacao[0]);
    }
}