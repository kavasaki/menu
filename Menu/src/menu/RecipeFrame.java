package menu;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import javax.swing.*;

public class RecipeFrame extends JFrame {
    private IngredientsPane ingredients;
    private RecListModel menuModel;
    private Mode mode;
    private Recipe recipe;
    private int index;
    
    private JPanel buttons;
    private JButton add;
    private JButton delete;
    private JButton ok;
    private JButton cancel;
    
    RecipeFrame(String name, RecListModel menuModel, Mode mode) {
        setLayout(new BorderLayout());
        setTitle(name);
        
        this.menuModel = menuModel;
        this.mode = mode;
        recipe = null;
        index = 0;
        ingredients = new IngredientsPane();
        add(ingredients.getP(), BorderLayout.CENTER);
        
        setButtons();
        add(buttons, BorderLayout.LINE_END);
        
        setPreferredSize(new Dimension(640, 480));
        pack();
        setVisible(true);
    }
    
    RecipeFrame(String name, RecListModel menuModel, Mode mode, Recipe recipe, int index) {
        this(name, menuModel, mode);
        this.recipe = recipe;
        this.index = index;
        populate(recipe);
    }
    
    private void setButtons() {
        buttons = new JPanel(new GridLayout(4, 1));
        add = new JButton("+");
        add.addActionListener((e) -> {
            ingredients.addC();
        });
        
        delete = new JButton("-");
        delete.addActionListener((e) -> {
            ingredients.remC();
        });
        
        ok = new JButton("OK");
        ok.addActionListener((e) -> {
            JOptionPane pane = new JOptionPane();
            String name = "";
            int option;
            if(mode == Mode.NEW) {
                name = pane.showInputDialog(this, "Wpisz nazwę przepisu");
            } else if(mode == Mode.EDIT) {
                name = pane.showInputDialog(this, "Wpisz nazwę przepisu", recipe.toString());
            }
            
            if(name != null) {
                if(mode == Mode.EDIT) {
                    File f = new File("recipes\\" + recipe.toString() + ".rc");
                    f.delete();
                    menuModel.delElement(index);
                }
                
                recipe = new Recipe(name, ingredients.getList());
                menuModel.addElement(recipe);
                saveRecipe(recipe);
                dispose();
            }
        });
        
        cancel = new JButton("ANULUJ");
        cancel.addActionListener((e) -> {
            dispose();
        });
        
        buttons.add(add);
        buttons.add(delete);
        buttons.add(ok);
        buttons.add(cancel);
    }
    
    private void saveRecipe(Recipe rec) {
        try {
            FileOutputStream fos = new FileOutputStream("recipes\\" + rec.toString() + ".rc");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(rec);
            oos.close();
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void populate(Recipe recipe) {
        HashMap<String, Recipe.Values> map = recipe.getIngredients();
        for(String s : map.keySet()) {
            ingredients.addC(s, map.get(s).getQuantity(), map.get(s).getUnit());
        }
    }
    
    public enum Mode{NEW, EDIT};
}
