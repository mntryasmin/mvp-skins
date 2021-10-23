package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.EmpresaDTO;
import br.com.rd.mvpskins.model.entity.Empresa;
import br.com.rd.mvpskins.repository.contract.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {

    @Autowired
    EmpresaRepository empresaRepository;

    public EmpresaDTO getEmpresa(){
        Empresa e = empresaRepository.getById(1l);
        return boToDto(e);
    }

    private EmpresaDTO boToDto(Empresa e){
        EmpresaDTO dto = new EmpresaDTO();
        dto.setTelefone(e.getTelefone());
        dto.setCnpj(e.getCnpj());
        dto.setEndereco(e.getEndereco());
        dto.setNome(e.getNome());
        dto.setInscricaoEstadual(e.getInscricaoEstadual());

        return dto;
    }
}
