package com.example.controlefinanceiro.service;

import com.example.controlefinanceiro.domain.Despesa;
import com.example.controlefinanceiro.repository.DespesaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DespesaService {

    public void insert(Despesa despesa) {
        DespesaRepository.saveAndFlush(despesa);
    }

}
