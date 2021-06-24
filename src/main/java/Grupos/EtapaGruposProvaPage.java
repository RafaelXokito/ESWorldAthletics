package Grupos;

import Atletas.Atleta;
import Atletas.SelecionarAtletasPage;
import Etapas.Etapa;
import Etapas.EtapaProvaPage;
import Eventos.Evento;
import Eventos.GestorEventos;
import Provas.Prova;
import Resultados.AtletasVencedoresPage;
import Resultados.RegistosAtletasPage;
import Utils.TipoProva;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class EtapaGruposProvaPage extends JFrame{
    private JPanel painelPrincipal;
    private JButton buttonVoltar;
    private JTable tableEventos;
    private JTextField pesquisaTextField;
    private JScrollPane sp;
    private JLabel lblTitle;
    private JPanel panelBottom;
    private JButton buttonConfirmar;
    private JButton buttonCancelar;
    private Etapa etapa;

    public EtapaGruposProvaPage(TipoProva tipoProva, Etapa etapa){
        super("EtapaGruposProvaPage - Etapas de " + tipoProva.toString() + " " + etapa.getRonda() + " " + etapa.getGrupos().size());
        this.etapa = etapa;
        lblTitle.setText("Etapas de " + tipoProva.toString() + etapa.getRonda() + " " + etapa.getGrupos().size());
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
    }
    private void createTable(){

        //DATA FOR OUR TABLE
        Object[][] data = new Object[0][8];
        int i = 0;
        for (Grupo grupo : etapa.getGrupos()) {
            Object[][] dataAux = new Object[data.length+1][8];
            System.arraycopy(data, 0, dataAux, 0, data.length);
            dataAux[i++] = new Object[]{grupo.getNum(),grupo.getAtletas().size(),"Registar Valores","Selecionar Vencedores", "Selecionar Atletas"};
            data = dataAux.clone();
        }

        //COLUMN HEADERS
        String columnHeaders[]={"Numero do Grupo","Número de Atletas","","",""};

        tableEventos.setModel(new DefaultTableModel(
                data,columnHeaders
        ));
        //SET CUSTOM RENDERER TO TEAMS COLUMN
        tableEventos.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
        tableEventos.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
        tableEventos.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());

        //SET CUSTOM EDITOR TO TEAMS COLUMN
        tableEventos.getColumnModel().getColumn(2).setCellEditor(new ButtonEditor(new JTextField(), this.etapa));
        tableEventos.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JTextField(), this.etapa));
        tableEventos.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JTextField(), this.etapa));

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
    private Etapa etapa;

    public ButtonEditor(JTextField txt, Etapa etapa) {
        super(txt);
        this.etapa = etapa;
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
            //EtapaProvaPage etapaProvaPage = new EtapaProvaPage(this.provas.get(row));
            //etapaProvaPage.setVisible(true);
            switch(lbl){
                case "Selecionar Atletas":
                    var selecionarAtletasPage = new SelecionarAtletasPage(this.etapa.getGrupos().get(row), this.etapa.getAtletas());
                    selecionarAtletasPage.setVisible(true);
                    break;
                case "Selecionar Vencedores":
                    var etapaGruposProvaPage = new AtletasVencedoresPage(this.etapa.getGrupos().get(row), this.etapa);
                    etapaGruposProvaPage.setVisible(true);
                    break;
                case "Registar Valores":
                    var registosAtletasPage = new RegistosAtletasPage(this.etapa.getGrupos().get(row), this.etapa);
                    registosAtletasPage.setVisible(true);
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