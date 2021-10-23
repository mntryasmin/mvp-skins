package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.TypeNFDTO;
import br.com.rd.mvpskins.model.entity.TypeNF;
import br.com.rd.mvpskins.repository.contract.TypeNFRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TypeNFService {

    @Autowired
    TypeNFRepository typeNFRepository;

    //  ---------------------> CONVERTER PARA BUSINESS
    private TypeNF dtoToBusiness (TypeNFDTO dto) {
        TypeNF b = new TypeNF();

        b.setId(dto.getId());
        b.setDescription(dto.getDescription());

        return b;
    }

    //  ---------------------> CONVERTER PARA DTO
    private TypeNFDTO businessToDTO (TypeNF b) {
        TypeNFDTO dto = new TypeNFDTO();

        dto.setId(b.getId());
        dto.setDescription(b.getDescription());

        return dto;
    }

    //  ---------------------> CONVERTER LISTA BUSINESS PARA LISTA DTO
    private List<TypeNFDTO> listToDTO (List<TypeNF> listB) {
        List<TypeNFDTO> listDTO = new ArrayList<>();

        for (TypeNF b : listB) {
            listDTO.add(this.businessToDTO(b));
        }

        return listDTO;
    }


    //  ---------------------> CRIAR
    public TypeNFDTO create (TypeNFDTO typeNFDTO) {
        TypeNF typeNF = dtoToBusiness(typeNFDTO);
        typeNF = typeNFRepository.save(typeNF);

        return businessToDTO(typeNF);
    }


    //  ---------------------> BUSCAR
    //TODOS OS PEDIDOS
    public List<TypeNFDTO> searchAll() {
        List<TypeNF> list = typeNFRepository.findAll();

        return listToDTO(list);
    }

    //UM PEDIDO POR ID
    public TypeNFDTO searchID(Long id) {
        if (typeNFRepository.existsById(id)) {
            return businessToDTO(typeNFRepository.getById(id));
        }

        return null;
    }

    //  ---------------------> ATUALIZAR
    public TypeNFDTO update(TypeNFDTO dto, Long id) {

        Optional<TypeNF> opt = typeNFRepository.findById(id);
        TypeNF typeNF = dtoToBusiness(dto);

        if (opt.isPresent()) {
            TypeNF update = opt.get();

            if (typeNF.getDescription() != null) {
                update.setDescription(typeNF.getDescription());
            }

            typeNFRepository.save(update);
            return businessToDTO(update);
        }

        return null;
    }

    //  ---------------------> DELETAR
    public void delete(Long id) {
        if (typeNFRepository.existsById(id)) {
            typeNFRepository.deleteById(id);
        }
    }
}