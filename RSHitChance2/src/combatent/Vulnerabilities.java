package combatent;
/*
 * this is a list of buffs the monster is susceptible too (eg slayer mask)
 * Buffs that affect everything like affinity raise do not check this class and there is no need to include them here
 */
import java.util.ArrayList;
import java.util.List;

import buff.BuffName;

public class Vulnerabilities {
    private List<BuffName> vulnList;
    
    public Vulnerabilities(){
        this.vulnList = new ArrayList<>();
    }
    
    public List<BuffName> getVulnList() {
        return vulnList;
    }
    
    public void addBuff(BuffName vulnName) {
        vulnList.add(vulnName);
    }
    public void addBuffToStart(BuffName vulnName) {
        vulnList.add(0,vulnName);
    }
    
    public boolean isVulnerableTo(BuffName buffName) {
        for(int i=0;i<vulnList.size();i++){
            if(vulnList.get(i).equals(buffName)){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString(){
        return vulnList.toString();
    }
}