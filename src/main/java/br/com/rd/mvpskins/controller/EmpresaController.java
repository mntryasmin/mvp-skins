package br.com.rd.mvpskins.controller;

import br.com.rd.mvpskins.model.dto.EmpresaDTO;
import br.com.rd.mvpskins.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
    @Autowired
    EmpresaService empresaService;

    @GetMapping
    @ResponseBody
    public List<EmpresaDTO> getListCompany(){
        return empresaService.getListCompany();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public EmpresaDTO getCompany(@PathVariable("id")Long id){
        return empresaService.searchID(id);
    }
}
