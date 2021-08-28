
public class Potion extends Item { // Potion 클래스
	
	// 생성자
	public Potion(String name, String description, int value) {
		super(name, description, value);
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
