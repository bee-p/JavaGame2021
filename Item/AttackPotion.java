
	// ��� ����� NPC���� �Լ� �ۼ�
public class AttackPotion extends Potion { // ���ݷ� ���� ������
	
	// GameUI���� ȣ�� -> ���� ����
	public AttackPotion(String name, String description, int value, int type) {
		super(name, description, value, type);
	}

	// ���� ��� �� ���ݷ� ����
	public boolean useAttackPotion(Player player) {
		int attack = getValue();
		player.increaseAttack(attack); // PlayerŬ������ �ִ� �Լ��� %�� ����
		
		return true;
	}
	
	// ������ ���� �������� �޼ҵ�
	// �����ε�
	public String itemInfo() {
		String info = "�̸� : " + getName() + "\n";
		info += "���� : " + getDescription() + "\n";
		info += "��� : " + getValue() + "% ��ŭ ���ݷ� ����\n";
		
		return info;
	}
}
