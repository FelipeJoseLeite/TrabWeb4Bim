package com.example.gestaofinanceira.controller;

import com.example.gestaofinanceira.domain.Receita;
import com.example.gestaofinanceira.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @GetMapping
    public String listarReceitas(Model model) {
        List<Receita> receitas = receitaService.listarReceitas();
        model.addAttribute("receitas", receitas);
        return "receitas/listagem";
    }

    @GetMapping("/nova")
    public String novaReceitaForm(Model model){
        model.addAttribute("receita", new Receita());
        return "receita/formulario";
    }

    @PostMapping("/nova")
    public String salvarNovaReceita(@ModelAttribute("receita") Receita receita) {
        receitaService.salvarReceita(receita);
        return "redirect:/receitas";
    }

    @GetMapping("/editar/{id}")
    public String editarReceitaForm(@PathVariable Long id, Model model) {
        Receita receita = receitaService.buscarReceitaPorId(id);
        model.addAttribute("receita", receita);
        return "receitas/formulario";
    }

    @PostMapping("/editar")
    public String salvarReceitaEditada(@ModelAttribute("receita") Receita receita) {
        receitaService.salvarReceita(receita);
        return "redirect:/receitas";
    }

    @GetMapping("/excluir/{id}")
    public String excluirReceita(@PathVariable Long id) {
        receitaService.excluirReceita(id);
        return "redirect:/receitas";
    }

}
