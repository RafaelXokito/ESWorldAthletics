package Atletas;

import Utils.Data;
import Utils.Genero;
import Utils.TipoProva;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NovoAtleta extends JFrame{
    private JPanel painelPrincipal;
    private JButton buttonImportar;
    private JButton buttonApagar;
    private JTextField textFieldNome;
    private JTextField textFieldPais;
    private JTextField textFieldGenero;
    private JFormattedTextField formattedTextFieldDtaNascimento;
    private JFormattedTextField formattedTextFieldContacto;
    private JFormattedTextField formattedTextFieldTipoProva;
    private JButton confirmarButton;
    private JLabel lblTitle;
    private JPanel painelContent;

    private boolean textFieldNomeClicked=false;
    private boolean textFieldPaisClicked=false;
    private boolean textFieldGeneroClicked=false;
    private boolean textFieldDtaNascimentoClicked=false;
    private boolean textFieldContactoClicked=false;
    private boolean textFieldTipoProvaClicked=false;

    private Atleta atleta;
    private String metodo;

    public NovoAtleta(String metodo, Atleta atleta){
        super(metodo);

        this.metodo = metodo;
        this.atleta = atleta;

        textFieldNome.setToolTipText("Nome");
        textFieldPais.setToolTipText("País");
        textFieldGenero.setToolTipText("Genero");
        formattedTextFieldDtaNascimento.setToolTipText("Data de Nascimento");
        formattedTextFieldContacto.setToolTipText("Contacto");
        formattedTextFieldTipoProva.setToolTipText("Tipo de Prova");

        if (metodo.equals("Criar Atleta")) {
            lblTitle.setText(metodo);
            buttonApagar.setText("Cancelar");
        }
        else {
            buttonApagar.setText("Apagar");
            lblTitle.setText(metodo + " - " + atleta.getNome());

            textFieldNome.setText(atleta.getNome());
            textFieldPais.setText(atleta.getPais());
            textFieldGenero.setText(atleta.getGenero().toString());
            formattedTextFieldDtaNascimento.setText(atleta.getDataNascimento().toString());
            formattedTextFieldContacto.setText(atleta.getContacto());
            formattedTextFieldTipoProva.setText(atleta.getTipoProvaPref().toString());
        }

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(painelPrincipal);
        setVisible(true);
        pack();





        buttonApagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            apagar();
            }
        });
        if (metodo.equals("Criar Atleta")) {
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
            formattedTextFieldDtaNascimento.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (textFieldDtaNascimentoClicked == false) {
                        formattedTextFieldDtaNascimento.setText("");
                        textFieldDtaNascimentoClicked = true;
                    }
                }
            });
            formattedTextFieldContacto.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (textFieldContactoClicked == false) {
                        formattedTextFieldContacto.setText("");
                        textFieldContactoClicked = true;
                    }
                }
            });
            formattedTextFieldTipoProva.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (textFieldTipoProvaClicked == false) {
                        formattedTextFieldTipoProva.setText("");
                        textFieldTipoProvaClicked = true;
                    }
                }
            });
        }
        buttonImportar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                importarFicheiro();
            }
        });

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

            if (textFieldNome.getText().length() > 0 && textFieldPais.getText().length() > 0 && textFieldGenero.getText().length() > 0 &&
                formattedTextFieldDtaNascimento.getText().length() > 0 && formattedTextFieldContacto.getText().length() > 0 &&
                formattedTextFieldTipoProva.getText().length() > 0) {

                if (this.metodo.equals("Criar Atleta")) {
                    String[] aux = formattedTextFieldDtaNascimento.getText().split("-");
                    Data dataNascimento = new Data(Integer.valueOf(aux[2]), Integer.valueOf(aux[1]), Integer.valueOf(aux[0]));
                    this.atleta = new Atleta(textFieldNome.getText(), textFieldPais.getText(), Genero.getGenero(textFieldGenero.getText()), dataNascimento, formattedTextFieldContacto.getText(), TipoProva.getTipoProva(formattedTextFieldTipoProva.getText().replace(" ", "_")));
                    GestorAtletas.getInstance().addAtleta(atleta);
                }else{
                    String[] aux = formattedTextFieldDtaNascimento.getText().split("-");
                    Data dataNascimento = new Data(Integer.valueOf(aux[2]), Integer.valueOf(aux[1]), Integer.valueOf(aux[0]));
                    atleta.setGenero(Genero.getGenero(textFieldGenero.getText()));
                    atleta.setTipoProvaPref(TipoProva.getTipoProva(formattedTextFieldTipoProva.getText().replace(" ", "_")));
                    atleta.setNome(textFieldNome.getText());
                    atleta.setPais(textFieldPais.getText());
                    atleta.setDataNascimento(dataNascimento);
                    atleta.setContacto(formattedTextFieldContacto.getText());
                }
                dispose();
            }
        }
        catch(Exception e) {

            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void importarFicheiro() {
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
    private void apagar() {
        textFieldNome.setText("");
        textFieldPais.setText("");
        textFieldGenero.setText("");
        formattedTextFieldDtaNascimento.setText("");
        formattedTextFieldContacto.setText("");
        formattedTextFieldTipoProva.setText("");
        dispose();

    }
}
