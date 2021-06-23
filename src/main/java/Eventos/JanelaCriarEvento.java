package Eventos;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JanelaCriarEvento extends JFrame{
    private JPanel painelPrincipal;
    private JButton buttonImportar;
    private JButton buttonApagar;
    private JTextField textFieldNome;
    private JTextField textFieldDtaInicio;
    private JTextField textFieldPais;
    private JTextField textFieldDtaFim;
    private JTextField textFieldGenero;
    private JButton confirmarButton;
    private JPanel painelContent;
    private JFormattedTextField formattedTextFieldDtaNascimento;
    private JFormattedTextField formattedTextFieldContacto;
    private JFormattedTextField formattedTextFieldTipoProva;

    private boolean textFieldNomeClicked=false;
    private boolean textFieldPaisClicked=false;
    private boolean textFieldLocalClicked=false;
    private boolean formatedTextFieldDtaInicioClicked=false;
    private boolean formatedTextFieldDtaFimClicked=false;

    public JanelaCriarEvento(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(painelPrincipal);
        setVisible(true);
        pack();


        textFieldNome.setToolTipText("Nome");
        textFieldPais.setToolTipText("País");
<<<<<<< Updated upstream
        textFieldGenero.setToolTipText("Local");
        textFieldDtaInicio.setToolTipText("Utils.Data de inicio");
        textFieldDtaFim.setToolTipText("Utils.Data de fim");
=======
        textFieldLocal.setToolTipText("Local");
        formattedTextFieldDtaInicio.setToolTipText("Data de inicio");
        formattedTextFieldDtaFim.setToolTipText("Data de fim");
>>>>>>> Stashed changes



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
        textFieldGenero.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (textFieldLocalClicked == false) {
                    textFieldGenero.setText("");
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
        textFieldGenero.setText("");
        textFieldDtaInicio.setText("");
        textFieldDtaFim.setText("");
=======
        textFieldLocal.setText("");
        formattedTextFieldDtaInicio.setText("");
        formattedTextFieldDtaFim.setText("");
>>>>>>> Stashed changes
    }
}
