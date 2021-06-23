import Etapas.Etapa;
import Eventos.Evento;
import Eventos.GestorEventos;
import Grupos.Grupo;
import Provas.Prova;
import Resultados.Resultado;
import Utils.TipoProva;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class RecordesMundoProvaPage extends JFrame{
    private JPanel painelPrincipal;
    private JButton buttonVoltar;
    private JTable tableEventos;
    private JTextField pesquisaTextField;
    private JButton criarEventoButton;
    private JScrollPane sp;
    private JPanel panelBottom;
    private JButton buttonConfirmar;
    private JButton buttonCancelar;

    public RecordesMundoProvaPage(){
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

        LinkedList<Evento> eventos = GestorEventos.getInstance().getEventos();
        LinkedList<TipoProva> tipoProvaLinkedList = new LinkedList<>();
        LinkedList<Resultado> resultadoLinkedList = new LinkedList<>();
        LinkedList<Evento> eventoLinkedList = new LinkedList<>();
        Object[][] data = new Object[0][8];
        int j = 0;
        for (Evento evento : eventos) {
            for (Prova prova : evento.getProvas()) {
                for (Etapa etapa : prova.getEtapas()) {
                    for (Grupo grupo: etapa.getGrupos()) {
                        for (Resultado resultado : grupo.getResultados()) {
                            if (tipoProvaLinkedList.contains(prova.getTipoProva())) {
                                for (int i = 0; i < tipoProvaLinkedList.size(); i++){
                                    if (tipoProvaLinkedList.get(i).equalsName(prova.getTipoProva().toString()) && resultadoLinkedList.get(i).getValor() < resultado.getValor()){
                                        resultadoLinkedList.set(i,resultado);
                                        eventoLinkedList.set(i,evento);

                                        data[i] = new Object[]{prova.getTipoProva(),resultado.getValor(),resultado.getAtleta().getNome(),etapa.getGenero().toString(), evento.getPais(), resultado.getData(), evento.getLocal(),evento.getNome()};
                                        break;
                                    }
                                }
                            }else {
                                tipoProvaLinkedList.add(prova.getTipoProva());
                                resultadoLinkedList.add(resultado);
                                eventoLinkedList.add(evento);

                                Object[][] dataAux = new Object[data.length+1][8];
                                System.arraycopy(data, 0, dataAux, 0, data.length);
                                dataAux[j++] = new Object[]{prova.getTipoProva(),resultado.getValor(),resultado.getAtleta().getNome(),etapa.getGenero().toString(), evento.getPais(), resultado.getData(), evento.getLocal(),evento.getNome()};
                                data = dataAux.clone();
                            }

                        }
                    }
                }

            }
        }

        tableEventos.setModel(new DefaultTableModel(
                data,
                new Object[]{"Tipo Provas.Prova", "Valor", "Nome Atletas.Atleta", "Género da Provas.Prova", "País", "Data", "Local", "Eventos.Evento"}
        ));
    }

}