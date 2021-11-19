package br.com.rd.mvpskins.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable()
// Não cheque essas requisições
            .authorizeRequests().antMatchers("/cliente",
                                                        "/cliente/{id}",
                                                        "/cliente/auto",
                                                        "/cliente/valid-password-client/{senha}/{idCliente}",
                                                        "/cliente/token/{token}",
                                                        "/empresa",
                                                        "/fornecedor",
                                                        "/fornecedor/{id}",
                                                        "/forma-pagamento",
                                                        "/forma-pagamento/{id}",
                                                        "/itens-pedido",
                                                        "itens-pedido/{id}/{id}",
                                                        "/itens-pedido/{idPedido}",
                                                        "/nf",
                                                        "/nf/{id}",
                                                        "/nf/historico/{idCliente}",
                                                        "/pedidos",
                                                        "/pedidos/{id}",
                                                        "/pedidos/order-history/{idCliente}",
                                                        "/tipo-nf",
                                                        "/tipo-nf/{id}",
                                                        "/produtos",
                                                        "/produtos/{id}",
                                                        "/categoria",
                                                        "/categoria/{id}",
                                                        "/subcategoria",
                                                        "/subcategoria/{idC}/{id}",
                                                        "/raridade",
                                                        "/raridade/{id}",
                                                        "/colecao",
                                                        "/colecao/{id}",
                                                        "/exterior",
                                                        "/exterior/{id}",
                                                        "/preco",
                                                        "/preco/{idC}/{idP}",
                                                        "/authenticate/login",
                                                        "/authenticate/esqueci-minha-senha",
                                                        "/estoque/em-estoque",
                                                        "/estoque/fora-de-estoque",
                                                        "/produtos/search/{s}",
                                                        "/produtos/category/{id}",
                                                        "/produtos/subcategory/{idC}/{idS}",
                                                        "/produtos/colection/{id}",
                                                        "/produtos/rarity/{id}",
                                                        "/produtos/exterior/{id}",
                                                        "/produtos/betweenPrices/{v1}/{v2}",
                                                        "/preco/recente/{idPreco}/{idProduto}",
                                                        "/empresa/{id}",
                                                        "/preco/recente/{idPreco}/{idProduto}",
                                                        "/itens-nf,",
                                                        "/promotion/coupon-validate/{cod}",
                                                        "/promotion/coupon-discount/{cod}").permitAll().

// Qualquer outra requisição deve ser checada
        anyRequest().authenticated().and().
                exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
