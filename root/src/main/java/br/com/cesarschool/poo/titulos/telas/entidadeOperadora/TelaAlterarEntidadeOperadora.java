package br.com.cesarschool.poo.titulos.telas.entidadeOperadora;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.util.logging.Logger;
import java.util.logging.Level;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.mediators.MediatorEntidadeOperadora;
/*
 * atributos de entidade operadora
 *     private int identificador;
    public String nome;
    private double autorizadoAcao;
    private double saldoAcao=0.0;
    private double saldoTituloDivida=0.0;
 */
public class TelaAlterarEntidadeOperadora {
private JFrame frame;
private JTextField textoId;
private JTextField textoNome;
private JTextField textoAutorizacoAcao;
private JTextField textoSaldoAcao;
private JTextField textoSaldoTituloDivida;
private JButton btnIncluir;   
private MediatorEntidadeOperadora mediatorEntidadeOperadora = MediatorEntidadeOperadora.getInstancia();

/*
 * Launch the application.
 * @param args
 */
public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        try {
            TelaAlterarEntidadeOperadora window = new TelaAlterarEntidadeOperadora();
            window.frame.setVisible(true);
        } catch (Exception e) {
            Logger.getLogger(TelaAlterarEntidadeOperadora.class.getName()).log(Level.SEVERE, null, e);//loga a exceção
        }
    });
}

public TelaAlterarEntidadeOperadora() {
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

    // COMPONENTE 2
    JLabel labelAutorizadoAcao = new JLabel("Nova autorizado ação");
    labelAutorizadoAcao.setBounds(xLabel, yPos, 121, 20);
    frame.getContentPane().add(labelAutorizadoAcao);

    // Criação do JComboBox com as opções true e false
    String[] options = {"true", "false"};
    JComboBox<String> comboAutorizadoAcao = new JComboBox<>(options);
    comboAutorizadoAcao.setBounds(xTextField, yPos, 78, 26);
    frame.getContentPane().add(comboAutorizadoAcao);

    // Atualiza a posição vertical para o próximo componente
    yPos += 36;


    // COMPONENTE 3
    JLabel labelSaldoAcao = new JLabel("Novo Saldo ação");
    labelSaldoAcao.setBounds(xLabel, yPos, 121, 20);
    frame.getContentPane().add(labelSaldoAcao);
    textoSaldoAcao = new JTextField();
    textoSaldoAcao.setBounds(xTextField, yPos, 78, 26);
    frame.getContentPane().add(textoSaldoAcao);
    yPos += 36;

    // COMPONENTE 4
    JLabel labelSaldoTituloDivida = new JLabel("Novo saldo título dívida");
    labelSaldoTituloDivida.setBounds(xLabel, yPos, 121, 20);
    frame.getContentPane().add(labelSaldoTituloDivida);
    textoSaldoTituloDivida = new JTextField();
    textoSaldoTituloDivida.setBounds(xTextField, yPos, 78, 26);
    frame.getContentPane().add(textoSaldoTituloDivida);
    yPos += 36;

    // COMPONENTE 5
    btnIncluir = new JButton("Alterar");
    btnIncluir.setBounds(xLabel, yPos, 90, 30);
    frame.getContentPane().add(btnIncluir);
    
    // Adicionando a ação do botão
    btnIncluir.addActionListener(e -> {
        try {
            int id = Integer.parseInt(textoId.getText());
            String nome = textoNome.getText();
            boolean autorizadoAcao = Boolean.parseBoolean(textoAutorizacoAcao.getText());
            double saldoTituloDivida = Double.parseDouble(textoSaldoTituloDivida.getText());
            double saldoAcao = Double.parseDouble(textoSaldoAcao.getText());
            
            // Cria o objeto EntidadeOperadora aqui
            EntidadeOperadora entidadeOperadora = new EntidadeOperadora(id, nome, autorizadoAcao, saldoTituloDivida, saldoAcao);

            // Tenta incluir a ação usando o mediador
            String msg = mediatorEntidadeOperadora.alterar(entidadeOperadora);
            if (msg == null) {
                JOptionPane.showMessageDialog(null, "Ação alterada com sucesso");
            } else {
                JOptionPane.showMessageDialog(null, msg);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar: " + ex.getMessage());
        }
    });

    
}
}