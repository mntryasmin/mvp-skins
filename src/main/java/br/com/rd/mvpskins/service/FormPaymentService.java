package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.FormPaymentDTO;
import br.com.rd.mvpskins.model.entity.FormPayment;
import br.com.rd.mvpskins.repository.contract.FormPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FormPaymentService {

    @Autowired
    FormPaymentRepository formPaymentRepository;

    //  ---------------------> CONVERTER PARA BUSINESS
    private FormPayment dtoToBusiness (FormPaymentDTO dto) {
        FormPayment b = new FormPayment();

        b.setId(dto.getId());
        b.setDescription(dto.getDescription());

        return b;
    }

    //  ---------------------> CONVERTER PARA DTO
    private FormPaymentDTO businessToDTO (FormPayment b) {
        FormPaymentDTO dto = new FormPaymentDTO();

        dto.setId(b.getId());
        dto.setDescription(b.getDescription());

        return dto;
    }

    //  ---------------------> CONVERTER LISTA BUSINESS PARA LISTA DTO
    private List<FormPaymentDTO> listToDTO (List<FormPayment> listB) {
        List<FormPaymentDTO> listDTO = new ArrayList<>();

        for (FormPayment b : listB) {
            listDTO.add(this.businessToDTO(b));
        }

        return listDTO;
    }


    //  ---------------------> CRIAR
    public FormPaymentDTO create (FormPaymentDTO formPaymentDTO) {
        FormPayment pedido = dtoToBusiness(formPaymentDTO);
        pedido = formPaymentRepository.save(pedido);

        return businessToDTO(pedido);
    }


    //  ---------------------> BUSCAR
    //TODOS OS PEDIDOS
    public List<FormPaymentDTO> searchAll() {
        List<FormPayment> list = formPaymentRepository.findAll();

        return listToDTO(list);
    }

    //UM PEDIDO POR ID
    public FormPaymentDTO searchID(Long id) {
        if (formPaymentRepository.existsById(id)) {
            return businessToDTO(formPaymentRepository.getById(id));
        }

        return null;
    }

    //  ---------------------> ATUALIZAR
    public FormPaymentDTO update(FormPaymentDTO dto, Long id) {

        Optional<FormPayment> opt = formPaymentRepository.findById(id);
        FormPayment formPayment = dtoToBusiness(dto);

        if (opt.isPresent()) {
            FormPayment update = opt.get();

            if (formPayment.getDescription() != null) {
                update.setDescription(formPayment.getDescription());
            }

            formPaymentRepository.save(update);
            return businessToDTO(update);
        }

        return null;
    }

    //  ---------------------> DELETAR
    public void delete(Long id) {
        if (formPaymentRepository.existsById(id)) {
            formPaymentRepository.deleteById(id);
        }
    }
}