package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.ExteriorDTO;
import br.com.rd.mvpskins.model.entity.Exterior;
import br.com.rd.mvpskins.repository.contract.ExteriorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExteriorService {

    @Autowired
    ExteriorRepository exteriorRepository;

    public List<ExteriorDTO> getList(){
        List<Exterior> list = exteriorRepository.findAll();
        return this.listToDto(list);
    }

    public ExteriorDTO getExteriorById(Long id){
        if (exteriorRepository.existsById(id)){
            Exterior e = exteriorRepository.getById(id);
            return this.businessToDto(e);
        }
        return null;
    }

    public ExteriorDTO businessToDto(Exterior e){
        ExteriorDTO dto = new ExteriorDTO();

        dto.setId(e.getId());
        dto.setDescricao(e.getDescricao());
        return dto;
    }

    public Exterior dtoToBusiness(ExteriorDTO dto){
        Exterior e = new Exterior();

        e.setId(dto.getId());
        e.setDescricao(dto.getDescricao());
        return e;
    }

    public List<ExteriorDTO> listToDto(List<Exterior> list){
        List<ExteriorDTO> listDto = new ArrayList<ExteriorDTO>();

        for (Exterior e : list){
            listDto.add(this.businessToDto(e));
        }
        return listDto;
    }
}
