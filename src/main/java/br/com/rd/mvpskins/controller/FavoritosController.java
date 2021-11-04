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
    public List<FavoritosDTO> getFavoritoByClientId(@PathVariable("id")Long id){
        return favoritosService.getFavoritoByClientId(id);
    }

    @GetMapping("/{idC}/{idP}")
    @ResponseBody
    public FavoritosDTO getFavoritoById(@PathVariable("idC")Long idC, @PathVariable("idP")Long idP){
        return favoritosService.getFavoritoById(idC, idP);
    }

    @PostMapping
    @ResponseBody
    public FavoritosDTO createFavorito(@RequestBody FavoritosDTO dto){
        return favoritosService.createFavorito(dto);
    }

    @PutMapping("/{idC}/{idP}")
    @ResponseBody
    public FavoritosDTO updateFavorito(@PathVariable("idC")Long idC,
                                       @PathVariable("idP")Long idP){
        return favoritosService.updateFavorito(idC, idP);
    }


}
