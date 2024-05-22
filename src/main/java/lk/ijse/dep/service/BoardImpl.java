package lk.ijse.dep.service;

import java.util.Arrays;

import static lk.ijse.dep.service.Piece.EMPTY;

public class BoardImpl implements Board{
    private Piece[][] pieces;
    private BoardUI boardUI;

    public BoardImpl(BoardUI boardUI) {
        this.boardUI = boardUI;
        pieces = new Piece[NUM_OF_COLS][NUM_OF_ROWS];
        for (int i = 0;i < pieces.length;i++){
            Arrays.fill(pieces[i], Piece.EMPTY);
        }
    }

    public Piece[][] getPieces() {
        return pieces;
    }

    @Override
    public BoardUI getBoardUI() {
        return boardUI;
    }

    @Override
    public int findNextAvailiableSpot(int col) {
        for (int i = 0;i < pieces[col].length;i++){
            if(pieces[col][i] == Piece.EMPTY){
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isLegalMove(int col) {
        int empty = findNextAvailiableSpot(col);
        return (empty >= 0);
    }

    @Override
    public boolean existLegalMoves() {
        for (int i = 0;i < pieces.length;i++){
            for (int j = 0;j < pieces[i].length;j++){
                if (pieces[i][j] == Piece.EMPTY){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void updateMove(int col, Piece move) {
        for (int i = 0;i < pieces[col].length;i++){
            if(pieces[col][i] == Piece.EMPTY){
                pieces[col][i] = move;
                break;
            }
        }
    }

    @Override
    public void updateMove(int col, int row, Piece move) {
        pieces[col][row] = move;
    }

    @Override
    public Winner findWinner() {
        //colum eke check krnw
        for (int i = 0;i < pieces.length;i++) {
            if (pieces[i][3] != EMPTY) {
                for (int j = 0; j < 2; j++) {
                    if (pieces[i][j] == pieces[i][j + 1] && pieces[i][j + 1] == pieces[i][j + 2] && pieces[i][j + 2] == pieces[i][j + 3]) {
                        return new Winner(pieces[i][j], i, j, i, j+3);
                    }
                }
            }
        }

        //row eka check krnw
        for (int i = 0;i < pieces[1].length;i++){
            if (pieces[3][i] != EMPTY) {
                for (int j = 0; j < 3; j++) {
                    if (pieces[j][i] == pieces[j + 1][i] && pieces[j + 1][i] == pieces[j + 2][i] && pieces[j + 2][i] == pieces[j + 3][i]) {
                        return new Winner(pieces[j][i], j, i, j+3, i);
                    }
                }
            }
        }
        return new Winner(Piece.EMPTY);
    }


}


