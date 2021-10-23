package br.com.rd.mvpskins.controller;

import br.com.rd.mvpskins.model.dto.ItemsRequestDTO;
import br.com.rd.mvpskins.service.ItemsRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items-request")
public class ItemsRequestController {

    @Autowired
    ItemsRequestService itemsRequestService;

    //  ---------------------> CRIAR
    @PostMapping
    @ResponseBody
    public ItemsRequestDTO create (@RequestBody ItemsRequestDTO itemsRequestDTO) {
        return itemsRequestService.create(itemsRequestDTO);
    }

    //  ---------------------> BUSCAR
    //TODOS OS ITENS PEDIDOS
    @GetMapping
    public List<ItemsRequestDTO> searchAll() {
        return itemsRequestService.searchAll();
    }

    //UM ITEM PEDIDO POR ID
    @GetMapping("/{idProduct}/{idRequest}")
    public ItemsRequestDTO searchID(@PathVariable("idProduct") Long idProduct, @PathVariable("idRequest") Long idRequest) {
        return itemsRequestService.searchID(idProduct, idRequest);
    }

    //  ---------------------> ATUALIZAR
    @PutMapping("/{idProduct}/{idRequest}")
    @ResponseBody
    public ItemsRequestDTO update(@RequestBody ItemsRequestDTO itemsRequestDTO, @PathVariable("idProduct") Long idProduct, @PathVariable("idRequest") Long idRequest) {
        return itemsRequestService.update(itemsRequestDTO, idProduct, idRequest);
    }

    //  ---------------------> DELETAR
    @DeleteMapping("/{idProduct}/{idRequest}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable("idProduct") Long idProduct, @PathVariable("idRequest") Long idRequest) {
        itemsRequestService.delete(idProduct, idRequest);
    }
}