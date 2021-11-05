package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.*;
import br.com.rd.mvpskins.model.entity.*;
import br.com.rd.mvpskins.repository.contract.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NFService {

    @Autowired
    NFRepository nfRepository;

    @Autowired
    FormaPagamentoService formaPagamentoService;

    @Autowired
    FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    PedidoService pedidoService;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    TipoNFService tipoNFService;

    @Autowired
    TipoNFRepository tipoNFRepository;

    @Autowired
    EmpresaRepository empresaRepository;

    @Autowired
    EmpresaService empresaService;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ClienteService clienteService;

    @Autowired
    FornecedorRepository fornecedorRepository;

    @Autowired
    FornecedorService fornecedorService;

    //  ---------------------> CONVERTER PARA BUSINESS
    private NF businessToDTO(NFDTO dto) {
        NF b = new NF();

        //        ===> PEDIDO
        if (dto.getPedido() != null) {
            Pedido r = pedidoRepository.getById(dto.getPedido().getId());
            b.setPedido(r);
        }

        //        ===> TIPO NF
        if (dto.getTipoNF() != null) {
            TipoNF t = tipoNFRepository.getById(dto.getTipoNF().getId());

            b.setTipoNF(t);
        }

        //        ===> FORMA DE PAGAMENTO
        if (dto.getFormaPagamento() != null) {
            FormaPagamento f = formaPagamentoRepository.getById(dto.getFormaPagamento().getId());

            b.setFormaPagamento(f);
        }

        //        ===> EMPRESA
        if (dto.getEmpresa() != null) {
            Empresa c = empresaRepository.getById(dto.getEmpresa().getId());

            b.setEmpresa(c);
        }

        //        ===> CLIENTE
        if (dto.getCliente() != null) {
            Cliente c = clienteRepository.getById(dto.getCliente().getCodigoCliente());

            b.setCliente(c);
        }

        //        ===> FORNECEDOR
        if (dto.getFornecedor() != null) {
            Fornecedor fo = fornecedorRepository.getById(dto.getFornecedor().getId());

            b.setFornecedor(fo);
        }

        b.setId(dto.getId());
        b.setChaveAcesso(dto.getChaveAcesso());
        b.setNumeroNF(dto.getNumeroNF());
        b.setIcms(dto.getIcms());
        b.setIpi(dto.getIpi());
        b.setPis(dto.getPis());
        b.setCofins(dto.getCofins());
        b.setFlagNF(dto.getFlagNF());
        b.setDataRegistro(dto.getDataRegistro());
        b.setDescontoCompra(dto.getDescontoProduto());
        b.setValorBruto(dto.getValorBruto());
        b.setValorLiquido(dto.getValorLiquido());

        return b;
    }

    //  ---------------------> CONVERTER PARA DTO
    private NFDTO dtoToBusiness(NF b) {
        NFDTO dto = new NFDTO();

        //        ===> PEDIDO
        if (b.getPedido() != null) {
            PedidoDTO r = pedidoService.searchID(b.getPedido().getId());

            dto.setPedido(r);
        }

        //        ===> TIPO NF
        if (b.getTipoNF() != null) {
            TipoNFDTO t = tipoNFService.searchID(b.getTipoNF().getId());

            dto.setTipoNF(t);
        }

        //        ===> FORMA DE PAGAMENTO
        if (b.getFormaPagamento() != null) {
            FormaPagamentoDTO f = formaPagamentoService.searchID(b.getFormaPagamento().getId());

            dto.setFormaPagamento(f);
        }

        //        ===> EMPRESA
        if (b.getEmpresa() != null) {
            EmpresaDTO c = empresaService.searchID(b.getEmpresa().getIdEmpresa());

            dto.setEmpresa(c);
        }

        //        ===> CLIENTE
        if (b.getCliente() != null) {
            ClienteDTO c = clienteService.searchClientById(b.getCliente().getCodigoCliente());

            dto.setCliente(c);
        }

        //        ===> FORNECEDOR
        if (b.getFornecedor() != null) {
            FornecedorDTO f = fornecedorService.searchID(b.getFornecedor().getId());

            dto.setFornecedor(f);
        }

        dto.setId(b.getId());
        dto.setChaveAcesso(b.getChaveAcesso());
        dto.setNumeroNF(b.getNumeroNF());
        dto.setIcms(b.getIcms());
        dto.setIpi(b.getIpi());
        dto.setPis(b.getPis());
        dto.setCofins(b.getCofins());
        dto.setFlagNF(b.getFlagNF());
        dto.setDataRegistro(b.getDataRegistro());
        dto.setDescontoProduto(b.getDescontoCompra());
        dto.setValorBruto(b.getValorBruto());
        dto.setValorLiquido(b.getValorLiquido());

        return dto;
    }

    //  ---------------------> CONVERTER LISTA BUSINESS PARA LISTA DTO
    private List<NFDTO> listToDTO(List<NF> listB) {
        List<NFDTO> listDTO = new ArrayList<>();

        for (NF b : listB) {
            listDTO.add(this.dtoToBusiness(b));
        }

        return listDTO;
    }


    //  ---------------------> CRIAR
    public NFDTO create(NFDTO nfDTO) {
        NF nf = this.businessToDTO(nfDTO);

        //        ===> PEDIDO
        if (nfDTO.getPedido() != null) {
            Long idRequest = nf.getPedido().getId();
            Pedido r;

            if (idRequest != null) {
                r = this.pedidoRepository.getById(idRequest);
            } else {
                r = this.pedidoRepository.save(nf.getPedido());
            }

            nf.setPedido(r);
        }

        //        ===> TIPO NF
        if (nfDTO.getTipoNF() != null) {
            Long idTypeNF = nf.getTipoNF().getId();
            TipoNF t;

            if (idTypeNF != null) {
                t = this.tipoNFRepository.getById(idTypeNF);
            } else {
                t = this.tipoNFRepository.save(nf.getTipoNF());
            }

            nf.setTipoNF(t);
        }

        //        ===> PAGAMENTO
        if (nfDTO.getFormaPagamento() != null) {
            Long idFormPayment = nf.getFormaPagamento().getId();
            FormaPagamento f;

            if (idFormPayment != null) {
                f = this.formaPagamentoRepository.getById(idFormPayment);
            } else {
                f = this.formaPagamentoRepository.save(nf.getFormaPagamento());
            }

            nf.setFormaPagamento(f);
        }

        //        ===> FORNECEDOR
        if(nfDTO.getFornecedor() != null) {
            Long idFornecedor = nf.getFornecedor().getId();
            Fornecedor fo;

            if (idFornecedor != null) {
                fo = this.fornecedorRepository.getById(idFornecedor);
            } else {
                fo = this.fornecedorRepository.save(nf.getFornecedor());
            }

            nf.setFornecedor(fo);
        }

        //        ===> EMPRESA
        if(nfDTO.getEmpresa() != null) {
            Long idEmpresa = nf.getEmpresa().getIdEmpresa();
            Empresa em;

            if (idEmpresa != null) {
                em = this.empresaRepository.getById(idEmpresa);
            } else {
                em = this.empresaRepository.save(nf.getEmpresa());
            }

            nf.setEmpresa(em);
        }

        //        ===> CLIENTE
        if(nfDTO.getCliente() != null) {
            Long idCliente = nf.getCliente().getCodigoCliente();
            Cliente cl;

            if (idCliente != null) {
                cl = this.clienteRepository.getById(idCliente);
            } else {
                cl = this.clienteRepository.save(nf.getCliente());
            }

            nf.setCliente(cl);
        }

        nf.setDataRegistro(new Date());
        nf = nfRepository.save(nf);

        return dtoToBusiness(nf);
    }


    //  ---------------------> BUSCAR
    //TODAS AS NF'S
    public List<NFDTO> searchAll() {
        List<NF> list = nfRepository.findAll();

        return listToDTO(list);
    }

    //TODAS AS NF'S POR CLIENTE
    public List<NFDTO> findByClienteCodigoCliente(Long id) {
        List<NF> list = nfRepository.findByClienteCodigoCliente(id);

        return listToDTO(list);
    }

    //UMA NF POR ID
    public NFDTO searchID(Long id) {
        if (nfRepository.existsById(id)) {
            return dtoToBusiness(nfRepository.getById(id));
        }

        return null;
    }

    //  ---------------------> ATUALIZAR
    public NFDTO update(NFDTO dto, Long id) {

        Optional<NF> opt = nfRepository.findById(id);
        NF nf = businessToDTO(dto);

        if (opt.isPresent()) {
            NF update = opt.get();

            if (nf.getPedido() != null) {
                update.setPedido(nf.getPedido());
            }

            if (nf.getTipoNF() != null) {
                update.setTipoNF(nf.getTipoNF());
            }

            if (nf.getEmpresa() != null) {
                update.setEmpresa(nf.getEmpresa());
            }

            if (nf.getFornecedor() != null) {
                update.setFornecedor(nf.getFornecedor());
            }

            if (nf.getCliente() != null) {
                update.setCliente(nf.getCliente());
            }

            if (nf.getFormaPagamento() != null) {
                update.setFormaPagamento(nf.getFormaPagamento());
            }

            if (nf.getChaveAcesso() != null) {
                update.setChaveAcesso(nf.getChaveAcesso());
            }

            if (nf.getNumeroNF() != null) {
                update.setNumeroNF(nf.getNumeroNF());
            }

            if (nf.getIcms() != null) {
                update.setIcms(nf.getIcms());
            }

            if (nf.getIpi() != null) {
                update.setIpi(nf.getIpi());
            }

            if (nf.getPis() != null) {
                update.setPis(nf.getPis());
            }

            if (nf.getCofins() != null) {
                update.setCofins(nf.getCofins());
            }

            if (nf.getFlagNF() != null) {
                update.setFlagNF(nf.getFlagNF());
            }

            if (nf.getDescontoCompra() != null) {
                update.setDescontoCompra(nf.getDescontoCompra());
            }

            if (nf.getValorBruto() != null) {
                update.setValorBruto(nf.getValorBruto());
            }

            if (nf.getValorLiquido() != null) {
                update.setValorLiquido(nf.getValorLiquido());
            }

            update.setDataRegistro(new Date());
            nfRepository.save(update);
            return dtoToBusiness(update);
        }

        return null;
    }

    //  ---------------------> DELETAR
    public void delete(Long id) {
        if (nfRepository.existsById(id)) {
            nfRepository.deleteById(id);
        }
    }
}