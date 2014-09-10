package Model;

import Model.Pieces.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Martin
 * Date: 9/16/13
 * Time: 6:09 PM
 * To change this template use File | Settings | File Templates.
 */
//SINGLETON
public class Gamestate implements Cloneable{

    public ArrayList<GamePiece> pieces[] = null;
    public GamePiece selectedPiece = null;
    private static Gamestate state = null;
    private static Gamestate savedState = null;
    public Integer winner = null;
    public boolean vsAI;

    public static Gamestate getInstance(){
         if(state==null){
             state = new Gamestate();
         }
        return state;
    }
    private Gamestate(){
        pieces = new ArrayList[2];
        for(int i = 0; i < 2; i++){
            pieces[i]=new ArrayList<GamePiece>();
        }

    }

    public GamePiece findPiece(int r, int c) {
        GamePiece p = null;
        for(int j = 0; j < pieces.length; j++){
            p = findPlayerPiece(r,c,j);
            if(p!=null)
                return p;
        }
        return p;
    }
    public GamePiece findPlayerPiece(int r, int c, int player) {
        GamePiece p = null;
            for(int i = 0; i < pieces[player].size(); i++){
                if(pieces[player].get(i).x == r && pieces[player].get(i).y == c)
                    p = pieces[player].get(i);
            }
        return p;
    }

    public boolean pieceHere(int r, int c){
        if(findPiece(r,c)==null)
            if(findPiece(r, c)==null)
                return false;
        return true;
    }

    public void removePiece(GamePiece p){
        for(int j = 0; j < pieces.length;j++)
            for(int i = 0; i < pieces[j].size(); i++){
                if(p.equals(pieces[j].get(i))){
                    pieces[j].remove(i);
                    if(p.getClass().equals(King.class))
                        winner = Manager.otherPlayer(j);
                }
            }
    }

    public void clearMovables(){
        for(int i = 0; i <pieces.length;i++){
            for(int j = 0; j < pieces[i].size();j++){
                GamePiece piece = pieces[i].get(j);
                if(piece==selectedPiece){
                    piece.removeOtherMovables();
                }
                else{
                    piece.movableLocs=new Rectangle[8][8];
                }
            }
        }
    }
    public void makePieces(){
        for(int player = 0; player<2; player++){
            if(pieces[player].size()<=0){
                for(int i = 0; i < 8; i++){
                    pieces[player].add(new Pawn(i, 1 + player*5,player));
                    pieces[player].get(pieces[player].size()-1).setIcon();
                }
                for(int i = 0; i < 2; i++){
                    pieces[player].add(new Rook(i*7, player*7,player));
                    pieces[player].get(pieces[player].size()-1).setIcon();
                }
                for(int i = 0; i < 2; i++){
                    pieces[player].add(new Knight(1+ i*5, player*7,player));
                    pieces[player].get(pieces[player].size()-1).setIcon();
                }
                for(int i = 0; i < 2; i++){
                    pieces[player].add(new Bishop(2+i*3, player*7,player));
                    pieces[player].get(pieces[player].size()-1).setIcon();
                }
                pieces[player].add(new Queen(3, player*7,player));
                pieces[player].get(pieces[player].size()-1).setIcon();
                pieces[player].add(new King(4, player*7,player));
                pieces[player].get(pieces[player].size()-1).setIcon();
            }
        }
    }

    public static void backUp(){
        savedState = state.clone();
    }
    public static void refreshState(){
        state = savedState.clone();
    }

    public Gamestate clone(){
        Gamestate clone = new Gamestate();
        clone.winner = winner;
        clone.vsAI = vsAI;
        ArrayList[] newPieces = new ArrayList[2];
        for(int i = 0; i < newPieces.length; i++){
            newPieces[i] = new ArrayList<GamePiece>();
            for(int j = 0; j < pieces[i].size(); j++){
                newPieces[i].add(pieces[i].get(j).clone());
            }
        }
        clone.pieces = newPieces;


        return clone;
    }
}
