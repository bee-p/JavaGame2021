
// ������ ���� ���
// ���1) ItemManager�� ���ؼ� �ʿ��� �������� ������ ��, itemList�迭�� �ִ´�.
// 		 �Ȱ��� �������� ������ �����Ϸ��� UI���� itemList�� �迭�� �ִ� ���� �����ͼ� �����Ѵ�.
// ���2) UIŬ�������� �׶����� �ʿ��� �������� �����Ѵ�.
import java.util.Random;

public class ItemManager {
	Random random = new Random();
	
	private Item[] easyDevilLanguage = new Item[9]; // ����� �迭 ����
	private Item[] electricLine = new Item[3]; // ȸ�μ�
	private Item remoteControl = new Item("������", "������ ����ϴ� �����ϱ�?", 0, 001);
	private Item secretFood = new Item("������ ����", "2�� �Ĵ翡�� �߰��� �����̴�.", 5, 001);
	private Item sFoodAnalyzerMenu = new Item("�м��� ��뼭", "� ������ ��뼭�̴�.", 0, 001);
	private Item[] diary = new Item[5];
	private Item[] document = new Item[4];
	
	public ItemManager() {
		makeItem();
	}
	
	// ������ ����
	public Item itemInitialize(int num) {
		Item item = new Item();
		switch (num) {
		case 0: item = new HealingPotion("hp ���� ����", "�÷��̾��� ü���� 5��ŭ ȸ����Ų��.", 5, 001); break;
		case 1: item = new AttackPotion("���ݷ� ���� ����", "�÷��̾��� ���ݷ��� 5%��ŭ ������Ų��.", 5, 011); break;
		case 2: item = new DefensePotion("���� ���� ����", "�÷��̾��� ������ 5%��ŭ ������Ų��.", 5, 021); break;
		case 3: item = new Item("������ ����", "���ݷ�/����/HP �� �ϳ��� �ɼǸ� �����Ͻÿ�.", 5, 031); break;
		case 4: item = remoteControl; break;
		case 5: item = secretFood; break;
		case 6: item = sFoodAnalyzerMenu; break;
		case 7: item = easyDevilLanguage[random.nextInt(9)]; break;
		case 8: item = electricLine[random.nextInt(3)]; break;
		case 9: item = diary[random.nextInt(5)]; break;
		}
		
		return item;
	}
	
	void makeItem() {
		// ����� ����
		for (int idx = 0; idx < 9; idx++) {
			easyDevilLanguage[idx] = new Item("���� ���� �Ǹ���" + idx + 1 + "��", "������ �� ���� å�̴�.", 0, 001);
		}
		
		// ȸ�μ� ����
		for (int idx = 0; idx < 3; idx++) {
			electricLine[idx] = new Item(idx + 1 + "�� ȸ�μ�", "�������� ��� ��� �����ؾ��� �� ����.", 0, 001);
		}
		
		// �ϱ��� �ܼ� ����
		diary[0] = new Item("8�� 2�� �ϱ�", "�㸶�� �δ��� ����� ��������. �ۿ��� �̻��� �Ҹ��� �������, �������� ���������� ���ߴ�.", 0, 001);
		diary[1] = new Item("8�� 9�� �ϱ�", "������ ��⳻�� ������ ������ ������ ������. �� �ָ����� Ư���� �߰��� �Ҹ��� �鸰��. ��� ����ؼ� ���� ȸ����� �����ε�.. ��ü ����������?", 0, 001);
		diary[2] = new Item("8�� 16�� �ϱ�", "���� �߱��ϴ� �����ϳ��� �׻� ���� 9�ð� �Ǹ� ���ָ��� ������ ���ٰ� �����. �и� �Ҹ��� ���� ��ġ�� �����ֿ� ���� �߰��� �Ҹ��� ����� �����̶� ��ġ�Ѵ�. Ȯ���� ���� ȸ�� ������ � ���� �߻��ϰ� �ִٴ� ���̴�.", 0, 001);
		diary[3] = new Item("8�� 23�� �ϱ�", "��ϱ⸦ ���ؼ� ������ �Ȱ� �־��µ�, �츮 ���� ���� ȸ���ϴ� �ٸ� �μ� ����� ������. �� ����� ���� ������ ������, �� ��򰡿� Ȧ���ִ� �� �ߴ�. ���� �λ縦 �ߴµ�, ������ ���� ���˾� ���� ��ġ����.", 0, 001);
		diary[4] = new Item("8�� 30�� �ϱ�", "8������ �������� �׻� ���ö��� �ΰ��µ�, ������ �Ĵ翡�� ������ �ذ��ߴ�. ���� ���־��µ�, �߱����� ���ݵ� �������� ���̴�. �ϱ⾲�� ���߿� ����9�ð� �� �� ����. ���ó� ������ �鸮�µ�, ���ڱ� �� ���� Ǯ����������.��.@ ", 0, 001);
		
		// ���� ������ ����
		for (int idx = 0; idx < 4; idx++) {
			document[idx] = new Item(idx + 1 + "�� ����", "���� �������̴�.", 0, 001);
		}
	}
	
	public Item getItem(int num) {
		return itemInitialize(num);
	}
 }
