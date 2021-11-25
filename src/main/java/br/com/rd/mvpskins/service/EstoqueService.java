package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.ProdutoDTO;
import br.com.rd.mvpskins.model.embeddable.CompositeKeyEstoque;
import br.com.rd.mvpskins.model.entity.ContaSteam;
import br.com.rd.mvpskins.model.entity.Estoque;
import br.com.rd.mvpskins.model.entity.Produto;
import br.com.rd.mvpskins.repository.contract.ContaSteamRepository;
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
  
    @Autowired
    ContaSteamRepository contaSteamRepository;


    //Retorna todos os produtos em estoque
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


    //Retorna todos os produtos fora de estoque
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

    //Verifica se o produto esta em Estoque
    public Boolean checkProductInventory(Long idProduto){
        return estoqueRepository.checkProductInventory(idProduto);
    }

    //Atualiza a quantidade de produto para 0(false)
    public void updateSelledProduct(Long idProduto){
        if(estoqueRepository.checkProductInventory(idProduto)){
            CompositeKeyEstoque keyEstoque = new CompositeKeyEstoque();
            Produto p = produtoRepository.getById(idProduto);
            ContaSteam c = contaSteamRepository.getById(1l);

            keyEstoque.setProduto(p);
            keyEstoque.setContaSteam(c);

            if(estoqueRepository.existsById(keyEstoque)){
                Estoque e = estoqueRepository.getById(keyEstoque);
                e.setFlagQuantidade(false);

                estoqueRepository.save(e);
            }
        }
    }

    //Atualiza a quantidade de produto para 1(true)
    public void updateCancelledProduct(Long idProduto){
        if(!estoqueRepository.checkProductInventory(idProduto)){
            CompositeKeyEstoque keyEstoque = new CompositeKeyEstoque();
            Produto p = produtoRepository.getById(idProduto);
            ContaSteam c = contaSteamRepository.getById(1l);

            keyEstoque.setProduto(p);
            keyEstoque.setContaSteam(c);

            if(estoqueRepository.existsById(keyEstoque)){
                Estoque e = estoqueRepository.getById(keyEstoque);
                e.setFlagQuantidade(true);

                estoqueRepository.save(e);
            }
        }
    }

}
