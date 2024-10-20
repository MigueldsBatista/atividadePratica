package br.com.cesarschool.poo.titulos.telas.tituloDivida;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.util.logging.Logger;
import java.util.logging.Level;
import br.com.cesarschool.poo.titulos.mediators.MediatorTituloDivida;

public class TelaExcluirTituloDivida {
    private JFrame frame;
    private JTextField texoId;
    private JButton btnExcluir;   
    private MediatorTituloDivida mediatorTituloDivida = MediatorTituloDivida.getInstancia();

    /*
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TelaExcluirTituloDivida window = new TelaExcluirTituloDivida();
                window.frame.setVisible(true);
            } catch (Exception e) {
                Logger.getLogger(TelaExcluirTituloDivida.class.getName()).log(Level.SEVERE, null, e); // Loga a exceção
            }
        });
    }

    public TelaExcluirTituloDivida() {
        initialize();
    }

    public void setVisible(boolean visibilidade) {
        frame.setVisible(visibilidade);
    }

    private void createFrame() {
        frame = new JFrame("Excluir Título Dívida");
        frame.setBounds(100, 100, 556, 370);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
    }

    private void initialize() {
        createFrame();
        
        int yPos = 40; // Posição inicial y
        int xLabel = 41; // Posição x para Labels
        int xTextField = 183; // Posição x para TextFields

        // COMPONENTE 1
        JLabel labelId = new JLabel("ID do título");
        labelId.setBounds(xLabel, yPos, 121, 20);
        frame.getContentPane().add(labelId);
        
        texoId = new JTextField();
        texoId.setBounds(xTextField, yPos, 122, 26);
        frame.getContentPane().add(texoId);
        yPos += 36; // Atualiza a posição y

        // COMPONENTE 2 - Botão Excluir
        btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(xLabel, yPos, 90, 30);
        frame.getContentPane().add(btnExcluir);
        
        // COMPONENTE 3 - Botão Voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(150, yPos, 90, 30); // Usando a mesma yPos para alinhar
        btnVoltar.addActionListener(e -> {
            NavegacaoTituloDivida navegacaoTituloDivida = new NavegacaoTituloDivida();
            navegacaoTituloDivida.setVisible(true);
            frame.dispose(); // Fecha a tela de exclusão
        });
        frame.getContentPane().add(btnVoltar);

        frame.getContentPane().add(btnVoltar);
        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(xLabel + 220, yPos, 100, 30); // Assuming ALTURA_BOTAO is 30
        btnLimpar.addActionListener(e -> {
            texoId.setText("");
        });
        frame.getContentPane().add(btnLimpar);
        
        // Adicionando a ação do botão Excluir
        btnExcluir.addActionListener(e -> {
            try {
                int id = Integer.parseInt(texoId.getText());
                String msg = mediatorTituloDivida.excluir(id);
                if (msg == null) {
                    JOptionPane.showMessageDialog(null, "Título excluído com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir título: " + msg);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, insira um ID válido.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir título: " + ex.getMessage());
            }
        });
    }
}
