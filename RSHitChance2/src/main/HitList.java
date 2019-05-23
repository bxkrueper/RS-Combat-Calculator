//package main;
//
//import java.util.ArrayList;
//import java.util.List;
//
///*
// * stores all information about an attack's final hits
// */
//public class HitList {
//	private final List<Hit> hitList;
//	
//	public HitList() {
//		this.hitList = new ArrayList<>();
//	}
//
//	public void clear() {
//		hitList.clear();
//	}
//	
//	public void addHit(Hit hit) {
//		hitList.add(hit);
//	}
//
//	public int numberOfHits() {
//		return hitList.size();//other list should be the same size
//	}
//
//	public Hit get(int hitNumber) {
//		return hitList.get(hitNumber);
//	}
//
//	public void applyEffectMultiplier(double effectMultiplier) {
//		for(int i=0;i<hitList.size();i++) {
//			if(hitList.get(i).applyEffectMultiplier()) {
//				hitList.get(i).multiply(effectMultiplier);
//			}
//		}
//	}
//
//	public void applyDamageFromExtraPowerLevels(int endDamageFromExtraPowerLevels) {
//		for(int i=0;i<hitList.size();i++) {
//			if(hitList.get(i).applyEffectMultiplier()) {
//				hitList.get(i).add(endDamageFromExtraPowerLevels);
//			}
//		}
//	}
//	
//	public void applyAutoAttackMultiplier(double autoMultiplier) {
//		for(int i=0;i<hitList.size();i++) {
//			if(hitList.get(i).applyAutoAttackEffectMultiplier()) {
//				hitList.get(i).multiply(autoMultiplier);
//			}
//		}
//	}
//
//    public int getTotalDamage() {
//        int total = 0;
//        for(int i=0;i<hitList.size();i++) {
//            total+=hitList.get(i).getDamage();
//        }
//        return total;
//    }
//}
