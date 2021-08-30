import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {

		//����
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
		boolean isBattle; //��Ʋ ������ ����
		
		private int posID;			// ���� ��ġ ���̵�
									// ex) 1�� 3���濡 �� �ִ� ���: 13
		
		//��ü
		SkillNPC npc = new SkillNPC();
		Scanner sc = new Scanner(System.in);
		
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
		public void setPosID(int posID) // �÷��̾� �̵��� Ȱ��
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
	    
	    //�Լ�
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
	
		public void saveInventory(Item item) //������ �������� 1���� ���
		{
			
			if(searchItem(item.getName())) //������ �������� �κ��丮�� �����ϴ� ���
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
			else  //�κ��丮�� ���� �������� ���
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
		
		public void saveInventory(Item[] items) //������ �������� 2�� �̻��� ���
		{
			int itemsLength = items.length;
			for(int i=0; i < itemsLength; i++)
			{
				Item item = items[i];
			
				if(searchItem(item.getName())) //������ �������� �κ��丮�� �����ϴ� ���
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
				else  //�κ��丮�� ���� �������� ���
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
		
		void showInventory() //����ڿ��� �κ��丮 �����ֱ�
		{
			System.out.println("[�κ��丮]\n ==============================");
			System.out.println("�̸�\t ����\t");
			
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
			else if (item.getName().equals("SecretPotion")) //��Ʋ�߿��� ��� ����
			{
				if(isBattle == true)
				{
					System.out.println("� ������ �ø��ðڽ��ϱ�? ");
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
						default : System.out.println("1~3�߿� �Է��ϼ���");
						}
					}
					catch (InputMismatchException ime)
					{
						System.out.println("������ �Է��ϼ���");
					}

				}
				else
					System.out.println("��Ʋ �߿��� ����� ������ ������ �Դϴ�.");
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
		
		public boolean searchItem(String itemName) //Ư�� �������� �κ��丮�� �����ϴ��� Ȯ��
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
