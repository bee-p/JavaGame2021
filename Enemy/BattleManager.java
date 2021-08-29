import java.util.*;

//enemyAttackCount 추가해서 수정하기
//Enemy에 플레이어 랭크 int로 받아오는 걸로 수정하기



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
	
	//적을 공격
	void attackEnemy(int enemyNum) {
		Enemy enemy = enemys[enemyNum];
		enemy.beAttacked(player.attackPower);
		
		//적이 분노 성질을 갖고 있다면 분노발동 연산
		if(enemy instanceof RageEnemy) {
			((RageEnemy) enemy).rage();
		}
		
		//적 사망시 적 삭제
		if(enemy.getHeart() <= 0) {
			player.saveInventory(enemy.death());
			for(int i = enemyNum;i<enemyCount-1;i++) {
				enemys[i] = enemys[i+1];
			}
			enemyCount--;
		}
	}
	
	//플레이어를 공격
	void attackPlayer() {
		for(Enemy enemy: enemys) {
			//스킬 사용 적
			if(enemy instanceof SkillEnemy) {
				Skill skill = ((SkillEnemy) enemy).useSkill();
				if(skill != null) {
					if(skill instanceof DamageSkill) {
						player.attacked(((DamageSkill) skill).useSkill());
					}
					else if(skill instanceof DebuffSkill) {
						//플레이어 방어력/공격력 갱신 함수 필요
						player.defensivePower = ((DebuffSkill) skill).DefenseDebuff(player.defensivePower);
						player.attackPower = ((DebuffSkill) skill).AttackDebuff(player.attackPower);
					}
				}
			}
			//분노 적
			else if(enemy instanceof RageEnemy) {
				//분노 상태일 때
				if(((RageEnemy) enemy).getIsRaged()) {
					//명중률에 따른 공격
					if(new Random().nextInt(100) >= ((RageEnemy) enemy).getHitRate()) {
						player.attacked(enemy.getAttackPower());
					}
				}
				else {
					player.attacked(enemy.getAttackPower());
				}
			}
			//일반 적
			else {
				player.attacked(enemy.getAttackPower());
			}
		}
	}
	
	//대화하기
	void interaction(int enemyNum, int skillCode) {
		Enemy enemy = enemys[enemyNum];
		player.saveInventory(enemy.interaction(skillCode, player.getCurrentRank()));
		if(skillCode == 2) {
			//플레이어의 평판도를 올리는 함수 필요
			player.reputation += enemy.reputation;
		}
		//적 삭제
		for(int i = enemyNum;i<enemyCount-1;i++) {
			enemys[i] = enemys[i+1];
		}
		enemyCount--;
		
	}
	
	//아이템 사용
	void useItem(int inventoryNum) {
		player.useInventory(inventoryNum);
	}
	
	//도망가기
	boolean runAway() {
		//모든 몬스터 중 가장 낮은 도망갈 확률로 진행
		int runAwayPercentage = 100;
		for(Object enemyObject: enemys) {
			Enemy enemy = (Enemy) enemyObject;
			if(runAwayPercentage > enemy.getRunAwayPercentage())
				runAwayPercentage = enemy.getRunAwayPercentage();
		}
		return (new Random().nextInt(100) < runAwayPercentage) ? false: true; 
	}
	
	//배틀 종료 여부(어떤 행동 이후 반드시 이 함수 사용
	boolean battleEndCheck() {
		if(player.hp <= 0 || enemyCount == 0)
			return true;
		else
			return false;
	}
}
