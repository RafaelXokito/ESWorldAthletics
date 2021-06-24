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
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.LinkedList;

public class ESWorldAtheleticsTests {
    public void setup(){
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
        Etapa evento1prova1 = new Etapa("24-06-2022", "09:00", "Quartos-Final", "24", Genero.Nao_Defenido, "10", prova1evento1);
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
    public void testCreateEvento() {
        Evento evento1 = new Evento("Jogos Olimpicos", "China", "Tokio",  new Data(2022, 6, 23),new Data(2022,7,23));
        GestorEventos.getInstance().addEvento(evento1);
        assertEquals(true,GestorEventos.getInstance().getEventos().contains(evento1));
    }

    @Test
    public void testDefinirAutomaticamenteCalendarioDeProvasEliminatorias(){
        setup();

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
    public void testApresentarCalendárioDeProvasEliminatórias(){
        testDefinirAutomaticamenteCalendarioDeProvasEliminatorias();
        assertEquals(true, GestorProvasEliminatorias.getInstance().getEtapasEliminatorias().size()>0);
    }

    @Test
    public void testRegistarProgressoDeProvaEliminatória(){
        setup();
        int i = 0;
        for (Grupo grupo:
        GestorEventos.getInstance().getEventos().getFirst().getProvas()
                .getFirst().getEtapas().getFirst().getGrupos()) {
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
}
