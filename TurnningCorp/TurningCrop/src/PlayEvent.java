import java.util.Scanner;

/*
 * object: �� ���ο� �ִ� ��ǥ�� �繰�� (���� å��, ������ å�� ,,,)
 * object ���� item: ���� ������... (å�� ���� �޸���, �ϱ���, ���� ������ ,,,)
 */

/*
 * ������ �����ؼ��� �ͼ��� ó���� �ѱ� ���� ����...���� �����
 * ���� ���� �κ� �޼ҵ�� ���� ����� ó���� ���� ����(�ݺ��Ǵ� �κ�)
 */

public class PlayEvent {
	// ��ĳ�� ��ü, ������/������Ʈ �ӽ� ���� ���� ����
	private Scanner scan = new Scanner(System.in);
	private int num;
	private MapObject mapObject;
	
	// Ÿ��Ʋ �̵� �Ǻ�
	private boolean goTitle;
	
	// ���� �Ǻ��� �� ����ϴ� ����Ʈ ��
//	private int endingPoint = 0;
	
	// Player, Map ��ü
	// -> GameManager Ŭ�������� PlayEvent ��ü ������ �� ����ֱ�
	private Map[][] map;
	private Player player;
	
	// ItemManager ��ü
	// �����, �ϱ����� �ҷ����� ���� Ȱ��
	private ItemManager im = new ItemManager();
	
	// ������
	PlayEvent()
	{
		// �ӽ�,,
	}
	PlayEvent(Map[][] map, Player player)
	{
		this.map = map;
		this.player = player;
	}
	
	// getter, setter �޼ҵ�
	public boolean getGoTitle()
	{
		return goTitle;
	}
	public void setGoTitle(boolean goTitle)
	{
		this.goTitle = goTitle;
	}
	
	
	// ���� �̵� ���� (����������)
	private void moveFloor()
	{
		while(true)
		{
			// �� ��� �����ֱ�
			// ex) 1��(Ȱ��ȭ) \n ...
			for (int i = 0; i < 5; i++)
			{
				System.out.println(map[i][0].printActive());
			}
			
			System.out.println("�̵��� ���� ��ư�� ������. (���� �Է�)");
			
			num = scan.nextInt();
			
			// ���� ���� ���� ��Ȱ��ȭ ���¶��
			if (!map[num - 1][0].getActive())
			{	
				// �̵� ����, �ٽ� �� ��� ������� ���ư�
				System.out.println("��ư�� ������ �ʴ´�.");
				continue;
			}
			
			if (num == 1)
			{
				player.setPosID(10);	// ��� �κ�� �̵��ϴ� ���̱� ������ ��� ID�� �� ��ȣ�� 0�̴�.
				break;
			}
			else if (num == 2)
			{
				player.setPosID(20);
				break;
			}
			else if (num == 3)
			{
				player.setPosID(30);
				break;
			}
			else if (num == 4)
			{
				player.setPosID(40);
				break;
			}
			else if (num == 5)
			{
				player.setPosID(50);
				break;
			}
			else if (num == 666)
			{
				System.out.println("�Ұ����ϴ�.");
				continue;
			}
			else
			{
				System.out.println("�׷� ��ư�� ����.");
				continue;
			}
		}
	}
	
	
	// �繰(object ��� ���ø�)
	// �ش� ������Ʈ�� �޾ƿ� �����
	public void objectPrint(MapObject mapObject)
	{
		// ex) ������� å���̴�.
		System.out.println(mapObject.getObjectName() + "�̴�.");
		// �ش� ������Ʈ�� ���� ���
		System.out.println(mapObject.getDescription());
	}
	
	
	// �Ϲ� �濡 �������� �� ���/�̺�Ʈ ����(�ݺ� ���) - �κ� ����
	// true ��ȯ: �ش� �� �̺�Ʈ �޼ҵ忡�� �״�� �繰 ���� ����
	// false ��ȯ: �ش� �� �̺�Ʈ �޼ҵ��� �ݺ����� Ż��, �κ�� ������ ��
	public boolean enterRoom(int floor, int roomID)
	{
		// �迭�� �ε����� Ȱ���ϱ� ���� 1 ����
		floor--;
		
		while(true)
		{
			System.out.println("��� ���캼��?");
			
			// for������ �ش� ���� ���� �繰 �迭 ��� (���ڿ� �Բ�) ����ϱ�..
			for (int i = 0; i < map[floor][roomID].getAllObject().length; i++)
			{	// ex) 1. �� å��
				System.out.println((i + 1) + ". " + map[floor][roomID].getObject(i).getObjectName());
			}
			// [������ ��ȣ]. �κ�� ������.
			System.out.println((map[floor][roomID].getAllObject().length + 1) + ". �κ�� ������.");
			System.out.print("* ���ϴ� �������� ���� �Է�: ");
			
			num = scan.nextInt();
			
			// ������ ���� ��ȣ�� ����� �Է����� �ʾ��� ���(������)
			if (!(num >= 1 && num <= map[floor][roomID].getAllObject().length + 1))
			{
				System.out.println("������ ���簡 �Ұ����ϴ�.");
				continue;
			}
			
			// �������� ����� �Է����� ���, ������ continue�� �ɸ��� �����Ƿ� break ����
			// ���� while�� Ż���Ͽ� ���� �����ϵ��� ��
			break;
		}
		
		// �κ�� �����⸦ �������� ���
		if (num == map[floor][roomID].getAllObject().length + 1)
		{
			// floor�� �ε����� Ȱ��Ǳ� ���� 1 �����߾����Ƿ�
			// �ٽ� 1 ���� ��Ų ��, ���� ����� ���� 10�̳� 20 ���� posID ���·� ��ȯ��
			int id = (floor + 1) * 10 + roomID;
			
			// �κ�� ��ġ ������
			player.setPosID(id);
			// �ش� �� �޼ҵ�(enterRoom()�� ȣ��Ǵ� ��)�� �ݺ����� Ż���� �� �ֵ��� ��
			return false;
		}
		
		// �������� ����� �Է����� ��� (�⺻ ����)
		// 1. ���� ������Ʈ�� ������ ������Ʈ�� ����
		mapObject = map[floor][roomID].getObject(num - 1);
		
		// 2. �ش� ������Ʈ�� �̸� & ���� ���
		// ex) ������� å��� �� å�� ���� ���� ���..
		objectPrint(mapObject);
		
		return true;
	}
	
	
	// ----------------------------------------------------------------- \\
	
	
	// 1�� �κ� �̺�Ʈ �Լ�
	public void playFloor1_0()
	{
		while(true)
		{
			// 1. �κ� ��ũ��Ʈ ���
			System.out.println("1�� �κ��. ������ �ұ�?");
			
			// 2. ������ ���
			System.out.println("1. ���Ա⿡ ������� ����.");	// ����
			System.out.println("2. ��� �ۿ��� ���� ����.");	// Ÿ��Ʋ�� ������ (���� �޴�)
			System.out.println("3. �ٸ� ���� �ѷ�����.");		// �ٸ� �� �̵�
			System.out.println("4. ���������͸� Ÿ��.");		// �� �̵�
			
			num = scan.nextInt();
			
			if (num == 1)			// ����
			{
				// ���� ���� ������ ���ÿ� ����
				
				System.out.println("������� ���������� �����!\n--����Ǿ����ϴ�.--");
			}
			else if (num == 2)		// ���� �� Ÿ��Ʋ�� ������
			{
				// 1. ���� ������ ���ÿ� ����
				
				// 2. Ÿ��Ʋ�� ������
				goTitle = true;
				break;
			}
			else if (num == 3)		// �ٸ� ������ �̵�
			{
				System.out.println("������, ī��, ������ ���δ�. ���� ����?");
				
				System.out.println("1. �����η� ����.");
				System.out.println("2. ī��� ����.");
				System.out.println("3. ���Ƿ� ����.");
				System.out.println("4. ȭ��ǿ� ����.");
				System.out.println("5. ������ �޶�����.");		// �ٽ� �κ� ������ ���
				
				num = scan.nextInt();
				
				if (num == 1)	// ������ ����
				{
					player.setPosID(11);
					break;
				}
				else if (num == 2)	// ī�� ����
				{
					player.setPosID(12);
					break;
				}
				else if (num == 3)	// ���� ����
				{
					player.setPosID(13);
					break;
				}
				else if (num == 4)	// ȭ��� ����
				{
					player.setPosID(14);
					break;
				}
				else if (num == 5)	// �ٸ� ������ ���� �ʱ�(�κ� ��ũ��Ʈ/������ �ٽ� ���)
				{
					continue;
				}
				else
				{
					// �� �� ������ ó��
				}
			}
			else if (num == 4)		// �ٸ� ������ �̵�(���������� ž��)
			{
				// 1. ��������(�ɷκ��ν�) óġ �Ǻ�
				// (óġ���� �ʾҴٸ�) �÷��̾��� ���ǵ� �˻� -> ����/�׳� ������
				
				// 2. (óġ�ߴٸ�) �� �̵� �޼ҵ� ����
				moveFloor();
				break;
			}
			else
			{
				// �� �� ������ ó��
			}
		}
	}
	
	// 1�� ������(��1) �̺�Ʈ �Լ�
	public void playFloor1_1()
	{
		// 1. ��Ʋ....Ȯ�� ������
		
		// 2. ������ ��ũ��Ʈ ���...
		System.out.println("�����δ�.");
		System.out.println("���� ���ϴ� �μ��� �����ϸ���ġ �ͼ��� ���ѵ�, ���ϰ� ������ ����� ���� ���Ѵ�. ���̶� �׷���.");
		System.out.println("�տ��� �ͼ��� å�� �迭���� ���δ�. ���߿��� ���� ��� å�� �ɽ����� �ִ�.");
		
		while(true)
		{
			// 1���� ù ��°(1) ���̹Ƿ� �μ��� 1, 1�� �������
			// �� ���� �̺�Ʈ (�繰(������Ʈ) ��� �� ���� ����)
			// enterRoom�� ��ȯ���� false�� ���� ���� �̺�Ʈ�� �����ϵ��� ��(�̵�)
			if (!enterRoom(1, 1))
			{
				break;
			}
			
			
			// enterRoom�� ��ȯ���� true�� ���
			// -> ���� ����
			if (num == 1)		// ���� å��
			{
				// å�� ������ �޸����� �ִ�.
				System.out.println("\nå�� ������ " + mapObject.getItem(0).getName() + "�� �ִ�.");
				
				System.out.println("�ڼ��� ����?");
				System.out.println("1. ����.");
				System.out.println("2. �� ����.");
				
				num = scan.nextInt();
				
				if (num == 1)		// ����
				{	// �޸��� ���� ���
					// ����� ���� ������ �̸��Ϸ� ��������.
					System.out.println(mapObject.getItem(0).getDescription());
				}
				else if (num == 2)	// �� ����
				{
					System.out.println("�� ����� �ߴ�. ����� �޸�� ���ĺ��� �� �ȴ�.");
					System.out.println("�ٸ� å���� ���캸��.");
				}
				else				// ������
				{
					System.out.println("�׷� �������� ����.");
				}
			}
			else if (num == 2)	// ������ ����� å��
			{
				while(true)
				{
					System.out.println("å�� ���� å�� �Ʒ� ���� �� ��� ���� ���� ����?");
					System.out.println("1. å�� ���� ����.");
					System.out.println("2. å�� �Ʒ� ������ ����.");
					System.out.println("3. �� �� ���̴�. (å�� ������� ������)\n");
					
					num = scan.nextInt();
					
					if (num == 1)		// å�� �� ����
					{
						// �̹� �������� ���������� �˻�(�ϱ���_1)
						if (player.searchItem(im.getDiary(0).getName()))
						{
							// �������� �̹� �������ٸ� �ٽ� å�� ��/å�� �Ʒ� �������� �̵�
							System.out.println("å�� ���� ���̻� Ư���� �� ������ �ʴ´�.");
							continue;
						}
						
						// �������� �������� �ʾҴٸ� ���� ���� �״��..
						
						// å�� ������ �ϱ����� �ִ�.
						System.out.println("å�� ������ " + im.getDiary(0).getName() + "�� �ִ�.");
						System.out.println("��������?");
						System.out.println("1. ��������.");
						System.out.println("2. �������� ����.");
						
						num = scan.nextInt();
						
						if (num == 1)
						{
							// �÷��̾� �κ��丮�� �ϱ��� ����
							player.saveInventory(im.getDiary(0));
							
							System.out.println(im.getDiary(0).getName() + "�� ��������.");
						}
						else if (num == 2)
						{
							System.out.println("�������� �ʱ�� �ߴ�.");
						}
						else				// ������
						{
							System.out.println("�׷� �������� ����.");
						}
					}
					else if (num == 2)	// å�� �Ʒ� ���� ����
					{
						// '�𸣴� ����'�� ���� �ִ��� �˻�
						// ���� �ִٸ�
						if (player.searchItem("�𸣴� ����"))
						{
							System.out.print("å�� ���� �ȿ��� ");
							
							// �ش� �������� ���� ������ �˻�(�����1)
							if (player.searchItem(im.getEasyDevilLanguage(0).getName()))
							{
								System.out.println("���̻� �ƹ� �͵� ����.");
							}
							else	// ���� �������� �ʾҴٸ�
							{	
								System.out.println(im.getEasyDevilLanguage(0).getName() + "�� �ִ�.");
								System.out.println("��������?");
								System.out.println("1. ��������.");
								System.out.println("2. �������� ����.");
								
								num = scan.nextInt();
								
								if (num == 1)		// ��������
								{
									// �÷��̾� �κ��丮�� ������ ����
									player.saveInventory(im.getEasyDevilLanguage(0));
									
									System.out.println(im.getEasyDevilLanguage(0).getName() + "�� ��������.");
								}
								else if (num == 2)	// �������� ����
								{
									System.out.println("�������� �ʱ�� �ߴ�.");
								}
								else				// ������
								{
									System.out.println("�׷� �������� ����.");
								}
							}
						}
						else	// ���谡 ���ٸ�
						{
							System.out.println("������ ������ �ʴ´�.");
							System.out.println("�ڹ���� �ܴ��� ����ִ�. ���谡 �ʿ��� �� ����.");
						}
					}
					else if (num == 3)	// �� ����
					{
						System.out.println("�ƹ��� �����ϴ��� ���� å���� ���� �� �ȴ�.");
						System.out.println("������ ����� å�󿡼� ���Դ�.");
						break;		// �ݺ��� Ż��, å�� ��ϵ� �������� �̵���
					}
					else				// ������
					{
						System.out.println("�׷� �������� ����.");
					}
				}
			}
			else if (num == 3)	// �������� å��
			{
				// �������� �̸�, ���� ���
				System.out.println("å�� ������ " + mapObject.getItem(0).getName() + "�� �ִ�.");
				System.out.println(mapObject.getItem(0).getDescription());
				
				System.out.println("�� �ܿ� ���ٸ� ���� ����δ�.");
				System.out.println("��ǻ� å�� ���� �������� ���� ���� �˾ƺ��Ⱑ �����. ������.");
			}
			else if (num == 4)	// ������ å��
			{
				// ���̾�� �ִ�!
				System.out.println("å�� ������ " + mapObject.getItem(0).getName() + "�� �ִ�.");
				System.out.println("�ڼ��� ����?");
				System.out.println("1. ����.");
				System.out.println("2. �� ����.");
				
				num = scan.nextInt();
				
				if (num == 1)		// ����
				{	
					// ���̾�� ���� ���
					System.out.println(mapObject.getItem(0).getDescription());
				}
				else if (num == 2)	// �� ����
				{
					System.out.println("�� ����� �ߴ�.");
					System.out.println("�ٸ� å����� ��������.");
				}
				else				// ������
				{
					System.out.println("�׷� �������� ����.");
				}
			}
			else if (num == 5)	// ��θ� å��.....?
			{	
				// '���� ������' ���
				System.out.print(mapObject.getItem(0).getName());
				
				// �� ���� ���ں����� ���������� �˻�
				if (player.searchItem(mapObject.getItem(1).getName()))
				{
					// �������ٸ� ���� �����⿡ ���� �̸�/���� ���
					System.out.println("�� �ִ�.");
					System.out.println(mapObject.getItem(0).getDescription());
				}
				else	// �� �������ٸ�
				{
					// ���� ������� �� ���� ���ں����� �ִ�.
					System.out.println("�� " + mapObject.getItem(1).getName() + "�� �ִ�.");
					
					System.out.println("��� ���� ���� ����?");
					// ������ ��� ���(���� ������, ���ں���,,,)
					for (int i = 0; i < 2; i++)
						System.out.println((i + 1) + ". " + mapObject.getItem(i).getName());
//					System.out.println("3. �� ����");	--> ������ ��ȣ..�������ְ� ���� �ʿ�
					
					num = scan.nextInt();
					
					if (num == 1)		// 1. ���� ������ ����
					{
						// ���� ������ ���� ���
						System.out.println(mapObject.getItem(0).getDescription());
					}
					else if (num == 2)	// 2. �� ���� ���ں��� ����
					{
						// �� ���� ���ں��� ���� ���
						System.out.println(mapObject.getItem(1).getDescription());
						System.out.println("��������?");
						System.out.println("1. ��������.");
						System.out.println("2. ������.");
						
						num = scan.nextInt();
						
						if (num == 1)		// ��������
						{
							// �÷��̾� �κ��丮�� ������ ����
							player.saveInventory(mapObject.getItem(1));
							
							System.out.println(mapObject.getItem(1).getName() + "�� ì���.");
						}
						else if (num == 2)	// ������
						{
							System.out.println("���α�� �ߴ�.");
							System.out.println("�ٸ� å���̳� �ѷ�����.");
						}
						else				// ������
						{
							System.out.println("�׷� �������� ����.");
						}
					}
					else	// �� �� ������
					{
						System.out.println("�������� �߸� �Է��� �� �ϴ�.");
					}
				}
			}
			else if (num == 6)	// �÷��̾��� å��
			{
				System.out.println("å�� ������ �����?");
				System.out.println("1. �����.");
				System.out.println("2. ��������.");
				
				num = scan.nextInt();
				
				if (num == 1)		// ���� ����
				{
					System.out.println("�ƹ� �͵� ������� �ʴ�. ��ҿ� ���𰡸� �� �־�� �� �׷�������.");
				}
				else if (num == 2)		// ���� ���� �ʱ�
				{
					System.out.println("���� �ʱ�� �ߴ�.");
					System.out.println("�ٸ� å���̳� �� �� �ѷ�����.");
				}
				else				// ������
				{
					System.out.println("�׷� �������� ����.");
				}
			}
		}
	}
	
