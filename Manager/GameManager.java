
import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class GameManager {	
	// private ItemManager itemManager;
	// private Random random;
	
	public static void main(String args[]) {
		
		// **아이템은 맵에서 생성하기
		Player player;
		Map maps[] = new Map[4];
		int happyEndingPoint = 0; // 엔딩 판별할 때 사용하는 포인트 값
		File endingFiles[] = new File[3]; // **엑셀로 파일의 데이터 가져오기&저장하기
		int currentFloor = 1; // 플레이어의 위치
		
		// 플레이어 정보 초기화
		player = new Player("?");
		
		// 맵 정보 초기화
		
		// 시작화면
		// 	=> 플레이 하기 or 게임 종료하기
		// 	파일로 불러오기
		
		
		
		// 플레이
		//	=> 플레이어의 위치 파악하기
		//		==> 맵클래스에서 엘리베이터로 이동했을 떄, 그 값을 전달받아서 위치 파악하기
		while(currentFloor >=0 && currentFloor <=3)
		{
		    //플레이중
		}

		
		// 엔딩화면
		// 	=> 1. 배드엔딩: 회로선을 다 모으지 못한 경우
		//	=> 2. 노말엔딩: 회로선까지 다 모아서 사장을 성불
		// 	=> 3. 진엔딩: 평판도를 다 올려서 사장식에 오름
		// 	===> 엔딩 판별은 각 아이템별로 포인트를 부여하여, 일정 포인트를 얻으면 엔딩 보기 가능
		// 	===> 포인트는 맵에서 아이템 포인트 더해주면 됨.
		
		if(player.getCurrentRank() == 'S'){
			// 트루엔딩
			// 파일에서 불러오기(읽기)
		}
		else if(happyEndingPoint == 3){
			//해피엔딩
			// 파일에서 불러오기
		}
		else{
			//노말 엔딩
			// 파일에서 불러오기
		}

		
	}
	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private boolean isPlay = true;
	
	public GameManager() {
		random = new Random();
	}
	
	public void startGame() {
		intro(); // 게임 인트로
		for (; isPlay;) {
			play();
		}
	}
	
	// 플레이어의 정보 생성
	private void intro() {
		player = new Player("감자"); // 플레이어 생성
		player.saveInventory(itemManager.getItem(0)); // 초기 아이템 인벤토리에 저장
	}
	
	private void play() {
		// 게임 플레이~~
		// switch-case문으로 map의 어떤 목적지에 달성했다면 클리어! or 사물보기..? => 이건 map에서 하는건가
	}
	
	private void checkPlay(int num) {
		// 1. 이동, 2. 플레이어 정보 보기, 3. 인벤토리, 4. 아이템 사용, 5. 아이템 정보 보기, 6. 게임 종료 => 퀘스트 보기...?
	}
	
	// 평상시에 아이템 사용
	private void useItem(Player player) {
		
	}
	
	// 배틀시 아이템 사용
	private void useItem(Player player, BattleManagement battle) {
		
	}
}
