package Model;

import View.ActiveUI;
import View.MListener;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Martin
 * Date: 9/16/13
 * Time: 8:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class Manager {
    protected Player players[] = new Player[2];
    protected static Gamestate state = Gamestate.getInstance();
    protected static Manager manager = null;
    protected ActiveUI ui;
    protected int turn = 1;

    protected Manager(ActiveUI ui){
        for(int i = 0; i < 2; i++){
            players[i] = new Player(i);
        }
        state.makePieces();
        this.ui = ui;
    }

    public static Manager getInstance(ActiveUI ui){
        if(manager==null){
            if(state.vsAI)
                manager = new AIManager(ui);
            else
                manager = new Manager(ui);
        }
        return manager;
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
    }
    public static int otherPlayer(int p){
        return p*(0-1)+1;
    }
    public void gameOver(){
        ui.removeMouseListener(MListener.getListener());
        String winningColor = ((state.winner)==0)? "Black" : "White";
        JOptionPane.showMessageDialog(null, (winningColor+" Wins the Game!"), "Game Over!",JOptionPane.INFORMATION_MESSAGE);
    }
    public void updateBoard(){
        ui.repaint();
    }
}
