package combatent;

import combatStyle.Magic;
import combatStyle.Melee;
import combatStyle.PrimaryCombatStyle;
import combatStyle.Ranged;
import javafx.scene.image.Image;
import resources.ImageFlyweight;

//groups up all affinity information for monsters
public class MonsterAffinityWeaknesses {
    
    private int meleeAffinity;
    private int rangedAffinity;
    private int magicAffinity;
    private SpecialAffinity specialAffinity;//can be null (doesn't have an extreme weakness)   ex: a monster can be particularly weak against fire instead of just magic
    
    public MonsterAffinityWeaknesses(int meleeAffinity, int rangedAffinity,int magicAffinity,SpecialAffinity affinityWeakness){
        this.meleeAffinity = meleeAffinity;
        this.rangedAffinity = rangedAffinity;
        this.magicAffinity = magicAffinity;
        this.specialAffinity = affinityWeakness;
    }
    
    public int getMeleeAffinity(){
        return meleeAffinity;
    }
    public int getRangedAffinity(){
        return rangedAffinity;
    }
    public int getMagicAffinity(){
        return magicAffinity;
    }

    //checks special affinity first, then goes with melee/range/magic affinity
    public int getAffinityTo(PrimaryCombatStyle cbs) {
        if(specialAffinity!=null && cbs.getName().equals(specialAffinity.getCombatStyle().getName())){
            return specialAffinity.getAffinity();
        }else{
            return getCombatTriangleAffinityTo(cbs);
        }
    }
    
    //ignores special styles, only looks at melee, range, or magic
    private int getCombatTriangleAffinityTo(PrimaryCombatStyle cbs){
        if(cbs.isSameGeneralPrimaryStyleAs(Melee.getInstance())){
            return meleeAffinity;
        }
        if(cbs.isSameGeneralPrimaryStyleAs(Ranged.getInstance())){
            return rangedAffinity;
        }
        if(cbs.isSameGeneralPrimaryStyleAs(Magic.getInstance())){
            return magicAffinity;
        }
        
        System.out.println("getCombatTriangleAffinityTo(): comat style is not melee,range,or magic. returned -1");
        return -1;
    }

    public boolean hasSpecialAffinity() {
        return specialAffinity!=null;
    }

    public Image getSpecialAffinityPicture() {
        if(specialAffinity==null){
            return ImageFlyweight.getDefaultImage();
        }else{
            return specialAffinity.getCombatStyle().getImage();
        }
    }

    public int getSpecialAffinityWeaknessValue() {
        if(specialAffinity==null){
            return -1;
        }else{
            return specialAffinity.getAffinity();
        }
    }
}
