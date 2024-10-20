package br.com.cesarschool.poo.titulos.telas.tituloDivida;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import br.com.cesarschool.poo.titulos.telas.TelaInicio;

public class NavegacaoTituloDivida {
    private JFrame frame;

    // Constantes para largura e altura
    private static final int LARGURA_JANELA = 450;
    private static final int ALTURA_JANELA = 450;
    private static final int LARGURA_BOTAO = 200;
    private static final int ALTURA_BOTAO = 30;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                NavegacaoTituloDivida window = new NavegacaoTituloDivida();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public NavegacaoTituloDivida() {
        initialize();
    }

    public void setVisible(boolean visibilidade) {
        frame.setVisible(visibilidade);
    }

    private void initialize() {
        frame = new JFrame("Navegação Título Dívida");
        frame.setSize(LARGURA_JANELA, ALTURA_JANELA);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Posição inicial para os botões
        int yPos = 30;
        int buttonSpacing = 20; // Espaçamento entre os botões

        // Botão para incluir
        JButton btnIncluir = new JButton("Incluir");
        btnIncluir.setBounds(100, yPos, LARGURA_BOTAO, ALTURA_BOTAO);
        btnIncluir.addActionListener(e -> abrirTelaIncluirTituloDivida());
        frame.getContentPane().add(btnIncluir);
        yPos += ALTURA_BOTAO + buttonSpacing;

        // Botão para alterar
        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(100, yPos, LARGURA_BOTAO, ALTURA_BOTAO);
        btnAlterar.addActionListener(e -> abrirTelaAlterarTituloDivida());
        frame.getContentPane().add(btnAlterar);
        yPos += ALTURA_BOTAO + buttonSpacing;

        // Botão para buscar
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(100, yPos, LARGURA_BOTAO, ALTURA_BOTAO);
        btnBuscar.addActionListener(e -> abrirTelaBuscarTituloDivida());
        frame.getContentPane().add(btnBuscar);
        yPos += ALTURA_BOTAO + buttonSpacing;

        // Botão para excluir
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(100, yPos, LARGURA_BOTAO, ALTURA_BOTAO);
        btnExcluir.addActionListener(e -> abrirTelaExcluirTituloDivida());
        frame.getContentPane().add(btnExcluir);
        yPos += ALTURA_BOTAO + buttonSpacing;

        // Botão para voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(150, yPos, 100, ALTURA_BOTAO);
        btnVoltar.addActionListener(e -> {
            TelaInicio telaInicio = new TelaInicio();
            telaInicio.setVisible(true);
            frame.dispose(); // Fecha a tela atual
        });
        frame.getContentPane().add(btnVoltar);
    }

    private void abrirTelaAlterarTituloDivida() {
        TelaAlterarTituloDivida telaAlterarTituloDivida = new TelaAlterarTituloDivida();
        telaAlterarTituloDivida.setVisible(true);
        frame.setVisible(false); // Esconde NavegacaoTituloDivida
    }

    private void abrirTelaIncluirTituloDivida() {
        TelaIncluirTituloDivida telaIncluirTituloDivida = new TelaIncluirTituloDivida();
        telaIncluirTituloDivida.setVisible(true);
        frame.setVisible(false); // Esconde NavegacaoTituloDivida
    }

    private void abrirTelaBuscarTituloDivida() {
        TelaBuscarTituloDivida telaBuscarTituloDivida = new TelaBuscarTituloDivida();
        telaBuscarTituloDivida.setVisible(true);
        frame.setVisible(false); // Esconde NavegacaoTituloDivida
    }

    private void abrirTelaExcluirTituloDivida() {
        TelaExcluirTituloDivida telaExcluirTituloDivida = new TelaExcluirTituloDivida();
        telaExcluirTituloDivida.setVisible(true);
        frame.setVisible(false); // Esconde NavegacaoTituloDivida
    }
}
