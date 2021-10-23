package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.RequestDTO;
import br.com.rd.mvpskins.model.entity.Request;
import br.com.rd.mvpskins.repository.contract.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    RequestRepository requestRepository;

    //  ---------------------> CONVERTER PARA BUSINESS
    private Request dtoToBusiness (RequestDTO dto) {
        Request b = new Request();

        b.setId(dto.getId());
        b.setIdCompany(dto.getIdCompany());
        b.setIdClient(dto.getIdClient());
        b.setIdFormPayment(dto.getIdFormPayment());
        b.setIssueDate(dto.getIssueDate());
        b.setDiscountProduct(dto.getDiscountProduct());
        b.setGrossAddedValue(dto.getGrossAddedValue());
        b.setNetValue(dto.getNetValue());

        return b;
    }

    //  ---------------------> CONVERTER PARA DTO
    private RequestDTO businessToDTO (Request b) {
        RequestDTO dto = new RequestDTO();

        dto.setId(b.getId());
        dto.setIdCompany(b.getIdCompany());
        dto.setIdClient(b.getIdClient());
        dto.setIdFormPayment(b.getIdFormPayment());
        dto.setIssueDate(b.getIssueDate());
        dto.setDiscountProduct(b.getDiscountProduct());
        dto.setGrossAddedValue(b.getGrossAddedValue());
        dto.setNetValue(b.getNetValue());

        return dto;
    }

    //  ---------------------> CONVERTER LISTA BUSINESS PARA LISTA DTO
    private List<RequestDTO> listToDTO (List<Request> listB) {
        List<RequestDTO> listDTO = new ArrayList<>();

        for (Request b : listB) {
            listDTO.add(this.businessToDTO(b));
        }

        return listDTO;
    }


    //  ---------------------> CRIAR
    public RequestDTO create (RequestDTO requestDTO) {
        Request pedido = dtoToBusiness(requestDTO);
        pedido.setIssueDate(new Date());
        pedido = requestRepository.save(pedido);

        return businessToDTO(pedido);
    }


    //  ---------------------> BUSCAR
    //TODOS OS PEDIDOS
    public List<RequestDTO> searchAll() {
        List<Request> list = requestRepository.findAll();

        return listToDTO(list);
    }

    //UM PEDIDO POR ID
    public RequestDTO searchID(Long id) {
        if (requestRepository.existsById(id)) {
            return businessToDTO(requestRepository.getById(id));
        }

        return null;
    }

    //  ---------------------> ATUALIZAR
    public RequestDTO update(RequestDTO dto, Long id) {

        Optional<Request> opt = requestRepository.findById(id);
        Request request = dtoToBusiness(dto);

        if (opt.isPresent()) {
            Request update = opt.get();

            if (request.getIdCompany() != null) {
                update.setIdCompany(request.getIdCompany());
            }

            if (request.getIdClient() != null) {
                update.setIdClient(request.getIdClient());
            }

            if (request.getIdFormPayment() != null) {
                update.setIdFormPayment(request.getIdFormPayment());
            }

            if (request.getIssueDate() != null) {
                update.setIssueDate(request.getIssueDate());
            }

            if (request.getDiscountProduct() != null) {
                update.setDiscountProduct(request.getDiscountProduct());
            }

            if (request.getGrossAddedValue() != null) {
                update.setGrossAddedValue(request.getGrossAddedValue());
            }

            if (request.getNetValue() != null) {
                update.setNetValue(request.getNetValue());
            }

            requestRepository.save(update);
            return businessToDTO(update);
        }

        return null;
    }

    //  ---------------------> DELETAR
    public void delete(Long id) {
        if (requestRepository.existsById(id)) {
            requestRepository.deleteById(id);
        }
    }
}