package com.example.gestaofinanceira.controller;

import com.example.gestaofinanceira.domain.Despesa;
import com.example.gestaofinanceira.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/despesas")
public class DespesaController {

    @Autowired
    private DespesaService despesaService;

    @GetMapping
    public String listarDespesas(Model model) {
        List<Despesa> despesas = despesaService.listarDespesas();
        model.addAttribute("despesas", despesas);
        return "despesas/listagem";
    }

    @GetMapping("/nova")
    public String novaDespesaForm(Model model) {
        model.addAttribute("despesa", new Despesa());
        return "despesas/formulario";
    }

    @PostMapping("/nova")
    public String salvarNovaDespesa(@ModelAttribute("despesa") Despesa despesa) {
        despesaService.salvarDespesa(despesa);
        return "redirect:/despesas";
    }

    @GetMapping("/editar/{id}")
    public String editarDespesaForm(@PathVariable Long id, Model model) {
        Despesa despesa = despesaService.buscarDespesaPorId(id);
        model.addAttribute("despesa", despesa);
        return "despesas/formulario";
    }

    @PostMapping("/editar")
    public String salvarDespesaEditada(@ModelAttribute("despesa") Despesa despesa) {
        despesaService.salvarDespesa(despesa);
        return "redirect:/despesas";
    }

    @GetMapping("/excluir/{id}")
    public String excluirDespesa(@PathVariable Long id) {
        despesaService.excluirDespesa(id);
        return "redirect:/despesas";
    }

}
