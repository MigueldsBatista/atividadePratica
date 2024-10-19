package br.com.cesarschool.poo.titulos.telas;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TelaEntidade {

    private JFrame frame;
    private final EntidadeMediator mediator = new EntidadeMediator(); 
    private JTextField txtCodigo;
    private JButton btnNovo;
    private JButton btnBuscar;
    private JLabel lblNome;
    private JTextField txtNome;
    private JLabel lblRenda;
    private JTextField txtRenda;
    private JButton btnIncluirAlterar;
    private JButton btnCancelar;
    private JButton btnLimpar;

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new TelaEntidade();
            } catch (Exception e) {
                Logger.getLogger(TelaEntidade.class.getName()).log(Level.SEVERE, null, e);
            }
        });
    }

    /**
     * Create the application.
     */
    public TelaEntidade() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 607, 369);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblCodigo = new JLabel("Código");
        lblCodigo.setBounds(36, 46, 70, 20);
        frame.getContentPane().add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(129, 46, 113, 26);
        frame.getContentPane().add(txtCodigo);
        txtCodigo.setColumns(10);

        btnNovo = new JButton("Novo");
        btnNovo.setBounds(264, 42, 90, 30);
        frame.getContentPane().add(btnNovo);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(360, 42, 90, 30);
        frame.getContentPane().add(btnBuscar);

        lblNome = new JLabel("Nome");
        lblNome.setBounds(36, 109, 70, 20);
        frame.getContentPane().add(lblNome);

        txtNome = new JTextField();
        txtNome.setEnabled(false);
        txtNome.setBounds(129, 109, 225, 26);
        frame.getContentPane().add(txtNome);
        txtNome.setColumns(10);

        lblRenda = new JLabel("Renda");
        lblRenda.setBounds(36, 167, 70, 20);
        frame.getContentPane().add(lblRenda);

        txtRenda = new JTextField();
        txtRenda.setEnabled(false);
        txtRenda.setBounds(129, 164, 113, 26);
        frame.getContentPane().add(txtRenda);
        txtRenda.setColumns(10);

        btnIncluirAlterar = new JButton("Incluir");
        btnIncluirAlterar.setEnabled(false);
        btnIncluirAlterar.setBounds(131, 258, 90, 30);
        frame.getContentPane().add(btnIncluirAlterar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setEnabled(false);
        btnCancelar.setBounds(239, 258, 90, 30);
        frame.getContentPane().add(btnCancelar);

        btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(347, 258, 90, 30);
        frame.getContentPane().add(btnLimpar);

        btnNovo.addActionListener(e -> {
            Entidade ent = mediator.buscar(txtCodigo.getText());
            if (ent != null) {
                JOptionPane.showMessageDialog(null, "Entidade já existente!");
            } else {
                btnIncluirAlterar.setEnabled(true);
                btnCancelar.setEnabled(true);
                txtNome.setEnabled(true);
                txtRenda.setEnabled(true);
                btnNovo.setEnabled(false);
                btnBuscar.setEnabled(false);
                txtCodigo.setEnabled(false);
            }
        });

        btnBuscar.addActionListener(e -> {
            Entidade ent = mediator.buscar(txtCodigo.getText());
            if (ent == null) {
                JOptionPane.showMessageDialog(null, "Entidade não existente!");
            } else {
                txtNome.setText(ent.getNome());
                txtRenda.setText(ent.getRenda() + "");
                btnIncluirAlterar.setText("Alterar");
                btnIncluirAlterar.setEnabled(true);
                btnCancelar.setEnabled(true);
                txtNome.setEnabled(true);
                txtRenda.setEnabled(true);
                btnNovo.setEnabled(false);
                btnBuscar.setEnabled(false);
                txtCodigo.setEnabled(false);
            }
        });

        btnIncluirAlterar.addActionListener(e -> {
            Entidade ent = new Entidade(txtCodigo.getText(), txtNome.getText(), Double.parseDouble(txtRenda.getText()));
            String msg;
            if (btnIncluirAlterar.getText().equals("Incluir")) {
                msg = mediator.incluir(ent);
            } else {
                msg = mediator.alterar(ent);
            }
            if (msg != null) {
                JOptionPane.showMessageDialog(null, msg);                    
            } else {
                btnIncluirAlterar.setEnabled(false);
                btnCancelar.setEnabled(false);
                txtNome.setEnabled(false);
                txtRenda.setEnabled(false);
                btnNovo.setEnabled(true);
                btnBuscar.setEnabled(true);
                txtCodigo.setEnabled(true);
                txtCodigo.setText("");
                txtRenda.setText("");
                txtNome.setText("");
                btnIncluirAlterar.setText("Incluir");
            }
        });

        btnCancelar.addActionListener(e -> {
            btnIncluirAlterar.setEnabled(false);
            btnCancelar.setEnabled(false);
            txtNome.setEnabled(false);
            txtRenda.setEnabled(false);
            btnNovo.setEnabled(true);
            btnBuscar.setEnabled(true);
            txtCodigo.setEnabled(true);
            txtCodigo.setText("");
            txtRenda.setText("");
            txtNome.setText("");
            btnIncluirAlterar.setText("Incluir");
        });

        btnLimpar.addActionListener(e -> {
            if (txtCodigo.isEnabled()) {
                txtCodigo.setText("");
            }
            txtRenda.setText("");
            txtNome.setText("");                
        });
    }
}