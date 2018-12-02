package Design.CampusConnect.Email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;


@Component("CampusConnectMailSender")
public class email {

    public email(){

        javaMailSender = new JavaMailSenderImpl();
        /*((JavaMailSenderImpl) javaMailSender).setHost("smtp.gmail.com");
        ((JavaMailSenderImpl) javaMailSender).setPort(587);
        ((JavaMailSenderImpl) javaMailSender).setUsername("alexb1203");
        ((JavaMailSenderImpl) javaMailSender).setPassword("Player1996!!");*/

        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);

        javaMailSender.setUsername("confirmcampusconnect@gmail.com");
        javaMailSender.setPassword("toortoor");

        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
    }

    /*
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=alexb1203
spring.mail.password=Player1996!!

#mail properties

*/

   // @Autowired
    JavaMailSenderImpl javaMailSender;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void sendMail(String from, String to, String subject, String body) {

        //javaMailSender = this.getBean("mailSender");

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setFrom(from);
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(body);

        logger.info("Sending...");

        javaMailSender.send(mail);

        logger.info("Done!");
    }
}