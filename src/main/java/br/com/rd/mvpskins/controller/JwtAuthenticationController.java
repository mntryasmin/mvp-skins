package br.com.rd.mvpskins.controller;

import br.com.rd.mvpskins.config.JwtTokenUtil;
import br.com.rd.mvpskins.model.jwt.JwtRequest;
import br.com.rd.mvpskins.model.jwt.JwtResponse;
import br.com.rd.mvpskins.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        try{
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            String senha = authenticationRequest.getPassword();
            try{
                authenticate(senha, userDetails);
                final String token = jwtTokenUtil.generateToken(userDetails);
                return ResponseEntity.ok(new JwtResponse(token));
            } catch(Exception e){
                e.printStackTrace();
            }


        } catch(UsernameNotFoundException u){
            u.printStackTrace();
        }
        return null;
    }

    private void authenticate(String senha, UserDetails user) throws Exception {
        if(!passwordEncoder.matches(senha, user.getPassword())){
            throw new Exception("Senha inválida");
        }
    }

    @PostMapping("/esqueci-minha-senha")
    public String esqueciSenha(@RequestBody String email){
        return userDetailsService.esqueciSenha(email);
    }

    @GetMapping("/logout/{token}")
    public void logout(@PathVariable("token") String token){
        userDetailsService.logout(token);
    }

    @GetMapping("/data/{token}")
    public Date dataToken(@PathVariable("token") String token ){
        return  userDetailsService.verDataToken(token);
    }

}
