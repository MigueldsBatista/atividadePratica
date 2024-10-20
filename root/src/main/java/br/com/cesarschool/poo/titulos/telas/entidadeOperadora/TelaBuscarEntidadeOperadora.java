package br.com.cesarschool.poo.titulos.telas.entidadeOperadora;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
<<<<<<< HEAD
import java.util.logging.Logger;

=======
>>>>>>> b6a42c1 (TRABALHO FINALIZADO VAMOOO)
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.mediators.MediatorEntidadeOperadora;

public class TelaBuscarEntidadeOperadora {
    private JFrame frame;
    private JTextField textoId;
    private JTextField textoNome;
    private JTextField textoAutorizacaoAcao;
    private JTextField textoSaldoAcao;
    private JTextField textoSaldoTituloDivida;
    private JButton btnBuscar;

    private MediatorEntidadeOperadora mediatorEntidadeOperadora = MediatorEntidadeOperadora.getInstancia();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TelaBuscarEntidadeOperadora window = new TelaBuscarEntidadeOperadora();
                window.frame.setVisible(true);
            } catch (Exception e) {
                Logger.getLogger(TelaBuscarEntidadeOperadora.class.getName()).log(Level.SEVERE, null, e);
            }
        });
    }

    public TelaBuscarEntidadeOperadora() {
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
        int espacoY = 36; // Espaçamento vertical

        // COMPONENTE 1: ID da Ação
        JLabel labelId = new JLabel("ID da ação");
        labelId.setBounds(xLabel, yPos, 121, 20);
        frame.getContentPane().add(labelId);
        
        textoId = new JTextField();
        textoId.setBounds(xTextField, yPos, 122, 26);
        frame.getContentPane().add(textoId);
        yPos += espacoY;

        // COMPONENTE 2: Nome
        JLabel labelNome = new JLabel("Nome");
        labelNome.setBounds(xLabel, yPos, 121, 20);
        frame.getContentPane().add(labelNome);
        
        textoNome = new JTextField();
        textoNome.setBounds(xTextField, yPos, 78, 26);
        textoNome.setEnabled(false);
        textoNome.setEditable(false);
        frame.getContentPane().add(textoNome);
        yPos += espacoY;

        // COMPONENTE 3: Autorização Ação
        JLabel labelAutorizacaoAcao = new JLabel("Autorização ação");
        labelAutorizacaoAcao.setBounds(xLabel, yPos, 121, 20);
        frame.getContentPane().add(labelAutorizacaoAcao);
        
        textoAutorizacaoAcao = new JTextField();
        textoAutorizacaoAcao.setBounds(xTextField, yPos, 78, 26);
        textoAutorizacaoAcao.setEnabled(false); // Definido como não editável
        frame.getContentPane().add(textoAutorizacaoAcao);
        yPos += espacoY;

        // COMPONENTE 4: Saldo Ação
        JLabel labelSaldoAcao = new JLabel("Saldo ação");
        labelSaldoAcao.setBounds(xLabel, yPos, 121, 20);
        frame.getContentPane().add(labelSaldoAcao);
        
        textoSaldoAcao = new JTextField();
        textoSaldoAcao.setBounds(xTextField, yPos, 78, 26);
        textoSaldoAcao.setEnabled(false); // Definido como não editável
        frame.getContentPane().add(textoSaldoAcao);
        yPos += espacoY;

        // COMPONENTE 5: Saldo Título Dívida
        JLabel labelSaldoTituloDivida = new JLabel("Saldo título dívida");
        labelSaldoTituloDivida.setBounds(xLabel, yPos, 121, 20);
        frame.getContentPane().add(labelSaldoTituloDivida);
        
        textoSaldoTituloDivida = new JTextField();
        textoSaldoTituloDivida.setBounds(xTextField, yPos, 78, 26);
        textoSaldoTituloDivida.setEnabled(false); // Definido como não editável
        frame.getContentPane().add(textoSaldoTituloDivida);
        yPos += espacoY;

        // COMPONENTE 6: Botão Buscar
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(xLabel, yPos, 90, 30);
        frame.getContentPane().add(btnBuscar);

        // COMPONENTE 7: Botão Voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(150, yPos, 90, 30);
        btnVoltar.addActionListener(e -> {
            NavegacaoEntidadeOperadora navegacaoEntidadeOperadora = new NavegacaoEntidadeOperadora();
            navegacaoEntidadeOperadora.setVisible(true);
            frame.dispose(); // Fecha a Tela Buscar
        });
        frame.getContentPane().add(btnVoltar);
        
        // Ação do botão Buscar
        btnBuscar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(textoId.getText());

                // Tenta buscar a ação usando o mediador
                EntidadeOperadora entidadeOperadora = mediatorEntidadeOperadora.buscar(id);
                if (entidadeOperadora == null) {
                    JOptionPane.showMessageDialog(null, "Erro ao buscar Entidade Operadora!");
                } else {
                    textoNome.setText(entidadeOperadora.getNome());
                    textoSaldoAcao.setText(String.valueOf(entidadeOperadora.getSaldoAcao()));
                    textoSaldoTituloDivida.setText(String.valueOf(entidadeOperadora.getSaldoTituloDivida()));
                    JOptionPane.showMessageDialog(null, "Entidade Operadora encontrada com sucesso!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar Entidade Operadora: " + ex.getMessage());
            }
        });
    }
}
