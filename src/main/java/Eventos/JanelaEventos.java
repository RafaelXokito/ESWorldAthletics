package Eventos;


import Provas.JanelaProvas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultCellEditor;
import java.awt.Component;
import java.util.LinkedList;

public class JanelaEventos extends JFrame{
    private JPanel painelPrincipal;
    private JButton btnVoltar;
    private JTable tableEventos;
    private JTextField pesquisaTextField;
    private JButton paisesMaisMedalhadosButton;
    private JButton btnCriarEvento;
    private JScrollPane sp;
    private JTable table1;

    public JanelaEventos(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(painelPrincipal);
        setVisible(true);

        pack();

        btnVoltar.addActionListener(new ActionListener() {
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


        btnCriarEvento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCriarEvento();
            }

            private void mostrarCriarEvento() {
                var janelaCriarEventos = new JanelaCriarEvento();
                janelaCriarEventos.setVisible(true);
            }
        });
    }
    private void createTable(){
        LinkedList<Evento> eventos = GestorEventos.getInstance().getEventos();
        Object[][] data = new Object[0][8];
        int i = 0;
        for (Evento evento : eventos) {
            Object[][] dataAux = new Object[data.length+1][8];
            System.arraycopy(data, 0, dataAux, 0, data.length);
            dataAux[i++] = new Object[]{evento.getNome(),evento.getPais(),evento.getLocal(),evento.getDataInicio(), evento.getDataFim(),"Alterar", "Abrir Provas", "Duplicar"};
            data = dataAux.clone();
        }

        tableEventos.setModel(new DefaultTableModel(
                data,
                new Object[]{"Nome", "País", "Local", "Data Inicio", "Data Fim", "Alterar", "Provas", "Duplicar"}
        ));

        tableEventos.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        tableEventos.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
        tableEventos.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());

        tableEventos.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JTextField()));
        tableEventos.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(new JTextField()));
        tableEventos.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor(new JTextField()));
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


    private String nome;
    private String pais;
    private String local;
    private String dtaInicio;
    private String dtaFim;


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
        //OBTER DADOS DA CELL SELECIONADA
        nome=table.getModel().getValueAt(row,0).toString();
        pais=table.getModel().getValueAt(row,1).toString();
        local=table.getModel().getValueAt(row,2).toString();
        dtaInicio=table.getModel().getValueAt(row,3).toString();
        dtaFim=table.getModel().getValueAt(row,4).toString();

        //System.out.println(nome);


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
                case "Abrir Provas":
                    var janelaProvas = new JanelaProvas(GestorEventos.getInstance().getEventos().get(row));
                    janelaProvas.setVisible(true);
                    break;
                case "Alterar":
                    var janelaEditarEvento = new JanelaEditarEvento(nome,pais,local, dtaInicio, dtaFim);
                    janelaEditarEvento.setVisible(true);
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