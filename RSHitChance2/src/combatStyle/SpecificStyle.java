//package combatStyle;
//
//import java.awt.Color;
//
//class SpecificStyle implements PrimaryCombatStyle{
//    
//    private PrimaryCombatStyle generalStyle;//melee,range,magic
//    private String name;
//    
//    public SpecificStyle(PrimaryCombatStyle generalStyle, String name){
//        this.generalStyle = generalStyle;
//        this.name = name;
//    }
//
//    @Override
//    public String getName() {
//        return name;
//    }
//
//    @Override
//    public Color getColor() {
//        return generalStyle.getColor();
//    }
//
//    @Override
//    public PrimaryCombatStyle getWeakAgainst() {
//        return generalStyle.getWeakAgainst();
//    }
//
//    @Override
//    public PrimaryCombatStyle getStrongAgainst() {
//        return generalStyle.getStrongAgainst();
//    }
//    
//    @Override
//    public OffensiveCombatStyle getGeneralStyle() {
//        return generalStyle;
//    }
//    
//    @Override
//    public String toString(){
//        return getName();
//    }
//
//}
