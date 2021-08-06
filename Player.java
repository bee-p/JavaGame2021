
public class Player {
	private String skillName1;
	private String skillName2;
	
	public String getSkillName1()
	{
		return skillName1;
	}
	public void setSkillName1(String skillName1)
	{
		this.skillName1 = skillName1;
	}
	
	public String getSkillName2()
	{
		return skillName2;
	}
	public void setSkillName2(String skillName2)
	{
		this.skillName2 = skillName2;
	}
	
	public void addItem(Item[] item)
	{
		// 인벤토리에 아이템 추가
	}
	
	public void plusReputation(int reputation)
	{
		// 플레이어의 평판도 증가
	}
}
