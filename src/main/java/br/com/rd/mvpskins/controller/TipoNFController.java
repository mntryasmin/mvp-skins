package br.com.rd.mvpskins.controller;

import br.com.rd.mvpskins.model.dto.TipoNFDTO;
import br.com.rd.mvpskins.service.TipoNFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipo-nf")
public class TipoNFController {

    @Autowired
    TipoNFService tipoNFService;

    //  ---------------------> CRIAR
    @PostMapping
    @ResponseBody
    public TipoNFDTO create (@RequestBody TipoNFDTO tipoNFDTO) {
        return tipoNFService.create(tipoNFDTO);
    }

    //  ---------------------> BUSCAR
    //TODOS OS TIPOS DE NF
    @GetMapping
    public List<TipoNFDTO> searchAll() {
        return tipoNFService.searchAll();
    }

    //UM TIPO DE NF POR ID
    @GetMapping("/{id}")
    public TipoNFDTO searchID(@PathVariable("id") Long id) {
        return tipoNFService.searchID(id);
    }

    //  ---------------------> ATUALIZAR
    @PutMapping("/{id}")
    @ResponseBody
    public TipoNFDTO update(@RequestBody TipoNFDTO tipoNFDTO, @PathVariable("id") Long id) {
        return tipoNFService.update(tipoNFDTO, id);
    }

    //  ---------------------> DELETAR
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Long id) {
        tipoNFService.delete(id);
    }
}