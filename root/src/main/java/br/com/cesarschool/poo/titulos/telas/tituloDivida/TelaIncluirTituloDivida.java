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

public class TelaIncluirTituloDivida {
private JFrame frame;
private JTextField textoId;
private JTextField textoNome;
private JTextField textoTaxaJuros;
private JTextField textoDataValidade;
private JButton btnIncluir;   
private MediatorTituloDivida mediatorAcao = MediatorTituloDivida.getInstancia();

/*
 * Launch the application.
 * @param args
 */
public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        try {
            TelaIncluirTituloDivida window = new TelaIncluirTituloDivida();
            window.frame.setVisible(true);
        } catch (Exception e) {
            Logger.getLogger(TelaIncluirTituloDivida.class.getName()).log(Level.SEVERE, null, e);//loga a exceção
        }
    });
}

public TelaIncluirTituloDivida() {
    initialize();
}
/*
 * Convenções de espaçamento e indentação
 * 
 * Os elementos alinhados um do lado do outro devem ter um espaçamento de 21 pixels entre si.
 * ou seja se um elemento tem width de 100px e está na coordenada x = 41
 * o proximo deve ser algo com novo_x = x do elemento anterior(41) + width do elemento novo + 21(espaçamento)
 * isso se aplica a labels e caixas de texto principalmente
 * 
 * 
 * Pra altura a convenção vai ser 36 de espaçamento entre os elementos
 * ou seja se um elemento tem height de 20px e está na coordenada y = 40
 * o proximo deve ser algo com novo_y = y do elemento anterior(40) + height do elemento novo + 36(espaçamento)
 * 
 * 
 *  botoes devem estar embaixo de todas as caixas de texto e labels
 * 
 * labels textos sempre vão ter altura 20
 * caixas de texto sempre vão ter altura 26
 * 
 * pra botoes a altura vai ser 30 e largura 90
 */

private void createFrame() {
    frame = new JFrame();
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
    JLabel labelId = new JLabel("Id");
    labelId.setBounds(xLabel, yPos, 121, 20);
    frame.getContentPane().add(labelId);
    textoId = new JTextField();
    textoId.setBounds(xTextField, yPos, 78, 26);
    frame.getContentPane().add(textoId);
    yPos += 36; // Atualiza a posição y

    // COMPONENTE 2
    JLabel labelNome = new JLabel("Nome");
    labelNome.setBounds(xLabel, yPos, 121, 20);
    frame.getContentPane().add(labelNome);
    textoNome = new JTextField();
    textoNome.setBounds(xTextField, yPos, 78, 26);
    frame.getContentPane().add(textoNome);
    yPos += 36;

    // COMPONENTE 3
    JLabel labelValor = new JLabel("Taxa de Juros");
    labelValor.setBounds(xLabel, yPos, 121, 20);
    frame.getContentPane().add(labelValor);
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
    btnIncluir = new JButton("Incluir");
    btnIncluir.setBounds(xLabel, yPos, 90, 30);
    frame.getContentPane().add(btnIncluir);
    
    // Adicionando a ação do botão
    btnIncluir.addActionListener(e -> {
        try {
            int id = Integer.parseInt(textoId.getText());
            String nome = textoNome.getText();
            LocalDate dataValidade = LocalDate.parse(textoDataValidade.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            double valor = Double.parseDouble(textoTaxaJuros.getText());
            
            // Cria o objeto TituloDivida aqui
            TituloDivida acao = new TituloDivida(id, nome, dataValidade, valor);

            // Tenta incluir a ação usando o mediador
            String msg = mediatorAcao.incluir(acao);
            if (msg == null) {
                JOptionPane.showMessageDialog(null, "Incluído com sucesso");
            } else {
                JOptionPane.showMessageDialog(null, msg);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir: " + ex.getMessage());
        }
    });

    
}
}