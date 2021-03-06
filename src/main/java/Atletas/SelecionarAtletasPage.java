package Atletas;

import Grupos.Grupo;
import Provas.ObjectWithAtletas;
import Provas.Prova;
import Provas.ProvasAtletaPage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class SelecionarAtletasPage extends JFrame{
    private JPanel painelPrincipal;
    private JButton buttonVoltar;
    private JTable tableAtletas;
    private JTextField pesquisaTextField;
    private JScrollPane sp;
    private JLabel lblTitle;
    private JButton btnCancelar;
    private JPanel panelBottom;
    private JButton btnConfirmar;
    private ObjectWithAtletas tWithAtletas;
    private LinkedList<Atleta> atletas;

    public SelecionarAtletasPage(ObjectWithAtletas tWithAtletas){
        super("SelecionarAtletasPage");
        this.tWithAtletas = tWithAtletas;
        inicialize();
    }

    public SelecionarAtletasPage(ObjectWithAtletas tWithAtletas, LinkedList<Atleta> atletas){
        super("SelecionarAtletasPage");
        this.tWithAtletas = tWithAtletas;
        this.atletas = atletas;
        inicialize();
    }

    private void inicialize() {
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
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnConfirmarClickedActionPerformed();
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
    }

    private void btnConfirmarClickedActionPerformed(){
        for (int i = 0; i<tableAtletas.getRowCount(); i++){
            if (tableAtletas.getValueAt(i,7).equals("Selecionado") && this.tWithAtletas.atletaCanBeRemoved(GestorAtletas.getInstance().getAtletas().get(i))){
                this.tWithAtletas.adicionarAtleta(GestorAtletas.getInstance().getAtletas().get(i));
            }
            if (tableAtletas.getValueAt(i,7).equals("Selecionar") && this.tWithAtletas.atletaCanBeRemoved(GestorAtletas.getInstance().getAtletas().get(i))){
                this.tWithAtletas.removerAtleta(GestorAtletas.getInstance().getAtletas().get(i));
            }
        }
        dispose();
    }

    private void createTable(){

        //DATA FOR OUR TABLE
        Object[][] data = new Object[0][8];
        LinkedList<Atleta> atletas = new LinkedList<>();
        if (tWithAtletas.getClass() == Grupo.class)
            atletas = this.atletas;
        else
            atletas = GestorAtletas.getInstance().getAtletas();

        int i = 0;
        for (Atleta atleta : atletas) {
            Object[][] dataAux = new Object[data.length+1][8];
            System.arraycopy(data, 0, dataAux, 0, data.length);
            String aux = "Selecionar";
            if (this.tWithAtletas.getAtletas().contains(atleta))
                aux="Selecionado";
            if (tWithAtletas.getClass() == Prova.class || tWithAtletas.getClass() == Grupo.class)
                dataAux[i++] = new Object[]{atleta.getNome(),atleta.getPais(),atleta.getGenero(),atleta.getDataNascimento(),atleta.getContacto(), atleta.getTipoProvaPref(), "Provas", aux};
            else
                dataAux[i++] = new Object[]{atleta.getNome(),atleta.getPais(),atleta.getGenero(),atleta.getDataNascimento(),atleta.getContacto(), atleta.getTipoProvaPref(), "Provas"};

            data = dataAux.clone();
        }

        //COLUMN HEADERS
        String columnHeaders[];
        if (tWithAtletas.getClass() == Prova.class || tWithAtletas.getClass() == Grupo.class)
            columnHeaders = new String[]{"Nome","Pa??s","G??nero","Data de Nascimento","Contacto","Tipo de Prova Pref.","", ""};
        else
            columnHeaders =new String[]{"Nome","Pa??s","G??nero","Data de Nascimento","Contacto","Tipo de Prova Pref.",""};

        tableAtletas.setModel(new DefaultTableModel(
                data,columnHeaders
        ));
        //SET CUSTOM RENDERER TO TEAMS COLUMN
        tableAtletas.getColumnModel().getColumn(6).setCellRenderer(new ButtonSelecionarAtletasPageRenderer());
        if (tWithAtletas.getClass() == Prova.class || tWithAtletas.getClass() == Grupo.class)
            tableAtletas.getColumnModel().getColumn(7).setCellRenderer(new ButtonSelecionarAtletasPageRenderer());

        //SET CUSTOM EDITOR TO TEAMS COLUMN
        tableAtletas.getColumnModel().getColumn(6).setCellEditor(new ButtonSelecionarAtletasPageEditor(new JTextField(), this.tWithAtletas));
        if (tWithAtletas.getClass() == Prova.class || tWithAtletas.getClass() == Grupo.class)
            tableAtletas.getColumnModel().getColumn(7).setCellEditor(new ButtonSelecionarAtletasPageEditor(new JTextField(), this.tWithAtletas));

    }
}

class ButtonSelecionarAtletasPageRenderer extends JButton implements TableCellRenderer
{

    //CONSTRUCTOR
    public ButtonSelecionarAtletasPageRenderer() {
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

class ButtonSelecionarAtletasPageEditor extends DefaultCellEditor
{
    protected JButton btn;
    private String lbl;
    private Boolean clicked;
    private int row;
    private ObjectWithAtletas tWithAtletas;

    public ButtonSelecionarAtletasPageEditor(JTextField txt, ObjectWithAtletas tWithAtletas) {
        super(txt);
        this.tWithAtletas = tWithAtletas;
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
        clicked=true;
        this.row = row;
        return btn;
    }

    //IF BUTTON CELL VALUE CHNAGES,IF CLICKED THAT IS
    @Override
    public Object getCellEditorValue() {

        if(clicked)
        {
            //SHOW US SOME MESSAGE
            //JOptionPane.showMessageDialog(btn, lbl+" Clicked");
            switch(lbl){
                case "Provas":
                    ProvasAtletaPage provasAtletaPage = new ProvasAtletaPage(GestorAtletas.getInstance().getAtletas().get(row));
                    provasAtletaPage.pack();
                    provasAtletaPage.setVisible(true);
                    break;
                case "Selecionar", "Selecionado":
                    //JOptionPane.showMessageDialog(btn, lbl+" Clicked");
                    if (this.tWithAtletas.atletaCanBeRemoved(GestorAtletas.getInstance().getAtletas().get(row)))
                        lbl = lbl.equals("Selecionar") ? "Selecionado" : "Selecionar";
                    else
                        JOptionPane.showMessageDialog(btn, "O atleta j?? est?? em uso em alguma etapa desta prova!");
                    break;
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