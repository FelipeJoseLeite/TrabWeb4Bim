package com.example.gestaofinanceira.repository;

import com.example.gestaofinanceira.domain.Lancamento;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LancamentoRepository {

    List<Lancamento> findByDataBetweenAndTipo(LocalDate dataInicio, LocalDate dataFim, String tipo);
    List<Lancamento> findByDataBetween(LocalDate dataInicio, LocalDate dataFim);
    List<Lancamento> findByTipo(String tipo);
    List<Lancamento> findAll();
}
