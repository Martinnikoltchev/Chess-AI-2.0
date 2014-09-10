package Model;

import View.ActiveUI;

/**
 * Created with IntelliJ IDEA.
 * User: Martin
 * Date: 9/30/13
 * Time: 11:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class AIManager extends Manager {

    public AIManager(ActiveUI ui) {
        super(ui);
        players[0] = new AIPlayer(0);
    }

    public void clicked(int x, int y){
        if(players[turn].clicked(x,y)){
            turn = otherPlayer(turn);
            state.clearMovables();
            state.selectedPiece = null;
        }
        updateBoard();
        if(state.winner!=null){
            gameOver();
        }
        if(state.vsAI&&players[turn].getClass().equals(AIPlayer.class))    //If playing AI, start their turn with clicked method
            clicked(0,0);
    }
}
