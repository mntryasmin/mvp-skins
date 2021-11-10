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
    public PromocaoDTO searchById(@PathVariable("idPromotion") Long codigoPromocao){
    return promocaoService.searchPromotionById(codigoPromocao);
}
    @PutMapping("/{updatePromotion}")
    @ResponseBody
    public PromocaoDTO updatePromotion(@RequestBody PromocaoDTO promocaoDTO, @PathVariable("idPromotion") Long codigoPromocao ){
    return promocaoService.updatePromotion(promocaoDTO,codigoPromocao);
    }

    @DeleteMapping("/{deletePromotion}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deletePromotion(@PathVariable("idPromotion")Long codigoPromocao){
    promocaoService.deletePromotion(codigoPromocao);
    }

}
