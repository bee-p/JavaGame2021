// 스킬 & 진급 NPC

public class SkillNPC extends NPC {
	// 스킬 목록
	private String[] skillName_1;
	private String[] skillName_2;
	
	
	// 생성자
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
	
	
	// questCount값으로 스킬 교체
	public void skillChange(int questCount, Player player)
	{
		// 스킬 목록에서 해당 인덱스의 스킬로 교체
		player.setSkillName1(skillName_1[questCount]);
		player.setSkillName2(skillName_2[questCount]);
	}
	
	// 퀘스트 활성화 여부 판단
	// 퀘스트 스크립트의 인덱스 값 반환
	public int playQuest(Player player)
	{
		while (true)
		{
			// 해당 퀘스트가 완료되지 않았다면
			if (super.getQuest(getQuestCount()).getCompletion() == false)
			{
				// 완료 조건 달성시
				if (super.getQuest(getQuestCount()).getCondition() == true)
				{
					// 퀘스트 스크립트 인덱스 1 증가
					super.setQuestScriptCount(getQuestScriptCount() + 1);
					
					// 해당 퀘스트 완료로 변경
					super.getQuest(getQuestCount()).setCompletion(true);
					
					// 플레이어 스킬 교체
					// 현재 퀘스트 배열 인덱스의 값으로 스킬 등급(순서) 판별
					// 스킬 등급에 따른 순서는 스킬 목록 필드(skillName_1~2)로 미리 지정함
					skilChange(super.getQuestCount(), player);
				}
				// 완료 조건 미달성시는 따로 수행하는 작업이 없으므로
				// 명시하지 않음
				
				// 퀘스트 스크립트의 인덱스 값 반환
				return super.getQuestScriptCount();
			}
			// 해당 퀘스트가 완료되었다면
			else
			{
				// 스크립트 인덱스 값과 [questScripts 배열길이 - 2]값이 서로 같다면
				if (super.getQuestScriptCount() == super.getQuestScripts().length - 2)
				{	// 스크립트 인덱스 + 1 반환
					return super.getQuestScriptCount() + 1;
				}
				// 스크립트 인덱스 값 < [questScripts 배열 길이 - 2]이라면
				else (super.getQuestScriptCount() < super.getQuestScripts().length - 2)
				{
					// 스크립트 인덱스 증가
					super.setQuestScriptCount(getQuestScriptCount() + 1);
					
					// 퀘스트 배열 인덱스 증가
					super.setQuestCount(getQuestCount() + 1);
					
					continue;
				}
			}
		}
	}
}
