package com.example.gestaofinanceira.controller;

import com.example.gestaofinanceira.domain.Categoria;
import com.example.gestaofinanceira.domain.Receita;
import com.example.gestaofinanceira.service.CategoriaService;
import com.example.gestaofinanceira.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/receita")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String listarReceitas(Model model) {
        List<Receita> receita = receitaService.listarReceitas();
        model.addAttribute("receita", receita);
        return "cadastroReceita";
    }

    @GetMapping("/nova")
    public String novaReceitaForm(Model model){
        model.addAttribute("receita", new Receita());
        return "cadastroReceita";
    }

    @PostMapping("/nova")
    public String salvarNovaReceita(@ModelAttribute("receita") Receita receita) {
        receitaService.salvarReceita(receita);
        return "redirect:/receita";
    }

    @GetMapping("/editar/{id}")
    public String editarReceitaForm(@PathVariable Long id, Model model) {
        Receita receita = receitaService.buscarReceitaPorId(id);
        model.addAttribute("receita", receita);
        return "cadastroReceita";
    }

    @PostMapping("/editar")
    public String salvarReceitaEditada(@ModelAttribute("receita") Receita receita) {
        receitaService.salvarReceita(receita);
        return "redirect:/receita";
    }

    @GetMapping("/excluir/{id}")
    public String excluirReceita(@PathVariable Long id) {
        receitaService.excluirReceita(id);
        return "redirect:/receita";
    }

    @GetMapping("/cadastro-receita")
    public String exibirFormularioReceita(Model model) {
        List<Categoria> categoria = categoriaService.listarCategorias();
        model.addAttribute("categoria", categoria);
        return "cadastroReceita";
    }

}
