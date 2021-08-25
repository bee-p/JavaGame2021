
public class Map {
	private Enemy[] enemy;
	private ItemNPC npc;
	private String[] scripts;		// ��(�Ǵ� �κ�)�� �������� �� �ߴ� ���ڿ�
	private boolean active;			// �ش� ��(��)�� Ȱ��ȭ �Ǿ�����?
	private int floorLevel;			// �� ǥ��
//	private int roomID;				// �� ǥ��
//									// (0~3 / 0��: �κ�, 1~3��: ��)
	private MapObject[] object;		// ������ �� �ִ� �繰 ��ü �迭
	
	
	// ������
	Map()				// �������..�μ����� ������
	{
		
	}
	Map(ItemNPC npc, int floorLevel, MapObject[] object)
	{
		// active �ʵ�� false�� �ڵ� �ʱ�ȭ �ǹǷ� ���� ���� ����.
		
		this.npc = npc;
		this.floorLevel = floorLevel;
		this.object = object;
	}
	
	
	// getter, setter �޼ҵ�
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
	
	public Enemy getEnemy(int index)			// ���� ���� ��ȯ
	{
		return enemy[index];
	}
	public Enemy[] getAllEnemy()				// ��� ���� ��ȯ
	{
		return enemy;
	}
	public void setEnemy(Enemy enemy, int index)		// ���� ���� ����
	{
		this.enemy[index] = enemy;
	}
	public void setAllEnemy(Enemy[] enemy)				// ��� ���� ����
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
	
	public MapObject getObject(int index)		// �繰 ���� ��ȯ
	{
		return object[index];
	}
	public MapObject[] getAllObject()			// ��� �繰 ��ȯ
	{
		return object;
	}
	public void setObject(MapObject object, int index)			// �繰 ���� ����
	{
		this.object[index] = object;
	}
	public void setAllObject(MapObject[] object)					// ��� �繰 ����
	{
		this.object = object;
	}
	
	
	
	// --------------------- �� �� �޼ҵ� --------------------- \\
	
	// �ش� ���� Ȱ��ȭ ���� ���ڿ��� �ѱ��
	public String printActive()
	{
		if (active)
			return floorLevel + "��(Ȱ��ȭ)";
		else
			return floorLevel + "��(��Ȱ��ȭ)";
	}
}
