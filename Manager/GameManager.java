
import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class GameManager {	
	// private ItemManager itemManager;
	// private Random random;
	
	public static void main(String args[]) {
		
		// **�������� �ʿ��� �����ϱ�
		Player player;
		Map maps[] = new Map[4];
		int happyEndingPoint = 0; // ���� �Ǻ��� �� ����ϴ� ����Ʈ ��
		File endingFiles[] = new File[3]; // **������ ������ ������ ��������&�����ϱ�
		int currentFloor = 1; // �÷��̾��� ��ġ
		
		// �÷��̾� ���� �ʱ�ȭ
		player = new Player("?");
		
		// �� ���� �ʱ�ȭ
		
		// ����ȭ��
		// 	=> �÷��� �ϱ� or ���� �����ϱ�
		// 	���Ϸ� �ҷ�����
		
		
		
		// �÷���
		//	=> �÷��̾��� ��ġ �ľ��ϱ�
		//		==> ��Ŭ�������� ���������ͷ� �̵����� ��, �� ���� ���޹޾Ƽ� ��ġ �ľ��ϱ�
		while(currentFloor >=0 && currentFloor <=3)
		{
		    //�÷�����
		}

		
		// ����ȭ��
		// 	=> 1. ��忣��: ȸ�μ��� �� ������ ���� ���
		//	=> 2. �븻����: ȸ�μ����� �� ��Ƽ� ������ ����
		// 	=> 3. ������: ���ǵ��� �� �÷��� ����Ŀ� ����
		// 	===> ���� �Ǻ��� �� �����ۺ��� ����Ʈ�� �ο��Ͽ�, ���� ����Ʈ�� ������ ���� ���� ����
		// 	===> ����Ʈ�� �ʿ��� ������ ����Ʈ �����ָ� ��.
		
		if(player.getCurrentRank() == 'S'){
			// Ʈ�翣��
			// ���Ͽ��� �ҷ�����(�б�)
		}
		else if(happyEndingPoint == 3){
			//���ǿ���
			// ���Ͽ��� �ҷ�����
		}
		else{
			//�븻 ����
			// ���Ͽ��� �ҷ�����
		}

		
	}
	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private boolean isPlay = true;
	
	public GameManager() {
		random = new Random();
	}
	
	public void startGame() {
		intro(); // ���� ��Ʈ��
		for (; isPlay;) {
			play();
		}
	}
	
	// �÷��̾��� ���� ����
	private void intro() {
		player = new Player("����"); // �÷��̾� ����
		player.saveInventory(itemManager.getItem(0)); // �ʱ� ������ �κ��丮�� ����
	}
	
	private void play() {
		// ���� �÷���~~
		// switch-case������ map�� � �������� �޼��ߴٸ� Ŭ����! or �繰����..? => �̰� map���� �ϴ°ǰ�
	}
	
	private void checkPlay(int num) {
		// 1. �̵�, 2. �÷��̾� ���� ����, 3. �κ��丮, 4. ������ ���, 5. ������ ���� ����, 6. ���� ���� => ����Ʈ ����...?
	}
	
	// ���ÿ� ������ ���
	private void useItem(Player player) {
		
	}
	
	// ��Ʋ�� ������ ���
	private void useItem(Player player, BattleManagement battle) {
		
	}
}
