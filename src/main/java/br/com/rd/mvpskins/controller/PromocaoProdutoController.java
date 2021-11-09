package br.com.rd.mvpskins.controller;

import br.com.rd.mvpskins.model.dto.PromocaoProdutoDTO;
import br.com.rd.mvpskins.service.PromocaoProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productpromotion")
public class PromocaoProdutoController {
    @Autowired
    PromocaoProdutoService promocaoProdutoService;

    @PostMapping
    @ResponseBody
    public PromocaoProdutoDTO createPromotion(@RequestBody PromocaoProdutoDTO promocaoProduto) {
        return promocaoProdutoService.createPromotion(promocaoProduto);
    }

    @GetMapping
    public List<PromocaoProdutoDTO> searchAll() {
        return promocaoProdutoService.searchAll();
    }

    @GetMapping("/{idProduto}/{idPromocao}")
    public PromocaoProdutoDTO searchById(@PathVariable("idProduto") Long idProduto, @PathVariable("idPromocao") Long idPromocao) {
        return promocaoProdutoService.searchById(idProduto, idPromocao);
    }

    @PutMapping ("/{idProduto}/{idPromocao}")
    @ResponseBody
    public PromocaoProdutoDTO updatePromotion(@RequestBody PromocaoProdutoDTO promocaoProdutoDTO, @PathVariable("idProduto")Long idProduto,@PathVariable("idPromocao") Long idPromocao){
        return promocaoProdutoService.updatePromotion(promocaoProdutoDTO, idProduto,idPromocao);
    }

    @DeleteMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable("idPromocao")Long idPromocao, @PathVariable("idProduto")Long idProduto){
        promocaoProdutoService.delete(idProduto, idPromocao);
    }
}
