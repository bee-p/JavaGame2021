

public class Player {

		//�ʵ�
		String name;
		String title;
		char rank;
		boolean[] skill;
		int hp;
		int attackPower;
		int defensivePower;
		int reputation;
		char[] rankArray = {'D', 'C', 'B', 'A', 'S'};
		String[] titleArray = {"����ѻ��", "�ȶ��ѻ��", "�밨���ΰ�", "�Ǹ����𸶻�", "����ִ»���"};
		int currentIndex = 0;
		ItemPair[] inventory;
		int inventoryLength;
		int inventoryIndex;
		
		private int posID;			// ���� ��ġ ���̵�
									// ex) 1�� 3���濡 �� �ִ� ���: 13
		
		//��ü
		SkillNPC npc = new SkillNPC();
		
		//�Լ�
		//������
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
		public void setPosID(int posID)		// �÷��̾� �̵��� Ȱ��
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
				if(inventory[i].item.getName() == item[itemIndex].getName()) //�κ��丮�� item�� �����ϴ� ���
				{
					inventory[i].count++;
				}
				
				else //�κ��丮�� item�� �������� �ʴ� ���
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
				return false; //�ش� �ε����� ��� ������ �׸��� ����.
			}
		}
		
		void useInventory(int inventoryNum)
		{
			inventory[inventoryNum].item.useItem(); //�����ʿ�**
		}
		
		public String toString(int inventoryIndex)
		{
			return inventory[inventoryIndex].item.getName() 
					+ inventory[inventoryIndex].item.getDescription();
		}
		
		void showInventory() //����ڿ��� �κ��丮 �����ֱ�
		{
			System.out.println("[�κ��丮]\n ==============================");
			System.out.println("�̸�\t ����\t");
			for(inventoryIndex = 0; inventoryIndex <= inventoryLength; inventoryIndex++)
			{
				toString(inventoryIndex);
			}
		}; 
	
		// �÷��̾ ���ݷ� ������ ���ø�, ���ݷ� ����
		public void increaseAttack(int attackPower) {
			this.attackPower += attackPower;
		}
		
		// �÷��̾ ���� ������ ���ø�, ���� ����
		public void increaseDefense(int defensivePower) {
			this.defensivePower += defensivePower;
		}
		
		// �÷��̾ hp ������ ���ø�, hp ����
		public void increaseHP(int hp) {
			this.hp += hp;
		}
}