package br.com.rd.mvpskins.controller;

import br.com.rd.mvpskins.model.dto.CategoriaDTO;
import br.com.rd.mvpskins.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/{id}")
    @ResponseBody
    public CategoriaDTO getCategoryById(@PathVariable("id") Long id){

        return categoriaService.getCategoryById(id);
    }

    @GetMapping
    @ResponseBody
    private List<CategoriaDTO> getList(){
        return categoriaService.getList();
    }
}
