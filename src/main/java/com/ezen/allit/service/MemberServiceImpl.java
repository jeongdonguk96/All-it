package com.ezen.allit.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.allit.domain.Member;
import com.ezen.allit.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberRepository memberRepo;

	@Override
	public Member getMember(Member member) {
		
		Optional<Member> findMember = memberRepo.findById(member.getId());
		
		if(findMember.isPresent()) {
			return findMember.get();
		} else {
			return null;
		}
	}

	@Override
	public void saveMember(Member member) {
		
		memberRepo.save(member);
		
	}

	@Override
	public int idCheck(String id) {
		Optional<Member> findId = memberRepo.findById(id);
		
		if(findId.isPresent()) {
			System.out.println("이미 사용중인 아이디");
			return 0;
		} else {
			System.out.println("사용 가능한 아이디");
			return -1;
		}
	}

	@Override
	public String findById(String name, String email) {
		return memberRepo.findId(name, email);
	}

	@Override
	public String findByPw(String id, String name, String email) {
		return memberRepo.findPw(id, name, email);
	}

	@Override
	public void deleteMember(String id) {
		memberRepo.deleteById(id);
	}


}