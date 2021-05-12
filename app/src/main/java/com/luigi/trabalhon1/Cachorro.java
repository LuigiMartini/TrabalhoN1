package com.luigi.trabalhon1;

public class Cachorro {
    public int id;
    public String nome;
    public String raca;
    public int idade;

    public Cachorro() { }

    public Cachorro(String nome, String raca, int idade) {
        this.nome = nome;
        this.raca = raca;
        this.idade = idade;
    }

    public Cachorro(int id, String nome, String raca, int idade) {
        this.id = id;
        this.nome = nome;
        this.raca = raca;
        this.idade = idade;
    }

    @Override
    public String toString() {
        return nome + " - " + raca + ", " + idade + " anos";
    }
}
