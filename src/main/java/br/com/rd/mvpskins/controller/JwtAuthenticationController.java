package br.com.rd.mvpskins.controller;

import br.com.rd.mvpskins.config.JwtTokenUtil;
import br.com.rd.mvpskins.model.jwt.JwtRequest;
import br.com.rd.mvpskins.model.jwt.JwtResponse;
import br.com.rd.mvpskins.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import br.com.rd.mvpskins.model.dto.EmailDTO;



@RestController
@CrossOrigin
@RequestMapping("/authenticate")
public class JwtAuthenticationController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;


    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String senha = authenticationRequest.getPassword();

        if(userDetailsService.authenticate(senha, userDetails)) {
            final String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(token));
        }else{
            return null;
        }

    }

    @PostMapping("/esqueci-minha-senha")
    public void  generateForgotPasswordToken(@RequestBody EmailDTO email) throws Exception{
        userDetailsService.generateForgotPasswordToken(email.getEmail());
    }

    @GetMapping("/logout/{token}")
    public void logout(@PathVariable("token") String token){
        userDetailsService.logout(token);
    }
    

}
