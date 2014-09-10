package Model.Pieces;

import Model.Gamestate;
import View.BoardPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Martin
 * Date: 9/16/13
 * Time: 7:31 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class GamePiece {

    public int x;
    public int y;
    public int pieceVal;
    String icon;
    int offSet;
    String iconName;
    public Rectangle[][] movableLocs;
    BufferedImage iPiece = null;
    public int player;
    Gamestate state = Gamestate.getInstance();
    int direction;
    int other;
    public GamePiece(int x, int y, int player){
        this.x = x;
        this.y = y;
        this.player = player;
        offSet = BoardPanel.rectSize/2-20;
        movableLocs = new Rectangle[8][8];
        direction=player*2-1;
        other = (player*(0-1))+1;
    }

    public void drawPiece(Graphics g){
        try{
            g.drawImage(iPiece, x*BoardPanel.rectSize, y*BoardPanel.rectSize, null);
        }catch(Exception e){
            g.setFont(new Font("Arial", 0, 40));
            g.drawString(icon, x*BoardPanel.rectSize+offSet, (y+1)*BoardPanel.rectSize-offSet);
        }
    }
    public void drawMovable(Graphics g){
        g.setColor(new Color(224,159,55));
        for(int c = 0; c < 8; c++){
            for(int r = 0; r < 8; r++){
                if(movableLocs[r][c]!=null){
                    g.fillRect(movableLocs[r][c].x, movableLocs[r][c].y, BoardPanel.rectSize, BoardPanel.rectSize);
                }
            }
        }
    }
    public void makeMovables(){
        movableLocs=new Rectangle[8][8];
            for(int c = 0; c < 8; c++){
                for(int r = 0; r < 8; r++){
                    if(movable(r,c)&&state.findPlayerPiece(r,c,player)==null){
                        movableLocs[r][c] = new Rectangle(r*BoardPanel.rectSize, c*BoardPanel.rectSize, BoardPanel.rectSize, BoardPanel.rectSize);
                    }
                    else
                        movableLocs[r][c] = null;
                }
            }
    }

    public void removeOtherMovables(){
        for(int c = 0; c < 8; c++){
            for(int r = 0; r < 8; r++){
                movableLocs[r][c] = null;
            }
        }
        movableLocs[x][y] = new Rectangle(x*BoardPanel.rectSize, y*BoardPanel.rectSize, BoardPanel.rectSize, BoardPanel.rectSize);
    }

    public void movePiece(int r,int c){
        GamePiece piece = state.findPlayerPiece(r, c, other);
        if(piece!=null){
            state.removePiece(piece);
        }
        x = r;
        y = c;
    }

    public void setIcon(){
        try {
            iPiece = ImageIO.read(new File(iconName));
        } catch (IOException ex) {System.out.println("IMG Not Found");}
    }

    public boolean movable(int r, int c){
        if(state.findPlayerPiece(r,c,player)!=null||this.equals(state.findPiece(r,c)))
            return false;
        return pMovable(r,c);
    }

    public void printMove(int r, int c){
        System.out.println("Move: "+ icon + " from " + x + ", " + y + " to  "+ r+", "+c);
    }

    public abstract boolean pMovable(int r, int c);
    public abstract GamePiece clone();
}
