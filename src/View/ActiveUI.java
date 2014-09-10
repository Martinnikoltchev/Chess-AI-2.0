package View;

import Model.Gamestate;
import Model.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created with IntelliJ IDEA.
 * User: Martin
 * Date: 9/16/13
 * Time: 5:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class ActiveUI extends JFrame {

    BoardPanel drawPanel;
    Gamestate state;
    Manager manager;
    public ActiveUI(String name, boolean vsAI){
        super(name);
        setSize(600, 600 + 22);
        drawPanel = new BoardPanel(600);
        add(drawPanel);
        state = Gamestate.getInstance();
        state.vsAI = vsAI;
        manager.getInstance(this);
        addMouseListener(MListener.getListener());
        repaint();
    }
}

