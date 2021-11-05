package br.com.rd.mvpskins.controller;

import br.com.rd.mvpskins.model.dto.PrecoDTO;
import br.com.rd.mvpskins.service.PrecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/preco")
public class PrecoController {

    @Autowired
    PrecoService precoService;

    @GetMapping
    @ResponseBody
    public List<PrecoDTO> getList(){
        return precoService.getList();
    }

    @GetMapping("/{idC}/{idP}")
    @ResponseBody
    public List<PrecoDTO> getPriceById(@PathVariable("idP")Long idP, @PathVariable("idC")Long idC){
        return precoService.getPriceById(idP, idC);
    }

    @GetMapping("/recente/{idPreco}/{idProduto}")
    public PrecoDTO getLastPrice(@PathVariable("idProduto") Long idProduto, @PathVariable("idPreco") Long idPreco){
        return precoService.getLastPrice(idProduto, idPreco);
    }
  
}
