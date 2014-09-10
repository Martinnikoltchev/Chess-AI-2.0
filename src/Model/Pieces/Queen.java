package Model.Pieces;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Martin
 */
public class Queen extends GamePiece implements Cloneable{

    public Queen(int x, int y, int player){
        super(x, y,player);
        if(player==0)
            iconName = "Images/BlackQueen.png";
        else
            iconName = "Images/WhiteQueen.png";
        icon = "Q";
        pieceVal = 9;
    }

    public boolean pMovable(int r, int c){
        if(!collides(r,c)){
            int xDiff = Math.abs(x-r);
            int yDiff = Math.abs(y-c);
            if(r!=x||c!=y){
                if(xDiff>0&&yDiff==0)
                    if(!collides(r,c))
                        return true;
                if(xDiff==0&&yDiff>0)
                    if(!collides(r,c))
                        return true;
                if(xDiff==yDiff)
                    if(!collides(r,c))
                        return true;
            }

        }
        return false;
    }

    private boolean collides(int r, int c){
        int xDiff = Math.abs(x-r);
        int yDiff = Math.abs(y-c);
        if(xDiff==yDiff)
            return dCollides(r,c);
        else
            return vCollides(r,c);

    }

    private boolean dCollides(int r, int c){

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

    private boolean vCollides(int r, int c){

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
        return false;
    }

    public GamePiece clone(){
        GamePiece clone = null;
        try {
            clone = new Queen(x, y, player);
        } catch (Exception ex) {System.out.println("Clone Error");}
        return clone;
    }

}
