
public class HealingPotion extends Potion { // 회복 아이템 클래스

	// 생성자
	public HealingPotion(String name, String description, int value) {
		super(name, description, value);
	}
	
	// 포션 사용 시 hp 증가, true값 리턴
	public boolean useHealingPotion(Player player) {
		int hp = getValue();
		player.increaseHP(hp); // Player클래스에 있는 함수에 정수값 전달
		
		return true;
	}
	
	// 아이템 정보 내보내는 메소드
	// 오버로딩
	public String itemInfo() {
		String info = "이름 : " + getName() + "\n";
		info += "설명 : " + getDescription() + "\n";
		info += "기능 : " + getValue() + "% 만큼 체력 증가\n";
		
		return info;
	}
}
