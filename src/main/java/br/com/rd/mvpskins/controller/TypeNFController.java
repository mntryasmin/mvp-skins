package br.com.rd.mvpskins.controller;

import br.com.rd.mvpskins.model.dto.TypeNFDTO;
import br.com.rd.mvpskins.service.TypeNFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type-nf")
public class TypeNFController {

    @Autowired
    TypeNFService typeNFService;

    //  ---------------------> CRIAR
    @PostMapping
    @ResponseBody
    public TypeNFDTO create (@RequestBody TypeNFDTO typeNFDTO) {
        return typeNFService.create(typeNFDTO);
    }

    //  ---------------------> BUSCAR
    //TODOS OS PEDIDOS
    @GetMapping
    public List<TypeNFDTO> searchAll() {
        return typeNFService.searchAll();
    }

    //UM PEDIDO POR ID
    @GetMapping("/{id}")
    public TypeNFDTO searchID(@PathVariable("id") Long id) {
        return typeNFService.searchID(id);
    }

    //  ---------------------> ATUALIZAR
    @PutMapping("/{id}")
    @ResponseBody
    public TypeNFDTO update(@RequestBody TypeNFDTO typeNFDTO, @PathVariable("id") Long id) {
        return typeNFService.update(typeNFDTO, id);
    }

    //  ---------------------> DELETAR
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Long id) {
        typeNFService.delete(id);
    }
}