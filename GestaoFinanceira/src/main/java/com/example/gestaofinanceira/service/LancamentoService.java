package com.example.gestaofinanceira.service;

import com.example.gestaofinanceira.domain.Lancamento;
import com.example.gestaofinanceira.repository.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    public List<Lancamento> consultarLancamentos(LocalDate dataInicio, LocalDate dataFim, String tipo) {
        if (dataInicio != null && dataFim != null && tipo != null) {
            return lancamentoRepository.findByDataBetweenAndTipo(dataInicio, dataFim, tipo);
        } else if (dataInicio != null && dataFim != null) {
            return lancamentoRepository.findByDataBetween(dataInicio, dataFim);
        } else if (tipo != null) {
            return lancamentoRepository.findByTipo(tipo);
        } else {
            return lancamentoRepository.findAll();
        }
    }

}
