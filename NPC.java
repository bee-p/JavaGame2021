// NPC 기본 설정
// 플레이어가 NPC에게 말을 걸면
// UI에서는 playQuestScript(playQuest())을 실행하여 스크립트 전달함

public abstract class NPC {
	private String[] questScripts;		// 퀘스트 설명 스크립트
	private int questScriptCount;		// 퀘스트 스크립트 인덱스
	private int questCount;				// 퀘스트 배열 인덱스
	private Quest[] quest;				// 퀘스트
   
	NPC()
	{
		// 스크립트 받아오기, 퀘스트 생성
	}
   
	// ---------getter, setter 메소드--------- \\
   
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
	
	public Quest getQuest(int index)	// 특정 퀘스트 반환(단일)
	{
		return quest[index];
	}
	public Quest[] getQuestAll()		// 모든 퀘스트 반환
	{
		return quest;
	}
	public void setQuest(int index, Quest quest)	// 특정 퀘스트 수정(단일)
	{
		this.quest[index] = quest;
	}
	public void setQuestAll(Quest[] quest)			// 모든 퀘스트 수정
	{
		this.quest = quest;
	}
	
   
	// -------------그 외 메소드------------- \\
   
	public String playQuestScript(int questScriptCount)   //questScriptCount 값으로 해당 인덱스의 퀘스트 스크립트 반환
	{
		return questScripts[questScriptCount];
	}
	
	// 추상 메소드. 하위 클래스에서 구현
	public abstract int playQuest(Player player);	//퀘스트 스크립트의 인덱스 값(questCount) 반환
}
