package br.com.rd.mvpskins.controller;

import br.com.rd.mvpskins.model.dto.ColecaoDTO;
import br.com.rd.mvpskins.service.ColecaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/colecao")
public class ColecaoController {

    @Autowired
    ColecaoService colecaoService;

    @GetMapping
    @ResponseBody
    public List<ColecaoDTO> getList(){
        return colecaoService.getList();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ColecaoDTO getConectionByList(@PathVariable("id") Long id){
        return colecaoService.getColectionById(id);
    }
}
