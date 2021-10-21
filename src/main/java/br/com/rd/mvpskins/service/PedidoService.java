package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.PedidoDTO;
import br.com.rd.mvpskins.model.entity.Pedido;
import br.com.rd.mvpskins.repository.contract.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    //  ---------------------> CONVERTER PARA BUSINESS
    private Pedido dtoToBusiness (PedidoDTO dto) {
        Pedido b = new Pedido();

        b.setId(dto.getId());
        b.setNf(dto.getNf());
        b.setQuantidade(dto.getQuantidade());
        b.setIcms(dto.getIcms());
        b.setPis(dto.getPis());
        b.setCofins(dto.getCofins());
        b.setIpi(dto.getIpi());
        b.setDesconto(dto.getDesconto());
        b.setValor_bruto(dto.getValor_bruto());
        b.setValor_liquido(dto.getValor_liquido());

        return b;
    }

    //  ---------------------> CONVERTER PARA DTO
    private PedidoDTO businessToDTO (Pedido b) {
        PedidoDTO dto = new PedidoDTO();

        dto.setId(b.getId());
        dto.setNf(b.getNf());
        dto.setQuantidade(b.getQuantidade());
        dto.setIcms(b.getIcms());
        dto.setPis(b.getPis());
        dto.setCofins(b.getCofins());
        dto.setIpi(b.getIpi());
        dto.setDesconto(b.getDesconto());
        dto.setValor_bruto(b.getValor_bruto());
        dto.setValor_liquido(b.getValor_liquido());

        return dto;
    }

    //  ---------------------> CONVERTER LISTA BUSINESS PARA LISTA DTO
    private List<PedidoDTO> listToDTO (List<Pedido> listB) {
        List<PedidoDTO> listDTO = new ArrayList<>();

        for (Pedido b : listB) {
            listDTO.add(this.businessToDTO(b));
        }

        return listDTO;
    }


    //  ---------------------> CRIAR
    public PedidoDTO criar (PedidoDTO pedidoDTO) {
        Pedido pedido;
        pedido = pedidoRepository.save(dtoToBusiness(pedidoDTO));

        return businessToDTO(pedido);
    }


    //  ---------------------> BUSCAR
    //TODOS OS PEDIDOS
    public List<PedidoDTO> procurarTodos() {
        List<Pedido> list = pedidoRepository.findAll();

        return listToDTO(list);
    }

    //UM PEDIDO POR ID
    public PedidoDTO procurarId(Long id) {
        if (pedidoRepository.existsById(id)) {
            return businessToDTO(pedidoRepository.getById(id));
        }

        return null;
    }

    //  ---------------------> ATUALIZAR
    public PedidoDTO atualizar(PedidoDTO dto, Long id) {

        Optional<Pedido> opt = pedidoRepository.findById(id);
        Pedido pedido = dtoToBusiness(dto);

        if (opt.isPresent()) {
            Pedido update = opt.get();

            if (pedido.getNf() != null) {
                update.setNf(pedido.getNf());
            }

            if (pedido.getQuantidade() != null) {
                update.setQuantidade(pedido.getQuantidade());
            }

            if (pedido.getIcms() != null) {
                update.setIcms(pedido.getIcms());
            }

            if (pedido.getPis() != null) {
                update.setPis(pedido.getPis());
            }

            if (pedido.getCofins() != null) {
                update.setCofins(pedido.getCofins());
            }

            if (pedido.getIpi() != null) {
                update.setIpi(pedido.getIpi());
            }

            if (pedido.getDesconto() != null) {
                update.setDesconto(pedido.getDesconto());
            }

            if (pedido.getValor_bruto() != null) {
                update.setValor_bruto(pedido.getValor_bruto());
            }

            if (pedido.getValor_liquido() != null) {
                update.setValor_liquido(pedido.getValor_liquido());
            }

            pedidoRepository.save(update);
            return businessToDTO(update);
        }

        return null;
    }

    //  ---------------------> DELETAR
    public void deletar(Long id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
        }
    }
}