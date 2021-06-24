package Etapas;



import Atletas.SelecionarAtletasPage;
import Eventos.Evento;
import Grupos.EtapaGruposProvaPage;
import Provas.NovaProva;
import Provas.Prova;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EtapaProvaPage extends JFrame{
    private JPanel painelPrincipal;
    private JButton buttonVoltar;
    private JButton button1;
    private JTextField pesquisarTextField;
    private JTable tableProvas;
    private JLabel lblNomeEvento;

    private Prova prova;

    public EtapaProvaPage(Prova prova){
        super("EtapaProvaPage - Prova - " + prova.getTipoProva().toString());
        lblNomeEvento.setText("Prova - " + prova.getTipoProva().toString());
        this.prova = prova;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(painelPrincipal);
        setVisible(true);

        pack();

        createTable();
    }



    private void createTable(){

        Object[][] data = new Object[0][8];
        int i = 0;
        for (Etapa etapa : prova.getEtapas()) {
            Object[][] dataAux = new Object[data.length+1][8];
            System.arraycopy(data, 0, dataAux, 0, data.length);
            dataAux[i++] = new Object[]{etapa.getDataInicio(),etapa.getDiaCompeticao(),etapa.getHora(),etapa.getGenero(), etapa.getRonda(), etapa.getMinimos(),"Alterar", "Abrir Grupos", "Abrir Atletas", "Recordes"};
            data = dataAux.clone();
        }

        tableProvas.setModel(new DefaultTableModel(
                data,
                new Object[]{"Data de Início", "Dia Competição", "Hora", "Género", "Ronda", "Mínimos", "", "", "", ""}
        ));
        //SET CUSTOM RENDERER TO TEAMS COLUMN
        tableProvas.getColumnModel().getColumn(6).setCellRenderer(new ButtonJanelaProvasRenderer());
        tableProvas.getColumnModel().getColumn(7).setCellRenderer(new ButtonJanelaProvasRenderer());
        tableProvas.getColumnModel().getColumn(8).setCellRenderer(new ButtonJanelaProvasRenderer());
        tableProvas.getColumnModel().getColumn(9).setCellRenderer(new ButtonJanelaProvasRenderer());

        //SET CUSTOM EDITOR TO TEAMS COLUMN
        tableProvas.getColumnModel().getColumn(6).setCellEditor(new ButtonJanelaProvasEditor(new JTextField(), this.prova));
        tableProvas.getColumnModel().getColumn(7).setCellEditor(new ButtonJanelaProvasEditor(new JTextField(), this.prova));
        tableProvas.getColumnModel().getColumn(8).setCellEditor(new ButtonJanelaProvasEditor(new JTextField(), this.prova));
        tableProvas.getColumnModel().getColumn(9).setCellEditor(new ButtonJanelaProvasEditor(new JTextField(), this.prova));

    }
}

class ButtonJanelaProvasRenderer extends JButton implements TableCellRenderer
{

    //CONSTRUCTOR
    public ButtonJanelaProvasRenderer() {
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

class ButtonJanelaProvasEditor extends DefaultCellEditor
{
    protected JButton btn;
    private String lbl;
    private Boolean clicked;
    private int row;
    private Prova prova;

    public ButtonJanelaProvasEditor(JTextField txt, Prova prova) {
        super(txt);

        this.prova = prova;
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
                case "Abrir Atletas":
                    var selecionarAtletasPage = new SelecionarAtletasPage(this.prova.getEtapas().get(row));
                    selecionarAtletasPage.setVisible(true);
                    break;
                case "Abrir Grupos":
                    var etapaGruposProvaPage = new EtapaGruposProvaPage(this.prova.getTipoProva(),this.prova.getEtapas().get(row));
                    etapaGruposProvaPage.setVisible(true);
                    break;
                case "Alterar":
                    /*var novaProva = new NovaProva("Alterar Prova", this.prova.getEtapas().get(row));
                    novaProva.setVisible(true);*/
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