package combatent;
/*
 * a basic monster. Attacks can be cycled through
 */
import buff.Buff;
import buff.Buffs;
import combatStyle.CantAttack;
import combatStyle.OffensiveCombatStyle;
import combatStyle.PrimaryCombatStyle;
import javafx.scene.image.Image;
import resources.ImageFlyweight;

public class MonsterSingle implements Monster{
    public static final String PATH_TO_MONSTER_IMAGES = "images/Monsters/";
    public static final int IMAGE_WIDTH = 480;
    public static final int IMAGE_HEIGHT = 250;
    
    private String name;
    private String pictureName;
    private Image image;//only loaded in when needed
    private String wikiLink;
    
    private MonsterStats baseStats;
    private MonsterAffinityWeaknesses affinityWeaknesses;//monsters have static base affinities instead of being calculated with armor
    private Vulnerabilities vulnerabilities;
    private Buff naturalBuffs;
    private Attacks attacks;
    final static Buffs editableBuffs = new Buffs();//these are the buffs applied by the player in the interface like quake and blackstone arrows. They don't change when the monster changes
    private Buffs allBuffs;//includes natural and editable buffs. this is the buff returned for combat calculations
    
    public MonsterSingle(String name, String pictureName, String wikiLink, MonsterStats stats,Attacks attacks,MonsterAffinityWeaknesses affinityWeaknesses,Vulnerabilities vulnerabilities,Buff naturalBuffs){
        this.name = name;
        this.pictureName = pictureName;
        this.wikiLink = wikiLink;
        this.baseStats = stats;
        this.affinityWeaknesses = affinityWeaknesses;
        this.vulnerabilities = vulnerabilities;
        this.naturalBuffs = naturalBuffs;
        this.allBuffs = new Buffs();
        this.attacks = attacks;
        allBuffs.addBuff(naturalBuffs);
        allBuffs.addBuff(editableBuffs);
    }
    
    public String getName(){
        return name;
    }
    public Image getImage(){
        if(image==null){
            image = ImageFlyweight.getImage(PATH_TO_MONSTER_IMAGES+pictureName,IMAGE_WIDTH,IMAGE_HEIGHT,true,true);
        }
        return image;
    }
    public String getLink(){
        return wikiLink;
    }
    @Override
    public MonsterStats getStats() {
        return baseStats;
    }
    //returns the combat style of the currently selected attack
    @Override
    public OffensiveCombatStyle getCombatStyle() {
        return attacks.getAttack().getCombatStyle();
    }
    @Override
    public Attacks getAttacks() {
        return attacks;
    }
    @Override
    public MonsterAffinityWeaknesses getAffinityWeaknesses() {
        return affinityWeaknesses;
    }
    @Override
    public int getAffinityTo(PrimaryCombatStyle cbs) {
        return affinityWeaknesses.getAffinityTo(cbs);
    }
    @Override
    public Vulnerabilities getVulnerabilities() {
        return vulnerabilities;
    }
    //for example: nex's turmoil
    @Override
    public Buff getNaturalBuffs(){
        return naturalBuffs;
    }
    @Override
    public Buffs getBuffs() {
        return allBuffs;
    }
    
    @Override
    public Buffs getEditableBuffs() {
        return editableBuffs;
    }

    @Override
    public int getBaseAccuracyLevel() {
        return baseStats.getAccuracyLevel(getCombatStyle());
    }

    @Override
    public int getNaturalAccuracy() {
        return baseStats.getAccuracy(getCombatStyle());
    }
    
    @Override
    public int getAccuracyPenaltyFromWrongArmor() {
        return 0;//monsters don't wear armor
    }

    @Override
    public int getBaseDefenseLevel() {
        return baseStats.getBaseDefenseLevel();
    }

    @Override
    public int getNaturalArmor() {
        return baseStats.getBaseArmour();
    }
    
    @Override
    public double getNaturalAbsorbsion() {
    	return 1.0;
    }

    @Override
    public int getBasePowerLevel() {
        return 0;//Strength for monsters is determined by their current attack, not level
    }

    
    @Override
    public String toString(){
        return name;
    }

    @Override
    public boolean canAttack(Combatent opponent) {
        return getCombatStyle()!=CantAttack.getInstance();
    }

    

    
    

    

}
