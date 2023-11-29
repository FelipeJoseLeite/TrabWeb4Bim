package com.example.gestaofinanceira.controller;
import com.example.gestaofinanceira.domain.Lancamento;
import com.example.gestaofinanceira.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private LancamentoService lancamentoService;

    @GetMapping("/")
    public String home(Model model) {

        Double receitaTotal = 0.0;
        Double despesaTotal = 0.0;

        Double receitaTotalReal = lancamentoService.calcularTotalReceita();
        Double despesaTotalReal = lancamentoService.calcularTotalDespesa();

        if (receitaTotalReal != null) {
            receitaTotal = receitaTotalReal;
        }

        if (despesaTotalReal != null) {
            despesaTotal = despesaTotalReal;
        }

        if (receitaTotal != 0 && despesaTotal != 0) {
            Double totalLiquido = receitaTotal - despesaTotal;
            model.addAttribute("totalLiquido", totalLiquido);
        }

        List<Lancamento> ultimosLancamentos = lancamentoService.buscarUltimosLancamentos(10);

        model.addAttribute("receitaTotal", receitaTotal);
        model.addAttribute("despesaTotal", despesaTotal);
        model.addAttribute("ultimosLancamentos", ultimosLancamentos);

        return "home";
    }

}
