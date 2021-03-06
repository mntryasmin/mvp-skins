package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.*;
import br.com.rd.mvpskins.model.embeddable.CompositeKeyPreco;
import br.com.rd.mvpskins.model.embeddable.CompositeKeySubcategoria;
import br.com.rd.mvpskins.model.entity.*;
import br.com.rd.mvpskins.repository.contract.CategoriaPrecoRepository;
import br.com.rd.mvpskins.repository.contract.PrecoRepository;
import br.com.rd.mvpskins.repository.contract.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PrecoService {

    @Autowired
    PrecoRepository precoRepository;
    @Autowired
    CategoriaPrecoRepository categoriaPrecoRepository;
    @Autowired
    ProdutoRepository produtoRepository;

    public List<PrecoDTO> getList(){
        List<Preco> list = precoRepository.findAll();
        return this.listToDTO(list);
    }

    public List<PrecoDTO> getPriceById(Long idP, Long idC){


        if(produtoRepository.existsById(idP) && categoriaPrecoRepository.existsById(idC)){

            List<Preco> lista = precoRepository.filtrarValorProduto(idC, idP);

            if(lista != null){
                return this.listToDTO(lista);
            }
        }
        return null;
    }

    public Double getLastPrice(Long idProduto, Long idCatPreco){
        List<PrecoDTO> lista = this.getPriceById(idProduto, idCatPreco);
        PrecoDTO preco = lista.get(0);
        for(PrecoDTO p : lista){
            if(p.getChaveComposta().getDtVigencia().isAfter(preco.getChaveComposta().getDtVigencia())){
                preco = p;
            }
        }
        return preco.getVlPreco();
    }

    public Preco dtoToBusiness(PrecoDTO dto){
        Preco p = new Preco();
        CompositeKeyPreco key = new CompositeKeyPreco();
        CategoriaPreco catPreco = new CategoriaPreco();
        Produto produto = new Produto();

        catPreco.setId(dto.getChaveComposta().getCategoriaPreco().getId());
        catPreco.setDescricao(dto.getChaveComposta().getCategoriaPreco().getDescricao());
        key.setCategoriaPreco(catPreco);

        produto.setId(dto.getChaveComposta().getProduto().getId());
        produto.setDescricao(dto.getChaveComposta().getProduto().getDescricao());

        Categoria category = new Categoria();
        category.setCodigoCategoria(dto.getChaveComposta().getProduto().getCategoria().getCodigoCategoria());
        category.setDescricao(dto.getChaveComposta().getProduto().getCategoria().getDescricao());
        produto.setCategoria(category);

        Subcategoria subcategory = new Subcategoria();
        CompositeKeySubcategoria keySubcategoria = new CompositeKeySubcategoria();
        keySubcategoria.setCodigoSubcategoria(dto.getChaveComposta().getProduto().getSubcategoria().getChaveComposta().getCodigoSubcategoria());
        keySubcategoria.setCategoria(category);
        subcategory.setChaveComposta(keySubcategoria);
        subcategory.setDescricao(dto.getChaveComposta().getProduto().getSubcategoria().getDescricao());
        produto.setSubcategoria(subcategory);

        Colecao colection = new Colecao();
        colection.setId(dto.getChaveComposta().getProduto().getColecao().getId());
        colection.setDescricao(dto.getChaveComposta().getProduto().getColecao().getDescricao());
        produto.setColecao(colection);

        Raridade rarity = new Raridade();
        rarity.setId(dto.getChaveComposta().getProduto().getRaridade().getId());
        rarity.setDescricao(dto.getChaveComposta().getProduto().getRaridade().getDescricao());
        produto.setRaridade(rarity);

        Exterior exterior = new Exterior();
        exterior.setId(dto.getChaveComposta().getProduto().getExterior().getId());
        exterior.setDescricao(dto.getChaveComposta().getProduto().getExterior().getDescricao());
        produto.setExterior(exterior);

        produto.setDesgaste(dto.getChaveComposta().getProduto().getDesgaste());
        produto.setPattern(dto.getChaveComposta().getProduto().getPattern());
        produto.setTradeLock(dto.getChaveComposta().getProduto().getTradeLock());
        produto.setUrlImagem(dto.getChaveComposta().getProduto().getUrlImagem());
        key.setProduto(produto);
        key.setDtVigencia(dto.getChaveComposta().getDtVigencia());

        p.setChaveComposta(key);
        p.setVlPreco(dto.getVlPreco());

        return p;
    }

    public PrecoDTO businessToDTO(Preco p){
        PrecoDTO dto = new PrecoDTO();
        CompositeKeyPrecoDTO key = new CompositeKeyPrecoDTO();
        CategoriaPrecoDTO catPreco = new CategoriaPrecoDTO();
        ProdutoDTO produto = new ProdutoDTO();

        catPreco.setId(p.getChaveComposta().getCategoriaPreco().getId());
        catPreco.setDescricao(p.getChaveComposta().getCategoriaPreco().getDescricao());
        key.setCategoriaPreco(catPreco);

        produto.setId(p.getChaveComposta().getProduto().getId());
        produto.setDescricao(p.getChaveComposta().getProduto().getDescricao());

        CategoriaDTO category = new CategoriaDTO();
        category.setCodigoCategoria(p.getChaveComposta().getProduto().getCategoria().getCodigoCategoria());
        category.setDescricao(p.getChaveComposta().getProduto().getCategoria().getDescricao());
        produto.setCategoria(category);

        SubcategoriaDTO subcategory = new SubcategoriaDTO();
        CompositeKeySubcategoriaDTO keySubcategory = new CompositeKeySubcategoriaDTO();
        keySubcategory.setCodigoSubcategoria(p.getChaveComposta().getProduto().getSubcategoria().getChaveComposta().getCodigoSubcategoria());
        keySubcategory.setCategoria(category);
        subcategory.setChaveComposta(keySubcategory);
        subcategory.setDescricao(p.getChaveComposta().getProduto().getSubcategoria().getDescricao());
        produto.setSubcategoria(subcategory);

        ColecaoDTO colection = new ColecaoDTO();
        colection.setId(p.getChaveComposta().getProduto().getColecao().getId());
        colection.setDescricao(p.getChaveComposta().getProduto().getColecao().getDescricao());
        produto.setColecao(colection);

        RaridadeDTO rarity = new RaridadeDTO();
        rarity.setId(p.getChaveComposta().getProduto().getRaridade().getId());
        rarity.setDescricao(p.getChaveComposta().getProduto().getRaridade().getDescricao());
        produto.setRaridade(rarity);

        ExteriorDTO exterior = new ExteriorDTO();
        exterior.setId(p.getChaveComposta().getProduto().getExterior().getId());
        exterior.setDescricao(p.getChaveComposta().getProduto().getExterior().getDescricao());
        produto.setExterior(exterior);

        produto.setDesgaste(p.getChaveComposta().getProduto().getDesgaste());
        produto.setPattern(p.getChaveComposta().getProduto().getPattern());
        produto.setTradeLock(p.getChaveComposta().getProduto().getPattern());
        produto.setUrlImagem(p.getChaveComposta().getProduto().getUrlImagem());
        key.setProduto(produto);
        key.setDtVigencia(p.getChaveComposta().getDtVigencia());

        dto.setChaveComposta(key);
        dto.setVlPreco(p.getVlPreco());

        return dto;
    }

    public List<PrecoDTO> listToDTO(List<Preco> list){
        List<PrecoDTO> listDTO = new ArrayList<PrecoDTO>();

        for (Preco p : list){
            listDTO.add(this.businessToDTO(p));
        }
        return listDTO;
    }
}