	// 1�� ī��(��2) �̺�Ʈ �Լ�
	public void playFloor1_2()
	{
		// 1. ��Ʋ....Ȯ�� ������
		
		// 2. ī�� ��ũ��Ʈ ���...
		System.out.println("ī���.");
		System.out.println("������ �պ����� �ʴ��� �׻� ��� �ξ���� �� �ִ� ���̾��µ�");
		System.out.println("�̷��� �ѻ��� ����� ���� ����� ���ϴ�.");
		System.out.println("������ Ŀ���⸸�� �� ���� �ɵ���.");
		
		while(true)
		{
			// 1���� �� ��°(2) ���̹Ƿ� �μ��� 1, 2�� �������
			// �� ���� �̺�Ʈ (�繰(������Ʈ) ��� �� ���� ����)
			// enterRoom�� ��ȯ���� false�� ���� ���� �̺�Ʈ�� �����ϵ��� ��(�̵�)
			if (!enterRoom(1, 2))
			{
				break;
			}
			
			// enterRoom�� ��ȯ���� true�� ���
			// -> ���� ����
			if (num == 1 || num == 2)			// TV 1, 2 ����
			{
				System.out.println("(����..)");
				System.out.println("�㿡 ���� �׷���..? TV�� ���ϰ� �̻��ϴ�.");
			}
			else if (num >= 3 && num <= 5)		// å�� 1, 2, 3 ����
			{
				System.out.println("�ƹ� �͵� ����.");
			}
			else if (num == 6)					// ī���� ����
			{
				// �����2 ŉ�� ���� ���
				System.out.println("�������.");
				
				System.out.println("������ ����Ʈ���� �پ��ִ�.");
				System.out.println("���� ũ�� �ְ��� �� �ִ�.");
				System.out.println("'��й�ȣ�� �� �� $&@# ������.'");
				System.out.println("..���� �����̶� �߰� �κ��� �˾ƺ� ���� ������.");
				
				System.out.println("�Ʒ� �ʿ��� ��й�ȣ�� �Է��ϴ� ���� �� ����.");
				System.out.println("����� �Է��ϸ� �ɱ�?");
				System.out.print("* �� �ڸ� ���ڸ� �Է��غ���: ");
				
				num = scan.nextInt();
				
				// ���� ���� å�� ������ 3 �Է�(����)
				if (num == 3)
				{
					System.out.println("(ö��)");
					System.out.println("���ȴ�! ��й�ȣ�� å�� ��������!");
					
					// �̹� �������� ��� ���ٸ�(���� �ִٸ�)
					if (player.searchItem(mapObject.getItem(0).getName()))
					{
						System.out.println("�׷����� ���̻� �ƹ� �͵� ������� �ʴ�...");
						System.out.println("���� ���� ������ �� ������ �ٽ� �ݾ� ����.");
						System.out.println("(�ٽ� ���� �Ҹ�)");
						
						// �ٽ� �繰 ������ ���(ó��)���� �ǵ��ư�
						continue;
					}
					
					while(true)
					{
						// �������� ���� �������� �ʾҴٸ�
						System.out.println(mapObject.getItem(0).getName() + "��.");
						System.out.println("��������?");
						System.out.println("1. ��������.");
						System.out.println("2. �������� ����.");
						
						num = scan.nextInt();
						
						if (num == 1)		// ��������
						{
							// ���� ���� �Ǹ���2 ����
							player.saveInventory(mapObject.getItem(0));
							
							System.out.println(mapObject.getItem(0).getName() + "�� ì���.");
						}
						else if (num == 2)	// �������� �ʱ�
						{
							
						}
						else				// ������
						{
							System.out.println("�ָ��� ������ ���� ����.");
							
							continue;
						}
						
						// ������ ���� ���� ������ ���
						System.out.println("Ȥ�� �𸣴� �ٽ� �����⸦ �ݾҴ�.");
						System.out.println("(�ٽ� ���� �Ҹ�)");
						
						break;
					}
				}
				else if (num >= 10)		// �� �ڸ����� �ƴ� �� �̻��� �Է����� ���
				{
					System.out.println("..�� �ڸ� ���ڶ�� ���� �ʾҳ�?");
				}
				else if (num < 0)		// ������ �Է����� ���
				{
					System.out.println("��й�ȣ��..���̳ʽ��� ���� ���� �� ����.");
				}
				else					// �� �� ����
				{
					System.out.println("�ƹ� �ϵ� �Ͼ�� �ʾҴ�.");
				}
			}
		}
	}
	
	// 1�� ����(��3) �̺�Ʈ �Լ�
	public void playFloor1_3(SkillNPC npc)
	{
		// 1. ��Ʋ....Ȯ�� ������
		
		// 2. ���� ��ũ��Ʈ ���
		System.out.println("�����̴�.");
		System.out.println("������ ��� ���̷� ����� ����� �Һ��� ������� �ִ�.");
		System.out.println("�׸��� �� �Һ� ������.. �� ����� �ɾ��ִ�.");
		
		while(true)
		{
			// �� ���� ��ų NPC�� �����ϴ� ���̹Ƿ� ������Ʈ(�繰)�� ���� �������� ����
			// ���� enterRoom() �޼ҵ带 ȣ������ ����
			
			// ex) "�������, ����� ��� @@��."
			System.out.println("\"�������, " + player.getName() + "��.\"");
			System.out.println("\"������ �Ͻðڽ��ϱ�?\"");
			System.out.println("\"���� ���� ���θ� Ȯ���� �� �ֽ��ϴ�.\"");
			
			System.out.println("1. ������ Ȯ������.");		// �÷��̾��� ��� & ��ų ������Ʈ
			System.out.println("2. ������ ����?");			// ���� ���� ���
			System.out.println("3. ������.");				// �κ�� ������
			
			num = scan.nextInt();
			
			if (num == 1)			// ���� ���� Ȯ�� & ���/��ų ������Ʈ
			{
				// ����Ʈ(���) �Ϸ� �ܰ迡 ���� NPC�� ��ũ��Ʈ ���
				System.out.println(npc.playQuestScript(npc.playQuest(player)));
				
				// ���� ������ ����Ʈ�� �Ϸ��� �Ķ��
				// (����Ʈ ��ũ��Ʈ�� �ε����� ������ �Ϸ� ��Ʈ�� ����ϰ� �ִ� ���¶��)
				if (npc.getQuestScriptCount() + 1 == npc.getQuestScripts().length - 1)
				{
					continue;
				}
				
				// ���� ���޿� �����ߴٸ� (���� ����Ʈ�� �Ϸ��ߴٸ�)
				if (npc.getQuest(npc.getQuestCount()).getCompletion())
				{
					// �÷��̾��� ��ũ ��
					player.upgradeReputation();
				}
			}
			else if (num == 2)		// ����/��ų�� ���� ���� ���
			{
				// ���� ��� ������ �־�� ������� �𸣰���,,�����
				// �뷫 ������ �ϸ� �� ���� ����� ���ϱ� ��ų�� �� �� �ִٴ� �ǹ�,,,
				System.out.println("\" ~~~ \"");
			}
			else if (num == 3)		// �κ�� ������
			{
				player.setPosID(10);
				break;
			}
			else					// ������
			{
				System.out.println("\"�׷� �� �Ͻ� �� �����ϴ�.\"");
			}
		}
	}
	
	// 1�� ȭ���(posID: 14) �̺�Ʈ �Լ�
	public void playFloor1_4()
	{
		// ȭ��� ��ũ��Ʈ ���...
		System.out.println("ȭ��ǿ� ���Դ�.");
		
		
		while(true)
		{
			// 1���� �� ��°(4) ��(ȭ���)�̹Ƿ� �μ��� 1, 4�� �������
			// �� ���� �̺�Ʈ (�繰(������Ʈ) ��� �� ���� ����)
			// enterRoom�� ��ȯ���� false�� ���� ���� �̺�Ʈ�� �����ϵ��� ��(�̵�)
			if (!enterRoom(1, 4))
			{
				break;
			}
			
			
			// enterRoom�� ��ȯ���� true�� ���
			// -> ���� ����
			if (num == 1)		// �ſ� ����
			{
				System.out.println("�� ����̴�.");
				System.out.println("* hp: " + player.getHp());
				System.out.println("* ���ݷ�: " + player.getAttackPower());
				System.out.println("* ����: " + player.getDefensivePower());
				System.out.println("* ���ǵ�: " + player.getReputation());
			}
			else if (num == 2)	// �ٴ� ����
			{
				// ���� �÷��̾ �̹� ������ �ֿ��ٸ�
				if (player.searchItem(mapObject.getItem(0).getName()))
				{
					System.out.println("������ �׷����� ������ �ٴ��̴�.");
					
					// �繰(������Ʈ) �������� �ٽ� ���ư�
					continue;
				}
				
				// ������ ���� �� �ֿ��ٸ�
				System.out.println("�ٴڿ� ���� �� ���� ������ �ִ�.");
				System.out.println("����... �繫������ �����.");
				
				System.out.println("��������?");
				System.out.println("1. ��������");
				System.out.println("2. ������.");
				
				num = scan.nextInt();
				
				if (num == 1)		// ��������
				{
					// ������ �÷��̾��� �κ��丮�� ����
					player.saveInventory(mapObject.getItem(0));
					
					System.out.println("������ ì���.");
					System.out.println("������ �ֿ� ���� ��¦ ������ �� ���⵵ �ϴ�..");
				}
				else if (num == 2)	// ���α�
				{
					System.out.println("����..�繫������ ���� ������ �ٴڿ� �������ξ���.");
					System.out.println("ȥ�� ������ �ִ� ����� �� ó���� ���̱⵵ ������ ��¿ �� ����.");
				}
				else				// ������
				{
					System.out.println("������ Ȯ���� �ؾ��Ѵ�.");
				}
			}
			else if (num == 3)	// �� ���� ����(�κ��丮, ���� �������� ����Ʈ Ȯ��)
			{
				System.out.println("� �� ����?");
				System.out.println("1. ���� ����");		// �κ��丮 Ȯ��
				System.out.println("2. �� �� ���");	// ����Ʈ ��� Ȯ��
				
				num = scan.nextInt();
				
				if (num == 1)			// �κ��丮 Ȯ��
				{
					player.showInventory();
				}
				else if (num == 2)		// ����Ʈ Ȯ��
				{
					// ���� �÷��̾ ���� ���� ����Ʈ ��� ���
					player.printQuestList();
				}
				else					// ������
				{
					System.out.println("�װ�... �߸��� �����̴�.");
				}
			}
		}
	}
	
	
	// * * * * * * * * * * * * * \\
	
	
	// 2�� �κ� �̺�Ʈ �Լ�
	public void playFloor2_0()
	{

		while(true)
		{
			// 1. �κ� ��ũ��Ʈ ���
			// �Ĵ��� 2���� �κ��.
			
			// 2. ������ ���
			System.out.println("1. ���Ա⿡ ������� ����.");	// ����
			System.out.println("2. ��� �ۿ��� ���� ����.");	// Ÿ��Ʋ�� ������ (���� �޴�)
			System.out.println("3. �ٸ� ���� �ѷ�����.");		// �ٸ� �� �̵�
			System.out.println("4. ���������͸� Ÿ��.");		// �� �̵�
			
			num = scan.nextInt();
			
			if (num == 1)			// ����
			{
				// ���� ���� ������ ���ÿ� ����
				
				System.out.println("������� ���������� �����!\n--����Ǿ����ϴ�.--");
			}
			else if (num == 2)		// ���� �� Ÿ��Ʋ�� ������
			{
				// 1. ���� ������ ���ÿ� ����
				
				// 2. Ÿ��Ʋ�� ������
				goTitle = true;
				break;
			}
			else if (num == 3)		// �ٸ� ������ �̵�
			{
				// ��Ľ�, ����� �繫���� ���δ�. ���� ����?
				System.out.println("1. ��ĽǷ� ����.");
				System.out.println("2. ����� �繫�Ƿ� ����.");
				System.out.println("3. ȭ��ǿ� ����.");
				System.out.println("4. ������ �޶�����.");		// �ٽ� �κ� ������ ���
				
				num = scan.nextInt();
				
				if (num == 1)		// ��Ľ� ����
				{
					player.setPosID(21);
					break;
				}
				else if (num == 2)	// ����� �繫�� ����
				{
					player.setPosID(22);
					break;
				}
				else if (num == 3)	// ȭ��� ����
				{
					player.setPosID(24);
					break;
				}
				else if (num == 4)	// �ٸ� ������ ���� �ʱ�(�κ� ��ũ��Ʈ/������ �ٽ� ���)
				{
					continue;
				}
				else
				{
					// �� �� ������ ó��
				}
			}
			else if (num == 4)		// �ٸ� ������ �̵�(���������� ž��)
			{
				moveFloor();
				break;
			}
			else
			{
				// �� �� ������ ó��
			}
		}
	}
	
