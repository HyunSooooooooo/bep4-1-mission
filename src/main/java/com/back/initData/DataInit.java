package com.back.initData;

import com.back.entity.Member;
import com.back.entity.Post;
import com.back.service.MemberService;
import com.back.service.PostService;
import jakarta.transaction.Transactional;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class DataInit {

    private final DataInit self;
    private final MemberService memberService;
    private final PostService postService;

    public DataInit(
            @Lazy DataInit self,
            MemberService memberService,
            PostService postService
    ) {
        this.self = self;
        this.memberService = memberService;
        this.postService = postService;
    }

    @Bean
    public ApplicationRunner baseInitDataRunner() {
        return args -> {
            //self.makeBaseMembers();
            self.makeBasePosts();
        };
    }

    public void makeBaseMembers() {
        memberService.join("user2", "1234", "유저2");
        memberService.join("user3", "1234", "유저3");
    }

    @Transactional
    public void makeBasePosts() {
        if (postService.count() > 0) return;

        Member user1Member = memberService.findByUsername("user1").get();
        Member user2Member = memberService.findByUsername("user2").get();
        Member user3Member = memberService.findByUsername("user3").get();

        postService.write(user1Member, "제목1", "내용1");
        postService.write(user1Member, "제목2", "내용2");
        postService.write(user1Member, "제목3", "내용3");
        postService.write(user2Member, "제목4", "내용4");
        postService.write(user2Member, "제목5", "내용5");
        postService.write(user3Member, "제목6", "내용6");
    }
}