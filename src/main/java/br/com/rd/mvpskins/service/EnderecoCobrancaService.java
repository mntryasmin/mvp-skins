package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.*;
import br.com.rd.mvpskins.model.entity.Cliente;
import br.com.rd.mvpskins.model.entity.EnderecoCobranca;
import br.com.rd.mvpskins.model.entity.FormaPagamento;
import br.com.rd.mvpskins.model.entity.Pedido;
import br.com.rd.mvpskins.repository.contract.ClienteRepository;
import br.com.rd.mvpskins.repository.contract.EnderecoCobrancaRepository;
import br.com.rd.mvpskins.repository.contract.FormaPagamentoRepository;
import br.com.rd.mvpskins.repository.contract.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoCobrancaService {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    PedidoService pedidoService;

    @Autowired
    EnderecoCobrancaRepository enderecoCobrancaRepository;


    //  ---------------------> CONVERTER PARA BUSINESS
    private EnderecoCobranca dtoToBusiness (EnderecoCobrancaDTO dto) {
        EnderecoCobranca b = new EnderecoCobranca();

        //        ===> PEDIDO
        if (dto.getPedido().getId() != null) {
            try {
                Pedido p = pedidoRepository.getById(dto.getPedido().getId());
                b.setPedido(p);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        b.setId(dto.getId());
        b.setCep(dto.getCep());
        b.setLogradouro(dto.getLogradouro());
        b.setNumero(dto.getNumero());
        b.setComplemento(dto.getComplemento());
        b.setBairro(dto.getBairro());
        b.setCidade(dto.getCidade());
        b.setEstado(dto.getEstado());

        return b;
    }

    //  ---------------------> CONVERTER PARA DTO
    private EnderecoCobrancaDTO businessToDTO (EnderecoCobranca b) {
        EnderecoCobrancaDTO dto = new EnderecoCobrancaDTO();

        //        ===> PEDIDO
        if (b.getPedido().getId() != null) {
            try {
                PedidoDTO p = pedidoService.searchOrderById(b.getPedido().getId());
                dto.setPedido(p);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        dto.setId(b.getId());
        dto.setCep(b.getCep());
        dto.setLogradouro(b.getLogradouro());
        dto.setNumero(b.getNumero());
        dto.setComplemento(b.getComplemento());
        dto.setBairro(b.getBairro());
        dto.setCidade(b.getCidade());
        dto.setEstado(b.getEstado());

        return dto;
    }

    //  ---------------------> CONVERTER LISTA BUSINESS PARA LISTA DTO
    private List<EnderecoCobrancaDTO> listToDTO (List<EnderecoCobranca> listB) {
        List<EnderecoCobrancaDTO> listDTO = new ArrayList<>();

        for (EnderecoCobranca b : listB) {
            listDTO.add(this.businessToDTO(b));
        }

        return listDTO;
    }


    //  ---------------------> CRIAR
    public EnderecoCobrancaDTO createBillingAddress(EnderecoCobrancaDTO enderecoCobrancaDTO) throws Exception{

        EnderecoCobranca enderecoCobranca = this.dtoToBusiness(enderecoCobrancaDTO);

        enderecoCobranca = enderecoCobrancaRepository.save(enderecoCobranca);

        return businessToDTO(enderecoCobranca);
    }


    //  ---------------------> BUSCAR
    //TODOS
    public List<EnderecoCobrancaDTO> searchAll() {
        List<EnderecoCobranca> list = enderecoCobrancaRepository.findAll();

        return listToDTO(list);
    }

    //UM ENDEREÇO POR ID
    public EnderecoCobrancaDTO searchBillingAddressByRequestId(Long id) {
        if (enderecoCobrancaRepository.existsById(id)) {
            return businessToDTO(enderecoCobrancaRepository.getById(id));
        }

        return null;
    }

    //UM ENDEREÇO POR PEDIDO
    public EnderecoCobrancaDTO searchAdressByRequest(Long idPedido) {
        if (pedidoRepository.existsById(idPedido)) {
            return businessToDTO(enderecoCobrancaRepository.getById(idPedido));
        }

        return null;
    }

    //  ---------------------> ATUALIZAR
    public EnderecoCobrancaDTO updateBillingAddressById(EnderecoCobrancaDTO dto, Long id) {

        Optional<EnderecoCobranca> opt = enderecoCobrancaRepository.findById(id);

        if (opt.isPresent()) {
            EnderecoCobranca update = opt.get();

            if (dto.getPedido() != null) {
                try{
                    Pedido c = pedidoRepository.getById(dto.getPedido().getId());
                    update.setPedido(c);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            if (dto.getCep() != null) {
                update.setCep(dto.getCep());
            }

            if (dto.getLogradouro() != null) {
                update.setLogradouro(dto.getLogradouro());
            }

            if (dto.getNumero() != null) {
                update.setNumero(dto.getNumero());
            }

            if (dto.getBairro() != null) {
                update.setBairro(dto.getBairro());
            }

            if (dto.getCidade() != null) {
                update.setCidade(dto.getCidade());
            }

            if (dto.getEstado() != null) {
                update.setEstado(dto.getEstado());
            }

            enderecoCobrancaRepository.save(update);
            return businessToDTO(update);
        }

        return null;
    }

    //  ---------------------> DELETAR
    public void deleteAdressById(Long id) {
        if (pedidoRepository.existsById(id)) {
            enderecoCobrancaRepository.deleteById(id);
        }
    }
}