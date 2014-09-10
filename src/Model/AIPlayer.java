package Model;

import Model.Pieces.GamePiece;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Martin
 * Date: 9/30/13
 * Time: 11:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class AIPlayer extends Player {


    int dFactor = 3; //defend friendly
    int aFactor = 2; //threaten opponent
    int oValue = 1; //open square value
    int selfDFactor = 3; //weighting of how fortified current piece is
    int lFactor = 0; //weighting of loosing a piece
    int[][][] hits = new int[2][8][8];
    int[][][] wHits = new int[2][8][8];
    Map<GamePiece, Integer> pieceScores = new HashMap<GamePiece, Integer>();


    Random rand = new Random();
    public AIPlayer(int player) {
        super(player);
    }

    public boolean clicked(int x, int y){
        //Try all moves returning highest valued move
        Gamestate.backUp();
        int highValue = Integer.MIN_VALUE;
        int highR = 0;
        int highC = 0;
        int highPiece = 0;
        for(int i = 0; i < state.pieces[player].size(); i++){
            GamePiece piece = state.pieces[player].get(i);
            for(int r = 0; r < 8; r++){
                for(int c = 0; c < 8; c++){
                    if(piece.movable(r, c)&&state.findPlayerPiece(r,c,player)==null){
                        int value = quantifyState(i,r,c);
                        if(value>highValue){
                            highValue = value;
                            highR = r;
                            highC = c;
                            highPiece = i;
                        }
                    }
                }
            }
        }
        state.pieces[player].get(highPiece).movePiece(highR, highC);

        return true;
    }

    private int quantifyState(int p, int r, int c) {
        Gamestate tempState = state.clone();
        GamePiece piece = tempState.pieces[1].get(p);
        piece.printMove(r, c);
        piece.movePiece(r, c);
        makeHits(tempState);
        makeWeightedHits(tempState);
        System.out.println("My : "+ evaluate(tempState,1)+ " Human: "+ evaluate(tempState,0)+ " Total: "+ String.valueOf(evaluate(tempState,1)-evaluate(tempState,0)));
        return evaluate(tempState,1)-evaluate(tempState,0);
    }

    private void makeHits(Gamestate tempState) {
        hits = new int[2][8][8];
        for(int pl = 0; pl <2; pl++){
            for(GamePiece p : tempState.pieces[pl] )
            for(int c = 0; c < 8; c++){
                for(int r = 0; r < 8; r++){
                    if(p.pMovable(r,c))
                        hits[pl][r][c]+=3;
                }
            }
        }
    }
    private void makeWeightedHits(Gamestate tempState) {
        wHits = new int[2][8][8];
        for(int pl = 0; pl <2; pl++){
            for(GamePiece p : tempState.pieces[pl] )
                for(int c = 0; c < 8; c++){
                    for(int r = 0; r < 8; r++){
                        if(p.pMovable(r,c))
                            wHits[pl][r][c]+=p.pieceVal;
                    }
                }
        }
    }

    private int evaluate(Gamestate tempState, int pl) {
        setMap(tempState, pl);
        int sum = 0;
        int piecesLeft = 0;
        int pieceMax = 54;
        for(int i = 0; i < tempState.pieces[pl].size();i++){
            sum+=evaluatePiece(tempState,i, pl);
            piecesLeft+=tempState.pieces[pl].get(i).pieceVal;
        }
        return sum-lFactor*(pieceMax-piecesLeft);
    }

    private void setMap(Gamestate tempState, int pl) {
        pieceScores.clear();
        for(GamePiece p: tempState.pieces[pl]){
            pieceScores.put(p, 0);
        }
    }

    private int evaluatePiece(Gamestate tempState, int i, int pl) {
        GamePiece p = tempState.pieces[pl].get(i);
        for(int c = 0; c < 8; c++){
            for(int r = 0; r < 8; r++){
                if(p.pMovable(r,c)){
                    pieceScores.put(p, pieceScores.get(p)+evaluateSquare(p, tempState, r, c, pl));
                }
            }
        }
        int selfDefence = (wHits[pl][p.x][p.y]-hits[Manager.otherPlayer(pl)][p.x][p.y]);
        pieceScores.put(p, selfDefence*selfDFactor*p.pieceVal);
        return pieceScores.get(p);
    }

    private int evaluateSquare(GamePiece p, Gamestate tempState, int r, int c, int pl) {
        GamePiece target = tempState.findPiece(r,c);
        if(target==null)
            return oValue;
        if(target.player!=pl){
            return aFactor*target.pieceVal*(hits[pl][target.x][target.y]-
                    hits[Manager.otherPlayer(pl)][target.x][target.y]);
        }
        else{
            return dFactor*target.pieceVal;
        }

    }


}
