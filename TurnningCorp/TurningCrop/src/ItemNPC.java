// 보상아이템으로 아이템을 반환하는 NPC

public class ItemNPC extends NPC {
	// 몬스터 NPC인지 판별
	// ex) true면 몬스터 NPC
	private boolean isMonster;
	
	// 생성자
	ItemNPC()
	{
		super();
	}
	
	// ------ getter, setter 메소드 ------ \\
	
	public boolean getIsMonster()
	{
		return isMonster;
	}
	public void setIsMonster(boolean isMonster)
	{
		this.isMonster = isMonster;
	}
	
	
	// ------------그 외 메소드------------ \\
	
	// 퀘스트 활성화 여부 판단 (스크립트 분별)
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
					
					// 플레이어에게 보상 아이템 지급 => **npc/quest 팀원과 이야기해서 바꾸기!
					player.saveInventory(super.getQuest(getQuestCount()).getReward());
					
					// 만일 몬스터 NPC일 경우
					if (isMonster == true)
					{
						// 플레이어에게 평판도 지급(임의)
						player.plusReputation(100);
					}
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