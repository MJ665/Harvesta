package com.courseweb.web.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.courseweb.web.Dao.Dao;
import com.courseweb.web.Model.Admin;
import com.courseweb.web.Model.Crops;
import com.courseweb.web.Model.User;
import com.fasterxml.jackson.core.JsonProcessingException;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private Dao dao;

    @Autowired
    private JavaMailSender emailsender;

    public String addUser(User user) {
        return dao.addUser(user);
    }

    public String viewAdmin(Admin admin) {
        return dao.viewAdmin(admin);
    }

    public String setCropPrice(Crops crop) throws JsonProcessingException {
        dao.setCropsPrice(crop);
        return "Price Updated Successfully";
    }

    public List<Crops> viewCrops() {
        return dao.viewCrops();
    }

    public String emailData() throws JsonProcessingException {
        List<User> users = this.dao.allUser();
        List<Crops> crops = this.dao.viewCrops();
        String cropTable=cropsTable(crops);
        users.forEach((user) -> {
            try {
                sendEmail(user.getUserEmail(), "Todays crops price", cropTable);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });
        return "Email sent successfully";

    }

    public String cropsTable(List<Crops> crops) {
        StringBuilder cropsTable = new StringBuilder();

        cropsTable.append("<html><body><h1>Todays crops price</h1><table>");
        cropsTable.append("<tr>");
        cropsTable.append("<th >Crop Name</th>");
        cropsTable.append("<th>Crop Price</th>");
        cropsTable.append("<tr>");


        int index = 0;
        while (crops.size() != index) {
            cropsTable.append("<tr>");
            cropsTable.append("<th>").append(crops.get(index).getCropName()).append("</th>");
            cropsTable.append("<th>").append(crops.get(index).getCropPrice()).append("</th>");
            cropsTable.append("</tr>");
            index++;
        }
        cropsTable.append("</table></body></html>");

        return cropsTable.toString();

    }

    public void sendEmail(String to, String subject, String text) throws MessagingException {
        MimeMessage mimeMessage = emailsender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setTo("recipient@example.com");
        helper.setSubject("Harvesta: Your Daily Crop Price Companion");
        helper.setText(text, true); 
        mimeMessage.setRecipients(MimeMessage.RecipientType.TO, to);

        emailsender.send(mimeMessage);
    }

}
