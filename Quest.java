// 스킬 관련 퀘스트의 경우 reward를 null로 설정하는 식
public class Quest {
	private String questName;
	private String description;
	private boolean condition;		// 퀘스트 완료 조건 달성 여부
	private boolean completion;		// 퀘스트 완료 여부
	private Item[] reward;			// 보상 아이템
	
	
	// 명시적인 이름 없는 생성자
	Quest()
	{
		
	}
	Quest(String questName, String description, Item[] reward)
	{
		this.questName = questName;
		this.description = description;
		this.reward = reward;
	}
	
	// -------- getter, setter 메소드 -------- \\
	
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
