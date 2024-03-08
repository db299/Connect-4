package gameplay_elements;
class Board{

    private int boardHeight;
    private int boardWidth;
    private String[][] boardArray;
    private int[] columnCounter;

    Board(int height, int width){
        boardHeight = height;
        boardWidth = width;
        boardArray = new String[width][height];
        columnCounter = new int[width];              
    }
    //Getters; no setters needed for this implementation
    
    int getBoardHeight() {
        return boardHeight;
    }
    int[] getColumnCounter() {
        return columnCounter;
    }
    int getBoardWidth() {
        return boardWidth;
    }
    String[][] getBoardArray(){
        return boardArray;
    }
    /* For the board to be displayed on each move, the indexes in the arrays representing it
     * must contain a character to display. Matching the specification, I have used "."s 
     * for this.
     */
    void initialiseBoard(int height, int width){
        for(int i=0; i<width; i++){
            columnCounter[i]=0;
            for(int j=0; j<height; j++){
                boardArray[i][j] = ".";
            }
        }
    }
    /* A move being played takes account of 2 things: 
     * 1. The column the move will be played into
     * 2. The number of moves that have been played into this column 
     * This if followed by 3,  updating the array holding information 
     * about the number of moves played into each column
     */
    void playMove(int column, String player){
        // 1 and 2
        boardArray[column-1][columnCounter[column-1]] = player;
        // 3
        columnCounter[column-1]+=1;
    }
    /* This method loops through each element in the board,
     * ensuring to display them from the top - down.
     */
    void displayBoard(){
        //Starts at the top, moving down
        for(int i=boardHeight-1;i>=0;i--){
            System.out.println();
            // Loops through the elements in each "line" of the 2D boardArray
            for(int j=0;j<boardWidth;j++){
                System.out.print(boardArray[j][i] + " ");
            }
        }
    }




}