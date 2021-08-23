import java.util.Scanner;

public class PlayEvent {
	// ��ĳ�� ��ü, ������ ���� ����
	Scanner scan = new Scanner(System.in);
	int num;
	
	// Ÿ��Ʋ �̵� �Ǻ�
	private boolean goTitle;
	
	
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
	private void moveFloor(Player player, Map[][] map)
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
	
	// ----------------------------------------------------------------- \\
	
	// 1�� �κ� �̺�Ʈ �Լ�
	public void playFloor1_0(Player player, Map[][] map)
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
				moveFloor(player, map);
				break;
			}
			else
			{
				// �� �� ������ ó��
			}
		}
	}
	
	// 1�� ������(��1) �̺�Ʈ �Լ�
	public void playFloor1_1(Map map)
	{
		while(true)
		{
			// 1. ��Ʋ....Ȯ�� ������
			
			// 2. ������ ��ũ��Ʈ ���...
			// �����δ�. ���� ���ϴ� �μ��� �����ϸ���ġ �ͼ��� ���ѵ�, ���ϰ� ������ ����� ���� ���Ѵ�. ���̶� �׷���.
			// �տ��� �ͼ��� å�� �迭���� ���δ�. ���߿��� ���� ��� å�� �ɽ����� �ִ�. ��� ���캼��?
			
			// for������ �ش� ���� ���� �繰 �迭 ��� (���ڿ� �Բ�) ����ϱ�..
			for (int i = 0; i < map.getAllObject().length; i++)
			{	// ex) 1. �� å��
				System.out.println((i + 1) + ". " + map.getObject(i).getObjectName());
			}
			System.out.println("(���ϴ� �������� ���� �Է�)");
			
			num = scan.nextInt();
			
			
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
	
	// * * * * * * * * * * * * * \\
	
	// 2�� �κ� �̺�Ʈ �Լ�
	public void playFloor2_0(Player player, Map[][] map)
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
	
	
	// * * * * * * * * * * * * * \\
	
	
	// 3�� �κ� �̺�Ʈ �Լ�
	public void playFloor3_0(Player player, Map[][] map)
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
	
	
	// * * * * * * * * * * * * * \\
	
	
	// 4�� �κ� �̺�Ʈ �Լ�
	public void playFloor4_0(Player player, Map[][] map)
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
