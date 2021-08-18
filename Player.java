package UML;

public class Player {

	//�ʵ�
	boolean[] skill; //��ų�� ���� ����
	int hp; //����(��Ʈ����) = ���ݷ�(enemy) - ����(player)
	int attackPower; //���ݷ�
	int defensivePower; //����
	int reputation; //���ǵ� - ���� ���ǵ��� ������ ��� 
	                //���� NPC���� ���� ���� ���θ� Ȯ���� �� ���� ����
	char[] rank = {'D', 'C', 'B', 'A', 'S'}; //����(D~S���) - ���޿� ���� ����(���/����/Ʈ��)�� �ٲ�.
	char currentRank;
	int currentRankIndex; //���� ������ �ε���
	Item[50] inventory; //�κ��丮 - �������� �����ϴ� ��
	int inventoryLength; //�κ��丮�� ����
	
	//�Լ�
	
	//getter & setter
    char getCurrentRank() 
    {
    	return rank[currentRankIndex];
    } //��ũ �������� - ��Ʋ �Ŵ�����Ʈ Ŭ�������� �������� ��ų���� ���(Ȯ���� ����...)
      //           - ���� �Ŵ�����Ʈ Ŭ�������� ����ڰ� ��ũ�� Ȯ���� �� ���
    int getReputation() 
    {
    	return reputation;
    } //���ǵ� �������� - ���ӸŴ�����Ʈ Ŭ�������� ���
    
    void upgradeReputation() //���ǵ��� ���� ���� �ø���
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
    
	boolean attacked(int attackedPower) //�÷��̾ ���� �޴� �Լ� - ��Ʋ�Ŵ�����Ʈ Ŭ�������� ���
	{
		//����'�޴�'�ɷ� �� ������ ����'�ϴ�'�ɷ� �ϸ� PlayerŬ���� ������ Enemy���� �����ϴ� ���� �Ǳ� �����ΰ�?
	}
	
	void saveInventory(Item item) //������ �������� �Ű������� �Ѱܼ� ����ִ� �κ��丮 �迭�� ������ ����
	{
		inventoryLength = inventory.length;
		for(int i=0; i<inventoryLength; i++)
		{
			if(Item[i]==null) //�κ��丮�� ����ִ� �κ�
			{
				Item[i] = item;
				return 0;
			}
		}
	}
	
	void deleteInventory(int inventoryNum) //�κ��丮���� ������ ������ - �κ��丮 �ڵ����� ���� -> ���� ������ ���� -> ����
	{
		Item[inventoryNum] = null;
	}
	
	void useInventory(int inventoryNum) //�κ��丮�� �ִ� ������ ��� - �κ��丮 �ڵ����� ���� -> ����� ������ ���� -> ���
	{
		Item itemToUse = inventory[inventoryNum];
		itemToUse.use~~ //������ Ŭ���� ���� ����ϴ� �Լ� ȣ��
	}
	
	void showInventory() //����ڿ��� �κ��丮 �����ֱ�
	{
		//����� ���ӸŴ�����Ʈ..?
	}; 
	
}
