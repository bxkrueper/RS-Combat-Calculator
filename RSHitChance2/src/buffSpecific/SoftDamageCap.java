package buffSpecific;

import java.util.List;

import buff.BuffName;
import buff.ConstantFillInBuff;
import calculations.Hit;
import combatent.Combatent;

public class SoftDamageCap extends ConstantFillInBuff{

    private int threshold;
    private double excessDamageMultiplier;
    
    public SoftDamageCap(BuffName name, int threshold, double excessDamageMultiplier) {
        super(name);
        this.threshold = threshold;
        this.excessDamageMultiplier = excessDamageMultiplier;
    }
    
    public SoftDamageCap(BuffName name, int threshold, double excessDamageMultiplier, String pictureName) {
        this(name,threshold,excessDamageMultiplier);
        this.setPictureName(pictureName);
    }
    
    @Override
    public void affectOpponentFinalHitList(List<Hit> list,Combatent owner, Combatent opponent) {
        for(int i=0;i<list.size();i++) {
            Hit hit = list.get(i);
            double minDamage = hit.getMinDamage();
            double maxDamage = hit.getMaxDamage();
            
            minDamage = getNerfedDamage(minDamage);
            maxDamage = getNerfedDamage(maxDamage);
            
            hit.setMinDamage(minDamage);
            hit.setMaxDamage(maxDamage);
        }
    }

    private double getNerfedDamage(double originalDamage) {
        double excessDamage = originalDamage-threshold;
        if(excessDamage<0) {
            return originalDamage;//no effect
        }else {
            
            return threshold+excessDamage*excessDamageMultiplier;
        }
    }

}
