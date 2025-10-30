package com.example.paysmart.Models;

public class CategoriaAnalytics {
    private String nome;
    private float porcentagem;

    public CategoriaAnalytics(String nome, float porcentagem) {
        this.nome = nome;
        this.porcentagem = porcentagem;
    }

    public String getNome() {
        return nome;
    }

    public float getPorcentagem() {
        return porcentagem;
    }
}
