// NPC �⺻ ����
// �÷��̾ NPC���� ���� �ɸ�
// UI������ playQuestScript(playQuest())�� �����Ͽ� ��ũ��Ʈ ������

public abstract class NPC {
	private String[] questScripts;		// ����Ʈ ���� ��ũ��Ʈ
	private int questScriptCount;		// ����Ʈ ��ũ��Ʈ �ε���
	private int questCount;				// ����Ʈ �迭 �ε���
	private Quest[] quest;				// ����Ʈ
   
	NPC()
	{
		// ��ũ��Ʈ �޾ƿ���, ����Ʈ ����
	}
   
	// ---------getter, setter �޼ҵ�--------- \\
   
	public String[] getQuestScripts()
	{
		return questScripts;
	}
	public void setQuestScripts(String[] questScripts)
	{
		this.questScripts = questScripts;
	}
   
	public int getQuestScriptCount()
	{
		return questScriptCount;
	}
	public void setQuestScriptCount(int questScriptCount)
	{
		this.questScriptCount = questScriptCount;
	}
   
	public int getQuestCount()
	{
		return questCount;
	}
	public void setQuestCount(int questCount)
	{
      this.questCount = questCount;
	}
	
	public Quest getQuest(int index)	// Ư�� ����Ʈ ��ȯ(����)
	{
		return quest[index];
	}
	public Quest[] getQuestAll()		// ��� ����Ʈ ��ȯ
	{
		return quest;
	}
	public void setQuest(int index, Quest quest)	// Ư�� ����Ʈ ����(����)
	{
		this.quest[index] = quest;
	}
	public void setQuestAll(Quest[] quest)			// ��� ����Ʈ ����
	{
		this.quest = quest;
	}
	
   
	// -------------�� �� �޼ҵ�------------- \\
   
	public String playQuestScript(int questScriptCount)   //questScriptCount ������ �ش� �ε����� ����Ʈ ��ũ��Ʈ ��ȯ
	{
		return questScripts[questScriptCount];
	}
	
	// �߻� �޼ҵ�. ���� Ŭ�������� ����
	public abstract int playQuest(Player player);	//����Ʈ ��ũ��Ʈ�� �ε��� ��(questCount) ��ȯ
}
