
public class Map {
	private Enemy[] enemy;
	private ItemNPC npc;
//	private String[] scripts;		// 방(또는 로비)에 입장했을 때 뜨는 문자열
	private boolean active;			// 해당 방(층)이 활성화 되었는지?
	private int floorLevel;			// 층 표시
//	private int roomID;				// 방 표시
//									// (0~3 / 0번: 로비, 1~3번: 방)
	private MapObject[] object;		// 조사할 수 있는 사물 객체 배열
	
	
	// 생성자
	Map()				// 명시적인..인수없는 생성자
	{
		
	}
	Map(ItemNPC npc, int floorLevel, MapObject[] object)
	{
		// active 필드는 false로 자동 초기화 되므로 따로 적지 않음.
		
		this.npc = npc;
		this.floorLevel = floorLevel;
		this.object = object;
	}
	
	
	// getter, setter 메소드
	public boolean getActive()
	{
		return active;
	}
	public void setActive(boolean active)
	{
		this.active = active;
	}
	
	public int getFloorLevel()
	{
		return floorLevel;
	}
	public void setFloorLevel(int floorLevel)
	{
		this.floorLevel = floorLevel;
	}
	
	public Enemy getEnemy(int index)			// 몬스터 단일 반환
	{
		return enemy[index];
	}
	public Enemy[] getAllEnemy()				// 모든 몬스터 반환
	{
		return enemy;
	}
	public void setEnemy(Enemy enemy, int index)		// 몬스터 단일 변경
	{
		this.enemy[index] = enemy;
	}
	public void setAllEnemy(Enemy[] enemy)				// 모든 몬스터 변경
	{
		this.enemy = enemy;
	}
	
	public ItemNPC getNpc()
	{
		return npc;
	}
	public void setNpc(ItemNPC npc)
	{
		this.npc = npc;
	}
	
	public MapObject getObject(int index)		// 사물 단일 반환
	{
		return object[index];
	}
	public MapObject[] getAllObject()			// 모든 사물 반환
	{
		return object;
	}
	public void setObject(MapObject object, int index)			// 사물 단일 변경
	{
		this.object[index] = object;
	}
	public void setAllObject(MapObject[] object)					// 모든 사물 변경
	{
		this.object = object;
	}
	
	
	
	// --------------------- 그 외 메소드 --------------------- \\
	
	// 해당 층과 활성화 상태 문자열로 넘기기
	public String printActive()
	{
		if (active)
			return floorLevel + "층(활성화)";
		else
			return floorLevel + "층(비활성화)";
	}
}
