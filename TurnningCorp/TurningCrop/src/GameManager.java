
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
		//�����Է�
		XSSFWorkbook xssfWb = null; //���� ���� �ʱ�ȭ
		XSSFSheet xssfSheet = null; //���� ��Ʈ �ʱ�ȭ
		XSSFRow xssfRow = null; //�� �ʱ�ȭ
		XSSFCell xssfCell = null; //�� �ʱ�ȭ
		
		xssfWb = new XSSFWorkbook();
		xssfSheet = xssfWb.createSheet(playerName);
		int rowNum = 0; //���� ����
		
		//�⺻ Ʋ ����
		XSSFCellStyle tableCellStyle = xssfWb.createCellStyle(); //��Ÿ�� ����
		tableCellStyle.setBorderBottom(BorderStyle.THICK); //�Ʒ��� �β���
		
		xssfRow = xssfSheet.createRow(rowNum); //0��° ��
		
		xssfCell = xssfRow.createCell((short)0); //cell�����
		xssfCell.setCellStyle(tableCellStyle); //��Ÿ������
		xssfCell.setCellValue("Name"); //������
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
		
		xssfRow = xssfSheet.createRow(rowNum++); //������° ��
		xssfCell = xssfRow.createCell((short)0);
		xssfCell.setCellValue(player.getName()); //������
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
		
		//���ϻ���
		File file;
		try {
			String path = "";
			file = new File(path + "/saveFile.xlsx"); // ������/�����̸�.xlsx
			
			FileOutputStream fos = new FileOutputStream(file); 
			xssfWb.write(fos);
			xssfWb.close();
			fos.close(); //���� ���� ���� ����
		}
		catch(IOException e)
		{
			e.printStackTrace(); //���� ���� ���� ����
		}
		
	}
	
	public static int findSheet(FileInputStream fis, String playerName) throws IOException
	{
		XSSFWorkbook Wb = new XSSFWorkbook(fis);
		int sheetsNum = Wb.getNumberOfSheets(); //������ ��Ʈ �� ����
		for(int i=0; i<sheetsNum; i++)
		{
			if(Wb.getSheetName(i).equals(playerName)) //��Ʈ �̸��� ���� �� ã��
			{
				Wb.close();
				return i; //�ش� �ε��� ��ȯ
			}
		}
		
		Wb.close();
		return -1; //ã�� ��Ʈ�� �������� ����
		
		
	}
	
	// ���Ա⿡�� ������� ����� ���, ������� ����� player�� ��ü ����, �κ��丮, ����Ʈ ����
	// ���� ���� ���� �÷��̾� ��Ʈ Ȯ�� �ʿ�
	// playerName Sheet�� ���� ����
	public static void useEmployeeCard(Player player) {
		XSSFWorkbook Wb;
		
		int sheetNum = 0;
		try {
			FileInputStream fis = new FileInputStream("saveFile.xlsx"); // ���� �о����
			Wb = new XSSFWorkbook(fis);
			// �����ϰ� �ִ� ���� ������ ����� ��Ʈ ã��
			sheetNum = findSheet(fis, player.getName());
			
			// �����ϰ� �ִ� ���� ������ ����� ��Ʈ�� ã�� ���
			XSSFSheet sheet = Wb.getSheetAt(sheetNum); // sheet������ �ش� ��Ʈ ���� ����
			
			// �÷��̾� ��ü ���� �ű��
			int rowIndex = 1;
			int cellIndex = 0;
			
			XSSFRow row = sheet.getRow(rowIndex);
			XSSFCell cell = row.getCell(cellIndex); // cell������ �ش� �� ���� ����
			
			// HP ����
			cellIndex++;
			cell = row.getCell(cellIndex);
			cell.setCellValue(player.getHp());
			
			// AttackPower ����
			cellIndex++;
			cell = row.getCell(cellIndex);
			cell.setCellValue(player.getAttackPower());
			
			// DefensivePower ����
			cellIndex++;
			cell = row.getCell(cellIndex);
			cell.setCellValue(player.getDefensivePower());
			
			// Reputation ����
			cellIndex++;
			cell = row.getCell(cellIndex);
			cell.setCellValue(player.getReputation());
			
			// CurrentIndex ����
			cellIndex++;
			cell = row.getCell(cellIndex);
			cell.setCellValue(player.getCurrentIndex());
			
			// Inventory Item Name ����
			cellIndex++;
			int rowsNum = sheet.getPhysicalNumberOfRows(); // ��Ʈ �� ���� ����
			for (rowIndex = 1; rowIndex < rowsNum; rowIndex++) {
				row = sheet.getRow(rowIndex);
				cell = row.getCell(cellIndex);
				if (cell != null) { // �� ������ �ִ� ���
					cell.setCellValue(""); // null������ �ʱ�ȭ
				}
			}
			int inventoryLength = player.getInventoryLength();
			for (int i = 1; i < inventoryLength; i++) { // �ʱ�ȭ�� ���� ���ο� ���� �ֱ�
				cell = row.getCell(cellIndex);
				cell.setCellValue(player.getInventory()[i - 1].item.getName());
				row = sheet.getRow(i++);
			}
			
			// Inventory Item Count ����
			cellIndex++;
			for (rowIndex = 1; rowIndex < rowsNum; rowIndex++) {
				row = sheet.getRow(rowIndex);
				cell = row.getCell(cellIndex);
				if (cell != null) { // �� ������ �ִ� ���
					cell.setCellValue(""); // null������ �ʱ�ȭ
				}
			}
			for (int i = 1; i < inventoryLength; i++) { // �ʱ�ȭ�� ���� ���ο� ���� �ֱ�
				cell = row.getCell(cellIndex);
				cell.setCellValue(player.getInventory()[i - 1].count);
				row = sheet.getRow(i++);
			}
			
			// PosID ����
			cellIndex++;
			cell = row.getCell(cellIndex);
			cell.setCellValue(player.getPosID());
			
			// Quest Name ����
			cellIndex++;
			int questArrayLength = player.getQuestArrayLength();
			for (rowIndex = 1; rowIndex < rowsNum; rowIndex++) {
				row = sheet.getRow(rowIndex);
				cell = row.getCell(cellIndex);
				if (cell != null) { // �� ������ �ִ� ���
					cell.setCellValue(""); // null������ �ʱ�ȭ
				}
			}
			for (int i = 1; i < questArrayLength; i++) { // �ʱ�ȭ�� ���� ���ο� ���� �ֱ�
				cell = row.getCell(cellIndex);
				cell.setCellValue(player.getQuestArray()[i - 1].getQuestName());
				row = sheet.getRow(i++);
			}
			
			// Quest Condition ���� (����Ʈ ���� ����)
			cellIndex++;
			for (rowIndex = 1; rowIndex < rowsNum; rowIndex++) {
				row = sheet.getRow(rowIndex);
				cell = row.getCell(cellIndex);
				if (cell != null) { // �� ������ �ִ� ���
					cell.setCellValue(""); // null������ �ʱ�ȭ
				}
			}
			for (int i = 1; i < questArrayLength; i++) { // �ʱ�ȭ�� ���� ���ο� ���� �ֱ�
				cell = row.getCell(cellIndex);
				cell.setCellValue(player.getQuestArray()[i - 1].getCondition());
				row = sheet.getRow(i++);
			}
			
			// Quest Complete ���� (����Ʈ �Ϸ� ����)
			cellIndex++;
			for (rowIndex = 1; rowIndex < rowsNum; rowIndex++) {
				row = sheet.getRow(rowIndex);
				cell = row.getCell(cellIndex);
				if (cell != null) { // �� ������ �ִ� ���
					cell.setCellValue(""); // null������ �ʱ�ȭ
				}
			}
			for (int i = 1; i < questArrayLength; i++) { // �ʱ�ȭ�� ���� ���ο� ���� �ֱ�
				cell = row.getCell(cellIndex);
				cell.setCellValue(player.getQuestArray()[i - 1].getCompletion());
				row = sheet.getRow(i++);
			}
			
			// ���� ���̴� ��Ʈ�� ���ο� �� �����Ű��
			FileOutputStream fos = new FileOutputStream("saveFile.xlsx");
			Wb.write(fos);
			Wb.close();
			fos.close(); //���� ���� ���� ����
			// System.out.println("���� ��Ʈ ���� ����");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[], BorderStyle BorderStyle) throws IOException {
		
		final int FLOOR = 4; // ��
		final int ROOM = 5; // ��
		Player player;
		Map maps[][] = new Map[FLOOR][ROOM];
		// 5��, 5���� ��(�κ� + �� 3�� + ȭ���)�� ��κ� �����Ǿ�������
		// 5���� ���� ������ ���Ҹ� �����ϹǷ� 5���� ������ 1~4���� Ȱ�� ������ ���
		// Map ��ü �迭�� [4][5]�� ������ �迭�� ������
		// 5���� ���� playEvent.playFloor5() �޼ҵ常�� ���� ���� �̺�Ʈ ������
		
		ItemNPC[] itemNPC = new ItemNPC[6]; // ��ü �迭�� �����Ϸ�!
		SkillNPC skillNPC = new SkillNPC();	// ��ųNPC�� �� ���̹Ƿ� �ϳ��� ������
		
		// �÷��̾� ���� ���� �� �ʱ�ȭ
		player = new Player("�̸�", 100, 100, 100, 0, 0, 100, 0);
		player.setPosID(10);		// 1�� �κ�� ��ġ �ʱ�ȭ
		
		// �� ���� ���� �� �ʱ�ȭ
		// �� �ʸ��� �����ϴ� �繰, ������ ������ �������� ������ �����ϱ�
		try {
			FileInputStream Objectfis = new FileInputStream("Object Script.xlsx"); // �繰 , ������ ��ũ��Ʈ
			XSSFWorkbook ObjectWB = new XSSFWorkbook(Objectfis);
			final int OBJECTNUM = 15;
			MapObject[][] mapObject = new MapObject[FLOOR][OBJECTNUM]; // �繰�� ������ mapObject ����, [��][�繰 ����]
			
			int rowNum = 0; // �� �ε���
			int objectIndex1 = 0, objectIndex2 = 0, objectIndex3 = 0, objectIndex4 = 0; // �繰 �迭 �ε���
			int itemIndex = 0; // �繰�� ���� ������ �迭 �ε���
			
			XSSFSheet ObjectScriptSheet = ObjectWB.getSheetAt(0); // Object Script ��Ʈ ��������
			XSSFSheet ItemScriptSheet = ObjectWB.getSheetAt(1); // Item Script ��Ʈ ��������
			
			ObjectWB.close();
			
			int objectRows = ObjectScriptSheet.getPhysicalNumberOfRows(); // ������Ʈ ��Ʈ�� row�� ����
			int itemRows = ItemScriptSheet.getPhysicalNumberOfRows(); // ������ ��Ʈ�� row�� ����
			
			for (rowNum = 1; rowNum <= objectRows; rowNum++) { // ���� �ǳʶٱ�
				XSSFRow objectRow = ObjectScriptSheet.getRow(rowNum); // row
				
				// mapObject�� ������ ������Ʈ �̸�&���� ����
				switch (objectRow.getCell(0).getStringCellValue()) {
					case "11" :
					case "12" :
					case "14" : { // ������ 0���� ����� ���� 1�̶�� (1��)
						XSSFCell objectName = objectRow.getCell(2); // �ش� row�� cell���� name�������� (�� �ε���2)
						mapObject[0][objectIndex1].setObjectName(objectName.getStringCellValue()); // ������Ʈ �̸� ����
					
						XSSFCell objectDescription = objectRow.getCell(3); // description �������� (�� �ε���3)
						mapObject[0][objectIndex1].setDescription(objectDescription.getStringCellValue()); // ������Ʈ ���� ����
						objectIndex1++;
					} break;
					case "21" :
					case "22" : { // ������ 0���� ����� ���� 2��� (2��)
						XSSFCell objectName = objectRow.getCell(2); // �ش� row�� cell���� name�������� (�� �ε���2)
						mapObject[1][objectIndex2].setObjectName(objectName.getStringCellValue()); // ������Ʈ �̸� ����
					
						XSSFCell objectDescription = objectRow.getCell(3); // description �������� (�� �ε���3)
						mapObject[1][objectIndex2].setDescription(objectDescription.getStringCellValue()); // ������Ʈ ���� ����
						objectIndex2++;
					} break;
					case "31" :
					case "32" :
					case "33" : { // ������ 0���� ����� ���� 3�̶�� (3��)
						XSSFCell objectName = objectRow.getCell(2); // �ش� row�� cell���� name�������� (�� �ε���2)
						mapObject[2][objectIndex3].setObjectName(objectName.getStringCellValue()); // ������Ʈ �̸� ����
					
						XSSFCell objectDescription = objectRow.getCell(3); // description �������� (�� �ε���3)
						mapObject[2][objectIndex3].setDescription(objectDescription.getStringCellValue()); // ������Ʈ ���� ����
						objectIndex3++;
					} break;
					case "41" :
					case "42" :
					case "43" : { // ������ 0���� ����� ���� 4��� (4��)
						XSSFCell objectName = objectRow.getCell(2); // �ش� row�� cell���� name�������� (�� �ε���2)
						mapObject[3][objectIndex4].setObjectName(objectName.getStringCellValue()); // ������Ʈ �̸� ����
					
						XSSFCell objectDescription = objectRow.getCell(3); // description �������� (�� �ε���3)
						mapObject[3][objectIndex4].setDescription(objectDescription.getStringCellValue()); // ������Ʈ ���� ����
						objectIndex4++;
					} break;
				}
			}
			
			// mapObject�� ���� ������Ʈ�� �����ִ� item �̸�&���� ����
			// �̶�, ���Թ��� �Ǹ���, �ϱ���, ȸ�μ� �� itemManager�� �ִ� �����۵��� mapObject�� ����X
			// PlayEventŬ�������� ItemManager ��ü ������ ���� ��, getItem()�Լ��� ����Ͽ� ������ �����ϱ�!
			for (rowNum = 1; rowNum <= objectRows; rowNum++) {
				XSSFRow objectRow = ObjectScriptSheet.getRow(rowNum); // object row
				for (int rowNum2 = 1; rowNum2 <= itemRows; rowNum2++) { // ���� �ǳʶٱ�
					XSSFRow itemRow = ItemScriptSheet.getRow(rowNum); // item row
				
					// ���� ������Ʈ ��ũ��Ʈ�� �ڵ�� ������ ��ũ��Ʈ�� �ڵ尡 ���ٸ�
					if (objectRow.getCell(1).getStringCellValue().equals(itemRow.getCell(1).getStringCellValue())) {
						XSSFCell objectName = objectRow.getCell(2); // ������Ʈ �̸� ����
						
						// mapObject�迭���� �ش� �̸��� ���� ������Ʈ �ε����� ã�Ƽ�, item �̸�&���� ����
						for (int i = 0; i < FLOOR; i++) {
							for (int j = 0; j < OBJECTNUM; j++) {
								if (mapObject[i][j].getObjectName().equals(objectName.getStringCellValue())) {
									XSSFCell itemName = itemRow.getCell(2); // ������ �̸� ����
									XSSFCell itemDescription = itemRow.getCell(3); // ������ ���� ����
									
									Item scriptItem = new Item(); // ������ ����
									scriptItem.setName(itemName.getStringCellValue()); // ������ �̸� ����
									scriptItem.setDescription(itemDescription.getStringCellValue()); // ������ ���� ����
									scriptItem.setValue(0);
									
									// �ش� mapObject �ε����� ����Ǿ� �ִ� ������ ���� ã��
									itemIndex = mapObject[i][j].getAllItem().length;
									if (itemIndex == 0) { // �������� ���ٸ�
										mapObject[i][j].setItem(scriptItem, itemIndex);
									}
									else if (itemIndex >= 1) { // �������� 1�� �̻��̶��
										mapObject[i][j].setItem(scriptItem, itemIndex + 1);
									}
								}
							}
						}
					}
				}
			}
			
			// maps�迭�� ������ mapObject�迭 �ֱ�
			for (rowNum = 1; rowNum <= objectRows; rowNum++) {
				XSSFRow objectRow = ObjectScriptSheet.getRow(rowNum); // object row
				for (int i = 0; i < FLOOR; i++) {
					int floorCode = (FLOOR + 1) * 10;
					for (int j = 1; j < ROOM; j++) {
						++floorCode;
						int mapIndex = 0;
						for (int z = 0; z < OBJECTNUM; z++) {
							// Object Script�� ����Ǿ� �ִ� Floor���� floorCode ��
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
		
		// 	�� ��ü ���� ���Ϸ� �ҷ�����(�о����)
		// ItemNPC�� ����Ʈ ���� ���� (�÷��̾��� �κ��丮�� �ۼ��Ǵ� ����Ʈ ��ũ��Ʈ)
		FileInputStream QuestScriptFile;
		XSSFWorkbook QuestScriptWB;
		try {
				QuestScriptFile = new FileInputStream("Quest Script.xlsx"); // ���� ��η� ���� �о����
				QuestScriptWB = new XSSFWorkbook(QuestScriptFile);
				Quest[] quest = new Quest[11]; // Quest Script ������ quest �迭 ����
				
				int rownum = 0; // �� �ε���
				int cellnum = 0; // �� �ε���
				int questIndex = 0; // ����Ʈ �迭 �ε���
				
				XSSFSheet QuestScriptSheet = QuestScriptWB.getSheetAt(0); // 0��° ��Ʈ ��������
				
				int rows = QuestScriptSheet.getPhysicalNumberOfRows(); // ����ڰ� �Է��� ���� row�� ��������
				for (rownum = 1; rownum < rows; rownum++) {
					XSSFRow row = QuestScriptSheet.getRow(rownum); // row
					
					if (row != null) {
						int cells = row.getPhysicalNumberOfCells(); // �ش� row���� ����ڰ� �Է��� cell�� ��������
						
						for (cellnum = 1; cellnum <= cells; cellnum++) {
							XSSFCell cell = row.getCell(cellnum); // ����ڰ� �Է��� cell�� ��������
							
							String description = ""; // Quest Script ������ �Ҵ�� ����
							if (cell == null) { // �� ���� ���
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
				
				QuestScriptWB.close(); //workbook�ݱ�
				QuestScriptFile.close(); //file�ݱ�
				
				// �� NPC��ü�� Quest Script ���� �Ҵ��ϱ� 
				int npc1Index = 0, npc2Index = 0, npc3Index = 0,
						npc4Index = 0, npc5Index = 0, npc6Index = 0; // �� NPC�� ����Ʈ ���� �ε���
				
				for (int index = 0; index < quest.length; index++) {
					if (index == 0 || index == 1) { // 1�� �ΰ�NPC
						itemNPC[0].setQuest(npc1Index, quest[index]);
						++npc1Index;
					}
					else if (index == 2 || index == 3) { // 2�� ����NPC
						itemNPC[1].setQuest(npc2Index, quest[index]);
						++npc2Index;
					}
					else if (index == 4 || index == 5) { // 2�� �ΰ�NPC(1)
						itemNPC[2].setQuest(npc3Index, quest[index]);
						++npc3Index;
					}
					else if (index == 6 || index == 7) { // 2�� �ΰ�NPC(2)
						itemNPC[3].setQuest(npc4Index, quest[index]);
						++npc4Index;
					}
					else if (index == 8) { // 3�� ����NPC(1)
						itemNPC[4].setQuest(npc5Index, quest[index]);
						++npc5Index;
					}
					else if (index == 9 || index == 10) { // 3�� ����NPC(2)
						itemNPC[5].setQuest(npc6Index, quest[index]);
						++npc6Index;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		//ItemNPC Script(ItemNPC ��ȭ ��ũ��Ʈ)
		FileInputStream itemNPCScriptFis;
		XSSFWorkbook itemNPCWb;
		String[] tempIQScripts = new String[22]; //��ũ��Ʈ �ӽ÷� ������ �� String�迭
		int tempIQIndex = 0; //�ӽ� String�迭�� �ε���
		
		try {
			//���� ���� �о����
			itemNPCScriptFis = new FileInputStream("ItemNPCScript.xlsx");
			itemNPCWb = new XSSFWorkbook(itemNPCScriptFis);
			XSSFSheet itemNPCSheet = itemNPCWb.getSheetAt(0); //0��° ��Ʈ �о����
			XSSFRow itemNPCRow;
			XSSFCell itemNPCCell;
			
			int rowNum = 0; // �� �ε���
			int cellNum = 0; // �� �ε���
			
			int rows = itemNPCSheet.getPhysicalNumberOfRows(); // sheet �� row�� �� ��
			int cells; //�� row �� cell���� �� ���� ������ ����
			
			//Cell���� �о �ӽ� String�迭�� ����
			for(rowNum = 1; rowNum < rows; rowNum++)
			{
				itemNPCRow = itemNPCSheet.getRow(rowNum);
				
				if(itemNPCRow != null) //row�� ������� ������
				{
					cells = itemNPCRow.getPhysicalNumberOfCells(); //�� row �� cell���� �� ��
					for(cellNum = 1; cellNum < cells; cellNum++)
					{
						itemNPCCell = itemNPCRow.getCell(cellNum);
						
						if(itemNPCCell != null) //���� ������ ��� ������ �ӽ� �迭�� ����
						{
							//���� String�̶� String���� �о����		
							tempIQScripts[tempIQIndex] = itemNPCCell.getStringCellValue(); 
							tempIQIndex++;
						}
						else //���� ��������� �н�
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

		// �ӽ� �迭�� �ִ� ������ �ش� NPC���� ������� �ο��ϱ�
		int itemNPCIndex = 0; // ������NPC �迭�� �ε���
		int tempIQLength = tempIQScripts.length; // �ӽ� �迭�� ����
		int itemNPCQSIndex = 0; // ������NPC�� ������ �ִ� QuestScripts�迭�� �ε���

		for (tempIQIndex = 0; tempIQIndex < tempIQLength; tempIQIndex++) 
		{
			switch (tempIQIndex) 
			{
			case 0: case 1: case 2: case 3: // 1�� �ΰ� NPC
				itemNPC[itemNPCIndex].setQuestScripts(itemNPCQSIndex, tempIQScripts[tempIQIndex]);
				break;
			case 4: case 5: case 6: case 7: // 2�� ���� NPC
				itemNPC[itemNPCIndex].setQuestScripts(itemNPCQSIndex, tempIQScripts[tempIQIndex]);
				break;
			case 8: case 9: case 10: case 11: // 2�� �ΰ� NPC(1)
				itemNPC[itemNPCIndex].setQuestScripts(itemNPCQSIndex, tempIQScripts[tempIQIndex]);
				break;
			case 12: case 13: case 14: case 15: // 2�� �ΰ� NPC(2)
				itemNPC[itemNPCIndex].setQuestScripts(itemNPCQSIndex, tempIQScripts[tempIQIndex]);
				break;
			case 16: case 17: // 3�� ���� NPC(1)
				itemNPC[itemNPCIndex].setQuestScripts(itemNPCQSIndex, tempIQScripts[tempIQIndex]);
				break;
			case 18: case 19: case 20: case 22: // 3�� ���� NPC(2)
				itemNPC[itemNPCIndex].setQuestScripts(itemNPCQSIndex, tempIQScripts[tempIQIndex]);
				break;
			}
			itemNPCIndex++; // ���� itemNPC�� �Ѿ
			itemNPCQSIndex++; // �ش� itemNPC�� QuestScripts[]�� �ε���
		}
		
		//SkillNPC Script(SkillNPC ��ȭ ��ũ��Ʈ)
		FileInputStream skillNPCScriptFis;
		XSSFWorkbook skillNPCWb;
		String[] tempSQScripts = new String[10]; //��ũ��Ʈ �ӽ÷� ������ �� String�迭
		int tempSQIndex = 0; //�ӽ� String�迭�� �ε���
		
		try
		{
			//SkillNPC Script ���� ���� �о����
			skillNPCScriptFis = new FileInputStream("SkillNPCScript.xlsx"); 
			skillNPCWb = new XSSFWorkbook(skillNPCScriptFis);
			XSSFSheet skillNPCSheet = skillNPCWb.getSheetAt(0); //0��° ��Ʈ �о����
			XSSFRow skillNPCRow;
			XSSFCell skiNPCCell;
			
			int rowNum = 0; // �� �ε���
			int cellNum = 0; // �� �ε���
			
			int rows = skillNPCSheet.getPhysicalNumberOfRows(); // sheet �� row�� �� ��
			int cells; //�� row �� cell���� �� ���� ������ ����
			
			//Cell���� �о �ӽ� String�迭�� ����
			for(rowNum = 1; rowNum < rows; rowNum++)
			{
				skillNPCRow = skillNPCSheet.getRow(rowNum);
				
				if(skillNPCRow != null) //row�� ������� ������
				{
					cells = skillNPCRow.getPhysicalNumberOfCells(); //�� row �� cell���� �� ��
					for(cellNum = 1; cellNum < cells; cellNum++)
					{
						skiNPCCell = skillNPCRow.getCell(cellNum);
						
						if(skiNPCCell != null) //���� ������ ��� ������ �ӽ� �迭�� ����
						{
							//���� String�̶� String���� �о����		
							tempSQScripts[tempSQIndex] = skiNPCCell.getStringCellValue(); 
							tempSQIndex++;
						}
						else //���� ��������� �н�
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
				
		//SkillNPC�� QuestScripts�迭�� ������� Script �ο��ϱ�
		int tempSQLength = tempSQScripts.length; //�ӽ� �迭�� ����
		int skillNPCQSindex = 0; //��ų NPC�� ������ �ִ� QuestScripts �迭�� �ε���
		
		for(tempSQIndex=0; tempSQIndex<tempSQLength; tempSQIndex++)
		{
			skillNPC.setQuestScripts(skillNPCQSindex, tempSQScripts[tempSQIndex]); 
			skillNPCQSindex++;
		}
		
		
		//EndingScript 
		final int endingNum = 3; //��������
		String[][] endingArray = new String[endingNum][]; //endingScript ������ String�迭. Bad-Normal-True Ending ������ ����.
		
		//EndingScript ���� ���� �о����
		FileInputStream endingScriptFis;
		XSSFWorkbook endingWb;
		XSSFSheet endingSheet;
		XSSFRow endingRow;
		XSSFCell endingCell;
		
		int sheetIndex; //EndingScript ���� ������ ��Ʈ �ε���
		int rowIndex; //EndingScript ���� ������ �� �ε���
		
		int endingSortIndex = 0; //���� ���� �ε��� => 0�� BAD / 1�� NORMAL / 2�� True
		int endingSentenceIndex = 0; //���� ���� �ε���
		
		try 
		{
			endingScriptFis = new FileInputStream("EndingScript.xlsx");
			endingWb = new XSSFWorkbook(endingScriptFis);
			
			String dialogue = ""; //��� �����ϴ� ����

			int sheetNum = endingWb.getNumberOfSheets(); //���� ���Ͽ� �ִ� ��Ʈ�� �� ����
			int rowNum; //���� ���Ͽ� �ִ� ��Ʈ �� ���� �� ������ �����ϴ� ����
			int cellNum; //���� ���Ͽ� �ִ� �� �� ���� �� ������ �����ϴ� ����
			for(sheetIndex=0; sheetIndex<sheetNum; sheetIndex++)
			{
				endingSheet = endingWb.getSheetAt(sheetIndex); //��Ʈ ����
				rowNum = endingSheet.getPhysicalNumberOfRows(); //��Ʈ �� ���� �� ����
				
				for(rowIndex = 0; rowIndex<rowNum; rowIndex++)
				{
					endingRow = endingSheet.getRow(rowIndex); //�� ����
					endingCell = endingRow.getCell(0); //�� ���� - �� �ε����� �׻� 0
					
					if(endingCell != null) //���� ������� �ʴٸ�
					{
						dialogue = endingCell.getStringCellValue(); //�� String���� �о����
						//�迭�� ���� ��ũ��Ʈ �����ϱ�			
						endingArray[endingSortIndex][endingSentenceIndex] = dialogue;
						endingSentenceIndex++; //���� ���� �Ѿ = �� �ٲ�
					}
				}
				endingSortIndex++; //���� ���� �ٲ� = ��Ʈ �ٲ�
			}
			
			//���� �ݱ�
			endingScriptFis.close();
			endingWb.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
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
			scan.next();
			
			if (num == 1 || num == 2)	// ���� ���� ����
			{
				if (num == 1)		// �� ����
				{
					// 1. ���ÿ� �� ����(�� ���� ������) ����
					System.out.print("�÷��̾��� �̸���? : ");
					String playerName = scan.nextLine();
					player.setName(playerName); //�̸� ����
					saveNewPlayerInfo(player, playerName); //���� ����
					
					// 2. ��Ʈ�� ��ũ��Ʈ ���
					System.out.println("�ȳ��Ͻʴϱ� " + player.getFullName() + "��? �������.");
					// �⺻ �����͵��� ������(���� ���� ����) �����ߴٴ� ���� �Ͽ� ����
				}
				else if (num == 2)	// ���� �̾��ϱ�
				{
					// ���ÿ� ����� ���� ������ �ҷ�����
					System.out.print("�̾ �� �÷��̾��� �̸��� �Է��ϼ���. : ");
					String playerName = scan.nextLine();
					scan.close();
					
					int sheetNum = 0;
					FileInputStream fis = null;
					try {
						fis = new FileInputStream("saveFile.xlsx"); //���� �о����
						XSSFWorkbook Wb = new XSSFWorkbook(fis);
						//�̾ �� �÷��̾��� ������ ����� �ش� ��Ʈ ã��
						sheetNum = findSheet(fis, playerName); 
						
						Wb.close();
						
					} catch (IOException e) {
						e.printStackTrace();
					}
					if(sheetNum == -1) //�ش� ��Ʈ�� �������� ����
					{
						System.out.println("�ش� �÷��̾� ������ �������� �ʽ��ϴ�.");
						continue; //���� �޴��� ���ư���
					}
					else //�ش� ��Ʈ�� ã��
					{
						XSSFWorkbook Wb = new XSSFWorkbook(fis);
						XSSFSheet sheet = Wb.getSheetAt(sheetNum); //sheet������ �ش� sheet ����
						Wb.close();
						
						//�÷��̾� ��ü�� ���� �ű��
						int rowIndex = 1;
						int cellIndex = 0;
						
						//Name
						XSSFRow row = sheet.getRow(rowIndex);
						XSSFCell cell = row.getCell(cellIndex); //cell������ �ش� cell ����
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
						int rowsNum = sheet.getPhysicalNumberOfRows(); //��Ʈ �� ���� ����
						int inventoryIndex = 0;
						for(rowIndex=1; rowIndex<rowsNum; rowIndex++)
						{
							row = sheet.getRow(rowIndex);
							cell = row.getCell(cellIndex);
							if(cell != null) //�� ������ �ִ� ���
							{
								player.getInventory()[inventoryIndex].item.setName(cell.getStringCellValue()); //�κ��丮�� ����
								inventoryIndex++;
							}
							else //�� ���� ������� ���
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
							if(cell != null) //�� ������ �ִ� ���
							{
								player.getInventory()[inventoryIndex].count=(int)cell.getNumericCellValue(); //�κ��丮�� ����
								inventoryIndex++;
							}
							else //�� ���� ������� ���
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
							if(cell != null) //�� ������ �ִ� ���
							{
								player.getQuestArray()[questIndex].setQuestName(cell.getStringCellValue());
								questIndex++;
							}
							else //�� ���� ������� ���
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
							if(cell != null) //�� ������ �ִ� ���
							{
								player.getQuestArray()[questIndex].setCondition(cell.getBooleanCellValue());
								questIndex++;
							}
							else //�� ���� ������� ���
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
							if(cell != null) //�� ������ �ִ� ���
							{
								player.getQuestArray()[questIndex].setCompletion(cell.getBooleanCellValue());
								questIndex++;
							}
							else //�� ���� ������� ���
							{
								continue;
							}
						}
						
						System.out.println("�ش� �����͸� ��� �ҷ����µ� �����߽��ϴ�.");
						
					}
					
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
							playEvent.playFloor1_3(skillNPC);
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
							playEvent.playFloor5(endingArray);
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
}
