package Eventos;

import Utils.Data;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class JanelaEditarEvento extends JFrame {
    private JPanel painelPrincipal;
    private JButton btnRemover;
    private JTextField textFieldNome;
    private JFormattedTextField formattedTextFieldDtaInicio;
    private JTextField textFieldPais;
    private JFormattedTextField formattedTextFieldDtaFim;
    private JTextField textFieldLocal;
    private JButton Confirmar;
    private JTextField textFieldDtaInicio1;
    private JTextField textFieldDtaInicio2;
    private JTextField textFieldDtaInicio3;
    private JTextField textFieldDtaFim1;
    private JTextField textFieldDtaFim2;
    private JTextField textFieldDtaFim3;
    //LinkedList<Evento> eventos = GestorEventos.getInstance().getEventos();
    //String nome, String pais, String local, String dtaInicio, String dtaFim

    public JanelaEditarEvento(int index, LinkedList<Evento> eventos){
        Evento evento = eventos.get(index);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(painelPrincipal);
        setVisible(true);
        pack();
        carregarDados(evento);


        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remover(evento);
            }

            private void remover(Evento evento) {
                try {
                    eventos.remove(evento);
                    JOptionPane.showMessageDialog(null,
                            "Evento eliminado",
                            "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                            "Não foi possivel eliminaar evento",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        Confirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editar();
            }

            private void editar() {

                String nome = textFieldNome.getText();
                String pais = textFieldPais.getText();
                String local = textFieldLocal.getText();
                int dia1 = Integer.parseInt(textFieldDtaInicio1.getText());
                int mes1 = Integer.parseInt(textFieldDtaInicio2.getText());
                int ano1 = Integer.parseInt(textFieldDtaInicio3.getText());
                int dia2 = Integer.parseInt(textFieldDtaFim1.getText());
                int mes2 = Integer.parseInt(textFieldDtaFim2.getText());
                int ano2 = Integer.parseInt(textFieldDtaFim3.getText());
                Data dtaInicio = new Data(ano1,mes1,dia1);
                Data dtaFim = new Data(ano2,mes2,dia2);
                try {
                    evento.setNome(nome);
                    evento.setPais(pais);
                    evento.setLocal(local);
                    evento.setDataInicio(dtaInicio);
                    evento.setDataFim(dtaFim);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                JOptionPane.showMessageDialog(null,
                        "Evento "+nome+" adicionado!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }


    private void carregarDados(Evento evento) {
        //textFieldNome.setText(nome);
        //textFieldPais.setText(pais);
        //textFieldLocal.setText(local);
        //formattedTextFieldDtaInicio.setText(dtaInicio.toString());
        //formattedTextFieldDtaFim.setText(dtaFim.toString());
        textFieldNome.setText(evento.getNome());
        textFieldPais.setText(evento.getPais());
        textFieldLocal.setText(evento.getLocal());
        String[] dtaInicio = evento.getDataInicio().toString().split("-");
        String[] dtaFim = evento.getDataFim().toString().split("-");

        textFieldDtaInicio1.setText(dtaInicio[0]);
        textFieldDtaInicio2.setText(dtaInicio[1]);
        textFieldDtaInicio3.setText(dtaInicio[2]);
        textFieldDtaFim1.setText(dtaFim[0]);
        textFieldDtaFim2.setText(dtaFim[1]);
        textFieldDtaFim3.setText(dtaFim[2]);
    }

    private void fechar(){
        dispose();
    }

}
