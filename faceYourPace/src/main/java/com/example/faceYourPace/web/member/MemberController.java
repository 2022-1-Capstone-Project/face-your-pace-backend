package com.example.faceYourPace.web.member;

import com.example.faceYourPace.domain.member.Member;
import com.example.faceYourPace.domain.member.MemberService;
import com.example.faceYourPace.domain.music.Music;
import com.example.faceYourPace.domain.music.MusicForm;
import com.example.faceYourPace.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @GetMapping("/auth/signup")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new com.example.faceYourPace.web.member.MemberForm());
        return "회원가입 페이지";
    }

    @PostMapping("/auth/signup")
    public String create(@Valid MemberForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "false";
        }

        Member member = new Member();
        member.setUserId(form.getUserId());
        member.setUserPw(form.getUserPw());
        member.setUserName(form.getUserName());
        member.setUserEmail(form.getUserEmail());
        member.setUserAge(form.getUserAge());
        member.setUserHeight(form.getUserHeight());
        member.setUserWeight(form.getUserWeight());
        member.setCreateDate(LocalDateTime.now());

        memberService.join(member);
        return "true";
    }

    //@GetMapping("/api/mypage/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "회원 리스트";
    }

    @GetMapping("/api/mypage/members")
    List<Member> getAll() {
        return memberRepository.findAll();
    }

    @GetMapping("/api/mypage/members/{userId}")
    List<Member> getUserIdAllDate(@PathVariable("userId") String userId) {
        return memberRepository.findByUserId(userId);
    }

    @GetMapping("auth/user/{userId}/edit")
    public String updateMemberForm(@PathVariable("memberId") Long memberId, Model model) {
        Member member = (Member) memberService.findOne(memberId);
        // update 안한 값들은 NULL로 저장되는 문제 해결할 것

        MemberForm form = new MemberForm();
        form.setUserName(member.getUserName());
        form.setUserEmail(member.getUserEmail());
        form.setUserAge(member.getUserAge());
        form.setUserHeight(member.getUserHeight());
        form.setUserWeight(member.getUserWeight());

        model.addAttribute("form", form);
        return "회원정보 update";
    }

    @PostMapping("auth/user/{memberId}/edit")
    public String updateMember(@PathVariable Long memberId, @ModelAttribute("form") Member member) {

        memberService.update(memberId, member);

        return "회원정보 update";
    }
}
