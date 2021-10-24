package br.com.rd.mvpskins.controller;

import br.com.rd.mvpskins.model.dto.RequestDTO;
import br.com.rd.mvpskins.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/request")
public class RequestController {

    @Autowired
    RequestService requestService;

    //  ---------------------> CRIAR
    @PostMapping
    @ResponseBody
    public RequestDTO create (@RequestBody RequestDTO requestDTO) {
        return requestService.create(requestDTO);
    }

    //  ---------------------> BUSCAR
    //TODOS OS PEDIDOS
    @GetMapping
    public List<RequestDTO> searchAll() {
        return requestService.searchAll();
    }

    //UM PEDIDO POR ID
    @GetMapping("/{id}")
    public RequestDTO searchID(@PathVariable("id") Long id) {
        return requestService.searchID(id);
    }

    //  ---------------------> ATUALIZAR
    @PutMapping("/{id}")
    @ResponseBody
    public RequestDTO update(@RequestBody RequestDTO requestDTO, @PathVariable("id") Long id) {
        return requestService.update(requestDTO, id);
    }

    //  ---------------------> DELETAR
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Long id) {
        requestService.delete(id);
    }
}