package com.cbnuDiary.demo.Service;

import com.cbnuDiary.demo.Dao.UserDAO;
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



import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

   private  final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private final UserDAO userDAO;

    private final NaverMailService naverMailService;

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
        return userEntity;
    }

    @Override
    public void registerUser(UserDTO userDTO) {
        if (userDAO.existByUserID(userDTO.getUserID())) {
            throw new userIDAlreadyExistsException("Username already exists: " + userDTO.getUserID());
        }
        UserEntity userEntity = convertToEntity(userDTO);
        userDAO.insert(userEntity);
    }

    @Override
    public void updatePregStatus(String extractedText, String userID) {
        if (extractedText.contains("임산부수첩")) {
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
        Optional<UserEntity> user = userRepository.findBynameAndemail(name, email);

        // 사용자가 존재하지 않는 경우 예외를 던집니다.
        UserEntity userEntity = user.orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        // userID를 이메일로 전송합니다.
        naverMailService.sendEmail(email, userEntity.getUserID());

    }

    @Override
    public void findPW(String userID, String email){


   }

}
    /*@Override
    public boolean checkUserExist(UserDTO userDTO){
        String userID = userDTO.getUserID();
        return userRepository.existsByuserId(userID);
    } */