	// 2�� ��Ľ�(��1) �̺�Ʈ �Լ�
	public void playFloor2_1()
	{
		// 1. ��Ʋ �ý��� Ȯ��
		
		// 2. ��Ľ� ��ũ��Ʈ ���
		// �Ĵ��� ��Ľ��̴�. �系 �Ĵ��� ���� ����� �����ؼ�, ��� ��κ��� ������ �ۿ��� �ذ��� ���� ������ �幮 ���̴�. ���õ� �� �ٸ� �� ���� ���δ�.
		// �ƹ��� ���� ��Ľǿ��� ��Ĵ�� ���̺�, �ļ��밡 ���δ�.
		
		while(true)
		{
			// 2���� ù ��°(1) ���̹Ƿ� �μ��� 2, 1�� �������
			// �� ���� �̺�Ʈ (�繰(������Ʈ) ��� �� ���� ����)
			// enterRoom�� ��ȯ���� false�� ���� ���� �̺�Ʈ�� �����ϵ��� ��(�̵�)
			if (!enterRoom(2, 1))
			{
				break;
			}
			
			
			// enterRoom�� ��ȯ���� true�� ���
			// -> ���� ����
			if (num == 1)			//���̺� ����
			//2�� 1��° �� ȹ�� ������ : ��(2��° obj�� 0), �����(2��° obj�� 1), ����(3���� obj�� 0)
			{
				while(true)
				{
					System.out.println("��Ľ� �Ѱ�� Ŀ�ٶ� ���̺��� �ִ�. ��� ���캼��?");
					System.out.println("1. ���̺� ���� ���캸��.");
					System.out.println("2. ���̺� �Ʒ��� ���캸��.");
					System.out.println("3. ���̺� �� ���ڵ��� ���캸��.");
					System.out.println("4. �׸� ���캸��.");
					
					num = scan.nextInt();
					
					if (num == 1) 			//���̺� ���� ���캼 ���
					{
						System.out.println("���̺� ������ ���� ȭ�е��� ������ �ΰ� ���� �ִ�. ��ȭ�� �ƴϰ�, ��� �ִ� �Ĺ� ����.");

						//������ ������� Ȯ��. ������ �ִٸ�~ **NPC ����Ʈ
						if (player.searchItem(map[1][1].getObject(2).getItem(1).getName()))
						{
							System.out.println("��ħ �ļ��뿡�� ����� ���� �ִ�. �̰ɷ� ���� �ٱ�?");
							System.out.println("1. ���� �ش�.");					
							System.out.println("2. ���� ���� �ʴ´�.");
							
							num = scan.nextInt();
							
							if (num == 1)
							{
								System.out.println("ȭ�е鿡 ���� ���. �Ĺ����� �⻵�ϵ� ��¦�Ÿ���.");
								player.deleteInventory(map[1][1].getObject(2).getItem(1));		//�κ��丮���� ������(����) ����
								
								//����Ʈ �Ϸ� ó��...
							}
							else if (num == 2)
							{
								System.out.println("�ٸ� ���� �� ���� �������� �𸥴�. �Ѿ��.");
							}
							else
							{
								System.out.println("���� ����, ���� ���� �� Ȯ���� ������.");	
							}
						}
					}
					else if (num == 2)		//���̺� �Ʒ��� ���캼 ���
					{
						// �̹� ������(��)�� ���������� �˻�
						// �������� �̹� �������ٸ� 
						if (player.searchItem(mapObject.getItem(0).getName()))
						{
							System.out.println("���̺� �Ʒ��� �� �̻� Ư���� �� ������ �ʴ´�.");
							continue;
						}
						
						// ������(��)�� �������� �ʾҴٸ�
						else {
							System.out.println("���̺� �Ʒ��� " + mapObject.getItem(0).getName() + " �ϳ��� �ִ�. ���� ����߷ȳ�?");
							
							System.out.println("��������?");
							System.out.println("1. ��������.");
							System.out.println("2. �������� ����.");
							
							num = scan.nextInt();
							
							if (num == 1)		//1. ��������. �� �������� ���
							{
								// �÷��̾� �κ��丮�� ������(��) ����
								player.saveInventory(mapObject.getItem(0));
								
								System.out.println("Ȥ�� �� ���� �������� �𸥴�. ��������.\n" + mapObject.getItem(0).getName() + "�� ì���.");
							}
							else if (num == 2)	//2. �������� �ʴ´�. �� �������� ���
							{
								System.out.println("�׳� ������ ����. ì���� ����.");
							}
							else				//������ �� ��ȣ �Է� ��
							{
								System.out.println("��� ���ڴ� ����? Ȯ���� ������.");
							}
						}
					}
					else if (num == 3)		//���̺� �� ���ڵ��� ���캼 ���
					{
						System.out.println("���̺��� �������� ���ڵ��� ������ ������ �ִ�.\n�ձ� ���ڿ� �׸� ���ڰ� ���� ���� �ִ�.");
						System.out.println("��ġ�� �ű��ϳ�. �� ���� ��ġ���� �پ��ִµ�, ���캼��?");
						System.out.println("1. ���캻��.");
						System.out.println("2. ���캸�� �ʴ´�.");
						
						num = scan.nextInt();
						
						if (num == 1)		//���캼 ���
						{
							System.out.println("--�Ĵ� ��ǽ� ��ġ��--");
							//ONTV
							System.out.println("������ ���ۡۡ� ������ ��ۡۡۡ�");
							System.out.println("��ۡۡۡ� ��ۡ�ۡ� �ۡۡ�ۡ� �ۡ�ۡ��");
							System.out.println("������ ��ۡۡ�� �ۡۡ�ۡ� �ۡۡ�ۡ�");
						}
						else if (num == 2)	//���캸�� ���� ���
						{
							System.out.println("�߿����� �ʾ� ���δ�. �׳� �Ѿ��.");
						}
						else				//������ ���� ��ȣ�� �Է��� ���
						{
							System.out.println("�� ����, ���� ���� ���� Ȯ���� ����.");
							
						}
					}
				}
			}
			else if (num == 2)		//��Ĵ� ����
			{
				while(true)
				{
					System.out.println("��Ĵ븦 ����ô�. \n�����ϰ� ������ ��Ĵ뿡�� 4���� ������� �÷��� �ִ�. �����?");
					System.out.println("1. ù ��° ���� �����.");
					System.out.println("2. �� ��° ���� �����.");
					System.out.println("3. �� ��° ���� �����.");
					System.out.println("4. �� ��° ���� �����.");
					System.out.println("5. ����� ����.");
					
					num = scan.nextInt();
					
					if (num == 1)		//ù ��° �� �����
					{
						System.out.println("ù ��° ���� ����� ����ô�.\n���Ͼ� ���� ��ܿ� ��� �ִ�.");
					}
					else if (num == 2)	//�� ��° �� �����
					{
						System.out.println("�� ��° ���� ����ô�.\n���� ����� ��ġ�� ��� �ִ�.");
					}
					else if (num == 3)	//�� ��° �� �����
					{
						System.out.println("�� ��° ���� ����ô�.\n� ���ڹ��� ��� �ִ�.");
					}
					else if (num == 4)	//�� ��° �� �����
					{
						System.out.println("������ ���� �� ��° ���� ����ô�.\nǪ�� ������ ��纣�� �����尡 ��� �ִ�.");
					}
					else if (num == 5)	//����� �ʰ� ��Ĵ� �����
					{
						System.out.println("�� �̻� ���캼 �� ���� �� ����.");
						break;	//�ݺ��� Ż��
					}
				}
			}
			else if (num == 3)		//�ļ��� ����
			{
				int waterNum = 0;	//�������� ���� ���� Ȯ�ο� ����
				
				while(true)
				{
					System.out.println("�ļ��븦 ����ô�.\n�ļ��뿡�� ���򺰷� �� ���� ���������� �ִ�. ���캼��?");
					System.out.println("1. ���� ���������� ���캸��.");
					System.out.println("2. Ǫ�� ���������� ���캸��.");
					System.out.println("3. �Ͼ� ���������� ���캸��.");
					System.out.println("4. � ���������� ���캸��.");
					System.out.println("5. �ļ��� ���� ���캸��.");
					System.out.println("6. �׸� ���캸��.");
					
					num = scan.nextInt();
					
					if (num == 1)		//���� �������� ����
					{
						if (waterNum == 1)		//������ ���� ��� (2��°)
						{
							System.out.println("���� ���������� ���ȴ�. ���� ���´�.");
							waterNum = 2;
						}
						else if (waterNum == 0)	//ù ��°�� ���� ư ���
						{
							System.out.println("���� ���������� ���ȴ�. ���� ���´�.");
							waterNum = 4;		//������ Ʋ�� ����̹Ƿ� ���� ��쿡�� �ٷ� else�� ���� �� �ֵ��� ��.
						}
						else					//������ Ʋ�� ���
						{
							System.out.println("���� ���������� ������ ��� ������������ ���� �����.");
							waterNum = 0;
						}
					}
					else if (num == 2)	//Ǫ�� �������� ����
					{
						if (waterNum == 3)		//������ ���� ��� (4��°)
						{
							//������(�����) ���������� Ȯ��
							//�������� ���
							if (player.searchItem(mapObject.getItem(0).getName()))
							{
								System.out.println("Ǫ�� ���������� ���ȴ�.\n��� ���� �������� �ļ��� �Ʒ����� �� �̻� �ƹ��͵� �������� �ʾҴ�.");
								waterNum = 0;
								continue;
							}
							//�������� �ʾ��� ���
							else
							{
								//���� �ùٸ��� �Է� -> ����� ȹ��
								System.out.println("Ǫ�� ���������� ���ȴ�.\n���� �ϴ� �Ҹ��� �Բ� ��� ���� ���߸� �ļ��� �Ʒ����� ���� ��������.");
								waterNum = 0;
								player.saveInventory(mapObject.getItem(0));
								//������� ì���.
								System.out.println("�ļ��� �Ʒ��� ���캸�� å �� ���� ������ �ִ�.\n" + mapObject.getItem(0).getName() + "�� ì���.");
							}
						}
						else if (waterNum == 0)	//ù ��°�� ���� ư ���
						{
							System.out.println("Ǫ�� ���������� ���ȴ�. ���� ���´�.");
							waterNum = 4;
						}
						else					//������ Ʋ�� ���
						{
							System.out.println("Ǫ�� ���������� ������ ��� ������������ ���� �����.");
							waterNum = 0;
						}
					}
					else if (num == 3)	//�Ͼ� �������� ����
					{
						if (waterNum == 0)		//������ ���� ��� (1��°)
						{
							System.out.println("�Ͼ� ���������� ���ȴ�. ���� ���´�.");
							waterNum = 1;
						}
						else
						{
							System.out.println("�Ͼ� ���������� ������ ��� ������������ ���� �����.");
							waterNum = 0;
						}
					}
					else if (num == 4)	//� �������� ����
					{
						if (waterNum == 2)		//������ ���� ��� (3��°)
						{
							System.out.println("� ���������� ���ȴ�. ���� ���´�.");
							waterNum = 3;
						}
						else if (waterNum == 0)	//ù ��°�� ���� ư ���
						{
							System.out.println("� ���������� ���ȴ�. ���� ���´�.");
							waterNum = 4;
						}
						else					//������ Ʋ�� ���
						{
							System.out.println("� ���������� ������ ��� ������������ ���� �����.");
							waterNum = 0;
						}
					}
					else if (num == 5)	//�ļ��� �� ����
					{
						System.out.println("�ļ��� ������ �� �ҵ��Ⱑ ���� �ִ�.");
						
						//������(����) ���������� Ȯ��
						//�������� ���
						if (player.searchItem(mapObject.getItem(1).getName()))
						{
							System.out.println("�� �̻� Ư���� �� ���� ���δ�.");
							continue;
						}
						//�������� �ʾ��� ���
						else
						{
							System.out.println("�ҵ��� ���� ���� ���� ��� ���� ���� �ִ�. ì���?");
							System.out.println("1. ì���.");
							System.out.println("2. ì���� �ʴ´�.");
							
							num = scan.nextInt();
							
							if (num == 1)		//������ ì���
							{
								player.saveInventory(mapObject.getItem(1));
								System.out.println("Ȥ�� �ʿ��� ���� �������� �𸥴�.\n" + mapObject.getItem(1).getName() + "�� ì���.");
							}
							else if (num == 2)	//������ ì���� �ʴ´�
							{
								System.out.println("���� ì�� �ʿ�� ���� ���δ�. �׳� ����.")	;
							}
							else				//������ �� ��ȣ �Է�
							{
								System.out.println("ì���� ������ Ȯ���� ������.");
							}
						}
					}
				}
			}
		}
	}
	
