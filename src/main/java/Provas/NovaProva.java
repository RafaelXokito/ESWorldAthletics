package Provas;

import Atletas.Atleta;
import Atletas.GestorAtletas;
import Atletas.SelecionarAtletasPage;
import Eventos.Evento;
import Utils.Data;
import Utils.Genero;
import Utils.TipoProva;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NovaProva extends JFrame{
    private JPanel painelPrincipal;
    private JButton buttonImportar;
    private JButton buttonApagar;
    private JTextField textFieldTipoProva;
    private JTextField textFieldCriterio;
    private JButton confirmarButton;
    private JPanel painelContent;
    private JFormattedTextField formattedTextFieldDtaInicio;
    private JFormattedTextField formattedTextFieldDtaFim;
    private JLabel lblTitle;
    private JButton btnSelecionarAtletas;

    private boolean textFieldTipoProvaClicked=false;
    private boolean textFieldCriterioClicked=false;
    private boolean formatedTextFieldDtaInicioClicked=false;
    private boolean formatedTextFieldDtaFimClicked=false;

    private Prova prova;
    private String metodo;
    private Evento evento;
    public NovaProva(String metodo, Prova prova){
        super(metodo);
        this.prova = prova;
        this.metodo = metodo;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(painelPrincipal);
        setVisible(true);
        pack();

        lblTitle.setText(metodo);

        if (metodo.equals("Criar Prova")) {
            buttonApagar.setText("Cancelar");
        }
        else {
            buttonApagar.setText("Apagar");

            textFieldTipoProva.setText(prova.getTipoProva().toString());
            textFieldCriterio.setText(prova.getCriterioProva());
            formattedTextFieldDtaInicio.setText(prova.getDataInicio().toString());
            formattedTextFieldDtaFim.setText(prova.getDataFimPrevisto().toString());
        }

        textFieldTipoProva.setToolTipText("Tipo de Prova");
        textFieldCriterio.setToolTipText("Criterio");
        formattedTextFieldDtaInicio.setToolTipText("Data de inicio");
        formattedTextFieldDtaFim.setToolTipText("Data de fim");

        buttonApagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            apagar();
            }
        });

        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarProva();
            }
        });

        btnSelecionarAtletas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSelecionarAtletasClickedActionPerformed();
            }
        });

        textFieldTipoProva.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (textFieldTipoProvaClicked == false) {
                    textFieldTipoProva.setText("");
                    textFieldTipoProvaClicked = true;
                }
            }
        });
        formattedTextFieldDtaInicio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (formatedTextFieldDtaInicioClicked == false) {
                    formattedTextFieldDtaInicio.setText("");
                    formatedTextFieldDtaInicioClicked = true;
                }
            }
        });

        textFieldCriterio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (textFieldCriterioClicked == false) {
                    textFieldCriterio.setText("");
                    textFieldCriterioClicked = true;
                }
            }


        });
        formattedTextFieldDtaFim.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (formatedTextFieldDtaFimClicked == false) {
                    formattedTextFieldDtaFim.setText("");
                    formatedTextFieldDtaFimClicked = true;
                }
            }
        });
        buttonImportar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                importarDeFicheiro();
            }
        });
    }

    public NovaProva(String metodo, Prova prova, Evento evento){
        this(metodo, prova);
        this.evento = evento;
    }

    private void criarProva() {
        try {
            //  Block of code to try
            if (textFieldTipoProva.getText().length() > 0 && textFieldCriterio.getText().length() > 0
                    && formattedTextFieldDtaInicio.getText().length() > 0 && formattedTextFieldDtaFim.getText().length() > 0 ) {

                if (this.metodo.equals("Criar Prova")) {
                    String[] aux = formattedTextFieldDtaInicio.getText().split("-");
                    Data dataInicio = new Data(Integer.valueOf(aux[2]), Integer.valueOf(aux[1]), Integer.valueOf(aux[0]));
                    aux = formattedTextFieldDtaFim.getText().split("-");
                    Data dataFim = new Data(Integer.valueOf(aux[2]), Integer.valueOf(aux[1]), Integer.valueOf(aux[0]));

                    this.prova = new Prova(TipoProva.getTipoProva(textFieldTipoProva.getText()), textFieldCriterio.getText(), dataInicio, dataFim,this.evento,textFieldCriterio.getText().charAt(0));
                    evento.adicionarProva(prova);
                }else{

                    String[] aux = formattedTextFieldDtaInicio.getText().split("-");
                    Data dataInicio = new Data(Integer.valueOf(aux[2]), Integer.valueOf(aux[1]), Integer.valueOf(aux[0]));
                    aux = formattedTextFieldDtaFim.getText().split("-");
                    Data dataFim = new Data(Integer.valueOf(aux[2]), Integer.valueOf(aux[1]), Integer.valueOf(aux[0]));

                    prova.setTipoProva(TipoProva.getTipoProva(textFieldTipoProva.getText()));
                    prova.setCriterioProva(textFieldCriterio.getText());
                    prova.setDataInicio(dataInicio);
                    prova.setDataFimPrevisto(dataFim);
                }
                dispose();
            }
        }
        catch(Exception e) {

            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    void importarDeFicheiro() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(painelContent);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
        }
    }

    private void btnSelecionarAtletasClickedActionPerformed(){
        SelecionarAtletasPage selecionarAtletasPage = new SelecionarAtletasPage(this.prova);
        selecionarAtletasPage.pack();
        selecionarAtletasPage.setVisible(true);
    }

    private void apagar() {
        textFieldTipoProva.setText("");
        textFieldCriterio.setText("");
        formattedTextFieldDtaInicio.setText("");
        formattedTextFieldDtaFim.setText("");
    }
}
