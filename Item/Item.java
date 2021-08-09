
public class Item { // ��� �����ۿ� ���� �⺻ ������ Ŭ����
	private String name; // ������ ����
	private String description; // ������ ����
	private int value; // �ɷ�ġ ������ ���� ��ġ��
	private int type; // �������� ���̵�
					  // �ٸ� Ŭ�������� � ���������� �Ǻ��ϱ� ���� ���� ���̵� ����  
	public Item() {
		
	}
	
	// ������
	public Item(String name, String description, int value, int type) {
		this.name = name;
		this.description = description;
		this.value = value;
		this.type =type;
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
	
	public int getType() {
		return type;
	}
	
	// ������ ���� �������� �޼ҵ�
	public String itemInfo() {
		String info = "�̸� : " + name + "\n";
		info += "���� : " + description + "\n";
		
		return info;
	}
}
