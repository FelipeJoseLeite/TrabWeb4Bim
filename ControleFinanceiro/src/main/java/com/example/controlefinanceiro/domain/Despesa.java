package com.example.controlefinanceiro.domain;

import javax.persistence.*;

@Entity
@Table(name = "DESPESA")
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private String data;
    private String valor;

    public Despesa() {
    }

    public Despesa(Long id, String descricao, String data, String valor) {
        this.id = id;
        this.descricao = descricao;
        this.data = data;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
