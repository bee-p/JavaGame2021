// ��ų & ���� NPC

public class SkillNPC extends NPC {
	// ��ų ���
	private String[] skillName_1;
	private String[] skillName_2;
	
	
	// ������
	SkillNPC()
	{
		super();
	}
	SkillNPC(String[] skillName_1, String[] skillName_2)
	{
		super();
		
		this.skillName_1 = skillName_1;
		this.skillName_2 = skillName_2; 
	}
	
	
	// questCount������ ��ų ��ü
	public void skillChange(int questCount, Player player)
	{
		// ��ų ��Ͽ��� �ش� �ε����� ��ų�� ��ü
		player.setSkillName1(skillName_1[questCount]);
		player.setSkillName2(skillName_2[questCount]);
	}
	
	// ����Ʈ Ȱ��ȭ ���� �Ǵ�
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
					
					// �÷��̾� ��ų ��ü
					// ���� ����Ʈ �迭 �ε����� ������ ��ų ���(����) �Ǻ�
					// ��ų ��޿� ���� ������ ��ų ��� �ʵ�(skillName_1~2)�� �̸� ������
					skilChange(super.getQuestCount(), player);
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
				else (super.getQuestScriptCount() < super.getQuestScripts().length - 2)
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
