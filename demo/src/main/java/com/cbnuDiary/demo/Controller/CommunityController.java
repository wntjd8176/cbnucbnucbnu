package com.cbnuDiary.demo.Controller;

import com.cbnuDiary.demo.Repository.CommentRepository;
import com.cbnuDiary.demo.Repository.PostRepository;
import com.cbnuDiary.demo.Service.CommentService;
import com.cbnuDiary.demo.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommunityController {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    private final PostService postService;
    private final CommentService commentService;



}
