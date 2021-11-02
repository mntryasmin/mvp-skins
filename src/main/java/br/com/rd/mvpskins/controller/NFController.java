package br.com.rd.mvpskins.controller;

import br.com.rd.mvpskins.model.dto.NFDTO;
import br.com.rd.mvpskins.model.dto.ProdutoDTO;
import br.com.rd.mvpskins.service.NFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nf")
public class NFController {

    @Autowired
    NFService nfService;

    //  ---------------------> CRIAR
    @PostMapping
    @ResponseBody
    public NFDTO create (@RequestBody NFDTO nfDTO) {
        return nfService.create(nfDTO);
    }

    //  ---------------------> BUSCAR
    //TODAS AS NF'S
    @GetMapping
    public List<NFDTO> searchAll() {
        return nfService.searchAll();
    }

    //TODAS NF'S DE UM CLIENTE
    @GetMapping("/historico/{idCliente}")
    public List<NFDTO> findByClienteCodigoCliente(@PathVariable ("idCliente") Long idCliente) {
        return nfService.findByClienteCodigoCliente(idCliente);
    }

    //TODOS OS PRODUTOS DENTRO DE UMA NF
//    @GetMapping("/historico/nf/{idNF}")
//    public List<ProdutoDTO> findByPedidoProduto

    //UMA NF POR ID
    @GetMapping("/{id}")
    public NFDTO searchID(@PathVariable("id") Long id) {
        return nfService.searchID(id);
    }

    //  ---------------------> ATUALIZAR
    @PutMapping("/{id}")
    @ResponseBody
    public NFDTO update(@RequestBody NFDTO nfDTO, @PathVariable("id") Long id) {
        return nfService.update(nfDTO, id);
    }

    //  ---------------------> DELETAR
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Long id) {
        nfService.delete(id);
    }
}