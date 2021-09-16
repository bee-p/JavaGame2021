
import java.io.*;
import java.util.Scanner;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class GameManager {	
//	private ItemManager itemManager = new ItemManager();
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
			xssfWb.close();
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
				Wb.close();
				return i; //해당 인덱스 반환
			}
		}
		
		Wb.close();
		return -1; //찾는 시트가 존재하지 않음
		
		
	}
	
	// 출입기에서 사원증을 사용할 경우, 현재까지 진행된 player의 객체 정보, 인벤토리, 퀘스트 저장
	// 현재 진행 중인 플레이어 시트 확인 필요
	// playerName Sheet에 정보 저장
	public static void useEmployeeCard(Player player) {
		XSSFWorkbook Wb;
		
		int sheetNum = 0;
		try {
			FileInputStream fis = new FileInputStream("saveFile.xlsx"); // 파일 읽어오기
			Wb = new XSSFWorkbook(fis);
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
			Wb.close();
			fos.close(); //엑셀 파일 생성 성공
			// System.out.println("엑셀 시트 갱신 성공");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[], BorderStyle BorderStyle) throws IOException {
		
		final int FLOOR = 4; // 층
		final int ROOM = 5; // 방
		Player player;
		Map maps[][] = new Map[FLOOR][ROOM];
		// 5층, 5개의 방(로비 + 방 3개 + 화장실)로 대부분 구성되어있으나
		// 5층은 엔딩 진행의 역할만 수행하므로 5층을 제외한 1~4층을 활동 범위로 잡아
		// Map 객체 배열을 [4][5]의 이차원 배열로 설정함
		// 5층의 경우는 playEvent.playFloor5() 메소드만을 통해 엔딩 이벤트 진행함
		
		ItemNPC[] itemNPC = new ItemNPC[6]; // 객체 배열로 수정완료!
		SkillNPC skillNPC = new SkillNPC();	// 스킬NPC는 한 명이므로 하나만 생성함
		
		// 플레이어 정보 생성 및 초기화
		player = new Player("이름", 100, 100, 100, 0, 0, 100, 0);
		player.setPosID(10);		// 1층 로비로 위치 초기화
		
		// 맵 정보 생성 및 초기화
		// 각 맵마다 존재하는 사물, 아이템 정보를 엑셀에서 가져와 저장하기
		try {
			FileInputStream Objectfis = new FileInputStream("Object Script.xlsx"); // 사물 , 아이템 스크립트
			XSSFWorkbook ObjectWB = new XSSFWorkbook(Objectfis);
			final int OBJECTNUM = 15;
			MapObject[][] mapObject = new MapObject[FLOOR][OBJECTNUM]; // 사물을 저장할 mapObject 변수, [층][사물 개수]
			
			int rowNum = 0; // 행 인덱스
			int objectIndex1 = 0, objectIndex2 = 0, objectIndex3 = 0, objectIndex4 = 0; // 사물 배열 인덱스
			int itemIndex = 0; // 사물에 속한 아이템 배열 인덱스
			
			XSSFSheet ObjectScriptSheet = ObjectWB.getSheetAt(0); // Object Script 시트 가져오기
			XSSFSheet ItemScriptSheet = ObjectWB.getSheetAt(1); // Item Script 시트 가져오기
			
			ObjectWB.close();
			
			int objectRows = ObjectScriptSheet.getPhysicalNumberOfRows(); // 오브젝트 시트의 row수 저장
			int itemRows = ItemScriptSheet.getPhysicalNumberOfRows(); // 아이템 시트의 row수 저장
			
			for (rowNum = 1; rowNum <= objectRows; rowNum++) { // 제목 건너뛰기
				XSSFRow objectRow = ObjectScriptSheet.getRow(rowNum); // row
				
				// mapObject에 층별로 오브젝트 이름&설명 저장
				switch (objectRow.getCell(0).getStringCellValue()) {
					case "11" :
					case "12" :
					case "14" : { // 각행의 0열에 저장된 값이 1이라면 (1층)
						XSSFCell objectName = objectRow.getCell(2); // 해당 row의 cell에서 name가져오기 (셀 인덱스2)
						mapObject[0][objectIndex1].setObjectName(objectName.getStringCellValue()); // 오브젝트 이름 저장
					
						XSSFCell objectDescription = objectRow.getCell(3); // description 가져오기 (셀 인덱스3)
						mapObject[0][objectIndex1].setDescription(objectDescription.getStringCellValue()); // 오브젝트 설명 저장
						objectIndex1++;
					} break;
					case "21" :
					case "22" : { // 각행의 0열에 저장된 값이 2라면 (2층)
						XSSFCell objectName = objectRow.getCell(2); // 해당 row의 cell에서 name가져오기 (셀 인덱스2)
						mapObject[1][objectIndex2].setObjectName(objectName.getStringCellValue()); // 오브젝트 이름 저장
					
						XSSFCell objectDescription = objectRow.getCell(3); // description 가져오기 (셀 인덱스3)
						mapObject[1][objectIndex2].setDescription(objectDescription.getStringCellValue()); // 오브젝트 설명 저장
						objectIndex2++;
					} break;
					case "31" :
					case "32" :
					case "33" : { // 각행의 0열에 저장된 값이 3이라면 (3층)
						XSSFCell objectName = objectRow.getCell(2); // 해당 row의 cell에서 name가져오기 (셀 인덱스2)
						mapObject[2][objectIndex3].setObjectName(objectName.getStringCellValue()); // 오브젝트 이름 저장
					
						XSSFCell objectDescription = objectRow.getCell(3); // description 가져오기 (셀 인덱스3)
						mapObject[2][objectIndex3].setDescription(objectDescription.getStringCellValue()); // 오브젝트 설명 저장
						objectIndex3++;
					} break;
					case "41" :
					case "42" :
					case "43" : { // 각행의 0열에 저장된 값이 4라면 (4층)
						XSSFCell objectName = objectRow.getCell(2); // 해당 row의 cell에서 name가져오기 (셀 인덱스2)
						mapObject[3][objectIndex4].setObjectName(objectName.getStringCellValue()); // 오브젝트 이름 저장
					
						XSSFCell objectDescription = objectRow.getCell(3); // description 가져오기 (셀 인덱스3)
						mapObject[3][objectIndex4].setDescription(objectDescription.getStringCellValue()); // 오브젝트 설명 저장
						objectIndex4++;
					} break;
				}
			}
			
			// mapObject에 층별 오브젝트에 속해있는 item 이름&설명 저장
			// 이때, 쉽게배우는 악마어, 일기장, 회로선 등 itemManager에 있는 아이템들은 mapObject에 저장X
			// PlayEvent클래스에서 ItemManager 객체 변수를 만든 뒤, getItem()함수를 사용하여 아이템 생성하기!
			for (rowNum = 1; rowNum <= objectRows; rowNum++) {
				XSSFRow objectRow = ObjectScriptSheet.getRow(rowNum); // object row
				for (int rowNum2 = 1; rowNum2 <= itemRows; rowNum2++) { // 제목 건너뛰기
					XSSFRow itemRow = ItemScriptSheet.getRow(rowNum); // item row
				
					// 만약 오브젝트 스크립트의 코드와 아이템 스크립트의 코드가 같다면
					if (objectRow.getCell(1).getStringCellValue().equals(itemRow.getCell(1).getStringCellValue())) {
						XSSFCell objectName = objectRow.getCell(2); // 오브젝트 이름 추출
						
						// mapObject배열에서 해당 이름을 가진 오브젝트 인덱스를 찾아서, item 이름&설명 저장
						for (int i = 0; i < FLOOR; i++) {
							for (int j = 0; j < OBJECTNUM; j++) {
								if (mapObject[i][j].getObjectName().equals(objectName.getStringCellValue())) {
									XSSFCell itemName = itemRow.getCell(2); // 아이템 이름 추출
									XSSFCell itemDescription = itemRow.getCell(3); // 아이템 설명 추출
									
									Item scriptItem = new Item(); // 아이템 변수
									scriptItem.setName(itemName.getStringCellValue()); // 아이템 이름 저장
									scriptItem.setDescription(itemDescription.getStringCellValue()); // 아이템 설명 저장
									scriptItem.setValue(0);
									
									// 해당 mapObject 인덱스에 저장되어 있는 아이템 개수 찾기
									itemIndex = mapObject[i][j].getAllItem().length;
									if (itemIndex == 0) { // 아이템이 없다면
										mapObject[i][j].setItem(scriptItem, itemIndex);
									}
									else if (itemIndex >= 1) { // 아이템이 1개 이상이라면
										mapObject[i][j].setItem(scriptItem, itemIndex + 1);
									}
								}
							}
						}
					}
				}
			}
			
			// maps배열에 층별로 mapObject배열 넣기
			for (rowNum = 1; rowNum <= objectRows; rowNum++) {
				XSSFRow objectRow = ObjectScriptSheet.getRow(rowNum); // object row
				for (int i = 0; i < FLOOR; i++) {
					int floorCode = (FLOOR + 1) * 10;
					for (int j = 1; j < ROOM; j++) {
						++floorCode;
						int mapIndex = 0;
						for (int z = 0; z < OBJECTNUM; z++) {
							// Object Script에 저장되어 있는 Floor값과 floorCode 비교
							if (objectRow.getCell(0).getNumericCellValue() == floorCode) {
								maps[i][j].setObject(mapObject[i][z], mapIndex);
								++mapIndex;
							}
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 	각 객체 정보 파일로 불러오기(읽어오기)
		// ItemNPC의 퀘스트 정보 저장 (플레이어의 인벤토리에 작성되는 퀘스트 스크립트)
		FileInputStream QuestScriptFile;
		XSSFWorkbook QuestScriptWB;
		try {
				QuestScriptFile = new FileInputStream("Quest Script.xlsx"); // 파일 경로로 파일 읽어오기
				QuestScriptWB = new XSSFWorkbook(QuestScriptFile);
				Quest[] quest = new Quest[11]; // Quest Script 저장할 quest 배열 변수
				
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
								default:
									break;
								}
							}
							quest[questIndex].setDescription(description);
							questIndex++;
						}
					}
				}
				
				QuestScriptWB.close(); //workbook닫기
				QuestScriptFile.close(); //file닫기
				
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
		
		//ItemNPC Script(ItemNPC 대화 스크립트)
		FileInputStream itemNPCScriptFis;
		XSSFWorkbook itemNPCWb;
		String[] tempIQScripts = new String[28]; //스크립트 임시로 저장해 둘 String배열
		int tempIQIndex = 0; //임시 String배열의 인덱스
		
		try {
			//엑셀 파일 읽어오기
			itemNPCScriptFis = new FileInputStream("ItemNPCScript.xlsx");
			itemNPCWb = new XSSFWorkbook(itemNPCScriptFis);
			XSSFSheet itemNPCSheet = itemNPCWb.getSheetAt(0); //0번째 시트 읽어오기
			XSSFRow itemNPCRow;
			XSSFCell itemNPCCell;
			
			int rowNum = 0; // 행 인덱스
			int cellNum = 0; // 열 인덱스
			
			int rows = itemNPCSheet.getPhysicalNumberOfRows(); // sheet 당 row의 총 수
			int cells; //각 row 당 cell들의 총 수를 저장할 변수
			
			//Cell마다 읽어서 임시 String배열에 저장
			for(rowNum = 1; rowNum < rows; rowNum++)
			{
				itemNPCRow = itemNPCSheet.getRow(rowNum);
				
				if(itemNPCRow != null) //row가 비어있지 않으면
				{
					cells = itemNPCRow.getPhysicalNumberOfCells(); //각 row 당 cell들의 총 수
					for(cellNum = 1; cellNum < cells; cellNum++)
					{
						itemNPCCell = itemNPCRow.getCell(cellNum);
						
						if(itemNPCCell != null) //셀에 내용이 들어 있으면 임시 배열에 저장
						{
							//전부 String이라 String으로 읽어오기		
							tempIQScripts[tempIQIndex] = itemNPCCell.getStringCellValue(); 
							tempIQIndex++;
						}
						else //셀이 비어있으면 패스
						{
							continue;
						}
						
					}
				}
			}
			
			itemNPCScriptFis.close();
			itemNPCWb.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		// 임시 배열에 있는 내용을 해당 NPC마다 순서대로 부여하기
		int itemNPCIndex = 0; // 아이템NPC 배열의 인덱스
		int tempIQLength = tempIQScripts.length; // 임시 배열의 길이
		int itemNPCQSIndex = 0; // 아이템NPC가 가지고 있는 QuestScripts배열의 인덱스

		for (tempIQIndex = 0; tempIQIndex < tempIQLength; tempIQIndex++) 
		{
			switch (tempIQIndex) 
			{
			case 0: case 1: case 2: case 3: case 4: // 1층 인간 NPC
				itemNPC[itemNPCIndex].setQuestScripts(itemNPCQSIndex, tempIQScripts[tempIQIndex]);
				break;
			case 5: case 6: case 7: case 8: case 9: // 2층 몬스터 NPC
				itemNPC[itemNPCIndex].setQuestScripts(itemNPCQSIndex, tempIQScripts[tempIQIndex]);
				break;
			case 10: case 11: case 12: case 13: case 14: // 2층 인간 NPC(1)
				itemNPC[itemNPCIndex].setQuestScripts(itemNPCQSIndex, tempIQScripts[tempIQIndex]);
				break;
			case 15: case 16: case 17: case 18: case 19: // 2층 인간 NPC(2)
				itemNPC[itemNPCIndex].setQuestScripts(itemNPCQSIndex, tempIQScripts[tempIQIndex]);
				break;
			case 20: case 21: case 22: // 3층 몬스터 NPC(1)
				itemNPC[itemNPCIndex].setQuestScripts(itemNPCQSIndex, tempIQScripts[tempIQIndex]);
				break;
			case 23: case 24: case 25: case 26: case 27:// 3층 몬스터 NPC(2)
				itemNPC[itemNPCIndex].setQuestScripts(itemNPCQSIndex, tempIQScripts[tempIQIndex]);
				break;
			}
			itemNPCIndex++; // 다음 itemNPC로 넘어감
			itemNPCQSIndex++; // 해당 itemNPC의 QuestScripts[]의 인덱스
		}
		
		//SkillNPC Script(SkillNPC 대화 스크립트)
		FileInputStream skillNPCScriptFis;
		XSSFWorkbook skillNPCWb;
		String[] tempSQScripts = new String[9]; //스크립트 임시로 저장해 둘 String배열
		int tempSQIndex = 0; //임시 String배열의 인덱스
		
		try
		{
			//SkillNPC Script 엑셀 파일 읽어오기
			skillNPCScriptFis = new FileInputStream("SkillNPCScript.xlsx"); 
			skillNPCWb = new XSSFWorkbook(skillNPCScriptFis);
			XSSFSheet skillNPCSheet = skillNPCWb.getSheetAt(0); //0번째 시트 읽어오기
			XSSFRow skillNPCRow;
			XSSFCell skiNPCCell;
			
			int rowNum = 0; // 행 인덱스
			int cellNum = 0; // 열 인덱스
			
			int rows = skillNPCSheet.getPhysicalNumberOfRows(); // sheet 당 row의 총 수
			int cells; //각 row 당 cell들의 총 수를 저장할 변수
			
			//Cell마다 읽어서 임시 String배열에 저장
			for(rowNum = 1; rowNum < rows; rowNum++)
			{
				skillNPCRow = skillNPCSheet.getRow(rowNum);
				
				if(skillNPCRow != null) //row가 비어있지 않으면
				{
					cells = skillNPCRow.getPhysicalNumberOfCells(); //각 row 당 cell들의 총 수
					for(cellNum = 1; cellNum < cells; cellNum++)
					{
						skiNPCCell = skillNPCRow.getCell(cellNum);
						
						if(skiNPCCell != null) //셀에 내용이 들어 있으면 임시 배열에 저장
						{
							//전부 String이라 String으로 읽어오기		
							tempSQScripts[tempSQIndex] = skiNPCCell.getStringCellValue(); 
							tempSQIndex++;
						}
						else //셀이 비어있으면 패스
						{
							continue;
						}
						
					}
				}
			
			}
			skillNPCScriptFis.close();
			skillNPCWb.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
				
		//SkillNPC의 QuestScripts배열에 순서대로 Script 부여하기
		int tempSQLength = tempSQScripts.length; //임시 배열의 길이
		int skillNPCQSindex = 0; //스킬 NPC가 가지고 있는 QuestScripts 배열의 인덱스
		
		for(tempSQIndex=0; tempSQIndex<tempSQLength; tempSQIndex++)
		{
			skillNPC.setQuestScripts(skillNPCQSindex, tempSQScripts[tempSQIndex]); 
			skillNPCQSindex++;
		}
		
		
		//EndingScript 
		final int endingNum = 3; //엔딩개수
		String[][] endingArray = new String[endingNum][]; //endingScript 저장할 String배열. Bad-Normal-True Ending 순서로 저장.
		
		//EndingScript 엑셀 파일 읽어오기
		FileInputStream endingScriptFis;
		XSSFWorkbook endingWb;
		XSSFSheet endingSheet;
		XSSFRow endingRow;
		XSSFCell endingCell;
		
		int sheetIndex; //EndingScript 엑셀 파일의 시트 인덱스
		int rowIndex; //EndingScript 엑셀 파일의 행 인덱스
		
		int endingSortIndex = 0; //엔딩 종류 인덱스 => 0은 BAD / 1은 NORMAL / 2는 True
		int endingSentenceIndex = 0; //엔딩 문장 인덱스
		
		try 
		{
			endingScriptFis = new FileInputStream("EndingScript.xlsx");
			endingWb = new XSSFWorkbook(endingScriptFis);
			
			String dialogue = ""; //대사 저장하는 변수

			int sheetNum = endingWb.getNumberOfSheets(); //엑셀 파일에 있는 시트의 총 개수
			int rowNum; //엑셀 파일에 있는 시트 당 행의 총 개수를 저장하는 변수
			int cellNum; //엑셀 파일에 있는 행 당 셀의 총 개수를 저장하는 변수
			for(sheetIndex=0; sheetIndex<sheetNum; sheetIndex++)
			{
				endingSheet = endingWb.getSheetAt(sheetIndex); //시트 지정
				rowNum = endingSheet.getPhysicalNumberOfRows(); //시트 당 행의 총 개수
				
				for(rowIndex = 0; rowIndex<rowNum; rowIndex++)
				{
					endingRow = endingSheet.getRow(rowIndex); //행 지정
					endingCell = endingRow.getCell(0); //셀 지정 - 셀 인덱스는 항상 0
					
					if(endingCell != null) //셀이 비어있지 않다면
					{
						dialogue = endingCell.getStringCellValue(); //셀 String으로 읽어오기
						//배열에 엔딩 스크립트 저장하기			
						endingArray[endingSortIndex][endingSentenceIndex] = dialogue;
						endingSentenceIndex++; //다음 대사로 넘어감 = 행 바뀜
					}
				}
				endingSortIndex++; //엔딩 종류 바뀜 = 시트 바뀜
			}
			
			//파일 닫기
			endingScriptFis.close();
			endingWb.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		// 플레이시 필요한 객체 생성
		PlayEvent playEvent = new PlayEvent(maps, player);		// 이벤트 객체 생성
		Scanner scan = new Scanner(System.in);					// 스캐너 객체 생성
		int num = 0;											// 선택지 저장
		
		
		// --------------------------- 게임 시작 --------------------------- \\
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
					System.out.print("* 플레이어의 이름은? : ");
					String playerName = scan.nextLine();
					player.setName(playerName); //이름 설정
					saveNewPlayerInfo(player, playerName); //파일 생성
					
					System.out.println("* 게임을 시작합니다.");
					
					// 2. 인트로 스크립트 출력
					System.out.println("오늘은 금요일. 퇴근을 하고 집에서 휴식을 즐기는 중이다.");
					System.out.println("하루는 항상 고되지만, 우리 회사만의 장점이 있다면 절대로 야근을 하지 않는다는 점이다.");
					System.out.println("...절대로 안 하는 걸로 알고 있었다.");
					System.out.println("상사가 내게 전화를 걸기 전까지는.");
					System.out.println("하하..");
					System.out.println("도대체 금요일 밤에 추가 작업을 시키는 이유가 뭔지 좀 묻고 싶다.");
					System.out.println("게다가 그 작업을 하려면 저번달 회의록을 참고해서 해야한댄다.");
					System.out.println("왜 그건 서류로 밖에 안 남아있는지 모르겠지만,");
					System.out.println("상사 말에 따르면 그 회의록은 꼭대기 층에 있는 사장님의 방에 있다고 한다.");
					System.out.println("뭐 급하다고 하니까 지금 얼른 출발하려고 한다. 별 수 있나.");
					
					// 딜레이
					System.out.println("* * *");
					// 딜레이
					
					System.out.println("회사 앞에 도착했다.");
					System.out.println("< Turning.corp >.");
					System.out.println("나름 이 곳에선 잘 나가는 방송국이다.");
					System.out.println("복지도 좋고, 연봉도 나름 쏠쏠하고, 그야말로 완벽한 회사라 할 수 있다.");
					System.out.println("그러나 완벽한 만큼 회사 내에 작은 소문도 돌고 있긴 했었다.");
					System.out.println("밤이 되면 귀신들이 나오기 때문에 야근을 안 시키는 거라나 뭐라나.");
					System.out.println("물론 나는 그런 소문들을 믿진 않았지만, 지금 그 생각이 나는 건 대체 왜일까.");
					System.out.println("어쨌거나 난 얼른 추가 작업을 완수해야한다. 최대한 빨리 서류를 가져오기나 하자.");
					System.out.println("(문 열리는 소리)");
					
					// 기본 데이터들은 사전에(게임 시작 전에) 생성했다는 가정 하에 진행
				}
				else if (num == 2)	// 게임 이어하기
				{
					// 로컬에 저장된 게임 데이터 불러오기
					System.out.print("* 이어서 할 플레이어의 이름을 입력하세요. : ");
					String playerName = scan.nextLine();
					scan.close();
					
					int sheetNum = 0;
					FileInputStream fis = null;
					try {
						fis = new FileInputStream("saveFile.xlsx"); //파일 읽어오기
						XSSFWorkbook Wb = new XSSFWorkbook(fis);
						//이어서 할 플레이어의 정보가 저장된 해당 시트 찾기
						sheetNum = findSheet(fis, playerName); 
						
						Wb.close();
						
					} catch (IOException e) {
						e.printStackTrace();
					}
					if(sheetNum == -1) //해당 시트가 존재하지 않음
					{
						System.out.println("* 해당 플레이어 정보는 존재하지 않습니다.");
						continue; //메인 메뉴로 돌아가기
					}
					else //해당 시트를 찾음
					{
						XSSFWorkbook Wb = new XSSFWorkbook(fis);
						XSSFSheet sheet = Wb.getSheetAt(sheetNum); //sheet변수에 해당 sheet 저장
						Wb.close();
						
						//플레이어 객체에 정보 옮기기
						rowIndex = 1;
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
						
						System.out.println("* 해당 데이터를 모두 불러오는데 성공했습니다.");
						
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
							playEvent.playFloor5(endingArray);
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
	}
}
