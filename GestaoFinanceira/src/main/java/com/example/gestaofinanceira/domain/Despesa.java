package com.example.gestaofinanceira.domain;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "DESPESA")
public class Despesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, max = 120)
    private String descricao;
    @NotNull
    private Double valor;
    @NotNull
    private Date data;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Despesa() {
    }

    public Despesa(Long id, String descricao, Double valor, Date data, Categoria categoria) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.categoria = categoria;
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
