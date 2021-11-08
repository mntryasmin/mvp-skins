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
    private NF dtoToBusiness(NFDTO dto) {
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
    private NFDTO businessToDTO(NF b) {
        NFDTO dto = new NFDTO();

        //        ===> PEDIDO
        if (b.getPedido() != null) {
            PedidoDTO r = pedidoService.searchOrderById(b.getPedido().getId());

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
            listDTO.add(this.businessToDTO(b));
        }

        return listDTO;
    }


    //  ---------------------> CRIAR
    public NFDTO createInvoice(NFDTO nfDTO) {
        NF nf = this.dtoToBusiness(nfDTO);

        //        ===> PEDIDO
        if (nfDTO.getPedido().getId() != null) {
            Long idRequest = nfDTO.getPedido().getId();
            Pedido pedido = this.pedidoRepository.getById(idRequest);

            nf.setPedido(pedido);
        }

        //        ===> TIPO NF
        if (nfDTO.getTipoNF().getId() != null) {
            Long idTypeNF = nfDTO.getTipoNF().getId();
            TipoNF t = this.tipoNFRepository.getById(idTypeNF);

            nf.setTipoNF(t);
        }

        //        ===> PAGAMENTO
        if (nf.getPedido().getFormaPagamento() != null) {
            FormaPagamento f = nf.getPedido().getFormaPagamento();

            nf.setFormaPagamento(f);
        }

        //        ===> EMPRESA
        if(nfDTO.getEmpresa().getId() != null) {
            Long idCompany = nfDTO.getEmpresa().getId();
            Empresa em = this.empresaRepository.getById(idCompany);

            nf.setEmpresa(em);
        }

        //        ===> CLIENTE
        if(nf.getPedido().getCliente() != null) {
            Cliente cl = nf.getPedido().getCliente();

            nf.setCliente(cl);
        }

        Double valorBruto = nf.getPedido().getValorBruto();
        //Cálculo dos impostos
        Double icms = valorBruto*0.18;
        Double pis = valorBruto*0.065;
        Double cofins = valorBruto*0.03;
        Double ipi = valorBruto*0.1;
        Double desconto = nf.getPedido().getDescontoProduto();

        //Impostos não são descontados do valor bruto ao consumidor final
        Double valorLiquido = valorBruto-desconto;

        nf.setIcms(icms);
        nf.setIpi(ipi);
        nf.setPis(pis);
        nf.setCofins(cofins);
        nf.setDescontoCompra(desconto);
        nf.setValorBruto(valorBruto);
        nf.setValorLiquido(valorLiquido);

        nf.setDataRegistro(new Date());
        nf = nfRepository.save(nf);

        return businessToDTO(nf);
    }


    //  ---------------------> BUSCAR
    //TODAS AS NF'S
    public List<NFDTO> searchAllInvoices() {
        List<NF> list = nfRepository.findAll();

        return listToDTO(list);
    }

    //TODAS AS NF'S POR CLIENTE
    public List<NFDTO> findInvoicesByClient(Long id) {
        List<NF> list = nfRepository.findByClienteCodigoCliente(id);

        return listToDTO(list);
    }

    //UMA NF POR ID
    public NFDTO searchInvoiceByID(Long id) {
        if (nfRepository.existsById(id)) {
            return businessToDTO(nfRepository.getById(id));
        }

        return null;
    }

    //  ---------------------> ATUALIZAR
    public NFDTO updateInvoice(NFDTO dto, Long id) {

        Optional<NF> opt = nfRepository.findById(id);
        NF nf = dtoToBusiness(dto);

        if (opt.isPresent()) {
            NF update = opt.get();

            if (nf.getPedido() != null) {
                Long idPedido = nf.getPedido().getId();

                if(pedidoRepository.existsById(idPedido)){
                    Pedido pedido = pedidoRepository.getById(idPedido);
                    update.setPedido(pedido);
                }
            }

            if (nf.getTipoNF() != null) {
                Long idTipoNF = nf.getTipoNF().getId();

                if(tipoNFRepository.existsById(idTipoNF)){
                    TipoNF tipoNF = tipoNFRepository.getById(idTipoNF);
                    update.setTipoNF(tipoNF);
                }
            }

            if (nf.getEmpresa() != null) {
                Long idEmpresa = nf.getEmpresa().getIdEmpresa();
                if(empresaRepository.existsById(idEmpresa)){
                    Empresa empresa = empresaRepository.getById(idEmpresa);
                    update.setEmpresa(empresa);
                }
            }

            if (nf.getCliente() != null) {
                Long idCliente = nf.getCliente().getCodigoCliente();
                if(clienteRepository.existsById(idCliente)){
                    Cliente cliente = clienteRepository.getById(idCliente);
                    update.setCliente(nf.getCliente());
                }
            }

            if (nf.getFormaPagamento() != null) {
                Long idFormaPgto = nf.getFormaPagamento().getId();
                if(formaPagamentoRepository.existsById(idFormaPgto)){
                    FormaPagamento formaPagamento = formaPagamentoRepository.getById(idFormaPgto);
                    update.setFormaPagamento(nf.getFormaPagamento());
                }
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

            Double valorLiquido = update.getValorBruto()-update.getDescontoCompra();
            update.setValorLiquido(valorLiquido);
            nfRepository.save(update);
            return businessToDTO(update);
        }

        return null;
    }

    //  ---------------------> DELETAR
    public void deleteInvoice(Long id) {
        if (nfRepository.existsById(id)) {
            nfRepository.deleteById(id);
        }
    }
}