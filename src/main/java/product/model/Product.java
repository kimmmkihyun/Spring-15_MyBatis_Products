package product.model;

import javax.validation.constraints.Min;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;



public class Product {
	private int num;
	@Length(min = 3,max = 10,message = "��ǰ �̸��� �ּ� 3�ڸ� �ִ� 10�ڸ� �Դϴ�.")
	private String name;
	
	private String company;
	
	@NotEmpty(message = "ȭ�� ���� ����")
	private String image;
	
	private int stock;
	@Min(value = 3000,message = "������ �ּ� 3000�� �̻� �̿��� �մϴ�.")
	private int price;
	
	private String category;
	@Length(min = 10,max = 15,message = "��ǰ������ �ּ� 10�ڸ� ~ �ִ� 15�ڸ� �Դϴ�.")
	private String contents;
	
	private int point;
	
	private String inputdate;
	
	private int orderqty; //�ֹ�����
	private String uploadOld;
	
	private MultipartFile upload; //������ ȭ���� ������ ����� ���� ���´�
	
	
	public Product(int num, String name, String company, String image, int stock, int price, String category,
			String contents, int point, String inputdate) {
		super();
		this.num = num;
		this.name = name;
		this.company = company;
		this.image = image;
		this.stock = stock;
		this.price = price;
		this.category = category;
		this.contents = contents;
		this.point = point;
		this.inputdate = inputdate;
	}
	
	public String getUploadOld() {
		return uploadOld;
	}

	public void setUploadOld(String uploadOld) {
		this.uploadOld = uploadOld;
	}

	public MultipartFile getUpload() {
		return upload;
	}

	public void setUpload(MultipartFile upload) {
		this.upload = upload;
		System.out.println("upload : "+upload);// ~~.jpg (X)
		if(upload != null) {
			System.out.println(upload.getName()); // upload
			System.out.println(upload.getOriginalFilename()); // ~~.jpg  , ��¥ ȭ���̸�
			this.image = upload.getOriginalFilename();  
		}
	}

	public Product() {
		super();
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getInputdate() {
		return inputdate;
	}
	public void setInputdate(String inputdate) {
		this.inputdate = inputdate;
	}
	public int getOrderqty() {
		return orderqty;
	}
	public void setOrderqty(int orderqty) {
		this.orderqty = orderqty;
	}
	
	
}
