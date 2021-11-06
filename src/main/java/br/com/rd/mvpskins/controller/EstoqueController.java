package br.com.rd.mvpskins.controller;

import br.com.rd.mvpskins.model.dto.ProdutoDTO;
import br.com.rd.mvpskins.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
    @Autowired
    EstoqueService estoqueService;

    @GetMapping("/em-estoque")
    public List<ProdutoDTO> getListProductIninventory(){
        return estoqueService.getListProductIninventory();
    }

    @GetMapping("/fora-de-estoque")
    public List<ProdutoDTO> getListProductOutInventory(){
        return estoqueService.getListProductOutInventory();
    }
}
