package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.ColecaoDTO;
import br.com.rd.mvpskins.model.entity.Colecao;
import br.com.rd.mvpskins.repository.contract.ColecaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ColecaoService {

    @Autowired
    ColecaoRepository colecaoRepository;

    public List<ColecaoDTO> getList(){
        List<Colecao> list = colecaoRepository.findAll();
        return this.listToDto(list);
    }

    public ColecaoDTO getColectionById(Long id){
        if(colecaoRepository.existsById(id)){
            Colecao c = colecaoRepository.getById(id);
            return this.businessToDto(c);
        }
        return null;
    }

    public ColecaoDTO businessToDto(Colecao c){
        ColecaoDTO dto = new ColecaoDTO();

        dto.setId(c.getId());
        dto.setDescricao(c.getDescricao());
        return dto;
    }

    public Colecao dtoToBusiness(ColecaoDTO dto){
        Colecao c = new Colecao();

        c.setId(dto.getId());
        c.setDescricao(c.getDescricao());
        return c;
    }

    public List<ColecaoDTO> listToDto(List<Colecao> list){
        List<ColecaoDTO> listDto = new ArrayList<ColecaoDTO>();

        for (Colecao c : list){
            listDto.add(this.businessToDto(c));
        }
        return listDto;
    }
}
