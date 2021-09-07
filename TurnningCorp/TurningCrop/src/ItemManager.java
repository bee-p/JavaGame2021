
import java.util.Random;

public class ItemManager { // Item 내용 초기화 
	Random random = new Random();
	
	private Item[] easyDevilLanguage = new Item[9]; // 쉽배악 배열 변수
	private Item[] electricLine = new Item[3]; // 회로선
	private Item remoteControl = new Item("리모컨", "어디에서 사용하는 물건일까?", 0); // 리모컨
	private Item secretFood = new Item("수상한 음식", "2층 식당에서 발견한 음식이다.", 5); // 수상한 음식
	private Item sFoodAnalyzerMenu = new Item("분석기 사용설명서", "어떤 물건의 사용서이다.", 0); // 분석기 사용설명서
	private Item[] diary = new Item[5]; // 일기장 단서
	private Item[] document = new Item[4]; // 문서 아이템
	
	// 생성자
	public ItemManager() {
		makeItem();
	}
	
	// getter 메소드
	public Item getEasyDevilLanguage(int index)		// 단일 쉽배악 가져오기
	{
		return easyDevilLanguage[index];
	}
	public Item[] getAllEasyDevilLanguage()			// 모든 쉽배악 가져오기
	{
		return easyDevilLanguage;
	}
	
	public Item getDiary(int index)				// 단일 일기장 가져오기
	{
		return diary[index];
	}
	public Item[] getAllDiary()					// 모든 일기장 가져오기
	{
		return diary;
	}
	
	
	// 아이템 생성
	private Item itemInitialize(int num) {
		Item item = new Item();
		switch (num) {
		case 0: item = new HealingPotion("hp 증가 포션", "플레이어의 체력을 5만큼 회복시킨다.", 5); break;
		case 1: item = new AttackPotion("공격력 증가 포션", "플레이어의 공격력을 5%만큼 증가시킨다.", 5); break;
		case 2: item = new DefensePotion("방어력 증가 포션", "플레이어의 방어력을 5%만큼 증가시킨다.", 5); break;
		case 3: item = new Item("수상한 포션", "공격력/방어력/HP 중 하나의 옵션만 선택하시오.", 5); break;
		case 4: item = remoteControl; break;
		case 5: item = secretFood; break;
		case 6: item = sFoodAnalyzerMenu; break;
		case 7: item = easyDevilLanguage[random.nextInt(9)]; break;
		case 8: item = electricLine[random.nextInt(3)]; break;
		case 9: item = diary[random.nextInt(5)]; break;
		}
		
		return item;
	}
	
	// 아이템 초기화, main함수에서 해당 메소드 사용하기!?
	public void makeItem() {
		// 쉽배악 초기화
		for (int idx = 0; idx < 9; idx++) {
			easyDevilLanguage[idx] = new Item("쉽게 배우는 악마어 제" + idx + 1 + "권", "몬스터의 언어가 적힌 책이다.", 0);
		}
		
		// 회로선 초기화
		for (int idx = 0; idx < 3; idx++) {
			electricLine[idx] = new Item(idx + 1 + "번 회로선", "여러개를 모아 어디에 연결해야할 것 같다.", 0);
		}
		
		// 일기장 단서 초기화
		diary[0] = new Item("8월 2일 일기", "밤마다 싸늘한 기운이 느껴진다. 밖에서 이상한 소리가 들렸으나, 무서워서 나가보지는 못했다.", 0);
		diary[1] = new Item("8월 9일 일기", "오늘은 용기내서 밖으로 업무실 밖으로 나갔다. 저 멀리에서 특이한 발걸음 소리가 들린다. 모두 퇴근해서 남은 회사원은 나뿐인데.. 대체 누구였을까?", 0);
		diary[2] = new Item("8월 16일 일기", "내가 야근하는 월요일날은 항상 저녁 9시가 되면 저멀리서 굉음이 났다가 끊긴다. 분명 소리가 났던 위치는 저번주에 내가 발걸음 소리를 들었던 방향이랑 일치한다. 확실한 것은 회사 내에서 어떤 일이 발생하고 있다는 것이다.", 0);
		diary[3] = new Item("8월 23일 일기", "기록기를 향해서 복도를 걷고 있었는데, 우리 팀과 자주 회의하던 다른 부서 사람을 만났다. 그 사람은 눈에 초점이 없었고, 꼭 어딘가에 홀려있는 듯 했다. 내가 인사를 했는데, 상대방은 나를 못알아 보는 눈치였다.", 0);
		diary[4] = new Item("8월 30일 일기", "8월에는 점심으로 항상 도시락을 싸갔는데, 오늘은 식당에서 점심을 해결했다. 정말 맛있었는데, 야근중인 지금도 생각나는 맛이다. 일기쓰는 와중에 저녁9시가 된 것 같다. 역시나 굉음이 들리는데, 갑자기 왜 손에 힘이 풀ㄹㄹ리ㅈㅣ.ㅇ.@ ", 0);
		
		// 문서 아이템 초기화
		for (int idx = 0; idx < 4; idx++) {
			document[idx] = new Item(idx + 1 + "번 문서", "문서 아이템이다.", 0);
		}
	}	
	
	// 다른 클래스에서 아이템 생성하는 get메소드
	public Item getItem(int num) {
		return itemInitialize(num);
	}
 }
