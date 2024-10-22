package br.com.cesarschool.poo.titulos.telas.entidadeOperadora;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import br.com.cesarschool.poo.titulos.MainApplicationScreen;

public class NavegacaoEntidadeOperadora {
    
    private JFrame frame;
    private static final int LARGURA_JANELA = 450;
    private static final int ALTURA_JANELA = 450;
    private static final int LARGURA_BOTAO = 200;
    private static final int ALTURA_BOTAO = 30;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                NavegacaoEntidadeOperadora window = new NavegacaoEntidadeOperadora();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

 public NavegacaoEntidadeOperadora(){
    initialize();

 }

    public void setVisible(boolean visibilidade) {
        frame.setVisible(visibilidade);
    }
    private void initialize() {
        frame = new JFrame("Navegação Entidade Operadora");
        frame.setSize(LARGURA_JANELA, ALTURA_JANELA);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Botão para incluir
        JButton btnIncluir = new JButton("Incluir");
        btnIncluir.setBounds(100, 80, LARGURA_BOTAO, ALTURA_BOTAO);
        btnIncluir.addActionListener(e -> abrirTelaIncluirEntidadeOperadora());
        frame.getContentPane().add(btnIncluir);

        // Botão para alterar
        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(100, 30, LARGURA_BOTAO, ALTURA_BOTAO);
        btnAlterar.addActionListener(e -> abrirTelaAlterarEntidadeOperadora());
        frame.getContentPane().add(btnAlterar);

        // Botão para buscar
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(100, 130, LARGURA_BOTAO, ALTURA_BOTAO);
        btnBuscar.addActionListener(e -> abrirTelaBuscarEntidadeOperadora());
        frame.getContentPane().add(btnBuscar);

        // Botão para excluir
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(100, 180, LARGURA_BOTAO, ALTURA_BOTAO);
        btnExcluir.addActionListener(e -> abrirTelaExcluirEntidadeOperadora());
        frame.getContentPane().add(btnExcluir);

        // Botão para voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(150, 230, 100, ALTURA_BOTAO);
        btnVoltar.addActionListener(e -> {
            MainApplicationScreen telaInicio = new MainApplicationScreen();
            telaInicio.setVisible(true);
            frame.dispose(); // Fecha a tela atual
        });
        frame.getContentPane().add(btnVoltar);
    }

    private void abrirTelaIncluirEntidadeOperadora() {
        TelaIncluirEntidadeOperadora tela = new TelaIncluirEntidadeOperadora();
        tela.setVisible(true);
        frame.setVisible(false);
    }

    private void abrirTelaAlterarEntidadeOperadora() {
        TelaAlterarEntidadeOperadora tela = new TelaAlterarEntidadeOperadora();
        tela.setVisible(true);
        frame.setVisible(false);
    }

    private void abrirTelaBuscarEntidadeOperadora() {
        TelaBuscarEntidadeOperadora tela = new TelaBuscarEntidadeOperadora();
        tela.setVisible(true);
        frame.setVisible(false);
    }
    private void abrirTelaExcluirEntidadeOperadora() {
        TelaExcluirEntidadeOperadora tela = new TelaExcluirEntidadeOperadora();
        tela.setVisible(true);
        frame.setVisible(false);
    }
}
