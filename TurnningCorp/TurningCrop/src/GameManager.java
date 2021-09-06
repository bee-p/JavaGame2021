
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
				return i; //�ش� �ε��� ��ȯ
			}
		}
		return -1; //ã�� ��Ʈ�� �������� ����
	}
	
	// ���Ա⿡�� ������� ����� ���, ������� ����� player�� ��ü ����, �κ��丮, ����Ʈ ����
	// ���� ���� ���� �÷��̾� ��Ʈ Ȯ�� �ʿ�
	// playerName Sheet�� ���� ����
	public static void useEmployeeCard(Player player) {
		
		int sheetNum = 0;
		try {
			FileInputStream fis = new FileInputStream("saveFile.xlsx"); // ���� �о����
			XSSFWorkbook Wb = new XSSFWorkbook(fis);
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
			fos.close(); //���� ���� ���� ����
			// System.out.println("���� ��Ʈ ���� ����");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[], BorderStyle BorderStyle) throws FileNotFoundException {
		// **�������� �ʿ��� �����ϱ�
		//saveFile ������ �� �÷��̾ ���� ������ ������ �ʿ���.
		Item item;
		Player player;
		Map maps[][] = new Map[4][5];
		// 5��, 5���� ��(�κ� + �� 3�� + ȭ���)�� ��κ� �����Ǿ�������
		// 5���� ���� ������ ���Ҹ� �����ϹǷ� 5���� ������ 1~4���� Ȱ�� ������ ���
		// Map ��ü �迭�� [4][5]�� ������ �迭�� ������
		// 5���� ���� playEvent.playFloor5() �޼ҵ常�� ���� ���� �̺�Ʈ ������
		
		ItemNPC[] itemNPC = new ItemNPC[6]; 		// ��ü �迭�� ���� �Ϸ�
		SkillNPC skillNPC = new SkillNPC();	// ��ųNPC�� �� ���̹Ƿ� �ϳ��� ������
		
		File endingFiles[] = new File[3]; // **������ ������ ������ ��������&�����ϱ�
		
		
		// �÷��̾� ���� ���� �� �ʱ�ȭ
		player = new Player("�̸�", 100, 100, 100, 0, 0, 100, 0);
		player.setPosID(10);		// 1�� �κ�� ��ġ �ʱ�ȭ
		
		// �� ���� ���� �� �ʱ�ȭ
		
		
		
		// 	�� ��ü ���� ���Ϸ� �ҷ�����(�о����)
		// ItemNPC ��ü�� setQuestAll()�Լ��� �÷��̾��� �κ��丮�� �� ����Ʈ ���� ����
		try {
				FileInputStream QuestScriptFile = new FileInputStream("Quest Script.xlsx"); // ���� ��η� ���� �о����
				XSSFWorkbook QuestScriptWB = new XSSFWorkbook(QuestScriptFile);
				Quest[] quest = new Quest[6]; // Quest Script ������ quest �迭 ����
				
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
								}
							}
							quest[questIndex].setDescription(description);
							questIndex++;
						}
					}
				}
				
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
					int sheetNum = 0;
					FileInputStream fis;
					try {
						fis = new FileInputStream("saveFile.xlsx"); //���� �о����
						XSSFWorkbook Wb = new XSSFWorkbook(fis);
						//�̾ �� �÷��̾��� ������ ����� �ش� ��Ʈ ã��
						sheetNum = findSheet(fis, playerName); 
						
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
