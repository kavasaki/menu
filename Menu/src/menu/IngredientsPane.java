package menu;

import java.util.ArrayList;
import javax.swing.*;

class IngredientsPane {
    private JScrollPane jsp;
    private JPanel p;
    private ArrayList<IngredientLabel> panelsList;

    IngredientsPane() {
        p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
        jsp = new JScrollPane(p, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        panelsList = new ArrayList();
    }
    
    public JScrollPane getP() {
        return jsp;
    }
    
    public ArrayList getList() {
        return panelsList;
    }

    public void addC() {
        IngredientLabel il = new IngredientLabel();
        p.add(il);
        panelsList.add(il);
        p.revalidate();
    }
    
    public void addC(String ingr, double quan, Unit u) {
        IngredientLabel il = new IngredientLabel(ingr, quan, u);
        p.add(il);
        panelsList.add(il);
        p.revalidate();
    }
    
    public void remC() {
        if(p.getComponentCount()>0) {
            p.remove(p.getComponentCount()-1);
            panelsList.remove(panelsList.size()-1);
            p.repaint();
            p.revalidate();
        }
    }
}
