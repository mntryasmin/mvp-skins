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
        List<Categoria> lista = categoriaRepository.findAll();

        return this.listToDto(lista);
    }

    public Categoria dtoToBusiness (CategoriaDTO dto) {
        Categoria c = new Categoria();

        c.setDescricao(dto.getDESCRICAO());
        return c;
    }

    public CategoriaDTO businessToDTO (Categoria c){
        CategoriaDTO dto = new CategoriaDTO();

        dto.setCODIGO_CATEGORIA(c.getCodigoCategoria());
        dto.setDESCRICAO(c.getDescricao());
        return dto;
    }

    public List<CategoriaDTO> listToDto(List<Categoria> lista) {
        List<CategoriaDTO> listaDTO = new ArrayList<CategoriaDTO>();

        for (Categoria c : lista) {
            listaDTO.add(this.businessToDTO(c));
        }
        return listaDTO;
    }
}
