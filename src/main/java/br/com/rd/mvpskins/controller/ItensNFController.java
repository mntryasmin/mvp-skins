package br.com.rd.mvpskins.controller;

import br.com.rd.mvpskins.model.dto.ItensNFDTO;
import br.com.rd.mvpskins.service.ItensNFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens-nf")
public class ItensNFController {

    @Autowired
    ItensNFService itensNFService;

    //  ---------------------> CRIAR
    @PostMapping
    @ResponseBody
    public ItensNFDTO create(@RequestBody ItensNFDTO itensNFDTO) {
        return itensNFService.create(itensNFDTO);
    }

    //  ---------------------> BUSCAR
    //TODOS OS ITENS NF
    @GetMapping
    public List<ItensNFDTO> searchAll() {
        return itensNFService.searchAll();
    }

    //UM ITEM NF POR ID
    @GetMapping("/{idProduto}/{idNF}")
    public ItensNFDTO searchID(@PathVariable("idProduto") Long idProduto, @PathVariable("idNF") Long idNF) {
        return itensNFService.searchID(idProduto, idNF);
    }

    //  ---------------------> ATUALIZAR
    @PutMapping("/{idProduto}/{idNF}")
    @ResponseBody
    public ItensNFDTO update(@RequestBody ItensNFDTO itensNFDTO, @PathVariable("idProduto") Long idProduto, @PathVariable("idNF") Long idNF) {
        return itensNFService.update(itensNFDTO, idProduto, idNF);
    }

    //  ---------------------> DELETAR
    @DeleteMapping("/{idProduto}/{idNF}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable("idProduto") Long idProduto, @PathVariable("idNF") Long idNF) {
        itensNFService.delete(idProduto, idNF);
    }
}