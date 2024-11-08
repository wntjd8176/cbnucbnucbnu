package com.cbnuDiary.demo.Service;

import com.cbnuDiary.demo.Controller.CounselingController;
import com.cbnuDiary.demo.Dao.UserDAO;
import com.cbnuDiary.demo.Dto.CounselingCenterDTO;
import com.cbnuDiary.demo.Dto.GpsDTO;
import com.cbnuDiary.demo.Dto.UserDTO;
import com.cbnuDiary.demo.Entity.UserEntity;
import com.cbnuDiary.demo.Exception.UserNotFoundException;
import com.cbnuDiary.demo.Exception.userIDAlreadyExistsException;
import com.cbnuDiary.demo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private  final ThreeNoticeService threeNoticeService;
    private final CounselingController counselingController;
    private final RestTemplate restTemplate;
    private  final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private final UserDAO userDAO;

    private final NaverMailService naverMailService;

    private  final WarningService warningService;

    /*@Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }*/
    @Override
    public UserEntity convertToEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();   //0910날 함 이부분 수정해야함
        userEntity.setuserID(userDTO.getUserID());
        userEntity.setname(userDTO.getName());
        userEntity.setemail(userDTO.getEmail());
        userEntity.setuserPW(userDTO.getUserPW());
        userEntity.setPregStatus(userDTO.getPreg());
        userEntity.setBabyName(userDTO.getBabyName());
        return userEntity;
    }
    @Override
    public UserDTO convertToDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserID(userEntity.getUserID());
        userDTO.setUserPW(userEntity.getUserPW());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setName(userEntity.getName());
        userDTO.setPreg(userEntity.getPregStatus());
        // 필요한 다른 필드도 설정...

        return userDTO;
    }


    @Override
    public boolean IDAvailable(String userID){
        return !userRepository.existsByuserID(userID);
    }

    @Override
    public void registerUser(UserDTO userDTO) {
        if (userDAO.existByUserID(userDTO.getUserID())) {
            throw new userIDAlreadyExistsException("Username already exists: " + userDTO.getUserID());
        }
        UserEntity userEntity = convertToEntity(userDTO);
        userDAO.insert(userEntity);
    }

    public void incrementDepressCount(String userID){
        Optional<UserEntity> userEntityOptional = userRepository.findByuserID(userID);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            userEntity.setDepressCnt(userEntity.getDepressCnt() + 1); // depresscnt 증가
            userRepository.save(userEntity); // 변경된 사용자 정보를 DB에 저장
        } else {
            throw new RuntimeException("사용자를 찾을 수 없습니다: " + userID); // 예외 처리
        }
    }

    @Override
    public void updatePregStatus(String extractedText, String userID) {
        if (extractedText.contains("임산부")) {
            // 사용자 정보 가져오기    이 부분 수정해야함 0929
            UserEntity userEntity = userRepository.findByuserID(userID).orElseThrow(() -> new RuntimeException("User not found"));

            // preg 변수 증가
            userEntity.setPregStatus(userEntity.getPregStatus() + 1);

            // 변경 사항 저장
            userRepository.save(userEntity);
        }
    }

    @Override
    public void changeID(UserDTO userDTO,String newID){
        String currentID = userDTO.getUserID();
        Optional<UserEntity> optionalUser = userRepository.findByuserID(currentID);
        if (optionalUser.isPresent()) {
            UserEntity userEntity = optionalUser.get();
            userEntity.setuserID(newID);

            userDAO.insert(userEntity);
        }else{
            throw new IllegalArgumentException("User with ID " + currentID + " not found.");
        }

    }
    @Override
    public void changePW(String userID,String oldPW,String newPW){
        UserEntity userEntity = userRepository.findByuserID(userID)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 기존 비밀번호 확인
        if (!passwordEncoder.matches(oldPW, userEntity.getUserPW())) {
            throw new RuntimeException("Old password is incorrect");
        }

        // 새 비밀번호 암호화 후 저장
        userEntity.setuserPW(passwordEncoder.encode(newPW));
        userRepository.save(userEntity);
    }



    @Override
    public boolean loginApp(String userID , String userPW){
        return userDAO.checkUserCredentials(userID, userPW);

    }


    @Override
    public void updateEmail(String userID,String newEmail){
        UserEntity userEntity = userRepository.findByuserID(userID)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userEntity.setEmail(newEmail);
        userRepository.save(userEntity);

    }

    @Override
    public void updateBabyName(String userName,String newBabyName){

        UserEntity userEntity = userRepository.findByname(userName)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userEntity.setBabyName(newBabyName); // babyName 업데이트
        userRepository.save(userEntity);

    }

    @Override
    public void updateEventDay(String eventDay){


    }



    @Override
    public void withdraw(String userID,String userPW){
       UserEntity userEntity = userRepository.findByuserID(userID)
               .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(userPW, userEntity.getUserPW())) {
            throw new IllegalArgumentException("Incorrect password");
        }

        userRepository.delete(userEntity);
    }


    @Override
    public void findID(String name , String email) {
        Optional<UserEntity> user = userRepository.findByNameAndEmail(name, email);

        // 사용자가 존재하지 않는 경우 예외를 던집니다.
        UserEntity userEntity = user.orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        // userID를 이메일로 전송합니다.
        naverMailService.sendEmail(email, userEntity.getUserID());

    }

    @Override
    public void findPW(String userID, String email){


   }

   @Transactional
    @Override
    public void checkDepression(UserEntity userEntity) {
       if(userEntity.getDepressCnt() ==3){
           String fcmToken = userEntity.getFcmToken();  // 사용자의 FCM 토큰을 가져옵니다.  아래꺼랑 선택해서쓰기
           threeNoticeService.sendDepressionAlert(fcmToken);
           // warningService.sendPushNotification(fcmToken, "우울감 알림", "우울감이 감지되었습니다. 상담이 필요할 수 있습니다.");
        }
        /*
        if (userEntity.getDepressCnt() == 5) {
            // Android Studio로 GPS 요청
            String url = "http://android-device/api/user/gps"; // Android API 주소
            GpsDTO gpsDTO = restTemplate.getForObject(url, GpsDTO.class);

            // GPS 정보를 이용해 상담소 목록을 조회하고 다시 Android로 전송
            List<CounselingCenterDTO> centers = counselingController.getCounselingCenters(gpsDTO).getBody();
            sendCentersToAndroid(centers);
        }*/
    }


    @Override
    public void sendCentersToAndroid(List<CounselingCenterDTO> centers) {
        String url = "http://android-device/api/counseling/centers"; // Android API 주소
        restTemplate.postForObject(url, centers, Void.class);
    }

    @Override
    public UserDTO getUserByID(String userID) {
        // userRepository를 사용하여 DB에서 사용자 정보를 조회
        Optional<UserEntity> userEntityOptional = userRepository.findByuserID(userID);
        return userEntityOptional.map(this::convertToDTO).orElse(null);
    }

}
    /*@Override
    public boolean checkUserExist(UserDTO userDTO){
        String userID = userDTO.getUserID();
        return userRepository.existsByuserId(userID);
    } */


