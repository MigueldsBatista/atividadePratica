package br.com.cesarschool.poo.titulos.repositorios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.daogenerico.RepositorioGeral;
import br.com.cesarschool.poo.titulos.entidades.Acao;

public class RepositorioTransacao extends RepositorioGeral<Transacao> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public Class<Transacao> getClasseEntidade() {
        return Transacao.class;
    }
    
    //FIXME: Corrigir método incluir
    public Transacao[] buscarPorEntidadeCredora(int identificadorEntidadeCredito) {
        List<Transacao> transacoes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.dao.getNomeDir()))) {
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