

public class Player {

		//필드
		String name;
		String title;
		char rank;
		boolean[] skill;
		int hp;
		int attackPower;
		int defensivePower;
		int reputation;
		char[] rankArray = {'D', 'C', 'B', 'A', 'S'};
		String[] titleArray = {"평범한사원", "똑똑한사원", "용감한인간", "악마왕퇴마사", "명망있는사장"};
		int currentIndex = 0;
		ItemPair[] inventory;
		int inventoryLength;
		int inventoryIndex;
		
		private int posID;			// 현재 위치 아이디
									// ex) 1층 3번방에 들어가 있는 경우: 13
		
		//객체
		SkillNPC npc = new SkillNPC();
		
		//함수
		//생성자
		Player()
		{
			
		}
		Player (String name, int hp, int attackPower, int defensivePower,
				int reputation, int currentIndex, int inventorySize, int posID) 
		{
			this.name = name;
			this.hp = hp;
			this.attackPower = attackPower;
			this.defensivePower = defensivePower;
			this.reputation = reputation;
			this.currentIndex = currentIndex;
			inventory = new ItemPair[inventorySize];
			this.posID = posID;
			
			this.title = titleArray[currentIndex];
			this.rank = rankArray[currentIndex];
		}
		
		//getter & setter
		public int getPosID()
		{
			return posID;
		}
		public void setPosID(int posID)		// 플레이어 이동시 활용
		{
			this.posID = posID;
		}
		
		String getName(){
			   return title + " " + name;
		}
		char getCurrentRank() 
	    {
	    	return rankArray[currentIndex];
	    }
	    int getReputation() 
	    {
	    	return reputation;
	    }
	    int getCurrentIndex()
	    {
	    	return currentIndex;
	    }
	    
	    
	    int upgradeReputation()
	    {
	    	currentIndex++;
	    	
	    	return currentIndex;
	    }; 	    
	    
		void beAttacked (int damage)
		{
			damage = damage/100 * defensivePower;
			hp -= damage; 
		}
		
		public void plusReputation(int num)
		{
			reputation += num;
		}
	
		public void saveInventory(Item[] item)
		{
			inventoryLength = inventory.length;
			int itemIndex = 0;
			for(int i=0; i<inventoryLength; i++)
			{
				if(inventory[i].item.getName() == item[itemIndex].getName()) //인벤토리에 item이 존재하는 경우
				{
					inventory[i].count++;
				}
				
				else //인벤토리에 item이 존재하지 않는 경우
					if (inventory[i]==null)
					{
						inventory[i].item = item[itemIndex];
						inventory[i].count = 1;
					}						
				
				itemIndex++;
				if(item[itemIndex] == null)
				{
					break;
				}
			}
			
		}
		
		
		boolean deleteInventory(int inventoryNum)
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
		
		void useInventory(int inventoryNum)
		{
			inventory[inventoryNum].item.useItem(); //수정필요**
		}
		
		public String toString(int inventoryIndex)
		{
			return inventory[inventoryIndex].item.getName() 
					+ inventory[inventoryIndex].item.getDescription();
		}
		
		void showInventory() //사용자에게 인벤토리 보여주기
		{
			System.out.println("[인벤토리]\n ==============================");
			System.out.println("이름\t 설명\t");
			for(inventoryIndex = 0; inventoryIndex <= inventoryLength; inventoryIndex++)
			{
				toString(inventoryIndex);
			}
		}; 
	
		// 플레이어가 공격력 포션을 마시면, 공격력 증가
		public void increaseAttack(int attackPower) {
			this.attackPower += attackPower;
		}
		
		// 플레이어가 방어력 포션을 마시면, 방어력 증가
		public void increaseDefense(int defensivePower) {
			this.defensivePower += defensivePower;
		}
		
		// 플레이어가 hp 포션을 마시면, hp 증가
		public void increaseHP(int hp) {
			this.hp += hp;
		}
}