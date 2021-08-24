import java.util.*;

//enemyAttackCount �߰��ؼ� �����ϱ�



public class BattleManager {
	Player player;
	Enemy[] enemys = new Enemy[5];
	int enemyCount = 0;
	int enemyAttackCount;
	
	BattleManager(Player player, Enemy[] enemys){
		this.player = player;
		for(Enemy enemy : enemys) {
			this.enemys[enemyCount++] = enemy;
		}
		enemyAttackCount = 0;
	}
	
	//���� ����
	void attackEnemy(int enemyNum) {
		Enemy enemy = enemys[enemyNum];
		enemy.beAttacked(player.attackPower);
		
		//���� �г� ������ ���� �ִٸ� �г�ߵ� ����
		if(enemy instanceof RageEnemy) {
			((RageEnemy) enemy).rage();
		}
		
		//�� ����� �� ����
		if(enemy.getHeart() <= 0) {
			player.saveInventory(enemy.death());
			for(int i = enemyNum;i<enemyCount-1;i++) {
				enemys[i] = enemys[i+1];
			}
			enemyCount--;
		}
	}
	
	//�÷��̾ ����
	void attackPlayer() {
		for(Enemy enemy: enemys) {
			//��ų ��� ��
			if(enemy instanceof SkillEnemy) {
				if(enemyAttackCount >3 && new Random().nextInt(100) < ((SkillEnemy) enemy).getUseSkillRate()) {
					Skill skill = ((SkillEnemy) enemy).useSkill();
					if(skill != null) {
						if(skill instanceof DamageSkill) {
							player.beAttacked(((DamageSkill) skill).useSkill());
						}
						else if(skill instanceof DebuffSkill) {
							//�÷��̾� ����/���ݷ� ���� �Լ� �ʿ�
							player.defensivePower = ((DebuffSkill) skill).DefenseDebuff(player.defensivePower);
							player.attackPower = ((DebuffSkill) skill).AttackDebuff(player.attackPower);
						}
					}
				} else {
					player.beAttacked(enemy.getAttackPower());
				}
				
			}
			//�г� ��
			else if(enemy instanceof RageEnemy) {
				//�г� ������ ��
				if(((RageEnemy) enemy).getIsRaged()) {
					//���߷��� ���� ����
					if(new Random().nextInt(100) >= ((RageEnemy) enemy).getHitRate()) {
						player.beAttacked(enemy.getAttackPower());
					}
				}
				else {
					player.beAttacked(enemy.getAttackPower());
				}
			}
			//�Ϲ� ��
			else {
				player.beAttacked(enemy.getAttackPower());
			}
		}
	}
	
	//��ȭ�ϱ�
	void interaction(int enemyNum, int skillCode) {
		Enemy enemy = enemys[enemyNum];
		player.saveInventory(enemy.interaction(skillCode, player.currentIndex));
		if(skillCode == 2) {
			//�÷��̾��� ���ǵ��� �ø��� �Լ� �ʿ�
			player.reputation += enemy.reputation;
		}
		//�� ����
		for(int i = enemyNum;i<enemyCount-1;i++) {
			enemys[i] = enemys[i+1];
		}
		enemyCount--;
		
	}
	
	//������ ���
	void useItem(int inventoryNum) {
		player.useInventory(inventoryNum);
	}
	
	//��������
	boolean runAway() {
		//��� ���� �� ���� ���� ������ Ȯ���� ����
		int runAwayPercentage = 100;
		for(Object enemyObject: enemys) {
			Enemy enemy = (Enemy) enemyObject;
			if(runAwayPercentage > enemy.getRunAwayPercentage())
				runAwayPercentage = enemy.getRunAwayPercentage();
		}
		return (new Random().nextInt(100) < runAwayPercentage) ? false: true; 
	}
	
	//��Ʋ ���� ����(� �ൿ ���� �ݵ�� �� �Լ� ���
	boolean battleEndCheck() {
		if(player.hp <= 0 || enemyCount == 0)
			return true;
		else
			return false;
	}
}
