package Resultados;

import Atletas.Atleta;
import Atletas.GestorAtletas;
import Atletas.NovoAtleta;
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

public class RegistosAtletasPage extends JFrame{
    private JPanel painelPrincipal;
    private JButton buttonVoltar;
    private JTable tableAtletas;
    private JTextField pesquisaTextField;
    private JScrollPane sp;
    private JPanel panelBottom;
    private JButton buttonConfirmar;
    private JButton buttonCancelar;

    private Grupo grupo;
    private Etapa etapa;

    public RegistosAtletasPage(Grupo grupo, Etapa etapa){
        super("RegistosAtletasPage - Atletas para registo de resultados");
        this.grupo = grupo;
        this.etapa = etapa;
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

        Object[][] data = new Object[0][8];

        int i = 0;
        for (Atleta atleta : grupo.getAtletas()) {
            Object[][] dataAux = new Object[data.length+1][8];
            System.arraycopy(data, 0, dataAux, 0, data.length);
            dataAux[i++] = new Object[]{atleta.getNome(),atleta.getPais(),atleta.getGenero(),atleta.getDataNascimento(), atleta.getContacto(), atleta.getTipoProvaPref(), grupo.getResultado(atleta) == null ? "" : grupo.getResultado(atleta).getValor(), "Registar",};
            data = dataAux.clone();
        }

        tableAtletas.setModel(new DefaultTableModel(
                data,
                new Object[]{"Nome", "País", "Género", "Data Nascimento", "Contacto", "Tipo Prova Preferida", "Valor", ""}
        ));
        //SET CUSTOM RENDERER TO TEAMS COLUMN
        tableAtletas.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());

        //SET CUSTOM EDITOR TO TEAMS COLUMN
        tableAtletas.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor(new JTextField(),this.grupo, this.etapa));
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
    private Grupo grupo;
    private Etapa etapa;

    public ButtonEditor(JTextField txt, Grupo grupo, Etapa etapa) {
        super(txt);
        this.grupo = grupo;
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
            if (lbl.equals("Registar")) {
                registarInRowClicked();
            }
        }
        //SET IT TO FALSE NOW THAT ITS CLICKED
        clicked=false;
        return new String(lbl);
    }

    void registarInRowClicked() {
        RegistoResultadosAtletaGrupoEtapa registoResultadosAtletaGrupoEtapa = new RegistoResultadosAtletaGrupoEtapa("Registar", this.grupo.getAtletas().get(row), this.grupo, this.etapa);
        registoResultadosAtletaGrupoEtapa.setVisible(true);
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