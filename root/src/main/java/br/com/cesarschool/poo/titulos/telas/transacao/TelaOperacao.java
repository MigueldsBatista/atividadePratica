package br.com.cesarschool.poo.titulos.telas.transacao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

import br.com.cesarschool.poo.titulos.MainApplicationScreen;
import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.mediators.MediatorOperacao;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioAcao; // Supondo que tenha esse repositório
import br.com.cesarschool.poo.titulos.repositorios.RepositorioEntidadeOperadora; // Supondo que tenha esse repositório

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelaOperacao {
    private JFrame frame;
    private JComboBox<EntidadeOperadora> cmbEntidadeCredora;
    private JComboBox<EntidadeOperadora> cmbEntidadesDebito;
    private JToggleButton toggleOperarAcao;
    private JComboBox<Acao> cmbAcoes;
    private JComboBox<TituloDivida> cmbTitulos;
    private JTextField txtValor;
    private JButton btnOperar, btnLimpar, btnVoltar;
    private static final int ALTURA_COMPONENTE = 30;
    private static final int LARGURA_COMPONENTE = 120;
    private static final int ESPACAMENTO_HORIZONTAL = 36;

    private MediatorOperacao mediatorOperacao = MediatorOperacao.getInstancia();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TelaOperacao window = new TelaOperacao();
                window.frame.setVisible(true);
            } catch (Exception e) {
                Logger.getLogger(TelaOperacao.class.getName()).log(Level.SEVERE, null, e);
            }
        });
    }

    public TelaOperacao() {
        initialize();
    }

    public void setVisible(boolean visibilidade) {
        frame.setVisible(visibilidade);
    }

    private void initialize() {
        createFrame();

        int yPos = 40;
        int xLabel = 41;
        int xComboBox = 183;
        
        // COMPONENTE 1: Entidade Credora
        JLabel labelEntidadeCredora = new JLabel("Entidade Credora");
        labelEntidadeCredora.setBounds(xLabel, yPos, 200, 20);
        frame.getContentPane().add(labelEntidadeCredora);

        cmbEntidadeCredora = new JComboBox<>(carregarEntidadesOperadoras());
        cmbEntidadeCredora.setBounds(xComboBox, yPos, 300, 26);
        frame.getContentPane().add(cmbEntidadeCredora);
        yPos += ESPACAMENTO_HORIZONTAL;

        // COMPONENTE 2: Entidades de Débito
        JLabel labelEntidadesDebito = new JLabel("Entidades Debitada");
        labelEntidadesDebito.setBounds(xLabel, yPos, 200, 20);
        frame.getContentPane().add(labelEntidadesDebito);

        cmbEntidadesDebito = new JComboBox<>(carregarEntidadesOperadoras());
        cmbEntidadesDebito.setBounds(xComboBox, yPos, 300, 26);
        frame.getContentPane().add(cmbEntidadesDebito);
        yPos += ESPACAMENTO_HORIZONTAL;

        toggleOperarAcao = new JToggleButton("Usar Ação");
        toggleOperarAcao.setBounds(xLabel, yPos, LARGURA_COMPONENTE, ALTURA_COMPONENTE);
        frame.getContentPane().add(toggleOperarAcao);


        // COMPONENTE 3: Toggle "Operar com Ação"


        JLabel labelAcao = new JLabel("Saldo: (Ação - Título) da entidade");
        labelAcao.setBounds(xLabel+150, yPos, 250, ALTURA_COMPONENTE);
        frame.getContentPane().add(labelAcao);
        yPos += ESPACAMENTO_HORIZONTAL;

        // COMPONENTE 4: Dropdown de Ações
        JLabel labelAcoes = new JLabel("Ação");
        labelAcoes.setBounds(xLabel, yPos, 200, 20);
        frame.getContentPane().add(labelAcoes);

        cmbAcoes = new JComboBox<>(carregarAcoes());
        cmbAcoes.setBounds(xComboBox, yPos, 300, 26);
        cmbAcoes.setVisible(false); // Inicialmente invisível
        frame.getContentPane().add(cmbAcoes);
        yPos += ESPACAMENTO_HORIZONTAL;

        toggleOperarAcao.addActionListener(e -> {
            boolean isSelected = toggleOperarAcao.isSelected();
            cmbAcoes.setVisible(isSelected); // Mostrar ou ocultar a lista de Ações
            cmbTitulos.setVisible(!isSelected); // Mostrar ou ocultar a lista de Títulos
            frame.revalidate();
            frame.repaint();
        });

        // COMPONENTE 5: Dropdown de Títulos
        JLabel labelTitulos = new JLabel("Título Dívida");
        labelTitulos.setBounds(xLabel, yPos, 200, 20);
        frame.getContentPane().add(labelTitulos);

        cmbTitulos = new JComboBox<>(carregarTitulosDivida());
        cmbTitulos.setBounds(xComboBox, yPos, 300, 26);
        cmbTitulos.setVisible(true); // Inicialmente visível
        frame.getContentPane().add(cmbTitulos);
        yPos += ESPACAMENTO_HORIZONTAL;

        // COMPONENTE 6: Campo para valor da operação
        JLabel labelValor = new JLabel("Valor da Operação");
        labelValor.setBounds(xLabel, yPos, 200, 20);
        frame.getContentPane().add(labelValor);

        txtValor = new JTextField();
        txtValor.setBounds(xComboBox, yPos, 300, 26);
        frame.getContentPane().add(txtValor);
        yPos += ESPACAMENTO_HORIZONTAL;

        // COMPONENTE 7: Botão "Operar"
        btnOperar = new JButton("Operar");
        btnOperar.setBounds(xLabel, yPos, LARGURA_COMPONENTE, ALTURA_COMPONENTE);
        frame.getContentPane().add(btnOperar);

        // COMPONENTE 8: Botão "Voltar"
        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(xLabel + 140, yPos, LARGURA_COMPONENTE, ALTURA_COMPONENTE);
        btnVoltar.addActionListener(e -> {
            MainApplicationScreen telaInicio = new MainApplicationScreen();
            telaInicio.setVisible(true);
            frame.dispose(); // Fecha a tela atual
        });
        frame.getContentPane().add(btnVoltar);

        // COMPONENTE 9: Botão "Limpar"
        btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(xLabel + 280, yPos, LARGURA_COMPONENTE, ALTURA_COMPONENTE);
        btnLimpar.addActionListener(e -> {
            cmbEntidadeCredora.setSelectedIndex(-1);
            cmbEntidadesDebito.setSelectedIndex(-1);
            toggleOperarAcao.setSelected(false);
            cmbAcoes.setVisible(false);
            cmbTitulos.setVisible(true);
            cmbAcoes.setSelectedIndex(-1);
            cmbTitulos.setSelectedIndex(-1);
            txtValor.setText("");
        });
        frame.getContentPane().add(btnLimpar);

        // Adicionando a ação do botão "Operar"
        btnOperar.addActionListener(e -> {
            try {
                double valor = Double.parseDouble(txtValor.getText());

                // Obtendo a entidade selecionada
                EntidadeOperadora entidadeCredora = (EntidadeOperadora) cmbEntidadeCredora.getSelectedItem();
                EntidadeOperadora entidadeDebito = (EntidadeOperadora) cmbEntidadesDebito.getSelectedItem();

                // Exemplo de operação
                boolean isAcao = toggleOperarAcao.isSelected();
                int idAcaoOuTitulo = isAcao ? ((Acao) cmbAcoes.getSelectedItem()).getIdentificador() : ((TituloDivida) cmbTitulos.getSelectedItem()).getIdentificador();

                String msg = mediatorOperacao.realizarOperacao(isAcao, entidadeCredora.getIdentificador(), entidadeDebito.getIdentificador(), idAcaoOuTitulo, valor);
                if (msg == null) {
                    JOptionPane.showMessageDialog(null, "Operação realizada com sucesso: " + isAcao +
                            " - Valor: " + valor +
                            " - Data-Hora: " + LocalDateTime.now() +
                            " - Entidade Credora: " + entidadeCredora.getNome() +
                            " - Entidade Débito: " + entidadeDebito.getNome());
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao operar: " + msg);
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao operar: " + ex.getMessage());
            }
        });
    }

    private void createFrame() {
        frame = new JFrame("Operação");
        frame.setSize(600, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
    }

    // Método para carregar entidades operadoras
    private EntidadeOperadora[] carregarEntidadesOperadoras() {
        RepositorioEntidadeOperadora repositorioEntidadeOperadora = new RepositorioEntidadeOperadora();
        List<EntidadeOperadora> entidades = repositorioEntidadeOperadora.listar(); 
        return entidades.toArray(new EntidadeOperadora[0]); // Retorna um array de EntidadeOperadora
    }

    // Método para carregar ações
    private Acao[] carregarAcoes() {
        RepositorioAcao repositorioAcao = new RepositorioAcao();
        List<Acao> acoes = repositorioAcao.listar(); 
        return acoes.toArray(new Acao[0]); // Retorna um array de Ação
    }

    // Método para carregar títulos de dívida
    private TituloDivida[] carregarTitulosDivida() {
        RepositorioTituloDivida repositorioTituloDivida = new RepositorioTituloDivida();
        List<TituloDivida> titulos = repositorioTituloDivida.listar(); 
        return titulos.toArray(new TituloDivida[0]); // Retorna um array de TituloDivida
    }
}
