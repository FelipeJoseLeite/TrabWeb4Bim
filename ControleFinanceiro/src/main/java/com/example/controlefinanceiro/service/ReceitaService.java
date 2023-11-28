package com.example.controlefinanceiro.service;

import com.example.controlefinanceiro.domain.Receita;
import com.example.controlefinanceiro.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    public void insert(Receita receita) {
        receitaRepository.saveAndFlush(receita);
    }

    public void delete(Long id) {
        receitaRepository.deleteById(id);
    }


}
