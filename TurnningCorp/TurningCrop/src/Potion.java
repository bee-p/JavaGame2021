
public class Potion extends Item { // Potion Ŭ����
	
	// ������
	public Potion(String name, String description, int value) {
		super(name, description, value);
	}
		
	// ������ ���� �������� �޼ҵ�
	// �����ε�
	public String itemInfo() {
		String info = "�̸� : " + getName() + "\n";
		info += "���� : " + getDescription() + "\n";
		info += "��� : " + getValue() + "\n";
		
		return info;
	}
}
