package Resultados;

import Atletas.Atleta;
import Atletas.GestorAtletas;
import Etapas.Etapa;
import Grupos.Grupo;
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

public class AtletasVencedoresPage extends JFrame{
    private JPanel painelPrincipal;
    private JButton buttonVoltar;
    private JTable tableAtletas;
    private JTextField pesquisaTextField;
    private JScrollPane sp;
    private JLabel lblTitle;
    private JButton btnCancelar;
    private JPanel panelBottom;
    private JButton btnConfirmar;
    private Grupo grupo;
    private LinkedList<Atleta> atletas;
    private Etapa etapa;

    public AtletasVencedoresPage(Grupo grupo,  Etapa etapa){
        super("AtletasVencedoresPage - Selecionar Atletas Vencedores");
        lblTitle.setText("Selecionar Atletas Vencedores " + grupo.getNum());
        this.grupo = grupo;
        this.etapa = etapa;
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
            if (tableAtletas.getValueAt(i,8).equals("Vencedor")){
                this.grupo.adicionarVencedor(grupo.getAtletas().get(i));
            }
            if (tableAtletas.getValueAt(i,8).equals("Perdedor")){
                this.grupo.removerVencedor(grupo.getAtletas().get(i));
            }
        }
        int i = 0;
        for (Grupo grupo:etapa.getGrupos()) {
            if (grupo.getResultados().size() == grupo.getAtletas().size()){
                i++;
            }
        }
        if (etapa.getGrupos().size() == i) {
            etapa.setConcluido();
        }
        dispose();
    }

    private void createTable(){

        //DATA FOR OUR TABLE
        Object[][] data = new Object[0][8];
        LinkedList<Atleta> atletas = new LinkedList<>();
        if (grupo.getClass() == Grupo.class)
            atletas = grupo.getAtletas();
        else
            atletas = GestorAtletas.getInstance().getAtletas();

        int i = 0;
        for (Atleta atleta : atletas) {
            Object[][] dataAux = new Object[data.length+1][8];
            System.arraycopy(data, 0, dataAux, 0, data.length);
            String aux = "Perdedor";
            if (this.grupo.getVencedores().contains(atleta))
                aux="Vencedor";

            dataAux[i++] = new Object[]{atleta.getNome(),atleta.getPais(),atleta.getGenero(),atleta.getDataNascimento(),atleta.getContacto(), atleta.getTipoProvaPref(), this.grupo.getResultado(atleta).getValor(), "Provas", aux};

            data = dataAux.clone();
        }

        //COLUMN HEADERS
        String columnHeaders[];
        columnHeaders = new String[]{"Nome","País","Género","Data de Nascimento","Contacto","Tipo de Prova Pref.", "Resultado","", "Vencedores"};

        tableAtletas.setModel(new DefaultTableModel(
                data,columnHeaders
        ));
        //SET CUSTOM RENDERER TO TEAMS COLUMN
        tableAtletas.getColumnModel().getColumn(7).setCellRenderer(new ButtonAtletasVencedoresPageRenderer());
        if (grupo.getClass() == Grupo.class)
            tableAtletas.getColumnModel().getColumn(8).setCellRenderer(new ButtonAtletasVencedoresPageRenderer());

        //SET CUSTOM EDITOR TO TEAMS COLUMN
        tableAtletas.getColumnModel().getColumn(7).setCellEditor(new ButtonAtletasVencedoresPageEditor(new JTextField(), this.grupo));
        if (grupo.getClass() == Grupo.class)
            tableAtletas.getColumnModel().getColumn(8).setCellEditor(new ButtonAtletasVencedoresPageEditor(new JTextField(), this.grupo));

    }
}

class ButtonAtletasVencedoresPageRenderer extends JButton implements TableCellRenderer
{

    //CONSTRUCTOR
    public ButtonAtletasVencedoresPageRenderer() {
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

class ButtonAtletasVencedoresPageEditor extends DefaultCellEditor
{
    protected JButton btn;
    private String lbl;
    private Boolean clicked;
    private int row;
    private Grupo Grupo;

    public ButtonAtletasVencedoresPageEditor(JTextField txt, Grupo Grupo) {
        super(txt);
        this.Grupo = Grupo;
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
                case "Perdedor", "Vencedor":
                    //JOptionPane.showMessageDialog(btn, lbl+" Clicked");
                    lbl = lbl.equals("Perdedor") ? "Vencedor" : "Perdedor";
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