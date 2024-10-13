package com.cbnuDiary.demo;

import com.cbnuDiary.demo.Dao.DiaryDAO;
import com.cbnuDiary.demo.Dao.DiaryDAOImpl;
import com.cbnuDiary.demo.Dao.UserDAO;
import com.cbnuDiary.demo.Dao.UserDAOImpl;
import com.cbnuDiary.demo.Repository.DiaryRepository;
import com.cbnuDiary.demo.Repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CbnuDiaryConfig {

    private final UserRepository userRepository;
    private final DiaryRepository diaryRepository;
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public CbnuDiaryConfig(UserRepository userRepository,DiaryRepository diaryRepository){
        this.userRepository=userRepository;
        this.diaryRepository = diaryRepository;
    }
    @Bean
    public UserDAO userDAOImpl(UserRepository userRepository){
        return new UserDAOImpl(userRepository);
    }
    @Bean
    public DiaryDAO diaryDAOImpl(DiaryRepository diaryRepository){return new DiaryDAOImpl(diaryRepository);}
    //이건 메소드 이름이라 LocationDAOImpl에서 locationDAOImpl로 바꿈 이게 통상적임                 //생성자를 통한 의존성 주입

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder(); // BCrypt 사용
    }
}
