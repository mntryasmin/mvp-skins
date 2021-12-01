package br.com.rd.mvpskins.controller;

import br.com.rd.mvpskins.model.dto.EnderecoCobrancaDTO;
import br.com.rd.mvpskins.model.dto.PedidoDTO;
import br.com.rd.mvpskins.service.EnderecoCobrancaService;
import br.com.rd.mvpskins.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/billing-address")
public class EnderecoCobrancaController {

    @Autowired
    PedidoService pedidoService;

    @Autowired
    EnderecoCobrancaService enderecoCobrancaService;


    //  ---------------------> CRIAR
    @PostMapping
    @ResponseBody
    public EnderecoCobrancaDTO createBillingAdress(@RequestBody EnderecoCobrancaDTO enderecoCobrancaDTO) {
        try {
            return enderecoCobrancaService.createBillingAddress(enderecoCobrancaDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //  ---------------------> BUSCAR
    //TODOS
    @GetMapping
    public List<EnderecoCobrancaDTO> searchAll() {
        return enderecoCobrancaService.searchAll();
    }

    //ENDEREÇO POR ID
    @GetMapping("/{id}")
    public EnderecoCobrancaDTO searchBillingAddressByRequestId(@PathVariable("id") Long id) {
        return enderecoCobrancaService.searchBillingAddressByRequestId(id);
    }

    //ENDEREÇO POR PEDIDO
    @GetMapping("/request/{id}")
    public EnderecoCobrancaDTO searchAdressByRequest(@PathVariable("id") Long idPedido) {
        return enderecoCobrancaService.searchAdressByRequest(idPedido);
    }

    //  ---------------------> ATUALIZAR
    @PutMapping("/{id}")
    @ResponseBody
    public EnderecoCobrancaDTO updateBillingAddressById(@RequestBody EnderecoCobrancaDTO enderecoCobrancaDTO, @PathVariable("id") Long id) {
        return enderecoCobrancaService.updateBillingAddressById(enderecoCobrancaDTO, id);
    }

    //  ---------------------> DELETAR
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deleteAdressById(@PathVariable("id") Long id) {
        enderecoCobrancaService.deleteAdressById(id);
    }
}