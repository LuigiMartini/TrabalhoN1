package com.luigi.trabalhon1;

public class Historia {
    public int id;
    public String nome;
    public String autor;
    public int ano;
    public  String foto;

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Historia() { }

    public Historia(String nome, String autor, int ano, String foto) {
        this.nome = nome;
        this.autor = autor;
        this.ano = ano;
        this.foto = foto;


    }

    @Override
    public String toString() {
        return nome + " - " + autor + ", " + ano + " anos" + foto;
    }
}
