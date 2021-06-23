package Atletas;

import Utils.Data;
import Utils.Genero;
import Utils.TipoProva;

public class Atleta {
    private String nome;
    private String pais;
    private Genero genero;
    private Data dataNascimento;
    private String contacto;
    private TipoProva tipoProvaPref; //Provas.Prova preferida

    public Atleta(String nome, String pais, Genero genero, Data dataNascimento, String contacto, TipoProva tipoProvaPref) {
        this.nome = nome;
        this.pais = pais;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
        this.contacto = contacto;
        this.tipoProvaPref = tipoProvaPref;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Data getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Data dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public TipoProva getTipoProvaPref() {
        return tipoProvaPref;
    }

    public void setTipoProvaPref(TipoProva tipoProvaPref) {
        this.tipoProvaPref = tipoProvaPref;
    }
}
