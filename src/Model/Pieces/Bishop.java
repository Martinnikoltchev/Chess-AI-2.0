package Model.Pieces;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Martin
 */
public class Bishop extends GamePiece implements Cloneable{


    public Bishop(int x, int y, int player){
        super(x, y, player);
        if(player==0)
            iconName = "Images/BlackBishop.png";
        else
            iconName = "Images/WhiteBishop.png";
        icon = "B";
        pieceVal = 3;
    }

    public boolean pMovable(int r, int c) {
        if(Math.abs(y-c) == Math.abs(x-r)){
            if(r!=x||c!=y)
                if(!collides(r,c))
                    return true;
        }
        return false;
    }


    private boolean collides(int r, int c){

        int xDir = r-x;
        int yDir = c-y;
        try{
            xDir/=Math.abs(xDir);
            yDir/=Math.abs(yDir);
        }catch(Exception e){}
        for(int i = 1; i < Math.abs(x-r); i++){
            if(state.pieceHere(x+xDir*i, y+yDir*i))
                return true;
        }
        return false;
    }
    public GamePiece clone(){
        GamePiece clone = null;
        try {
            clone = new Bishop(x, y, player);
        } catch (Exception ex) {System.out.println("ERROR2");}
        return clone;
    }
}
