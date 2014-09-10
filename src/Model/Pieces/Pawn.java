package Model.Pieces;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Martin
 */
public class Pawn extends GamePiece implements Cloneable{


    public Pawn(int x, int y, int player){
        super(x, y, player);
        if(player==0)
            iconName = "Images/BlackPawn.png";
        else
            iconName = "Images/WhitePawn.png";
        icon = "P";
        pieceVal = 1;
    }

    public boolean pMovable(int r, int c) {
        int yDiff = Math.abs(y-c);
        int xDiff = Math.abs(x-r);
        int dir = y-c;
        if(r!=x||c!=y){
            if(yDiff>0)
                dir/=yDiff;
            if(yDiff==2 && xDiff==0 && y==1+player*5)
                if(dir==direction){
                    if(collides(r, c))
                        return false;
                    if(state.findPiece(r, c)!=null)
                        return false;
                    return true;
                }
            if(yDiff==1 && xDiff==0)
                if(dir==direction){
                    if(state.findPiece(r, c)!=null)
                        return false;
                    return true;
                }
            if((r==x-1||r==x+1)&&c==y-direction&&state.findPiece(r,c)!=null)
                return true;
        }
        return false;
    }

    private boolean collides(int r, int c){

        int yDir = c-y;
        try{
            yDir/=Math.abs(yDir);
        }catch(Exception e){}
        if(state.findPiece(x, y+yDir)!=null)
            return true;
        return false;
    }


    public void turnToQueen(int r, int c){
        Queen q = new Queen(r, c, player);
        q.setIcon();
        for(int i = 0; i < state.pieces[player].size(); i++){
            if(state.pieces[player].get(i)==this)
                state.pieces[player].set(i, q);
        }
    }
    public void movePiece(int r, int c){
        super.movePiece(r, c);
        if((c==7) || (c ==0)){
            turnToQueen(r, c);
        }
    }
    public GamePiece clone(){
        GamePiece clone = null;
        try {
            clone = new Pawn(x, y, player);
        } catch (Exception ex) {System.out.println("Clone Error");}
        return clone;
    }
    /*
    public boolean attackLoc(int r, int c){
        int yDiff = Math.abs(y-c);
        int dir = y-c;
        if(yDiff>0)
            dir/=yDiff;
        return (r==x-1||r==x+1)&&c==y-p.direction;
    }    */

}
