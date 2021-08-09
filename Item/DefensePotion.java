
	// 얻는 방법은 NPC에서 함수 작성
public class DefensePotion extends Potion { // 방어력 증가 아이템

	// 생성자
	public DefensePotion(String name, String description, int value, int type) {
		super(name, description, value, type);
	}
	
	// 포션 사용 시 방어력 증가
	public boolean useDefensePotion(Player player) {
		int defense = getValue();
		player.increaseDefense(defense);  // Player클래스에 있는 함수에 %값 전달
		
		return true;
	}
	
	// 아이템 정보 내보내는 메소드
	// 오버로딩
	public String itemInfo() {
		String info = "이름 : " + getName() + "\n";
		info += "설명 : " + getDescription() + "\n";
		info += "기능 : " + getValue() + "만큼 방어력 증가\n";
		
		return info;
	}
		
	// 방법1) Player 클래스에 있는 함수를 사용하여 해당 함수에서 값을 조작하는 방법
	// public boolean useHelmetItem(Player player) {
	//	player.useHelmetItem();
		
	//	return true;
	// }
}
