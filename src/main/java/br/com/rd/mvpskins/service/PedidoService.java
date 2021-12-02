package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.ClienteDTO;
import br.com.rd.mvpskins.model.dto.FormaPagamentoDTO;
import br.com.rd.mvpskins.model.dto.ItensPedidoDTO;
import br.com.rd.mvpskins.model.dto.PedidoDTO;
import br.com.rd.mvpskins.model.entity.*;
import br.com.rd.mvpskins.repository.contract.ClienteRepository;
import br.com.rd.mvpskins.repository.contract.FormaPagamentoRepository;
import br.com.rd.mvpskins.repository.contract.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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

    @Autowired
    FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    FormaPagamentoService formaPagamentoService;

    @Autowired
    ItensPedidoService itensPedidoService;

    @Autowired
    EstoqueService estoqueService;

    @Autowired
    EmailService emailService;

    //  ---------------------> CONVERTER PARA BUSINESS
    private Pedido dtoToBusiness (PedidoDTO dto) {
        Pedido b = new Pedido();

        //        ===> CLIENTE
        if (dto.getCliente().getCodigoCliente() != null) {
            try {
                Cliente c = clienteRepository.getById(dto.getCliente().getCodigoCliente());
                b.setCliente(c);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //        ===> FORMA PAGAMENTO
        if (dto.getFormaPagamento().getId() != null){
            try {
                FormaPagamento f = formaPagamentoRepository.getById(dto.getFormaPagamento().getId());
                b.setFormaPagamento(f);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        b.setDataRegistro(dto.getDataRegistro());
        b.setDescontoProduto(dto.getDescontoProduto());
        b.setValorBruto(dto.getValorBruto());
        b.setValorLiquido(dto.getValorLiquido());
        b.setStatus(dto.getStatus());
        b.setParcelas(dto.getParcelas());

        return b;
    }

    //  ---------------------> CONVERTER PARA DTO
    private PedidoDTO businessToDTO (Pedido b) {
        PedidoDTO dto = new PedidoDTO();

        //        ===> CLIENTE
        if (b.getCliente().getCodigoCliente() != null) {

            ClienteDTO c = clienteService.searchClientById(b.getCliente().getCodigoCliente());
            dto.setCliente(c);

        }
        //        ===> FORMA PAGAMENTO
        if (b.getFormaPagamento().getId() != null){

            FormaPagamentoDTO f = formaPagamentoService.searchID(b.getFormaPagamento().getId());
            dto.setFormaPagamento(f);

        }

        dto.setId(b.getId());
        dto.setDataRegistro(b.getDataRegistro());
        dto.setDescontoProduto(b.getDescontoProduto());
        dto.setValorBruto(b.getValorBruto());
        dto.setValorLiquido(b.getValorLiquido());
        dto.setStatus(b.getStatus());
        dto.setParcelas(b.getParcelas());

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
    public PedidoDTO createOrder(PedidoDTO pedidoDTO) throws Exception{

        Pedido pedido = this.dtoToBusiness(pedidoDTO);

        pedido.setDataRegistro(new Date());
        pedido.setStatus(false);

        if(pedido.getDescontoProduto()==null){
            pedido.setDescontoProduto(0.0);
        }
        pedido.setValorLiquido(pedido.getValorBruto()-pedido.getDescontoProduto());
        pedido = pedidoRepository.save(pedido);

        return businessToDTO(pedido);

    }


    //  ---------------------> BUSCAR
    //TODOS OS PEDIDOS
    public List<PedidoDTO> searchAll() {
        List<Pedido> list = pedidoRepository.findAll();

        return listToDTO(list);
    }

    //TODOS OS PRODUTOS COMPRADOS POR UM CLIENTE
    public List<PedidoDTO> searchAllClientOrders(Long idCliente) {
        List<Pedido> list = pedidoRepository.searchPedidosCliente(idCliente);

        return listToDTO(list);
    }

    //UM PEDIDO POR ID
    public PedidoDTO searchOrderById(Long id) {
        if (pedidoRepository.existsById(id)) {
            return businessToDTO(pedidoRepository.getById(id));
        }

        return null;
    }

    //  ---------------------> ATUALIZAR
    public PedidoDTO updateOrderById(PedidoDTO dto, Long id) {

        Optional<Pedido> opt = pedidoRepository.findById(id);
        Pedido pedido = dtoToBusiness(dto);

        if (opt.isPresent()) {
            Pedido update = opt.get();

            if (pedido.getCliente().getCodigoCliente() != null) {
                try{
                    Cliente c = clienteRepository.getById(pedido.getCliente().getCodigoCliente());
                    update.setCliente(c);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            if(pedido.getFormaPagamento().getId() != null){
                try{
                    FormaPagamento f = formaPagamentoRepository.getById(pedido.getFormaPagamento().getId());
                    update.setFormaPagamento(f);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            if (pedido.getDescontoProduto() != null) {
                update.setDescontoProduto(pedido.getDescontoProduto());
            }

            if (pedido.getValorBruto() != null) {
                update.setValorBruto(pedido.getValorBruto());
            }


            if (pedido.getStatus() != null) {
                update.setStatus(pedido.getStatus());
            }

            update.setValorLiquido(update.getValorBruto()-update.getDescontoProduto());
            pedidoRepository.save(update);
            return businessToDTO(update);
        }

        return null;
    }

    //  ---------------------> DELETAR
    public void deleteOrderById(Long id) {
        if (pedidoRepository.existsById(id)) {
            Pedido p = pedidoRepository.getById(id);

            List<ItensPedidoDTO> listaProdutosPedido = itensPedidoService.searchItemsByOrder(id);

            for(ItensPedidoDTO i : listaProdutosPedido){
                estoqueService.updateCancelledProduct(i.getId().getProduto().getId());
            }

            p.setStatus(false);
        }
    }

    public void sendEmailPurchaseSuccess(Long idPedido){
        if(pedidoRepository.existsById(idPedido)){
            Pedido p = pedidoRepository.getById(idPedido);
            PedidoDTO pedidoDTO = businessToDTO(p);

            String email = pedidoDTO.getCliente().getEmailCliente();
            if(pedidoDTO.getFormaPagamento().getId()==3){
                emailService.sendEmailPurchaseSuccess(pedidoDTO, email);
                emailService.sendEmailTicket(pedidoDTO);
            } else {
                emailService.sendEmailPurchaseSuccess(pedidoDTO, email);
            }
        }
    }


}