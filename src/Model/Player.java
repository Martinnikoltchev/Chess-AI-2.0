package Model;

import Model.Pieces.GamePiece;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Martin
 * Date: 9/16/13
 * Time: 8:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class Player {
    int player;        // 0 = black player, 1 = while
    Gamestate state = Gamestate.getInstance();

    public Player(int player){
        this.player = player;

    }
    public boolean clicked(int x, int y){
        GamePiece targetPiece = state.findPiece(x,y);
        if(state.selectedPiece!=null){
            if(targetPiece!=null){
                if(targetPiece.player==this.player){
                    state.selectedPiece.movableLocs=new Rectangle[8][8];
                    state.selectedPiece = targetPiece;
                    state.selectedPiece.makeMovables();
                }
                else{
                    if(state.selectedPiece.movable(x,y)){
                        state.selectedPiece.movePiece(x,y);
                        state.selectedPiece.removeOtherMovables();
                        return true;
                    }
                }
            }
            else{
                if(state.selectedPiece.movable(x,y)){
                    state.selectedPiece.movePiece(x,y);
                    state.selectedPiece.removeOtherMovables();
                    return true;
                }
            }
        }
        else{
            if(targetPiece!=null){
                if(targetPiece.player==this.player){
                    state.selectedPiece=targetPiece;
                    state.selectedPiece.makeMovables();
                }
            }
        }
        return false;
    }
}
