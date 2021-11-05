package br.com.rd.mvpskins.controller;

import br.com.rd.mvpskins.model.dto.FornecedorDTO;
import br.com.rd.mvpskins.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {

    @Autowired
    FornecedorService fornecedorService;

    //  ---------------------> CRIAR
    @PostMapping
    @ResponseBody
    public FornecedorDTO create (@RequestBody FornecedorDTO providerDTO) {
        return fornecedorService.create(providerDTO);
    }

    //  ---------------------> BUSCAR
    //TODOS OS TIPOS DE NF
    @GetMapping
    public List<FornecedorDTO> searchAll() {
        return fornecedorService.searchAll();
    }

    //UM TIPO DE NF POR ID
    @GetMapping("/{id}")
    public FornecedorDTO searchID(@PathVariable("id") Long id) {
        return fornecedorService.searchID(id);
    }

    //  ---------------------> ATUALIZAR
    @PutMapping("/{id}")
    @ResponseBody
    public FornecedorDTO update(@RequestBody FornecedorDTO providerDTO, @PathVariable("id") Long id) {
        return fornecedorService.update(providerDTO, id);
    }

    //  ---------------------> DELETAR
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        fornecedorService.delete(id);
    }
}