package lk.ijse.dep.service;

import lk.ijse.dep.controller.BoardController;

public class HumanPlayer extends Player{
    public HumanPlayer(Board board) {
        super(board);
    }

    @Override
    public void movePiece(int col){
        boolean move = super.getBoard().isLegalMove(col);

        if (move){
            super.getBoard().updateMove(col,Piece.BLUE);
            super.getBoard().getBoardUI().update(col,true);
            Winner winner = super.getBoard().findWinner();
            if (winner.getWinningPiece() != Piece.EMPTY){
                super.getBoard().getBoardUI().notifyWinner(winner);
            } else {
                boolean legalMove = super.getBoard().existLegalMoves();
                if (!(legalMove)){
                   super.getBoard().getBoardUI().notifyWinner(winner);
                }
            }
        }
    }
}
