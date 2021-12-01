package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.ClienteDTO;
import br.com.rd.mvpskins.model.dto.NFDTO;
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
                "http://localhost:3000/resetpassword/"+token+
                "\n\n Equipe MVPSkins");
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
                "\n\n\nPara mais informações acesse a Área de Cliente em nosso site\n\n"+
                "http://localhost:3000/myaccount\n\n\n"+
                "Obrigado por escolher a MVPSkins!\nEquipe MVPSkins");
        message.setSubject("MVPSkins - Recebemos seu pedido!");
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

    //Método para enviar email de cadastro realizado
    public String sendEmailRegisterSuccess(ClienteDTO cliente){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Olá "+cliente.getNomeCliente()+"!\n\n"+
                "Seja bem-vindo(a) a MVPSkins, confira nosso site no link abaixo:\n\n"+
                "http://localhost:3000/home\n\n"+
                "Equipe MVPSkins");
        message.setSubject("Bem-vindo(a) a MVPSkins!");
        message.setTo(cliente.getEmailCliente());
        message.setFrom(cliente.getEmailCliente());

        try {
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar email.";
        }
    }

    //Método para enviar email de NF
    public String sendEmailPaymentApproved(PedidoDTO pedido) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Olá " + pedido.getCliente().getNomeCliente() + ",\n\n" +
                "O pagamento do pedido " + pedido.getId() + " foi aprovado!\n" +
                "Em breve você receberá sua nota fiscal." +
                "\n\nEquipe MVPSkins");
        message.setSubject("MVPSkins - Pagamento aprovado!");
        message.setTo(pedido.getCliente().getEmailCliente());
        message.setFrom(pedido.getCliente().getEmailCliente());

        try {
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar email.";
        }
    }

    //Método para enviar email de NF
    public String sendEmailInvoice(NFDTO nf){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Olá "+nf.getPedido().getCliente().getNomeCliente()+",\n\n"+
                "Sua nota fiscal foi emitida! Veja os detalhes da nota:\n\n"+
                nf.email()+
                "\n\nObrigado por comprar conosco!"+
                "\nEquipe MVPSkins");
        message.setSubject("MVPSkins - Sua nota fiscal foi emitida");
        message.setTo(nf.getPedido().getCliente().getEmailCliente());
        message.setFrom(nf.getPedido().getCliente().getEmailCliente());

        try {
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar email.";
        }
    }
}
