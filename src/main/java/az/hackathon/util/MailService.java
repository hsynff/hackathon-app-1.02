package az.hackathon.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;

@Repository
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public boolean sendEmail(String from,String to,String subject,String content){

        try{
            SimpleMailMessage smm=new SimpleMailMessage();
            smm.setFrom(from);
            smm.setTo(to);
            smm.setSubject(subject);
            smm.setText(content);
            javaMailSender.send(smm);
            return true;

        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
}
