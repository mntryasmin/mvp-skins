package br.com.rd.mvpskins.controller;

import br.com.rd.mvpskins.model.dto.PedidoDTO;
import br.com.rd.mvpskins.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    //  ---------------------> CRIAR
    @PostMapping
    @ResponseBody
    public PedidoDTO create (@RequestBody PedidoDTO pedidoDTO) {
        return pedidoService.create(pedidoDTO);
    }

    //  ---------------------> BUSCAR
    //TODOS OS PEDIDOS
    @GetMapping
    public List<PedidoDTO> searchAll() {
        return pedidoService.searchAll();
    }

    //TODAS AS NF'S DE UM CLIENTE
    @GetMapping("/historico/{idCliente}")
    public List<PedidoDTO> searchProdutosCliente(@PathVariable("idCliente") Long idCliente) {
        return pedidoService.searchProdutosCliente(idCliente);
    }

    //UM PEDIDO POR ID
    @GetMapping("/{id}")
    public PedidoDTO searchID(@PathVariable("id") Long id) {
        return pedidoService.searchID(id);
    }

    //  ---------------------> ATUALIZAR
    @PutMapping("/{id}")
    @ResponseBody
    public PedidoDTO update(@RequestBody PedidoDTO pedidoDTO, @PathVariable("id") Long id) {
        return pedidoService.update(pedidoDTO, id);
    }

    //  ---------------------> DELETAR
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Long id) {
        pedidoService.delete(id);
    }
}