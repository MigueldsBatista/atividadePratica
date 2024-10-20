package br.com.cesarschool.poo.titulos.telas.tituloDivida;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;
import java.util.logging.Level;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.mediators.MediatorTituloDivida;

public class TelaIncluirTituloDivida {
    private JFrame frame;
    private JTextField textoId, textoNome, textoTaxaJuros, textoDataValidade;
    private JButton btnIncluir;   
    private MediatorTituloDivida mediatorAcao = MediatorTituloDivida.getInstancia();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TelaIncluirTituloDivida window = new TelaIncluirTituloDivida();
                window.frame.setVisible(true);
            } catch (Exception e) {
                Logger.getLogger(TelaIncluirTituloDivida.class.getName()).log(Level.SEVERE, null, e);
            }
        });
    }

    public TelaIncluirTituloDivida() {
        initialize();
    }

    public void setVisible(boolean visibilidade) {
        frame.setVisible(visibilidade);
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

        addLabel("Id", xLabel, yPos);
        textoId = addTextField(xTextField, yPos);
        yPos += 36;

        addLabel("Nome", xLabel, yPos);
        textoNome = addTextField(xTextField, yPos);
        yPos += 36;

        addLabel("Taxa de Juros", xLabel, yPos);
        textoTaxaJuros = addTextField(xTextField, yPos);
        yPos += 36;

        addLabel("Data de Validade", xLabel, yPos);
        textoDataValidade = addTextField(xTextField, yPos);
        yPos += 36;

        btnIncluir = new JButton("Incluir");
        btnIncluir.setBounds(xLabel, yPos, 90, 30);
        frame.getContentPane().add(btnIncluir);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(xLabel+100, yPos, 100, 30);
        btnVoltar.addActionListener(e -> {
            new NavegacaoTituloDivida().setVisible(true);
            frame.dispose(); // Fecha a tela atual
        });
        frame.getContentPane().add(btnVoltar);

        frame.getContentPane().add(btnVoltar);
        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(xLabel + 210, yPos, 100, 30);
        btnLimpar.addActionListener(e -> {
            textoId.setText("");
            textoNome.setText("");
            textoTaxaJuros.setText("");
            textoDataValidade.setText("");
        });
        frame.getContentPane().add(btnLimpar);
        // Adicionando a ação do botão
        btnIncluir.addActionListener(e -> incluirTitulo());
    }

    private JLabel addLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 121, 20);
        frame.getContentPane().add(label);
        return label;
    }

    private JTextField addTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 78, 26);
        frame.getContentPane().add(textField);
        return textField;
    }

    private void incluirTitulo() {
        try {
            int id = Integer.parseInt(textoId.getText());
            String nome = textoNome.getText();
            LocalDate dataValidade = LocalDate.parse(textoDataValidade.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            double valor = Double.parseDouble(textoTaxaJuros.getText());

            TituloDivida acao = new TituloDivida(id, nome, dataValidade, valor);
            String msg = mediatorAcao.incluir(acao);
            JOptionPane.showMessageDialog(null, msg != null ? msg : "Incluído com sucesso");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro: valor inválido - " + e.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir: " + ex.getMessage());
        }
    }
}
