package neomerger;

public class NeoPage {

	private String fileName;
	private Integer order;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public void setOrder(String order) {
		this.order = Integer.parseInt(order);
	}
	
	public NeoPage() {
		
	}
	
	public NeoPage(String fileName) {
		this.fileName = fileName;
		this.order = getOrderNumber(fileName);
	}
	
	private Integer getOrderNumber(String fileName) {
		// TODO Auto-generated method stub
		String strNum = fileName.substring(0, fileName.indexOf(")"));		
		return Integer.parseInt(strNum);
	}
	
}
