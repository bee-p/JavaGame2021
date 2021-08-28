import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {

		//핃드
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
		boolean isBattle; //배틀 중인지 여부
		
		private int posID;			// 현재 위치 아이디
									// ex) 1층 3번방에 들어가 있는 경우: 13
		
		//객체
		SkillNPC npc = new SkillNPC();
		Scanner sc = new Scanner(System.in);
		
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
			inventoryLength = inventory.length;
			this.posID = posID;
			
			this.title = titleArray[currentIndex];
			this.rank = rankArray[currentIndex];
		}
		
		//getter & setter
		public int getPosID()
		{
			return posID;
		}
		public void setPosID(int posID) // 플레이어 이동시 활용
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
	    
	    //함수
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
	
		public void saveInventory(Item item) //저장할 아이템이 1개인 경우
		{
			
			if(searchItem(item.getName())) //저장할 아이템이 인벤토리에 존재하는 경우
			{
				for(int i=0; i<inventoryLength; i++)
				{
					if(inventory[i].item.getName().equals(item.getName()))
					{
						inventory[i].count++;
						break;
					}
				}
			}
			else  //인벤토리에 없는 아이템인 경우
			{
				for(int i=0; i<inventoryLength; i++)
				{
					if(inventory[i] == null)
					{
						inventory[i].item = item;
						inventory[i].count = 1;
						break;
					}
					else
					{
						continue;
					}
				}
			}
			
		}
		
		public void saveInventory(Item item1, Item item2) //저장할 아이템이 2개인 경우 (2개는 다른 아이템)
		{
			Item item = item1;
			int saveCount = 0;
			
			while(saveCount!=2)
			{
				if(searchItem(item.getName())) //저장할 아이템이 인벤토리에 존재하는 경우
				{
					for(int i=0; i<inventoryLength; i++)
					{
						if(inventory[i].item.getName().equals(item.getName()))
						{
							inventory[i].count++;
							break;
						}
					}
				}
				else  //인벤토리에 없는 아이템인 경우
				{
					for(int i=0; i<inventoryLength; i++)
					{
						if(inventory[i] == null)
						{
							inventory[i].item = item;
							inventory[i].count = 1;
							break;
						}
						else
						{
							continue;
						}
					}
				}
				item = item2;
				saveCount++;
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
				return false;
			}
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
	
		void useInventory(int inventoryNum)
		{
			Item itemToUse = inventory[inventoryNum].item;
			useItem(itemToUse);
		}

		public void useItem(Item item)
		{
			if (item.getName().equals("AttackPotion"))
			{
				increaseAttackPower(item.getValue());
			}
			else if (item.getName().equals("DefensePotion"))
			{
				increaseDefensivePower(item.getValue());
			}
			else if (item.getName().equals("HealingPotion"))
			{
				increaseHpPower(item.getValue());
			}
			else if (item.getName().equals("SecretPotion")) //배틀중에만 사용 가능
			{
				if(isBattle == true)
				{
					System.out.println("어떤 스탯을 올리시겠습니까? ");
					System.out.println("1. AttackPower");
					System.out.println("2. DefensivePower");
					System.out.println("3. Hp");
					try
					{
						int selectNum = sc.nextInt();
						sc.next();

						switch(selectNum)
						{
						case 1: increaseAttackPower(item.getValue());
						case 2: increaseDefensivePower(item.getValue());
						case 3: increaseHpPower(item.getValue());
						default : System.out.println("1~3중에 입력하세요");
						}
					}
					catch (InputMismatchException ime)
					{
						System.out.println("정수를 입력하세요");
					}

				}
				else
					System.out.println("배틀 중에만 사용이 가능한 아이템 입니다.");
			}
		}
		
		public void increaseAttackPower(int attackPower)
		{
			this.attackPower += attackPower;
		}; 
		
		public void increaseDefensivePower(int defensivePower)
		{
			this.defensivePower += defensivePower;
		}; 
		
		public void increaseHpPower(int hp)
		{
			this.hp += hp;
		}; 
		
		public boolean searchItem(String itemName) //특정 아이템이 인벤토리에 존재하는지 확인
		{
			for(int i=0; i<inventoryLength; i++)
			{
				if(inventory[i].item.getName().equals(itemName))
				{
					return true;
				}
				else
					continue;
			}
			return false;
		}
		
		
		
}
