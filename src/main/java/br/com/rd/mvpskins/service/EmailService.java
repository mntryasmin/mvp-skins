package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.PedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    //Método para enviar email de esqueci minha senha
    public String sendEmailForgotPassword(String token, String email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Para redefinir sua senha, por favor acesse o link abaixo\n\n"+
                "http://localhost:3000/resetpassword/"+token);
        message.setSubject("Redefinição de senha MVPSkins");
        message.setTo(email);
        message.setFrom(email);

        try {
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar email.";
        }
    }

    //Método para enviar email de confirmação de Pedido
    public String sendEmailPurchaseSuccess(PedidoDTO pedido, String email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Olá "+pedido.getCliente().getNomeCliente()+" recebemos seu pedido em nosso site!\n"+
                "\nDetalhes do pedido:\n"+
                pedido.email()+
                "\n\n\n Para mais informações acesse a Página Dashboard de Cliente em nosso site");
        message.setSubject("Agradecemos seu pedido na MVPSkins!");
        message.setTo(email);
        message.setFrom(email);

        try {
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar email.";
        }
    }
}