	// 2�� ����� �繫��(��2) �̺�Ʈ �Լ�
	// �Ĵ� ���ο� ����� �繫�� ����
	public void playFloor2_2()
	{
		// 1. ��Ʋ �ý��� Ȯ��
		
		// 2. ����� �繫�� ��ũ��Ʈ ���
		// ȸ�� �Ĵ� ������� �繫���̴�. �Ĵ� �� �ҿ� ��ġ�� �繫���� �����ϰ� �����Ǿ� �ִ� ����̴�.
		// ����� å�󿡴� ��ǻ�Ͱ� �� �� ���� �ְ�, �� �����δ� ������� ȭ��, �������� ������ ���鿡�� ȭ��Ʈ���尡 �ִ�.
		
		while(true)
		{
			// 2���� �� ��°(2) ���̹Ƿ� �μ��� 2, 2�� �������
			// �� ���� �̺�Ʈ (�繰(������Ʈ) ��� �� ���� ����)
			// enterRoom�� ��ȯ���� false�� ���� ���� �̺�Ʈ�� �����ϵ��� ��(�̵�)
			if (!enterRoom(2, 2))
			{
				break;
			}
			
			
			// enterRoom�� ��ȯ���� true�� ���
			// -> ���� ����
			if (num == 1)		//��ǻ�͸� ���캼 ���
			//2�� 2��° �� ȹ�� ������ : �����(0��° obj�� 0), �����(0��° obj�� 1), �ϱ���(1��° obj�� 0), �ϱ���(3��° obj�� 0)
			{
				System.out.println("å�� ���� �ִ� ��ǻ�͸� ����ô�.\n������ ����, ��й�ȣ�� �ɷ� �ִ� ���ȭ���� ���δ�.");
				System.out.println("��й�ȣ ��Ʈ : '�������� ������ �ϰŸ� ����!");
				System.out.println("��й�ȣ�� Ǯ���?");
				System.out.println("1. Ǯ���.");
				System.out.println("2. Ǯ�� �ʴ´�.");
				
				num = scan.nextInt();
				
				if (num == 1)		//��й�ȣ�� Ǯ�� �� ���
				{
					System.out.println("��й�ȣ�� Ǯ���.\n��й�ȣ�� �Է��Ͻÿ� : ");
					int password = scan.nextInt();
					
					if (password == 5419)	//��й�ȣ�� �°� �Է��� ���
					{
						System.out.println("��й�ȣ�� Ǯ�ȴ�.");
						System.out.println("�⺻ ȭ���� ���ȭ���� ���δ�. �� �� ���캼��?");
						System.out.println("1. ����ȭ���� ���캸��.");
						System.out.println("2. �������� ���캸��.");
						System.out.println("3. ���ٱ� ������ ���캸��.");
						System.out.println("4. �׸� ���캸��.");
						
						num = scan.nextInt();
						
						if (num == 1)		//����ȭ���� ���캼 ���
						{
							// �̹� ������(�����4)�� ���������� �˻�
							// �������� �̹� �������ٸ� 
							if (player.searchItem(im.getEasyDevilLanguage(3).getName()))
							{
								System.out.println("����ȭ�鿡 �� �̻� Ư���� �� ������ �ʴ´�.");
								continue;
							}
							//�������� �ʾҴٸ�
							else {
								System.out.println("����ȭ���� ���캸��.\n�ٷΰ���� ������ ���� SBA_4.pdf��� ������ �ִ�.");
								System.out.println("������ ����� '���� ���� �Ǹ���'��� ������ ������ ���δ�.");
								player.saveInventory(im.getEasyDevilLanguage(3));	//������(�����4)ȹ��
							}
						}
						else if (num == 2)	//�������� ���캼 ���
						{
							// �̹� ������(�����5)�� ���������� �˻�
							// �������� �̹� �������ٸ� 
							if (player.searchItem(im.getEasyDevilLanguage(4).getName()))
							{
								System.out.println("�����뿡 �� �̻� Ư���� �� ������ �ʴ´�.");
								continue;
							}
							//�������� �ʾҴٸ�
							else {
								System.out.println("�������� ���캸��.\n�������� ������ SBA_5.pdf��� ������ ���� �ִ°� ���δ�.");
								System.out.println("�������� '���� ���� �Ǹ���'��� ������ ������ ���ȴ�.");
								player.saveInventory(im.getEasyDevilLanguage(4));	//������(�����5)ȹ��
							}
						}
						else if (num == 3)	//���ٱ� ������ ���캼 ���
						{
							System.out.println("���ٱ� ������ ���캸��.\n������ ���� 5��_�Ĵ�.xlsx, 6��_�Ĵ�.xlsx... ���� ������ ���δ�.");
							System.out.println("������ ���� �ش� ���� �Ĵ�ǥ�� ���. �Ѱᰰ�� ������ �ν��� ��������̴�.");
						}
						else if (num == 4)	//�׸� ���캼 ���
						{
							System.out.println("��ǻ�ʹ� ���� �׸� ���캸��.");
						}
						else				//������ ���� ��ȣ�� �Է��� ���
						{
							System.out.println("�� �κ��� ���캼 ������ ����.");
						}
					}
					else					//�߸��� ��й�ȣ�� �Է����� ���
					{
						System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�.\n�ٽ� �����غ���.");
					}
				}
				else if (num == 2)	//��й�ȣ�� Ǯ�� ���� ���
				{
					System.out.println("��ǻ�ʹ� �׸� ���캸��.");
				}
				else				//������ ���� ��ȣ�� �Է��� ���
				{
					System.out.println("��й�ȣ�� Ǯ��, Ǯ�� ������ ������.");
				}
			}
			else if (num == 2)	//�������� ���캼 ���
			{
				System.out.println("�����忡�� ��й�ȣ�� �ɷ� �ִ�.");
				System.out.println("��ȣ�� ������ �������� ���δ�. \nŰ�е�� �Ʒ��� ���� ����̴�.\n12\n34\n56\n78\n");
				System.out.println("Ű�е� ���� ��� ����Ʈ���� �پ� �ִ�. '���� ���� �� �����̴�.' ��� ������ �����ִ�.");
				System.out.println("��й�ȣ�� Ǯ���?");
				System.out.println("1. Ǯ���.");
				System.out.println("2. Ǯ�� �ʴ´�.");
				
				num = scan.nextInt();
				
				if (num == 1)		//��й�ȣ�� Ǯ�� �� ���
				{
					System.out.println("��й�ȣ�� Ǯ���.\n� ��ư�� �Է��ұ�? : ");
					int password = scan.nextInt();
					
					if (password == 13458)
					{
						// �̹� ������(�ϱ���_2)�� ���������� �˻�
						// �������� �̹� �������ٸ� 
						if (player.searchItem(im.getDiary(1).getName()))
						{
							System.out.println("�����忡 �� �̻� Ư���� �� ������ �ʴ´�.");
							continue;
						}
						//�������� �ʾҴٸ�
						else {
							System.out.println("�������� ���ȴ�.\n����� �ȿ� ���� �ִ� ���� ��Ʈ�� ���δ�.");
							System.out.println("�ϱ����̴�.");
							player.saveInventory(im.getDiary(1));	//������(�ϱ���_2) ȹ��
						}
					}
					else
					{
						System.out.println("�������� ������ �ʴ´�.\n��й�ȣ�� Ʋ�� �� ����. �ٽ� �����غ���.");
					}
				}
				else if (num == 2)	//��й�ȣ�� Ǯ�� ���� ���
				{
					System.out.println("�������� �׸� ���캸��.");
				}
				else				//������ ���� ��ȣ�� �Է��� ���
				{
					System.out.println("��й�ȣ�� Ǯ��, Ǯ�� ������ ������.");
				}
			}
			else if (num == 3)	//ȭ���� ���캼 ���
			{
				System.out.println("å�� ���� â���� �� ������ ȭ�е��� ���� �ִ�.");
				System.out.println("ȭ���� ��ġ�� �龦�����Ѱ� Ư���� ������.");
				System.out.println("���캼��?");
				System.out.println("1. ���캻��.");
				System.out.println("2. ���캸�� �ʴ´�.");
				
				num = scan.nextInt();
				
				if (num == 1)		//���캼 ���
				{
					System.out.println("ȭ�е��� �ڼ��� ���캸�Ҵ�.");
					System.out.println("�� \n���\n�� \n ��");
					System.out.println("�̷��� ��ġ�� ȭ�е��� ���� �ִ�.");
				}
				else if (num == 2)	//���캸�� ���� ���
				{
					System.out.println("���� �߿����� �ʾ� ���δ�. ȭ���� �׸� ���캸��.");
				}
				else				//������ ���� ���ڸ� �Է��� ���
				{
					System.out.println("���캼��, ���캸�� ������ Ȯ���� ������.");
				}
			}
			else if (num == 4)	//�������� ���캼 ���
			{
				System.out.println("å�� ���� ���� ���� �������� �����Ⱑ ���� ���ִ�. �� �ڼ��� Ȯ���غ���?");
				System.out.println("1. Ȯ���غ���.");
				System.out.println("2. Ȯ������ �ʴ´�.");
				
				num = scan.nextInt();
				
				if (num == 1)		//Ȯ���� ���
				{	
					// �̹� ������(�ϱ���_3)�� ���������� �˻�
					// �������� �̹� �������ٸ� 
					if (player.searchItem(im.getDiary(2).getName()))
					{
						System.out.println("�����뿡 �� �̻� Ư���� �� ������ �ʴ´�.");
						continue;
					}
					//�������� �ʾҴٸ�
					else {
						System.out.println("�������� �� �������� ���� å�� ���Դ�.");
						System.out.println("�ϱ����̴�.");
						player.saveInventory(im.getDiary(2));	//������(�ϱ���_3) ȹ��
					}
				}
				else if (num == 2)	//Ȯ������ ���� ���
				{
					System.out.println("�������� �׸� ���캸��.");
				}
				else				//������ ���� ���ڸ� �Է��� ���
				{
					System.out.println("Ȯ������, Ȯ������ ������ Ȯ���� ������.");
				}
			}
			else if (num == 5)	//ȭ��Ʈ ���带 ���캼 ���
			{
				System.out.println("���鿡 �ɸ� ȭ��Ʈ ���忡�� �Ĵ� ������ ����� ������ ������� ���� �ִ�.");
				System.out.println("������ ���̴� �Ĵ��� ����ؼ� Ư���� ������ ���� ���̴µ�... �ڼ��� �о��?");
				System.out.println("1. �о��.");
				System.out.println("2. ���� �ʴ´�.");
				
				num = scan.nextInt();
				
				if (num == 1)		//�о ���
				{
					System.out.println("�ڼ��� �о��.\n'ȭ�� A�� ����Ḧ Ȱ���� �ΰ��鿡�� �Կ�����, rnxh�� enxhd�� aptmRjdna�� �� ������ ������.'��� ������ �����ִ�.");
					System.out.println("......���� ������?");	
				}
				else if (num == 2)	//���� ���� ���
				{
					System.out.println("ȭ��Ʈ ����� �׸� ���캸��.");
				}
				else				//������ ���� ���ڸ� �Է��� ���
				{
					System.out.println("������, ���� Ȯ���� ������.");
				}
			}
		}		
	}
	
	// 2�� ȭ���(posID: 24) �̺�Ʈ �Լ�
	public void playFloor2_4()
	{
		while(true)
		{
			// 2���� �� ��°(4) ��(ȭ���)�̹Ƿ� �μ��� 2, 4�� ������� (2���� ���� ȭ��� ���� 3�� ��������, ���ϼ��� ���� 4�� ����)
			// �� ���� �̺�Ʈ (�繰(������Ʈ) ��� �� ���� ����)
			// enterRoom�� ��ȯ���� false�� ���� ���� �̺�Ʈ�� �����ϵ��� ��(�̵�)
			if (!enterRoom(2, 4))
			{
				break;
			}
			
			// enterRoom�� ��ȯ���� true�� ���
			// -> ���� ����
			
			//System.out.println("����ϰ� û�ҵ� ȭ��ǿ����� ������ ���� ����.\n� �� �ұ�?");
			//System.out.println("1. �ſ��� ����");
			//System.out.println("2. �ٴ��� ����");
			//System.out.println("3. ������ ����");
			//System.out.println("4. ������");
			
			//num = scan.nextInt();
			
			if (num == 1)		// �ſ� ����
			{
				System.out.println("�� ����̴�.");
				System.out.println("* hp: " + player.getHp());
				System.out.println("* ���ݷ�: " + player.getAttackPower());
				System.out.println("* ����: " + player.getDefensivePower());
				System.out.println("* ���ǵ�: " + player.getReputation());
			}
			
			else if (num == 2)	// �ٴ� ����
			{
				System.out.println("���������ϰ� ���� �Ͼ� Ÿ�Ͽ� ���Ⱑ ���� �ִ�. �Ѿ����� �ʵ��� ��������.");			
			}
			
			else if (num == 3)	// �� ���� ����(�κ��丮, ���� �������� ����Ʈ Ȯ��)
			{
				System.out.println("� �� ����?");
				System.out.println("1. ���� ����");	// �κ��丮 Ȯ��
				System.out.println("2. �� �� ���");	// ����Ʈ ��� Ȯ��
				
				num = scan.nextInt();
				
				if (num == 1)			// �κ��丮 Ȯ��
				{
					player.showInventory();
				}
				else if (num == 2)		// ����Ʈ Ȯ��
				{
					// ���� �÷��̾ ���� ���� ����Ʈ ��� ���
					player.printQuestList();
				}
				else					// ������
				{
					System.out.println("�װ�... �߸��� �����̴�.");
				}
			}
			else if (num == 4)	//�κ�� ������
			{
				player.setPosID(20);
				break;
			}
			else				//������ ���� ���ڸ� �Է��� ��� (������)
			{
				System.out.println("�װ� ȭ��ǿ��� �� �� ����.");
				
			}
		}
	}
	
	
	// * * * * * * * * * * * * * \\
	
	
	// 3�� �κ� �̺�Ʈ �Լ�
	public void playFloor3_0()
	{
		while(true)
		{
			// 1. �κ� ��ũ��Ʈ ���
			// �κ��! (��ũ��Ʈ ���� �ʿ�)
			
			// 2. ������ ���
			System.out.println("1. ���Ա⿡ ������� ����.");	// ����
			System.out.println("2. ��� �ۿ��� ���� ����.");	// Ÿ��Ʋ�� ������ (���� �޴�)
			System.out.println("3. �ٸ� ���� �ѷ�����.");		// �ٸ� �� �̵�
			System.out.println("4. ���������͸� Ÿ��.");		// �� �̵�
			
			num = scan.nextInt();
			
			if (num == 1)			// ����
			{
				// ���� ���� ������ ���ÿ� ����
				
				System.out.println("������� ���������� �����!\n--����Ǿ����ϴ�.--");
			}
			else if (num == 2)		// ���� �� Ÿ��Ʋ�� ������
			{
				// 1. ���� ������ ���ÿ� ����
				
				// 2. Ÿ��Ʋ�� ������
				goTitle = true;
				break;
			}
			else if (num == 3)		// �ٸ� ������ �̵�
			{
				System.out.println("���߽�1, ���߽�2, ������� ���δ�. ���� ����?");
				
				System.out.println("1. ���߽�1�� ����.");
				System.out.println("2. ���߽�2�� ����.");
				System.out.println("3. ����ǿ� ����.");
				System.out.println("4. ȭ��ǿ� ����.");
				System.out.println("5. ������ �޶�����.");		// �ٽ� �κ� ������ ���
				
				num = scan.nextInt();
				
				if (num == 1)		// ���߽�1 ����
				{
					player.setPosID(31);
					break;
				}
				else if (num == 2)	// ���߽�2 ����
				{
					player.setPosID(32);
					break;
				}
				else if (num == 3)	// ����� ����
				{
					player.setPosID(33);
					break;
				}
				else if (num == 4)	// ȭ��� ����
				{
					player.setPosID(34);
					break;
				}
				else if (num == 5)	// �ٸ� ������ ���� �ʱ�(�κ� ��ũ��Ʈ/������ �ٽ� ���)
				{
					continue;
				}
				else				// �� �� ������ ó��
				{
					System.out.println("��ü ���� ���� ���� �ǰ�?");
				}
			}
			else if (num == 4)		// �ٸ� ������ �̵�(���������� ž��)
			{
				moveFloor();
				break;
			}
			else					// �� �� ������ ó��
			{
				System.out.println("�� ����, �߸��� �� ������.");
			}
		}
	}
	
