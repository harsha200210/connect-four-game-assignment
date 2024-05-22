package lk.ijse.dep.service;

public interface Board {
    int NUM_OF_ROWS = 5;
    int NUM_OF_COLS = 6;

    Piece[][] getPieces();
    BoardUI getBoardUI();
    int findNextAvailiableSpot(int col);
    boolean isLegalMove(int col);
    boolean existLegalMoves();
    void updateMove(int col,Piece move);
    void updateMove(int col,int row,Piece move);
    Winner findWinner();
}
