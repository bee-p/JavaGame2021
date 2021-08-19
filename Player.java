package TurningCorp;

public class Player {

	//필드
	String name; //플레이어 이름
	char rank; //랭크
	boolean[] skill; //스킬에 대한 변수
	int hp; //생명(하트개수) = 공격력(enemy) - 방어력(player)
	int attackPower; //공격력
	int defensivePower; //방어력
	int reputation; //평판도 - 일정 평판도가 충족될 경우 
	                //진급 NPC에게 진급 가능 여부를 확인한 후 진급 가능
	char[] rankArray = {'D', 'C', 'B', 'A', 'S'}; //직급(D~S등급) - 직급에 따라 엔딩(노멀/해피/트루)이 바뀜.'
	String[] nameArray = {"평범한사원", "똑똑한사원", "용감한인간", "악마왕퇴마사", "명망있는사장"};
	int currentIndex = 0; //현재 직급의 인덱스
	Item[] inventory; //인벤토리 - 아이템을 저장하는 곳
	int inventoryLength; //인벤토리가 길이
	int inventoryIndex; //인벤토리인덱스
	
	//객체
	NPC npc = new NPC();
	Item item = new Item();
	
	//함수
	Player () //생성자
	{
		currentIndex = 0;
		hp = 5;
		attackPower = 10;
		defensivePower = 10;
		reputation = 0;
		name = nameArray[currentIndex];
		rank = rankArray[currentIndex];
	}
	
	//getter & setter
    char getCurrentRank() 
    {
    	return rankArray[currentIndex];
    }
    
    int getReputation() 
    {
    	return reputation;
    }
    
    int upgradeReputation()
    {
    	if(npc.isUpgradePossible())
    	{
    		currentIndex++;
    		return currentIndex;
    	}
    	else
    	{
    		return currentIndex;
    	}
    }; //메인에서 받아서 숫자가 증가했으면 변경된 칭호와 랭크 보여주기,
       //숫자가 그대로면 "평판도가 부족하여 직급을 올릴 수 없습니다"문자 띄우기.
    
	void beAttacked (int damage)
	{
		damage = damage/100 * defensivePower;
		hp -= damage; //hp가 5??????
	}
	
	boolean saveInventory(Item item)
	{
		int postInventoryLength = 0;
		inventoryLength = inventory.length;
		for(int i=0; i<inventoryLength; i++)
		{
			if(inventory[i]==null) //인벤토리가 비어있는 부분
			{
				inventory[i] = item;
				postInventoryLength = inventory.length;
				break;
			}
		}
		
		if(inventoryLength < postInventoryLength)
		{
			return true;
		}
		else
			return false; //인벤토리에 공간이 없음.
	}
	
	boolean deleteInventory(int inventoryNum) //인벤토리에서 아이템 버리기 - 인벤토리 자동으로 열기 -> 버릴 아이템 선택 -> 삭제
	{
		if(inventory[inventoryNum] != null)
		{
			inventory[inventoryNum] = null;
			return true;
		}
		else
		{
			return false; //해당 인덱스가 비어 삭제할 항목이 없음.
		}
	}
	
	void useInventory(int inventoryNum) //인벤토리에 있는 아이템 사용 - 인벤토리 자동으로 열기 -> 사용할 아이템 선택 -> 사용
	{
		item.useItem(inventory[inventoryNum]); //수정필요
	}
	
	public String toString(int inventoryIndex)
	{
		return inventory[inventoryIndex].name + inventory.[inventoryIndex].num;
	}
	
	void showInventory() //사용자에게 인벤토리 보여주기
	{
		System.out.println("[인벤토리]\n ==============================");
		System.out.println("이름\t 개수\t");
		for(inventoryIndex = 0; inventoryIndex <= inventoryLength; inventoryIndex++)
		{
			toString(inventoryIndex);
		}
	}; 
	
}
