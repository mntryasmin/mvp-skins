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
    @GetMapping("/{idProduto}/{idPedido}")
    public ItensPedidoDTO searchID(@PathVariable("idProduto") Long idProduto, @PathVariable("idPedido") Long idPedido) {
        return itensPedidoService.searchID(idProduto, idPedido);
    }

    // PRODUTOS DE UM PEDIDO
    //UM ITEM DE PEDIDO POR ID
    @GetMapping("/{idPedido}")
    public List<ItensPedidoDTO> searchProdutosPedido(@PathVariable("idPedido") Long idPedido) {
        return itensPedidoService.searchProdutosPedido(idPedido);
    }

    //TOP 12 PRODUTOS MAIS VENDIDOS
    @GetMapping("/topProdutos")
    public List<ItensPedidoDTO> searchProdutosMaisVendidos() {

        return itensPedidoService.searchProdutosMaisVendidos();
    }

    //TOP 12 FACAS MAIS VENDIDAS
    @GetMapping("/topFacas")
    public List<ItensPedidoDTO> searchFacasMaisVendidas() {

        return itensPedidoService.searchFacasMaisVendidas();
    }

    //TOP 12 ARMAS MAIS VENDIDAS
    @GetMapping("/topArmas")
    public List<ItensPedidoDTO> searchArmasMaisVendidas() {

        return itensPedidoService.searchArmasMaisVendidas();
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