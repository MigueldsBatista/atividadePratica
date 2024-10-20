package br.com.cesarschool.poo.titulos.telas.acao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.mediators.MediatorAcao;

public class TelaIncluirAcao {
    private JFrame frame;
    private JTextField textoId;
    private JTextField textoNome;
    private JTextField textoValor;
    private JTextField textoDataValidade;
    private JButton btnIncluir;   
    private MediatorAcao mediatorAcao = MediatorAcao.getInstancia();

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TelaIncluirAcao window = new TelaIncluirAcao();
                window.frame.setVisible(true);
            } catch (Exception e) {
                Logger.getLogger(TelaIncluirAcao.class.getName()).log(Level.SEVERE, null, e);
            }
        });
    }

    public TelaIncluirAcao() {
        initialize();
    }

    private void createFrame() {
        frame = new JFrame();
        frame.setBounds(100, 100, 556, 370);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
    }

    private void initialize() {
        createFrame();

        int yPos = 40; // Posição inicial y
        int xLabel = 41; // Posição x para Labels
        int xTextField = 183; // Posição x para TextFields
        int textFieldWidth = 122; // Largura dos campos de texto
        int labelHeight = 20; // Altura dos labels
        int textFieldHeight = 26; // Altura dos campos de texto
        int buttonHeight = 30; // Altura dos botões
        int buttonWidth = 90; // Largura dos botões
        int verticalSpacing = 36; // Espaçamento vertical

        // COMPONENTE 1 - ID
        JLabel labelId = new JLabel("Id");
        labelId.setBounds(xLabel, yPos, 121, labelHeight);
        frame.getContentPane().add(labelId);
        textoId = new JTextField();
        textoId.setBounds(xTextField, yPos, textFieldWidth, textFieldHeight);
        frame.getContentPane().add(textoId);
        yPos += verticalSpacing; // Atualiza a posição y

        // COMPONENTE 2 - NOME
        JLabel labelNome = new JLabel("Nome");
        labelNome.setBounds(xLabel, yPos, 121, labelHeight);
        frame.getContentPane().add(labelNome);
        textoNome = new JTextField();
        textoNome.setBounds(xTextField, yPos, textFieldWidth, textFieldHeight);
        frame.getContentPane().add(textoNome);
        yPos += verticalSpacing;

        // COMPONENTE 3 - VALOR
        JLabel labelValor = new JLabel("Valor");
        labelValor.setBounds(xLabel, yPos, 121, labelHeight);
        frame.getContentPane().add(labelValor);
        textoValor = new JTextField();
        textoValor.setBounds(xTextField, yPos, textFieldWidth, textFieldHeight);
        frame.getContentPane().add(textoValor);
        yPos += verticalSpacing;

        // COMPONENTE 4 - DATA DE VALIDADE
        JLabel labelDataValidade = new JLabel("Data de Validade");
        labelDataValidade.setBounds(xLabel, yPos, 121, labelHeight);
        frame.getContentPane().add(labelDataValidade);
        textoDataValidade = new JTextField();
        textoDataValidade.setBounds(xTextField, yPos, textFieldWidth, textFieldHeight);
        frame.getContentPane().add(textoDataValidade);
        yPos += verticalSpacing;

        // COMPONENTE 5 - BOTÃO INCLUIR
        btnIncluir = new JButton("Incluir");
        btnIncluir.setBounds(xLabel, yPos, buttonWidth, buttonHeight);
        frame.getContentPane().add(btnIncluir);

        // COMPONENTE 6 - BOTÃO VOLTAR
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(150, yPos, 100, buttonHeight); // Botão 'Voltar' posicionado ao lado do botão 'Incluir'
        btnVoltar.addActionListener(e -> {
            NavegacaoAcao navegacaoAcao = new NavegacaoAcao();
            navegacaoAcao.setVisible(true);
            frame.dispose(); // Fecha a Tela
        });
        frame.getContentPane().add(btnVoltar);
        
        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(xLabel + 230, yPos, 90, 30);
        btnLimpar.addActionListener(e -> {
            textoId.setText("");
            textoNome.setText("");
            textoValor.setText("");
            textoDataValidade.setText("");
        });

        frame.getContentPane().add(btnLimpar);
        // Adicionando a ação do botão Incluir
        btnIncluir.addActionListener(e -> {
            try {
                int id = Integer.parseInt(textoId.getText());
                String nome = textoNome.getText();
                LocalDate dataValidade = LocalDate.parse(textoDataValidade.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                double valor = Double.parseDouble(textoValor.getText());
                
                // Cria o objeto Acao
                Acao acao = new Acao(id, nome, dataValidade, valor);

                // Tenta incluir a ação usando o mediador
                String msg = mediatorAcao.incluir(acao);
                if (msg == null) {
                    JOptionPane.showMessageDialog(null, "Incluído com sucesso");
                } else {
                    JOptionPane.showMessageDialog(null, msg);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao incluir: " + ex.getMessage());
            }
        });
    }
}
