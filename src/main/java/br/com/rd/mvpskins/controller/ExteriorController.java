package br.com.rd.mvpskins.controller;

import br.com.rd.mvpskins.model.dto.ExteriorDTO;
import br.com.rd.mvpskins.service.ExteriorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exterior")
public class ExteriorController {

    @Autowired
    ExteriorService exteriorService;

    @GetMapping
    @ResponseBody
    public List<ExteriorDTO> getList(){
        return exteriorService.getList();
    }

    @GetMapping("/{id}")
    public ExteriorDTO getExteriorById(@PathVariable("id") Long id){
        return exteriorService.getExteriorById(id);
    }
}
