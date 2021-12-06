package br.com.rd.mvpskins.service;

import br.com.rd.mvpskins.model.dto.ClienteDTO;
import br.com.rd.mvpskins.model.dto.EnderecoCobrancaDTO;
import br.com.rd.mvpskins.model.dto.NFDTO;
import br.com.rd.mvpskins.model.dto.PedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.text.NumberFormat;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EnderecoCobrancaService enderecoCobrancaService;

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
        EnderecoCobrancaDTO endereco = enderecoCobrancaService.searchAdressByRequest(nf.getPedido().getId());
        message.setText("Olá "+nf.getPedido().getCliente().getNomeCliente()+",\n\n"+
                "A nota fiscal referente ao pedido " + nf.getPedido().getId()+
                " foi emitida! Veja os detalhes da nota:\n\n"+
                nf.email()+
                "\n\nEndereço de faturamento: \n" +
                "CEP: " + endereco.getCep() + "\n" +
                "Rua: " + endereco.getLogradouro() + ", nº " + endereco.getNumero() + ", " + endereco.getComplemento() + "\n" +
                "Bairro: " + endereco.getBairro() + "\n" +
                "Cidade: " + endereco.getCidade() + " - " + endereco.getEstado() + "\n"+
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

    public String sendEmailTicket(PedidoDTO pedido){

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try{
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            NumberFormat numberFormat = NumberFormat.getCurrencyInstance();

            helper.setText(pedido.getCliente().getNomeCliente()+" segue abaixo o número do boleto referente ao pedido: "+
                    pedido.getId()+"\n\n"+
                    "\n23793.39100 90000.183484 47000.400409 8 87990000032164\n"+
                    "Valor do Boleto: "+numberFormat.format(pedido.getValorLiquido())+
                    "\n\n\nAo pagar confira se o valor do boleto está correto e se o beneficiário é 'MVP Skins' !!!!\n\n"+
                    "Após a confirmação do pagamento será emitida a Nota Fiscal desse pedido.\n\n\n"+
                    "Obrigado por escolher a MVPSkins!\nEquipe MVPSkins");
            helper.setSubject("MVPSkins - Boleto Bancário Pedido: "+pedido.getId());
            helper.setTo(pedido.getCliente().getEmailCliente());
            helper.setFrom(pedido.getCliente().getEmailCliente());

            FileSystemResource file = new FileSystemResource("/users/treinamento/boleto.png");
            helper.addAttachment(file.getFilename(), file);

        } catch (Exception e){
            System.out.println("Ocorreu um erro ao gerar o mimeMessage"+e.getMessage());
            e.printStackTrace();
        }


        try {
            mailSender.send(mimeMessage);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar email.";
        }
    }
}
