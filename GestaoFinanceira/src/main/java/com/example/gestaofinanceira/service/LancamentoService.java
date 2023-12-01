package com.example.gestaofinanceira.service;

import com.example.gestaofinanceira.domain.Despesa;
import com.example.gestaofinanceira.domain.Lancamento;
import com.example.gestaofinanceira.domain.Receita;
import com.example.gestaofinanceira.repository.DespesaRepository;
import com.example.gestaofinanceira.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Service
public class LancamentoService {


    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private DespesaService despesaService;

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private DespesaRepository despesaRepository;

    public List<Lancamento> findAll() {

        List<Receita> receitas = receitaService.listAll();
        List<Despesa> despesas = despesaService.listAll();

        List<Lancamento> lancamentos = new ArrayList<>();

        for (Receita receita : receitas) {
            Lancamento lancamento = new Lancamento();
            lancamento.setData(receita.getData());
            lancamento.setId(receita.getId());
            lancamento.setDescricao(receita.getDescricao());
            lancamento.setTipo("Receita");
            lancamento.setValor(receita.getValor());
            lancamentos.add(lancamento);
        }

        for (Despesa despesa : despesas) {
            Lancamento lancamento = new Lancamento();
            lancamento.setData(despesa.getData());
            lancamento.setId(despesa.getId());
            lancamento.setDescricao(despesa.getDescricao());
            lancamento.setTipo("Despesa");
            lancamento.setValor(despesa.getValor());
            lancamentos.add(lancamento);
        }

        return lancamentos;

    }


    public List<Lancamento> findFiltros(String dataInicial, String dataFinal, String tipoLancamento) {

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        List<Lancamento> lancamentoList = new ArrayList<>();

        try {
            Date dataInicialFormat = null;
            Date dataFinalFormat = null;

            if (dataInicial != null && !dataInicial.isEmpty()) {
                dataInicialFormat = new Date(formato.parse(dataInicial).getTime());
            }

            if (dataFinal != null && !dataFinal.isEmpty()) {
                dataFinalFormat = new Date(formato.parse(dataFinal).getTime());
            }

            List<Receita> receitas = receitaRepository.listFilter(dataInicialFormat, dataFinalFormat);
            List<Despesa> despesas = despesaRepository.listFilter(dataInicialFormat, dataFinalFormat);

            if (tipoLancamento == "SELECIONE" || tipoLancamento == "RECEITA") {
                for (Receita receita : receitas) {
                    Lancamento lancamento = new Lancamento();
                    lancamento.setData(receita.getData());
                    lancamento.setId(receita.getId());
                    lancamento.setDescricao(receita.getDescricao());
                    lancamento.setTipo("Receita");
                    lancamento.setValor(receita.getValor());
                    lancamentoList.add(lancamento);
                }
            }
            if (tipoLancamento == "SELECIONE" || tipoLancamento == "DESPESA") {

                for (Despesa despesa : despesas) {
                    Lancamento lancamento = new Lancamento();
                    lancamento.setData(despesa.getData());
                    lancamento.setId(despesa.getId());
                    lancamento.setDescricao(despesa.getDescricao());
                    lancamento.setTipo("Despesa");
                    lancamento.setValor(despesa.getValor());
                    lancamentoList.add(lancamento);
                }
            }


        } catch (Exception ex) {
            System.out.printf("Erro ao formatar data " + ex.getMessage());
        }

        return lancamentoList;
    }


}
