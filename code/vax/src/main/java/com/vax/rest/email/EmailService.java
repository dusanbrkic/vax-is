package com.vax.rest.email;

import lombok.AllArgsConstructor;
import proj.xml.gradj.interesovanje.Interesovanje;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;


@Component
@AllArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public void sendMailInteresovanjeCreated(Interesovanje i) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("xmlprojekat@gmail.com");
        message.setTo(i.getAdresaElektronskePoste());
        message.setSubject(String.format("Interesovanje za vakcinaciju"));
        message.setText(InteresovanjeToStr(i));

        mailSender.send(message);
    }
    
    public String InteresovanjeToStr(Interesovanje i) {
    	StringBuilder res = new StringBuilder("");
    	 ReflectionUtils.doWithFields(i.getClass(), field -> {
    		 	if (field.getName().contentEquals("jmbg")) {
    		 		field.setAccessible(true);
        	        res.append(field.getName() + ": ");
        	        res.append(i.getJMBG().getValue() + "\n");
        	        
    		 	}else if (field.getName().contentEquals("lokacija")) {
    		 		field.setAccessible(true);
        	        res.append(field.getName() + ": ");
        	        res.append(i.getLokacija().getValue() + "\n");
        	        
    		 	}else if (field.getName().contentEquals("datum")) {
    		 		field.setAccessible(true);
        	        res.append(field.getName() + ": ");
        	        res.append(i.getDatum().getValue() + "\n");
        	        
    		 	}else {
    		 		field.setAccessible(true);
        	        res.append(field.getName() + ": ");
        	        res.append(field.get(i) + "\n");
    		 	}
    	        

    	    });
    	 return res.toString();
    }

  

}
