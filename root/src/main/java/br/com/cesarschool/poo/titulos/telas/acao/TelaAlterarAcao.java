package br.com.cesarschool.poo.titulos.telas.acao;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.util.logging.Logger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.mediators.MediatorAcao;

public class TelaAlterarAcao {
    private JFrame frame;
    private JTextField txtId;
    private JTextField txtNome;
    private JTextField txtValor;
    private JTextField txtDataValidade;
    private JButton btnIncluir;   
    private MediatorAcao mediatorAcao = MediatorAcao.getInstancia();

    // Constantes para dimensões e espaçamentos
    private static final int LARGURA_JANELA = 500;
    private static final int ALTURA_JANELA = 450;
    private static final int ESPACO_VERTICAL = 40; // Espaçamento vertical entre os componentes
    private static final int LARGURA_BOTAO = 90;
    private static final int ALTURA_BOTAO = 30;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TelaAlterarAcao window = new TelaAlterarAcao();
                window.frame.setVisible(true);
            } catch (Exception e) {
                Logger.getLogger(TelaAlterarAcao.class.getName()).log(Level.SEVERE, null, e);
            }
        });
    }

    public TelaAlterarAcao() {
        initialize();
    }

    private void createFrame() {
        frame = new JFrame("Alterar Ação");
        frame.setBounds(100, 100, LARGURA_JANELA, ALTURA_JANELA);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
    }

    private void initialize() {
        createFrame();

        int yPos = 40; // Posição inicial y
        int xLabel = 41; // Posição x para Labels
        int xTextField = 183; // Posição x para TextFields

        // Adicionando os componentes
        adicionarComponente("ID atual", txtId = new JTextField(), xLabel, xTextField, yPos);
        yPos += ESPACO_VERTICAL;

        adicionarComponente("Novo Nome", txtNome = new JTextField(), xLabel, xTextField, yPos);
        yPos += ESPACO_VERTICAL;

        adicionarComponente("Novo Valor", txtValor = new JTextField(), xLabel, xTextField, yPos);
        yPos += ESPACO_VERTICAL;

        adicionarComponente("Data de Validade", txtDataValidade = new JTextField(), xLabel, xTextField, yPos);
        yPos += ESPACO_VERTICAL;

        // Botão para alterar
        btnIncluir = new JButton("Alterar");
        btnIncluir.setBounds(xLabel, yPos, LARGURA_BOTAO, ALTURA_BOTAO);
        frame.getContentPane().add(btnIncluir);

        // Botão para voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(xLabel + 120, yPos, 100, ALTURA_BOTAO);
        btnVoltar.addActionListener(e -> {
            NavegacaoAcao navegacaoAcao = new NavegacaoAcao();
            navegacaoAcao.setVisible(true);
            frame.dispose(); // Fecha a Tela Alterar
        });
        frame.getContentPane().add(btnVoltar);

        // Ação do botão para alterar
        btnIncluir.addActionListener(e -> alterarAcao());
    }

    private void adicionarComponente(String rotulo, JTextField campoTexto, int xLabel, int xTextField, int yPos) {
        JLabel label = new JLabel(rotulo);
        label.setBounds(xLabel, yPos, 121, 20);
        frame.getContentPane().add(label);
        
        campoTexto.setBounds(xTextField, yPos, 200, 26);
        frame.getContentPane().add(campoTexto);
    }

    private void alterarAcao() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String nome = txtNome.getText();
            LocalDate dataValidade = LocalDate.parse(txtDataValidade.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            double valor = Double.parseDouble(txtValor.getText());

            // Cria o objeto Acao
            Acao acao = new Acao(id, nome, dataValidade, valor);

            // Tenta incluir a ação usando o mediador
            String msg = mediatorAcao.alterar(acao);
            if (msg == null) {
                JOptionPane.showMessageDialog(null, "Ação alterada com sucesso");
            } else {
                JOptionPane.showMessageDialog(null, msg);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar: " + ex.getMessage());
        }
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}
