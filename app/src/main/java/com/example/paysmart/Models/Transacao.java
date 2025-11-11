package com.example.paysmart.Models;

import java.io.Serializable;

public class Transacao implements Serializable {
    private String titulo;
    private String tipo;
    private String quantia;
    private String data;

    public Transacao(String titulo, String tipo, String quantia, String data) {
        this.titulo = titulo;
        this.tipo = tipo;
        this.quantia = quantia;
        this.data = data;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getQuantia() {
        return quantia;
    }

    public String getData() {
        return data;
    }
}
