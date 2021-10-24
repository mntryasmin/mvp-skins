package br.com.rd.mvpskins.controller;

import br.com.rd.mvpskins.model.dto.FormPaymentDTO;
import br.com.rd.mvpskins.service.FormPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/form-payment")
public class FormPaymentController {

    @Autowired
    FormPaymentService formPaymentService;

    //  ---------------------> CRIAR
    @PostMapping
    @ResponseBody
    public FormPaymentDTO create (@RequestBody FormPaymentDTO formPaymentDTO) {
        return formPaymentService.create(formPaymentDTO);
    }

    //  ---------------------> BUSCAR
    //TODAS AS FORMAS DE PAGAMENTO
    @GetMapping
    public List<FormPaymentDTO> searchAll() {
        return formPaymentService.searchAll();
    }

    //UMA FORMA DE PAGAMENTO POR ID
    @GetMapping("/{id}")
    public FormPaymentDTO searchID(@PathVariable("id") Long id) {
        return formPaymentService.searchID(id);
    }

    //  ---------------------> ATUALIZAR
    @PutMapping("/{id}")
    @ResponseBody
    public FormPaymentDTO update(@RequestBody FormPaymentDTO formPaymentDTO, @PathVariable("id") Long id) {
        return formPaymentService.update(formPaymentDTO, id);
    }

    //  ---------------------> DELETAR
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") Long id) {
        formPaymentService.delete(id);
    }
}