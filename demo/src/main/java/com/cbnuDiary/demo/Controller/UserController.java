package com.cbnuDiary.demo.Controller;

import com.cbnuDiary.demo.Dto.UserChartDTO;
import com.cbnuDiary.demo.Dto.UserDTO;
import com.cbnuDiary.demo.Service.UserChartService;
import com.cbnuDiary.demo.Service.UserChartServiceImpl;
import com.cbnuDiary.demo.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cbnuDiary.demo.Exception.userIDAlreadyExistsException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserChartService userChartService;

    // @RequestMapping("/api/users")
  /*  @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public UserController(UserChartService userChartervice) {
        this.userChartService = userChartService;
    }*/


    //안스에서 회원가입시 기입한 유저정보를 JSON형식으로 넘겨준값을 Dto로 바꾸고 그걸 Service계층에서 Entity로 바꾼후에 Dao를 이용해 db에 저장한다.
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<UserChartDTO> registerUser(@RequestBody UserDTO userDTO) {
        try {
            userService.registerUser(userDTO);
            UserChartDTO userChartDTO = userChartService.createUserChart(userDTO);
            return ResponseEntity.ok(userChartDTO);
        } catch (userIDAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); //404


        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/regUserChart", method = RequestMethod.POST)
    public ResponseEntity<String> saveUserChart(@RequestBody UserChartDTO userChartDTO) {

        userChartService.saveUserChart(userChartDTO);
        return ResponseEntity.ok("특성표가 저장되었습니다.");
    }
}