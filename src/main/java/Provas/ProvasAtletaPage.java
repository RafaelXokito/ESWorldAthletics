package Provas;

import Atletas.Atleta;
import Etapas.EtapaProvaPage;
import Eventos.Evento;
import Eventos.GestorEventos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class ProvasAtletaPage extends JFrame{
    private JPanel painelPrincipal;
    private JButton buttonVoltar;
    private JTable tableEventos;
    private JTextField pesquisaTextField;
    private JScrollPane sp;
    private JLabel lblTitle;
    private JPanel panelBottom;
    private JButton buttonConfirmar;
    private JButton buttonCancelar;
    private Atleta atleta;
    private LinkedList<Prova> provas;

    public ProvasAtletaPage(Atleta atleta){
        super("ProvasAtletaPage "+atleta.getNome());
        this.atleta = atleta;
        provas = new LinkedList<>();
        lblTitle.setText("Provas de " + atleta.getNome());
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
        LinkedList<Evento> eventos = GestorEventos.getInstance().getEventos();
        for (Evento evento : eventos) {
            for (Prova prova : evento.getProvas()) {
                for (Atleta atleta : prova.getAtletas()) {
                    if (atleta.equals(this.atleta)){
                        Object[][] dataAux = new Object[data.length+1][8];
                        System.arraycopy(data, 0, dataAux, 0, data.length);
                        dataAux[i++] = new Object[]{evento.getNome(),prova.getTipoProva(),prova.getEtapas().size(),evento.getDataInicio(), evento.getDataFim(), "", "Abrir Prova"};
                        data = dataAux.clone();
                        provas.add(prova);
                    }
                }
            }
        }

        //COLUMN HEADERS
        String columnHeaders[]={"Nome Evento","Tipo Prova","Num. Etapas","Data Inicio do Evento","Data Fim do Evento","Resultado",""};

        tableEventos.setModel(new DefaultTableModel(
                data,columnHeaders
        ));
        //SET CUSTOM RENDERER TO TEAMS COLUMN
        tableEventos.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());

        //SET CUSTOM EDITOR TO TEAMS COLUMN
        tableEventos.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(new JTextField(), this.provas));

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
    private LinkedList<Prova> provas;

    public ButtonEditor(JTextField txt, LinkedList<Prova> provas) {
        super(txt);
        this.provas = provas;
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
            EtapaProvaPage etapaProvaPage = new EtapaProvaPage(this.provas.get(row));
            etapaProvaPage.setVisible(true);
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