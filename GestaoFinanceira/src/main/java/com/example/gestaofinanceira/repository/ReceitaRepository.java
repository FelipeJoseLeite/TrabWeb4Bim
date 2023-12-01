package com.example.gestaofinanceira.repository;

import com.example.gestaofinanceira.domain.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    @Query
    public List<Receita> findAllByOrderByIdAsc();

    @Query
    List<Receita> findTop10ByOrderByIdDesc();

    @Query("SELECT R FROM Receita R WHERE " +
            "R.data BETWEEN :dataInicio AND :dataFim")
    List<Receita> listFilter(
            @Param("dataInicio") Date dataInicio,
            @Param("dataFim") Date dataFim
    );

}