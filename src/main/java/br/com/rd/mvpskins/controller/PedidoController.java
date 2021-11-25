package br.com.rd.mvpskins.controller;

import br.com.rd.mvpskins.model.dto.PedidoDTO;
import br.com.rd.mvpskins.service.JwtUserDetailsService;
import br.com.rd.mvpskins.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public PedidoDTO createOrder(@RequestBody PedidoDTO pedidoDTO) {
        try {
            return pedidoService.createOrder(pedidoDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //  ---------------------> BUSCAR
    //TODOS OS PEDIDOS
    @GetMapping
    public List<PedidoDTO> searchAllOrders() {
        return pedidoService.searchAll();
    }

    //UM PEDIDO POR ID
    @GetMapping("/{id}")
    public PedidoDTO searchOrderByID(@PathVariable("id") Long id) {
        return pedidoService.searchOrderById(id);
    }

    //TODOS OS PEDIDOS DE UM CLIENTE
    @GetMapping("/order-history/{idCliente}")
    public List<PedidoDTO> searchPedidosCliente(@PathVariable("idCliente") Long idCliente) {
        return pedidoService.searchAllClientOrders(idCliente);
    }

    //  ---------------------> ATUALIZAR
    @PutMapping("/{id}")
    @ResponseBody
    public PedidoDTO updateOrderById(@RequestBody PedidoDTO pedidoDTO, @PathVariable("id") Long id) {
        return pedidoService.updateOrderById(pedidoDTO, id);
    }

    //  ---------------------> DELETAR
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deleteOrderById(@PathVariable("id") Long id) {
        pedidoService.deleteOrderById(id);
    }

    //  ---------------------> SUCESSO DE COMPRAS
    @PostMapping("/email/{idPedido}")
    public void purchaseSuccess(@PathVariable("idPedido") Long idPedido){
        try{
            pedidoService.sendEmailPurchaseSuccess(idPedido);
        } catch(UsernameNotFoundException u){
            u.printStackTrace();
        }
    }

}