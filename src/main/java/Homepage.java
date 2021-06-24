import Atletas.Atleta;
import Atletas.AtletasPage;
import Atletas.GestorAtletas;
import Etapas.Etapa;
import Eventos.Evento;
import Eventos.JanelaEventos;
import Grupos.Grupo;
import Provas.Prova;
import Resultados.Resultado;
import Utils.Data;
import Utils.Genero;
import Utils.TipoProva;
import Eventos.GestorEventos;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class Homepage extends JFrame {
    private JPanel painelPrincipal;
    private JPanel gridLayoutPanel;
    private JButton calendarioEliminatorioButton;
    private JButton recordesMundoButton;
    private JButton buttonEventos;
    private JButton atletasButton;

    public Homepage(String title){


        super(title);

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

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(painelPrincipal);

        calendarioEliminatorioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnCalendarioEliminatorioActionPerformed();
            }
        });

        recordesMundoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selecionarRecordesMundoClickedActionPerformed();
            }
        });

        pack();

        buttonEventos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            var janelaEventos = new JanelaEventos();
            janelaEventos.setVisible(true);
            }
        });

        atletasButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selecionarAtletasClickedActionPerformed();
            }
        });
    }

    public void btnCalendarioEliminatorioActionPerformed() {
        CalendárioEliminatorioPage calendarioEliminatorioPage = new CalendárioEliminatorioPage();
        calendarioEliminatorioPage.pack();
        calendarioEliminatorioPage.setVisible(true);
    }

    public void selecionarRecordesMundoClickedActionPerformed() {
        RecordesMundoProvaPage recordesMundoProvaPage = new RecordesMundoProvaPage();
        recordesMundoProvaPage.pack();
        recordesMundoProvaPage.setVisible(true);
    }

    public void selecionarAtletasClickedActionPerformed() {
        AtletasPage atletasPage = new AtletasPage();
        atletasPage.pack();
        atletasPage.setVisible(true);
    }
    public static void main(String[] args){
        new Homepage("Homepage").setVisible(true);
    }
}
