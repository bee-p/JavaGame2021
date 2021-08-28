
public class Item { // ��� �����ۿ� ���� �⺻ ������ Ŭ����
	String name; // ������ ����
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
	
	// getter �޼ҵ�
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getValue() {
		return value;
	}
	
	// ������ ���� �������� �޼ҵ�
	public String itemInfo() {
		String info = "�̸� : " + name + "\n";
		info += "���� : " + description + "\n";
		
		return info;
	}
	
}
