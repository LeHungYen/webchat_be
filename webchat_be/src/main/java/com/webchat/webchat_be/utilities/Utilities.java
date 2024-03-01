package com.webchat.webchat_be.utilities;

import com.webchat.webchat_be.dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class Utilities {

    public static UserDTO getUserDTOFromContext(){
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(SecurityContextHolder.getContext().getAuthentication().getPrincipal() , userDTO);
        return userDTO;
    }

    private static String normalizeFileName(String fileName) {
        // Replace spaces with underscores
        fileName = fileName.replaceAll("\\s+", "_");

        // Remove non-alphanumeric characters
        fileName = fileName.replaceAll("[^a-zA-Z0-9._]", "");

        // Convert Vietnamese accented characters to their unaccented equivalents
        fileName = java.text.Normalizer.normalize(fileName, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        return fileName;
    }

    public static String saveFile(MultipartFile file) throws IOException {
        // mac
//        String directoryPath = "/Users/lehungyen/Desktop/chatSphereFe/webchatFE/src/assets/imgs";
        // loc
//        String directoryPath = "C:/Users/Loc/Desktop/socialsphere/src/assets/imgs";
        // pc
        String directoryPath = "C:/Users/yenbo/Desktop/socialsphere/webchat_fe/src/assets/imgs";

        // Create the directory if it doesn't exist
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Normalize the file name
        String originalFileName = file.getOriginalFilename();
        String normalizedFileName = normalizeFileName(originalFileName);

        // Save the file to the directory
        String filePath = directoryPath + File.separator + normalizedFileName;
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(file.getBytes());
        }

        return normalizedFileName;
    }

    public static void sendMail(String to , String subject , String body){
        Properties props = new Properties();
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.port", "587");

        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");


        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                String username = "message.socialsphere@gmail.com";
                String password = "yexr ghxm czew qusu";

                return  new PasswordAuthentication(username, password);
            }

        });

        session.getProperties().put("mail.smtp.starttls.enable", "true");

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("message.socialsphere@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, to);
            message.setSubject(subject , "utf-8");
            message.setText(body , "utf-8" , "html");
            message.setReplyTo(message.getFrom());
            Transport.send(message);

        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static String generateRandomNumber() {
        Random random = new Random();
        int randomNumber = 100000 + random.nextInt(900000);
        return String.valueOf(randomNumber);
    }
}
