package com.example.finanquest;

public class Conquista {
    private String titulo;
    private String descricao;
    private boolean desbloqueada;

    public Conquista(String titulo, String descricao, boolean desbloqueada) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.desbloqueada = desbloqueada;
    }

    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public boolean isDesbloqueada() { return desbloqueada; }
}
