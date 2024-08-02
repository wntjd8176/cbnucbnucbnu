package com.cbnuDiary.demo.Controller;

import com.cbnuDiary.demo.Dto.UserDTO;
import com.cbnuDiary.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    //안스에서 회원가입시 기입한 유저정보를 JSON형식으로 넘겨준값을 Dto로 바꾸고 그걸 Service계층에서 Entity로 바꾼후에 Dao를 이용해 db에 저장한다.
    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@RequestBody UserDTO userDTO){
        try{
            userService.registerUser(userDTO);
            return ResponseEntity.ok.build();
        }catch (EmailAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); //404


        }
    }
}
