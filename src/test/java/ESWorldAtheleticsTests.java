import Atletas.Atleta;
import Atletas.GestorAtletas;
import Etapas.Etapa;
import Eventos.Evento;
import Eventos.GestorEventos;
import Grupos.Grupo;
import Provas.Prova;
import Resultados.Resultado;
import Utils.Data;
import Utils.Genero;
import Utils.TipoProva;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import static org.junit.jupiter.api.Assertions.*;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

class ESWorldAtheleticsTests {
    @BeforeEach
    void setUp() {
        Evento evento1 = new Evento("Jogos Olimpicos", "China", "Tokio",  new Data(2022, 6, 23),new Data(2022,7,23));
        Prova prova1evento1 = new Prova(TipoProva.Salto_em_Comprimento, "Menor Valor", new Data(2022,6,24), new Data(2022,7, 22), evento1,'<');
        Atleta atletaRafael = new Atleta("Rafael Mendes Pereira", "Portugal", Genero.M, new Data(2000, 9, 3), "910000000", TipoProva.Salto_em_Comprimento);
        Atleta atletaBruna = new Atleta("Bruna Alexandra Marques Leitão", "Portugal", Genero.F, new Data(2001,7,8), "920000000", TipoProva.Salto_em_Altura);
        Atleta atletaLucas = new Atleta("Lucas", "Portugal", Genero.M, new Data(1999,1,28), "930000000", TipoProva.Salto_em_Altura);
        GestorAtletas.getInstance().addAtleta(atletaRafael);
        GestorAtletas.getInstance().addAtleta(atletaBruna);
        GestorAtletas.getInstance().addAtleta(atletaLucas);
        prova1evento1.adicionarAtleta(atletaRafael);
        prova1evento1.adicionarAtleta(atletaBruna);
        Etapa evento1prova1 = new Etapa(new Data(2022, 6,24), "09:00", "Quartos-Final", "24", Genero.Nao_Defenido, "10", prova1evento1);
        LinkedList<Atleta> linkedListAtletas = new LinkedList<Atleta>();
        linkedListAtletas.add(atletaRafael);
        Grupo grupo1etapa1prova1 = new Grupo(1,linkedListAtletas, evento1prova1);
        linkedListAtletas = new LinkedList<Atleta>();
        linkedListAtletas.add(atletaBruna);
        Grupo grupo2etapa1prova1 = new Grupo(2,linkedListAtletas, evento1prova1);
        grupo1etapa1prova1.adicionarResultado(new Resultado(atletaRafael,1.7, new Data(2022, 6, 24), grupo1etapa1prova1));
        grupo1etapa1prova1.computeVencedores();
        grupo2etapa1prova1.adicionarResultado(new Resultado(atletaBruna,1.8, new Data(2022, 6, 25), grupo2etapa1prova1));
        evento1prova1.adicionarGrupo(grupo1etapa1prova1);
        evento1prova1.adicionarGrupo(grupo2etapa1prova1);
        prova1evento1.adicionarEtapa(evento1prova1);
        evento1.adicionarProva(prova1evento1);

        GestorEventos.getInstance().addEvento(evento1);
    }

    @Test
    void testCreateEvento() {
        Evento evento1 = new Evento("Jogos Olimpicos", "China", "Tokio",  new Data(2022, 6, 23),new Data(2022,7,23));
        GestorEventos.getInstance().addEvento(evento1);
        assertEquals(true,GestorEventos.getInstance().getEventos().contains(evento1));
    }

    @Test
    void testDefinirAutomaticamenteCalendarioDeProvasEliminatorias(){

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

        assertEquals(true, data.length>0);
    }

    @Test
    void testApresentarCalendárioDeProvasEliminatórias(){
        testDefinirAutomaticamenteCalendarioDeProvasEliminatorias();
        assertEquals(true, GestorProvasEliminatorias.getInstance().getEtapasEliminatorias().size()>0);
    }

    @Test
    void testRegistarProgressoDeProvaEliminatória(){
        LinkedList<Grupo> grupos = GestorEventos.getInstance().getEventos().getFirst().getProvas()
                .getFirst().getEtapas().getFirst().getGrupos();
        int i = 0;
        for (Grupo grupo: grupos) {
            for (Atleta atleta : grupo.getAtletas()) {
                if (grupo.getResultado(atleta) == null)
                    grupo.adicionarResultado(new Resultado(atleta,1.6,new Data(2020,6, 3), grupo));
            }
            if (grupo.getResultados().size() == grupo.getAtletas().size())
                i++;
        }
        if (i == GestorEventos.getInstance().getEventos().getFirst().getProvas()
                .getFirst().getEtapas().getFirst().getGrupos().size()){
            GestorEventos.getInstance().getEventos().getFirst().getProvas()
                    .getFirst().getEtapas().getFirst().setConcluido();
        }
        assertEquals(true, GestorEventos.getInstance().getEventos().getFirst().getProvas()
                .getFirst().getEtapas().getFirst().getConcluido());
    }

    @Test
    void testRegistarResultadoDeProva(){
        LinkedList<Grupo> grupos = GestorEventos.getInstance().getEventos().getFirst().getProvas()
                .getFirst().getEtapas().getFirst().getGrupos();
        for (Grupo grupo: grupos) {
            for (Atleta atleta : grupo.getAtletas()) {
                if (grupo.getResultado(atleta) == null) {
                    grupo.adicionarResultado(new Resultado(atleta, 1.6, new Data(2020, 6, 3), grupo));
                    assertEquals(true, grupo.getResultado(atleta) != null);
                }
            }
        }
    }

