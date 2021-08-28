
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
		Map maps[][] = new Map[4][5];
		// 5��, 5���� ��(�κ� + �� 3�� + ȭ���)�� ��κ� �����Ǿ�������
		// 5���� ���� ������ ���Ҹ� �����ϹǷ� 5���� ������ 1~4���� Ȱ�� ������ ���
		// Map ��ü �迭�� [4][5]�� ������ �迭�� ������
		// 5���� ���� playEvent.playFloor5() �޼ҵ常�� ���� ���� �̺�Ʈ ������
		
		File endingFiles[] = new File[3]; // **������ ������ ������ ��������&�����ϱ�
		
		
		// �÷��̾� ���� ���� �� �ʱ�ȭ
		player = new Player();
		player.setPosID(10);		// 1�� �κ�� ��ġ �ʱ�ȭ
		
		// �� ���� ���� �� �ʱ�ȭ
		
		
		
		// 	�� ��ü ���� ���Ϸ� �ҷ�����(�о����)
		
		
		// �÷��̽� �ʿ��� ��ü ����
		PlayEvent playEvent = new PlayEvent(maps, player);		// �̺�Ʈ ��ü ����
		Scanner scan = new Scanner(System.in);					// ��ĳ�� ��ü ����
		int num = 0;											// ������ ����
		
		
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
					// posID�� ù ��° ����(1~5)�� ��, �� ��° ����(0~4)�� �� ��ġ�� �ǹ���
					// �� ��ġ(�� ��° ����)���� 0�� �κ�, 1~3�� �Ϲ� ��, 4�� ȭ����� �ǹ�
					switch(player.getPosID())
					{	// 1�� �κ� - ����, Ÿ��Ʋ�� ���ư� �� ����
						case 10:
							playEvent.playFloor1_0();
							break;
						
						// 1�� ù ��° ��
						case 11:
							playEvent.playFloor1_1();
							break;
							
						// 1�� �� ��° ��
						case 12:
							playEvent.playFloor1_2();
							break;
							
						// 1�� �� ��° ��
						case 13:
							playEvent.playFloor1_3();
							break;
						
						// 1�� ȭ���
						case 14:
							playEvent.playFloor1_4();
							break;
							
						// 2�� �κ� - ����, Ÿ��Ʋ�� ���ư� �� ����
						case 20:
							playEvent.playFloor2_0();
							break;
						
						// 2�� ù ��° ��
						case 21:
							playEvent.playFloor2_1();
							break;
							
						// 2�� �� ��° ��
						case 22:
							playEvent.playFloor2_2();
							break;
							
						// 2�� ȭ���
						case 24:
							playEvent.playFloor2_4();
							break;
							
						// 3�� �κ� - ����, Ÿ��Ʋ�� ���ư� �� ����
						case 30:
							playEvent.playFloor3_0();
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
							
						// 3�� ȭ���
						case 34:
							playEvent.playFloor3_4();
						
						// 4�� �κ� - ����, Ÿ��Ʋ�� ���ư� �� ����
						case 40:
							playEvent.playFloor4_0();
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
							
						// 4�� ȭ���
						case 44:
							playEvent.playFloor4_4();
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
