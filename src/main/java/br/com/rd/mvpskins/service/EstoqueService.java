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

    public List<ProdutoDTO> produtoEmEstoque(){
        List<Estoque> listaEstoque = estoqueRepository.findAllByFlagQuantidadeTrue();
        List<Produto> listaProduto = new ArrayList<>();

        for(Estoque e : listaEstoque){
            Long id = e.getCompositeKeyEstoque().getProduto().getId();
            listaProduto.add(produtoRepository.getById(id));
        }

        List<ProdutoDTO> listaProdutoDTO = produtoService.listToDTO(listaProduto);

        return listaProdutoDTO;
    }

    public List<ProdutoDTO> produtoForaDeEstoque(){
        List<Estoque> listaEstoque = estoqueRepository.findAllByFlagQuantidadeFalse();
        List<Produto> listaProduto = new ArrayList<>();

        for(Estoque e : listaEstoque){
            Long id = e.getCompositeKeyEstoque().getProduto().getId();
            listaProduto.add(produtoRepository.getById(id));
        }

        List<ProdutoDTO> listaProdutoDTO = produtoService.listToDTO(listaProduto);

        return listaProdutoDTO;
    }
}
