package menu;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    private JPanel buttons;
    private JButton nowy;
    private JButton edytuj;
    private JButton usun;
    private JButton filtruj;
    
    private JList<Recipe> menu;
    private RecListModel menuModel;
    private JScrollPane jsp;
    
    MainFrame() {
        setTitle("PRZEPISY");
        setLayout(new BorderLayout());
        
        setButtons();
        
        menuModel = new RecListModel();
        menu = new JList(menuModel);
        jsp = new JScrollPane(menu, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        add(buttons, BorderLayout.PAGE_END);
        add(jsp, BorderLayout.CENTER);
        
        populate();
        
        setPreferredSize(new Dimension(800, 600));
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void setButtons() {
        buttons = new JPanel();
        nowy = new JButton("NOWY");
        nowy.addActionListener((e) -> {
            new RecipeFrame("Dodawanie nowego przepisu", menuModel, RecipeFrame.Mode.NEW);
        });
        
        edytuj = new JButton("EDYTUJ");
        edytuj.addActionListener((e) -> {
            new RecipeFrame("Edycja przepisu", menuModel, RecipeFrame.Mode.EDIT, menu.getSelectedValue(), menu.getSelectedIndex());
        });
        
        usun = new JButton("USUŃ");
        usun.addActionListener((e) -> {
            if(!menu.isSelectionEmpty()) {
                File f = new File("recipes\\" + menu.getSelectedValue().toString() + ".rc");
                f.delete();
                menuModel.delElement(menu.getSelectedIndex());
            }
        });
        
        filtruj = new JButton("FILTRUJ");
        //dodaj filtrowanie ****************************************************************************************
        
        buttons.add(nowy);
        buttons.add(edytuj);
        buttons.add(usun);
        buttons.add(filtruj);
    }
    
    private void populate() {
        JFileChooser jfc = new JFileChooser("recipes");
        
        for(File f : Arrays.asList(jfc.getCurrentDirectory().listFiles())) {
            if(f.toString().matches(".+\\.rc")) {
                try {
                    FileInputStream fis = new FileInputStream(f);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    Recipe r = (Recipe)ois.readObject();
                    ois.close();
                    fis.close();
                    menuModel.addElement(r);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
