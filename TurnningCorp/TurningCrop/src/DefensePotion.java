
public class DefensePotion extends Potion { // ���� ���� ������

	// ������
	public DefensePotion(String name, String description, int value) {
		super(name, description, value);
	}
	
	// ������ ���� �������� �޼ҵ�
	// �����ε�
	public String itemInfo() {
		String info = "�̸� : " + getName() + "\n";
		info += "���� : " + getDescription() + "\n";
		info += "��� : " + getValue() + "��ŭ ���� ����\n";
		
		return info;
	}
}
