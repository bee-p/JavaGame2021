
public class HealingPotion extends Potion { // ȸ�� ������ Ŭ����

	// ������
	public HealingPotion(String name, String description, int value) {
		super(name, description, value);
	}
	
	// ������ ���� �������� �޼ҵ�
	// �����ε�
	public String itemInfo() {
		String info = "�̸� : " + getName() + "\n";
		info += "���� : " + getDescription() + "\n";
		info += "��� : " + getValue() + "% ��ŭ ü�� ����\n";
		
		return info;
	}
}
