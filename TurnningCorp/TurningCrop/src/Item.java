
public class Item { // ��� �����ۿ� ���� �⺻ ������ Ŭ����
	private String name; // ������ ����
	private String description; // ������ ����
	private int value; // �ɷ�ġ ������ ���� ��ġ��
	
	// �⺻ ������
	public Item() {
		
	}
	
	// ������
	public Item(String name, String description, int value) {
		this.name = name;
		this.description = description;
		this.value = value;
	}
	
	// getter & setter �޼ҵ�
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getValue() {
		return value;
	}
	
	// ������ ������ �ϱ���, ����� ���� �������� value������ ȹ�� ���θ� �Ǻ�
	// 0 : ��ȹ��, 1 : ȹ��
	public void setValue(int value) {
		this.value = value;
	}
	
	// ������ ���� �������� �޼ҵ�
	public String itemInfo() {
		String info = "�̸� : " + name + "\n";
		info += "���� : " + description + "\n";
		
		return info;
	}
}
