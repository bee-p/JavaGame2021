
public class AttackPotion extends Potion { // ���ݷ� ���� ������
	
	// ������
	public AttackPotion(String name, String description, int value) {
		super(name, description, value);
	}

	// ���� ��� �� ���ݷ� ����, true�� ����
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
