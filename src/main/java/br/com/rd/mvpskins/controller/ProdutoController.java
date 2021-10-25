package br.com.rd.mvpskins.controller;

import br.com.rd.mvpskins.model.dto.ProdutoDTO;
import br.com.rd.mvpskins.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping
    @ResponseBody
    public List<ProdutoDTO> getList(){
        return produtoService.getList();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ProdutoDTO getProductById(@PathVariable("id") Long id){
        return produtoService.getProductById(id);
    }
}
