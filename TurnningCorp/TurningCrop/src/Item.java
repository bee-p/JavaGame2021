
public class Item { // 모든 아이템에 대한 기본 아이템 클래스
	private String name; // 아이템 종류
	private String description; // 아이템 설명
	private int value; // 능력치 변경을 위한 수치값
	
	// 기본 생성자
	public Item() {
		
	}
	
	// 생성자
	public Item(String name, String description, int value) {
		this.name = name;
		this.description = description;
		this.value = value;
	}
	
	// getter & setter 메소드
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getValue() {
		return value;
	}
	
	// 포션을 제외한 일기장, 쉽배악 등의 아이템은 value값으로 획득 여부를 판별
	// 0 : 미획득, 1 : 획득
	public void setValue(int value) {
		this.value = value;
	}
	
	// 아이템 정보 내보내는 메소드
	public String itemInfo() {
		String info = "이름 : " + name + "\n";
		info += "설명 : " + description + "\n";
		
		return info;
	}
}
