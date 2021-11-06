package br.com.rd.mvpskins.service;

import java.util.ArrayList;

import br.com.rd.mvpskins.config.JwtTokenUtil;
import br.com.rd.mvpskins.model.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private EmailService emailService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    //Método para criar um novo usuário para o token
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = clienteService.searchClientByEmail(email);

        if (cliente!=null && cliente.getEmailCliente().equals(email)) {
            return new User(email, cliente.getSenhaCliente(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado: " + email);
        }
    }

    //Método que verifica se a senha passada é igual a salva no banco
    public Boolean authenticate(String senha, UserDetails user) throws Exception {

        return passwordEncoder.matches(senha, user.getPassword());
    }

    //Método para registrar nova senha
    public void esqueciSenha(String email) throws Exception{
        Cliente cliente = clienteService.searchClientByEmail(email);
        if(cliente==null){
            throw new UsernameNotFoundException("email não encontrado: "+email);
        }

        User user = new User(email, cliente.getSenhaCliente(), new ArrayList<>());
        String token = jwtTokenUtil.generateTokenForgotPassword(user);
        emailService.sendEmailForgotPassword(token, user.getUsername());

    }


    public void logout(String token){
        jwtTokenUtil.setExpirationDateFromToken(token);
    }
}
