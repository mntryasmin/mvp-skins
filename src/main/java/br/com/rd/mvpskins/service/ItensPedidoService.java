package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.ItensPedidoCompositeKeyDTO;
import br.com.rd.mvpskins.model.dto.ItensPedidoDTO;
import br.com.rd.mvpskins.model.dto.PedidoDTO;
import br.com.rd.mvpskins.model.dto.ProdutoDTO;
import br.com.rd.mvpskins.model.embeddable.ItensPedidoCompositeKey;
import br.com.rd.mvpskins.model.entity.ItensPedido;
import br.com.rd.mvpskins.model.entity.Pedido;
import br.com.rd.mvpskins.model.entity.Produto;
import br.com.rd.mvpskins.repository.contract.ItensPedidoRepository;
import br.com.rd.mvpskins.repository.contract.PedidoRepository;
import br.com.rd.mvpskins.repository.contract.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItensPedidoService {

    @Autowired
    ItensPedidoRepository itensPedidoRepository;

    @Autowired
    PedidoService pedidoService;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ProdutoService produtoService;

    @Autowired
    ProdutoRepository produtoRepository;

    //  ---------------------> CONVERTER PARA BUSINESS
    private ItensPedido dtoToBusiness (ItensPedidoDTO dto) {

        //        ===> REQUEST
        ItensPedidoCompositeKey id = new ItensPedidoCompositeKey();
        if (dto.getId().getPedido() != null) {
            Produto p = produtoRepository.getById(dto.getId().getProduto().getId());
            Pedido r = pedidoRepository.getById(dto.getId().getPedido().getId());

            id.setProduto(p);
            id.setPedido(r);
        }

        ItensPedido b = new ItensPedido();
        b.setId(id);
        b.setQuantidade(dto.getQuantidade());
        b.setDesconto(dto.getDesconto());
        b.setValorBruto(dto.getValorBruto());
        b.setValorLiquido(dto.getValorLiquido());

        return b;
    }

    //  ---------------------> CONVERTER PARA DTO
    private ItensPedidoDTO businessToDTO (ItensPedido b) {
//        Pedido request = nfRepository.getById(b.getId().getIdProduct());

        //        ===> REQUEST
        ItensPedidoCompositeKeyDTO id = new ItensPedidoCompositeKeyDTO();
        if (b.getId().getPedido() != null) {
            ProdutoDTO p = produtoService.getProductById(b.getId().getPedido().getId());
            PedidoDTO r = pedidoService.searchID(b.getId().getPedido().getId());

            id.setProduto(p);
            id.setPedido(r);
        }

        ItensPedidoDTO dto = new ItensPedidoDTO();
        dto.setId(id);
        dto.setQuantidade(b.getQuantidade());
        dto.setDesconto(b.getDesconto());
        dto.setValorBruto(b.getValorBruto());
        dto.setValorLiquido(b.getValorLiquido());

        return dto;
    }

    //  ---------------------> CONVERTER LISTA BUSINESS PARA LISTA DTO
    private List<ItensPedidoDTO> listToDTO (List<ItensPedido> listB) {
        List<ItensPedidoDTO> listDTO = new ArrayList<>();

        for (ItensPedido b : listB) {
            listDTO.add(this.businessToDTO(b));
        }

        return listDTO;
    }

    //  ---------------------> CRIAR
    public ItensPedidoDTO create (ItensPedidoDTO itensPedidoDTO) {

            ItensPedido itensPedido = dtoToBusiness(itensPedidoDTO);
            itensPedido = itensPedidoRepository.save(itensPedido);

            return businessToDTO(itensPedido);
    }


    //  ---------------------> BUSCAR
    //TODOS OS ITENS DE PEDIDOS
    public List<ItensPedidoDTO> searchAll() {
        List<ItensPedido> list = itensPedidoRepository.findAll();

        return listToDTO(list);
    }

    //UM ITEM DE PEDIDO POR ID
    public ItensPedidoDTO searchID(Long idProduto, Long idPedido) {

        if (pedidoRepository.existsById(idPedido) && produtoRepository.existsById(idProduto)) {
            ItensPedidoCompositeKey id = new ItensPedidoCompositeKey();
            id.setProduto(produtoRepository.getById(idProduto));
            id.setPedido(pedidoRepository.getById(idPedido));

            if (itensPedidoRepository.existsById(id)) {
                return businessToDTO(itensPedidoRepository.getById(id));
            }
        }

        return null;
    }

    //  ---------------------> ATUALIZAR
    public ItensPedidoDTO update(ItensPedidoDTO dto, Long idProduto, Long idPedido) {

        if (pedidoRepository.existsById(idPedido)) {
            ItensPedidoCompositeKeyDTO id = new ItensPedidoCompositeKeyDTO();
            id.setProduto(produtoService.getProductById(idProduto));
            id.setPedido(pedidoService.searchID(idPedido));
            dto.setId(id);

            ItensPedido itensPedido = dtoToBusiness(dto);
            Optional<ItensPedido> opt = itensPedidoRepository.findById(itensPedido.getId());

            if (opt.isPresent()) {
                ItensPedido update = opt.get();

                if (itensPedido.getQuantidade() != null) {
                    update.setQuantidade(itensPedido.getQuantidade());
                }

                if (itensPedido.getDesconto() != null) {
                    update.setDesconto(itensPedido.getDesconto());
                }

                if (itensPedido.getValorBruto() != null) {
                    update.setValorBruto(itensPedido.getValorBruto());
                }

                if (itensPedido.getValorLiquido() != null) {
                    update.setValorLiquido(itensPedido.getValorLiquido());
                }

                itensPedidoRepository.save(update);
                return businessToDTO(update);
            }
        }

        return null;
    }

    //  ---------------------> DELETAR
    public void delete(Long idProduto, Long idPedido) {
        ItensPedidoCompositeKey id = new ItensPedidoCompositeKey();
        id.setProduto(produtoRepository.getById(idProduto));
        id.setPedido(pedidoRepository.getById(idPedido));

        if (itensPedidoRepository.existsById(id)) {
            itensPedidoRepository.deleteById(id);
        }
    }
}