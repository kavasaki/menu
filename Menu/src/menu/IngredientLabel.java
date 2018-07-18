package menu;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;

class IngredientLabel extends JPanel {
    private JTextField ingredient;
    private JTextField quantity;
    private JComboBox unit;

    IngredientLabel() {
        setLayout(new GridLayout(1, 2));
        setMaximumSize(new Dimension(1000, 25));
        
        ingredient = new JTextField();
        quantity = new JTextField();
        unit = new JComboBox(Unit.values());
        
        add(ingredient);
        JPanel p2 = new JPanel(new GridLayout(1, 2));
        p2.add(quantity);
        p2.add(unit);
        add(p2);
    }
    
    IngredientLabel(String ingredient, double quantity, Unit unit) {
        this();
        this.ingredient.setText(ingredient);
        this.quantity.setText(Double.toString(quantity));
        this.unit.setSelectedItem(unit);
    }
    
    public String getIngredient() {
        return ingredient.getText();
    }
    
    public double getQuantity() {
        if(quantity.getText().isEmpty())
            return 0d;
        return Double.parseDouble(quantity.getText());
    }
    
    public Unit getUnit() {
        return (Unit)unit.getSelectedItem();
    }
}
