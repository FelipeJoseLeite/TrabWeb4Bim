package com.example.gestaofinanceira.service;

import com.example.gestaofinanceira.domain.Despesa;
import com.example.gestaofinanceira.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    public List<Despesa> listarDespesas() {
        return despesaRepository.findAll();
    }

    public void salvarDespesa(Despesa despesa) {
        despesaRepository.saveAndFlush(despesa);
    }

}
