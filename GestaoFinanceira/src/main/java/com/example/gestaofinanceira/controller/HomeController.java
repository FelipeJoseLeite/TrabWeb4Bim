package com.example.gestaofinanceira.controller;
import com.example.gestaofinanceira.domain.Lancamento;
import com.example.gestaofinanceira.service.DespesaService;
import com.example.gestaofinanceira.service.LancamentoService;
import com.example.gestaofinanceira.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private LancamentoService lancamentoService;

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private DespesaService despesaService;

    @GetMapping
    public String home(Model model) {

        ModelAndView modelAndView = new ModelAndView("home");

        Double receitaTotalReal = receitaService. calculaTotalReceita();
        Double despesaTotalReal = despesaService.calculaTotalDespesa();
        Double total = receitaTotalReal - despesaTotalReal;

        model.addAttribute("receitaTotal", receitaTotalReal);
        model.addAttribute("despesaTotal", despesaTotalReal);
        model.addAttribute("totalLiquido", total);
        model.addAttribute("lancamentoDespesaList", despesaService.listUltimosLancamentos());
        model.addAttribute("lancamentoReceitaList", receitaService.listUltimosLancamentos());

        return "home";
    }

}
