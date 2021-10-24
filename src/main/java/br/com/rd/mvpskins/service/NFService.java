package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.FormPaymentDTO;
import br.com.rd.mvpskins.model.dto.NFDTO;
import br.com.rd.mvpskins.model.dto.RequestDTO;
import br.com.rd.mvpskins.model.dto.TypeNFDTO;
import br.com.rd.mvpskins.model.entity.FormPayment;
import br.com.rd.mvpskins.model.entity.NF;
import br.com.rd.mvpskins.model.entity.Request;
import br.com.rd.mvpskins.model.entity.TypeNF;
import br.com.rd.mvpskins.repository.contract.FormPaymentRepository;
import br.com.rd.mvpskins.repository.contract.NFRepository;
import br.com.rd.mvpskins.repository.contract.RequestRepository;
import br.com.rd.mvpskins.repository.contract.TypeNFRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NFService {

    @Autowired
    NFRepository nfRepository;

    @Autowired
    FormPaymentService formPaymentService;

    @Autowired
    FormPaymentRepository formPaymentRepository;

    @Autowired
    RequestService requestService;

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    TypeNFService typeNFService;

    @Autowired
    TypeNFRepository typeNFRepository;

    //  ---------------------> CONVERTER PARA BUSINESS
    private NF dtoToBusiness (NFDTO dto) {
        NF b = new NF();

        //        ===> REQUEST
        if (dto.getRequestDTO() != null) {
            Request r = requestRepository.getById(dto.getRequestDTO().getId());
            b.setRequest(r);
        }

        //        ===> TYPENF
        if (dto.getTypeNFDTO() != null) {
            TypeNF t = typeNFRepository.getById(dto.getTypeNFDTO().getId());

            b.setTypeNF(t);
        }

        //        ===> FORMPAYMENT
        if (dto.getFormPaymentDTO() != null) {
            FormPayment f = formPaymentRepository.getById(dto.getFormPaymentDTO().getId());

            b.setFormPayment(f);
        }

        b.setId(dto.getId());
        b.setIdCompany(dto.getIdCompanyDTO());
        b.setIdProvider(dto.getIdProviderDTO());
        b.setIdClient(dto.getIdClientDTO());
        b.setAccessKey(dto.getAccessKey());
        b.setNoteNumber(dto.getNoteNumber());
        b.setIcms(dto.getIcms());
        b.setIpi(dto.getIpi());
        b.setPis(dto.getPis());
        b.setCofins(dto.getCofins());
        b.setFlagNF(dto.getFlagNF());
        b.setIssueDate(dto.getIssueDate());
        b.setDiscountProduct(dto.getDiscountProduct());
        b.setGrossAddedValue(dto.getGrossAddedValue());
        b.setNetValue(dto.getNetValue());

        return b;
    }

    //  ---------------------> CONVERTER PARA DTO
    private NFDTO businessToDTO (NF b) {
        NFDTO dto = new NFDTO();

        //        ===> REQUEST
        if (b.getRequest() != null) {
            RequestDTO r = requestService.searchID(b.getRequest().getId());

            dto.setRequestDTO(r);
        }

        //        ===> TYPENF
        if (b.getTypeNF() != null) {
            TypeNFDTO t = typeNFService.searchID(b.getTypeNF().getId());

            dto.setTypeNFDTO(t);
        }

        //        ===> FORMPAYMENT
        if (b.getFormPayment() != null) {
            FormPaymentDTO f = formPaymentService.searchID(b.getFormPayment().getId());

            dto.setFormPaymentDTO(f);
        }

        dto.setId(b.getId());
        dto.setIdCompanyDTO(b.getIdCompany());
        dto.setIdProviderDTO(b.getIdProvider());
        dto.setIdClientDTO(b.getIdClient());
        dto.setAccessKey(b.getAccessKey());
        dto.setNoteNumber(b.getNoteNumber());
        dto.setIcms(b.getIcms());
        dto.setIpi(b.getIpi());
        dto.setPis(b.getPis());
        dto.setCofins(b.getCofins());
        dto.setFlagNF(b.getFlagNF());
        dto.setIssueDate(b.getIssueDate());
        dto.setDiscountProduct(b.getDiscountProduct());
        dto.setGrossAddedValue(b.getGrossAddedValue());
        dto.setNetValue(b.getNetValue());

        return dto;
    }

    //  ---------------------> CONVERTER LISTA BUSINESS PARA LISTA DTO
    private List<NFDTO> listToDTO (List<NF> listB) {
        List<NFDTO> listDTO = new ArrayList<>();

        for (NF b : listB) {
            listDTO.add(this.businessToDTO(b));
        }

        return listDTO;
    }


    //  ---------------------> CRIAR
    public NFDTO create (NFDTO nfDTO) {
        NF nf = this.dtoToBusiness(nfDTO);

        //        ===> REQUEST
        if (nfDTO.getRequestDTO() != null) {
            Long idRequest = nf.getRequest().getId();
            Request r;

            if (idRequest != null) {
                r = this.requestRepository.getById(idRequest);
            } else {
                r = this.requestRepository.save(nf.getRequest());
            }

            nf.setRequest(r);
        }

        //        ===> TYPENF
        if (nfDTO.getTypeNFDTO() != null) {
            Long idTypeNF = nf.getTypeNF().getId();
            TypeNF t;

            if (idTypeNF != null) {
                t = this.typeNFRepository.getById(idTypeNF);
            } else {
                t = this.typeNFRepository.save(nf.getTypeNF());
            }

            nf.setTypeNF(t);
        }

        //        ===> FORMPAYMENT
        if (nfDTO.getFormPaymentDTO() != null) {
            Long idFormPayment = nf.getFormPayment().getId();
            FormPayment f;

            if (idFormPayment != null) {
                f = this.formPaymentRepository.getById(idFormPayment);
            } else {
                f = this.formPaymentRepository.save(nf.getFormPayment());
            }

            nf.setFormPayment(f);
        }

        nf.setIssueDate(new Date());
        nf = nfRepository.save(nf);

        return businessToDTO(nf);
    }


    //  ---------------------> BUSCAR
    //TODAS AS NF'S
    public List<NFDTO> searchAll() {
        List<NF> list = nfRepository.findAll();

        return listToDTO(list);
    }

    //UMA NF POR ID
    public NFDTO searchID(Long id) {
        if (nfRepository.existsById(id)) {
            return businessToDTO(nfRepository.getById(id));
        }

        return null;
    }

    //  ---------------------> ATUALIZAR
    public NFDTO update(NFDTO dto, Long id) {

        Optional<NF> opt = nfRepository.findById(id);
        NF nf = dtoToBusiness(dto);

        if (opt.isPresent()) {
            NF update = opt.get();

            if (nf.getRequest() != null) {
                update.setRequest(nf.getRequest());
            }

            if (nf.getTypeNF() != null) {
                update.setTypeNF(nf.getTypeNF());
            }

            if (nf.getIdCompany() != null) {
                update.setIdCompany(nf.getIdCompany());
            }

            if (nf.getIdProvider() != null) {
                update.setIdProvider(nf.getIdProvider());
            }

            if (nf.getIdClient() != null) {
                update.setIdClient(nf.getIdClient());
            }

            if (nf.getFormPayment() != null) {
                update.setFormPayment(nf.getFormPayment());
            }

            if (nf.getAccessKey() != null) {
                update.setAccessKey(nf.getAccessKey());
            }

            if (nf.getNoteNumber() != null) {
                update.setNoteNumber(nf.getNoteNumber());
            }

            if (nf.getIcms() != null) {
                update.setIcms(nf.getIcms());
            }

            if (nf.getIpi() != null) {
                update.setIpi(nf.getIpi());
            }

            if (nf.getPis() != null) {
                update.setPis(nf.getPis());
            }

            if (nf.getCofins() != null) {
                update.setCofins(nf.getCofins());
            }

            if (nf.getFlagNF() != null) {
                update.setFlagNF(nf.getFlagNF());
            }

            if (nf.getDiscountProduct() != null) {
                update.setDiscountProduct(nf.getDiscountProduct());
            }

            if (nf.getGrossAddedValue() != null) {
                update.setGrossAddedValue(nf.getGrossAddedValue());
            }

            if (nf.getNetValue() != null) {
                update.setNetValue(nf.getNetValue());
            }

            update.setIssueDate(new Date());
            nfRepository.save(update);
            return businessToDTO(update);
        }

        return null;
    }

    //  ---------------------> DELETAR
    public void delete(Long id) {
        if (nfRepository.existsById(id)) {
            nfRepository.deleteById(id);
        }
    }
}