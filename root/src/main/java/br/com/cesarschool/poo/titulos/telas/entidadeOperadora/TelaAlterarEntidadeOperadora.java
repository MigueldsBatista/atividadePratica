package br.com.cesarschool.poo.titulos.telas.entidadeOperadora;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.mediators.MediatorEntidadeOperadora;

public class TelaAlterarEntidadeOperadora {
    private JFrame frame;
    private JTextField textoId;
    private JTextField textoNome;
    private JComboBox<String> comboAutorizadoAcao;
    private JTextField textoSaldoAcao;
    private JTextField textoSaldoTituloDivida;
    private JButton btnAlterar;   
    private MediatorEntidadeOperadora mediatorEntidadeOperadora = MediatorEntidadeOperadora.getInstancia();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TelaAlterarEntidadeOperadora window = new TelaAlterarEntidadeOperadora();
                window.frame.setVisible(true);
            } catch (Exception e) {
                Logger.getLogger(TelaAlterarEntidadeOperadora.class.getName()).log(Level.SEVERE, null, e);
            }
        });
    }

    public TelaAlterarEntidadeOperadora() {
        initialize();
    }

    public void setVisible(boolean visibilidade) {
        frame.setVisible(visibilidade);
    }

    private void createFrame() {
        frame = new JFrame("Alterar Entidade Operadora");
        frame.setBounds(100, 100, 556, 370);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
    }

    private void initialize() {
        createFrame();
        int yPos = 40; // Posição inicial y
        int xLabel = 41; // Posição x para Labels
        int xTextField = 183; // Posição x para TextFields
        int espacoY = 36; // Espaçamento vertical
        int alturaLabel = 20; // Altura para Labels
        int alturaTextField = 26; // Altura para TextFields
        int alturaBotao = 30; // Altura para Botões

        // COMPONENTE 1
        JLabel labelId = new JLabel("ID atual");
        labelId.setBounds(xLabel, yPos, 121, alturaLabel);
        frame.getContentPane().add(labelId);
        
        textoId = new JTextField();
        textoId.setBounds(xTextField, yPos, 78, alturaTextField);
        frame.getContentPane().add(textoId);
        yPos += espacoY;

        // COMPONENTE 2
        JLabel labelNome = new JLabel("Novo Nome");
        labelNome.setBounds(xLabel, yPos, 121, alturaLabel);
        frame.getContentPane().add(labelNome);
        textoNome = new JTextField();
        textoNome.setBounds(xTextField, yPos, 78, alturaTextField);
        frame.getContentPane().add(textoNome);
        yPos += espacoY;

        // COMPONENTE 3
        JLabel labelAutorizadoAcao = new JLabel("Nova autorização ação");
        labelAutorizadoAcao.setBounds(xLabel, yPos, 121, alturaLabel);
        frame.getContentPane().add(labelAutorizadoAcao);

        String[] options = {"true", "false"};
        comboAutorizadoAcao = new JComboBox<>(options);
        comboAutorizadoAcao.setBounds(xTextField, yPos, 78, alturaTextField);
        frame.getContentPane().add(comboAutorizadoAcao);
        yPos += espacoY;

        // COMPONENTE 4
        JLabel labelSaldoAcao = new JLabel("Novo Saldo ação");
        labelSaldoAcao.setBounds(xLabel, yPos, 121, alturaLabel);
        frame.getContentPane().add(labelSaldoAcao);
        textoSaldoAcao = new JTextField();
        textoSaldoAcao.setBounds(xTextField, yPos, 78, alturaTextField);
        frame.getContentPane().add(textoSaldoAcao);
        yPos += espacoY;

        // COMPONENTE 5
        JLabel labelSaldoTituloDivida = new JLabel("Novo saldo título dívida");
        labelSaldoTituloDivida.setBounds(xLabel, yPos, 121, alturaLabel);
        frame.getContentPane().add(labelSaldoTituloDivida);
        textoSaldoTituloDivida = new JTextField();
        textoSaldoTituloDivida.setBounds(xTextField, yPos, 78, alturaTextField);
        frame.getContentPane().add(textoSaldoTituloDivida);
        yPos += espacoY;

        // COMPONENTE 6
        btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(xLabel, yPos, 90, alturaBotao);
        frame.getContentPane().add(btnAlterar);
        
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(xLabel+100, yPos, 90, alturaBotao);
        btnVoltar.addActionListener(e -> {
            NavegacaoEntidadeOperadora navegacaoEntidadeOperadora = new NavegacaoEntidadeOperadora();
            navegacaoEntidadeOperadora.setVisible(true);
            frame.dispose(); // Fecha a Tela Alterar
        });
        frame.getContentPane().add(btnVoltar);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(xLabel + 200, yPos, 100, 30);
        btnLimpar.addActionListener(e -> {
            textoId.setText("");
            textoNome.setText("");
            textoSaldoAcao.setText("");
            textoSaldoTituloDivida.setText("");
        });
        frame.getContentPane().add(btnLimpar);

        // Adicionando a ação do botão
        btnAlterar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(textoId.getText());
                String nome = textoNome.getText();
                boolean autorizadoAcao = Boolean.parseBoolean(comboAutorizadoAcao.getSelectedItem().toString());
                double saldoTituloDivida = Double.parseDouble(textoSaldoTituloDivida.getText());
                double saldoAcao = Double.parseDouble(textoSaldoAcao.getText());
                
                // Cria o objeto EntidadeOperadora aqui
                EntidadeOperadora entidadeOperadora = new EntidadeOperadora(id, nome, autorizadoAcao, saldoTituloDivida, saldoAcao);

                // Tenta incluir a ação usando o mediador
                String msg = mediatorEntidadeOperadora.alterar(entidadeOperadora);
                if (msg == null) {
                    JOptionPane.showMessageDialog(null, "Entidade alterada com sucesso");
                } else {
                    JOptionPane.showMessageDialog(null, msg);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao alterar: " + ex.getMessage());
            }
        });
    }
}
