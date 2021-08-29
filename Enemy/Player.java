

public class Player {

	//필드
	boolean[] skill; //스킬에 대한 변수
	int hp; //생명(하트개수) = 공격력(enemy) - 방어력(player)
	int attackPower; //공격력
	int defensivePower; //방어력
	int reputation; //평판도 - 일정 평판도가 충족될 경우 
	                //진급 NPC에게 진급 가능 여부를 확인한 후 진급 가능
	char[] rank = {'D', 'C', 'B', 'A', 'S'}; //직급(D~S등급) - 직급에 따라 엔딩(노멀/해피/트루)이 바뀜.
	char currentRank;
	int currentRankIndex; //현재 직급의 인덱스
	Item[] inventory = new Item[50]; //인벤토리 - 아이템을 저장하는 곳
	int inventoryLength; //인벤토리가 길이
	
	//함수
	
	//getter & setter
    char getCurrentRank() 
    {
    	return rank[currentRankIndex];
    } //랭크 가져오기 - 배틀 매니지먼트 클래스에서 도망가기 스킬에서 사용(확률에 따라...)
      //           - 게임 매니지먼트 클래스에서 사용자가 랭크를 확인할 때 사용
    int getReputation() 
    {
    	return reputation;
    } //평판도 가져오기 - 게임매니지먼트 클래스에서 사용
    
    void upgradeReputation() //평판도에 따라 직급 올리기
    {
    	if(reputation < 100)
    	{
    		currentRankIndex = 0;
    	}
    	else if(reputation < 200)
    	{
    		currentRankIndex = 1;
    	}
    	else if(reputation < 300)
    	{
    		currentRankIndex = 2;
    	}
    	else if(reputation < 400)
    	{
    		currentRankIndex = 3;
    	}
    	else if(reputation >= 400)
    	{
    		currentRankIndex = 4;
    	}
    	
    	currentRank = rank[currentRankIndex];
    };
    
	boolean attacked(int attackedPower) //플레이어가 공격 받는 함수 - 배틀매니지먼트 클래스에서 사용
	{
		//공격'받는'걸로 한 이유가 공격'하는'걸로 하면 Player클래스 내에서 Enemy값을 변경하는 것이 되기 때문인가?
		return false;
	}
	
	void saveInventory(Item item) //저장할 아이템을 매개변수로 넘겨서 비어있는 인벤토리 배열에 아이템 저장
	{
		inventoryLength = inventory.length;
		for(int i=0; i<inventoryLength; i++)
		{
			if(inventory[i]==null) //인벤토리가 비어있는 부분
			{
				inventory[i] = item;
				return ;
			}
		}
	}
	
	void deleteInventory(int inventoryNum) //인벤토리에서 아이템 버리기 - 인벤토리 자동으로 열기 -> 버릴 아이템 선택 -> 삭제
	{
		inventory[inventoryNum] = null;
	}
	
	void useInventory(int inventoryNum) //인벤토리에 있는 아이템 사용 - 인벤토리 자동으로 열기 -> 사용할 아이템 선택 -> 사용
	{
		//Item itemToUse = inventory[inventoryNum];
		//itemToUse.use~~ //아이템 클래스 내에 사용하는 함수 호출
	}
	
	void showInventory() //사용자에게 인벤토리 보여주기
	{
		//출력을 게임매니지먼트..?
	}; 
	
}