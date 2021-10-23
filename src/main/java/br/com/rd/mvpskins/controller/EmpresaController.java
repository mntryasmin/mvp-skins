package br.com.rd.mvpskins.controller;

import br.com.rd.mvpskins.model.dto.EmpresaDTO;
import br.com.rd.mvpskins.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
    @Autowired
    EmpresaService empresaService;
    @GetMapping
    public EmpresaDTO getEmpresa(){
        return empresaService.getEmpresa();
    }
}
