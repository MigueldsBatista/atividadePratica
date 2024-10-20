package br.com.cesarschool.poo.titulos.telas.entidadeOperadora;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import br.com.cesarschool.poo.titulos.mediators.MediatorEntidadeOperadora;

public class TelaExcluirEntidadeOperadora {
    private JFrame frame;
    private JTextField textoId;
    private JButton btnExcluir;   
    private MediatorEntidadeOperadora mediatorEntidadeOperadora = MediatorEntidadeOperadora.getInstancia();
    
    private static final int ESPACO_VERTICAL = 36;
    private static final int ESPACO_HORIZONTAL = 21;
    private static final int LARGURA_TEXTFIELD = 122;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TelaExcluirEntidadeOperadora window = new TelaExcluirEntidadeOperadora();
                window.frame.setVisible(true);
            } catch (Exception e) {
                Logger.getLogger(TelaExcluirEntidadeOperadora.class.getName()).log(Level.SEVERE, null, e);
            }
        });
    }

    public TelaExcluirEntidadeOperadora() {
        initialize();
    }

    public void setVisible(boolean visibilidade) {
        frame.setVisible(visibilidade);
    }

    private void createFrame() {
        frame = new JFrame("Excluir Entidade Operadora");
        frame.setBounds(100, 100, 556, 370);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
    }

    private void initialize() {
        createFrame();
        
        int yPos = 40; 
        int xLabel = 41; 
        int xTextField = 183; 

        // COMPONENTE 1
        JLabel labelId = new JLabel("ID da Entidade Operadora");
        labelId.setBounds(xLabel, yPos, 121, 20);
        frame.getContentPane().add(labelId);
        
        textoId = new JTextField();
        textoId.setBounds(xTextField, yPos, LARGURA_TEXTFIELD, 26);
        frame.getContentPane().add(textoId);
        yPos += ESPACO_VERTICAL; 

        // COMPONENTE 2
        btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(xLabel, yPos, 90, 30);
        frame.getContentPane().add(btnExcluir);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(150, yPos, 100, 30);
        btnVoltar.addActionListener(e -> {
            NavegacaoEntidadeOperadora navegacaoEntidadeOperadora = new NavegacaoEntidadeOperadora();
            navegacaoEntidadeOperadora.setVisible(true);
            frame.dispose(); 
        });
        frame.getContentPane().add(btnVoltar);
        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(xLabel + 230, yPos, 100, 30);
        btnLimpar.addActionListener(e -> {
            textoId.setText("");
        });
        frame.getContentPane().add(btnLimpar);
        // Ação do botão Excluir
        btnExcluir.addActionListener(e -> {
            try {
                int id = Integer.parseInt(textoId.getText());
                int confirmacao = JOptionPane.showConfirmDialog(frame, 
                    "Tem certeza que deseja excluir?", 
                    "Confirmação", 
                    JOptionPane.YES_NO_OPTION);
                
                if (confirmacao == JOptionPane.YES_OPTION) {
                    String msg = mediatorEntidadeOperadora.excluir(id);
                    if (msg == null) {
                        JOptionPane.showMessageDialog(null, "Entidade Operadora excluída com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao excluir Entidade Operadora: " + msg);
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir Entidade Operadora: " + ex.getMessage());
            }
        });
    }
}
