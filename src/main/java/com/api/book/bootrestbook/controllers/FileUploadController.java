package com.api.book.bootrestbook.controllers;

import com.api.book.bootrestbook.helper.FileUploadHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class FileUploadController {
    
    @Autowired
    FileUploadHelper fileUploadHelper;

    @PostMapping("/upload-file")
    public ResponseEntity<String> fileUpload(@RequestParam("file") MultipartFile file)
    {
        // System.out.println(file.getOriginalFilename());
        // System.out.println(file.getSize());
        // System.out.println(file.getContentType());
        try{
            if(file.isEmpty())
            {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("kindly, upload file!!");
            }
            if(!file.getContentType().equals("image/jpeg"))
            {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("kindly, upload file of type image/png!!");
            }
            boolean f = fileUploadHelper.fileUpload(file);
            if(f)
            {
                //return ResponseEntity.ok("File is sucessfully Uploaded!");
                return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(file.getOriginalFilename()).toUriString());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong please try again!");
    }

}
