package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.FormaPagamentoDTO;
import br.com.rd.mvpskins.model.entity.FormaPagamento;
import br.com.rd.mvpskins.repository.contract.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FormaPagamentoService {

    @Autowired
    FormaPagamentoRepository formaPagamentoRepository;

    //  ---------------------> CONVERTER PARA BUSINESS
    public FormaPagamento dtoToBusiness (FormaPagamentoDTO dto) {
        FormaPagamento b = new FormaPagamento();

        b.setId(dto.getId());
        b.setDescricao(dto.getDescricao());

        return b;
    }

    //  ---------------------> CONVERTER PARA DTO
    private FormaPagamentoDTO businessToDTO (FormaPagamento b) {
        FormaPagamentoDTO dto = new FormaPagamentoDTO();

        dto.setId(b.getId());
        dto.setDescricao(b.getDescricao());

        return dto;
    }

    //  ---------------------> CONVERTER LISTA BUSINESS PARA LISTA DTO
    private List<FormaPagamentoDTO> listToDTO (List<FormaPagamento> listB) {
        List<FormaPagamentoDTO> listDTO = new ArrayList<>();

        for (FormaPagamento b : listB) {
            listDTO.add(this.businessToDTO(b));
        }

        return listDTO;
    }


    //  ---------------------> CRIAR
    public FormaPagamentoDTO create (FormaPagamentoDTO paymentFormDTO) {
        FormaPagamento paymentForm = dtoToBusiness(paymentFormDTO);
        paymentForm = formaPagamentoRepository.save(paymentForm);

        return businessToDTO(paymentForm);
    }


    //  ---------------------> BUSCAR
    //TODOS AS FORMAS DE PAGAMENTO
    public List<FormaPagamentoDTO> searchAll() {
        List<FormaPagamento> list = formaPagamentoRepository.findAll();

        return listToDTO(list);
    }

    //UMA FORMA DE PAGAMENTO POR ID
    public FormaPagamentoDTO searchID(Long id) {
        if (formaPagamentoRepository.existsById(id)) {
            return businessToDTO(formaPagamentoRepository.getById(id));
        }

        return null;
    }

    //  ---------------------> ATUALIZAR
    public FormaPagamentoDTO update(FormaPagamentoDTO dto, Long id) {

        Optional<FormaPagamento> opt = formaPagamentoRepository.findById(id);
        FormaPagamento paymentForm = dtoToBusiness(dto);

        if (opt.isPresent()) {
            FormaPagamento update = opt.get();

            if (paymentForm.getDescricao() != null) {
                update.setDescricao(paymentForm.getDescricao());
            }

            formaPagamentoRepository.save(update);
            return businessToDTO(update);
        }

        return null;
    }

    //  ---------------------> DELETAR
    public void delete(Long id) {
        if (formaPagamentoRepository.existsById(id)) {
            formaPagamentoRepository.deleteById(id);
        }
    }
}