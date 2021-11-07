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
        try {
            return itensPedidoService.create(itensPedidoDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //  ---------------------> BUSCAR
    //TODOS OS ITENS DE PEDIDOS
    @GetMapping
    public List<ItensPedidoDTO> searchAll() {
        return itensPedidoService.searchAll();
    }

    //UM ITEM DE PEDIDO POR ID
    @GetMapping("/{idProduto}/{idPedido}")
    public ItensPedidoDTO searchID(@PathVariable("idProduto") Long idProduto, @PathVariable("idPedido") Long idPedido) {
        return itensPedidoService.searchOrderItemById(idProduto, idPedido);
    }

    // PRODUTOS DE UM PEDIDO
    @GetMapping("/{idPedido}")
    public List<ItensPedidoDTO> searchOrderProduct(@PathVariable("idPedido") Long idPedido) {
        return itensPedidoService.searchItemsByOrder(idPedido);
    }

    //TOP 12 PRODUTOS MAIS VENDIDOS
    @GetMapping("/topProdutos")
    public List<ItensPedidoDTO> searchTopSellers() {

        return itensPedidoService.searchTopSellers();
    }

    //TOP 12 FACAS MAIS VENDIDAS
    @GetMapping("/topFacas")
    public List<ItensPedidoDTO> searchTopKnifes() {

        return itensPedidoService.searchTopKnife();
    }

    //TOP 12 ARMAS MAIS VENDIDAS
    @GetMapping("/topArmas")
    public List<ItensPedidoDTO> searchArmasMaisVendidas() {

        return itensPedidoService.searchTopGuns();
    }

    //  ---------------------> ATUALIZAR
    @PutMapping("/{idProduto}/{idRequest}")
    @ResponseBody
    public ItensPedidoDTO update(@RequestBody ItensPedidoDTO itensPedidoDTO, @PathVariable("idProduto") Long idProduto, @PathVariable("idPedido") Long idPedido) {
        return itensPedidoService.update(itensPedidoDTO, idProduto, idPedido);
    }

    //  ---------------------> DELETAR
    @DeleteMapping("/{idProduto}/{idPedido}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable("idProduto") Long idProduto, @PathVariable("idPedido") Long idPedido) {
        itensPedidoService.delete(idProduto, idPedido);
    }
}