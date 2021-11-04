package br.com.rd.mvpskins.controller;

import br.com.rd.mvpskins.model.dto.PromocaoProdutoDTO;
import br.com.rd.mvpskins.service.PromocaoProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productpromotion")
public class PromocaoProdutoController {
    @Autowired
    PromocaoProdutoService promocaoProdutoService;
    @PostMapping
    @ResponseBody
    public PromocaoProdutoDTO createPromotion(@RequestBody PromocaoProdutoDTO promocaoProduto){
        return promocaoProdutoService.createPromotion(promocaoProduto);
    }

    @GetMapping
    public List<PromocaoProdutoDTO> searchAll(){
        return promocaoProdutoService.searchAll();
    }





}
