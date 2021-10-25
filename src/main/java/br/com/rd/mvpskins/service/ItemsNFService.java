package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.ItemsNFCompositeKeyDTO;
import br.com.rd.mvpskins.model.dto.ItemsNFDTO;
import br.com.rd.mvpskins.model.embeddable.ItemsNFCompositeKey;
import br.com.rd.mvpskins.model.entity.ItemsNF;
import br.com.rd.mvpskins.repository.contract.ItemsNFRepository;
import br.com.rd.mvpskins.repository.contract.NFRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemsNFService {

    @Autowired
    ItemsNFRepository itemsNFRepository;

    @Autowired
    NFService nfService;

    @Autowired
    NFRepository nfRepository;

    //  ---------------------> CONVERTER PARA BUSINESS
    private ItemsNF dtoToBusiness (ItemsNFDTO dto) {
        ItemsNFCompositeKey id = new ItemsNFCompositeKey();
        id.setIdProduct(dto.getId().getIdProduct());
        id.setNf(nfRepository.getById(dto.getId().getNf().getId()));

        ItemsNF b = new ItemsNF();
        b.setId(id);
        b.setQuantity(dto.getQuantity());
        b.setIcms(dto.getIcms());
        b.setPis(dto.getPis());
        b.setCofins(dto.getCofins());
        b.setIpi(dto.getIpi());
        b.setDiscount(dto.getDiscount());
        b.setGrossAddedValue(dto.getGrossAddedValue());
        b.setNetValue(dto.getNetValue());

        return b;
    }

    //  ---------------------> CONVERTER PARA DTO
    private ItemsNFDTO businessToDTO (ItemsNF b) {
        ItemsNFCompositeKeyDTO id = new ItemsNFCompositeKeyDTO();
        id.setIdProduct(b.getId().getIdProduct());
        id.setNf(nfService.searchID(b.getId().getNf().getId()));

        ItemsNFDTO dto = new ItemsNFDTO();
        dto.setId(id);
        dto.setQuantity(b.getQuantity());
        dto.setIcms(b.getIcms());
        dto.setPis(b.getPis());
        dto.setCofins(b.getCofins());
        dto.setIpi(b.getIpi());
        dto.setDiscount(b.getDiscount());
        dto.setGrossAddedValue(b.getGrossAddedValue());
        dto.setNetValue(b.getNetValue());

        return dto;
    }

    //  ---------------------> CONVERTER LISTA BUSINESS PARA LISTA DTO
    private List<ItemsNFDTO> listToDTO (List<ItemsNF> listB) {
        List<ItemsNFDTO> listDTO = new ArrayList<>();

        for (ItemsNF b : listB) {
            listDTO.add(this.businessToDTO(b));
        }

        return listDTO;
    }


    //  ---------------------> CRIAR
    public ItemsNFDTO create (ItemsNFDTO itemsNFDTO) {
        ItemsNF itemsNF = dtoToBusiness(itemsNFDTO);

        itemsNF = itemsNFRepository.save(itemsNF);

        return businessToDTO(itemsNF);
    }


    //  ---------------------> BUSCAR
    //TODOS OS ITENS NF
    public List<ItemsNFDTO> searchAll() {
        List<ItemsNF> list = itemsNFRepository.findAll();

        return listToDTO(list);
    }

    //UM ITEM NF POR ID
    public ItemsNFDTO searchID(Long idProduct, Long idNF) {

        ItemsNFCompositeKey id = new ItemsNFCompositeKey();
        id.setIdProduct(idProduct);
        id.setNf(nfRepository.getById(idNF));

        if (itemsNFRepository.existsById(id)) {
            return businessToDTO(itemsNFRepository.getById(id));
        }

        return null;
    }

    //  ---------------------> ATUALIZAR
    public ItemsNFDTO update(ItemsNFDTO dto, Long idProduct, Long idNF) {

            ItemsNFCompositeKeyDTO id = new ItemsNFCompositeKeyDTO();
            id.setIdProduct(idProduct);
            id.setNf(nfService.searchID(idNF));
            dto.setId(id);

            ItemsNF itemsNF = dtoToBusiness(dto);
            Optional<ItemsNF> opt = itemsNFRepository.findById(itemsNF.getId());

            if (opt.isPresent()) {
                ItemsNF update = opt.get();

                if (itemsNF.getQuantity() != null) {
                    update.setQuantity(itemsNF.getQuantity());
                }

                if (itemsNF.getDiscount() != null) {
                    update.setDiscount(itemsNF.getDiscount());
                }

                if (itemsNF.getGrossAddedValue() != null) {
                    update.setGrossAddedValue(itemsNF.getGrossAddedValue());
                }

                if (itemsNF.getNetValue() != null) {
                    update.setNetValue(itemsNF.getNetValue());
                }

                itemsNFRepository.save(update);
                return businessToDTO(update);
            }
//        }

        return null;
    }

    //  ---------------------> DELETAR
    public void delete(Long idProduct, Long idNF) {
        ItemsNFCompositeKey id = new ItemsNFCompositeKey();
        id.setIdProduct(idProduct);
        id.setNf(nfRepository.getById(idNF));

        if (itemsNFRepository.existsById(id)) {
            itemsNFRepository.deleteById(id);
        }
    }
}