
public class Player {

	//�ʵ�
	String name; //�÷��̾� �̸�
	char rank; //��ũ
	boolean[] skill; //��ų�� ���� ����
	int hp; //����(��Ʈ����) = ���ݷ�(enemy) - ����(player)
	int attackPower; //���ݷ�
	int defensivePower; //����
	int reputation; //���ǵ� - ���� ���ǵ��� ������ ��� 
	                //���� NPC���� ���� ���� ���θ� Ȯ���� �� ���� ����
	char[] rankArray = {'D', 'C', 'B', 'A', 'S'}; //����(D~S���) - ���޿� ���� ����(���/����/Ʈ��)�� �ٲ�.'
	String[] nameArray = {"����ѻ��", "�ȶ��ѻ��", "�밨���ΰ�", "�Ǹ����𸶻�", "����ִ»���"};
	int currentIndex = 0; //���� ������ �ε���
	Item[] inventory; //�κ��丮 - �������� �����ϴ� ��
	int inventoryLength; //�κ��丮�� ����
	int inventoryIndex; //�κ��丮�ε���
	
	//��ü
	SkillNPC npc = new SkillNPC();
	
	//�Լ�
	Player () //������ => **GameManager���� �ʵ� ���� �ٲٸ� �����ڿ� �� �޾ƿͼ� �����ϴ� ������� �����ϱ� => ?
	{
		currentIndex = 0;
		hp = 5;
		attackPower = 10;
		defensivePower = 10;
		reputation = 0;
		name = nameArray[currentIndex];
		this.name = nameArray[0] + this.name;
		rank = rankArray[currentIndex];
	}
	
	Player (String name) {
		this.name = name;
		
		// print(name + " " + nameArray[0]); //UI���� �̸� ���� �Լ�
	}
	
	//getter & setter
	String getName(){
		   return nameArray[currentIndex] + " " + name;
	}
	
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
    	currentIndex++;
    	
    	return currentIndex;
    }; //���ο��� �޾Ƽ� ���ڰ� ���������� ����� Īȣ�� ��ũ �����ֱ�,
       //���ڰ� �״�θ� "���ǵ��� �����Ͽ� ������ �ø� �� �����ϴ�"���� ����.
    
	void beAttacked (int damage)
	{
		damage = damage/100 * defensivePower;
		hp -= damage; //hp�� 5??????
	}
	
	// **ItemNPCŬ������ �ִ� ����� ����
	public void plusReputation(int num)
	{
		
	}
	
	// **ItemNpcŬ������ �ִ� ������ ���� �ش� �Լ� �������̵� �ϱ�!
	boolean saveInventory(Item item)
	{
		int postInventoryLength = 0;
		inventoryLength = inventory.length;
		for(int i=0; i<inventoryLength; i++)
		{
			if(inventory[i]==null) //�κ��丮�� ����ִ� �κ�
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
			return false; //�κ��丮�� ������ ����.
	}
	
	boolean deleteInventory(int inventoryNum) //�κ��丮���� ������ ������ - �κ��丮 �ڵ����� ���� -> ���� ������ ���� -> ����
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
	
	void useInventory(int inventoryNum) //�κ��丮�� �ִ� ������ ��� - �κ��丮 �ڵ����� ���� -> ����� ������ ���� -> ���
	{
		inventory[inventoryNum].useItem(); //�����ʿ�**
	}
	
	public String toString(int inventoryIndex)
	{
		return null;
		//return inventory[inventoryIndex].getName() + inventory[inventoryIndex].num; //**������ ���� ó���� player�κ��丮�� ���� �ٲ�Ƿ� pairŬ������ ó��
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
	
	// �߰��ؾ��� ����
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
	
	// Pair Ŭ���� �߰�! => ���� �ڹ����Ϸ� �����ϱ�
	class Pair {
	}
	
}