	// 3�� ���߽�1(��1) �̺�Ʈ �Լ�
	public void playFloor3_1()
	{
		// 1. ��Ʋ....Ȯ�� ������
		
		// 2. ���߽�1 ��ũ��Ʈ ���...
		System.out.println("���߽�1�̴�.");
		System.out.println("��򰡿��� �ö���� ������ ������ �ڸ� �񷶴�.");
		System.out.println("�̷� ���� ���� �ִٰ� �� �ʿ��� ������ �� �� ����. �� Ȯ���ϰ� ������.");
		
		while(true)
		{
			// 3���� ù ��°(1) ���̹Ƿ� �μ��� 3, 1�� �������
			// �� ���� �̺�Ʈ (�繰(������Ʈ) ��� �� ���� ����)
			// enterRoom�� ��ȯ���� false�� ���� ���� �̺�Ʈ�� �����ϵ��� ��(�̵�)
			if (!enterRoom(3, 1))
			{
				break;
			}
			
			
			// enterRoom�� ��ȯ���� true�� ���
			// -> ���� ����
			if (num == 1)			// å�� ����
			{
				// ����, USB, ���̹���, �Դ� ���� ���ڹ�, ���� ��ø, ����(�����6) ���� ����
				System.out.println("å�� ������ �⵿��ϵ��� ���� �ְ�, �ؿ��� ������ �ִ�.");
				System.out.println("������ ���캼��?");
				
				/*
				 * ����....���� �ʿ� *
				 */
				
				// USB�� �Դ� ���� ���ڹٸ� ���������� �˻�
				// �� �� ������ ���
				if (player.searchItem(mapObject.getItem(1).getName()) && player.searchItem(mapObject.getItem(3).getName()))
				{
					System.out.println("1. ����");
					System.out.println("2. ���̹���");
					System.out.println("3. ���� ��ø");
					System.out.println("4. ����");
					
					num = scan.nextInt();
					
					if (num == 2)		// 2. ���̹��� ����
					{
						num = 3;	// �⺻ ��¿����� ������ ��ȣ�� ����(3. ���̹���)
					}
					else if (num == 3)	// 3. ���� ��ø ����
					{
						num = 5;	// ���� ����(5. ���� ��ø)
					}
					else if (num == 4)	// 4. ���� ����
					{
						num = 6;	// ���� ����(6. ����)
					}
				}
				// USB�� ������ ���
				else if (player.searchItem(mapObject.getItem(1).getName()))
				{
					System.out.println("1. ����");
					System.out.println("2. ���̹���");
					System.out.println("3. �Դ� ���� ���ڹ�");
					System.out.println("4. ���� ��ø");
					System.out.println("5. ����");
					
					num = scan.nextInt();
					
					if (num >= 2)
					{	// �⺻ ��¿����� ������ ��ȣ�� ����
						num++;
					}
				}
				// �Դ� ���� ���ڹٸ� ������ ���
				else if (player.searchItem(mapObject.getItem(3).getName()))
				{
					System.out.println("1. ����");
					System.out.println("2. USB");
					System.out.println("3. ���̹���");
					System.out.println("4. ���� ��ø");
					System.out.println("5. ����");
					
					num = scan.nextInt();
					
					if (num >= 4)
					{	// �⺻ ��¿����� ������ ��ȣ�� ����
						num++;
					}
				}
				// �� �� �������� ���� ��� (�⺻ ���)
				else
				{
					// 1. ���� ~ 5. ���� ��ø ������ ���
					for (int i = 0; i < mapObject.getAllItem().length - 1; i++)
						System.out.println((i + 1) + ". " + mapObject.getItem(i));
					// 6. ���� ���
					System.out.println("6. ����");
					
					num = scan.nextInt();
				}
				
				
				if (num == 1 || num == 3 || num == 5)		// ����, ���̹���, ���� ��ø �����
				{
					// �ش� ������ �������� ���� ���
					System.out.println(mapObject.getItem(num - 1).getDescription());
				}
				else if (num == 2 || num == 4 || num == 6)	// USB, �Դ� ���� ���ڹ�, ���� �����
				{
					if (num == 6)	// ���� ����
					{
						// �����6�� �̹� ŉ���ߴٸ�
						if (player.searchItem(im.getEasyDevilLanguage(5).getName()))
						{
							System.out.println("���� �ƹ� �͵� ������� �ʴ�.");
							
							continue;	// ������Ʈ ������� ���ư�(enterRoom)
						}
						
						// ���� ŉ������ �ʾҴٸ�
						System.out.println("���� ���� �Ǹ���6�� ����ִ�.");
						System.out.println("��������?");
						System.out.println("1. ��������.");
						System.out.println("2. ������.");
						
						num = scan.nextInt();
						
						if (num == 1)		// ��������
						{
							// �κ��丮�� �����6 ����
							player.saveInventory(im.getEasyDevilLanguage(5));
							
							System.out.println("���� ���� �Ǹ���6�� ì���.");
						}
						else if (num == 2)	// ���α�
						{
							System.out.println("å�� �ǵ��� �ʰ� ���ڸ��� �״�� �ξ���.");
						}
						else				// ������
						{
							System.out.println("�ƹ��� ���õ� ���� ���ߴ�..");
						}
					}
				}
				else										// ������
				{
					System.out.println("Ȯ���ϰ� ��������.");
				}
				
			}
			else if (num == 2)		// ���� ��Ʈ�� ����
			{
				// ���ͳ�, '������' ����, ������, �� ��ǻ��(�ϱ���) ���� ����
				System.out.println("ȭ���� �� �ΰ� �� �� ����. �׷� �� �ð� �����̳� �����ִ� ����..?");
				System.out.println("��� ���캼��?");
				
				// 1. ���ͳ� ~ 3. ������ ������ ���
				for (int i = 0; i < mapObject.getAllItem().length - 1; i++)
					System.out.println((i + 1) + ". " + mapObject.getItem(i).getName());
				// [������ ��ȣ]. �� ��ǻ�� ���
				System.out.println(mapObject.getAllItem().length + ". �� ��ǻ��");
				
				num = scan.nextInt();
				
				if (num >= 1 && num <= 3)	// ���ͳ�~������ ����
				{	
					// �� �������� ���� ���
					System.out.println(mapObject.getItem(num - 1).getDescription());
				}
				else if (num == 4)			// �� ��ǻ�� ����
				{
					// USB�� ���� ���� ���
					if (player.searchItem("USB"))
					{
						System.out.println("���� �ִ� USB�� �ȾҴ�.");
						System.out.println("� ������ ������� Ȯ���� ����?");
						System.out.println("1. Ȯ������.");
						System.out.println("2. Ȯ������ ����.");
						
						num = scan.nextInt();
						
						if (num == 1)		// Ȯ���ϱ�
						{
							// �ϱ���(4)�� ����ִ�.
							System.out.println(im.getDiary(3).getName() + "�� ����ִ�.");
							// �ϱ����� ���� ���
							System.out.println(im.getDiary(3).getDescription());
							
							// ���� �ϱ����� ����ؼ� ��� ���� ������, �׳� USB�� ����� �������� ���� �ʿ�
							
							// �ϴ� �ӽ÷� �÷��̾� �κ��丮�� �ϱ��� ������
							player.saveInventory(im.getDiary(3));
						}
						else if (num == 2)	// Ȯ�� �� �ϱ�
						{
							System.out.println("���� ����� �ʾҴ�.");
						}
						else				// ������
						{
							System.out.println("������ Ȯ���ϰ� �ؾ��Ѵ�.");
						}
					}
				}
			}
		}
	}
	
	// 3�� ���߽�2(��2) �̺�Ʈ �Լ�
	public void playFloor3_2()
	{
		// 1. ��Ʋ....Ȯ�� ������
		
		// 2. ���߽�2 ��ũ��Ʈ ���...
		System.out.println("���߽�2��.");
		System.out.println("å��, ���̿�, ���̸� �����ϴ� �͵鸸 �ܶ� �־� ��ĩ ��������� ���� �־�������");
		System.out.println("�������� �ϳ� �����ϴ�. �׷��ٰ� ��û �����ϴٴ� �� �ƴϴ�.");
		
		while(true)
		{
			// 3���� �� ��°(2) ���̹Ƿ� �μ��� 3, 2�� �������
			// �� ���� �̺�Ʈ (�繰(������Ʈ) ��� �� ���� ����)
			// enterRoom�� ��ȯ���� false�� ���� ���� �̺�Ʈ�� �����ϵ��� ��(�̵�)
			if (!enterRoom(3, 2))
			{
				break;
			}
			
			
			// enterRoom�� ��ȯ���� true�� ���
			// -> ���� ����
			if (num == 1)		// å�� ����
			{
				System.out.println("å�� ������ ���� ���ǵ��� �����Ǿ� �ִ�.");
				System.out.println("� �ͺ��� ����?");
				// 1. ���� ~ 4. �޷�
				for (int i = 0; i < mapObject.getAllItem().length; i++)
					System.out.println((i + 1) + ". " + mapObject.getItem(i).getName());
				
				num = scan.nextInt();
				
				if (num >= 1 && num <= 4)	// ����, ���� ����, �ð�, �޷� ����
				{
					// �ش� ������ ���� ���
					System.out.println(mapObject.getItem(num - 1));
				}
				else						// ������
				{
					System.out.println("����� ��������.");
				}
			}
			else if (num == 2)	// ����� ����
			{
				System.out.println("�� ���� ��ư�� ������������ ���� ���´�.");
				System.out.println("� �ͺ��� ����?");
				// 1. ����¹�ư ~ 3. ��ĵ��ư
				for (int i = 0; i < mapObject.getAllItem().length; i++)
					System.out.println((i + 1) + ". " + mapObject.getItem(i).getName());
				
				num = scan.nextInt();
				
				if (num >= 1 && num <= 3)	// ����¹�ư, ����������, ��ĵ��ư ����
				{
					// �ش� ������ ���� ���
					System.out.println(mapObject.getItem(num - 1));
				}
				else						// ������
				{
					System.out.println("�׷��� �� ���� ����.");
				}
			}
			else if (num == 3)	// å���� ����
			{
				System.out.println("� �ͺ��� ����?");
				// 1. �Ҽ�å ~ 2. ����
				for (int i = 0; i < mapObject.getAllItem().length - 1; i++)
					System.out.println((i + 1) + ". " + mapObject.getItem(i).getName());
				// 3. �ݰ�
				System.out.println("3. �ݰ�");
				
				num = scan.nextInt();
				
				/*
				 * �Ҽ�å �����ؼ��� ���� �� ���� �ʿ�..
				 * �켱�� �ӽ÷� ������ �����ϰ� �����
				 */
				
				if (num == 1 || num == 2)	// �Ҽ�å, ���� ����
				{
					// �ش� ������ ���� ���
					System.out.println(mapObject.getItem(num - 1));
				}
				else if (num == 3)			// �ݰ� ����
				{
					// �ϱ���(5)�� �̹� �������ٸ�
					if (player.searchItem(im.getDiary(4).getName()))
					{
						System.out.println("�ݰ�� �����ִ� ä�� �����ִ�.");
						System.out.println("�ȿ��� ���̻� �ƹ� �͵� �������� �ʴ´�.");
						
						continue;
					}
					
					// ���� �������� �ʾҴٸ�
					System.out.println("������־� ������ �ʴ´�.");
					System.out.println("������ 'PASSWORD: This year'�̶�� ���� ����Ʈ���� �ִ�.");
					System.out.println("��й�ȣ�� �Է��� ����.");
					System.out.println("* ��й�ȣ�� ���� �빮���̴�.");
					
					String str = scan.next();
					
					if (str.equals("DRUG"))		// ������ �Է����� ���
					{
						System.out.println("(ö��)");
						System.out.println("���� ���ȴ�!");
						// �� �ȿ��� �ϱ����� �־���.
						System.out.println("�� �ȿ��� " + im.getDiary(4).getName() + "�� �־���.");
						
						System.out.println("��������?");
						System.out.println("1. ��������.");
						System.out.println("2. ������.");
						
						num = scan.nextInt();
						
						if (num == 1)			// ��������
						{
							// �κ��丮�� �ϱ���(5) ����
							player.saveInventory(im.getDiary(4));
							
							System.out.println(im.getDiary(4).getName() + "�� ì���.");
						}
						else if (num == 2)		// ���α�
						{
							System.out.println("�ǵ��� �ʰ� �״�� �ξ���.");
						}
						else					// ������
						{
							System.out.println("�߸��� �����̴�.");
						}
					}
					
				}
				else						// ������
				{
					System.out.println("�׷��� �� ���� ����.");
				}
			}
		}
	}
	
