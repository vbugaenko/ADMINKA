package ru.innopolis.stc9.saturn.controller;

import com.itextpdf.text.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.stc9.saturn.db.dao.UsersListJpaDaoImpl;
import ru.innopolis.stc9.saturn.db.entities.User;
import ru.innopolis.stc9.saturn.service.PDF;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Victor Bugaenko
 * @since 08.07.2018
 */

@RestController
@RequestMapping("/pdf")
public class PDFController {

    /*
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public User getPDF(@PathVariable String id)
    {
        for (User user : new UsersListJpaDaoImpl().getAllUsers())
            if (user.getID() == recogInt(id))
                return user;
            else
                return null;
    }
    */

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getPDF(@PathVariable String id)
    {
        for (User user : new UsersListJpaDaoImpl().getAllUsers())
            if (user.getID() == recogInt(id))
            {
                try {
                    Document document = new PDF().createPDF(user);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }

        byte[] contents = new byte[0];
        try {
            contents = Files.readAllBytes(Paths.get("user.pdf"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String filename = "user.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
        return response;
    }

    private int recogInt(String str) {
        if ((str != null) && (!str.equals(""))) {
            try {
                return Integer.parseInt(str);
            } catch (Exception e) {
            }
        }
        return 0;
    }
}