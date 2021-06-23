package Eventos;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

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
    private JFormattedTextField formattedTextFieldDtaInicio;
    private JFormattedTextField formattedTextFieldDtaFim;

    private boolean textFieldNomeClicked=false;
    private boolean textFieldPaisClicked=false;
    private boolean textFieldLocalClicked=false;
    private boolean textFieldDtaInicioClicked=false;
    private boolean textFieldDtaFimClicked=false;

    public JanelaCriarEvento(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(painelPrincipal);
        setVisible(true);
        pack();


        textFieldNome.setToolTipText("Nome");
        textFieldPais.setToolTipText("País");
        textFieldLocal.setToolTipText("Local");
        textFieldDtaInicio.setToolTipText("Utils.Data de inicio");
        textFieldDtaFim.setToolTipText("Utils.Data de fim");



        buttonApagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            apagar();
            }
        });

        textFieldNome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (textFieldNomeClicked == false) {
                    textFieldNome.setText("");
                    textFieldNomeClicked = true;
                }
            }
        });
        textFieldDtaInicio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (textFieldDtaInicioClicked == false) {
                    textFieldDtaInicio.setText("");
                    textFieldDtaInicioClicked = true;
                }
            }
        });

        textFieldPais.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (textFieldPaisClicked == false) {
                    textFieldPais.setText("");
                    textFieldPaisClicked = true;
                }
            }


        });
        textFieldDtaFim.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (textFieldDtaFimClicked == false) {
                    textFieldDtaFim.setText("");
                    textFieldDtaFimClicked = true;
                }
            }
        });
        textFieldLocal.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (textFieldLocalClicked == false) {
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

    private void apagar() {
        textFieldNome.setText("");
        textFieldPais.setText("");
        textFieldLocal.setText("");
        textFieldDtaInicio.setText("");
        textFieldDtaFim.setText("");
    }
}
