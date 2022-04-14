package gameapplication.statecontrol;


import gameapplication.model.Board;
import gameapplication.model.IllegalMoveException;
import gameapplication.model.Move;
import gameapplication.model.Side;
import gameapplication.statecontrol.ai.AIChessEngine;
import gameapplication.statecontrol.ai.RandomChessEngine;

/**
 * Controls a game between one human and one computer
 *
 * @author Joe
 */
public class AIChessController extends ChessController {

    private Side mySide;
    private AIChessEngine aiChessEngine;

    public AIChessController() {
        this(Side.WHITE);
    }

    public AIChessController(Side s) {
        this(s, new RandomChessEngine());
    }

    public AIChessController(Side s, AIChessEngine ai) {
        mySide = s;
        aiChessEngine = ai;
    }

    @Override
    public void beginTurn() {
        super.beginTurn();
        if (getCurrentSide() != mySide && !getCurrentState().isGameOver()) {
            Move selected = aiChessEngine.chooseNextMove(getCurrentMoves(),
                    (Board) getBoard());
            try {
                super.makeMove(selected);
            } catch (IllegalMoveException e) {
                beginTurn();
            }
            super.endTurn();
            super.beginTurn();
        }
    }

    @Override
    public GameController getNewInstance() {
        AIChessController res = new AIChessController();
        res.setCurrentState(ChessState.ONGOING);
        return res;
    }
}
