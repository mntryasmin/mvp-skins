package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.RaridadeDTO;
import br.com.rd.mvpskins.model.entity.Raridade;
import br.com.rd.mvpskins.repository.contract.RaridadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RaridadeService {

    @Autowired
    RaridadeRepository raridadeRepository;

    public List<RaridadeDTO> getList(){
        List<Raridade> list = raridadeRepository.findAll();
        return this.listToDto(list);
    }

    public RaridadeDTO getRarityById(Long id){
        if(raridadeRepository.existsById(id)){
            Raridade r = raridadeRepository.getById(id);
            return this.businessToDto(r);
        }
        return null;
    }

    public RaridadeDTO businessToDto(Raridade r){
        RaridadeDTO dto = new RaridadeDTO();

        dto.setId(r.getId());
        dto.setDescricao(r.getDescricao());
        return dto;
    }

    public Raridade dtoToBusiness(RaridadeDTO dto){
        Raridade r = new Raridade();

        r.setId(dto.getId());
        r.setDescricao(dto.getDescricao());
        return r;
    }

    public List<RaridadeDTO> listToDto(List<Raridade> list){
        List<RaridadeDTO> listDto = new ArrayList<RaridadeDTO>();

        for (Raridade r : list) {
            listDto.add(this.businessToDto(r));
        }

        return listDto;
    }
}
