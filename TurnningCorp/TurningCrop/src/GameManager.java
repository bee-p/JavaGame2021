
import java.io.File;
import java.io.FileInputStream;
import java.util.Random;
import java.util.Scanner;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class GameManager {	
//	private ItemManager itemManager;
//	private Random random;
	
	
	// -------------------------------------------------------------------------------- \\
	
	
	public static void main(String args[]) {
		
		// **아이템은 맵에서 생성하기
		Player player;
		Map maps[][] = new Map[4][5];
		ItemNPC npc = new ItemNPC(); // ItemNPC 변수
		// 5층, 5개의 방(로비 + 방 3개 + 화장실)로 대부분 구성되어있으나
		// 5층은 엔딩 진행의 역할만 수행하므로 5층을 제외한 1~4층을 활동 범위로 잡아
		// Map 객체 배열을 [4][5]의 이차원 배열로 설정함
		// 5층의 경우는 playEvent.playFloor5() 메소드만을 통해 엔딩 이벤트 진행함
		
		File endingFiles[] = new File[3]; // **엑셀로 파일의 데이터 가져오기&저장하기
		
		
		// 플레이어 정보 생성 및 초기화
		player = new Player();
		player.setPosID(10);		// 1층 로비로 위치 초기화
		
		// 맵 정보 생성 및 초기화
		
		
		
		// 	각 객체 정보 파일로 불러오기(읽어오기)
			// ItemNPC 객체의 setQuestAll()함수로 플레이어의 인벤토리에 들어갈 퀘스트 정보 저장
		try {
				FileInputStream QuestScriptFile = new FileInputStream("Quest Script.xlsx"); // 파일 경로로 파일 읽어오기
				XSSFWorkbook QuestScriptWB = new XSSFWorkbook(QuestScriptFile);
				Quest[] quest = new Quest[6]; // Quest Script 저장할 quest 배열 변수
				
				int rownum = 0; // 행 인덱스
				int cellnum = 0; // 열 인덱스
				int questIndex = 0; // 퀘스트 배열 인덱스
				
				XSSFSheet QuestScriptSheet = QuestScriptWB.getSheetAt(0); // 0번째 시트 가져오기
				
				int rows = QuestScriptSheet.getPhysicalNumberOfRows(); // 사용자가 입력한 엑셀 row수 가져오기
				for (rownum = 1; rownum < rows; rownum++) {
					XSSFRow row = QuestScriptSheet.getRow(rownum); // row
					if (row != null) {
						int cells = row.getPhysicalNumberOfCells(); // 해당 row에서 사용자가 입력한 cell수 가져오기
						for (cellnum = 1; cellnum <= cells; cellnum++) {
							XSSFCell cell = row.getCell(cellnum); // 사용자가 입력한 cell값 가져오기
							
							String description = ""; // Quest Script 내용이 할당될 변수
							if (cell == null) { // 빈 셀일 경우
								continue;
							}
							else {
								switch (cell.getCellType()) {
								case FORMULA:
									description = cell.getCellFormula();
									break;
								case NUMERIC:
									description = cell.getNumericCellValue() + "";
									break;
								case STRING:
									description = cell.getStringCellValue() + "";
									break;
								case BLANK:
									description = cell.getBooleanCellValue() + "";
									break;
								case ERROR:
									description = cell.getErrorCellValue() + "";
									break;
								}
							}
							quest[questIndex].setDescription(description);
							questIndex++;
						}
					}
				}
				npc.setQuestAll(quest); // ItemNpc 객체에 모든 Quest Script 내용 할당하기 
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		// 플레이시 필요한 객체 생성
		PlayEvent playEvent = new PlayEvent(maps, player);		// 이벤트 객체 생성
		Scanner scan = new Scanner(System.in);					// 스캐너 객체 생성
		int num = 0;											// 선택지 저장
		
		
		// 게임 시작
		while(true)
		{	
			playEvent.setGoTitle(false);	// 타이틀 이동 판별 초기화
			
			// 메인 메뉴 출력
			System.out.println("<< Turning.corp >>\n");
			System.out.println("1. 새 게임");
			System.out.println("2. 이어하기");
			System.out.println("3. 종료");
			
			num = scan.nextInt();
			
			if (num == 1 || num == 2)	// 게임 시작 선택
			{
				if (num == 1)		// 새 게임
				{
					// 1. 로컬에 새 파일(새 게임 데이터) 저장
					
					// 2. 인트로 스크립트 출력
					// 기본 데이터들은 사전에(게임 시작 전에) 생성했다는 가정 하에 진행
				}
				else if (num == 2)	// 게임 이어하기
				{
					// 로컬에 저장된 게임 데이터 불러오기
				}
				
				
				// 본격적인 게임 진행
				// goTitle이 true가 되면 반복문 탈출,
				// (타이틀) 메인 메뉴 출력으로 이동함
				while(!playEvent.getGoTitle())
				{
					// 현재 위치 검사, 해당 위치에 따른 이벤트 발동
					// posID의 첫 번째 숫자(1~5)는 층, 두 번째 숫자(0~4)는 방 위치를 의미함
					// 방 위치(두 번째 숫자)에서 0은 로비, 1~3은 일반 방, 4는 화장실을 의미
					switch(player.getPosID())
					{	// 1층 로비 - 저장, 타이틀로 돌아갈 수 있음
						case 10:
							playEvent.playFloor1_0();
							break;
						
						// 1층 첫 번째 방
						case 11:
							playEvent.playFloor1_1();
							break;
							
						// 1층 두 번째 방
						case 12:
							playEvent.playFloor1_2();
							break;
							
						// 1층 세 번째 방
						case 13:
							playEvent.playFloor1_3();
							break;
						
						// 1층 화장실
						case 14:
							playEvent.playFloor1_4();
							break;
							
						// 2층 로비 - 저장, 타이틀로 돌아갈 수 있음
						case 20:
							playEvent.playFloor2_0();
							break;
						
						// 2층 첫 번째 방
						case 21:
							playEvent.playFloor2_1();
							break;
							
						// 2층 두 번째 방
						case 22:
							playEvent.playFloor2_2();
							break;
							
						// 2층 화장실
						case 24:
							playEvent.playFloor2_4();
							break;
							
						// 3층 로비 - 저장, 타이틀로 돌아갈 수 있음
						case 30:
							playEvent.playFloor3_0();
							break;
						
						// 3층 첫 번째 방
						case 31:
							playEvent.playFloor3_1();
							break;
							
						// 3층 두 번째 방
						case 32:
							playEvent.playFloor3_2();
							break;
							
						// 3층 세 번째 방
						case 33:
							playEvent.playFloor3_3();
							break;
							
						// 3층 화장실
						case 34:
							playEvent.playFloor3_4();
						
						// 4층 로비 - 저장, 타이틀로 돌아갈 수 있음
						case 40:
							playEvent.playFloor4_0();
							break;
						
						// 4층 첫 번째 방
						case 41:
							playEvent.playFloor4_1();
							break;
							
						// 4층 두 번째 방
						case 42:
							playEvent.playFloor4_2();
							break;
							
						// 4층 세 번째 방
						case 43:
							playEvent.playFloor4_3();
							break;
							
						// 4층 화장실
						case 44:
							playEvent.playFloor4_4();
							break;
						
						// 5층 엔딩
						case 50:
							playEvent.playFloor5();
							break;
					}
				}
			}
			else if (num == 3)	// 게임 종료
			{
				// 현재 게임 데이터를 로컬에 저장
				
				System.out.println("게임을 종료합니다.");
				break;
			}
			else
			{
				// 그 외 선택지 오기입
			}
		}
		
		
		// 플레이
		//	=> 플레이어의 위치 파악하기
		//		==> 맵클래스에서 엘리베이터로 이동했을 떄, 그 값을 전달받아서 위치 파악하기
//		while(currentFloor >=0 && currentFloor <=3)
//		{
//		    //플레이중
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
//		intro(); // 게임 인트로
//		for (; isPlay;) {
//			play();
//		}
//	}
//	
//	// 플레이어의 정보 생성
//	private void intro() {
//		player = new Player("감자"); // 플레이어 생성
//		player.saveInventory(itemManager.getItem(0)); // 초기 아이템 인벤토리에 저장
//	}
//	
//	private void play() {
//		// 게임 플레이~~
//		// switch-case문으로 map의 어떤 목적지에 달성했다면 클리어! or 사물보기..? => 이건 map에서 하는건가
//	}
//	
//	private void checkPlay(int num) {
//		// 1. 이동, 2. 플레이어 정보 보기, 3. 인벤토리, 4. 아이템 사용, 5. 아이템 정보 보기, 6. 게임 종료 => 퀘스트 보기...?
//	}
	
//	// 평상시에 아이템 사용
//	private void useItem(Player player) {
//		
//	}
//	
//	// 배틀시 아이템 사용
//	private void useItem(Player player, BattleManager battle) {
//		
//	}
}
