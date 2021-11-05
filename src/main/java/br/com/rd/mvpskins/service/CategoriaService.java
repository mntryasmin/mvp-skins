package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.CategoriaDTO;
import br.com.rd.mvpskins.model.entity.Categoria;
import br.com.rd.mvpskins.repository.contract.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public CategoriaDTO getCategoryById(Long id){
        if(categoriaRepository.existsById(id)){
            Categoria c = categoriaRepository.getById(id);

            return this.businessToDTO(c);
        }
        return null;
    }

    public List<CategoriaDTO> getList(){
        List<Categoria> list = categoriaRepository.findAll();

        return this.listToDto(list);
    }

    public Categoria dtoToBusiness (CategoriaDTO dto) {
        Categoria c = new Categoria();

        c.setDescricao(dto.getDescricao());
        return c;
    }

    public CategoriaDTO businessToDTO (Categoria c){
        CategoriaDTO dto = new CategoriaDTO();

        dto.setCodigoCategoria(c.getCodigoCategoria());
        dto.setDescricao(c.getDescricao());
        return dto;
    }

    public List<CategoriaDTO> listToDto(List<Categoria> list) {
        List<CategoriaDTO> listDTO = new ArrayList<CategoriaDTO>();

        for (Categoria c : list) {
            listDTO.add(this.businessToDTO(c));
        }
        return listDTO;
    }
}
