// ��ų ���� ����Ʈ�� ��� reward�� null�� �����ϴ� ��
public class Quest {
	private String questName;
	private String description;
	private boolean condition;		// ����Ʈ �Ϸ� ���� �޼� ����
	private boolean completion;		// ����Ʈ �Ϸ� ����
	private Item[] reward;			// ���� ������
	
	
	// ������� �̸� ���� ������
	Quest()
	{
		
	}
	Quest(String questName, String description, Item[] reward)
	{
		this.questName = questName;
		this.description = description;
		this.reward = reward;
	}
	
	// -------- getter, setter �޼ҵ� -------- \\
	
	public String getQuestName()
	{
		return questName;
	}
	public void setQuestName(String questName)
	{
		this.questName = questName;
	}
	
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public boolean getCondition()
	{
		return condition;
	}
	public void setCondition(boolean condition)
	{
		this.condition = condition;
	}
	
	public boolean getCompletion()
	{
		return completion;
	}
	public void setCompletion(boolean completion)
	{
		this.completion = completion;
	}
	
	public Item[] getReward()
	{
		return reward;
	}
	public void setReward(Item[] reward)
	{
		this.reward = reward;
	}
}
