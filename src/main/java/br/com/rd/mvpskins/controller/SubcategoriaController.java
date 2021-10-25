package br.com.rd.mvpskins.controller;

import br.com.rd.mvpskins.model.dto.SubcategoriaDTO;
import br.com.rd.mvpskins.service.SubcategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subcategoria")
public class SubcategoriaController {

    @Autowired
    SubcategoriaService subcategoriaService;

    @GetMapping
    @ResponseBody
    public List<SubcategoriaDTO> getList(){
        return subcategoriaService.getList();
    }

    @GetMapping("/{idC}/{id}")
    @ResponseBody
    public SubcategoriaDTO getSubcategoryById(@PathVariable("idC")Long idC, @PathVariable("id") Long id){
        return subcategoriaService.getSubcategoryById(idC, id);
    }
}
