package br.com.cesarschool.poo.titulos.telas.tituloDivida;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.util.logging.Logger;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.mediators.MediatorTituloDivida;

public class TelaBuscarTituloDivida {
    private JFrame frame;
    private JTextField textoId;
    private JTextField textoNome;
    private JTextField textoTaxaJuros;
    private JTextField textoDataValidade;
    private JButton btnBuscar;   
    private MediatorTituloDivida mediatorTituloDivida = MediatorTituloDivida.getInstancia();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TelaBuscarTituloDivida window = new TelaBuscarTituloDivida();
                window.frame.setVisible(true);
            } catch (Exception e) {
                Logger.getLogger(TelaBuscarTituloDivida.class.getName()).log(Level.SEVERE, null, e);
            }
        });
    }

    public TelaBuscarTituloDivida() {
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

        // COMPONENTE 1
        JLabel labelId = new JLabel("ID atual");
        labelId.setBounds(xLabel, yPos, 121, 20);
        frame.getContentPane().add(labelId);
        textoId = new JTextField();
        textoId.setBounds(xTextField, yPos, 122, 26);
        frame.getContentPane().add(textoId);
        yPos += 36; // Atualiza a posição y

        // COMPONENTE 2
        JLabel labelNome = new JLabel("Novo Nome");
        labelNome.setBounds(xLabel, yPos, 121, 20);
        frame.getContentPane().add(labelNome);
        textoNome = new JTextField();
        textoNome.setBounds(xTextField, yPos, 122, 26);
        textoNome.setEnabled(false);
        textoNome.setEditable(false);
        frame.getContentPane().add(textoNome);
        yPos += 36; // Atualiza a posição y

        // COMPONENTE 3
        JLabel labelTaxaJuros = new JLabel("Novo Valor");
        labelTaxaJuros.setBounds(xLabel, yPos, 121, 20);
        frame.getContentPane().add(labelTaxaJuros);
        textoTaxaJuros = new JTextField();
        textoTaxaJuros.setBounds(xTextField, yPos, 122, 26);
        textoTaxaJuros.setEnabled(false);
        textoTaxaJuros.setEditable(false);
        frame.getContentPane().add(textoTaxaJuros);
        yPos += 36; // Atualiza a posição y

        // COMPONENTE 4
        JLabel labelDataValidade = new JLabel("Data de Validade");
        labelDataValidade.setBounds(xLabel, yPos, 121, 20);
        frame.getContentPane().add(labelDataValidade);
        textoDataValidade = new JTextField();
        textoDataValidade.setBounds(xTextField, yPos, 122, 26);
        textoDataValidade.setEnabled(false);
        textoDataValidade.setEditable(false);
        frame.getContentPane().add(textoDataValidade);
        yPos += 36; // Atualiza a posição y

        // COMPONENTE 5
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(xLabel, yPos, 90, 30);
        frame.getContentPane().add(btnBuscar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(150, yPos, 90, 30); // Usando a mesma yPos para alinhar
        btnVoltar.addActionListener(e -> {
            NavegacaoTituloDivida navegacaoTituloDivida = new NavegacaoTituloDivida();
            navegacaoTituloDivida.setVisible(true);
            frame.dispose(); // Fecha a Tela Alterar
        });
        frame.getContentPane().add(btnVoltar);

        // Adicionando a ação do botão Buscar
        btnBuscar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(textoId.getText());
                TituloDivida tituloDivida = mediatorTituloDivida.buscar(id);
                if (tituloDivida == null) {
                    JOptionPane.showMessageDialog(null, "Erro ao buscar ação");
                } else {
                    textoNome.setText(tituloDivida.getNome());
                    textoTaxaJuros.setText(String.valueOf(tituloDivida.getTaxaJuros()));
                    textoDataValidade.setText(tituloDivida.getDataValidade().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    JOptionPane.showMessageDialog(null, "Título encontrado com sucesso!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, insira um ID válido.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar título: " + ex.getMessage());
            }
        });
    }
}
