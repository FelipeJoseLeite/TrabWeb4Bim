package com.example.gestaofinanceira.repository;

import com.example.gestaofinanceira.domain.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    @Query
    public List<Despesa> findAllByOrderByIdAsc();

}
