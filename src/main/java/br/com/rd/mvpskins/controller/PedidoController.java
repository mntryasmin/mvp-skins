package br.com.rd.mvpskins.controller;

import br.com.rd.mvpskins.model.dto.PedidoDTO;
import br.com.rd.mvpskins.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    //  ---------------------> CRIAR
    @PostMapping
    @ResponseBody
    public PedidoDTO criar (@RequestBody PedidoDTO pedido) {
        return pedidoService.criar(pedido);
    }

    //  ---------------------> BUSCAR
    //TODOS OS PEDIDOS
    @GetMapping
    public List<PedidoDTO> procurarTodos() {
        return pedidoService.procurarTodos();
    }

    //UM PEDIDO POR ID
    @GetMapping("/{id}")
    public PedidoDTO procurarId(@PathVariable("id") Long id) {
        return pedidoService.procurarId(id);
    }

    //  ---------------------> ATUALIZAR
    @PutMapping("/{id}")
    @ResponseBody
    public PedidoDTO atualizar(@RequestBody PedidoDTO pedido, @PathVariable("id") Long id) {
        return pedidoService.atualizar(pedido, id);
    }

    //  ---------------------> DELETAR
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deletar(@PathVariable("id") Long id) {
        pedidoService.deletar(id);
    }
}