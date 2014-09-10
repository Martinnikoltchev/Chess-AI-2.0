package View;

import Model.Gamestate;
import Model.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created with IntelliJ IDEA.
 * User: Martin
 * Date: 8/21/13
 * Time: 12:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class BoardPanel extends JPanel {

    public static int height;
    public static int rectSize;
    Gamestate state = Gamestate.getInstance();

    public BoardPanel(int height){
        this.height = height;
        init();
    }

    public void init(){
        rectSize = height/8;
        setSize(height, height);
    }

    public void paintComponent(Graphics g){
        paintBoard(g);
        drawMovables(g);
        drawPieces(g);
    }

    public void paintBoard(Graphics g){
        g.setColor(new Color(191,147,71));
        g.fillRect(0,0,height,height);
        g.setColor(new Color(104, 77, 36));
        for(int c = 0; c < 8; c++){
            for(int r = 0; r < 8; r++){
                if((r%2+c%2)==1)
                    g.fillRect(r*rectSize,c*rectSize,rectSize, rectSize);
            }
        }
    }
    private void drawMovables(Graphics g){
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < state.pieces[i].size(); j++){
                state.pieces[i].get(j).drawMovable(g);
            }
        }
    }

    private void drawPieces(Graphics g){
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < state.pieces[i].size(); j++){
                state.pieces[i].get(j).drawPiece(g);
            }
        }
    }
}
