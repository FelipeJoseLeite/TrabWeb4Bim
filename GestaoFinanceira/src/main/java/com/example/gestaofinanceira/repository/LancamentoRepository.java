package com.example.gestaofinanceira.repository;

import com.example.gestaofinanceira.domain.Lancamento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LancamentoRepository {

    List<Lancamento> findByDataBetweenAndTipo(LocalDate dataInicio, LocalDate dataFim, String tipo);
    List<Lancamento> findByDataBetween(LocalDate dataInicio, LocalDate dataFim);
    List<Lancamento> findByTipo(String tipo);
    List<Lancamento> findAll();

    @Query("SELECT SUM(l.valor) FROM Lancamento l WHERE l.tipo = 'RECEITA'")
    Double calcularTotalReceita();

    @Query("SELECT SUM(l.valor) FROM Lancamento l WHERE l.tipo = 'DESPESA'")
    Double calcularTotalDespesa();

    @Query(value = "SELECT * FROM lancamento ORDER BY data DESC LIMIT ?1", nativeQuery = true)
    List<Lancamento> buscarUltimosLancamentos(int quantidade);
}
