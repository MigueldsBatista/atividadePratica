package br.com.cesarschool.poo.titulos.telas;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TelaCadastro {

    private JFrame frame;
    private static final EntidadeMediator mediator = new EntidadeMediator();    
    private JTextField txtCodigo;
    private JTextField txtNome;
    private JTextField txtRenda;

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TelaCadastro telaCadastro = new TelaCadastro();
                telaCadastro.frame.setVisible(true);
            } catch (Exception e) {
                Logger.getLogger(TelaCadastro.class.getName()).log(Level.SEVERE, null, e);git lo
            }
        });
    }

    /**
     * Create the application.
     */
    public TelaCadastro() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 584, 323);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblCodigo = new JLabel("Código");
        lblCodigo.setBounds(42, 41, 70, 20);
        frame.getContentPane().add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setToolTipText("Digite o código");
        txtCodigo.setBounds(110, 41, 100, 26);
        frame.getContentPane().add(txtCodigo);
        txtCodigo.setColumns(10);

        JButton btnNovo = new JButton("Novo");
        btnNovo.setBounds(237, 41, 90, 30);
        frame.getContentPane().add(btnNovo);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(345, 41, 90, 30);
        frame.getContentPane().add(btnBuscar);

        JLabel lblNome = new JLabel("Nome");
        lblNome.setBounds(42, 101, 70, 20);
        frame.getContentPane().add(lblNome);

        txtNome = new JTextField();
        txtNome.setEnabled(false);
        txtNome.setBounds(110, 101, 225, 26);
        frame.getContentPane().add(txtNome);
        txtNome.setColumns(10);

        JLabel lblRenda = new JLabel("Renda");
        lblRenda.setBounds(42, 162, 70, 20);
        frame.getContentPane().add(lblRenda);

        txtRenda = new JTextField();
        txtRenda.setEnabled(false);
        txtRenda.setBounds(110, 162, 118, 26);
        frame.getContentPane().add(txtRenda);
        txtRenda.setColumns(10);

        JButton btnIncluirAlterar = new JButton("Incluir");
        btnIncluirAlterar.setEnabled(false);
        btnIncluirAlterar.setBounds(138, 223, 90, 30);
        frame.getContentPane().add(btnIncluirAlterar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setEnabled(false);
        btnCancelar.setBounds(248, 223, 90, 30);
        frame.getContentPane().add(btnCancelar);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(363, 223, 90, 30);
        frame.getContentPane().add(btnLimpar);

        btnNovo.addActionListener(e -> {
            Entidade ent = TelaCadastro.mediator.buscar(txtCodigo.getText());
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
            Entidade ent = TelaCadastro.mediator.buscar(txtCodigo.getText());
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