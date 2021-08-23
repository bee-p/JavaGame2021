
public class MapObject {
	private String objectName;		// 사물 이름
	private String description;		// 설명
	private Item[] item;			// 반환할 아이템(있다면)
	
	
	// 생성자
	MapObject()				// 명시적인..인수 없는 생성자
	{
		
	}
	MapObject(String objectName, String description, Item[] item)
	{
		this.objectName = objectName;
		this.description = description;
		this.item = item;
	}
	
	
	// getter, setter 메소드
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
	
	public Item getItem(int index)				// 아이템 단일 반환
	{
		return item[index];
	}
	public Item[] getAllItem()					// 모든 아이템 반환
	{
		return item;
	}
	public void setItem(Item item, int index)				// 아이템 단일 변경
	{
		this.item[index] = item;
	}
	public void setAllItem(Item[] item)						// 모든 아이템 변경
	{
		this.item = item;
	}
}
