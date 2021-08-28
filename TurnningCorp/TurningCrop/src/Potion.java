
public class Potion extends Item { // Potion Ŭ����
	
	// ������
	public Potion(String name, String description, int value) {
		super(name, description, value);
	}
	
	// ������ ���� ��� �� ���ݷ�, ����, hp �� �ø��� ���� ������ �����ϸ� ��ȣ�� �Ѱ� �޾Ƽ� ���ǿ� �°� ó��
	public boolean useSecretkPotion(Player player, int num) {
		
		switch (num) {
		case 0: { // ���ݷ��� ������Ű�� ���Ѵٸ�
			int attack = getValue();
			player.increaseAttack(attack); // PlayerŬ������ �ִ� �Լ��� %�� ����
		} break;
		case 1: { // ������ ������Ű�� ���Ѵٸ� 
			int defense = getValue();
			player.increaseDefense(defense);  // PlayerŬ������ �ִ� �Լ��� %�� ����
		} break;
		case 2: { // HP�� ������Ű�� ���Ѵٸ�
			int hp = getValue();
			player.increaseHP(hp); // PlayerŬ������ �ִ� �Լ��� ������ ����
		} break;
		default: return false;
		}
		
		return true;
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
