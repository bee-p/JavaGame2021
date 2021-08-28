
public class DefensePotion extends Potion { // ���� ���� ������

	// ������
	public DefensePotion(String name, String description, int value) {
		super(name, description, value);
	}
	
	// ���� ��� �� ���� ����, true�� ����
	public boolean useDefensePotion(Player player) {
		int defense = getValue();
		player.increaseDefense(defense);  // PlayerŬ������ �ִ� �Լ��� %�� ����
		
		return true;
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
