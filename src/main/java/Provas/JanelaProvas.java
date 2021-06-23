package Provas;



import Eventos.Evento;
import Eventos.GestorEventos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class JanelaProvas extends JFrame{
    private JPanel painelPrincipal;
    private JButton buttonVoltar;
    private JButton button1;
    private JTextField pesquisarTextField;
    private JTable tableProvas;
    private JLabel lblNomeEvento;

    private Evento evento;

    public JanelaProvas(Evento evento){
        super(evento.getNome());
        lblNomeEvento.setText(evento.getNome());
        this.evento = evento;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(painelPrincipal);
        setVisible(true);

        pack();

        createTable();
    }



    private void createTable(){

        Object[][] data = new Object[0][8];
        int i = 0;
        for (Prova prova : evento.getProvas()) {
            Object[][] dataAux = new Object[data.length+1][8];
            System.arraycopy(data, 0, dataAux, 0, data.length);
            dataAux[i++] = new Object[]{prova.getTipoProva(),prova.getEtapas().size(),prova.getDataInicio(),prova.getDataFimPrevisto(),"Alterar", "Abrir Prova"};
            data = dataAux.clone();
        }

        tableProvas.setModel(new DefaultTableModel(
                data,
                new Object[]{"Tipo Prova", "Nº Etapas", "Data Inicio", "Data Fim", "Alterar", "Abrir Prova"}
        ));
        //SET CUSTOM RENDERER TO TEAMS COLUMN
        tableProvas.getColumnModel().getColumn(4).setCellRenderer(new ButtonJanelaProvasRenderer());
        tableProvas.getColumnModel().getColumn(5).setCellRenderer(new ButtonJanelaProvasRenderer());

        //SET CUSTOM EDITOR TO TEAMS COLUMN
        tableProvas.getColumnModel().getColumn(4).setCellEditor(new ButtonJanelaProvasEditor(new JTextField()));
        tableProvas.getColumnModel().getColumn(5).setCellEditor(new ButtonJanelaProvasEditor(new JTextField()));

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

    public ButtonJanelaProvasEditor(JTextField txt) {
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
        clicked=true;
        return btn;
    }

    //IF BUTTON CELL VALUE CHNAGES,IF CLICKED THAT IS
    @Override
    public Object getCellEditorValue() {

        if(clicked)
        {
            //SHOW US SOME MESSAGE
            JOptionPane.showMessageDialog(btn, lbl+" Clicked");
            switch(lbl){
                case "Abrir Provas":

                    break;
                case "Alterar":

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