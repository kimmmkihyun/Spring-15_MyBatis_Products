package order.model;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("myOrderDao")
public class OrderDao {
	
	private final String namespace = "order.model.Order";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public void insertData(String id) {
		int cnt = sqlSessionTemplate.insert(namespace+".InsertData", id);
		System.out.println("order dao insert cnt : "+cnt);
	}

	public int getMaxOid() {
		int maxOid = sqlSessionTemplate.selectOne(namespace+".GetMaxOid");
		return maxOid;
	}

}
