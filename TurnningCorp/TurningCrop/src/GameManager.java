
import java.io.*;
import java.util.Random;
import java.util.Scanner;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class GameManager {	
//	private ItemManager itemManager;
//	private Random random;
	
	// -------------------------------------------------------------------------------- \\
	//saveFile
	public static void saveNewPlayerInfo(Player player, String playerName)
	{
		//파일입력
		XSSFWorkbook xssfWb = null; //엑셀 파일 초기화
		XSSFSheet xssfSheet = null; //엑셀 시트 초기화
		XSSFRow xssfRow = null; //행 초기화
		XSSFCell xssfCell = null; //셀 초기화
		
		xssfWb = new XSSFWorkbook();
		xssfSheet = xssfWb.createSheet(playerName);
		int rowNum = 0; //행의 개수
		
		//기본 틀 설정
		XSSFCellStyle tableCellStyle = xssfWb.createCellStyle(); //스타일 설정
		tableCellStyle.setBorderBottom(BorderStyle.THICK); //아랫선 두껍게
		
		xssfRow = xssfSheet.createRow(rowNum); //0번째 행
		
		xssfCell = xssfRow.createCell((short)0); //cell만들기
		xssfCell.setCellStyle(tableCellStyle); //스타일적용
		xssfCell.setCellValue("Name"); //데이터
		xssfCell = xssfRow.createCell((short)1);
		xssfCell.setCellStyle(tableCellStyle);
		xssfCell.setCellValue("Hp");
		xssfCell = xssfRow.createCell((short)2);
		xssfCell.setCellStyle(tableCellStyle);
		xssfCell.setCellValue("AttackPower");
		xssfCell = xssfRow.createCell((short)3);
		xssfCell.setCellStyle(tableCellStyle); 
		xssfCell.setCellValue("DefensivePower"); 
		xssfCell = xssfRow.createCell((short)4);
		xssfCell.setCellStyle(tableCellStyle);
		xssfCell.setCellValue("Reputation"); 
		xssfCell = xssfRow.createCell((short)5);
		xssfCell.setCellStyle(tableCellStyle); 
		xssfCell.setCellValue("CurrentIndex"); 
		xssfCell = xssfRow.createCell((short)6);
		xssfCell.setCellStyle(tableCellStyle); 
		xssfCell.setCellValue("Inventory Item Name");
		xssfCell = xssfRow.createCell((short)7); 
		xssfCell.setCellStyle(tableCellStyle);
		xssfCell.setCellValue("Inventory Item Number");
		xssfCell = xssfRow.createCell((short)8); 
		xssfCell.setCellStyle(tableCellStyle);
		xssfCell.setCellValue("PosID");
		xssfCell = xssfRow.createCell((short)9);
		xssfCell.setCellStyle(tableCellStyle); 
		xssfCell.setCellValue("Quest Name");
		xssfCell = xssfRow.createCell((short)10);
		xssfCell.setCellStyle(tableCellStyle); 
		xssfCell.setCellValue("Quest Condition");
		xssfCell = xssfRow.createCell((short)11);
		xssfCell.setCellStyle(tableCellStyle); 
		xssfCell.setCellValue("Quest Complete");
		
		xssfRow = xssfSheet.createRow(rowNum++); //다음번째 행
		xssfCell = xssfRow.createCell((short)0);
		xssfCell.setCellValue(player.getName()); //데이터
		xssfCell = xssfRow.createCell((short)1);
		xssfCell.setCellValue(player.getHp());
		xssfCell = xssfRow.createCell((short)2);
		xssfCell.setCellValue(player.getAttackPower());
		xssfCell = xssfRow.createCell((short)3);
		xssfCell.setCellValue(player.getDefensivePower());
		xssfCell = xssfRow.createCell((short)4);
		xssfCell.setCellValue(player.getReputation());
		xssfCell = xssfRow.createCell((short)5);
		xssfCell.setCellValue(player.getCurrentIndex());
		xssfCell = xssfRow.createCell((short)6);
		int inventoryLength = player.getInventoryLength();
		for(int i=0; i<inventoryLength; i++)
		{
			xssfCell.setCellValue(player.getInventory()[i].item.getName());
			xssfRow = xssfSheet.createRow(rowNum++);
		}
		xssfCell = xssfRow.createCell((short)7);
		for(int i=0; i<inventoryLength; i++)
		{
			xssfCell.setCellValue(player.getInventory()[i].count);
			xssfRow = xssfSheet.createRow(rowNum++);
		}
		xssfCell = xssfRow.createCell((short)8);
		xssfCell.setCellValue(player.getPosID());
		xssfCell = xssfRow.createCell((short)9);
		int questArrayLength = player.getQuestArrayLength();
		for(int i=0; i<questArrayLength; i++)
		{
			xssfCell.setCellValue(player.getQuestArray()[i].getQuestName());
			xssfRow = xssfSheet.createRow(rowNum++);
		}
		xssfCell = xssfRow.createCell((short)10);
		for(int i=0; i<questArrayLength; i++)
		{
			xssfCell.setCellValue(player.getQuestArray()[i].getCondition());
			xssfRow = xssfSheet.createRow(rowNum++);
		}
		xssfCell = xssfRow.createCell((short)11);
		for(int i=0; i<questArrayLength; i++)
		{
			xssfCell.setCellValue(player.getQuestArray()[i].getCompletion());
			xssfRow = xssfSheet.createRow(rowNum++);
		}
		
		//파일생성
		File file;
		try {
			String path = "";
			file = new File(path + "/saveFile.xlsx"); // 저장경로/파일이름.xlsx
			
			FileOutputStream fos = new FileOutputStream(file); 
			xssfWb.write(fos);
			fos.close(); //엑셀 파일 생성 성공
		}
		catch(IOException e)
		{
			e.printStackTrace(); //엑셀 파일 생성 실패
		}
		
	}
	
	public static int findSheet(FileInputStream fis, String playerName) throws IOException
	{
		XSSFWorkbook Wb = new XSSFWorkbook(fis);
		int sheetsNum = Wb.getNumberOfSheets(); //파일의 시트 총 개수
		for(int i=0; i<sheetsNum; i++)
		{
			if(Wb.getSheetName(i).equals(playerName)) //시트 이름이 같은 것 찾기
			{
				return i; //해당 인덱스 반환
			}
		}
		return -1; //찾는 시트가 존재하지 않음
	}
	
	// 출입기에서 사원증을 사용할 경우, 현재까지 진행된 player의 객체 정보, 인벤토리, 퀘스트 저장
	// 현재 진행 중인 플레이어 시트 확인 필요
	// playerName Sheet에 정보 저장
	public static void useEmployeeCard(Player player) {
		
		int sheetNum = 0;
		try {
			FileInputStream fis = new FileInputStream("saveFile.xlsx"); // 파일 읽어오기
			XSSFWorkbook Wb = new XSSFWorkbook(fis);
			// 진행하고 있던 계정 정보가 저장된 시트 찾기
			sheetNum = findSheet(fis, player.getName());
			
			// 진행하고 있던 계정 정보가 저장된 시트를 찾은 경우
			XSSFSheet sheet = Wb.getSheetAt(sheetNum); // sheet변수에 해당 시트 정보 저장
			
			// 플레이어 객체 정보 옮기기
			int rowIndex = 1;
			int cellIndex = 0;
			
			XSSFRow row = sheet.getRow(rowIndex);
			XSSFCell cell = row.getCell(cellIndex); // cell변수에 해당 셀 정보 저장
			
			// HP 갱신
			cellIndex++;
			cell = row.getCell(cellIndex);
			cell.setCellValue(player.getHp());
			
			// AttackPower 갱신
			cellIndex++;
			cell = row.getCell(cellIndex);
			cell.setCellValue(player.getAttackPower());
			
			// DefensivePower 갱신
			cellIndex++;
			cell = row.getCell(cellIndex);
			cell.setCellValue(player.getDefensivePower());
			
			// Reputation 갱신
			cellIndex++;
			cell = row.getCell(cellIndex);
			cell.setCellValue(player.getReputation());
			
			// CurrentIndex 갱신
			cellIndex++;
			cell = row.getCell(cellIndex);
			cell.setCellValue(player.getCurrentIndex());
			
			// Inventory Item Name 갱신
			cellIndex++;
			int rowsNum = sheet.getPhysicalNumberOfRows(); // 시트 당 행의 개수
			for (rowIndex = 1; rowIndex < rowsNum; rowIndex++) {
				row = sheet.getRow(rowIndex);
				cell = row.getCell(cellIndex);
				if (cell != null) { // 셀 내용이 있는 경우
					cell.setCellValue(""); // null값으로 초기화
				}
			}
			int inventoryLength = player.getInventoryLength();
			for (int i = 1; i < inventoryLength; i++) { // 초기화된 셀에 새로운 정보 넣기
				cell = row.getCell(cellIndex);
				cell.setCellValue(player.getInventory()[i - 1].item.getName());
				row = sheet.getRow(i++);
			}
			
			// Inventory Item Count 갱신
			cellIndex++;
			for (rowIndex = 1; rowIndex < rowsNum; rowIndex++) {
				row = sheet.getRow(rowIndex);
				cell = row.getCell(cellIndex);
				if (cell != null) { // 셀 내용이 있는 경우
					cell.setCellValue(""); // null값으로 초기화
				}
			}
			for (int i = 1; i < inventoryLength; i++) { // 초기화된 셀에 새로운 정보 넣기
				cell = row.getCell(cellIndex);
				cell.setCellValue(player.getInventory()[i - 1].count);
				row = sheet.getRow(i++);
			}
			
			// PosID 갱신
			cellIndex++;
			cell = row.getCell(cellIndex);
			cell.setCellValue(player.getPosID());
			
			// Quest Name 갱신
			cellIndex++;
			int questArrayLength = player.getQuestArrayLength();
			for (rowIndex = 1; rowIndex < rowsNum; rowIndex++) {
				row = sheet.getRow(rowIndex);
				cell = row.getCell(cellIndex);
				if (cell != null) { // 셀 내용이 있는 경우
					cell.setCellValue(""); // null값으로 초기화
				}
			}
			for (int i = 1; i < questArrayLength; i++) { // 초기화된 셀에 새로운 정보 넣기
				cell = row.getCell(cellIndex);
				cell.setCellValue(player.getQuestArray()[i - 1].getQuestName());
				row = sheet.getRow(i++);
			}
			
			// Quest Condition 갱신 (퀘스트 받음 여부)
			cellIndex++;
			for (rowIndex = 1; rowIndex < rowsNum; rowIndex++) {
				row = sheet.getRow(rowIndex);
				cell = row.getCell(cellIndex);
				if (cell != null) { // 셀 내용이 있는 경우
					cell.setCellValue(""); // null값으로 초기화
				}
			}
			for (int i = 1; i < questArrayLength; i++) { // 초기화된 셀에 새로운 정보 넣기
				cell = row.getCell(cellIndex);
				cell.setCellValue(player.getQuestArray()[i - 1].getCondition());
				row = sheet.getRow(i++);
			}
			
			// Quest Complete 갱신 (퀘스트 완료 여부)
			cellIndex++;
			for (rowIndex = 1; rowIndex < rowsNum; rowIndex++) {
				row = sheet.getRow(rowIndex);
				cell = row.getCell(cellIndex);
				if (cell != null) { // 셀 내용이 있는 경우
					cell.setCellValue(""); // null값으로 초기화
				}
			}
			for (int i = 1; i < questArrayLength; i++) { // 초기화된 셀에 새로운 정보 넣기
				cell = row.getCell(cellIndex);
				cell.setCellValue(player.getQuestArray()[i - 1].getCompletion());
				row = sheet.getRow(i++);
			}
			
			// 진행 중이던 시트에 새로운 값 저장시키기
			FileOutputStream fos = new FileOutputStream("saveFile.xlsx");
			Wb.write(fos);
			fos.close(); //엑셀 파일 생성 성공
			// System.out.println("엑셀 시트 갱신 성공");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[], BorderStyle BorderStyle) throws FileNotFoundException {
		// **아이템은 맵에서 생성하기
		//saveFile 저장할 때 플레이어가 가진 아이템 정보가 필요함.
		Item item;
		Player player;
		Map maps[][] = new Map[4][5];
		// 5층, 5개의 방(로비 + 방 3개 + 화장실)로 대부분 구성되어있으나
		// 5층은 엔딩 진행의 역할만 수행하므로 5층을 제외한 1~4층을 활동 범위로 잡아
		// Map 객체 배열을 [4][5]의 이차원 배열로 설정함
		// 5층의 경우는 playEvent.playFloor5() 메소드만을 통해 엔딩 이벤트 진행함
		
		ItemNPC[] itemNPC = new ItemNPC[6]; 		// 객체 배열로 수정 완료
		SkillNPC skillNPC = new SkillNPC();	// 스킬NPC는 한 명이므로 하나만 생성함
		
		File endingFiles[] = new File[3]; // **엑셀로 파일의 데이터 가져오기&저장하기
		
		
		// 플레이어 정보 생성 및 초기화
		player = new Player("이름", 100, 100, 100, 0, 0, 100, 0);
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
				
				// 각 NPC객체에 Quest Script 내용 할당하기 
				int npc1Index = 0, npc2Index = 0, npc3Index = 0,
						npc4Index = 0, npc5Index = 0, npc6Index = 0; // 각 NPC별 퀘스트 순서 인덱스
				
				for (int index = 0; index < quest.length; index++) {
					if (index == 0 || index == 1) { // 1층 인간NPC
						itemNPC[0].setQuest(npc1Index, quest[index]);
						++npc1Index;
					}
					else if (index == 2 || index == 3) { // 2층 몬스터NPC
						itemNPC[1].setQuest(npc2Index, quest[index]);
						++npc2Index;
					}
					else if (index == 4 || index == 5) { // 2층 인간NPC(1)
						itemNPC[2].setQuest(npc3Index, quest[index]);
						++npc3Index;
					}
					else if (index == 6 || index == 7) { // 2층 인간NPC(2)
						itemNPC[3].setQuest(npc4Index, quest[index]);
						++npc4Index;
					}
					else if (index == 8) { // 3층 몬스터NPC(1)
						itemNPC[4].setQuest(npc5Index, quest[index]);
						++npc5Index;
					}
					else if (index == 9 || index == 10) { // 3층 몬스터NPC(2)
						itemNPC[5].setQuest(npc6Index, quest[index]);
						++npc6Index;
					}
				}
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
			scan.next();
			
			if (num == 1 || num == 2)	// 게임 시작 선택
			{
				if (num == 1)		// 새 게임
				{
					// 1. 로컬에 새 파일(새 게임 데이터) 저장
					System.out.print("플레이어의 이름은? : ");
					String playerName = scan.nextLine();
					player.setName(playerName); //이름 설정
					saveNewPlayerInfo(player, playerName); //파일 생성
					
					// 2. 인트로 스크립트 출력
					System.out.println("안녕하십니까 " + player.getFullName() + "님? 어서오세요.");
					// 기본 데이터들은 사전에(게임 시작 전에) 생성했다는 가정 하에 진행
				}
				else if (num == 2)	// 게임 이어하기
				{
					// 로컬에 저장된 게임 데이터 불러오기
					System.out.print("이어서 할 플레이어의 이름을 입력하세요. : ");
					String playerName = scan.nextLine();
					int sheetNum = 0;
					FileInputStream fis;
					try {
						fis = new FileInputStream("saveFile.xlsx"); //파일 읽어오기
						XSSFWorkbook Wb = new XSSFWorkbook(fis);
						//이어서 할 플레이어의 정보가 저장된 해당 시트 찾기
						sheetNum = findSheet(fis, playerName); 
						
					} catch (IOException e) {
						e.printStackTrace();
					}
					if(sheetNum == -1) //해당 시트가 존재하지 않음
					{
						System.out.println("해당 플레이어 정보는 존재하지 않습니다.");
						continue; //메인 메뉴로 돌아가기
					}
					else //해당 시트를 찾음
					{
						XSSFWorkbook Wb = new XSSFWorkbook(fis);
						XSSFSheet sheet = Wb.getSheetAt(sheetNum); //sheet변수에 해당 sheet 저장
						
						//플레이어 객체에 정보 옮기기
						int rowIndex = 1;
						int cellIndex = 0;
						
						//Name
						XSSFRow row = sheet.getRow(rowIndex);
						XSSFCell cell = row.getCell(cellIndex); //cell변수에 해당 cell 저장
						player.setName(cell.getStringCellValue());
						
						//HP
						cellIndex++;
						cell = row.getCell(cellIndex);
						player.setHp((int)cell.getNumericCellValue());
						
						//AttackPower
						cellIndex++;
						cell = row.getCell(cellIndex);
						player.setAttackPower((int)cell.getNumericCellValue());
						
						//DefensivePower
						cellIndex++;
						cell = row.getCell(cellIndex);
						player.setDefensivePower((int)cell.getNumericCellValue());
						
						//Reputation
						cellIndex++;
						cell = row.getCell(cellIndex);
						player.setReputation((int)cell.getNumericCellValue());
						
						//CurrentIndex
						cellIndex++;
						cell = row.getCell(cellIndex);
						player.setCurrentIndex((int)cell.getNumericCellValue());
						
						//inventory item name
						cellIndex++;
						int rowsNum = sheet.getPhysicalNumberOfRows(); //시트 당 행의 개수
						int inventoryIndex = 0;
						for(rowIndex=1; rowIndex<rowsNum; rowIndex++)
						{
							row = sheet.getRow(rowIndex);
							cell = row.getCell(cellIndex);
							if(cell != null) //셀 내용이 있는 경우
							{
								player.getInventory()[inventoryIndex].item.setName(cell.getStringCellValue()); //인벤토리에 저장
								inventoryIndex++;
							}
							else //셀 값이 비어있을 경우
							{
								continue;
							}
						}
						
						//inventory item count
						cellIndex++;
						inventoryIndex = 0;
						for(rowIndex=1; rowIndex<rowsNum; rowIndex++)
						{
							row = sheet.getRow(rowIndex);
							cell = row.getCell(cellIndex);
							if(cell != null) //셀 내용이 있는 경우
							{
								player.getInventory()[inventoryIndex].count=(int)cell.getNumericCellValue(); //인벤토리에 저장
								inventoryIndex++;
							}
							else //셀 값이 비어있을 경우
							{
								continue;
							}
						}
						
						//posID
						cellIndex++;
						cell = row.getCell(cellIndex);
						player.setPosID((int)cell.getNumericCellValue());
						
						//Quest Name
						cellIndex++;
						int questIndex = 0;
						int questArrayLength = player.getQuestArrayLength();
						for(rowIndex=1; rowIndex<questArrayLength; rowIndex++)
						{
							row = sheet.getRow(rowIndex);
							cell = row.getCell(cellIndex);
							if(cell != null) //셀 내용이 있는 경우
							{
								player.getQuestArray()[questIndex].setQuestName(cell.getStringCellValue());
								questIndex++;
							}
							else //셀 값이 비어있을 경우
							{
								continue;
							}
						}
						
						//Quest Condition
						cellIndex++;
						questIndex = 0;
						for(rowIndex=1; rowIndex<questArrayLength; rowIndex++)
						{
							row = sheet.getRow(rowIndex);
							cell = row.getCell(cellIndex);
							if(cell != null) //셀 내용이 있는 경우
							{
								player.getQuestArray()[questIndex].setCondition(cell.getBooleanCellValue());
								questIndex++;
							}
							else //셀 값이 비어있을 경우
							{
								continue;
							}
						}
						
						//Quest Complete
						cellIndex++;
						questIndex = 0;
						for(rowIndex=1; rowIndex<questArrayLength; rowIndex++)
						{
							row = sheet.getRow(rowIndex);
							cell = row.getCell(cellIndex);
							if(cell != null) //셀 내용이 있는 경우
							{
								player.getQuestArray()[questIndex].setCompletion(cell.getBooleanCellValue());
								questIndex++;
							}
							else //셀 값이 비어있을 경우
							{
								continue;
							}
						}
						
						System.out.println("해당 데이터를 모두 불러오는데 성공했습니다.");
					}
					
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
							playEvent.playFloor1_3(skillNPC);
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
