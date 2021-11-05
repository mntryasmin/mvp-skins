package br.com.rd.mvpskins.controller;

import br.com.rd.mvpskins.model.dto.FormaPagamentoDTO;
import br.com.rd.mvpskins.service.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forma-pagamento")
public class FormaPagamentoController {

    @Autowired
    FormaPagamentoService formaPagamentoService;

    //  ---------------------> CRIAR
    @PostMapping
    @ResponseBody
    public FormaPagamentoDTO create (@RequestBody FormaPagamentoDTO paymentFormDTO) {
        return formaPagamentoService.create(paymentFormDTO);
    }

    //  ---------------------> BUSCAR
    //TODAS AS FORMAS DE PAGAMENTO
    @GetMapping
    public List<FormaPagamentoDTO> searchAll() {
        return formaPagamentoService.searchAll();
    }

    //UMA FORMA DE PAGAMENTO POR ID
    @GetMapping("/{id}")
    public FormaPagamentoDTO searchID(@PathVariable("id") Long id) {
        return formaPagamentoService.searchID(id);
    }

    //  ---------------------> ATUALIZAR
    @PutMapping("/{id}")
    @ResponseBody
    public FormaPagamentoDTO update(@RequestBody FormaPagamentoDTO paymentFormDTO, @PathVariable("id") Long id) {
        return formaPagamentoService.update(paymentFormDTO, id);
    }

    //  ---------------------> DELETAR
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Long id) {
        formaPagamentoService.delete(id);
    }
}