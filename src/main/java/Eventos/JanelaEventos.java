package Eventos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultCellEditor;
import java.awt.Component;

public class JanelaEventos extends JFrame{
    private JPanel painelPrincipal;
    private JButton buttonVoltar;
    private JTable tableEventos;
    private JTextField pesquisaTextField;
    private JButton paisesMaisMedalhadosButton;
    private JButton criarEventoButton;
    private JScrollPane sp;
    private JPanel panelBottom;
    private JButton buttonConfirmar;
    private JButton buttonCancelar;

    public JanelaEventos(){
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


        criarEventoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var janelaCriarEventos = new JanelaCriarEvento();
                janelaCriarEventos.setVisible(true);
            }
        });
    }
    private void createTable(){

        JButton btn1 = new JButton("Alterar");
        JButton btn2 = new JButton("Abrir Provas");



        Object[][] data = {{"Eventos.Evento 1", "Portugal","Lisboa", "11/04/2030", "11/06/2030", "Alterar", "Abrir Provas", "Duplicar"},
                {"Eventos.Evento 2", "Franca", "Paris", "05/11/2050", "14/01/2051", "Alterar", "Abrir Provas", "Duplicar" }
        };

        tableEventos.setModel(new DefaultTableModel(
                data,
                new Object[]{"Nome", "País", "Local", "Utils.Data Inicio", "Utils.Data Fim", "Alterar", "Provas", "Duplicar"}
        ));
        //SET CUSTOM RENDERER TO TEAMS COLUMN
        tableEventos.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        tableEventos.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
        tableEventos.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());

        //SET CUSTOM EDITOR TO TEAMS COLUMN
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