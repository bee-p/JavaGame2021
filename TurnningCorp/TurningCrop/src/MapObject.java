
public class MapObject {
	private String objectName;		// �繰 �̸�
	private String description;		// ����
	private Item[] item;			// ��ȯ�� ������(�ִٸ�)
	
	
	// ������
	MapObject()				// �������..�μ� ���� ������
	{
		
	}
	MapObject(String objectName, String description, Item[] item)
	{
		this.objectName = objectName;
		this.description = description;
		this.item = item;
	}
	
	
	// getter, setter �޼ҵ�
	public String getObjectName()
	{
		return objectName;
	}
	public void setObjectName(String objectName)
	{
		this.objectName = objectName;
	}
	
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public Item getItem(int index)				// ������ ���� ��ȯ
	{
		return item[index];
	}
	public Item[] getAllItem()					// ��� ������ ��ȯ
	{
		return item;
	}
	public void setItem(Item item, int index)				// ������ ���� ����
	{
		this.item[index] = item;
	}
	public void setAllItem(Item[] item)						// ��� ������ ����
	{
		this.item = item;
	}
}
