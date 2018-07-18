package menu;

public enum Unit{
    KG("kg"), G("g"), L("l"), ML("ml"), SZT("szt");
    
    private String unit;
    
    private Unit(String unit) {
        this.unit = unit;
    }
    
    public String getUnit() {
        return unit;
    }
    
//    private long convert(long quantity, Unit unit) {
//        switch(unit) {
//            case KG:
//                
//        }
//        return 1L;
//    }
}