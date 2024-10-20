package br.com.cesarschool.poo.titulos.telas.entidadeOperadora;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.util.logging.Logger;
import java.util.logging.Level;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.mediators.MediatorEntidadeOperadora;

public class TelaIncluirEntidadeOperadora {
    private JFrame frame;
    private JTextField textoId;
    private JTextField textoNome;
    private JComboBox<String> comboAutorizadoAcao;
    private JTextField textoSaldoAcao;
    private JTextField textoSaldoTituloDivida;
    private JButton btnIncluir;   
    private MediatorEntidadeOperadora mediatorEntidadeOperadora = MediatorEntidadeOperadora.getInstancia();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TelaIncluirEntidadeOperadora window = new TelaIncluirEntidadeOperadora();
                window.frame.setVisible(true);
            } catch (Exception e) {
                Logger.getLogger(TelaIncluirEntidadeOperadora.class.getName()).log(Level.SEVERE, null, e);
            }
        });
    }

    public TelaIncluirEntidadeOperadora() {
        initialize();
    }

    public void setVisible(boolean visibilidade) {
        frame.setVisible(visibilidade);
    }

    private void createFrame() {
        frame = new JFrame("Incluir Entidade Operadora");
        frame.setBounds(100, 100, 556, 370);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
    }

    private void initialize() {
        createFrame();

        int yPos = 40; // Posição inicial y
        int xLabel = 41; // Posição x para Labels
        int xTextField = 183; // Posição x para TextFields
        int fieldWidth = 78; // Largura comum para campos de texto
        int labelHeight = 20; // Altura dos labels
        int textFieldHeight = 26; // Altura dos text fields
        int buttonHeight = 30; // Altura dos botões
        int buttonWidth = 90; // Largura dos botões
        int verticalSpacing = 36; // Espaçamento vertical
        int horizontalSpacing = 21; // Espaçamento horizontal

        // COMPONENTE 1
        JLabel labelId = new JLabel("ID");
        labelId.setBounds(xLabel, yPos, 121, labelHeight);
        frame.getContentPane().add(labelId);
        textoId = new JTextField();
        textoId.setBounds(xTextField, yPos, fieldWidth, textFieldHeight);
        frame.getContentPane().add(textoId);
        yPos += verticalSpacing; // Atualiza a posição y

        // COMPONENTE 2
        JLabel labelNome = new JLabel("Nome");
        labelNome.setBounds(xLabel, yPos, 121, labelHeight);
        frame.getContentPane().add(labelNome);
        textoNome = new JTextField();
        textoNome.setBounds(xTextField, yPos, fieldWidth, textFieldHeight);
        frame.getContentPane().add(textoNome);
        yPos += verticalSpacing;

        // COMPONENTE 3
        JLabel labelAutorizadoAcao = new JLabel("Autorizado ação");
        labelAutorizadoAcao.setBounds(xLabel, yPos, 121, labelHeight);
        frame.getContentPane().add(labelAutorizadoAcao);

        // Criação do JComboBox com as opções true e false
        String[] options = {"true", "false"};
        comboAutorizadoAcao = new JComboBox<>(options);
        comboAutorizadoAcao.setBounds(xTextField, yPos, fieldWidth, textFieldHeight);
        frame.getContentPane().add(comboAutorizadoAcao);

        // Atualiza a posição vertical para o próximo componente
        yPos += verticalSpacing;

        // COMPONENTE 4
        JLabel labelSaldoAcao = new JLabel("Saldo ação");
        labelSaldoAcao.setBounds(xLabel, yPos, 121, labelHeight);
        frame.getContentPane().add(labelSaldoAcao);
        textoSaldoAcao = new JTextField();
        textoSaldoAcao.setBounds(xTextField, yPos, fieldWidth, textFieldHeight);
        frame.getContentPane().add(textoSaldoAcao);
        yPos += verticalSpacing;

        // COMPONENTE 5
        JLabel labelSaldoTituloDivida = new JLabel("Saldo título dívida");
        labelSaldoTituloDivida.setBounds(xLabel, yPos, 121, labelHeight);
        frame.getContentPane().add(labelSaldoTituloDivida);
        textoSaldoTituloDivida = new JTextField();
        textoSaldoTituloDivida.setBounds(xTextField, yPos, fieldWidth, textFieldHeight);
        frame.getContentPane().add(textoSaldoTituloDivida);
        yPos += verticalSpacing;

        // COMPONENTE 6
        btnIncluir = new JButton("Incluir");
        btnIncluir.setBounds(xLabel, yPos, buttonWidth, buttonHeight);
        frame.getContentPane().add(btnIncluir);

        // COMPONENTE 7
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(xLabel + buttonWidth + horizontalSpacing, yPos, buttonWidth, buttonHeight);
        btnVoltar.addActionListener(e -> {
            NavegacaoEntidadeOperadora navegacaoEntidadeOperadora = new NavegacaoEntidadeOperadora();
            navegacaoEntidadeOperadora.setVisible(true);
            frame.dispose(); // Fecha a tela atual
        });

        frame.getContentPane().add(btnVoltar);
        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(xLabel + 220, yPos, 100, 30);
        btnLimpar.addActionListener(e -> {
            textoId.setText("");
            textoNome.setText("");
            comboAutorizadoAcao.setSelectedIndex(0);
            textoSaldoAcao.setText("");
            textoSaldoTituloDivida.setText("");
        });
        frame.getContentPane().add(btnLimpar);

        // Adicionando a ação do botão Incluir
        btnIncluir.addActionListener(e -> {
            try {
                int id = Integer.parseInt(textoId.getText());
                String nome = textoNome.getText();
                boolean autorizadoAcao = Boolean.parseBoolean(comboAutorizadoAcao.getSelectedItem().toString());

                double saldoTituloDivida = Double.parseDouble(textoSaldoTituloDivida.getText());
                double saldoAcao = Double.parseDouble(textoSaldoAcao.getText());
                
                // Cria o objeto EntidadeOperadora
                EntidadeOperadora entidadeOperadora = new EntidadeOperadora(id, nome, autorizadoAcao, saldoTituloDivida, saldoAcao);

                // Tenta incluir a ação usando o mediador
                String msg = mediatorEntidadeOperadora.incluir(entidadeOperadora);
                if (msg == null) {
                    JOptionPane.showMessageDialog(null, "Entidade Operadora incluída com sucesso");
                } else {
                    JOptionPane.showMessageDialog(null, msg);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao incluir: " + ex.getMessage());
            }
        });
    }
}
