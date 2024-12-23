package com.cbnuDiary.demo.Controller;

import com.cbnuDiary.demo.Dto.UserChartDTO;
import com.cbnuDiary.demo.Dto.UserDTO;
import com.cbnuDiary.demo.Exception.UserNotFoundException;
import com.cbnuDiary.demo.Service.DiaryService;
import com.cbnuDiary.demo.Service.UserChartService;
import com.cbnuDiary.demo.Service.UserChartServiceImpl;
import com.cbnuDiary.demo.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
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

    private  final DiaryService diaryService;

    // @RequestMapping("/api/users")
  /*  @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public UserController(UserChartService userChartervice) {
        this.userChartService = userChartService;
    }*/

    @RequestMapping(value = "/findID",method = RequestMethod.GET)
    public ResponseEntity<String> findUserID(@RequestParam String userName, @RequestParam String email) {
        try {
            userService.findID(userName, email);
            return ResponseEntity.ok("이메일 전송이 완료되었습니다.");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자를 찾을 수 없습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이메일 전송 중 오류가 발생했습니다.");
        }
    }
 @RequestMapping(value = "/loginApp",method = RequestMethod.POST)
 public ResponseEntity<String> login(@RequestParam String userID, @RequestParam String userPW){
     boolean isAuthenticated = userService.loginApp(userID, userPW);
     if (isAuthenticated) {
         return ResponseEntity.ok("Login successful");
     } else {
         return ResponseEntity.status(401).body("Invalid credentials");
     }
 }

    @RequestMapping(value ="/checkIDAvailability",method = RequestMethod.GET)
    public ResponseEntity<Boolean> checkIDAvailability(@RequestParam("userID") String userID) {
        boolean isAvailable = userService.IDAvailable(userID);
        return ResponseEntity.ok(isAvailable);
    }

   @RequestMapping(value ="/updatePW/{userID}",method = RequestMethod.PUT)
   public ResponseEntity<String> updatePW(@PathVariable String userID, @RequestParam String oldPW, @RequestParam String newPW) {

       userService.changePW(userID, oldPW, newPW);
       return new ResponseEntity<>("Password updated successfully", HttpStatus.OK);
   }
   @RequestMapping(value="/updateBabyname",method = RequestMethod.PUT)
   public ResponseEntity<String> updateBabyName(@RequestParam String userName, String newBabyName){

       userService.updateBabyName(userName, newBabyName);
       return new ResponseEntity<>("Baby name updated successfully", HttpStatus.OK);
   }

   @RequestMapping(value="/withdraw",method = RequestMethod.DELETE)
   public ResponseEntity<String> withdrawUser(@PathVariable String userID,@RequestBody String userPW){
       userService.withdraw(userID,userPW);
       return new ResponseEntity<>("good bye", HttpStatus.OK);
   }

   @RequestMapping(value="/changeEmail",method = RequestMethod.PUT)
   public ResponseEntity<String> updateEmail(@RequestParam String userID,@RequestParam String newEmail){
       userService.updateEmail(userID,newEmail);
       return new ResponseEntity<>("Email updated successfully", HttpStatus.OK);
   }




    //안스에서 회원가입시 기입한 유저정보를 JSON형식으로 넘겨준값을 Dto로 바꾸고 그걸 Service계층에서 Entity로 바꾼후에 Dao를 이용해 db에 저장한다.
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<UserChartDTO> registerUser(@RequestBody UserDTO userDTO) {
        try {
            userService.registerUser(userDTO);
           // UserChartDTO userChartDTO = userChartService.createUserChart(userDTO);
            return ResponseEntity.ok().build();
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

    @RequestMapping(value = "updateDepressCnt",method =RequestMethod.POST )
    public ResponseEntity<Void> updateDepressCount(@RequestParam int result, @RequestParam String userID) {
        try {
            if (result == 4) { // 감정 분석 결과가 우울감인 경우
                userService.incrementDepressCount(userID); // 유저의 depresscnt 증가
                diaryService.setResultEmotion(userID,result);
                //userChartRepository.save(result)
                return ResponseEntity.ok().build();
            } else {
                diaryService.setResultEmotion(userID,result);
                return ResponseEntity.ok().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}