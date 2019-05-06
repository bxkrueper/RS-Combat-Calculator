package equipment;

public enum WeaponSpeed {
    Fastest (1,1),
    Fast (1.25,192/(double) 245),
    Average(1.5,96/(double) 149),
    Slow(0,0),///////? unknown
    Slowest(0,0),/////////?unknown
    NA(0,0);
    
    private final double meleeMod;
    private final double speedMod;
    WeaponSpeed(double speedMod,double meleeMod){
        this.speedMod = speedMod;
        this.meleeMod = meleeMod;
    }
    
    public double speedMod(){
        return speedMod;
    }
    
    public double meleeMod(){/////////range and mage use these values inversed fore auto attacks
        return meleeMod;
    }
    
    }
