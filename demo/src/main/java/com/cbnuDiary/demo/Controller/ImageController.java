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
@RequestMapping("/ocr")
public class ImageController {
    @Autowired
    UserService userService;

    @PostMapping("/upload")  //안스에서 넘긴 사진파일을 MultipartFile 인터페이스로 서버가 받는다
    public ResponseEntity<String>pregCertify(@RequestParam("image")MultipartFile image, @RequestParam("userID")String userID) {

        try {
            Tesseract tesseract = new Tesseract();

            tesseract.setDatapath("C://tessdata//tessdata_best-main");
            tesseract.setLanguage("kor");

            File tempFile = File.createTempFile("tempImage", ".jpg");
            image.transferTo(tempFile);

            String extractedText = tesseract.doOCR(tempFile);
            tempFile.delete();

            userService.updatePregStatus(extractedText,userID);

            return ResponseEntity.ok("OCR 처리 완료");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }


    }
    }

