
public class DebuffSkill extends Skill{
	int defenseDebuffRate;
	int AttackDebuffRate;
	
	DebuffSkill(String skillName, int defenseDebuff, int AttackDebuff) {
		super(skillName);
		// TODO Auto-generated constructor stub
		this.defenseDebuffRate = defenseDebuff;
		this.AttackDebuffRate = AttackDebuff;
	}

	int DefenseDebuff(int playerDefense) {
		return playerDefense / 100 * (100 - defenseDebuffRate);
	}
	
	int AttackDebuff(int playerAttackPower) {
		return playerAttackPower / 100 * ( 100 - AttackDebuffRate);
	}

}
