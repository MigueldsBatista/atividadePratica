package br.com.cesarschool.poo.titulos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.*;

import br.com.cesarschool.poo.titulos.entidades.*;
import br.com.cesarschool.poo.titulos.relatorios.*;
import br.com.cesarschool.poo.titulos.repositorios.*;
import br.com.cesarschool.poo.titulos.telas.*;




public class MainApplicationScreen {
    /*
    private JFrame frame;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                MainApplicationScreen window = new MainApplicationScreen();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
        public MainApplicationScreen() {
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
        frame.setVisible(false); // Esconde MainApplicationScreen
    }

    private void abrirNavegacaoTituloDivida() {
        // Implementação da lógica para abrir NavegacaoTituloDivida
        NavegacaoTituloDivida navegacaoTituloDivida = new NavegacaoTituloDivida();
        navegacaoTituloDivida.setVisible(true);
        frame.setVisible(false); // Esconde MainApplicationScreen
    }

    private void abrirNavegacaoEntidadeOperadora() {
        // Implementação da lógica para abrir NavegacaoEntidadeOperadora
        NavegacaoEntidadeOperadora navegacaoEntidadeOperadora = new NavegacaoEntidadeOperadora();
        navegacaoEntidadeOperadora.setVisible(true);
        frame.setVisible(false); // Esconde MainApplicationScreen
    }
    private void abrirOperacao(){
        // Implementação da lógica para abrir Operacao
        TelaOperacao operacao = new TelaOperacao();
        operacao.setVisible(true);
        frame.setVisible(false); // Esconde MainApplicationScreen
    }
}
 */

 public static void main(String[] args) {

    RepositorioTransacao rep = new RepositorioTransacao();		
    RelatorioTransacaoBroker broker = new RelatorioTransacaoBroker(rep);
    Acao a = new Acao(1, "PB01", LocalDate.now(), 100);
    EntidadeOperadora e1 = new EntidadeOperadora(1, "XXX", 100);
    EntidadeOperadora e2 = new EntidadeOperadora(2, "ZZZ", 100);
    EntidadeOperadora e3 = new EntidadeOperadora(3, "AAA", 100);
    Transacao t1 = new Transacao(e1, e2, a, null, 0, LocalDateTime.now());
    Transacao t2 = new Transacao(e2, e1, a, null, 0, LocalDateTime.now().plusDays(2));
    Transacao t3 = new Transacao(e3, e1, a, null, 0, LocalDateTime.now().plusDays(1));
    rep.incluir(t1);
    rep.incluir(t2);
    rep.incluir(t3);	
    Transacao[] trans = broker.relatorioTransacaoPorNomeEntidadeCredora();
    for (Transacao transacao : trans) {
        System.out.println(transacao);
    }


 }
    
}
