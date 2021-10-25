package br.com.rd.mvpskins.controller;

import br.com.rd.mvpskins.model.dto.RaridadeDTO;
import br.com.rd.mvpskins.service.RaridadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/raridade")
public class RaridadeController {

    @Autowired
    RaridadeService raridadeService;

    @GetMapping
    @ResponseBody
    public List<RaridadeDTO> getList(){
        return raridadeService.getList();
    }

    @GetMapping("{id}")
    @ResponseBody
    public RaridadeDTO getRarityById(@PathVariable("id")Long id){
        return raridadeService.getRarityById(id);
    }
}
