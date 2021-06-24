package Atletas;

import Eventos.JanelaCriarEvento;
import Provas.ProvasAtletaPage;
import Utils.Data;
import Utils.Genero;
import Utils.TipoProva;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class AtletasPage extends JFrame{
    private JPanel painelPrincipal;
    private JButton buttonVoltar;
    private JTable tableAtletas;
    private JTextField pesquisaTextField;
    private JButton importarAtletasButton;
    private JButton criarAtletaButton;
    private JScrollPane sp;
    private JPanel panelBottom;
    private JButton buttonConfirmar;
    private JButton buttonCancelar;

    public AtletasPage(){
        super("AtletasPage");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(painelPrincipal);
        setVisible(true);

        pack();

        buttonVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        pesquisaTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                pesquisaTextField.setText("");
            }
        });

        createTable();


        criarAtletaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var novoAtleta = new NovoAtleta("Criar Atleta", null);
                novoAtleta.setVisible(true);
            }
        });

        importarAtletasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    importarFicheiro();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });
    }

    private void importarFicheiro() throws FileNotFoundException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Text Files", "txt");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(painelPrincipal);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
            Scanner myReader = new Scanner(chooser.getSelectedFile());
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataSplited = data.split("\\t");
                GestorAtletas.getInstance().addAtleta(new Atleta(dataSplited[0],dataSplited[1], Genero.getGenero(dataSplited[2]),
                        new Data(Integer.valueOf(dataSplited[3].split("-")[2]), Integer.valueOf(dataSplited[3].split("-")[1]), Integer.valueOf(dataSplited[3].split("-")[0])),
                        dataSplited[4], TipoProva.getTipoProva(dataSplited[5])));
                System.out.println(data);
            }
            myReader.close();
        }
    }

    private void createTable(){

        Object[][] data = new Object[0][8];

        LinkedList<Atleta> atletas = GestorAtletas.getInstance().getAtletas();
        int i = 0;
        for (Atleta atleta : atletas) {
            Object[][] dataAux = new Object[data.length+1][8];
            System.arraycopy(data, 0, dataAux, 0, data.length);
            dataAux[i++] = new Object[]{atleta.getNome(),atleta.getPais(),atleta.getGenero(),atleta.getDataNascimento(), atleta.getContacto(), atleta.getTipoProvaPref(), "Provas","Alterar"};
            data = dataAux.clone();
        }

        tableAtletas.setModel(new DefaultTableModel(
                data,
                new Object[]{"Nome", "País", "Género", "Data Nascimento", "Contacto", "Tipo Prova Preferida", "", ""}
        ));
        //SET CUSTOM RENDERER TO TEAMS COLUMN
        tableAtletas.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
        tableAtletas.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());

        //SET CUSTOM EDITOR TO TEAMS COLUMN
        tableAtletas.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(new JTextField()));
        tableAtletas.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor(new JTextField()));


    }

}

class ButtonRenderer extends JButton implements TableCellRenderer
{

    //CONSTRUCTOR
    public ButtonRenderer() {
        //SET BUTTON PROPERTIES
        setOpaque(true);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object obj,
                                                   boolean selected, boolean focused, int row, int col) {

        //SET PASSED OBJECT AS BUTTON TEXT
        setText((obj==null) ? "":obj.toString());

        return this;
    }

}

class ButtonEditor extends DefaultCellEditor
{
    protected JButton btn;
    private String lbl;
    private Boolean clicked;
    private int row;

    public ButtonEditor(JTextField txt) {
        super(txt);

        btn=new JButton();
        btn.setOpaque(true);

        //WHEN BUTTON IS CLICKED
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                fireEditingStopped();
            }
        });
    }

    //OVERRIDE A COUPLE OF METHODS
    @Override
    public Component getTableCellEditorComponent(JTable table, Object obj,
                                                 boolean selected, int row, int col) {

        //SET TEXT TO BUTTON,SET CLICKED TO TRUE,THEN RETURN THE BTN OBJECT
        lbl=(obj==null) ? "":obj.toString();
        btn.setText(lbl);
        this.row = row;
        clicked=true;
        return btn;
    }

    //IF BUTTON CELL VALUE CHNAGES,IF CLICKED THAT IS
    @Override
    public Object getCellEditorValue() {
        if(clicked)
        {
            //SHOW US SOME MESSAGE
            //JOptionPane.showMessageDialog(btn, row+" Clicked");
            if (lbl.equals("Provas")) {
                ProvasAtletaPage provasAtletaPage = new ProvasAtletaPage(GestorAtletas.getInstance().getAtletas().get(row));
                provasAtletaPage.pack();
                provasAtletaPage.setVisible(true);
            }
            if (lbl.equals("Alterar")) {
                NovoAtleta novoAtleta = new NovoAtleta("Alterar Atleta",GestorAtletas.getInstance().getAtletas().get(row));
                novoAtleta.pack();
                novoAtleta.setVisible(true);
            }
        }
        //SET IT TO FALSE NOW THAT ITS CLICKED
        clicked=false;
        return new String(lbl);
    }

    @Override
    public boolean stopCellEditing() {

        //SET CLICKED TO FALSE FIRST
        clicked=false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        // TODO Auto-generated method stub
        super.fireEditingStopped();
    }
}