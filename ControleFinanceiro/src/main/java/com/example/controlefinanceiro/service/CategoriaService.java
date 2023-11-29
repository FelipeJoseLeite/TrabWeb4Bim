package com.example.controlefinanceiro.service;

import com.example.controlefinanceiro.domain.Categoria;
import com.example.controlefinanceiro.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    public void insert(Categoria categoria) {
        categoriaRepository.saveAndFlush(categoria);
    }

    public List<Categoria> listAll() {
        return categoriaRepository.findAllByOrderByIdAsc();
    }
}
