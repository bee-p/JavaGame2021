// ������������� �������� ��ȯ�ϴ� NPC

public class ItemNPC extends NPC {
	// ���� NPC���� �Ǻ�
	// ex) true�� ���� NPC
	private boolean isMonster;
	
	// ������
	ItemNPC()
	{
		super();
	}
	
	// ------ getter, setter �޼ҵ� ------ \\
	
	public boolean getIsMonster()
	{
		return isMonster;
	}
	public void setIsMonster(boolean isMonster)
	{
		this.isMonster = isMonster;
	}
	
	
	// ------------�� �� �޼ҵ�------------ \\
	
	// ����Ʈ Ȱ��ȭ ���� �Ǵ� (��ũ��Ʈ �к�)
	// ����Ʈ ��ũ��Ʈ�� �ε��� �� ��ȯ
	public int playQuest(Player player)
	{
		while (true)
		{
			// �ش� ����Ʈ�� �Ϸ���� �ʾҴٸ�
			if (super.getQuest(getQuestCount()).getCompletion() == false)
			{
				// �Ϸ� ���� �޼���
				if (super.getQuest(getQuestCount()).getCondition() == true)
				{
					// ����Ʈ ��ũ��Ʈ �ε��� 1 ����
					super.setQuestScriptCount(getQuestScriptCount() + 1);
					
					// �ش� ����Ʈ �Ϸ�� ����
					super.getQuest(getQuestCount()).setCompletion(true);
					
					// �÷��̾�� ���� ������ ���� => **npc/quest ������ �̾߱��ؼ� �ٲٱ�!
					player.saveInventory(super.getQuest(getQuestCount()).getReward());
					
					// ���� ���� NPC�� ���
					if (isMonster == true)
					{
						// �÷��̾�� ���ǵ� ����(����)
						player.plusReputation(100);
					}
				}
				// �Ϸ� ���� �̴޼��ô� ���� �����ϴ� �۾��� �����Ƿ�
				// ������� ����
				
				// ����Ʈ ��ũ��Ʈ�� �ε��� �� ��ȯ
				return super.getQuestScriptCount();
			}
			// �ش� ����Ʈ�� �Ϸ�Ǿ��ٸ�
			else
			{
				// ��ũ��Ʈ �ε��� ���� [questScripts �迭���� - 2]���� ���� ���ٸ�
				if (super.getQuestScriptCount() == super.getQuestScripts().length - 2)
				{	// ��ũ��Ʈ �ε��� + 1 ��ȯ
					return super.getQuestScriptCount() + 1;
				}
				// ��ũ��Ʈ �ε��� �� < [questScripts �迭 ���� - 2]�̶��
				else if (super.getQuestScriptCount() < super.getQuestScripts().length - 2)
				{
					// ��ũ��Ʈ �ε��� ����
					super.setQuestScriptCount(getQuestScriptCount() + 1);
					
					// ����Ʈ �迭 �ε��� ����
					super.setQuestCount(getQuestCount() + 1);
					
					continue;
				}
			}
		}
	}
}