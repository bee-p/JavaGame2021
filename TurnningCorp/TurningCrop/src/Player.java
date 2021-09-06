import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {

		//핃드
		private String name;
		private String title;
		private char rank;
		private boolean[] skill;
		private int hp;
		private int attackPower;
		private int defensivePower;
		private int reputation;
		private char[] rankArray = {'D', 'C', 'B', 'A', 'S'};
		private String[] titleArray = {"평범한사원", "똑똑한사원", "용감한인간", "악마왕퇴마사", "명망있는사장"};
		private int currentIndex = 0;
		private ItemPair[] inventory; //인벤토리-아이템저장
		private Quest[] questArray; //퀘스트배열-퀘스트저장
		private int questArrayLength;
		private int inventoryLength;
		private int inventoryIndex;
		private boolean isBattle; //배틀 중인지 여부
		
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
			this.questArray = new Quest[15];
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
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public char getRank() {
			return rank;
		}
		public void setRank(char rank) {
			this.rank = rank;
		}
		public boolean[] getSkill() {
			return skill;
		}
		public void setSkill(boolean[] skill) {
			this.skill = skill;
		}
		public char[] getRankArray() {
			return rankArray;
		}
		public void setRankArray(char[] rankArray) {
			this.rankArray = rankArray;
		}
		public String[] getTitleArray() {
			return titleArray;
		}
		public void setTitleArray(String[] titleArray) {
			this.titleArray = titleArray;
		}
		public ItemPair[] getInventory() {
			return inventory;
		}
		public void setInventory(ItemPair[] inventory) {
			this.inventory = inventory;
		}
		public Quest[] getQuestArray() {
			return questArray;
		}
		public void setQuestArray(Quest[] questArray) {
			this.questArray = questArray;
		}
		public int getQuestArrayLength() {
			return questArrayLength;
		}
		public void setQuestArrayLength(int questArrayLength) {
			this.questArrayLength = questArrayLength;
		}
		public int getInventoryLength() {
			return inventoryLength;
		}
		public void setInventoryLength(int inventoryLength) {
			this.inventoryLength = inventoryLength;
		}
		public int getInventoryIndex() {
			return inventoryIndex;
		}
		public void setInventoryIndex(int inventoryIndex) {
			this.inventoryIndex = inventoryIndex;
		}
		public boolean getIsBattle() {
			return isBattle;
		}
		public void setIsBattle(boolean isBattle) {
			this.isBattle = isBattle;
		}
		public String getName()
		{
			return name;
		}
		public String getFullName()
		{
			return title + " " + name;
		}
		public void setName(String name) 
		{
			this.name = name;
		}
		int getAttackPower()
	    {
	    	return attackPower;
	    }
		public void setAttackPower(int attackPower) 
		{
			this.attackPower = attackPower;
		}
		 int getDefensivePower()
		{
		    	return defensivePower;
	    }
		public void setDefensivePower(int defensivePower) 
		{
			this.defensivePower = defensivePower;
		}
		public int getHp() 
		{
			return hp;
		}
		public void setHp(int hp) 
		{
			this.hp = hp;
		}
		int getReputation() 
	    {
	    	return reputation;
	    }
		public void setReputation(int reputation)
		{
			this.reputation = reputation;
		}
		char getCurrentRank() 
	    {
	    	return rankArray[currentIndex];
	    }
	    int getCurrentIndex()
	    {
	    	return currentIndex;
	    }
	    void setCurrentIndex(int currentIndex)
	    {
	    	this.currentIndex = currentIndex;
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
		
		public void saveInventory(Item[] items) //저장할 아이템이 2개 이상인 경우
		{
			int itemsLength = items.length;
			for(int i=0; i < itemsLength; i++)
			{
				Item item = items[i];
			
				if(searchItem(item.getName())) //저장할 아이템이 인벤토리에 존재하는 경우
				{
					for(int j=0; j<inventoryLength; j++)
					{
						if(inventory[j].item.getName().equals(item.getName()))
						{
							inventory[j].count++;
							break;
						}
					}
				}
				else  //인벤토리에 없는 아이템인 경우
				{
					for(int j=0; j<inventoryLength; j++)
					{
						if(inventory[j] == null)
						{
							inventory[j].item = item;
							inventory[j].count = 1;
							break;
						}
						else
						{
							continue;
						}
					}
				}
			
			}
			
		}
		
		public boolean deleteInventory(int inventoryNum) //플레이어가 인벤토리를 열어 아이템을 삭제할 때(선택)
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
		
		public void deleteInventory(Item item) //사용 후 자동으로 아이템이 삭제될 때(자동)
		{
			for(int i=0; i<inventoryLength; i++)
			{
				if(item.getName().equals(inventory[i].item.getName()))
				{
					inventory[i]=null;
					break;
				}
			}
		}
		
		public void showInventory() //사용자에게 인벤토리 보여주기
		{
			System.out.println("[인벤토리]\n ==============================");
			System.out.println("이름\t 개수\t 설명\t");
			
			for(int i=0; i<inventoryLength; i++)
			{
				if(inventory[i]==null)
				{
					continue;
				}
				else
				{
					System.out.println(inventory[i].item.getName() + "\t" + inventory[i].count 
							+ "\t" + inventory[i].item.getDescription());
				}
			}
		};
	
		public void useInventory(int inventoryNum)
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
					System.out.println("어떤 스탯을 올리겠는가? ");
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
						default : System.out.println("1~3중에 입력해야한다.");
						}
					}
					catch (InputMismatchException ime)
					{
						System.out.println("정수를 입력해야한다.");
					}

				}
				else
					System.out.println("배틀 중에만 사용이 가능한 아이템이다.");
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
		
		public void printQuestList() // 현재 플레이어가 진행 중인 퀘스트 목록 출력
		{
			int questArrayLength = questArray.length;
			for(int i=0; i<questArrayLength; i++)
			{
				if(questArray[i].getCompletion()==true)
				{
					continue;
				}
				else
				{
					System.out.println("이름\t 설명");
					System.out.println(questArray[i].getQuestName() 
							+ "\t" +questArray[i].getDescription());
				}
			}
		}
		
		
}
