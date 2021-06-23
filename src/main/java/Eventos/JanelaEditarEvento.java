package Eventos;

import javax.swing.*;

public class JanelaEditarEvento extends JFrame {
    private JPanel painelPrincipal;
    private JButton buttonRemover;
    private JTextField textFieldNome;
    private JFormattedTextField formattedTextFieldDtaInicio;
    private JTextField textFieldPais;
    private JFormattedTextField formattedTextFieldDtaFim;
    private JTextField textFieldLocal;
    private JButton Confirmar;

    private String nome;
    private String pais;
    private String local;
    private String dtaInicio;
    private String dtaFim;

    public JanelaEditarEvento(String nome, String pais, String local, String dtaInicio, String dtaFim){
        this.nome=nome;
        this.pais=pais;
        this.local=local;
        this.dtaInicio=dtaInicio;
        this.dtaFim=dtaFim;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(painelPrincipal);
        setVisible(true);
        pack();
        carregarDados();


    }


    private void carregarDados() {
        textFieldNome.setText(nome);
        textFieldPais.setText(pais);
        textFieldLocal.setText(local);
        formattedTextFieldDtaInicio.setText(dtaInicio.toString());
        formattedTextFieldDtaFim.setText(dtaFim.toString());
    }

}
