
public class HealingPotion extends Potion { // ȸ�� ������ Ŭ����

	// ������
	public HealingPotion(String name, String description, int value) {
		super(name, description, value);
	}
	
	// ���� ��� �� hp ����, true�� ����
	public boolean useHealingPotion(Player player) {
		int hp = getValue();
		player.increaseHP(hp); // PlayerŬ������ �ִ� �Լ��� ������ ����
		
		return true;
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
