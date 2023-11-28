package com.example.gestaofinanceira.service;

import com.example.gestaofinanceira.domain.Categoria;
import com.example.gestaofinanceira.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    public void salvarCategoria(Categoria categoria) {
        categoriaRepository.saveAndFlush(categoria);
    }

    public Categoria buscarCategoriaPorId(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    public void excluirCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
}
