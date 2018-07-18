package menu;

import java.util.*;
import javax.swing.AbstractListModel;

public class RecListModel extends AbstractListModel {
    private List<Recipe> lista;
    
    RecListModel() {
        lista = new ArrayList();
    }
    
    public void addElement(Recipe rec) {
        lista.add(rec);
        fireIntervalAdded(this, getSize(), getSize()+1);
    }
    
    public void delElement(int index) {
        lista.remove(index);
        fireIntervalRemoved(this, index, index+1);
    }
    
    public void updElement(Recipe rec) {
        lista.add(0, rec);
    }
    
    @Override
    public int getSize() {
        return lista.size();
    }

    @Override
    public Object getElementAt(int index) {
        return (Recipe)lista.get(index);
    }
    
}
