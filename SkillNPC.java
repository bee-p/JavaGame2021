// 스킬 & 진급 NPC

public class SkillNPC extends NPC {
	
	// 생성자
	SkillNPC()
	{
		super();
	}
	
	// 퀘스트 활성화 여부 판단(스크립트 분별)
	// 퀘스트 최초 완료시 게임매니저가 플레이어의 진급 여부를 알기 위해서는
	// 게임매니저에서 playQuestScript(playQuest()) 메소드를 실행시킨 후,
	// 해당 NPC의 현재 퀘스트의 완료 여부를 체크하면 된다.
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
				else if (super.getQuestScriptCount() < super.getQuestScripts().length - 2)
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