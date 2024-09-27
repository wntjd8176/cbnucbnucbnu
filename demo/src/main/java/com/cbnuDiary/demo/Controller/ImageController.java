package com.cbnuDiary.demo.Controller;

import com.cbnuDiary.demo.Service.UserService;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
@RestController
public class ImageController {
    @Autowired
    UserService userService;
/*
    @PostMapping("/upload")  //안스에서 넘긴 사진파일을 MultipartFile 인터페이스로 서버가 받는다
    public ResponseEntity<String> handleFileUpload(@RequestParam("file")MultipartFile file){
        try{
            String extractedText = extractTextFromImage(file);

            if (extractedText.contains("임산부")) {
                userService.updatePregStatus();
            }

            return ResponseEntity.ok("Image processed successfully");
        } catch (TesseractException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing image");
        }
    }

    private String extractTextFromImage(MultipartFile file) throws Exception {
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
        file.transferTo(convFile);

        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath("/C:/Users/wntjd/Downloads/tesseract-main/tesseract-main/tessdata");
        return tesseract.doOCR(convFile);
    }*/
}
