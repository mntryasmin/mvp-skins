package br.com.rd.mvpskins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import javax.naming.InitialContext;
import javax.naming.NamingException;

@SpringBootApplication
public class MvpSkinsApplication {

	public static void main(String[] args) throws NamingException {
		SpringApplication.run(MvpSkinsApplication.class, args);
	}
}
