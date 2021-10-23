package br.com.rd.ProjetoIntegrador.Controller;

import br.com.rd.ProjetoIntegrador.DTO.ClienteDTO;
import br.com.rd.ProjetoIntegrador.Entity.Cliente;
import br.com.rd.ProjetoIntegrador.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    ClienteService clienteService;
    @PostMapping
    @ResponseBody
    public ClienteDTO createCliente (@RequestBody ClienteDTO cliente){
        return clienteService.createCliente(cliente);
    }

    @GetMapping
    public List<ClienteDTO> searchAll(){
        return clienteService.searchAllCliente();
    }

    @GetMapping("/{idCliente}")
    public ClienteDTO searchClienteById(@PathVariable("idCliente") Long codigoCliente){
        return clienteService.searchClienteById(codigoCliente);
    }

    @PutMapping("/{idCliente}")
    public ClienteDTO updateCliente (@RequestBody ClienteDTO clienteDTO,@PathVariable("idCliente")Long idCliente){
        return clienteService.updateCliente(clienteDTO,idCliente);

    }

    @DeleteMapping("/{idCliente}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deleteCliente(@PathVariable("idCliente")Long codigoCliente){
        clienteService.deleteCliente(codigoCliente);
    }

}
