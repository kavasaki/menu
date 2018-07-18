package menu;

import java.io.Serializable;
import java.util.*;

public class Recipe implements Serializable {
    private String name;
    private HashMap<String, Values> ingredients;
    
    Recipe(String name, ArrayList<IngredientLabel> a) {
        this.name = name;
        ingredients = new HashMap();
        
        for(IngredientLabel il : a) {
            ingredients.put(il.getIngredient(), new Values(il.getQuantity(), il.getUnit()));
        }
    }
    
    public HashMap getIngredients() {
        return ingredients;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    public class Values implements Serializable {
        private double q;
        private Unit u;
        
        Values(double q, Unit u) {
            Values.this.q = q;
            Values.this.u = u;
        }
        
        public double getQuantity() {
            return q;
        }
        
        public Unit getUnit() {
            return u;
        }
    }
}