	// 3�� �����(��3) �̺�Ʈ �Լ�
	public void playFloor3_3()
	{
		// 1. ��Ʋ....(NPC�������� ����?)
		
		// 2. ����� ��ũ��Ʈ ���...
		System.out.println("������̴�.");
		System.out.println("���� ���Ⱑ �� ���� �ɵ���.");
		System.out.println("�ƹ��� �� ȸ�翡 �ٴϸ鼭 1���ۿ� �� �ٳ�ô����� �̷��� �ͼ����� ���� ���� ó���̴�.");
		System.out.println("ó�� �� ���̶� ����� �� �翬�� ���ε� ���� �𸣰� �������� ��� ������ ����.");
		
		while(true)
		{
			// 3���� �� ��°(3) ���̹Ƿ� �μ��� 3, 3�� �������
			// �� ���� �̺�Ʈ (�繰(������Ʈ) ��� �� ���� ����)
			// enterRoom�� ��ȯ���� false�� ���� ���� �̺�Ʈ�� �����ϵ��� ��(�̵�)
			if (!enterRoom(3, 3))
			{
				break;
			}
			
			
			// enterRoom�� ��ȯ���� true�� ���
			// -> ���� ����
			if (num == 1)		// [�繰]å�� ����
			{
				// 1. �跮�� ��Ǭ ~ 2. ���� ������ ���
				for(int i = 0; i < mapObject.getAllItem().length - 1; i++)
					System.out.println((i + 1) + ". " + mapObject.getItem(i));
				
				// ��Ʈ(���� ���� �Ǹ���7)�� ���� �������� �ʾҴٸ�
				if (!player.searchItem(im.getEasyDevilLanguage(6).getName()))
				{
					// ���� ������ ���(3. ��Ʈ)
					System.out.println("3. " + mapObject.getItem(2).getName());
				}
				// �������ٸ� ��Ʈ�� ���X
				
				// ������ ������ �Է� �ޱ�
				num = scan.nextInt();
				
				if (num == 1 || num == 2)	// [������]�跮�� ��Ǭ �Ǵ� ���� ����
				{
					System.out.println(mapObject.getItem(num - 1).getDescription());
				}
				else if (num == 3)			// [������]��Ʈ ����
				{
					// ���� �����7�� �̹� ������ ���¶��
					if (player.searchItem(im.getEasyDevilLanguage(6).getName()))
					{
						System.out.println("�׷� �� �������� �ʴ´�.");
						continue;
					}
					
					// ���� �������� �ʾҴٸ� ���� ����
					System.out.println("������ �� ���� �� ����.");
					System.out.println("��������?");
					System.out.println("1. ��������.");
					System.out.println("2. �������� ����.");
					
					num = scan.nextInt();
					
					if (num == 1)		// ��������
					{
						// ��Ʈ(�����7) �κ��丮�� ����
						player.saveInventory(im.getEasyDevilLanguage(6));
						
						System.out.println("�� ���濡 �ٷ� �־���.");
						System.out.println("�ڼ��� ������ ���߿� ������ ���� Ȯ���� ����.");
					}
					else if (num == 2)	// ���α�
					{
						 System.out.println("�׳� �״�� �ξ���.");
					}
					else				// ������
					{
						System.out.println("Ȯ���ϰ� �����ϴ� ���� ���� ���̴�.");
					}
				}
				else						// [������ ������ ��]������
				{
					System.out.println("�׷� �������� �־���?");
					System.out.println("�ٽ� ���캸��.");
				}
			}
			else if (num == 2)	// [�繰]���� �м� ��� ����
			{
				// NPC�� ��ȯ �������� ��� �Ǵ� (���...?)
				if (map[2][3].getEnemy(0) == null)
				{
					System.out.println("�� �ʿ��� ����..�� �� �ְ�, �� �ʿ��� ���� �м� ��Ⱑ �ִ�.");
					System.out.println("��� ���� ���캼��?");
					
					System.out.println("1. ����..���� ���� ����.");
					System.out.println("2. ���� �м� ��⸦ ��������.");
					
					num = scan.nextInt();
					
					if (num == 1)		// ����NPC ����
					{
						// ����Ʈ(���) �Ϸ� �ܰ迡 ���� NPC�� ��ũ��Ʈ ���
						System.out.println(map[2][3].getNpc().playQuestScript(map[2][3].getNpc().playQuest(player)));
						
						// ���� ������ ����Ʈ�� �Ϸ��� �Ķ��
						// (����Ʈ ��ũ��Ʈ�� �ε����� ������ �Ϸ� ��Ʈ�� ����ϰ� �ִ� ���¶��)
						if (map[2][3].getNpc().getQuestScriptCount() + 1 == map[2][3].getNpc().getQuestScripts().length - 1)
						{
							continue;
						}
						
						// ���� ����Ʈ�� �Ϸ��ߴٸ�
						if (map[2][3].getNpc().getQuest(map[2][3].getNpc().getQuestCount()).getCompletion())
						{
							// �÷��̾�� ���� ����(�κ��丮�� ���� ������ ����)
							player.saveInventory(map[2][3].getNpc().getQuest(map[2][3].getNpc().getQuestCount()).getReward());
						}
					}
					else if (num == 2)	// [�繰]���� �м� ��� ����
					{
						System.out.println("���𰡸� �м��� �� ���� �� ����.");
						
						// �м� �ⱸ ������ ���� ���� ���� ���
						if (player.searchItem("�м� �ⱸ ����"))
						{
							System.out.println("�׷��� ����ϴ� ����� �� �𸣰ڴ�.");
							System.out.println("���Ҹ� �ǵ��� ����.");
							
							continue;
						}
						
						// ���� ���� ��� - ��������
						System.out.println("� ���� �м��� ����?");
						// player�� �κ��丮 ���
						player.showInventory();
						System.out.print("* �м��ϰ� ���� ���� �̸��� ��Ȯ�ϰ� ����. : ");
						
						String answer = scan.next();
						
						if (answer.equals("������ ����") || answer.equals("������ ��"))
						{
							System.out.println("U ������ ����ƴ�.");
						}
						else	// �� �� ���
						{
							System.out.println("�װ� �м��� �� ����.");
						}
					}
					else				// ������
					{
						System.out.println("�ȹٷ� ������ �ϴ� ���� ���ƺ��δ�.");
					}
					
					continue;
				}
				
				// NPC�������͸� óġ���� ���(NPC�� ��ȯ���� ������ ���)
				System.out.println("���𰡸� �м��� �� ���� �� ����.");
				
				// �м� �ⱸ ������ ���� ���� ���� ���
				if (player.searchItem("�м� �ⱸ ����"))
				{
					System.out.println("�׷��� ����ϴ� ����� �� �𸣰ڴ�.");
					System.out.println("���Ҹ� �ǵ��� ����.");
					
					continue;
				}
				
				// ���� ���� ��� - ��������
				System.out.println("� ���� �м��� ����?");
				// player�� �κ��丮 ���
				player.showInventory();
				System.out.print("* �м��ϰ� ���� ���� �̸��� ��Ȯ�ϰ� ����. : ");
				
				String answer = scan.next();
				
				if (answer.equals("������ ����") || answer.equals("������ ��"))
				{
					System.out.println("U ������ ����ƴ�.");
				}
				else	// �� �� ���
				{
					System.out.println("�װ� �м��� �� ����.");
				}
			}
			else if (num == 3)	// [�繰]���� ����
			{
				System.out.println("� ���� ���캼��?");
				// 1. ������~ 3. ���� ���
				for (int i = 0; i < mapObject.getAllItem().length - 1; i++)
					System.out.println((i + 1) + ". " + mapObject.getItem(i));
				// 4. ������ ���(������� �繰�� ���� �����Ƿ� ���� �����)
				System.out.println("4. ������");
				
				num = scan.nextInt();
				
				if (num >= 1 && num <= 3)	// [������]������ ~ ���� ����
				{
					// �ش� ������ ���� ���
					System.out.println(mapObject.getItem(num - 1).getDescription());
				}
				else if (num == 4)			// ������ ����
				{
					// �̹� ������ ������ �˻�(������ ���縦 ��� �ִ��� �˻�)
					if (player.searchItem(mapObject.getItem(3).getName()))
					{
						System.out.println("���⿡ ���̻� �� ���� ����.");
						continue;
					}
					
					// ���� ������ �� ���� ���,, ���� ����
					System.out.println("����ü ȸ�翡 �̷� �� �� �ִ°��� �𸣰ڴ�.");
					System.out.println("���� ��۱� �ƴϾ���?");
					System.out.println("������ ������ A ������ 20g �÷��� �ִ�.");
					System.out.println("���⿡ �� �� D ������ �� �־� ����?");
					System.out.print("* �� �׷��� ������ ���ڷθ� ����. : ");
					
					num = scan.nextInt();
					
					if (num == 40)		// ���� �Է� (D���� - 40g)
					{
						System.out.println("���������� H ������ �� �־ �� ���� �� ����.");
						System.out.println("�󸶳� ������?");
						System.out.print("* �� �׷��� ������ ���ڷθ� ����. : ");
						
						num = scan.nextInt();
						
						if (num == 30)		// ���� �Է� (H���� - 30g)
						{
							System.out.println("������ ���簡 ���������!");
							System.out.println("Ȥ�� �𸣴ϱ� ��� ������.");
							
							// �κ��丮�� ������ ���� �ֱ�
							player.saveInventory(mapObject.getItem(3));
						}
						else				// ���� �Է�
						{
							System.out.println("����.. �߸��� ������ �� �� ����.");
							System.out.println("ó������ �ٽ� �����غ���.");
						}
					}
					else				// �� �� ����
					{
						System.out.println("�ֱ� ������ �߸��� ������ �� ���� ������ �����.");
						System.out.println("ó������ �ٽ� �����غ���.");
					}
				}
				else						// ������
				{
					System.out.println("�׷� �� ����.");
				}
			}
		}
	}
	
	// 3�� ȭ���(posID: 34) �̺�Ʈ �Լ�
	public void playFloor3_4()
	{
		// ȭ��� ��ũ��Ʈ ���...
		System.out.println("ȭ��ǿ� ���Դ�.");
		
		
		while(true)
		{
			// 3���� �� ��°(4) ��(ȭ���)�̹Ƿ� �μ��� 1, 4�� �������
			// �� ���� �̺�Ʈ (�繰(������Ʈ) ��� �� ���� ����)
			// enterRoom�� ��ȯ���� false�� ���� ���� �̺�Ʈ�� �����ϵ��� ��(�̵�)
			if (!enterRoom(3, 4))
			{
				break;
			}
			
			
			// enterRoom�� ��ȯ���� true�� ���
			// -> ���� ����
			if (num == 1)		// �ſ� ����
			{
				System.out.println("�� ����̴�.");
				System.out.println("* hp: " + player.getHp());
				System.out.println("* ���ݷ�: " + player.getAttackPower());
				System.out.println("* ����: " + player.getDefensivePower());
				System.out.println("* ���ǵ�: " + player.getReputation());
			}
			else if (num == 2)	// �ٴ� ����
			{
				System.out.println("������ �׷����� ������ �ٴ��̴�.");
				System.out.println("�ణ ���� �� ���⵵.");
					
			}
			else if (num == 3)	// �� ���� ����(�κ��丮, ���� �������� ����Ʈ Ȯ��)
			{
				System.out.println("� �� ����?");
				System.out.println("1. ���� ����");		// �κ��丮 Ȯ��
				System.out.println("2. �� �� ���");	// ����Ʈ ��� Ȯ��
				
				num = scan.nextInt();
				
				if (num == 1)			// �κ��丮 Ȯ��
				{
					player.showInventory();
				}
				else if (num == 2)		// ����Ʈ Ȯ��
				{
					// ���� �÷��̾ ���� ���� ����Ʈ ��� ���
					player.printQuestList();
				}
				else					// ������
				{
					System.out.println("�װ�... �߸��� �����̴�.");
				}
			}
		}
	}
	
	
	// * * * * * * * * * * * * * \\
	
	
	// 4�� �κ� �̺�Ʈ �Լ�
	public void playFloor4_0()
	{
		while(true)
		{
			// 1. �κ� ��ũ��Ʈ ���
			// �����ο� ��ȭ���� �ִ� 4���� �κ��̴�.
			
			// 2. ������ ���
			System.out.println("1. ���Ա⿡ ������� ����.");	// ����
			System.out.println("2. ��� �ۿ��� ���� ����.");	// Ÿ��Ʋ�� ������ (���� �޴�)
			System.out.println("3. �ٸ� ���� �ѷ�����.");		// �ٸ� �� �̵�
			System.out.println("4. ���������͸� Ÿ��.");		// �� �̵�
			
			num = scan.nextInt();
			
			if (num == 1)			// ����
			{
				// ���� ���� ������ ���ÿ� ����
				
				System.out.println("������� ���������� �����!\n--����Ǿ����ϴ�.--");
			}
			else if (num == 2)		// ���� �� Ÿ��Ʋ�� ������
			{
				// 1. ���� ������ ���ÿ� ����
				
				// 2. Ÿ��Ʋ�� ������
				goTitle = true;
				break;
			}
			else if (num == 3)		// �ٸ� ������ �̵�
			{
				// �繫��, ȸ�ǽ�, ��ȭ���� ���δ�. ���� ����?
				System.out.println("1. �繫�Ƿ� ����.");
				System.out.println("2. ȸ�ǽǷ� ����.");
				System.out.println("3. ��ȭ�Ƿ� ����.");
				System.out.println("4. ������ �޶�����.");		// �ٽ� �κ� ������ ���
				
				num = scan.nextInt();
				
				if (num == 1)		// �繫�� ����
				{
					player.setPosID(41);
					break;
				}
				else if (num == 2)	// ȸ�ǽ� ����
				{
					player.setPosID(42);
					break;
				}
				else if (num == 3)	// ��ȭ�� ����
				{
					player.setPosID(43);
					break;
				}
				else if (num == 4)	// �ٸ� ������ ���� �ʱ�(�κ� ��ũ��Ʈ/������ �ٽ� ���)
				{
					continue;
				}
				else
				{
					// �� �� ������ ó��
				}
			}
			else if (num == 4)		// �ٸ� ������ �̵�(���������� ž��)
			{
				moveFloor();
				break;
			}
			else
			{
				// �� �� ������ ó��
			}
		}
	}
	
