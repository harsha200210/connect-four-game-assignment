package lk.ijse.dep.service;

import static lk.ijse.dep.service.Piece.EMPTY;

public class AiPlayer extends Player{
    protected static Piece[][] pieces;
    public AiPlayer(Board board) {
        super(board);
        pieces = board.getPieces();
    }
    private static class AiAction {
        protected int col;
        public AiAction(){
            winBlockOrWin();
            if (col == -1){
                aiTurn();
            }
        }

        private void aiTurn() {
            //green 2k thiyenwd check krnw
            for (int i = 0;i < pieces.length;i++) {
                for (int j = 0; j < 3; j++) {
                    if (pieces[i][j] == pieces[i][j + 1] && pieces[i][j + 1] == Piece.GREEN) {
                        if(pieces[i][j+2] == EMPTY){
                            col = i;
                            return;
                        }
                    }
                }
            }

            for (int i = 0; i < pieces[1].length; i++) {
                for (int j = 0; j < 5; j++) {
                    if (pieces[j][i] == pieces[j + 1][i] && pieces[j + 1][i] == Piece.GREEN) {
                        if((j+1) != 5 && pieces[j+2][i] == EMPTY){
                            col = j+2;
                            return;
                        }
                        if (j > 0 && pieces[j-1][i] == EMPTY){
                            col = j-1;
                            return;
                        }
                    }
                }
            }
        }

        private void winBlockOrWin() {
            col = -1;
            //ai playert win krnn chanse ekak thiyenwd kiyl chech krnw
            for (int i = 0;i < pieces.length;i++) {
                for (int j = 0; j < 2; j++) {
                    if (pieces[i][j] == pieces[i][j + 1] && pieces[i][j + 1] == pieces[i][j + 2] && pieces[i][j + 2] == Piece.GREEN) {
                        if(pieces[i][j+3] == EMPTY){
                            col = i;
                            return;
                        }
                    }
                }
            }

            for (int i = 0;i < pieces[1].length;i++){
                for (int j = 0; j < 4; j++) {
                    if (pieces[j][i] == pieces[j + 1][i] && pieces[j + 1][i] == pieces[j + 2][i] && pieces[j + 2][i] == Piece.GREEN) {
                        if((j+2) != 5 && pieces[j+3][i] == EMPTY){
                            col = j+3;
                            return;
                        }
                        if (j > 0 && pieces[j-1][i] == EMPTY){
                            col = j-1;
                            return;
                        }
                    }
                }
                for (int j = 0; j < 3; j++) {
                    if (pieces[j][i] == pieces[j + 2][i] && pieces[j + 2][i] == pieces[j + 3][i] && pieces[j + 3][i] == Piece.GREEN) {
                        if(pieces[j+1][i] == EMPTY){
                            col = j+1;
                            return;
                        }
                    }
                    if (pieces[j][i] == pieces[j + 1][i] && pieces[j + 1][i] == pieces[j + 3][i] && pieces[j + 3][i] == Piece.GREEN) {
                        if(pieces[j+2][i] == EMPTY){
                            col = j+2;
                            return;
                        }
                    }
                }
            }
            //human playerge win eka block krnw
            for (int i = 0;i < pieces.length;i++) {
                for (int j = 0; j < 2; j++) {
                    if (pieces[i][j] == pieces[i][j + 1] && pieces[i][j + 1] == pieces[i][j + 2] && pieces[i][j + 2] == Piece.BLUE) {
                        if(pieces[i][j+3] == EMPTY){
                            col = i;
                            return;
                        }
                    }
                }
            }

            for (int i = 0;i < pieces[1].length;i++){
                for (int j = 0; j < 4; j++) {
                    if (pieces[j][i] == pieces[j + 1][i] && pieces[j + 1][i] == pieces[j + 2][i] && pieces[j + 2][i] == Piece.BLUE) {
                        if((j+2) != 5 && pieces[j+3][i] == EMPTY){
                            col = j+3;
                            return;
                        }
                        if (j > 0 && pieces[j-1][i] == EMPTY ){
                            col = j-1;
                            return;
                        }
                    }
                }
                for (int j = 0; j < 3; j++) {
                    if (pieces[j][i] == pieces[j + 2][i] && pieces[j + 2][i] == pieces[j + 3][i] && pieces[j + 3][i] == Piece.BLUE) {
                        if(pieces[j+1][i] == EMPTY){
                            col = j+1;
                            return;
                        }
                    }
                    if (pieces[j][i] == pieces[j + 1][i] && pieces[j + 1][i] == pieces[j + 3][i] && pieces[j + 3][i] == Piece.BLUE) {
                        if(pieces[j+2][i] == EMPTY){
                            col = j+2;
                            return;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void movePiece(int col){
        boolean move;

        AiAction aiAction = new AiAction();
        if (aiAction.col != -1){
            col = aiAction.col;
            super.getBoard().updateMove(aiAction.col,Piece.GREEN);
        } else {
            do {
                col = (int)(Math.random() * 6);
                move = super.getBoard().isLegalMove(col);
            } while (!move);
            super.getBoard().updateMove(col,Piece.GREEN);
        }
        super.getBoard().getBoardUI().update(col,false);
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
