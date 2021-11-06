package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.*;
import br.com.rd.mvpskins.model.embeddable.CompositeKeyFavoritos;
import br.com.rd.mvpskins.model.embeddable.CompositeKeySubcategoria;
import br.com.rd.mvpskins.model.entity.*;
import br.com.rd.mvpskins.repository.contract.ClienteRepository;
import br.com.rd.mvpskins.repository.contract.FavoritosRepository;
import br.com.rd.mvpskins.repository.contract.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FavoritosService {

    @Autowired
    FavoritosRepository favoritosRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ProdutoRepository produtoRepository;

    public List<FavoritosDTO> getList(){
        List<Favoritos> list = favoritosRepository.findAll();

        return this.listToDTO(list);
    }

    public FavoritosDTO getFavoriteById(Long idC, Long idP){
        CompositeKeyFavoritos key = new CompositeKeyFavoritos();

        if(clienteRepository.existsById(idC)){
            Cliente c = clienteRepository.getById(idC);
            key.setCliente(c);
        }
        if (produtoRepository.existsById(idP)){
            Produto p = produtoRepository.getById(idP);
            key.setProduto(p);
        }

        Optional<Favoritos> op = favoritosRepository.findById(key);

        if (op.isPresent()) {
            Favoritos fav = op.get();
            return this.favToDto(fav);
        }
        return null;
    }

    public List<FavoritosDTO> getFavoriteByClientId(Long id){
        List<Favoritos> list = favoritosRepository.getListByClientId(id);

        return this.listToDTO(list);
    }

    public FavoritosDTO createFavorite(FavoritosDTO dto){
        Favoritos fav = new Favoritos();
        Long idC = dto.getChaveComposta().getCliente().getCodigoCliente();
        Long idP = dto.getChaveComposta().getProduto().getId();
        CompositeKeyFavoritos key = new CompositeKeyFavoritos();

        if (dto.getChaveComposta().getCliente() != null){
            if (idC == null){
                return null;
            }
            if(clienteRepository.existsById(idC)){
                Cliente c = clienteRepository.getById(idC);
                key.setCliente(c);
            }else {
                return null;
            }
        }
        if (dto.getChaveComposta().getProduto() != null){
            if (idP == null){
                return null;
            }
            if(produtoRepository.existsById(idP)){
                Produto p = produtoRepository.getById(idP);
                key.setProduto(p);
            }else {
                return null;
            }
            if (favoritosRepository.existsById(key)){
                return this.updateFavorite(idC, idP);
            }

            fav.setChaveComposta(key);
            fav.setFavorito(true);
            fav = favoritosRepository.save(fav);
            return this.favToDto(fav);
        }

        return null;
    }

    public FavoritosDTO updateFavorite(Long idC, Long idP){
        CompositeKeyFavoritos key = new CompositeKeyFavoritos();

        if(clienteRepository.existsById(idC)){
            Cliente c = clienteRepository.getById(idC);
            key.setCliente(c);
        }
        if (produtoRepository.existsById(idP)){
            Produto p = produtoRepository.getById(idP);
            key.setProduto(p);
        }

        Optional<Favoritos> op = favoritosRepository.findById(key);

        if(op.isPresent()){
            Favoritos fav = op.get();

            if(fav.isFavorito()){
                fav.setFavorito(false);
            }else {
                fav.setFavorito(true);
            }

            fav = favoritosRepository.save(fav);
            return this.favToDto(fav);
        }
        return null;
    }

    public Favoritos dtoToFav(FavoritosDTO dto){
        Favoritos fav = new Favoritos();
        Produto product = new Produto();
        CompositeKeyFavoritos key = new CompositeKeyFavoritos();

        Cliente c = new Cliente();
        c.setCodigoCliente(dto.getChaveComposta().getCliente().getCodigoCliente());
        c.setNomeCliente(dto.getChaveComposta().getCliente().getNomeCliente());
        c.setSenhaCliente(dto.getChaveComposta().getCliente().getSenhaCliente());

        Genero g = new Genero();
        g.setCodigoGenero(dto.getChaveComposta().getCliente().getGenero().getCodigoGenero());
        g.setNomeGenero(dto.getChaveComposta().getCliente().getGenero().getNomeGenero());

        c.setGenero(g);
        c.setDataNascimento(dto.getChaveComposta().getCliente().getDataNascimento());
        c.setEmailCliente(dto.getChaveComposta().getCliente().getEmailCliente());
        c.setNumeroTelefone(dto.getChaveComposta().getCliente().getNumeroTelefone());
        c.setTradeLink(dto.getChaveComposta().getCliente().getTradeLink());
        key.setCliente(c);

        product.setId(dto.getChaveComposta().getProduto().getId());
        product.setDescricao(dto.getChaveComposta().getProduto().getDescricao());

        Categoria category = new Categoria();
        category.setCodigoCategoria(dto.getChaveComposta().getProduto().getCategoria().getCodigoCategoria());
        category.setDescricao(dto.getChaveComposta().getProduto().getCategoria().getDescricao());
        product.setCategoria(category);

        Subcategoria subcategory = new Subcategoria();
        CompositeKeySubcategoria keySubcategory = new CompositeKeySubcategoria();
        keySubcategory.setCodigoSubcategoria(dto.getChaveComposta().getProduto().getSubcategoria().getChaveComposta().getCodigoSubcategoria());
        keySubcategory.setCategoria(category);
        subcategory.setChaveComposta(keySubcategory);
        subcategory.setDescricao(dto.getChaveComposta().getProduto().getSubcategoria().getDescricao());
        product.setSubcategoria(subcategory);

        Colecao colection = new Colecao();
        colection.setId(dto.getChaveComposta().getProduto().getColecao().getId());
        colection.setDescricao(dto.getChaveComposta().getProduto().getColecao().getDescricao());
        product.setColecao(colection);

        Raridade rarity = new Raridade();
        rarity.setId(dto.getChaveComposta().getProduto().getRaridade().getId());
        rarity.setDescricao(dto.getChaveComposta().getProduto().getRaridade().getDescricao());
        product.setRaridade(rarity);

        Exterior exterior = new Exterior();
        exterior.setId(dto.getChaveComposta().getProduto().getExterior().getId());
        exterior.setDescricao(dto.getChaveComposta().getProduto().getExterior().getDescricao());
        product.setExterior(exterior);

        product.setDesgaste(dto.getChaveComposta().getProduto().getDesgaste());
        product.setPattern(dto.getChaveComposta().getProduto().getPattern());
        product.setTradeLock(dto.getChaveComposta().getProduto().getPattern());
        product.setUrlImagem(dto.getChaveComposta().getProduto().getUrlImagem());
        key.setProduto(product);

        fav.setChaveComposta(key);
        fav.setFavorito(dto.getFavorito());

        return fav;
    }

    public FavoritosDTO favToDto(Favoritos fav){
        FavoritosDTO dto = new FavoritosDTO();
        CompositeKeyFavoritosDTO key = new CompositeKeyFavoritosDTO();
        ProdutoDTO p = new ProdutoDTO();
        ClienteDTO c = new ClienteDTO();

        c.setCodigoCliente(fav.getChaveComposta().getCliente().getCodigoCliente());
        c.setNomeCliente(fav.getChaveComposta().getCliente().getNomeCliente());
        c.setSenhaCliente(fav.getChaveComposta().getCliente().getSenhaCliente());

        GeneroDTO g = new GeneroDTO();
        g.setCodigoGenero(fav.getChaveComposta().getCliente().getCodigoCliente());
        g.setNomeGenero(fav.getChaveComposta().getCliente().getGenero().getNomeGenero());

        c.setGenero(g);
        c.setDataNascimento(fav.getChaveComposta().getCliente().getDataNascimento());
        c.setEmailCliente(fav.getChaveComposta().getCliente().getEmailCliente());
        c.setNumeroTelefone(fav.getChaveComposta().getCliente().getNumeroTelefone());
        c.setTradeLink(fav.getChaveComposta().getCliente().getTradeLink());
        key.setCliente(c);

        p.setId(fav.getChaveComposta().getProduto().getId());
        p.setDescricao(fav.getChaveComposta().getProduto().getDescricao());

        CategoriaDTO category = new CategoriaDTO();
        category.setCodigoCategoria(fav.getChaveComposta().getProduto().getCategoria().getCodigoCategoria());
        category.setDescricao(fav.getChaveComposta().getProduto().getCategoria().getDescricao());
        p.setCategoria(category);

        SubcategoriaDTO subcategory = new SubcategoriaDTO();
        CompositeKeySubcategoriaDTO keySubcategory = new CompositeKeySubcategoriaDTO();
        keySubcategory.setCodigoSubcategoria(fav.getChaveComposta().getProduto().getSubcategoria().getChaveComposta().getCodigoSubcategoria());
        keySubcategory.setCategoria(category);
        subcategory.setChaveComposta(keySubcategory);
        subcategory.setDescricao(fav.getChaveComposta().getProduto().getSubcategoria().getDescricao());
        p.setSubcategoria(subcategory);

        ColecaoDTO colection = new ColecaoDTO();
        colection.setId(fav.getChaveComposta().getProduto().getColecao().getId());
        colection.setDescricao(fav.getChaveComposta().getProduto().getColecao().getDescricao());
        p.setColecao(colection);

        RaridadeDTO rarity = new RaridadeDTO();
        rarity.setId(fav.getChaveComposta().getProduto().getRaridade().getId());
        rarity.setDescricao(fav.getChaveComposta().getProduto().getRaridade().getDescricao());
        p.setRaridade(rarity);

        ExteriorDTO exterior = new ExteriorDTO();
        exterior.setId(fav.getChaveComposta().getProduto().getExterior().getId());
        exterior.setDescricao(fav.getChaveComposta().getProduto().getExterior().getDescricao());
        p.setExterior(exterior);

        p.setDesgaste(fav.getChaveComposta().getProduto().getDesgaste());
        p.setPattern(fav.getChaveComposta().getProduto().getPattern());
        p.setTradeLock(fav.getChaveComposta().getProduto().getPattern());
        p.setUrlImagem(fav.getChaveComposta().getProduto().getUrlImagem());
        key.setProduto(p);

        dto.setChaveComposta(key);
        dto.setFavorito(fav.isFavorito());

        return dto;
    }

    public List<FavoritosDTO> listToDTO(List<Favoritos> list){
        List<FavoritosDTO> listDTO = new ArrayList<FavoritosDTO>();

        for (Favoritos f : list) {
            listDTO.add(this.favToDto(f));
        }

        return listDTO;
    }
}

