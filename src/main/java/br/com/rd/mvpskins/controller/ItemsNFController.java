package br.com.rd.mvpskins.controller;

import br.com.rd.mvpskins.model.dto.ItemsNFDTO;
import br.com.rd.mvpskins.service.ItemsNFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items-nf")
public class ItemsNFController {

    @Autowired
    ItemsNFService itemsNFService;

    //  ---------------------> CRIAR
    @PostMapping
    @ResponseBody
    public ItemsNFDTO create (@RequestBody ItemsNFDTO itemsNFDTO) {
        return itemsNFService.create(itemsNFDTO);
    }

    //  ---------------------> BUSCAR
    //TODOS OS ITENS NF
    @GetMapping
    public List<ItemsNFDTO> searchAll() {
        return itemsNFService.searchAll();
    }

    //UM ITEM NF POR ID
    @GetMapping("/{idProduct}/{idNF}")
    public ItemsNFDTO searchID(@PathVariable("idProduct") Long idProduct, @PathVariable("idNF") Long idNF) {
        return itemsNFService.searchID(idProduct, idNF);
    }

    //  ---------------------> ATUALIZAR
    @PutMapping("/{idProduct}/{idNF}")
    @ResponseBody
    public ItemsNFDTO update(@RequestBody ItemsNFDTO itemsNFDTO, @PathVariable("idProduct") Long idProduct, @PathVariable("idNF") Long idNF) {
        return itemsNFService.update(itemsNFDTO, idProduct, idNF);
    }

    //  ---------------------> DELETAR
    @DeleteMapping("/{idProduct}/{idNF}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable("idProduct") Long idProduct, @PathVariable("idNF") Long idNF) {
        itemsNFService.delete(idProduct, idNF);
    }
}