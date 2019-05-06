package combatent;
/*
 * composite pattern for monster
 * acts like the monster that is currently selected
 */
import buff.Buff;
import buff.Buffs;
import combatStyle.OffensiveCombatStyle;
import combatStyle.PrimaryCombatStyle;
import javafx.scene.image.Image;


public class MonsterGroup implements MonsterInterface{

    private String groupName;
    private Monster[] monsterArray;
    private int selected;
    
    public MonsterGroup(String groupName, Monster[] monsterArray){
        this.groupName = groupName;
        this.monsterArray = monsterArray;
        this.selected = 0;
    }
    
    public void setSelected(int newSelected){
        this.selected = newSelected;
    }
    
    public int getSelected(){
        return selected;
    }
    
    public int getNumberOfMonsters(){
        return monsterArray.length;
    }
    
    public String getGroupName(){
        return groupName;
    }
    
    public int getSize(){
        return monsterArray.length;
    }
    
    @Override
    public String getName() {
        return monsterArray[selected].getName();
    }

    @Override
    public Image getImage() {
        return monsterArray[selected].getImage();
    }
    
    @Override
    public String getLink(){
        return monsterArray[selected].getLink();
    }
    
    @Override
    public MonsterStats getStats() {
        return monsterArray[selected].getStats();
    }
    
    @Override
    public OffensiveCombatStyle getCombatStyle() {
        return monsterArray[selected].getCombatStyle();
    }
    
    @Override
    public Attacks getAttacks() {
        return monsterArray[selected].getAttacks();
    }
    
    @Override
    public MonsterAffinityWeaknesses getAffinityWeaknesses() {
        return monsterArray[selected].getAffinityWeaknesses();
    }

    @Override
    public int getAffinityTo(PrimaryCombatStyle cbs) {
        return monsterArray[selected].getAffinityTo(cbs);
    }
    
    @Override
    public Vulnerabilities getVulnerabilities() {
        return monsterArray[selected].getVulnerabilities();
    }
    
    @Override
    public Buff getNaturalBuffs() {
        return monsterArray[selected].getNaturalBuffs();
    }
    
    public Buffs getEditableBuffs() {
        return monsterArray[selected].getEditableBuffs();
    }
    
    @Override
    public int getBaseAccuracyLevel() {
        return monsterArray[selected].getBaseAccuracyLevel();
    }

    @Override
    public int getNaturalAccuracy() {
        return monsterArray[selected].getNaturalAccuracy();
    }
    
    @Override
    public int getAccuracyPenaltyFromWrongArmor() {
        return monsterArray[selected].getAccuracyPenaltyFromWrongArmor();
    }

    @Override
    public int getBaseDefenseLevel() {
        return monsterArray[selected].getBaseDefenseLevel();
    }

    @Override
    public int getNaturalArmor() {
        return monsterArray[selected].getNaturalArmor();
    }
    
    @Override
    public String toString(){
        return "monster group: " + getGroupName() + " Monster: " + monsterArray[selected].toString();
    }
    
    public void nextMonster(){
        selected++;
        if(selected>=monsterArray.length){
            selected=0;
        }
    }

    public void prevMonster(){
        selected--;
        if(selected<0){
            selected=monsterArray.length-1;
        }
    }

    @Override
    public int getBasePowerLevel() {
        return monsterArray[selected].getBasePowerLevel();
    }

    @Override
    public Buffs getBuffs() {
        return monsterArray[selected].getBuffs();
    }

    @Override
    public boolean canAttack(Combatent opponent) {
        return monsterArray[selected].canAttack(opponent);
    }

    

    

    

    

    
}
