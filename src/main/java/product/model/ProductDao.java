package product.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import utility.Paging;

@Component("myProductDao")
public class ProductDao {
	
	private String namespace = "product.model.Product";
	@Autowired //객체는 root-context.xml에서 만들었다
	SqlSessionTemplate sqlSessionTemplate;
		
	
	public List<Product> selectProductAll(Paging pageInfo, Map<String, String> map) {
		List<Product> lists = new ArrayList<Product>();
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
		lists = sqlSessionTemplate.selectList(namespace+".GetProductList",map,rowBounds);
		System.out.println("listcontroller lists.size():"+lists.size());
		return lists;
	}


	public int getTotalCount(Map<String, String> map) {
		int cnt = sqlSessionTemplate.selectOne(namespace+".GetTotalCount",map);
		return cnt;
	}


	public void insertProduct(Product product) {
		int cnt = sqlSessionTemplate.insert(namespace+".insertProduct",product);
		System.out.println("prodao insert cnt : "+cnt);
		
	}


	public Product getOneProduct(int num) {
		Product bean = new Product();
		bean = sqlSessionTemplate.selectOne(namespace+".GetOneProduct", num);
		return bean;
	}


	public int updateProduct(Product product) {
		int cnt = sqlSessionTemplate.update(namespace+".UpdateProduct", product);
		System.out.println("dao update cnt : "+cnt);
		return cnt;
	}


	public void deleteProduct(int num) {
		sqlSessionTemplate.delete(namespace+".DeleteProduct", num);
		
	}


	public void updateStock(Integer pnum, Integer qty) {
		Product bean = new Product();
		bean.setNum(pnum);
		bean.setStock(qty);
		sqlSessionTemplate.update(namespace+".UpdateStock", bean);
		
	}


	
}
