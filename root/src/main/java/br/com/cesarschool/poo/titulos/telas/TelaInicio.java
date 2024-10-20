package br.com.cesarschool.poo.titulos.telas;

import javax.swing.*;

import br.com.cesarschool.poo.titulos.telas.acao.NavegacaoAcao;
import br.com.cesarschool.poo.titulos.telas.entidadeOperadora.NavegacaoEntidadeOperadora;
import br.com.cesarschool.poo.titulos.telas.tituloDivida.NavegacaoTituloDivida;
import br.com.cesarschool.poo.titulos.telas.transacao.TelaOperacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInicio {
    private JFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TelaInicio window = new TelaInicio();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaInicio() {
        initialize();
    }
    public void setVisible(boolean visibilidade){
        frame.setVisible(visibilidade);
    }
    private void initialize() {
        frame = new JFrame("Tela Inicial");
        frame.setSize(450, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Botão para Tela de Adição
        JButton btnAdicao = new JButton("Navegação Ação");
        btnAdicao.setBounds(100, 30, 200, 30);
        btnAdicao.addActionListener(e -> abrirNavegacaoAcao());
        frame.getContentPane().add(btnAdicao);

        // Botão para Tela de Cadastro
        JButton btnCadastro = new JButton("Nagevação Titulo Divida");
        btnCadastro.setBounds(100, 80, 200, 30);
        btnCadastro.addActionListener(e -> abrirNavegacaoTituloDivida());
        frame.getContentPane().add(btnCadastro);

        // Botão para Tela de Entidade
        JButton btnEntidade = new JButton("Navegação Entidade Operadora");
        btnEntidade.setBounds(100, 130, 200, 30);
        btnEntidade.addActionListener(e -> abrirNavegacaoEntidadeOperadora());
        frame.getContentPane().add(btnEntidade);

        // Botão para Tela de Operação
        JButton btnOperacao = new JButton("Navegação Operação");
        btnOperacao.setBounds(100, 180, 200, 30);
        btnOperacao.addActionListener(e -> abrirOperacao());
        frame.getContentPane().add(btnOperacao);

    }

    private void abrirNavegacaoAcao() {
        // Implementação da lógica para abrir NavegacaoAcao
        NavegacaoAcao navegacaoAcao = new NavegacaoAcao();
        navegacaoAcao.setVisible(true);
        frame.setVisible(false); // Esconde TelaInicio
    }

    private void abrirNavegacaoTituloDivida() {
        // Implementação da lógica para abrir NavegacaoTituloDivida
        NavegacaoTituloDivida navegacaoTituloDivida = new NavegacaoTituloDivida();
        navegacaoTituloDivida.setVisible(true);
        frame.setVisible(false); // Esconde TelaInicio
    }

    private void abrirNavegacaoEntidadeOperadora() {
        // Implementação da lógica para abrir NavegacaoEntidadeOperadora
        NavegacaoEntidadeOperadora navegacaoEntidadeOperadora = new NavegacaoEntidadeOperadora();
        navegacaoEntidadeOperadora.setVisible(true);
        frame.setVisible(false); // Esconde TelaInicio
    }
    private void abrirOperacao(){
        // Implementação da lógica para abrir Operacao
        TelaOperacao operacao = new TelaOperacao();
        operacao.setVisible(true);
        frame.setVisible(false); // Esconde TelaInicio
    }
}
