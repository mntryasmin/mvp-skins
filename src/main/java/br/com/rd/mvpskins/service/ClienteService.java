package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.ClienteDTO;
import br.com.rd.mvpskins.model.dto.GeneroDTO;
import br.com.rd.mvpskins.model.entity.Cliente;
import br.com.rd.mvpskins.model.entity.Genero;
import br.com.rd.mvpskins.repository.contract.ClienteRepository;
import br.com.rd.mvpskins.repository.contract.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    GeneroRepository generoRepository;
    @Autowired
    PasswordEncoder encoder;


    private Cliente dtoToBusiness (ClienteDTO dto){
        Cliente c = new Cliente();
        c.setEmailCliente(dto.getEmailCliente());
        c.setNomeCliente(dto.getNomeCliente());
        c.setDataNascimento(dto.getDataNascimento());
        c.setTradeLink(dto.getTradeLink());
        c.setSenhaCliente(dto.getSenhaCliente());
        c.setNumeroTelefone(dto.getNumeroTelefone());

        if(dto.getGenero()!= null){
            Genero g = new Genero();
            g.setCodigoGenero(dto.getGenero().getCodigoGenero());
            g.setNomeGenero(dto.getGenero().getNomeGenero());
            c.setGenero(g);
        }

        return c;

    }

    private ClienteDTO businessToDto(Cliente c){
        ClienteDTO dto = new ClienteDTO();
        dto.setCodigoCliente(c.getCodigoCliente());
        dto.setEmailCliente(c.getEmailCliente());
        dto.setNomeCliente(c.getNomeCliente());
        dto.setDataNascimento(c.getDataNascimento());
        dto.setTradeLink(c.getTradeLink());
        dto.setSenhaCliente(c.getSenhaCliente());
        dto.setNumeroTelefone(c.getNumeroTelefone());

        if(c.getGenero()!= null){
            GeneroDTO g = new GeneroDTO();
            g.setCodigoGenero(c.getGenero().getCodigoGenero());
            g.setNomeGenero(c.getGenero().getNomeGenero());
            dto.setGenero(g);
        }

        return dto;
    }
    private List<ClienteDTO> listToDto (List<Cliente> listC){
        List<ClienteDTO> listDTO = new ArrayList<>();
        for (Cliente c : listC){
            listDTO.add(this.businessToDto(c));
        }
        return listDTO;
    }
    public ClienteDTO createCliente (ClienteDTO clienteDTO){
        Cliente cliente = dtoToBusiness(clienteDTO);
        cliente.setSenhaCliente(encoder.encode(cliente.getSenhaCliente()));
        if(generoRepository.existsById(cliente.getGenero().getCodigoGenero())){
            Genero genero = generoRepository.getById(cliente.getGenero().getCodigoGenero());
            cliente.setGenero(genero);
        }
        cliente = clienteRepository.save(cliente);
        return businessToDto(cliente);
    }

    public List<ClienteDTO> searchAllCliente(){
        List<Cliente> lista = clienteRepository.findAll();
        return listToDto(lista);
    }

    public ClienteDTO searchClienteById(Long codigoCliente){
        if(clienteRepository.existsById(codigoCliente)){
            return businessToDto(clienteRepository.getById(codigoCliente));
        }
        return null;
    }

    public Cliente searchClienteByEmail(String email){
        List<Cliente> lista = clienteRepository.findAll();
        for(Cliente c : lista){
            if(c.getEmailCliente().equals(email)){
                return c;
            }
        }
        return null;
    }

    public ClienteDTO updateCliente (ClienteDTO dto, Long codigoCliente) {
        Optional<Cliente> opt = clienteRepository.findById(codigoCliente);
        Cliente cliente = dtoToBusiness(dto);
        if (opt.isPresent()) {
            Cliente update = opt.get();
            if (cliente.getCodigoCliente() != null) {
                update.setCodigoCliente(cliente.getCodigoCliente());
            }
            if (cliente.getEmailCliente() != null) {
                update.setEmailCliente(cliente.getEmailCliente());
            }
            if (cliente.getSenhaCliente() != null) {
                update.setSenhaCliente(encoder.encode(cliente.getSenhaCliente()));
            }
            if (cliente.getDataNascimento() != null) {
                update.setDataNascimento(cliente.getDataNascimento());
            }
            if (cliente.getTradeLink() != null) {
                update.setTradeLink(cliente.getTradeLink());
            }
            if (cliente.getNomeCliente() != null) {
                update.setNomeCliente(cliente.getNomeCliente());
            }
            if(cliente.getNumeroTelefone() !=null){
                update.setNumeroTelefone(cliente.getNumeroTelefone());
            }
            if (cliente.getGenero() != null) {
                Long idGenero = cliente.getGenero().getCodigoGenero();
                if(idGenero !=null) {
                    Genero g = generoRepository.getById(idGenero);
                    update.setGenero(g);
                }
            }

            clienteRepository.save(update);
            return businessToDto(update);
        }
        return null;
    }
        public void deleteCliente (Long id){
            if (clienteRepository.existsById(id)){
                clienteRepository.deleteById(id);
            }

        }
    }


