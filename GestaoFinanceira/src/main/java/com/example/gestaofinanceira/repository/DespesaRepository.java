package com.example.gestaofinanceira.repository;

import com.example.gestaofinanceira.domain.Despesa;
import com.example.gestaofinanceira.domain.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    @Query
    public List<Despesa> findAllByOrderByIdAsc();
    @Query
    List<Despesa> findTop10ByOrderByIdDesc();

    @Query("SELECT D FROM Despesa D WHERE " +
            "D.data BETWEEN :dataInicio AND :dataFim")
    List<Despesa> listFilter(
            @Param("dataInicio") Date dataInicio,
            @Param("dataFim") Date dataFim
    );

}

