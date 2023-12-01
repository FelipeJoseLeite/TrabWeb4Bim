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

    public List<Receita> listaReceita() {
        return receitaRepository.findAll();
    }

    public void insert(Receita receita) {
        receitaRepository.saveAndFlush(receita);
    }

    public Receita findById(Long id){
        return receitaRepository.findById(id).get();
    }

    public List<Receita> listAll() {
        return receitaRepository.findAllByOrderByIdAsc();
    }

    public double calculaTotalReceita(){

        List<Receita> receitas = listAll();

        double total = 0;

        for(Receita receita: receitas){

            total = total + receita.getValor();
        }

        return total;

    }

    public List<Receita> listUltimosLancamentos() {
        return receitaRepository.findTop10ByOrderByIdDesc();
    }

}
