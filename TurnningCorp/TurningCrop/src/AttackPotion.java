
public class AttackPotion extends Potion { // 공격력 증가 아이템
	
	// 생성자
	public AttackPotion(String name, String description, int value) {
		super(name, description, value);
	}

	// 아이템 정보 내보내는 메소드
	// 오버로딩
	public String itemInfo() {
		String info = "이름 : " + getName() + "\n";
		info += "설명 : " + getDescription() + "\n";
		info += "기능 : " + getValue() + "% 만큼 공격력 증가\n";
		
		return info;
	}
}
