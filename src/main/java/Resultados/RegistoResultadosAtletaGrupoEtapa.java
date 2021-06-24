package Resultados;

import Atletas.Atleta;
import Atletas.GestorAtletas;
import Etapas.Etapa;
import Grupos.Grupo;
import Utils.Data;
import Utils.Genero;
import Utils.TipoProva;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

public class RegistoResultadosAtletaGrupoEtapa extends JFrame{
    private JPanel painelPrincipal;
    private JButton buttonImportar;
    private JButton buttonApagar;
    private JTextField textFieldValor;
    private JButton confirmarButton;
    private JLabel lblTitle;
    private JPanel painelContent;

    private boolean textFieldValorClicked=false;

    private Grupo grupo;
    private Atleta atleta;
    private String metodo;
    private Etapa etapa;

    public RegistoResultadosAtletaGrupoEtapa(String metodo, Atleta atleta, Grupo grupo, Etapa etapa){
        super("RegistoResultadosAtletaGrupoEtapa "+ metodo);

        this.metodo = metodo;
        this.atleta = atleta;
        this.grupo = grupo;
        this.etapa = etapa;

        textFieldValor.setToolTipText("Nome");

        if (metodo.equals("Registar")) {
            lblTitle.setText(metodo);
            buttonApagar.setText("Cancelar");
        }

        Resultado resultado = grupo.getResultado(atleta);
        if (resultado != null) {
            textFieldValor.setText(String.valueOf(resultado.getValor()));
        }

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(painelPrincipal);
        setVisible(true);
        pack();

        buttonApagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonApagarClicked();
            }
        });
        if (metodo.equals("Registar")) {
            textFieldValor.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (textFieldValorClicked == false) {
                        textFieldValor.setText("");
                        textFieldValorClicked = true;
                    }
                }
            });
        }

        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnConfirmarClickedActionPerformed();
            }
        });
    }

    private void btnConfirmarClickedActionPerformed(){
        try {
            //  Block of code to try

            if (textFieldValor.getText().length() > 0) {

                if (this.metodo.equals("Registar")) {
                    Resultado resultado = grupo.getResultado(atleta);
                    if (resultado == null) {
                        resultado = new Resultado(this.atleta, Double.parseDouble(textFieldValor.getText()), new Data(new Date().getYear(), new Date().getMonth(), new Date().getDay()), grupo);
                        grupo.adicionarResultado(resultado);
                    }else{
                        resultado.setValor(Double.parseDouble(textFieldValor.getText()));
                        resultado.setData(new Data(new Date().getYear(), new Date().getMonth(), new Date().getDay()));
                    }
                    if (grupo.getAtletas().size() == grupo.getResultados().size())
                        grupo.computeVencedores();

                    int i = 0;
                    for (Grupo grupo:etapa.getGrupos()) {
                        if (grupo.getResultados().size() == grupo.getAtletas().size()){
                            i++;
                        }
                    }
                    if (etapa.getGrupos().size() == i){
                        etapa.setConcluido();
                    }
                }
                dispose();
            }
        }
        catch(Exception e) {

            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    private void buttonApagarClicked() {
        textFieldValor.setText("");
        dispose();
    }
}
