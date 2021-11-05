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
        try{
            return clienteService.createClient(cliente);
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

    @DeleteMapping("/{idCliente}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deleteClient(@PathVariable("idCliente")Long codeClient){
        clienteService.deleteCliente(codeClient);
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
}
