
public class Potion extends Item { // Potion 클래스
	
	// 생성자
	public Potion(String name, String description, int value) {
		super(name, description, value);
	}
	
	// 수상한 포션 사용 시 공격력, 방어력, hp 중 올리고 싶은 스탯을 선택하면 번호를 넘겨 받아서 조건에 맞게 처리
	public boolean useSecretkPotion(Player player, int num) {
		
		switch (num) {
		case 0: { // 공격력을 증가시키길 원한다면
			int attack = getValue();
			player.increaseAttack(attack); // Player클래스에 있는 함수에 %값 전달
		} break;
		case 1: { // 방어력을 증가시키길 원한다면 
			int defense = getValue();
			player.increaseDefense(defense);  // Player클래스에 있는 함수에 %값 전달
		} break;
		case 2: { // HP를 증가시키길 원한다면
			int hp = getValue();
			player.increaseHP(hp); // Player클래스에 있는 함수에 정수값 전달
		} break;
		default: return false;
		}
		
		return true;
	}
		
	// 아이템 정보 내보내는 메소드
	// 오버로딩
	public String itemInfo() {
		String info = "이름 : " + getName() + "\n";
		info += "설명 : " + getDescription() + "\n";
		info += "기능 : " + getValue() + "\n";
		
		return info;
	}
}
