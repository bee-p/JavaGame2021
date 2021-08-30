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
	private int endingPoint = 0;
	
	// Player, Map ��ü
	// -> GameManager Ŭ�������� PlayEvent ��ü ������ �� ����ֱ�
	private Map[][] map;
	private Player player;
	
	
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
				// ������, ī��, ������ ���δ�. ���� ����?
				System.out.println("1. �����η� ����.");
				System.out.println("2. ī��� ����.");
				System.out.println("3. ���Ƿ� ����.");
				System.out.println("4. ������ �޶�����.");		// �ٽ� �κ� ������ ���
				
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
						// �̹� �������� ���������� �˻�(�ϱ���)
						if (player.searchItem(mapObject.getItem(0).getName()))
						{
							// �������� �̹� �������ٸ� �ٽ� å�� ��/å�� �Ʒ� �������� �̵�
							System.out.println("å�� ���� ���̻� Ư���� �� ������ �ʴ´�.");
							continue;
						}
						
						// �������� �������� �ʾҴٸ� ���� ���� �״��..
						
						// å�� ������ �ϱ����� �ִ�.
						System.out.println("å�� ������ " + mapObject.getItem(0).getName() + "�� �ִ�.");
						System.out.println("��������?");
						System.out.println("1. ��������.");
						System.out.println("2. �������� ����.");
						
						num = scan.nextInt();
						
						if (num == 1)
						{
							// �÷��̾� �κ��丮�� ������ ����
							player.saveInventory(mapObject.getItem(0));
							
							System.out.println(mapObject.getItem(0).getName() + "�� ��������.");
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
							if (player.searchItem(mapObject.getItem(1).getName()))
							{
								System.out.println("���̻� �ƹ� �͵� ����.");
							}
							else	// ���� �������� �ʾҴٸ�
							{	
								System.out.println(mapObject.getItem(1).getName() + "�� �ִ�.");
								System.out.println("��������?");
								System.out.println("1. ��������.");
								System.out.println("2. �������� ����.");
								
								num = scan.nextInt();
								
								if (num == 1)		// ��������
								{
									// �÷��̾� �κ��丮�� ������ ����
									player.saveInventory(mapObject.getItem(1));
									
									System.out.println(mapObject.getItem(1).getName() + "�� ��������.");
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
					// �𸣴� ���踦 �������ٸ�
					if (player.searchItem(mapObject.getItem(0).getName()))
					{
						System.out.println("�ƹ� �͵� ������� �ʴ�. ��ҿ� ���𰡸� �� �־�� �� �׷�������.");
					}
					// ���� �������� �ʾҴٸ�
					else
					{	
						// �𸣴� ���谡 ����ִ�.
						System.out.println(mapObject.getItem(0).getName() + "�� ����ִ�. �̰� ����?");
						System.out.println("����������?");
						System.out.println("1. ��������.");
						System.out.println("2. ������.");
						
						num = scan.nextInt();
						
						if (num == 1)		// ��������
						{
							// �÷��̾� �κ��丮�� ������ ����
							player.saveInventory(mapObject.getItem(0));
							
							System.out.println(mapObject.getItem(0).getName() + "�� ì���.");
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
	public void playFloor1_3()
	{
		
	}
	
	// 1�� ȭ���(posID: 14) �̺�Ʈ �Լ�
	public void playFloor1_4()
	{
		
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
				System.out.println("3. ������ �޶�����.");		// �ٽ� �κ� ������ ���
				
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
				else if (num == 3)	// �ٸ� ������ ���� �ʱ�(�κ� ��ũ��Ʈ/������ �ٽ� ���)
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
					System.out.println("3. ���̺� �� ���ڵ���	 ���캸��.");
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
								Player.deleteInventory(map[1][1].getObject(2).getItem(1));		//�κ��丮���� ������(����) ����
								
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
							System.out.println("���������� ���������� ���������� ����������");
							System.out.println("���������� ���������� ���������� ����������");
							System.out.println("���������� ���������� ���������� ����������");
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
							// �̹� ������(�����)�� ���������� �˻�
							// �������� �̹� �������ٸ� 
							if (player.searchItem(mapObject.getItem(0).getName()))
							{
								System.out.println("����ȭ�鿡 �� �̻� Ư���� �� ������ �ʴ´�.");
								continue;
							}
							//�������� �ʾҴٸ�
							else {
								System.out.println("����ȭ���� ���캸��.\n�ٷΰ���� ������ ���� SBA_4.pdf��� ������ �ִ�.");
								System.out.println("������ ����� '���� ���� �Ǹ���'��� ������ ������ ���δ�.");
								player.saveInventory(mapObject.getItem(0));	//������(�����)ȹ��
							}
						}
						else if (num == 2)	//�������� ���캼 ���
						{
							// �̹� ������(�����)�� ���������� �˻�
							// �������� �̹� �������ٸ� 
							if (player.searchItem(mapObject.getItem(1).getName()))
							{
								System.out.println("�����뿡 �� �̻� Ư���� �� ������ �ʴ´�.");
								continue;
							}
							//�������� �ʾҴٸ�
							else {
								System.out.println("�������� ���캸��.\n�������� ������ SBA_5.pdf��� ������ ���� �ִ°� ���δ�.");
								System.out.println("�������� '���� ���� �Ǹ���'��� ������ ������ ���ȴ�.");
								player.saveInventory(mapObject.getItem(1));	//������(�����)ȹ��
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
						// �̹� ������(�ϱ���)�� ���������� �˻�
						// �������� �̹� �������ٸ� 
						if (player.searchItem(mapObject.getItem(0).getName()))
						{
							System.out.println("�����忡 �� �̻� Ư���� �� ������ �ʴ´�.");
							continue;
						}
						//�������� �ʾҴٸ�
						else {
							System.out.println("�������� ���ȴ�.\n����� �ȿ� ���� �ִ� ���� ��Ʈ�� ���δ�.");
							System.out.println("�ϱ����̴�.");
							player.saveInventory(mapObject.getItem(0));	//������(�ϱ���) ȹ��
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
					System.out.println("- \n--\n- \n -");
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
					// �̹� ������(�ϱ���)�� ���������� �˻�
					// �������� �̹� �������ٸ� 
					if (player.searchItem(mapObject.getItem(0).getName()))
					{
						System.out.println("�����뿡 �� �̻� Ư���� �� ������ �ʴ´�.");
						continue;
					}
					//�������� �ʾҴٸ�
					else {
						System.out.println("�������� �� �������� ���� å�� ���Դ�.");
						System.out.println("�ϱ����̴�.");
						player.saveInventory(mapObject.getItem(0));	//������(�ϱ���) ȹ��
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
		
	}
	
	
	// * * * * * * * * * * * * * \\
	
	
	// 3�� �κ� �̺�Ʈ �Լ�
	public void playFloor3_0()
	{
		
	}
	
	// 3�� ���߽�1(��1) �̺�Ʈ �Լ�
	public void playFloor3_1()
	{
		
	}
	
	// 3�� ���߽�2(��2) �̺�Ʈ �Լ�
	public void playFloor3_2()
	{
		
	}
	
	// 3�� �����(��3) �̺�Ʈ �Լ�
	public void playFloor3_3()
	{
		
	}
	
	// 3�� ȭ���(posID: 34) �̺�Ʈ �Լ�
	public void playFloor3_4()
	{
		
	}
	
	
	// * * * * * * * * * * * * * \\
	
	
	// 4�� �κ� �̺�Ʈ �Լ�
	public void playFloor4_0()
	{
		
	}
	
	// 4�� (��1) �̺�Ʈ �Լ�
	public void playFloor4_1()
	{
		
	}
	
	// 4�� (��2) �̺�Ʈ �Լ�
	public void playFloor4_2()
	{
		
	}
	
	// 4�� (��3) �̺�Ʈ �Լ�
	public void playFloor4_3()
	{
		
	}
	
	// 4�� ȭ���(posID: 44) �̺�Ʈ �Լ�
	public void playFloor4_4()
	{
		
	}
	
	
	// * * * * * * * * * * * * * \\
	
	
	// 5�� �̺�Ʈ �Լ�(����)
	public void playFloor5()
	{
		// ����ȭ��
		// 	=> 1. ��忣��: ȸ�μ��� �� ������ ���� ���
		//	=> 2. �븻����: ȸ�μ����� �� ��Ƽ� ������ ����
		// 	=> 3. ������: ���ǵ��� �� �÷��� ����Ŀ� ����
		// 	===> ���� �Ǻ��� �� �����ۺ��� ����Ʈ�� �ο��Ͽ�, ���� ����Ʈ�� ������ ���� ���� ����
		// 	===> ����Ʈ�� �ʿ��� ������ ����Ʈ �����ָ� ��.
		
//					if(player.getCurrentRank() == 'S'){
//						// Ʈ�翣��
//						// ���Ͽ��� �ҷ�����(�б�)
//					}
//					else if(happyEndingPoint == 3){
//						//���ǿ���
//						// ���Ͽ��� �ҷ�����
//					}
//					else{
//						//�븻 ����
//						// ���Ͽ��� �ҷ�����
//					}
	}
}
