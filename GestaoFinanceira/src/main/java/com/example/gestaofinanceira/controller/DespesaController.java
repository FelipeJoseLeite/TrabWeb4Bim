package com.example.gestaofinanceira.controller;

import com.example.gestaofinanceira.domain.Categoria;
import com.example.gestaofinanceira.domain.Despesa;
import com.example.gestaofinanceira.service.CategoriaService;
import com.example.gestaofinanceira.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/despesa")
public class DespesaController {

    @Autowired
    private DespesaService despesaService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String listarDespesas(Model model) {
        List<Despesa> despesa = despesaService.listarDespesas();
        model.addAttribute("despesas", despesa);
        return "cadastroDespesa";
    }

    @GetMapping("/nova")
    public String novaDespesaForm(Model model) {
        model.addAttribute("despesa", new Despesa());
        return "cadastroDespesa";
    }

    @PostMapping("/nova")
    public String salvarNovaDespesa(@ModelAttribute("despesa") Despesa despesa) {
        despesaService.salvarDespesa(despesa);
        return "redirect:/cadastroDespesa";
    }

    @GetMapping("/editar/{id}")
    public String editarDespesaForm(@PathVariable Long id, Model model) {
        Despesa despesa = despesaService.buscarDespesaPorId(id);
        model.addAttribute("despesa", despesa);
        return "cadastroDespesa";
    }

    @PostMapping("/editar")
    public String salvarDespesaEditada(@ModelAttribute("despesa") Despesa despesa) {
        despesaService.salvarDespesa(despesa);
        return "redirect:/cadastroDespesa";
    }

    @GetMapping("/excluir/{id}")
    public String excluirDespesa(@PathVariable Long id) {
        despesaService.excluirDespesa(id);
        return "redirect:/cadastroDespesa";
    }

    @GetMapping("/cadastro-receita")
    public String exibirFormularioReceita(Model model) {
        List<Categoria> categorias = categoriaService.listarCategorias();
        model.addAttribute("categorias", categorias);
        return "cadastroDespesa";
    }

}
