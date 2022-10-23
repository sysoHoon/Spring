package day2.mapper;

import java.util.List;

import day2.vo.Member;

public interface MemberMapper {
	//member.xml 매퍼파일과 연관되는 매퍼 인터페이스입니다.
	//추상메소드는 매퍼 xml파일의 id로 작성합니다.
	
	void insert(Member member);
	List<Member> selectAll();
	Member selectOne(int custno);
	int deleteOne(int custno);
	int update(Member member);
	
}
