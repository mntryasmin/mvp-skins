package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.ClienteDTO;
import br.com.rd.mvpskins.model.dto.PedidoDTO;
import br.com.rd.mvpskins.model.entity.*;
import br.com.rd.mvpskins.repository.contract.ClienteRepository;
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

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ClienteService clienteService;

    //  ---------------------> CONVERTER PARA BUSINESS
    private Pedido dtoToBusiness (PedidoDTO dto) {
        Pedido b = new Pedido();

        //        ===> CLIENTE
        if (dto.getCliente() != null) {
            Cliente c = clienteRepository.getById(dto.getCliente().getCodigoCliente());

            b.setCliente(c);
        }

        b.setId(dto.getId());
        b.setDataRegistro(dto.getDataRegistro());
        b.setDescontoProduto(dto.getDescontoProduto());
        b.setValorBruto(dto.getValorBruto());
        b.setValorLiquido(dto.getValorLiquido());

        return b;
    }

    //  ---------------------> CONVERTER PARA DTO
    private PedidoDTO businessToDTO (Pedido b) {
        PedidoDTO dto = new PedidoDTO();

        //        ===> CLIENTE
        if (b.getCliente() != null) {
            ClienteDTO c = clienteService.searchClienteById(b.getCliente().getCodigoCliente());

            dto.setCliente(c);
        }

        dto.setId(b.getId());
        dto.setDataRegistro(b.getDataRegistro());
        dto.setDescontoProduto(b.getDescontoProduto());
        dto.setValorBruto(b.getValorBruto());
        dto.setValorLiquido(b.getValorLiquido());

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
    public PedidoDTO create (PedidoDTO pedidoDTO) {
        Pedido pedido = this.dtoToBusiness(pedidoDTO);

        //        ===> CLIENTE
        if(pedidoDTO.getCliente() != null) {
            Long idCliente = pedido.getCliente().getCodigoCliente();
            Cliente cl;

            if (idCliente != null) {
                cl = this.clienteRepository.getById(idCliente);
            } else {
                cl = this.clienteRepository.save(pedido.getCliente());
            }

            pedido.setCliente(cl);
        }

        pedido.setDataRegistro(new Date());
        pedido = pedidoRepository.save(pedido);

        return businessToDTO(pedido);
    }


    //  ---------------------> BUSCAR
    //TODOS OS PEDIDOS
    public List<PedidoDTO> searchAll() {
        List<Pedido> list = pedidoRepository.findAll();

        return listToDTO(list);
    }

    //TODAS OS PRODUTOS COMPRADOS POR UM CLIENTE
    public List<PedidoDTO> searchPedidosCliente (Long idCliente) {
        List<Pedido> list = pedidoRepository.searchPedidosCliente(idCliente);

        return listToDTO(list);
    }

    //UM PEDIDO POR ID
    public PedidoDTO searchID(Long id) {
        if (pedidoRepository.existsById(id)) {
            return businessToDTO(pedidoRepository.getById(id));
        }

        return null;
    }

    //  ---------------------> ATUALIZAR
    public PedidoDTO update(PedidoDTO dto, Long id) {

        Optional<Pedido> opt = pedidoRepository.findById(id);
        Pedido pedido = dtoToBusiness(dto);

        if (opt.isPresent()) {
            Pedido update = opt.get();

            if (pedido.getCliente() != null) {
                update.setCliente(pedido.getCliente());
            }

            if (pedido.getDescontoProduto() != null) {
                update.setDescontoProduto(pedido.getDescontoProduto());
            }

            if (pedido.getValorBruto() != null) {
                update.setValorBruto(pedido.getValorBruto());
            }

            if (pedido.getValorLiquido() != null) {
                update.setValorLiquido(pedido.getValorLiquido());
            }

            update.setDataRegistro(new Date());
            pedidoRepository.save(update);
            return businessToDTO(update);
        }

        return null;
    }

    //  ---------------------> DELETAR
    public void delete(Long id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
        }
    }
}