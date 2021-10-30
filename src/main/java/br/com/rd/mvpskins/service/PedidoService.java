package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.ClienteDTO;
import br.com.rd.mvpskins.model.dto.EmpresaDTO;
import br.com.rd.mvpskins.model.dto.FormaPagamentoDTO;
import br.com.rd.mvpskins.model.dto.PedidoDTO;
import br.com.rd.mvpskins.model.entity.*;
import br.com.rd.mvpskins.repository.contract.ClienteRepository;
import br.com.rd.mvpskins.repository.contract.EmpresaRepository;
import br.com.rd.mvpskins.repository.contract.FormaPagamentoRepository;
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
    FormaPagamentoService formaPagamentoService;

    @Autowired
    FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    EmpresaService companyService;

    @Autowired
    EmpresaRepository companyRepository;

    @Autowired
    ClienteService clientService;

    @Autowired
    ClienteRepository clientRepository;

    //  ---------------------> CONVERTER PARA BUSINESS
    private Pedido dtoToBusiness (PedidoDTO dto) {
        Pedido b = new Pedido();

        //        ===> FORMPAYMENT
        if (dto.getFormaPagamento() != null) {
            FormaPagamento f = formaPagamentoRepository.getById(dto.getFormaPagamento().getId());

            b.setFormaPagamento(f);
        }

        //        ===> COMPANY
        if (dto.getEmpresa() != null) {
            Empresa c = companyRepository.getById(dto.getEmpresa().getId());

            b.setEmpresa(c);
        }

        //        ===> CLIENT
        if (dto.getCliente() != null) {
            Cliente c = clientRepository.getById(dto.getCliente().getCodigoCliente());

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

        //        ===> FORMPAYMENT
        if (b.getFormaPagamento() != null) {
            FormaPagamentoDTO f = formaPagamentoService.searchID(b.getFormaPagamento().getId());

            dto.setFormaPagamento(f);
        }

        //        ===> COMPANY
        if (b.getEmpresa() != null) {
            EmpresaDTO c = companyService.searchID(b.getEmpresa().getIdEmpresa());

            dto.setEmpresa(c);
        }

        //        ===> CLIENT
        if (b.getCliente() != null) {
            ClienteDTO c = clientService.searchClienteById(b.getCliente().getCodigoCliente());

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

        //        ===> COMPANY
        if (pedidoDTO.getEmpresa() != null) {
            Long idCompany = pedido.getEmpresa().getIdEmpresa();
            Empresa e;

            if (idCompany != null) {
                e = this.companyRepository.getById(idCompany);
            } else {
                e = this.companyRepository.save(pedido.getEmpresa());
            }

            pedido.setEmpresa(e);
        }

        //        ===> CLIENT
        if (pedidoDTO.getCliente() != null) {
            Long idClient = pedido.getCliente().getCodigoCliente();
            Cliente c;

            if (idClient != null) {
                c = this.clientRepository.getById(idClient);
            } else {
                c = this.clientRepository.save(pedido.getCliente());
            }

            pedido.setCliente(c);
        }

        //        ===> FORMPAYMENT
        if (pedidoDTO.getFormaPagamento() != null) {
            Long idFormPayment = pedido.getFormaPagamento().getId();
            FormaPagamento f;

            if (idFormPayment != null) {
                f = this.formaPagamentoRepository.getById(idFormPayment);
            } else {
                f = this.formaPagamentoRepository.save(pedido.getFormaPagamento());
            }

            pedido.setFormaPagamento(f);
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

            if (pedido.getEmpresa() != null) {
                update.setEmpresa(pedido.getEmpresa());
            }

            if (pedido.getCliente() != null) {
                update.setCliente(pedido.getCliente());
            }

            if (pedido.getFormaPagamento() != null) {
                update.setFormaPagamento(pedido.getFormaPagamento());
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