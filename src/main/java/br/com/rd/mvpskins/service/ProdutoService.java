package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.*;
import br.com.rd.mvpskins.model.embeddable.CompositeKeySubcategoria;
import br.com.rd.mvpskins.model.entity.*;
import br.com.rd.mvpskins.repository.contract.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    SubcategoriaRepository subcategoriaRepository;
    @Autowired
    RaridadeRepository raridadeRepository;
    @Autowired
    ExteriorRepository exteriorRepository;
    @Autowired
    ColecaoRepository colecaoRepository;

    public List<ProdutoDTO> getList(){
        List<Produto> list = produtoRepository.findAll();
        return this.listToDTO(list);
    }

    public ProdutoDTO getProductById(Long id){
        if (produtoRepository.existsById(id)){
            Produto p = produtoRepository.getById(id);
            return this.businessToDto(p);
        }
        return null;
    }

    public List<ProdutoDTO> getListBySearch(String search){
        List<Produto> list = produtoRepository.getListBySearch(search);

        return this.listToDTO(list);
    }

    public List<ProdutoDTO> getListByCategory(Long idCategory){
        List<Produto> list = produtoRepository.getListByCategory(idCategory);

        return this.listToDTO(list);
    }

    public List<ProdutoDTO> getListBySubcategory(Long idCategory, Long idSubcategory){
        List<Produto> list = produtoRepository.getListBySubcategory(idCategory, idSubcategory);

        return this.listToDTO(list);
    }

    public List<ProdutoDTO> getListByColection(Long idColection){
        List<Produto> list = produtoRepository.getListByColection(idColection);

        return this.listToDTO(list);
    }

    public List<ProdutoDTO> getListByRarity(Long idRarity){
        List<Produto> list = produtoRepository.getListByRarity(idRarity);

        return this.listToDTO(list);
    }

    public List<ProdutoDTO> getListByExterior(Long idExterior){
        List<Produto> list = produtoRepository.getListByExterior(idExterior);

        return this.listToDTO(list);
    }

    public List<ProdutoDTO> getListBetweenValues(Double value1, Double value2){
        List<Produto> list = produtoRepository.getListBetweenValues(value1, value2);

        return this.listToDTO(list);
    }

    public ProdutoDTO businessToDto(Produto p){
        ProdutoDTO dto = new ProdutoDTO();
        CategoriaDTO categoryDTO = new CategoriaDTO();
        CompositeKeySubcategoriaDTO keySubcategoria = new CompositeKeySubcategoriaDTO();
        SubcategoriaDTO subcategoryDTO = new SubcategoriaDTO();
        ColecaoDTO colectionDTO = new ColecaoDTO();
        RaridadeDTO rarityDTO = new RaridadeDTO();
        ExteriorDTO exteriorDTO = new ExteriorDTO();

        dto.setId(p.getId());
        dto.setDescricao(p.getDescricao());

        categoryDTO.setCodigoCategoria(p.getCategoria().getCodigoCategoria());
        categoryDTO.setDescricao(p.getCategoria().getDescricao());
        dto.setCategoria(categoryDTO);

        keySubcategoria.setCategoria(categoryDTO);
        keySubcategoria.setCodigoSubcategoria(p.getSubcategoria().getChaveComposta().getCodigoSubcategoria());
        subcategoryDTO.setChaveComposta(keySubcategoria);
        subcategoryDTO.setDescricao(p.getSubcategoria().getDescricao());
        dto.setSubcategoria(subcategoryDTO);

        colectionDTO.setId(p.getColecao().getId());
        colectionDTO.setDescricao(p.getColecao().getDescricao());
        dto.setColecao(colectionDTO);

        rarityDTO.setId(p.getRaridade().getId());
        rarityDTO.setDescricao(p.getRaridade().getDescricao());
        dto.setRaridade(rarityDTO);

        exteriorDTO.setId(p.getExterior().getId());
        exteriorDTO.setDescricao(p.getExterior().getDescricao());
        dto.setExterior(exteriorDTO);

        dto.setDesgaste(p.getDesgaste());
        dto.setPattern(p.getPattern());
        dto.setTradeLock(p.getTradeLock());
        dto.setUrlImagem(p.getUrlImagem());

        return dto;
    }

    public Produto dtoToBusiness(ProdutoDTO dto){
        Produto p = new Produto();

        p.setId(dto.getId());
        p.setDescricao(p.getDescricao());

        Categoria category = new Categoria();
        category.setCodigoCategoria(dto.getCategoria().getCodigoCategoria());
        category.setDescricao(dto.getCategoria().getDescricao());
        p.setCategoria(category);

        Subcategoria subcategory = new Subcategoria();
        CompositeKeySubcategoria keySubcategoria = new CompositeKeySubcategoria();
        keySubcategoria.setCodigoSubcategoria(dto.getSubcategoria().getChaveComposta().getCodigoSubcategoria());
        keySubcategoria.setCategoria(category);
        subcategory.setChaveComposta(keySubcategoria);
        subcategory.setDescricao(dto.getSubcategoria().getDescricao());
        p.setSubcategoria(subcategory);

        Colecao colection = new Colecao();
        colection.setId(dto.getColecao().getId());
        colection.setDescricao(dto.getColecao().getDescricao());
        p.setColecao(colection);

        Raridade rarity = raridadeRepository.getById(dto.getRaridade().getId());
        rarity.setId(dto.getRaridade().getId());
        rarity.setDescricao(dto.getRaridade().getDescricao());
        p.setRaridade(rarity);

        Exterior exterior = exteriorRepository.getById(dto.getExterior().getId());
        exterior.setId(dto.getExterior().getId());
        exterior.setDescricao(dto.getExterior().getDescricao());
        p.setExterior(exterior);

        p.setDesgaste(dto.getDesgaste());
        p.setPattern(dto.getPattern());
        p.setTradeLock(dto.getTradeLock());
        p.setUrlImagem(dto.getUrlImagem());

        return p;
    }

    public List<ProdutoDTO> listToDTO(List<Produto> list){
        List<ProdutoDTO> listDto = new ArrayList<ProdutoDTO>();

        for (Produto p : list){
            listDto.add(this.businessToDto(p));
        }
        return listDto;
    }
}
