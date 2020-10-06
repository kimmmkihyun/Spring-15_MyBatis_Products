package member.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import utility.Paging;

@Component("myMemberDao")
public class MemberDao {

	private String namespace = "member.model.Member";
	@Autowired //객체는 root-context.xml에서 만들었다
	SqlSessionTemplate sqlSessionTemplate;
	
	public int insertMember(Member member) {
		int cnt = sqlSessionTemplate.insert(namespace+".insertMember", member);
		return cnt;
	}

	public int getTotalCount(Map<String, String> map) {
		int cnt = sqlSessionTemplate.selectOne(namespace+".GetTotalCount",map);
		return cnt;
	}

	public List<Member> getMemberList(Paging pageInfo, Map<String, String> map) {
		List<Member> lists = new ArrayList<Member>();
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
		lists = sqlSessionTemplate.selectList(namespace+".GetListMember",map,rowBounds);
		System.out.println("lists.size():"+lists.size());
		return lists; 
	}

	public void deleteMember(String id) {
		int cnt = sqlSessionTemplate.delete(namespace+".DeleteMember", id);
		System.out.println("delete cnt : "+cnt);
		
	}

	public Member checkData(String id) {
		Member member = sqlSessionTemplate.selectOne(namespace+".checkData",id);
		return member;
	}

}
