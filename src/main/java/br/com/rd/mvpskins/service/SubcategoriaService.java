package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.CategoriaDTO;
import br.com.rd.mvpskins.model.dto.CompositeKeySubcategoriaDTO;
import br.com.rd.mvpskins.model.dto.SubcategoriaDTO;
import br.com.rd.mvpskins.model.embeddable.CompositeKeySubcategoria;
import br.com.rd.mvpskins.model.entity.Categoria;
import br.com.rd.mvpskins.model.entity.Subcategoria;
import br.com.rd.mvpskins.repository.contract.CategoriaRepository;
import br.com.rd.mvpskins.repository.contract.SubcategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubcategoriaService {

    @Autowired
    SubcategoriaRepository subcategoriaRepository;
    @Autowired
    CategoriaRepository categoriaRepository;

    public List<SubcategoriaDTO> getList(){
        List<Subcategoria> list = subcategoriaRepository.findAll();
        return this.listToDto(list);
    }

    public SubcategoriaDTO getSubcategoryById(Long idC, Long id){
        CompositeKeySubcategoria key = new CompositeKeySubcategoria();

        if(categoriaRepository.existsById(idC)){
            Categoria c = categoriaRepository.getById(idC);
            key.setCategoria(c);
            key.setCodigoSubcategoria(id);

            Optional<Subcategoria> op = subcategoriaRepository.findById(key);

            if(op.isPresent()){
                Subcategoria s = op.get();
                return this.businessToDto(s);
            }
        }
        return null;
    }

    public SubcategoriaDTO businessToDto(Subcategoria s){
        SubcategoriaDTO dto = new SubcategoriaDTO();
        CompositeKeySubcategoriaDTO key = new CompositeKeySubcategoriaDTO();
        CategoriaDTO cDTO = new CategoriaDTO();

        cDTO.setCodigoCategoria(s.getChaveComposta().getCategoria().getCodigoCategoria());
        cDTO.setDescricao(s.getChaveComposta().getCategoria().getDescricao());

        key.setCodigoSubcategoria(s.getChaveComposta().getCodigoSubcategoria());
        key.setCategoria(cDTO);

        dto.setChaveComposta(key);
        dto.setDescricao(s.getDescricao());
        return dto;
    }

    public Subcategoria dtoToBusiness(SubcategoriaDTO dto) {
        Subcategoria s = new Subcategoria();
        CompositeKeySubcategoria key = new CompositeKeySubcategoria();
        Categoria c = new Categoria();

        c.setCodigoCategoria(dto.getChaveComposta().getCategoria().getCodigoCategoria());
        c.setDescricao(dto.getChaveComposta().getCategoria().getDescricao());

        key.setCodigoSubcategoria(dto.getChaveComposta().getCodigoSubcategoria());
        key.setCategoria(c);

        s.setChaveComposta(key);
        s.setDescricao(dto.getDescricao());
        return s;
    }

    public List<SubcategoriaDTO> listToDto(List<Subcategoria> list){
        List<SubcategoriaDTO> listDTO = new ArrayList<SubcategoriaDTO>();

        for (Subcategoria s : list){
            listDTO.add(this.businessToDto(s));
        }

        return listDTO;
    }
}