    @Test
    void testEfetuarListagemDeProvasAQueUmDadoAtletaEstaInscrito(){
        LinkedList<Prova> provas = new LinkedList<>();
        Atleta atletaTest = GestorAtletas.getInstance().getAtletas().getFirst();
        Object[][] data = new Object[0][8];
        int i = 0;
        LinkedList<Evento> eventos = GestorEventos.getInstance().getEventos();
        for (Evento evento : eventos) {
            for (Prova prova : evento.getProvas()) {
                for (Atleta atleta : prova.getAtletas()) {
                    if (atleta.equals(atletaTest)){
                        Object[][] dataAux = new Object[data.length+1][8];
                        System.arraycopy(data, 0, dataAux, 0, data.length);
                        dataAux[i++] = new Object[]{evento.getNome(),prova.getTipoProva(),prova.getEtapas().size(),evento.getDataInicio(), evento.getDataFim(), "", "Abrir Prova"};
                        data = dataAux.clone();
                        provas.add(prova);
                    }
                }
            }
        }
        assertEquals(true, data.length>0);
    }

    @Test
    void testEfetuarListagemDeInscritosPorProva(){
        //DATA FOR OUR TABLE
        Object[][] data = new Object[0][8];
        LinkedList<Atleta> atletas = GestorAtletas.getInstance().getAtletas();
        Prova prova = GestorEventos.getInstance().getEventos().getFirst().getProvas().getFirst();

        int i = 0;
        for (Atleta atleta : atletas) {
            Object[][] dataAux = new Object[data.length+1][8];
            System.arraycopy(data, 0, dataAux, 0, data.length);
            String aux = "Selecionar";
            if (prova.getAtletas().contains(atleta))
                aux="Selecionado";

            dataAux[i++] = new Object[]{atleta.getNome(),atleta.getPais(),atleta.getGenero(),atleta.getDataNascimento(),atleta.getContacto(), atleta.getTipoProvaPref(), "Provas", aux};

            data = dataAux.clone();
        }

        assertEquals(true, data.length>0);
    }

    @Test
    void testListarOsRecordesDoMundoPorProva(){

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
        assertEquals(true, data.length>0);
    }

    @Test
    void testCriarAtleta(){
        Atleta atletaRafael = new Atleta("Rafael Mendes Pereira", "Portugal", Genero.M, new Data(2000, 9, 3), "910000000", TipoProva.Salto_em_Comprimento);
        assertEquals(true, atletaRafael != null);
    }

    @Test
    void testEditarAtleta(){
        Atleta atleta = GestorAtletas.getInstance().getAtletas().getFirst();
        atleta.setGenero(Genero.Nao_Defenido);
        atleta.setTipoProvaPref(TipoProva.Salto_em_Altura);
        atleta.setNome("Bruna Leitão");
        atleta.setPais("Portugal");
        atleta.setDataNascimento(new Data(2000, 6, 3));
        atleta.setContacto("930000000");
        assertEquals(true, atleta != null);
    }

    @Test
    void testGerirInscricoesDeAtletasNasProvas() {
        Prova prova = GestorEventos.getInstance().getEventos().getFirst().getProvas().getFirst();
        Atleta atletaLucas = new Atleta("Lucas", "Portugal", Genero.M, new Data(199, 9, 3), "930000000", TipoProva.Salto_em_Comprimento);
        prova.adicionarAtleta(atletaLucas);

        assertEquals(true, prova.getAtletas().contains(atletaLucas));

        prova.removerAtleta(atletaLucas);

        assertEquals(true, !prova.getAtletas().contains(atletaLucas));
    }

    @Test
    void testConsultarHistóricoDoAtleta() {
        //DATA FOR OUR TABLE
        Object[][] data = new Object[0][8];
        Atleta atleta1 = GestorAtletas.getInstance().getAtletas().getFirst();
        LinkedList<Prova> provas = new LinkedList<>();
        int i = 0;
        LinkedList<Evento> eventos = GestorEventos.getInstance().getEventos();
        for (Evento evento : eventos) {
            for (Prova prova : evento.getProvas()) {
                for (Atleta atleta : prova.getAtletas()) {
                    if (atleta.equals(atleta1)){
                        Object[][] dataAux = new Object[data.length+1][8];
                        System.arraycopy(data, 0, dataAux, 0, data.length);
                        dataAux[i++] = new Object[]{evento.getNome(),prova.getTipoProva(),prova.getEtapas().size(),evento.getDataInicio(), evento.getDataFim(), "", "Abrir Prova"};
                        data = dataAux.clone();
                        provas.add(prova);
                    }
                }
            }
        }
        assertEquals(true, data.length>0);
    }

    @Test
    void testImportarDadosDosAtletasAPartirDeFicheiro() {
        try {
            //  Block of code to try
            int atletas = GestorAtletas.getInstance().getAtletas().size();
            File file = new File("ESAtletaTeste.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataSplited = data.split("\\t");
                GestorAtletas.getInstance().addAtleta(new Atleta(dataSplited[0],dataSplited[1], Genero.getGenero(dataSplited[2]),
                        new Data(Integer.valueOf(dataSplited[3].split("-")[2]), Integer.valueOf(dataSplited[3].split("-")[1]), Integer.valueOf(dataSplited[3].split("-")[0])),
                        dataSplited[4], TipoProva.getTipoProva(dataSplited[5])));
                System.out.println(data);
            }
            myReader.close();
            assertEquals(true, GestorAtletas.getInstance().getAtletas().size()>atletas);

        }
        catch(Exception e) {
            throw new IllegalCallerException();
        }
    }
}
