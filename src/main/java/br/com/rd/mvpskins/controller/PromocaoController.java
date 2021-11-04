package br.com.rd.mvpskins.controller;

import br.com.rd.mvpskins.model.dto.PromocaoDTO;
import br.com.rd.mvpskins.service.PromocaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promotion")

public class PromocaoController {
    @Autowired
    PromocaoService promocaoService;

    @PostMapping
    @ResponseBody
    public PromocaoDTO createPromotion(@RequestBody PromocaoDTO promocao) {
        return promocaoService.createPromotion(promocao);
    }

    @GetMapping
    public List<PromocaoDTO> searchAllPromotion() {
        return promocaoService.searchAllPromotion();
    }

    @GetMapping("/{idPromotion}")
    public PromocaoDTO searchPromotionById(@PathVariable("idPromotion") String codigoPromocao){
    return promocaoService.searchPromotionById(codigoPromocao);
}
    @GetMapping("/{idPromotion}")
    public PromocaoDTO updatePromotion(@RequestBody PromocaoDTO promocaoDTO, @PathVariable("idPromotion") String codigoPromocao ){
    return promocaoService.updatePromotion(promocaoDTO,codigoPromocao);
    }

    @DeleteMapping("/{idPromotion}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deletePromotion(@PathVariable("idPromotion")String codigoPromocao){
    promocaoService.deletePromotion(codigoPromocao);
    }

}
