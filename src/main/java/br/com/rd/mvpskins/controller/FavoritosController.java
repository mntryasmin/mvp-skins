package br.com.rd.mvpskins.controller;

import br.com.rd.mvpskins.model.dto.FavoritosDTO;
import br.com.rd.mvpskins.service.FavoritosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fav")
public class FavoritosController {

    @Autowired
    FavoritosService favoritosService;

    @GetMapping
    @ResponseBody
    public List<FavoritosDTO> getList(){
        return favoritosService.getList();
    }

    @GetMapping("/client/{id}")
    @ResponseBody
    public List<FavoritosDTO> getFavoriteByClientId(@PathVariable("id")Long id){
        return favoritosService.getFavoriteByClientId(id);
    }

    @GetMapping("/{idC}/{idP}")
    @ResponseBody
    public FavoritosDTO getFavoriteById(@PathVariable("idC")Long idC, @PathVariable("idP")Long idP){
        return favoritosService.getFavoriteById(idC, idP);
    }

    @PostMapping
    @ResponseBody
    public FavoritosDTO createFavorite(@RequestBody FavoritosDTO dto){
        return favoritosService.createFavorite(dto);
    }

    @PutMapping("/{idC}/{idP}")
    @ResponseBody
    public FavoritosDTO updateFavorite(@PathVariable("idC")Long idC,
                                       @PathVariable("idP")Long idP){
        return favoritosService.updateFavorite(idC, idP);
    }


}
