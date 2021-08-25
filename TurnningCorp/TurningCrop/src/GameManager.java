
import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class GameManager {	
//	private ItemManager itemManager;
//	private Random random;
	
	
	// -------------------------------------------------------------------------------- \\
	
	
	public static void main(String args[]) {
		
		// **�������� �ʿ��� �����ϱ�
		Player player;
		Map maps[][] = new Map[5][4];
		int happyEndingPoint = 0; // ���� �Ǻ��� �� ����ϴ� ����Ʈ ��
		File endingFiles[] = new File[3]; // **������ ������ ������ ��������&�����ϱ�
		
		PlayEvent playEvent = new PlayEvent();		// �̺�Ʈ ��ü ����
		Scanner scan = new Scanner(System.in);		// ��ĳ�� ��ü ����
		int num = 0;								// ������ ����
		
		
		// �÷��̾� ���� �ʱ�ȭ
		player = new Player();
		player.setPosID(10);		// 1�� �κ�� ��ġ �ʱ�ȭ
		
		// �� ���� �ʱ�ȭ
		
		
		
		// ����ȭ��
		// 	=> �÷��� �ϱ� or ���� �����ϱ�
		// 	���Ϸ� �ҷ�����
		
		
		// ���� ����
		while(true)
		{	
			playEvent.setGoTitle(false);	// Ÿ��Ʋ �̵� �Ǻ� �ʱ�ȭ
			
			// ���� �޴� ���
			System.out.println("<< Turning.corp >>\n");
			System.out.println("1. �� ����");
			System.out.println("2. �̾��ϱ�");
			System.out.println("3. ����");
			
			num = scan.nextInt();
			
			if (num == 1 || num == 2)	// ���� ���� ����
			{
				if (num == 1)		// �� ����
				{
					// 1. ���ÿ� �� ����(�� ���� ������) ����
					
					// 2. ��Ʈ�� ��ũ��Ʈ ���
					// �⺻ �����͵��� ������(���� ���� ����) �����ߴٴ� ���� �Ͽ� ����
				}
				else if (num == 2)	// ���� �̾��ϱ�
				{
					// ���ÿ� ����� ���� ������ �ҷ�����
				}
				
				
				// �������� ���� ����
				// goTitle�� true�� �Ǹ� �ݺ��� Ż��,
				// (Ÿ��Ʋ) ���� �޴� ������� �̵���
				while(!playEvent.getGoTitle())
				{
					// ���� ��ġ �˻�, �ش� ��ġ�� ���� �̺�Ʈ �ߵ�
					switch(player.getPosID())
					{	// 1�� �κ� - ����, Ÿ��Ʋ�� ���ư� �� ����
						case 10:
							playEvent.playFloor1_0(player, maps);
							break;
						
						// 1�� ù ��° ��
						case 11:
							playEvent.playFloor1_1(maps[0][1]);
							break;
							
						// 1�� �� ��° ��
						case 12:
							playEvent.playFloor1_2();
							break;
							
						// 1�� �� ��° ��
						case 13:
							playEvent.playFloor1_3();
							break;
							
						// 2�� �κ� - ����, Ÿ��Ʋ�� ���ư� �� ����
						case 20:
							playEvent.playFloor2_0(player, maps);
							break;
						
						// 2�� ù ��° ��
						case 21:
							playEvent.playFloor2_1();
							break;
							
						// 2�� �� ��° ��
						case 22:
							playEvent.playFloor2_2();
							break;
							
						// 3�� �κ� - ����, Ÿ��Ʋ�� ���ư� �� ����
						case 30:
							playEvent.playFloor3_0(player, maps);
							break;
						
						// 3�� ù ��° ��
						case 31:
							playEvent.playFloor3_1();
							break;
							
						// 3�� �� ��° ��
						case 32:
							playEvent.playFloor3_2();
							break;
							
						// 3�� �� ��° ��
						case 33:
							playEvent.playFloor3_3();
							break;
						
						// 4�� �κ� - ����, Ÿ��Ʋ�� ���ư� �� ����
						case 40:
							playEvent.playFloor4_0(player, maps);
							break;
						
						// 4�� ù ��° ��
						case 41:
							playEvent.playFloor4_1();
							break;
							
						// 4�� �� ��° ��
						case 42:
							playEvent.playFloor4_2();
							break;
							
						// 4�� �� ��° ��
						case 43:
							playEvent.playFloor4_3();
							break;
						
						// 5�� ����
						case 50:
							playEvent.playFloor5();
							break;
					}
				}
			}
			else if (num == 3)	// ���� ����
			{
				// ���� ���� �����͸� ���ÿ� ����
				
				System.out.println("������ �����մϴ�.");
				break;
			}
			else
			{
				// �� �� ������ ������
			}
		}
		
		
		// �÷���
		//	=> �÷��̾��� ��ġ �ľ��ϱ�
		//		==> ��Ŭ�������� ���������ͷ� �̵����� ��, �� ���� ���޹޾Ƽ� ��ġ �ľ��ϱ�
//		while(currentFloor >=0 && currentFloor <=3)
//		{
//		    //�÷�����
//		}
	}
	
	
	// -------------------------------------------------------------------------------- \\
	
	
//	private boolean isPlay = true;
//	
//	public GameManager() {
//		random = new Random();
//	}
//	
//	public void startGame() {
//		intro(); // ���� ��Ʈ��
//		for (; isPlay;) {
//			play();
//		}
//	}
//	
//	// �÷��̾��� ���� ����
//	private void intro() {
//		player = new Player("����"); // �÷��̾� ����
//		player.saveInventory(itemManager.getItem(0)); // �ʱ� ������ �κ��丮�� ����
//	}
//	
//	private void play() {
//		// ���� �÷���~~
//		// switch-case������ map�� � �������� �޼��ߴٸ� Ŭ����! or �繰����..? => �̰� map���� �ϴ°ǰ�
//	}
//	
//	private void checkPlay(int num) {
//		// 1. �̵�, 2. �÷��̾� ���� ����, 3. �κ��丮, 4. ������ ���, 5. ������ ���� ����, 6. ���� ���� => ����Ʈ ����...?
//	}
	
//	// ���ÿ� ������ ���
//	private void useItem(Player player) {
//		
//	}
//	
//	// ��Ʋ�� ������ ���
//	private void useItem(Player player, BattleManager battle) {
//		
//	}
}
