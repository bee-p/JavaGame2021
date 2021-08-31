
public class EnemyPair {
	Enemy enemySet[];
	int engagePer;
	int enemyCount = 0;
	
	EnemyPair(Enemy[] enemys, int engagePer){
		for(Enemy enemy : enemys) {
			enemySet[enemyCount] = enemy;
			enemyCount++;
		}
		this.engagePer = engagePer;
	}
	
	EnemyPair(Enemy enemy, int engagePer){
		enemyCount = 1;
		enemySet[0] = enemy;
		this.engagePer = engagePer;
	}
}
