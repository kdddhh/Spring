package chap03.model;

import java.util.*;

public class MemberDAO {
	private static long nextId = 0;
	private Map<String, Member> members;
	// 배열이나 컬렉션은 여러 값들을 저장(값만)한다. 맵은 키와 밸류를 함께 저장(하나의 쌍으로)한다.
	// 맵은 인터페이스이기 때문에 맵을 구현한 클래스로만 인스턴스가 생성이 가능하다.
	
	public MemberDAO() {
		this.members = new HashMap<>();
	}
	// 여기선 해쉬맵을 이용하여 맵 인스턴스를 생성
	
	public Member selectByEmail(String email) {
		return this.members.get(email);
	}
	
	public void insert(Member member) {
		member.setId(++MemberDAO.nextId);
		members.put(member.getEmail(), member);
	}
	
	public void update(Member member) {
		members.put(member.getEmail(), member);
	}
	
	public Collection<Member> selectAll() {
		return members.values(); // 키가 아닌 값들이 필요해서 밸류즈를 사용하고 밸류즈의 반환형이 collection<member>임
	}
	
}
