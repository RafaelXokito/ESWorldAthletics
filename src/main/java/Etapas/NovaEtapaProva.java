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
    private JTextField textFieldHora;
    private JTextField textFieldDtaInicio;
    private JTextField textFieldRonda;
    private JButton confirmarButton;
    private JPanel painelContent;
    private JFormattedTextField textFieldMinimos;
    private JLabel lblTitle;
    private JButton btnSelecionarAtletas;
    private JFormattedTextField textFieldDiaCompeticao;
    private JFormattedTextField textFieldGenero;

    private boolean textFieldHoraClicked =false;
    private boolean textFieldRondaClicked =false;
    private boolean formatedTextFieldDtaInicioClicked=false;
    private boolean formatedTextFieldMinimosClicked =false;
    private boolean textFieldDiaCompeticaoClicked = false;
    private boolean textFieldGeneroClicked = false;


    private Prova prova;
    private Etapa etapa;

    public NovaEtapaProva(String metodo, Etapa etapa){
        super("NovaEtapaProva "+metodo);
        this.etapa = etapa;
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

            textFieldDtaInicio.setText(etapa.getDataInicio().toString());
            textFieldHora.setText(etapa.getHora());
            textFieldRonda.setText(etapa.getRonda());
            textFieldGenero.setText(etapa.getGenero().toString());
            textFieldDiaCompeticao.setText(etapa.getDiaCompeticao());
            textFieldMinimos.setText(etapa.getMinimos());
        }


        textFieldDtaInicio.setToolTipText("Data de inicio");
        textFieldHora.setToolTipText("Hora");
        textFieldRonda.setToolTipText("Ronda");
        textFieldGenero.setToolTipText("Género");
        textFieldDiaCompeticao.setToolTipText("Dia da Competição");
        textFieldMinimos.setToolTipText("Mínimos");

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

        textFieldHora.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (textFieldHoraClicked == false) {
                    textFieldHora.setText("");
                    textFieldHoraClicked = true;
                }
            }
        });
        textFieldGenero.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (textFieldGeneroClicked == false) {
                    textFieldGenero.setText("");
                    textFieldGeneroClicked = true;
                }
            }
        });
        textFieldDiaCompeticao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (textFieldDiaCompeticaoClicked == false) {
                    textFieldDiaCompeticao.setText("");
                    textFieldDiaCompeticaoClicked = true;
                }
            }
        });
        textFieldDtaInicio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (formatedTextFieldDtaInicioClicked == false) {
                    textFieldDtaInicio.setText("");
                    formatedTextFieldDtaInicioClicked = true;
                }
            }
        });

        textFieldRonda.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (textFieldRondaClicked == false) {
                    textFieldRonda.setText("");
                    textFieldRondaClicked = true;
                }
            }


        });
        textFieldMinimos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (formatedTextFieldMinimosClicked == false) {
                    textFieldMinimos.setText("");
                    formatedTextFieldMinimosClicked = true;
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

    public NovaEtapaProva(String metodo,Etapa etapa, Prova prova){
        this(metodo,etapa);
        this.prova = prova;
    }

    private void btnSelecionarAtletasClickedActionPerformed(){
        SelecionarAtletasPage selecionarAtletasPage = new SelecionarAtletasPage(this.prova);
        selecionarAtletasPage.pack();
        selecionarAtletasPage.setVisible(true);
    }

    private void apagar() {
        textFieldHora.setText("");
        textFieldRonda.setText("");
        textFieldMinimos.setText("");
    }
}
