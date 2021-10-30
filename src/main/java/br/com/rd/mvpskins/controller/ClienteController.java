package br.com.rd.mvpskins.controller;

import br.com.rd.mvpskins.config.JwtTokenUtil;
import br.com.rd.mvpskins.model.dto.ClienteDTO;
import br.com.rd.mvpskins.model.entity.Cliente;
import br.com.rd.mvpskins.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/email/{email}")
    public Cliente searchEmail(@PathVariable("email") String email){
        return clienteService.searchClienteByEmail(email);
    }

    @GetMapping("/auto")
    public String auto(HttpServletRequest request){
        String auto = request.getHeader("Authorization");
        return auto;
    }

    @PostMapping("/mudar-senha")
    public String mudarSenha(@RequestParam("token")String token, @RequestBody String senha){
        return clienteService.mudarSenha(token, senha);
    }
}
