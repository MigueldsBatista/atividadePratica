package br.com.cesarschool.poo.titulos.telas.tituloDivida;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.util.logging.Logger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;

import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.mediators.MediatorTituloDivida;

public class TelaAlterarTituloDivida {
    private JFrame frame;
    private JTextField textoId;
    private JTextField textoNome;
    private JTextField textoTaxaJuros;
    private JTextField textoDataValidade;
    private JButton btnIncluir;   
    private MediatorTituloDivida mediatorTituloDivida = MediatorTituloDivida.getInstancia();

    /*
     * Launch the application.
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TelaAlterarTituloDivida window = new TelaAlterarTituloDivida();
                window.frame.setVisible(true);
            } catch (Exception e) {
                Logger.getLogger(TelaAlterarTituloDivida.class.getName()).log(Level.SEVERE, null, e); // Loga a exceção
            }
        });
    }

    public TelaAlterarTituloDivida() {
        initialize();
    }

    public void setVisible(boolean visibilidade) {
        frame.setVisible(visibilidade);
    }

    /*
     * Convenções de espaçamento e indentação
     * 
     * Os elementos alinhados um do lado do outro devem ter um espaçamento de 21 pixels entre si.
     * Ou seja, se um elemento tem width de 100px e está na coordenada x = 41,
     * o próximo deve ser algo com novo_x = x do elemento anterior(41) + width do elemento novo + 21(espaçamento).
     * Isso se aplica a labels e caixas de texto principalmente.
     * 
     * Pra altura a convenção vai ser 36 de espaçamento entre os elementos.
     * Ou seja, se um elemento tem height de 20px e está na coordenada y = 40,
     * o próximo deve ser algo com novo_y = y do elemento anterior(40) + height do elemento novo + 36(espaçamento).
     * 
     * Botoes devem estar embaixo de todas as caixas de texto e labels.
     * 
     * Labels textos sempre vão ter altura 20.
     * Caixas de texto sempre vão ter altura 26.
     * 
     * Pra botoes a altura vai ser 30 e largura 90.
     */

    private void createFrame() {
        frame = new JFrame("Alterar Título de Dívida");
        frame.setBounds(100, 100, 556, 370);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
    }

    private void initialize() {
        createFrame();
        // Método para limpar os campos de texto

        int yPos = 40; // Posição inicial y
        int xLabel = 41; // Posição x para Labels
        int xTextField = 183; // Posição x para TextFields

        // COMPONENTE 1
        JLabel labelId = new JLabel("ID atual");
        labelId.setBounds(xLabel, yPos, 121, 20);
        frame.getContentPane().add(labelId);
        textoId = new JTextField();
        textoId.setBounds(xTextField, yPos, 78, 26);
        frame.getContentPane().add(textoId);
        yPos += 36; // Atualiza a posição y

        // COMPONENTE 2
        JLabel labelNome = new JLabel("Novo Nome");
        labelNome.setBounds(xLabel, yPos, 121, 20);
        frame.getContentPane().add(labelNome);
        textoNome = new JTextField();
        textoNome.setBounds(xTextField, yPos, 78, 26);
        frame.getContentPane().add(textoNome);
        yPos += 36;

        // COMPONENTE 3
        JLabel labelTaxaJuros = new JLabel("Nova taxa de juros");
        labelTaxaJuros.setBounds(xLabel, yPos, 121, 20);
        frame.getContentPane().add(labelTaxaJuros);
        textoTaxaJuros = new JTextField();
        textoTaxaJuros.setBounds(xTextField, yPos, 78, 26);
        frame.getContentPane().add(textoTaxaJuros);
        yPos += 36;

        // COMPONENTE 4
        JLabel labelDataValidade = new JLabel("Data de Validade");
        labelDataValidade.setBounds(xLabel, yPos, 121, 20);
        frame.getContentPane().add(labelDataValidade);
        textoDataValidade = new JTextField();
        textoDataValidade.setBounds(xTextField, yPos, 78, 26);
        frame.getContentPane().add(textoDataValidade);
        yPos += 36;

        // COMPONENTE 5
        btnIncluir = new JButton("Alterar");
        btnIncluir.setBounds(xLabel, yPos, 90, 30);
        frame.getContentPane().add(btnIncluir);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(xLabel+100, yPos, 90, 30); // Ajusta a posição de 'Voltar'
        btnVoltar.addActionListener(e -> {
            NavegacaoTituloDivida navegacaoTituloDivida = new NavegacaoTituloDivida();
            navegacaoTituloDivida.setVisible(true);
            frame.dispose(); // Fecha a Tela Alterar
        });
        frame.getContentPane().add(btnVoltar);

        frame.getContentPane().add(btnVoltar);
        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(xLabel + 200, yPos, 100, 30); // Ajusta a posição e tamanho do botão 'Limpar'
        btnLimpar.addActionListener(e -> {
            textoId.setText("");
            textoNome.setText("");
            textoTaxaJuros.setText("");
            textoDataValidade.setText("");
        });
        frame.getContentPane().add(btnLimpar);
        // Adicionando a ação do botão
        btnIncluir.addActionListener(e -> {
            try {
                int id = Integer.parseInt(textoId.getText());
                String nome = textoNome.getText();
                LocalDate dataValidade = LocalDate.parse(textoDataValidade.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                double taxaJuros = Double.parseDouble(textoTaxaJuros.getText());

                // Cria o objeto TituloDivida aqui
                TituloDivida tituloDivida = new TituloDivida(id, nome, dataValidade, taxaJuros);

                // Tenta incluir a ação usando o mediador
                String msg = mediatorTituloDivida.alterar(tituloDivida);
                if (msg == null) {
                    JOptionPane.showMessageDialog(null, "Título alterado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, msg);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao alterar: " + ex.getMessage());
            }
        });
    }
}
