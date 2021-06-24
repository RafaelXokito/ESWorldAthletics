package Eventos;

import Utils.Data;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class JanelaCriarEvento extends JFrame{
    private JPanel painelPrincipal;
    private JButton buttonImportar;
    private JButton buttonApagar;
    private JTextField textFieldNome;
    private JTextField textFieldDtaInicio;
    private JTextField textFieldPais;
    private JTextField textFieldDtaFim;
    private JTextField textFieldLocal;
    private JButton confirmarButton;
    private JPanel painelContent;
    private JTextField textFieldDtaInicio1;
    private JTextField textFieldDtaInicio2;
    private JTextField textFieldDtaInicio3;
    private JTextField textFieldDtaFim1;
    private JTextField textFieldDtaFim2;
    private JTextField textFieldDtaFim3;

    private boolean textFieldNomeClicked=false;
    private boolean textFieldPaisClicked=false;
    private boolean textFieldLocalClicked=false;
    private boolean textFieldDtaInicio1Clicked=false;
    private boolean textFieldDtaInicio2Clicked=false;
    private boolean textFieldDtaInicio3Clicked=false;
    private boolean textFieldDtaFim1Clicked=false;
    private boolean textFieldDtaFim2Clicked=false;
    private boolean textFieldDtaFim3Clicked=false;

    public JanelaCriarEvento(){
        super("NovoEvento");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(painelPrincipal);
        setVisible(true);
        pack();


        textFieldNome.setToolTipText("Nome");
        textFieldPais.setToolTipText("País");
        textFieldLocal.setToolTipText("Local");
        textFieldDtaInicio1.setToolTipText("Data Inicio");
        textFieldDtaInicio2.setToolTipText("Data Inicio");
        textFieldDtaInicio3.setToolTipText("Data Inicio");
        textFieldDtaFim1.setToolTipText("Data Fim");
        textFieldDtaFim2.setToolTipText("Data Fim");
        textFieldDtaFim3.setToolTipText("Data Fim");


        buttonApagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            apagar();
            }
        });

        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestorEventos eventos = new GestorEventos();
                 String nome = textFieldNome.getText();
                 String pais = textFieldPais.getText();
                 String local = textFieldPais.getText();
                 int dia1 = Integer.parseInt(textFieldDtaInicio1.getText());
                 int mes1 = Integer.parseInt(textFieldDtaInicio2.getText());
                 int ano1 = Integer.parseInt(textFieldDtaInicio3.getText());
                 int dia2 = Integer.parseInt(textFieldDtaFim1.getText());
                 int mes2 = Integer.parseInt(textFieldDtaFim2.getText());
                 int ano2 = Integer.parseInt(textFieldDtaFim3.getText());
                 Data dtaInicio = new Data(ano1,mes1,dia1);
                 Data dtaFim = new Data(ano2,mes2,dia2);
                 Evento evento = new Evento(nome, pais  , local, dtaInicio, dtaFim);

                try {
                    if(verifica(nome,pais,local,dtaInicio,dtaFim)==false){
                        dispose();
                    }
                    if(!isDataValida(dtaInicio, dtaFim)){
                        JOptionPane.showMessageDialog(null,
                                "A data de fim nao pode ser inferior á data de inicio.",
                                "Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null,
                                "Evento Adicionado",
                                "Sucesso",
                                JOptionPane.INFORMATION_MESSAGE);
                        eventos.addEvento(evento);
                        fechar();
                    }
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
            }

            private void fechar() {
                    dispose();
            }

            private boolean verifica(String nome, String pais, String local, Data dtaInicio, Data dtaFim) {
                if (nome.equals("") || pais.equals("") || local.equals("") || dtaInicio.toString().equals("") || dtaFim.toString().equals("")){
                    JOptionPane.showMessageDialog(null,
                            "Tem campos por preencher",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                return true;
            }
        });

        textFieldNome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!textFieldNomeClicked) {
                    textFieldNome.setText("");
                    textFieldNomeClicked = true;
                }
            }
        });

        textFieldDtaInicio1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!textFieldDtaInicio1Clicked) {
                    textFieldDtaInicio1.setText("");
                    textFieldDtaInicio1Clicked = true;
                }
            }
        });

        textFieldDtaInicio2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!textFieldDtaInicio1Clicked) {
                    textFieldDtaInicio1.setText("");
                    textFieldDtaInicio1Clicked = true;
                }
            }
        });

        textFieldDtaInicio3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!textFieldDtaInicio3Clicked) {
                    textFieldDtaInicio3.setText("");
                    textFieldDtaInicio3Clicked = true;
                }
            }
        });

        textFieldDtaFim1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!textFieldDtaFim1Clicked) {
                    textFieldDtaFim1.setText("");
                    textFieldDtaFim1Clicked = true;
                }
            }
        });

        textFieldDtaFim2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!textFieldDtaFim2Clicked) {
                    textFieldDtaFim2.setText("");
                    textFieldDtaFim2Clicked = true;
                }
            }
        });

        textFieldDtaFim3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!textFieldDtaFim3Clicked) {
                    textFieldDtaFim3.setText("");
                    textFieldDtaFim3Clicked = true;
                }
            }
        });

        textFieldPais.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!textFieldPaisClicked) {
                    textFieldPais.setText("");
                    textFieldPaisClicked = true;
                }
            }


        });

        textFieldLocal.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!textFieldLocalClicked) {
                    textFieldLocal.setText("");
                    textFieldLocalClicked = true;
                }
            }
        });
        buttonImportar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "XML", "xml");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(painelContent);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("You chose to open this file: " +
                            chooser.getSelectedFile().getName());
                }



            }
        });
    }

    private void apagar() {
        textFieldNome.setText("");
        textFieldPais.setText("");
        textFieldLocal.setText("");
        textFieldDtaInicio1.setText("");
        textFieldDtaInicio2.setText("");
        textFieldDtaInicio3.setText("");
        textFieldDtaFim1.setText("");
        textFieldDtaFim2.setText("");
        textFieldDtaFim3.setText("");

    }

    private boolean isDataValida(Data dtaInicio, Data dtaFim) throws ParseException {
        SimpleDateFormat sdformat = new SimpleDateFormat("dd-MM-yyyy");
        Date d1 = sdformat.parse(dtaInicio.toString());
        Date d2 = sdformat.parse(dtaFim.toString());
        if(d1.compareTo(d2) < 0) {
           return true;
        } else
            return false;
    }


}
