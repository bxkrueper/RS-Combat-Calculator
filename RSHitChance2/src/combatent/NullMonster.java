package combatent;
import abilities.Ability;
/*
 * this monster displayed when the program launches
 */
import buff.Buff;
import buff.Buffs;
import combatStyle.OffensiveCombatStyle;
import combatStyle.PrimaryCombatStyle;
import combatStyle.SoftTypeless;
import javafx.scene.image.Image;
import resources.ImageFlyweight;


public class NullMonster implements Monster{
    
    private Image image;
    private MonsterStats stats;
    private String link;
    private Vulnerabilities vulnerabilities;
    private Buffs nullBuffs;
    private Attacks attacks;
    private MonsterAffinityWeaknesses affinityWeaknesses;
    
    public NullMonster(){
        this.image = ImageFlyweight.getImage("images/Monsters/Null Monster",MonsterSingle.IMAGE_WIDTH,MonsterSingle.IMAGE_HEIGHT,true,true);
        this.stats = new MonsterStats(0,0,0,0,0,0,0,0);
        this.link = "https://runescape.wiki/w/Boss";
        this.vulnerabilities = new Vulnerabilities();
        this.nullBuffs = new Buffs();
        this.attacks = new Attacks(new MonsterAttack(SoftTypeless.getInstance(),0,false));
        this.affinityWeaknesses = new MonsterAffinityWeaknesses(0,0,0,null);
    }

    @Override
    public String getName() {
        return "Null Monster";
    }

    @Override
    public Image getImage() {
        return image;
    }
    
    @Override
    public String getLink(){
        return link;
    }

    @Override
    public MonsterStats getStats() {
        return stats;
    }

    @Override
    public OffensiveCombatStyle getCombatStyle() {
        return attacks.getAttack().getCombatStyle();
    }

    @Override
    public MonsterAffinityWeaknesses getAffinityWeaknesses() {
        return affinityWeaknesses;
    }
    
    @Override
    public double getAffinityTo(PrimaryCombatStyle cbs) {
        return 0;
    }
    
    @Override
    public Vulnerabilities getVulnerabilities() {
        return vulnerabilities;
    }
    
    @Override
    public Buff getNaturalBuffs() {
        return this.nullBuffs;
    }
    
    @Override
    public Attacks getAttacks() {
        return attacks;
    }

    @Override
    public int getBaseAccuracyLevel() {
        return 0;
    }

    @Override
    public int getNaturalAccuracy() {
        return 0;
    }
    
    @Override
    public double getAccuracyPenaltyFromWrongArmor() {
        return 0.0;
    }

    @Override
    public int getBaseDefenseLevel() {
        return 0;
    }

    @Override
    public double getNaturalArmor() {
        return 0.0;
    }
    
    @Override
    public double getNaturalAbsorbsion() {
    	return 1.0;
    }

    @Override
    public int getBasePowerLevel() {
        return 0;
    }

    @Override
    public Buffs getBuffs() {
        return nullBuffs;
    }
    
    public Buffs getEditableBuffs() {
        return MonsterSingle.editableBuffs;
    }

    @Override
    public String toString(){
        return "Null Monster";
    }

    @Override
    public boolean canAttack(Combatent opponent) {
        return false;
    }

	@Override
	public double getBaseDamage() {
		return 0.0;
	}
	
	@Override
	public Attack getAttack() {
		return getAttacks().getAttack();
	}
    

    
}
