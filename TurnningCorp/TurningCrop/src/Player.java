import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {

		private static final Readable InputStream = null;
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

		/*
		public void saveInventory(Item item) //������ �������� 1���� ���
		{
			inventoryLength = inventory.length;
			for(int i=0; i<inventoryLength; i++)
			{
				
			}

		}
		
		public void saveInventory(Item[] item) //������ �������� 2���� ���
		{
			inventoryLength = inventory.length;
			int itemIndex = 0;
			for(int i=0; i<inventoryLength; i++)
			{
				if(inventory[i].item.getName().equals(item[itemIndex].getName())) //�κ��丮�� item�� �����ϴ� ���
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
		*/
		
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
		
		public String toString(int inventoryIndex)
		{
			return inventory[inventoryIndex].item.getName() 
					+ inventory[inventoryIndex].item.getDescription();
		}
		
		void showInventory()
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
			if (item.name.equals("AttackPotion"))
			{
				increaseAttackPower(item.getValue());
			}
			else if (item.name.equals("DefensePotion"))
			{
				increaseDefensePower(item.getValue());
			}
			else if (item.name.equals("HealingPotion"))
			{
				increaseHpPower(item.getValue());
			}
			else if (item.name.equals("SecretPotion")) //��Ʋ�߿��� ��� ����
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
						case 2: increaseDefensePower(item.getValue());
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
		}
		
		public void increaseDefensePower(int defensivePower)
		{
			this.defensivePower += defensivePower;
		}
		
		public void increaseHpPower(int hp)
		{
			this.hp += hp;
		}
		
		public boolean searchItem(String itemName) //Ư�� �������� �κ��丮�� �����ϴ��� Ȯ��
		{
			for(int i=0; i<inventoryLength; i++)
			{
				if(inventory[i].item.name.equals(itemName))
				{
					return true;
				}
				else
					continue;
			}
			return false;
		}
		
		
}