package br.com.cesarschool.poo.titulos.telas;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TelaAdicao {

    private JFrame frame;
    private JTextField txtPrimeiroNumero;
    private JTextField txtSegundoNumero;
    private JTextField txtResultado;

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TelaAdicao window = new TelaAdicao();
                window.frame.setVisible(true);
            } catch (Exception e) {
                java.util.logging.Logger.getLogger(TelaAdicao.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
            }
        });
    }

    /**
     * Create the application.
     */
    public TelaAdicao() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 556, 370);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);//permite definir a posição dos componentes manualmente
        //incluindo FlowLayout, BorderLayout, GridLayout, BoxLayout, GridBagLayout, GroupLayout

        //getContentPane() retorna o painel de conteúdo do JFrame

        JLabel lblPrimeiroNumero = new JLabel("Primeiro número");
        lblPrimeiroNumero.setBounds(41, 40, 121, 20);
        frame.getContentPane().add(lblPrimeiroNumero);//adiciona o componente ao painel de conteúdo
        txtPrimeiroNumero = new JTextField();
        txtPrimeiroNumero.setBounds(183, 40, 78, 26);
        frame.getContentPane().add(txtPrimeiroNumero);
        txtPrimeiroNumero.setColumns(10);//define o número de colunas no campo de texto

        JLabel lblSegundoNumero = new JLabel("Segundo número");
        lblSegundoNumero.setBounds(41, 102, 121, 20);
        frame.getContentPane().add(lblSegundoNumero);



        txtSegundoNumero = new JTextField();
        txtSegundoNumero.setBounds(183, 102, 78, 26);
        frame.getContentPane().add(txtSegundoNumero);
        txtSegundoNumero.setColumns(10);

        JLabel lblResultado = new JLabel("Resultado");
        lblResultado.setBounds(41, 163, 70, 20);
        frame.getContentPane().add(lblResultado);

        txtResultado = new JTextField();
        txtResultado.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        txtResultado.setEnabled(false);
        txtResultado.setEditable(false);
        txtResultado.setBounds(183, 163, 78, 37);
        frame.getContentPane().add(txtResultado);
        txtResultado.setColumns(10);

        JButton btnSomar = new JButton("Somar");
        btnSomar.addActionListener(e -> {
            double n1 = Double.parseDouble(txtPrimeiroNumero.getText());
            double n2 = Double.parseDouble(txtSegundoNumero.getText());
            double soma = n1 + n2;
            txtResultado.setText("" + soma);
        });
        btnSomar.setBounds(130, 220, 90, 30);
        frame.getContentPane().add(btnSomar);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.addActionListener(e -> {
            txtPrimeiroNumero.setText("");
            txtSegundoNumero.setText("");
            txtResultado.setText("");
        });
        btnLimpar.setBounds(254, 220, 90, 30);
        frame.getContentPane().add(btnLimpar);
    }
}