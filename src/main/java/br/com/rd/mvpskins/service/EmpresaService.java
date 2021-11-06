package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.EmpresaDTO;
import br.com.rd.mvpskins.model.entity.Empresa;
import br.com.rd.mvpskins.repository.contract.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpresaService {

    @Autowired
    EmpresaRepository empresaRepository;

    public List<EmpresaDTO> getListCompany(){
        List<Empresa> list = empresaRepository.findAll();

        return this.listToDto(list);
    }

    public EmpresaDTO searchID(Long id) {
        if (empresaRepository.existsById(id)) {
            return boToDto(empresaRepository.getById(id));
        }

        return null;
    }

    private List<EmpresaDTO> listToDto(List<Empresa> list){
        List<EmpresaDTO> listDto = new ArrayList<EmpresaDTO>();

        for (Empresa e : list){
            listDto.add(this.boToDto(e));
        }

        return listDto;
    }

    private Empresa dtoToBo(EmpresaDTO dto){
        Empresa e = new Empresa();
        e.setIdEmpresa(dto.getId());
        e.setCnpj(dto.getCnpj());
        e.setEndereco(dto.getEndereco());
        e.setNome(dto.getNome());
        e.setInscricaoEstadual(dto.getInscricaoEstadual());
        e.setTelefone(dto.getTelefone());

        return e;
    }

    private EmpresaDTO boToDto(Empresa e){
        EmpresaDTO dto = new EmpresaDTO();
        dto.setId(e.getIdEmpresa());
        dto.setTelefone(e.getTelefone());
        dto.setCnpj(e.getCnpj());
        dto.setEndereco(e.getEndereco());
        dto.setNome(e.getNome());
        dto.setInscricaoEstadual(e.getInscricaoEstadual());

        return dto;
    }
}
