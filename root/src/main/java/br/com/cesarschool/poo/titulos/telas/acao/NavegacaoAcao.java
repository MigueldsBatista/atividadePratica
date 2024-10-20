package br.com.cesarschool.poo.titulos.telas.acao;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import br.com.cesarschool.poo.titulos.telas.TelaInicio;

public class NavegacaoAcao {
    private JFrame frame;

    // Constantes para largura e altura
    private static final int LARGURA_JANELA = 450;
    private static final int ALTURA_JANELA = 450;
    private static final int LARGURA_BOTAO = 200;
    private static final int ALTURA_BOTAO = 30;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                NavegacaoAcao window = new NavegacaoAcao();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public NavegacaoAcao() {
        initialize();
    }

    public void setVisible(boolean visibilidade) {
        frame.setVisible(visibilidade);
    }

    private void initialize() {
        frame = new JFrame("Navegação Ação");
        frame.setSize(LARGURA_JANELA, ALTURA_JANELA);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Botão para incluir
        JButton btnIncluir = new JButton("Incluir");
        btnIncluir.setBounds(100, 30, LARGURA_BOTAO, ALTURA_BOTAO);
        btnIncluir.addActionListener(e -> abrirTelaIncluir());
        frame.getContentPane().add(btnIncluir);

        // Botão para alterar
        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(100, 80, LARGURA_BOTAO, ALTURA_BOTAO);
        btnAlterar.addActionListener(e -> abrirTelaAlterarAcao());
        frame.getContentPane().add(btnAlterar);

        // Botão para buscar
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(100, 130, LARGURA_BOTAO, ALTURA_BOTAO);
        btnBuscar.addActionListener(e -> abrirTelaBuscarAcao());
        frame.getContentPane().add(btnBuscar);

        // Botão para excluir
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(100, 180, LARGURA_BOTAO, ALTURA_BOTAO);
        btnExcluir.addActionListener(e -> abrirTelaExcluirAcao());
        frame.getContentPane().add(btnExcluir);

        // Botão para voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(150, 230, 100, ALTURA_BOTAO);
        btnVoltar.addActionListener(e -> {
            TelaInicio telaInicio = new TelaInicio();
            telaInicio.setVisible(true);
            frame.dispose(); // Fecha a tela atual
        });
        frame.getContentPane().add(btnVoltar);
    }

    private void abrirTelaAlterarAcao() {
        // Implementação da lógica para abrir TelaAlterarAcao
        TelaAlterarAcao telaAlterarAcao = new TelaAlterarAcao();
        telaAlterarAcao.setVisible(true);
        frame.setVisible(false); // Esconde NavegacaoAcao
    }

    private void abrirTelaIncluir() {
        // Implementação da lógica para abrir TelaIncluirAcao
        TelaIncluirAcao telaIncluirAcao = new TelaIncluirAcao();
        telaIncluirAcao.setVisible(true);
        frame.setVisible(false); // Esconde NavegacaoAcao
    }

    private void abrirTelaBuscarAcao() {
        // Implementação da lógica para abrir TelaBuscarAcao
        TelaBuscarAcao telaBuscarAcao = new TelaBuscarAcao();
        telaBuscarAcao.setVisible(true);
        frame.setVisible(false); // Esconde NavegacaoAcao
    }

    private void abrirTelaExcluirAcao() {
        // Implementação da lógica para abrir TelaExcluirAcao
        TelaExcluirAcao telaExcluirAcao = new TelaExcluirAcao();
        telaExcluirAcao.setVisible(true);
        frame.setVisible(false); // Esconde NavegacaoAcao
    }
}
