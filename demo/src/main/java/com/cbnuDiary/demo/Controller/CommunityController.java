package com.cbnuDiary.demo.Controller;

import com.cbnuDiary.demo.Dto.UserDTO;
import com.cbnuDiary.demo.Repository.CommentRepository;
import com.cbnuDiary.demo.Repository.PostRepository;
import com.cbnuDiary.demo.Service.CommentService;
import com.cbnuDiary.demo.Service.PostService;
import com.cbnuDiary.demo.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommunityController {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    private final UserService userSerivce;

    private final PostService postService;
    private final CommentService commentService;

    @GetMapping("/access")
    public ResponseEntity<String> accessCommunity(@RequestParam String userId) {
        try {
            UserDTO userDTO = userSerivce.getUserByID(userId); // 사용자 정보를 DB에서 가져옴

            if (userDTO != null && userDTO.getPreg() == 1) {

                return ResponseEntity.ok("Access granted to community.");
            } else {

                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("임산부 인증을 해주세요");
                //프론트로 특정 값을 보내고 그 값을 받으면 카메라 연동되게 구현해야 될듯.
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

}
