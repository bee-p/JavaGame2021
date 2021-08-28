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
	
	
	// ----------------------------------------------------------------- \\
	
	
	// 1�� �κ� �̺�Ʈ �Լ�
	public void playFloor1_0()
	{
		while(true)
		{
			// 1. �κ� ��ũ��Ʈ ���
			// �κ��!
			
			// 2. ������ ���
			System.out.println("1. ���Ա⿡ ������� ����.");	// ����
			System.out.println("2. Ÿ��Ʋ�� ������.");			// Ÿ��Ʋ�� ������ �������� �־�� �Ǵµ� ������ ���...����� ���� �𸣰���..
			System.out.println("3. �ٸ� ���� �ѷ�����.");		// �ٸ� �� �̵�
			System.out.println("4. ���������͸� Ÿ��.");		// �� �̵�
			
			num = scan.nextInt();
			
			if (num == 1)			// ����
			{
				// ���� ���� ������ ���ÿ� ����
				
				System.out.println("������� ���������� �����!");
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
		// �����δ�. ���� ���ϴ� �μ��� �����ϸ���ġ �ͼ��� ���ѵ�, ���ϰ� ������ ����� ���� ���Ѵ�. ���̶� �׷���.
		// �տ��� �ͼ��� å�� �迭���� ���δ�. ���߿��� ���� ��� å�� �ɽ����� �ִ�.

		while(true)
		{
			System.out.println("��� ���캼��?");
			
			// for������ �ش� ���� ���� �繰 �迭 ��� (���ڿ� �Բ�) ����ϱ�..
			for (int i = 0; i < map[0][1].getAllObject().length; i++)
			{	// ex) 1. �� å��
				System.out.println((i + 1) + ". " + map[0][1].getObject(i).getObjectName());
			}
			// [������ ��ȣ]. �κ�� ������.
			System.out.println((map[0][1].getAllObject().length + 1) + ". �κ�� ������.");
			System.out.println("(���ϴ� �������� ���� �Է�)");
			
			num = scan.nextInt();
			
			// ������ ���� ��ȣ�� ����� �Է����� �ʾ��� ���(������)
			if (!(num >= 1 && num <= map[0][1].getAllObject().length + 1))
			{
				System.out.println("�׷� �������� ����.");
				continue;
			}
			
			// �κ�� �����⸦ �������� ���
			if (num == map[0][1].getAllObject().length + 1)
			{
				// �κ�� ��ġ ������
				player.setPosID(10);
				break;						// �ݺ��� Ż��! (�޼ҵ� ����)
			}
			
			
			// �������� ����� �Է����� ��� (�⺻ ���)
			// 1. ���� ������Ʈ�� ������ ������Ʈ�� ����
			mapObject = map[0][1].getObject(num - 1);
			
			// 2. �ش� ������Ʈ�� �̸� & ���� ���
			// ex) ������� å��� �� å�� ���� ���� ���..
			objectPrint(mapObject);
			
			
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
		
	}
	
	// 2�� �Ĵ�(��1) �̺�Ʈ �Լ�
	public void playFloor2_1()
	{
		
	}
	
	// 2�� ����� �繫��(��2) �̺�Ʈ �Լ�
	// �Ĵ� ���ο� ����� �繫�� ����
	public void playFloor2_2()
	{
		
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
