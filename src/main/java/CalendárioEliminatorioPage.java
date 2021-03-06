import Etapas.Etapa;
import Eventos.Evento;
import Eventos.GestorEventos;
import Provas.Prova;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class Calend√°rioEliminatorioPage extends JFrame{
    private JPanel painelPrincipal;
    private JButton buttonVoltar;
    private JTable tableEventos;
    private JTextField pesquisaTextField;
    private JScrollPane sp;
    private JPanel panelBottom;
    private JButton buttonConfirmar;
    private JButton buttonCancelar;

    public Calend√°rioEliminatorioPage(){
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
        GestorProvasEliminatorias gestorProvasEliminatorias = GestorProvasEliminatorias.getInstance();
        LinkedList<Evento> eventos = GestorEventos.getInstance().getEventos();
        Object[][] data = new Object[0][8];
        int i = 0;
        for (Evento evento : eventos) {
            for (Prova prova : evento.getProvas()) {
                for (Etapa etapa : prova.getEtapas()) {
                    if (etapa.getGrupos().size() >= 2 && !etapa.getConcluido()){
                        Object[][] dataAux = new Object[data.length+1][8];
                        System.arraycopy(data, 0, dataAux, 0, data.length);
                        gestorProvasEliminatorias.adicionarEtapaEliminatoria(etapa);
                        dataAux[i++] = new Object[]{etapa.getDataInicio(),etapa.getDiaCompeticao(),etapa.getHora(),etapa.getGenero().toString(), etapa.getRonda(), prova.getTipoProva().toString(), evento.getNome(),"Abrir Atletas"};
                        data = dataAux.clone();
                    }
                }
            }
        }

        //COLUMN HEADERS
        String columnHeaders[]={"Data Inicio","Dia Competi√ß√£o","Hora","G√©nero","Ronda","Tipo Provas.Prova","Eventos.Evento", ""};

        tableEventos.setModel(new DefaultTableModel(
                data,columnHeaders
        ));
        //SET CUSTOM RENDERER TO TEAMS COLUMN
        tableEventos.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());

        //SET CUSTOM EDITOR TO TEAMS COLUMN
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