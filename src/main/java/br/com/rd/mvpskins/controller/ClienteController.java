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
    public ClienteDTO createCliente (@RequestBody ClienteDTO client){
        try{
            return clienteService.createClient(client);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping
    public List<ClienteDTO> searchAll(){
        return clienteService.searchAllClients();
    }

    @GetMapping("/{idCliente}")
    public ClienteDTO searchClientById(@PathVariable("idCliente") Long codeClient){
        return clienteService.searchClientById(codeClient);
    }

    @PutMapping("/{idCliente}")
    public ClienteDTO updateClient(@RequestBody ClienteDTO clientDTO,@PathVariable("idCliente")Long idClient){
        return clienteService.updateClient(clientDTO,idClient);
    }

    @GetMapping("/valid-password-client/{senha}/{idCliente}")
    public Boolean validPasswordClient(@PathVariable("senha") String senha, @PathVariable("idCliente") Long idCliente) {
        return clienteService.validPasswordClient(senha, idCliente);
    }

    @DeleteMapping("/{idCliente}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deleteClient(@PathVariable("idCliente")Long codeClient){
        clienteService.deleteClient(codeClient);
    }

    @GetMapping("/email/{email}")
    public Cliente searchEmail(@PathVariable("email") String email){
        return clienteService.searchClientByEmail(email);
    }

    @GetMapping("/auto")
    public String auto(HttpServletRequest request){
        String auto = request.getHeader("Authorization");
        return auto;
    }

    @PostMapping("/mudar-senha")
    public String changePassword(@RequestParam("token")String token, @RequestBody String password){
        return clienteService.changepassword(token, password);
    }

    @GetMapping("/token/{token}")
    public ClienteDTO getClientByToken(@PathVariable("token")String token){
        return clienteService.getClientByToken(token);
    }
}
