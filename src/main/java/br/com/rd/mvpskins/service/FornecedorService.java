package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.FornecedorDTO;
import br.com.rd.mvpskins.model.embeddable.ItensNFCompositeKey;
import br.com.rd.mvpskins.model.entity.Fornecedor;
import br.com.rd.mvpskins.repository.contract.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    FornecedorRepository fornecedorRepository;

    //  ---------------------> CONVERTER PARA BUSINESS
    private Fornecedor dtoToBusiness (FornecedorDTO dto) {
        Fornecedor b = new Fornecedor();

        b.setId(dto.getId());
        b.setCnpj(dto.getCnpj());
        b.setDescricao(dto.getDescricao());

        return b;
    }

    //  ---------------------> CONVERTER PARA DTO
    private FornecedorDTO businessToDTO (Fornecedor b) {
        FornecedorDTO dto = new FornecedorDTO();

        dto.setId(b.getId());
        dto.setCnpj(b.getCnpj());
        dto.setDescricao(b.getDescricao());

        return dto;
    }

    //  ---------------------> CONVERTER LISTA BUSINESS PARA LISTA DTO
    private List<FornecedorDTO> listToDTO (List<Fornecedor> listB) {
        List<FornecedorDTO> listDTO = new ArrayList<>();

        for (Fornecedor b : listB) {
            listDTO.add(this.businessToDTO(b));
        }

        return listDTO;
    }


    //  ---------------------> CRIAR
    public FornecedorDTO create (FornecedorDTO fornecedorDTO) {
        Fornecedor fornecedor = dtoToBusiness(fornecedorDTO);
        fornecedor = fornecedorRepository.save(fornecedor);

        return businessToDTO(fornecedor);
    }


    //  ---------------------> BUSCAR
    //TODOS OS FORNECEDORES
    public List<FornecedorDTO> searchAll() {
        List<Fornecedor> list = fornecedorRepository.findAll();

        return listToDTO(list);
    }

    //UM FORNECEDOR POR ID
    public FornecedorDTO searchID(Long id) {
        if (fornecedorRepository.existsById(id)) {
            return businessToDTO(fornecedorRepository.getById(id));
        }

        return null;
    }

    //  ---------------------> ATUALIZAR
    public FornecedorDTO update(FornecedorDTO dto, Long id) {

        Optional<Fornecedor> opt = fornecedorRepository.findById(id);
        Fornecedor fornecedor = dtoToBusiness(dto);

        if (opt.isPresent()) {
            Fornecedor update = opt.get();

            if (fornecedor.getCnpj() != null) {
                update.setCnpj(fornecedor.getCnpj());
            }

            if (fornecedor.getDescricao() != null) {
                update.setDescricao(fornecedor.getDescricao());
            }

            fornecedorRepository.save(update);
            return businessToDTO(update);
        }

        return null;
    }

    //  ---------------------> DELETAR
    public void delete(Long id) {
        if (fornecedorRepository.existsById(id)) {
            fornecedorRepository.deleteById(id);
        }
    }
}