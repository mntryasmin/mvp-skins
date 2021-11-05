package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.ProdutoDTO;
import br.com.rd.mvpskins.model.entity.Estoque;
import br.com.rd.mvpskins.model.entity.Produto;
import br.com.rd.mvpskins.repository.contract.EstoqueRepository;
import br.com.rd.mvpskins.repository.contract.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    ProdutoService produtoService;

    public List<ProdutoDTO> getListProductIninventory(){
        List<Estoque> listInventory = estoqueRepository.findAllByFlagQuantidadeTrue();
        List<Produto> listProduct = new ArrayList<>();

        for(Estoque e : listInventory){
            Long id = e.getCompositeKeyEstoque().getProduto().getId();
            listProduct.add(produtoRepository.getById(id));
        }

        List<ProdutoDTO> listaProdutoDTO = produtoService.listToDTO(listProduct);

        return listaProdutoDTO;
    }

    public List<ProdutoDTO> getListProductOutInventory(){
        List<Estoque> listInventory = estoqueRepository.findAllByFlagQuantidadeFalse();
        List<Produto> listProduct = new ArrayList<>();

        for(Estoque e : listInventory){
            Long id = e.getCompositeKeyEstoque().getProduto().getId();
            listProduct.add(produtoRepository.getById(id));
        }

        List<ProdutoDTO> listaProdutoDTO = produtoService.listToDTO(listProduct);

        return listaProdutoDTO;
    }
}
