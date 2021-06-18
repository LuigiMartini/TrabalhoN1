package com.luigi.trabalhon1;

public class Historia {
    public int id;
    public String nome;
    public String autor;
    public int ano;

    public Historia() { }

    public Historia(String nome, String autor, int ano) {
        this.nome = nome;
        this.autor = autor;
        this.ano = ano;
    }

    public Historia(int id, String nome, String autor, int ano) {
        this.id = id;
        this.nome = nome;
        this.autor = autor;
        this.ano = ano;
    }

    @Override
    public String toString() {
        return nome + " - " + autor + ", " + ano + " anos";
    }
}
