package br.com.cesarschool.poo.titulos.telas.acao;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.util.logging.Logger;
import java.util.logging.Level;
import br.com.cesarschool.poo.titulos.mediators.MediatorAcao;

public class TelaExcluirAcao {
    private JFrame frame;
    private JTextField textoId;
    private JButton btnExcluir;   
    private MediatorAcao mediatorAcao = MediatorAcao.getInstancia();

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TelaExcluirAcao window = new TelaExcluirAcao();
                window.frame.setVisible(true);
            } catch (Exception e) {
                Logger.getLogger(TelaExcluirAcao.class.getName()).log(Level.SEVERE, null, e);
            }
        });
    }

    public TelaExcluirAcao() {
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

        JLabel labelId = new JLabel("ID da ação");
        labelId.setBounds(xLabel, yPos, 121, 20);
        frame.getContentPane().add(labelId);

        xLabel += 110;
        // Cria o campo de texto para o ID
        textoId = new JTextField();
        textoId.setBounds(xLabel, yPos, 122, 26);
        frame.getContentPane().add(textoId);
        yPos += 36; // Atualiza a posição y

        //COMPONENTE 2
        btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(xLabel, yPos, 90, 30);
        frame.getContentPane().add(btnExcluir);
        xLabel+= 110;

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(xLabel, yPos, 90, 30);
        btnVoltar.addActionListener(e -> {
            NavegacaoAcao navegacaoAcao = new NavegacaoAcao();
            navegacaoAcao.setVisible(true);
            frame.dispose(); // Fecha a Tela Excluir
        });
        frame.getContentPane().add(btnVoltar);
        
        btnExcluir.addActionListener(e -> {
            try {
                int id = Integer.parseInt(textoId.getText());            
                String msg = mediatorAcao.excluir(id);
                if (msg == null) {
                    JOptionPane.showMessageDialog(null, "Ação excluída com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir ação: " + msg);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, insira um ID válido.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir ação: " + ex.getMessage());
            }
        });
    }
}
