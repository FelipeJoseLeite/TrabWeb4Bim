package com.example.gestaofinanceira.controller;

import com.example.gestaofinanceira.domain.Categoria;
import com.example.gestaofinanceira.domain.Despesa;
import com.example.gestaofinanceira.service.CategoriaService;
import com.example.gestaofinanceira.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/despesa")
public class DespesaController {

    @Autowired
    private DespesaService despesaService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/cadastroDespesa")
    public String mostrarFormulario(Model model) {
        model.addAttribute("despesa", new Despesa());
        return "cadastroDespesa";
    }

    @GetMapping("/listar")
    public String listarDespesas(Model model) {
        List<Despesa> despesa = despesaService.listarDespesas();
        model.addAttribute("despesa", despesa);
        return "cadastroDespesa";
    }

    @PostMapping("/salvar")
    public String salvaDespesa(@ModelAttribute("despesa")Despesa despesa){
        despesaService.salvarDespesa(despesa);
        return "redirect:/cadastroDespesa";
    }

    @GetMapping("/cadastro-receita")
    public String exibirFormularioReceita(Model model) {
        List<Categoria> categorias = categoriaService.listarCategorias();
        model.addAttribute("categorias", categorias);
        return "cadastroDespesa";
    }

}
