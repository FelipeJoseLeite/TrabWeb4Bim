package com.example.gestaofinanceira.controller;

import com.example.gestaofinanceira.domain.Lancamento;
import com.example.gestaofinanceira.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/lancamento")
public class LancamentoController {

    @Autowired
    private LancamentoService lancamentoService;

    @GetMapping("/filtro")
    public String consultarLancamentos(
            @RequestParam(required = false) LocalDate dataInicio,
            @RequestParam(required = false) LocalDate dataFim,
            @RequestParam(required = false) String tipo,
            Model model) {

        List<Lancamento> lancamento = lancamentoService.consultarLancamentos(dataInicio, dataFim, tipo);
        model.addAttribute("lancamentoList", lancamento);

        return "redirect:/lancamento";
    }

}
