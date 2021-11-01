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

    @GetMapping("/search/{s}")
    @ResponseBody
    public List<ProdutoDTO> getListBySearch(@PathVariable("s") String search){
        return produtoService.getListBySearch(search);
    }

    @GetMapping("/category/{id}")
    @ResponseBody
    public List<ProdutoDTO> getListByCategory(@PathVariable("id") Long id){
        return produtoService.getListByCategory(id);
    }

    @GetMapping("/subcategory/{idC}/{idS}")
    @ResponseBody
    public List<ProdutoDTO> getListBySubcategory(@PathVariable("idC")Long idC,
                                                 @PathVariable("idS")Long idS){
        return produtoService.getListBySubcategory(idC, idS);
    }

    @GetMapping("/colection/{id}")
    @ResponseBody
    public List<ProdutoDTO> getListByColection(@PathVariable("id") Long id){
        return produtoService.getListByColection(id);
    }

    @GetMapping("/rarity/{id}")
    @ResponseBody
    public List<ProdutoDTO> getListByRarity(@PathVariable("id")Long id){
        return produtoService.getListByRarity(id);
    }

    @GetMapping("/exterior/{id}")
    @ResponseBody
    public List<ProdutoDTO> getListByExterior(@PathVariable("id")Long id){
        return produtoService.getListByExterior(id);
    }

    @GetMapping("/maxValue/{v}")
    @ResponseBody
    public List<ProdutoDTO> getListByMaxValue(@PathVariable("v")Double v){
        return produtoService.getListByMaxValue(v);
    }

    @GetMapping("/minValue/{v}")
    @ResponseBody
    public List<ProdutoDTO> getListByMinValue(@PathVariable("v")Double v){
        return produtoService.getListByMinValue(v);
    }
}
