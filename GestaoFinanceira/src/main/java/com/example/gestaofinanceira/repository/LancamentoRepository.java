package com.example.gestaofinanceira.repository;

import com.example.gestaofinanceira.domain.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

    @Query
    List<Lancamento> findByDataBetweenAndTipo(Date dataInicio, Date dataFim, String tipo);
    @Query
    List<Lancamento> findByDataBetween(Date dataInicio, Date dataFim);
    @Query
    List<Lancamento> findByTipo(String tipo);

    @Query("SELECT SUM(l.valor) FROM Lancamento l WHERE l.tipo = 'RECEITA'")
    Double calcularTotalReceita();

    @Query("SELECT SUM(l.valor) FROM Lancamento l WHERE l.tipo = 'DESPESA'")
    Double calcularTotalDespesa();

    @Query(value = "SELECT * FROM lancamento ORDER BY data DESC LIMIT ?1", nativeQuery = true)
    List<Lancamento> buscarUltimosLancamentos(int quantidade);
}
