package com.example.paysmart.Models;

public class Meta {
    private String nome;
    private String descricao;
    private int valorAtual;
    private int valorMeta;
    private int cor;
    private int iconeId;

    public Meta(String nome, String descricao, int valorAtual, int valorMeta, int cor, int iconeId) {
        this.nome = nome;
        this.descricao = descricao;
        this.valorAtual = valorAtual;
        this.valorMeta = valorMeta;
        this.cor = cor;
        this.iconeId = iconeId;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getValorAtual() {
        return valorAtual;
    }

    public int getValorMeta() {
        return valorMeta;
    }

    public int getCor() {
        return cor;
    }

    public int getIconeId() {
        return iconeId;
    }

    public void setValorAtual(int valorAtual) {
        this.valorAtual = valorAtual;
    }

    public void setValorMeta(int valorMeta) {
        this.valorMeta = valorMeta;
    }

    public void setCor(int cor) {
        this.cor = cor;
    }

    public void setIconeId(int iconeId) {
        this.iconeId = iconeId;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