	// 4�� �繫��(��1) �̺�Ʈ �Լ�
	public void playFloor4_1()
	{
		// 1. ��Ʋ �ý��� Ȯ��
		
		// 2. �繫�� ��ũ��Ʈ ���
		// �������� �繫���̴�. ����� �� �� �� �� ��, �δ��� ���Ⱑ �ɵ���.
		// å����� �� ����� �ְ�, �������� ���� ������� ���õǾ� �ִ�. ������ �Խ����� �پ� �ִ�.
		
		while(true)
		{
			// 4���� ù ��°(1) ���̹Ƿ� �μ��� 4, 1�� �������
			// �� ���� �̺�Ʈ (�繰(������Ʈ) ��� �� ���� ����)
			// enterRoom�� ��ȯ���� false�� ���� ���� �̺�Ʈ�� �����ϵ��� ��(�̵�)
			if (!enterRoom(4, 1))
			{
				break;
			}
			
			
			// enterRoom�� ��ȯ���� true�� ���
			// -> ���� ����
			
			if (num == 1)		// å�� ����
			//4�� 1��° �� ȹ�� ������ : �����(1��° obj�� 0), �Դ� ���� Ŀ��(2��° obj�� 0)
			{
				while(true)
				{
					System.out.println("�繫�� ��� �ִ� å�������� ������.\n���� �� ������ å��� ���� ���� ��� �� ���ǵ��� ���δ�.\n� �� ���캼��?");
					System.out.println("1. ����ö�� ���캸��.");
					System.out.println("2. ������ ���캸��.");
					System.out.println("3. �ڵ����� ���캸��.");
					System.out.println("4. å�� ���캸��.");
					System.out.println("5. �׸� ���캸��.");
					
					num = scan.nextInt();
					
					if (num == 1)		//����ö�� ���캼 ���
					{
						System.out.println("å�� ���� �������� ���� �ִ� � ����ö�� ����Ҵ�.");
						System.out.println("���ŷ�ó���� �ŷ� ��꼭��. ...��� ȸ��� ó�� ��� ���ε�... ���� �ŷ��ΰ���?");
					}
					else if (num == 2)	//������ ���캼 ���
					{
						System.out.println("å�� ���� ���� �ִ� ������ �������� ������ ����Ҵ�. ������ ȸ�翡 �ΰ� ����Ѱǰ�?");
						System.out.println("������ ���ڸ��� ���� �������� ���̴� ���� �ź����� ���δ�.");
						System.out.println("----------\n���α�\n340823-XXXXXXX\n----------");
						System.out.println("......34����̶��??");
					}
					else if (num == 3)	//�ڵ����� ���캼 ���
					{
						System.out.println("���� ���� ������ ���� �ִ� �ڵ����� ���캸�Ҵ�. �ڵ������� ȸ�翡 �ΰ� ����Ѱǰ�...?");
						System.out.println("�ڵ��� ȭ���� ���� �������� ���� ������� ������ ȭ���� ���δ�.");
						System.out.println("�������� ���Ҵ� �ź����� ��������� ���������� ���δ�. ���� ����� �����ΰ�����.");
						System.out.println("��й�ȣ�� �ɷ� �ִ�. �����ڸ� ��й�ȣ��. Ǯ���?");
						
						System.out.println("1. Ǯ���.");
						System.out.println("2. Ǯ�� �ʴ´�.");
						
						num = scan.nextInt();
						
						if (num == 1)		//��й�ȣ�� Ǯ�� �� ���
						{
							System.out.println("��й�ȣ�� Ǯ���.\n��й�ȣ�� �Է��Ͻÿ� : ");
							int password = scan.nextInt();
							
							if (password == 340823)	//��й�ȣ�� �°� �Է��� ���
							{
								// ������(�����8)�� ���������� �˻�
								// �������� �̹� �������ٸ� 
								if (player.searchItem(im.getEasyDevilLanguage(7).getName()))
								{
									System.out.println("�ڵ����� �� �̻� Ư���� �� ������ �ʴ´�.");
									continue;
								}
								//�������� �ʾҴٸ�
								else {
									System.out.println("��й�ȣ�� Ǯ�ȴ�! ������ ��й�ȣ���, �� �ܼ��� ����̳�.");
									System.out.println("Ȩ ȭ�鿡 <Easy to Learn-...> �̶�� �ٷΰ��� �������� ���δ�.");
									System.out.println("�ش� �������� ������, <���� ���� �Ǹ���> ��� ������ ������ ȭ�鿡 ���ö���.");
									player.saveInventory(im.getEasyDevilLanguage(7));	//������(�����8)ȹ��
								}
							}
							else					//�߸��� ��й�ȣ�� �Է����� ���
							{
								System.out.println("��й�ȣ�� �߸� �Է��߽��ϴ�. �̷�, �ٽ� �����غ���.");
							}
							
						}
						else if (num == 2)	//��й�ȣ�� Ǯ�� ���� ���
						{
							System.out.println("�ڵ����� �׸� ���캸��.");
						}
						else				//������ ���� ��ȣ�� �Է��� ���
						{
							System.out.println("��й�ȣ �Է� �ܿ� ������ �� �ִ� �� ���� ���δ�.");
						}
					}
					else if (num == 4)	//å�� ���캼 ���
					{
						System.out.println("å�� ���ҿ� ���� �ִ� �β��� å���� ���캸�Ҵ�.");
						System.out.println("<����! �ΰ�ó�� �ൿ�ϴ� 5���� ���>, <�̷��Ը� �ϸ� ��ŵ� ���� �ΰ�!>, <�ΰ��� ��ư���, ����� �ʴ�> ... ��� ������ å���̴�.");
						System.out.println("å ������ [������� ���� �����ô� å... ��� ����Ͻô°ɱ�? ����ϴ�.] ��� ������ ���� ����Ʈ���� �پ� �ִ�.");
						System.out.println("......����?");
					}
					else if (num == 5)	//�׸� ���캼 ���
					{
						System.out.println("å���� �׸� ���캸��.");
					}
					else				//������ �� ���� �Է��� ��� (������)
					{
						System.out.println("������ ���� ���캼�� ���� ���δ�.");
					}
				}
			}
			else if (num == 2)		//��������� ����
			{
				while(true)
				{
					System.out.println("�繫�� ���ҿ� �ִ� ��������Ƿ� ������. \n���� �� �ִ� ����� ������ ���� �� �ִ� ���ķ��� ���δ�.\n� �� ���캼��?");
					System.out.println("1. ��ũ�븦 ���캸��.");
					System.out.println("2. �����⸦ ���캸��.");
					System.out.println("3. ������ ���캸��.");
					System.out.println("4. ������ ���캸��.");
					System.out.println("5. �׸� ���캸��.");
					
					num = scan.nextInt();
					
					if (num == 1)		//��ũ�븦 ���캼 ���
					{
						System.out.println("��ũ�븦 ���캸�Ҵ�. ���� ������ ���� �Ĵ� �뵵�� ���δ�.");
						
						// �̹� ������(Ŀ��)�� ���������� �˻�
						// �������� �̹� �������ٸ� 
						if (player.searchItem(mapObject.getItem(0).getName()))
						{
							System.out.println("��ũ�뿡 �� �̻� Ư���� �� ������ �ʴ´�.");
							continue;
						}
						//�������� �ʾҴٸ�
						else {
							System.out.println("��ũ�� �����ڸ���, ������ �Դ� ���� Ŀ�ǰ� �����ִ�.");
							System.out.println("��������?");
							System.out.println("1. ��������.");
							System.out.println("2. �������� ����.");
							
							num = scan.nextInt();
							
							if (num == 1)		//1. ��������. �� �������� ���
							{
								// �÷��̾� �κ��丮�� ������(Ŀ��) ����
								player.saveInventory(mapObject.getItem(0));
								
								System.out.println("Ȥ�� �� ���� �������� �𸥴�. ��������.\n" + mapObject.getItem(0).getName() + "�� ì���.");
							}
							else if (num == 2)	//2. �������� �ʴ´�. �� �������� ���
							{
								System.out.println("���� �Դ� ���� �� ������... �����ϴ�. �������� ����.");
							}
							else				//������ �� ��ȣ �Է� ��
							{
								System.out.println("��� ���ڴ� ����? Ȯ���� ������.");
							}
						}
					}
					else if (num == 2)	//�����⸦ ���캼 ���
					{
						System.out.println("����� ���ʿ� �ִ� ���� �����⸦ ���캸�Ҵ�.");
						System.out.println("���� �ٴ��� �巯�� ������ ���δ�.");
						System.out.println("���� �����ϳ�... ������ ���ƾ����ٵ�.");
					}
					else if (num == 3)	//������ ���캼 ���
					{
						System.out.println("���� �پ� �ִ� ������ ���캸�Ҵ�.");
						System.out.println("���ݿ��� Ŀ�ǰ��簡 ������� �� ���� ���� ���� �ִ�. �����?");
						System.out.println("1. �����.");
						System.out.println("2. ����� �ʴ´�.");
						
						num = scan.nextInt();
						
						if (num == 1)		//��� ���
						{
							System.out.println("�Ѳ��� ����� ������ Ŀ�ǰ��� ��� ���Ͼ� ������� �����ϴ�.");
							System.out.println("...�̰� ����? �����ΰ�?");
							System.out.println("��¦ ���� �Ծ������, ������ �� ���� ���� �ʴ´�.");
							System.out.println("......����?");
						}
						else if (num == 2)	//����� ���� ���
						{
							System.out.println("Ŀ���뿡 Ŀ�ǰ��簡 �ƴϸ� ���� �ְڳ�. ���� ��� �ʿ�� ����δ�.");
						}
						else				//������
						{
							System.out.println("���ų�, ���� �ʰų��� ������. ���� �ν����� ���� ���� �ʳ�.");
						}
					}
					else if (num == 4)	//������ ���캼 ���
					{
						System.out.println("������� ������ ���캸�Ҵ�.");
						System.out.println("������ ���� �� ����� Ƽ����� �������� ���� �ִ�. �� �ڼ��� Ȯ���غ���?");
						System.out.println("1. Ȯ���غ���.");
						System.out.println("2. Ȯ������ �ʴ´�.");
						
						num = scan.nextInt();
						
						if (num == 1)		//Ȯ���� ���
						{
							System.out.println("Ƽ�� ���� �ϳ��� ���� ������ ���Ҵ�.");
							System.out.println("������ ��� Ƽ���� ���� �� �˾Ҵµ�, ������ �ȿ��� ���� �� Ƽ���� �ƴ϶� �Ͼ� ���簡 �� ���̴�.");
							System.out.println("......�̰� ����?");
						}
						else if (num == 2)	//Ȯ������ ���� ���
						{
							System.out.println("Ƽ�� ������ Ƽ���� �ְ���...\n�Դٰ�, �츮 �繫�� ��ǰ�� �ƴѵ� ������� ���� ��������. �׳� �Ѿ��.");
						}
						else				//������
						{
							System.out.println("���� Ȯ�� �� ��, Ȯ������ �������� ���� �Ѵ�.");
						}
					}
					else if (num == 5)	//�׸� ���캼 ���
					{
						System.out.println("����������� �׸� ���캸��.");
					}
					else				//������ �� ���� �Է� (������)
					{
						System.out.println("������ ���� ���캼�� ���� ���δ�.");
					}
				}
			}
			else if (num == 3)		//�Խ��� ����
			{
				while(true)
				{
					System.out.println("���鿡 �پ� �ִ� �Խ������� ������.\nĿ�ٶ� �Խ��ǿ��� ����, �������� ���� ���� �ִ�.\n� �� ���캼��?");
					System.out.println("1. ����ǥ�� ���캸��.");
					System.out.println("2. ���������� ���캸��.");
					System.out.println("3. �����͸� ���캸��.");
					System.out.println("4. �׸� ���캸��.");
					
					num = scan.nextInt();
					
					if (num == 1)		//����ǥ�� ���캼 ���
					{
						System.out.println("���� ���� ������ �����ϰ� �ִ� �޷� ������ ����ǥ�� ���캸�Ҵ�.");
						System.out.println("\'X�� X��... 10:00 ȸ��... \nX�� X��... 15:00 ����...\nX�� X��... T��ǰ ��ǰ��...\'");
						System.out.println("��...? ��۱����� �� ��ǰ�Ѵٴ°���?");
					}
					else if (num == 2)	//���������� ���캼 ���
					{
						System.out.println("�Խ��� ������ �پ� �ִ� ���������� ���캸�Ҵ�.");
						System.out.println("\'��� �� ���� ���� �ʼ�! ����� ������ �ʾ� ��ų �� ����԰��� 1:1 ���\'");
						System.out.println("...�̶�� ���� �ִ�. ������ �׷��� �ĵ�... Ʋų �� ���? �̰� ���� ������?");
					}
					else if (num == 3)	//�����͸� ���캼 ���
					{
						System.out.println("�������� �Ʒ� �پ� �ִ� �����͵��� ���캸�Ҵ�.");
						System.out.println("ȭ�� ���� ������, ��� �� ���ǻ��� ��... �پ��� �����Ͱ� �پ� �ִ�.");
					}
					else if (num == 4)	//�׸� ���캼 ���
					{
						System.out.println("�Խ����� �׸� ���캸��.");
					}
					else				//������
					{
						System.out.println("������ ���� ���캼�� ���� ���δ�.");
					}
				}
			}
		}
	}
	
	// 4�� ȸ�ǽ�(��2) �̺�Ʈ �Լ�
	public void playFloor4_2()
	{
		// 1. ��Ʋ �ý��� Ȯ��
		
		// 2. ȸ�ǽ� ��ũ��Ʈ ���
		// 4���� ȸ�ǽ��̴�. ���鿡 ��ũ���� ��ǥ�� �ܻ��� �ְ�, �� ������ å���� ����� ������� ��ġ�Ǿ� �ִ�.
		// ������ �� �������Ϳ� ��ǻ�Ͱ� �ִ�. ȸ�Ǹ� ������ ������ ��ο� ���¸� �����ϱ⿡, â�� �ϳ� ���� ���� �����Ⱑ �����ϰ� ��������.
		
		while(true)
		{
			// 4���� �ι�°(2) ���̹Ƿ� �μ��� 4, 2�� �������
			// �� ���� �̺�Ʈ (�繰(������Ʈ) ��� �� ���� ����)
			// enterRoom�� ��ȯ���� false�� ���� ���� �̺�Ʈ�� �����ϵ��� ��(�̵�)
			if (!enterRoom(4, 2))
			{
				break;
			}
			
			// enterRoom�� ��ȯ���� true�� ���
			// -> ���� ����
			
			if (num == 1)		// ��ǻ�� ����
			{
				while(true)
				{
					System.out.println("���� ���� �ִ� ��ǻ�� ������ ������. ��ǻ�� ����Ϳ� ȭ�麸ȣ�Ⱑ ����� �ִ�.\n��й�ȣ�� ���� �ɷ� ���� �ʾ� ���δ�.\n� �� ���캼��?");
					System.out.println("1. ����ȭ���� ���캸��.");
					System.out.println("2. USB�� ���캸��.");
					System.out.println("3. ���̺��� ���캸��.");
					System.out.println("4. �����߹��� ������ ���캸��.");
					System.out.println("5. �׸� ���캸��.");
					
					num = scan.nextInt();
					
					if (num == 1)		//����ȭ���� ���캼 ���
					{
						System.out.println("��ǻ���� ����ȭ�鿡 '�ŷ�ó��_����_��Ȳ.pptx'��� ������ ���δ�.");
						System.out.println("������ �����, �� �ŷ�ó�� �Ǹ� ���� ��ǥ ������ ����ִ�.");
						System.out.println("PPT���� ó�� ��� �̻��� �̸��� ȸ����� ���� �ִ�.");
						System.out.println("...�̷� �̸��� ȸ�簡 �ִٰ�...?");
					}
					else if (num == 2)	//USB�� ���캼 ���
					{
						System.out.println("��ǻ�Ϳ� USB�� ���� �ִ�.");
						System.out.println("USB �̸���... �̰� ���� �˾ƺ� �� ���� ���ڴ�.");
					}
					else if (num == 3)	//���̺��� ���캼 ���
					{
						System.out.println("��ǻ�Ϳ� ���������͸� �����ϴ� ���̺��̴�.");
						System.out.println("��ǻ�� ȭ���� ��ũ���� ������ �ִ�.");
					}
					else if (num == 4)	//�����߹��� ������ ���캼 ���
					{
						System.out.println("����ȭ�鿡 �ִ� ������ ������ '�����߹���'������ ����ô�.");
						System.out.println("�ȿ��� '5��_����_��Ȳ.xlsx' ��� ���� ���� �ϳ��� �ִ�.");
						System.out.println("������ ����� ���� ��ǰ���� ���Ե� ����� �����Ǿ��ִ�.");
						System.out.println("U���, L���... ���� ó�� ���� �̸��� ��ǰ���̴�. �̰� �����ؼ� ��� ���� �ɱ�. �� �����ϴ� �ɱ�.");
					}
					else if (num == 5)	//�׸� ���캼 ���
					{
						System.out.println("��ǻ�ʹ� �׸� ���캸��.");
					}
					else				//������
					{
						System.out.println("�װ� ���� �� �ʿ䰡 ���� ���δ�.");
					}
				}
			}
			else if (num == 2)		//�ܻ� ����
			{
				while(true)
				{
					System.out.println("������ �ܻ� ������ ������.\n�ܻ󿡴� ������ ������ ��ǥ �غ� �ߴ� ������ ���� �ִ�.\n� �� ���캼��?");
					System.out.println("1. ���� ������ ���캸��.");
					System.out.println("2. �����������͸� ���캸��.");
					System.out.println("3. ����ũ�� ���캸��.");
					System.out.println("4. �׸� ���캸��.");
					
					num = scan.nextInt();
					
					if (num == 1)		//���� ������ ���캼 ���
					{
						System.out.println("�ܻ� ���� �ִ� ���� ������ ����ô�.");
						System.out.println("��ǥ�� ����� �� ���� �뺻 �ڷ��̴�. �Ʊ� ���Ҵ� PPT�� ������ �����ϴ�.");
						System.out.println("PPT���� �ô� ó�� ��� ���� ȸ�� �̸��� �״�� ���� �ִ�. �ŷ� ǰ�� �˴� ó�� ��� �̸��̴�.");
					}
					else if (num == 2)		//�����������͸� ���캼 ���
					{
						System.out.println("�ܻ� ���� �ִ� ���� �����������͸� ����ô�.");
						System.out.println("�̻� ���� �� �۵��Ѵ�. ��ǥ�� �� �̿��� �� ����.");
					}
					else if (num == 3)	//����ũ�� ���캼 ���
					{
						System.out.println("�ܻ� ���� �ִ� ����ũ�� ����ô�.");
						System.out.println("����Ŀ�� ����Ǿ��ִ� ����ũ��. ����� ���� �ִ��� �۵����� �ʴ´�.");
					}
					else if (num == 4)	//�׸� ���캼 ���
					{
						System.out.println("�ܻ��� �׸� ���캸��.");
					}
					else				//������
					{
						System.out.println("�� ���� ���캼 �� ���� ���δ�.");
					}
				}
			}
			else if (num == 3)		//å�� ����
			{
				while(true)
				{
					System.out.println("�߾��� å���� ���캸��.\nå���� ����ڷ� ��ٶ� ������, ����ϰ� �����Ǿ� �־� ���� ��� ���� ���� ����.\n� �� ���캼��?");
					System.out.println("1. �������� ���캸��.");
					System.out.println("2. ������ ���캸��.");
					System.out.println("3. ����Ʈ���� ���캸��.");
					System.out.println("4. �׸� ���캸��.");
					
					num = scan.nextInt();
					
					if (num == 1)		//�������� ���캼 ���
					{
						System.out.println("å�� ������ ���� �ִ� ������ �������� ����ô�.");
						System.out.println("���𰡸� ���ô� ���� ������ �� ��ü�� ���� ���� �ִ�.");
						System.out.println("���� ��ü�� �̻��� �Ͼ� ���簡 �յ� ���ٴѴ�.");
						System.out.println("...�̰� ����? ���� ���� ����ǿ��� ���Ҵ� �Ͼ� ����� ����� ���δ�.");
					}
					else if (num == 2)		//������ ���캼 ���
					{
						System.out.println("å�� ���� ���� �ִ� ������ ������ ����ô�.");
						System.out.println("����� ��޽����� �������̴�. ��� ���̴µ�. �̰� �ΰ� ���ٴ�.");
					}
					else if (num == 3)	//����Ʈ���� ���캼 ���
					{
						System.out.println("å�� �Ʒ��ʿ� ó�����ִ� ������ ����Ʈ���� ����ô�.");
						System.out.println("'ȸ�� ���� ������...' ���� �� �ǹ� ���� ������ ���� �ִ�.");
					}
					else if (num == 4)	//�׸� ���캼 ���
					{
						System.out.println("å���� �׸� ���캸��.");
					}
					else				//������
					{
						System.out.println("�� ���� ���캼 �� ���� ���δ�.");
					}
				}
			}
		}
	}
	
