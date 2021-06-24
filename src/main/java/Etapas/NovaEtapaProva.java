package Etapas;

import Atletas.SelecionarAtletasPage;
import Provas.Prova;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NovaEtapaProva extends JFrame{
    private JPanel painelPrincipal;
    private JButton buttonImportar;
    private JButton buttonApagar;
    private JTextField textFieldTipoProva;
    private JTextField textFieldDtaInicio;
    private JTextField textFieldCriterio;
    private JTextField textFieldDtaFim;
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
    public NovaEtapaProva(String metodo, Prova prova){
        super("NovaEtapaProva "+metodo);
        this.prova = prova;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(painelPrincipal);
        setVisible(true);
        pack();

        lblTitle.setText(metodo);

        if (metodo.equals("Criar Etapa")) {
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
        });
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
