
	// 얻는 방법은 NPC에서 함수 작성
public class AttackPotion extends Potion { // 공격력 증가 아이템
	
	// GameUI에서 호출 -> 포션 생성
	public AttackPotion(String name, String description, int value, int type) {
		super(name, description, value, type);
	}

	// 포션 사용 시 공격력 증가
	public boolean useAttackPotion(Player player) {
		int attack = getValue();
		player.increaseAttack(attack); // Player클래스에 있는 함수에 %값 전달
		
		return true;
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
