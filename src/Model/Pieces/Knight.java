package Model.Pieces;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Martin
 */
public class Knight extends GamePiece implements Cloneable{


    public Knight(int x, int y, int player){
        super(x, y, player);
        if(player==0)
            iconName = "Images/BlackKnight.png";
        else
            iconName = "Images/WhiteKnight.png";
        icon = "Kn";
        pieceVal = 3;
    }

    public boolean pMovable(int r, int c) {
        int xDiff = Math.abs(x-r);
        int yDiff = Math.abs(y-c);
        if(r!=x||c!=y){
            if(xDiff==2&&yDiff==1)
                return true;
            if(xDiff==1&&yDiff==2)
                return true;
        }
        return false;
    }
    public GamePiece clone(){
        GamePiece clone = null;
        try {
            clone = new Knight(x, y, player);
        } catch (Exception ex) {System.out.println("Clone Error");}
        return clone;
    }
}
