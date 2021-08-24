
public class HealSkill extends Skill{
	int healling;
	
	HealSkill(String skillName, int healling) {
		super(skillName);
		// TODO Auto-generated constructor stub
		this.healling = healling;
	}
	
	void setHealling(int healling) {
		this.healling = healling;
	}
	
	int getHealling() {
		return healling;
	}
}
