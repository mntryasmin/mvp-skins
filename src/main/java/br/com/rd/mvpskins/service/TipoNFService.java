package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.TipoNFDTO;
import br.com.rd.mvpskins.model.entity.TipoNF;
import br.com.rd.mvpskins.repository.contract.TipoNFRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TipoNFService {

    @Autowired
    TipoNFRepository tipoNFRepository;

    //  ---------------------> CONVERTER PARA BUSINESS
    private TipoNF dtoToBusiness (TipoNFDTO dto) {
        TipoNF b = new TipoNF();

        b.setId(dto.getId());
        b.setDescricao(dto.getDescricao());

        return b;
    }

    //  ---------------------> CONVERTER PARA DTO
    private TipoNFDTO businessToDTO (TipoNF b) {
        TipoNFDTO dto = new TipoNFDTO();

        dto.setId(b.getId());
        dto.setDescricao(b.getDescricao());

        return dto;
    }

    //  ---------------------> CONVERTER LISTA BUSINESS PARA LISTA DTO
    private List<TipoNFDTO> listToDTO (List<TipoNF> listB) {
        List<TipoNFDTO> listDTO = new ArrayList<>();

        for (TipoNF b : listB) {
            listDTO.add(this.businessToDTO(b));
        }

        return listDTO;
    }


    //  ---------------------> CRIAR
    public TipoNFDTO create (TipoNFDTO tipoNFDTO) {
        TipoNF tipoNF = dtoToBusiness(tipoNFDTO);
        tipoNF = tipoNFRepository.save(tipoNF);

        return businessToDTO(tipoNF);
    }


    //  ---------------------> BUSCAR
    //TODOS OS TIPOS DE NF
    public List<TipoNFDTO> searchAll() {
        List<TipoNF> list = tipoNFRepository.findAll();

        return listToDTO(list);
    }

    //UM TIPO DE NF POR ID
    public TipoNFDTO searchID(Long id) {
        if (tipoNFRepository.existsById(id)) {
            return businessToDTO(tipoNFRepository.getById(id));
        }

        return null;
    }

    //  ---------------------> ATUALIZAR
    public TipoNFDTO update(TipoNFDTO dto, Long id) {

        Optional<TipoNF> opt = tipoNFRepository.findById(id);
        TipoNF tipoNF = dtoToBusiness(dto);

        if (opt.isPresent()) {
            TipoNF update = opt.get();

            if (tipoNF.getDescricao() != null) {
                update.setDescricao(tipoNF.getDescricao());
            }

            tipoNFRepository.save(update);
            return businessToDTO(update);
        }

        return null;
    }

    //  ---------------------> DELETAR
    public void delete(Long id) {
        if (tipoNFRepository.existsById(id)) {
            tipoNFRepository.deleteById(id);
        }
    }
}