	// 4�� ��ȭ��(��3) �̺�Ʈ �Լ�
	public void playFloor4_3()
	{
		// 1. ��Ʋ �ý��� Ȯ��
		
		// 2. ��ȭ�� ��ũ��Ʈ ���
		// 4���� ��ȭ���̴�. ���� ����� ���� ��Ʈ���� �ƴϰ�, ������ ����� ���� ���õ� �����̴�.
		// ��ȭ�Ǵ�� �� �� ���� Ŀ�ٶ� ����ν��� ä���� �ְ�, ���� ��Ʈ���� ���ֺ��� ���õǾ��ִ�.
		
		while(true)
		{
			// 4���� ����°(3) ���̹Ƿ� �μ��� 4, 3�� �������
			// �� ���� �̺�Ʈ (�繰(������Ʈ) ��� �� ���� ����)
			// enterRoom�� ��ȯ���� false�� ���� ���� �̺�Ʈ�� �����ϵ��� ��(�̵�)
			if (!enterRoom(4, 3))
			{
				break;
			}
			
			// enterRoom�� ��ȯ���� true�� ���
			// -> ���� ����
			
			if (num == 1)		// ����ν� ����
			{
				System.out.println("����ν��� �����غ���.\n���� ����� ������ ����ν��� ������� �ܶ� �������� �����̴�.\n� �� ���캼��?");
				System.out.println("1. �뺻�� ���캸��.");
				System.out.println("2. �����⸦ ���캸��.");
				System.out.println("3. ����͸� ���캸��.");
				System.out.println("4. ����͸� ���캸��.");
				System.out.println("5. �׸� ���캸��.");
				
				num = scan.nextInt();
				
				if (num == 1)		//�뺻�� ���캼 ���
				{
					System.out.println("���� ��� �� å�� ���� �뺻�� ����ô�.");
					System.out.println("�̹� ���� ���� ���̷� �� ���α׷��� �뺻���� ���δ�.");
					System.out.println("������ �뺻����, ��翡 ���� ���� ������ ���� �ִ�. �ڼ��� �о��?");
					
					System.out.println("1. �о��.");
					System.out.println("2. �о�� �ʴ´�.");
					
					num = scan.nextInt();
					
					if (num == 1)		//�о ���
					{
						System.out.println("�����ϰ� ���� �뺻���� �ڼ��� �о��, �߿��� �κп� ���������� üũ�� �Ǿ��ִ°� ���δ�.");
						System.out.println("...�������� Ǫ�� �������� ������ ���߱�...");
						System.out.println("......ȭ���� �ٲ�� ��� ���� ���ϰ�...");
						System.out.println("...�����ڰ� �ٽ� ������ ���� �������� ������......");
						System.out.println("������ ��Ʈ �� ������ �� ���� ��ȯ...");
						System.out.println("�뷫 �� ������ ���뿡 üũ�Ǿ� �ִ�.");
					}
					else if (num == 2)	//�о�� ���� ���
					{
						System.out.println("���� �ڼ��� �о� �� �ʿ�� ���� ���δ�.");
						System.out.println("�� �뺻�� ���� �׸� ���캸��.");
					}
					else				//������
					{
						System.out.println("�׷��Դ� ���� �� ����. �ٽ� ������.");
					}
				}
				else if (num == 2)	//�����⸦ ���캼 ���
				{
					System.out.println("���� �����⿡�� �� ��ư�� ��⺰�� ���� ��ư���� ���� �ִ�.");
					System.out.println("MIC1... MIC2... PC... CAM... DEMON... DEMON? �Ǹ�?");
					System.out.println("���� �̸��� ���� �󺧵� ����, �� �� ���� ���� �پ� �ִ�.");
					System.out.println("......����?");
				}
				else if (num == 3)	//����͸� ���캼 ���
				{
					System.out.println("���� �ν��� ���ʿ� ���𰡸� ���� �� �ִ� ����Ͱ� ���� �ִ�.");
					System.out.println("����Ϳ� ����Ǿ��ִ� ���� �������� ����Ǿ� �ִ�. ������ ���� ����� �� ����.");
					System.out.println("���� �� �ִ� �κ��� �� �� ���ε�... �ڼ��� ���캼��?");
					
					System.out.println("1. ���캻��.");
					System.out.println("2. ���캸�� �ʴ´�.");
					
					num = scan.nextInt();
					
					int lineCount = 0;
					
					if (num == 1)		//�о ���
					{
						System.out.println("���� ���⿡ ���� ���� ����... ");
						
						//ȹ���� ȸ�μ� ���� ī��Ʈ
						for (int i = 0; i < 4; i++) {
							if (electricLine[i].getValue == 1)
							{
								lineCount++;
							}
						}
						
						if (lineCount == 0)			//ȹ���� ȸ�μ��� ���ٸ� 
						{
							System.out.println("������ ���� ���� �ʴ� �� ����.");
						}
						else if (lineCount == 1 || lineCount == 2)	//ȹ���� ȸ�μ��� 1���ų� 2�����
						{
							System.out.println("���� ���� ȸ�μ� "+ lineCount + "���� �Ⱦƺ��Ҵ�.");
							System.out.println("�� �°� ���� �� ����, ȸ�μ��� �ȴ� ���� �´� �� ����.");
							System.out.println("������ �ƹ��� ������ ����...");
							System.out.println("������ " + (3-lineCount) + "ĭ���� ȸ�μ��� �Ⱦƾ� �� �� ����. ȸ�μ��� �� ã�ƺ���.");
						}
						else if (lineCount == 3)	//ȹ���� ȸ�μ��� 3�����
						{
							System.out.println("���� ���� ȸ�μ� 3���� �ϳ��� �Ⱦƺ��Ҵ�.");
							System.out.println("�°� ���� ����, ����� ������ ���� �Ķ����� ���Դ�.");
						}
						else
						{
							//����ó��
						}
					}
					else if (num == 2)	//�о�� ���� ���
					{
						System.out.println("���� �ڼ��� ���� �� �ʿ�� ���� ���δ�.");
						System.out.println("����ʹ� ���� �׸� ���캸��.");
					}
					else				//������
					{
						System.out.println("Ȯ���ϰ� ��������.");
					}
				}
				else if (num == 4)	//����͸� ���캼 ���
				{
					System.out.println("ȭ���� ���� �ִ� ����Ͱ� Ǫ�� ���� ���հ� �ִ�.");
					System.out.println("�� �� �̻��� ���� ������ �ʴ´�.");
				}
				else if (num == 5)	//�׸� ���캼 ���
				{
					System.out.println("����ν��� �׸� ���캸��.");
				}
				else				//������ ���� ���ڸ� �Է��� ���(������)
				{
					System.out.println("�װ� ���캼 �ʿ䰡 ���� ���δ�.");
				}
			}
			else if (num == 2)	// ��Ʈ�� ����
			{
				System.out.println("��Ʈ���� �����غ���.\n���̷� ������ ���õ� ��Ʈ���� �ܼ��� ������ �Ǿ� �ִ�.\n� �� ���캼��?");
				System.out.println("1. ī�޶� ���캸��.");
				System.out.println("2. ����ũ�� ���캸��.");
				System.out.println("3. �������� ���캸��.");
				System.out.println("4. �׸� ���캸��.");
				
				num = scan.nextInt();
				
				if (num == 1)		//ī�޶� ���캼 ���
				{
					System.out.println("��Ʈ�� �߾��� ���ϰ� �ִ� ���� ���� ī�޶� ����ô�.");
					System.out.println("���������� ����� ��� �Ű���. ������ ���� ���� �ִ�.");
				}
				else if (num == 2)	//����ũ�� ���캼 ���
				{
					System.out.println("��Ʈ�� �߾�, �ܻ� ���� �ִ� ����ũ�� ����ô�.");
					System.out.println("���������� ������ ���� ���� �ִ�.");
				}
				else if (num == 3)	//�������� ���캼 ���
				{
					System.out.println("����ũ ���� ���� �ִ� ���� �������� ����ô�.");
					System.out.println("� ���������� ���� ���� ��ư���� �޷� �ִ�.");
					System.out.println("��ư�� ��������?");
					
					int buttonNum = 0;	//��ư ���� ���� Ȯ�ο� ����
					
					while(true)
					{
						//��-��-��-�� ���� (3-2-1-4)
						System.out.println("1. ���� ��ư�� ��������.");
						System.out.println("2. ��� ��ư�� ��������.");
						System.out.println("3. �Ķ� ��ư�� ��������.");
						System.out.println("4. �� ��ư�� ��������.");
						System.out.println("5. �׸� ���캸��.");
						
						num = scan.nextInt();
						
						if (num == 1)		//���� ��ư ����
						{
							if (buttonNum == 2)		//������ ���� ��� (3��°)
							{
								System.out.println("���� ��ư�� ������. ��Ʈ���� ���� ������ ������.");
								buttonNum = 3;
							}
							else if (buttonNum == 0)	//ù ��°�� ��ư�� ���� ���
							{
								System.out.println("���� ��ư�� ������. ��Ʈ���� ���� ������ ������.");
								buttonNum = 4;		//������ Ʋ�� ����̹Ƿ� ���� ��쿡�� �ٷ� else�� ���� �� �ֵ��� ��.
							}
							else					//������ Ʋ�� ���
							{
								System.out.println("���� ��ư�� ������ ��Ʈ���� ��� ������ ������.");
								buttonNum = 0;
							}
						}
						else if (num == 2)	//��� ��ư ����
						{
							if (buttonNum == 1)		//������ ���� ��� (2��°)
							{
								System.out.println("��� ��ư�� ������. ��Ʈ���� ��� ������ ������.");
								buttonNum = 2;
							}
							else if (buttonNum == 0)	//ù ��°�� ��ư�� ���� ���
							{
								System.out.println("��� ��ư�� ������. ��Ʈ���� ��� ������ ������.");
								buttonNum = 4;		//������ Ʋ�� ����̹Ƿ� ���� ��쿡�� �ٷ� else�� ���� �� �ֵ��� ��.
							}
							else					//������ Ʋ�� ���
							{
								System.out.println("��� ��ư�� ������ ��Ʈ���� ��� ������ ������.");
								buttonNum = 0;
							}
						}
						else if (num == 3)	//�Ķ� ��ư ����
						{
							if (buttonNum == 0)		//������ ���� ��� (1��°)
							{
								System.out.println("�Ķ� ��ư�� ������. ��Ʈ���� Ǫ�� ������ ������.");
								buttonNum = 1;
							}
							else
							{
								System.out.println("�Ķ� ��ư�� ������ ��Ʈ���� ��� ������ ������.");
								buttonNum = 0;
							}
						}
						else if (num == 4)	//�� ��ư ����
						{
							if (buttonNum == 3)		//������ ���� ��� (4��°)
							{
								//������(�����9) ���������� Ȯ��
								//�������� ���
								if (player.searchItem(im.getEasyDevilLanguage(8).getName()))
								{
									System.out.println("�� ��ư�� ������.\n��Ʈ���� ��� ������ �������� �ܻ� �Ʒ����� �� �̻� �ƹ��͵� ��Ÿ���� �ʾҴ�.");
									buttonNum = 0;
									continue;
								}
								//�������� �ʾ��� ���
								else
								{
									//���� �ùٸ��� �Է� -> ����� ȹ��
									System.out.println("�� ��ư�� ������.\n���� �ϴ� �Ҹ��� �Բ� ��� ������ ������ �ܻ� �Ʒ����� ���� ��������.");
									buttonNum = 0;
									player.saveInventory(im.getEasyDevilLanguage(8));
									//�����9�� ì���.
									System.out.println("�ܻ� �Ʒ��� ���캸�� å �� ���� ������ �ִ�.\n" + im.getEasyDevilLanguage(8).getName() + "�� ì���.");
								}
							}
							else if (buttonNum == 0)	//ù ��°�� ���� ư ���
							{
								System.out.println("�� ��ư�� ������. ��Ʈ���� �Ͼ� ������ ������.");
								buttonNum = 4;
							}
							else					//������ Ʋ�� ���
							{
								System.out.println("�� ��ư�� ������ ��Ʈ���� ��� ������ ������.");
								buttonNum = 0;
							}	
						}
					}
				}
				else if (num == 4)	//�׸� ���캼 ���
				{
					System.out.println("��Ʈ���� �׸� ���캸��.");
				}
				else 				//������ ���� ���ڸ� �Է��� ��� (������)
				{
					System.out.println("�װ� ���캼 �ʿ䰡 ���� ���δ�.");
				}
			}
		}
	}
	
	// 4�� ȭ���(posID: 44) �̺�Ʈ �Լ�
	public void playFloor4_4()
	{
		while(true)
		{
			// 4���� �� ��°(4) ��(ȭ���)�̹Ƿ� �μ��� 4, 4�� �������
			// �� ���� �̺�Ʈ (�繰(������Ʈ) ��� �� ���� ����)
			// enterRoom�� ��ȯ���� false�� ���� ���� �̺�Ʈ�� �����ϵ��� ��(�̵�)
			if (!enterRoom(4, 4))
			{
				break;
			}
			
			// enterRoom�� ��ȯ���� true�� ���
			// -> ���� ����
			
			System.out.println("������ ���ϰ� �����Ÿ��� ȭ����� ������ �� ���� ���� �� ���δ�.\n� �� �ұ�?");
			System.out.println("1. �ſ��� ����");
			System.out.println("2. �ٴ��� ����");
			System.out.println("3. ������ ����");
			System.out.println("4. ������");
			
			num = scan.nextInt();
			
			if (num == 1)		// �ſ� ����
			{
				System.out.println("�� ����̴�.");
				System.out.println("* hp: " + player.getHp());
				System.out.println("* ���ݷ�: " + player.getAttackPower());
				System.out.println("* ����: " + player.getDefensivePower());
				System.out.println("* ���ǵ�: " + player.getReputation());
			}
			
			else if (num == 2)	// �ٴ� ����
			{
				System.out.println("û�Ұ� ����� ���� ���� ������ �Ͼ� ������ �������� ���� �ִ�.");			
			}
			
			else if (num == 3)	// �� ���� ����(�κ��丮, ���� �������� ����Ʈ Ȯ��)
			{
				System.out.println("� �� ����?");
				System.out.println("1. ���� ����");	// �κ��丮 Ȯ��
				System.out.println("2. �� �� ���");	// ����Ʈ ��� Ȯ��
				
				num = scan.nextInt();
				
				if (num == 1)			// �κ��丮 Ȯ��
				{
					player.showInventory();
				}
				else if (num == 2)		// ����Ʈ Ȯ��
				{
					// ���� �÷��̾ ���� ���� ����Ʈ ��� ���
					player.printQuestList();
				}
				else					// ������
				{
					System.out.println("�װ�... �߸��� �����̴�.");
				}
			}
			else if (num == 4)	//�κ�� ������
			{
				player.setPosID(40);
				break;
			}
			else				//������ ���� ���ڸ� �Է��� ��� (������)
			{
				System.out.println("�װ� ȭ��ǿ��� �� �� ����.");
				
			}
		}
	}
	
	
	// * * * * * * * * * * * * * \\
	
	
	// 5�� �̺�Ʈ �Լ�(����)
	public void playFloor5(String[][] endingArray)
	{
		// ����ȭ��
		// 	=> 1. ��忣��: ȸ�μ��� �� ������ ���� ���
		//	=> 2. �븻����: ȸ�μ����� �� ��Ƽ� ������ ����
							// �÷��̾ ��� �ִ� ȸ�μ��� ������ üũ�ϴ� ���⵵ ������
		// 	=> 3. ������: ���ǵ��� �� �÷��� ����Ŀ� ����
		// 	===> ���� �Ǻ��� �� �����ۺ��� ����Ʈ�� �ο��Ͽ�, ���� ����Ʈ�� ������ ���� ���� ����
		// 	===> ����Ʈ�� �ʿ��� ������ ����Ʈ �����ָ� ��.
		
//					if(player.getCurrentRank() == 'S'){
//						// Ʈ�翣��
//						// ���Ͽ��� �ҷ�����(�б�)
//					}
//					else if(happyEndingPoint == 3){
//						// �븻����
//						// ���Ͽ��� �ҷ�����
//					}
//					else{
//						// ��� ����
//						// ���Ͽ��� �ҷ�����
//					}
	}
}
