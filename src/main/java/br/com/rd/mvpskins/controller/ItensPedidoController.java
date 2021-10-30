package br.com.rd.mvpskins.controller;

import br.com.rd.mvpskins.model.dto.ItensPedidoDTO;
import br.com.rd.mvpskins.service.ItensPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens-pedido")
public class ItensPedidoController {

    @Autowired
    ItensPedidoService itensPedidoService;

    //  ---------------------> CRIAR
    @PostMapping
    @ResponseBody
    public ItensPedidoDTO create (@RequestBody ItensPedidoDTO itensPedidoDTO) {
        return itensPedidoService.create(itensPedidoDTO);
    }

    //  ---------------------> BUSCAR
    //TODOS OS ITENS DE PEDIDOS
    @GetMapping
    public List<ItensPedidoDTO> searchAll() {
        return itensPedidoService.searchAll();
    }

    //UM ITEM DE PEDIDO POR ID
    @GetMapping("/{idProduct}/{idRequest}")
    public ItensPedidoDTO searchID(@PathVariable("idProduct") Long idProduct, @PathVariable("idRequest") Long idRequest) {
        return itensPedidoService.searchID(idProduct, idRequest);
    }

    //  ---------------------> ATUALIZAR
    @PutMapping("/{idProduct}/{idRequest}")
    @ResponseBody
    public ItensPedidoDTO update(@RequestBody ItensPedidoDTO itensPedidoDTO, @PathVariable("idProduct") Long idProduct, @PathVariable("idRequest") Long idRequest) {
        return itensPedidoService.update(itensPedidoDTO, idProduct, idRequest);
    }

    //  ---------------------> DELETAR
    @DeleteMapping("/{idProduct}/{idRequest}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable("idProduct") Long idProduct, @PathVariable("idRequest") Long idRequest) {
        itensPedidoService.delete(idProduct, idRequest);
    }
}