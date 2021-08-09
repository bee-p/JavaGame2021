
	// ��� ����� NPC���� �Լ� �ۼ�
public class DefensePotion extends Potion { // ���� ���� ������

	// ������
	public DefensePotion(String name, String description, int value, int type) {
		super(name, description, value, type);
	}
	
	// ���� ��� �� ���� ����
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
		
	// ���1) Player Ŭ������ �ִ� �Լ��� ����Ͽ� �ش� �Լ����� ���� �����ϴ� ���
	// public boolean useHelmetItem(Player player) {
	//	player.useHelmetItem();
		
	//	return true;
	// }
}
