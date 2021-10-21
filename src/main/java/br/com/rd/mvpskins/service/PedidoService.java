package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.PedidoDTO;
import br.com.rd.mvpskins.model.entity.Pedido;
import br.com.rd.mvpskins.repository.contract.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
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
        b.setCodigo_empresa(dto.getCodigo_empresa());
        b.setCodigo_cliente(dto.getCodigo_cliente());
        b.setCodigo_forma_pagamento(dto.getCodigo_forma_pagamento());
        b.setData_emissao(dto.getData_emissao());
        b.setDesconto_produto(dto.getDesconto_produto());
        b.setValor_bruto(dto.getValor_bruto());
        b.setValor_liquido(dto.getValor_liquido());

        return b;
    }

    //  ---------------------> CONVERTER PARA DTO
    private PedidoDTO businessToDTO (Pedido b) {
        PedidoDTO dto = new PedidoDTO();

        dto.setId(b.getId());
        dto.setCodigo_empresa(b.getCodigo_empresa());
        dto.setCodigo_cliente(b.getCodigo_cliente());
        dto.setCodigo_forma_pagamento(b.getCodigo_forma_pagamento());
        dto.setData_emissao(b.getData_emissao());
        dto.setDesconto_produto(b.getDesconto_produto());
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
        Pedido pedido = dtoToBusiness(pedidoDTO);
        pedido.setData_emissao(new Date());
        pedido = pedidoRepository.save(pedido);

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

            if (pedido.getCodigo_empresa() != null) {
                update.setCodigo_empresa(pedido.getCodigo_empresa());
            }

            if (pedido.getCodigo_cliente() != null) {
                update.setCodigo_cliente(pedido.getCodigo_cliente());
            }

            if (pedido.getCodigo_forma_pagamento() != null) {
                update.setCodigo_forma_pagamento(pedido.getCodigo_forma_pagamento());
            }

            if (pedido.getData_emissao() != null) {
                update.setData_emissao(pedido.getData_emissao());
            }

            if (pedido.getDesconto_produto() != null) {
                update.setDesconto_produto(pedido.getDesconto_produto());
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