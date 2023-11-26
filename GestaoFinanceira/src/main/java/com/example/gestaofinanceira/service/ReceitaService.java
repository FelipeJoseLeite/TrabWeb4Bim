package com.example.gestaofinanceira.service;

import com.example.gestaofinanceira.domain.Receita;
import com.example.gestaofinanceira.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    public List<Receita> listarReceitas() {
        return receitaRepository.findAll();
    }

    public void salvarReceita(Receita receita) {
        receitaRepository.save(receita);
    }

    public Receita buscarReceitaPorId(Long id) {
        return receitaRepository.findById(id).orElse(null);
    }

    public void excluirReceita(Long id) {
        receitaRepository.deleteById(id);
    }

}
