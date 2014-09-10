package Model.Pieces;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Martin
 */
public class Rook extends GamePiece implements Cloneable{
    boolean moved = false;
    public Rook(int x, int y, int player){
        super(x ,y, player);
        if(player==0)
            iconName = "Images/BlackRook.png";
        else
            iconName = "Images/WhiteRook.png";
        icon = "R";
        pieceVal = 5;
    }

    public boolean pMovable(int r, int c) {
        int xDiff = Math.abs(x-r);
        int yDiff = Math.abs(y-c);
        if(xDiff==0&&yDiff>0)
            if(!collides(r,c))
                return true;
        if(xDiff>0&&yDiff==0)
            if(!collides(r,c))
                return true;
        return false;
    }

    public boolean collides(int r, int c){
        if(r!=x||c!=y){
            if(x!=r){
                int xDir = r-x;
                try{
                    xDir/=Math.abs(xDir);
                }catch(Exception e){}
                for(int i = 1; i < Math.abs(x-r); i++){
                    if(state.pieceHere(x+xDir*i, c))
                        return true;
                }
            }
            else if(y!=c){
                int yDir = c-y;
                try{
                    yDir/=Math.abs(yDir);
                }catch(Exception e){}
                for(int i = 1; i < Math.abs(y-c); i++){
                    if(state.pieceHere(r, y+yDir*i))
                        return true;
                }
            }
        }
        return false;
    }

    public void movePiece(int r, int c){
        super.movePiece(r, c);
        moved = true;
    }
    public GamePiece clone(){
        GamePiece clone = null;
        try {
            clone = new Rook(x, y, player);
        } catch (Exception ex) {System.out.println("Clone Error");}
        return clone;
    }
}
