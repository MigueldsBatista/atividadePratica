package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/*
 * Deve gravar em e ler de um arquivo texto chamado Transacao.txt os dados dos objetos do tipo
 * Transacao. Seguem abaixo exemplos de linhas 
 * De entidadeCredito: identificador, nome, autorizadoAcao, saldoAcao, saldoTituloDivida.
 * De entidadeDebito: identificador, nome, autorizadoAcao, saldoAcao, saldoTituloDivida.
 * De acao: identificador, nome, dataValidade, valorUnitario OU null
 * De tituloDivida: identificador, nome, dataValidade, taxaJuros OU null. 
 * valorOperacao, dataHoraOperacao
 * 
 *   002192;BCB;true;0.00;1890220034.0;001112;BOFA;true;12900000210.00;3564234127.0;1;PETROBRAS;2024-12-12;30.33;null;100000.0;2024-01-01 12:22:21 
 *   002192;BCB;false;0.00;1890220034.0;001112;BOFA;true;12900000210.00;3564234127.0;null;3;FRANCA;2027-11-11;2.5;100000.0;2024-01-01 12:22:21
 *
 * A inclus�o deve adicionar uma nova linha ao arquivo. 
 * 
 * A busca deve retornar um array de transa��es cuja entidadeCredito tenha identificador igual ao
 * recebido como par�metro.  
 */

 

public class RepositorioTransacao {
    private static final String FILE_NAME = "Transacao.txt";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public boolean incluir(Transacao transacao) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(formatTransacao(transacao));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Transacao> buscar(int identificador) {
        List<Transacao> transacoes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Transacao transacao = parseTransacao(line);
                if (transacao.getEntidadeCredito().getIdentificador() == identificador) {
                    transacoes.add(transacao);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transacoes;
    }

    private String formatTransacao(Transacao transacao) {
        StringBuilder sb = new StringBuilder();
        sb.append(transacao.getEntidadeCredito().getIdentificador()).append(";")
          .append(transacao.getEntidadeCredito().getNome()).append(";")
          .append(transacao.getEntidadeCredito().isAutorizadoAcao()).append(";")
          .append(transacao.getEntidadeCredito().getSaldoAcao()).append(";")
          .append(transacao.getEntidadeCredito().getSaldoTituloDivida()).append(";")
          .append(transacao.getEntidadeDebito().getIdentificador()).append(";")
          .append(transacao.getEntidadeDebito().getNome()).append(";")
          .append(transacao.getEntidadeDebito().isAutorizadoAcao()).append(";")
          .append(transacao.getEntidadeDebito().getSaldoAcao()).append(";")
          .append(transacao.getEntidadeDebito().getSaldoTituloDivida()).append(";");

        if (transacao.getAcao() != null) {
            sb.append(transacao.getAcao().getIdentificador()).append(";")
              .append(transacao.getAcao().getNome()).append(";")
              .append(transacao.getAcao().getDataValidade().format(DATE_FORMATTER)).append(";")
              .append(transacao.getAcao().getValorUnitario()).append(";");
        } else {
            sb.append("null;null;null;null;");
        }

        if (transacao.getTituloDivida() != null) {
            sb.append(transacao.getTituloDivida().getIdentificador()).append(";")
              .append(transacao.getTituloDivida().getNome()).append(";")
              .append(transacao.getTituloDivida().getDataValidade().format(DATE_FORMATTER)).append(";")
              .append(transacao.getTituloDivida().getTaxaJuros()).append(";");
        } else {
            sb.append("null;null;null;null;");
        }

        sb.append(transacao.getValorOperacao()).append(";")
          .append(transacao.getDataHoraOperacao().format(DATE_TIME_FORMATTER));

        return sb.toString();
    }

    private Transacao parseTransacao(String line) {
        String[] parts = line.split(";");
        EntidadeCredito entidadeCredito = new EntidadeCredito(
            Integer.parseInt(parts[0]), parts[1], Boolean.parseBoolean(parts[2]),
            Double.parseDouble(parts[3]), Double.parseDouble(parts[4])
        );
        EntidadeDebito entidadeDebito = new EntidadeDebito(
            Integer.parseInt(parts[5]), parts[6], Boolean.parseBoolean(parts[7]),
            Double.parseDouble(parts[8]), Double.parseDouble(parts[9])
        );

        Acao acao = null;
        if (!"null".equals(parts[10])) {
            acao = new Acao(
                Integer.parseInt(parts[10]), parts[11], LocalDate.parse(parts[12], DATE_FORMATTER),
                Double.parseDouble(parts[13])
            );
        }

        TituloDivida tituloDivida = null;
        if (!"null".equals(parts[14])) {
            tituloDivida = new TituloDivida(
                Integer.parseInt(parts[14]), parts[15], LocalDate.parse(parts[16], DATE_FORMATTER),
                Double.parseDouble(parts[17])
            );
        }

        double valorOperacao = Double.parseDouble(parts[18]);
        LocalDateTime dataHoraOperacao = LocalDateTime.parse(parts[19], DATE_TIME_FORMATTER);

        return new Transacao(entidadeCredito, entidadeDebito, acao, tituloDivida, valorOperacao, dataHoraOperacao);
    }
}
