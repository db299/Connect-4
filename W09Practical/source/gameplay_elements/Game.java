package gameplay_elements;
import java.util.Scanner;
public class Game{
    /* New board object with specified height and width
     * Yellow gets the first move
     */
    private Board currentBoard;
    private String player;
    private int currentMove;
    private Scanner reader = new Scanner(System.in);

    public Game(int height, int width, String firstPlayer){
        currentBoard = new Board(height,width);
        player = firstPlayer;

    }
    /* Adds all patterns on columns, rows, and diagonals containing the
     * current player colour, and searches them for a pattern containing
     * 4 times the current player string
     */
    void checkWin(){
        String letters = "";
        /* Vertocal check stores the entire column where the last move 
         * was placed building a string that holds this information
         */
        for(int i=0;i<=currentBoard.getBoardHeight()-1;i++){
            letters += currentBoard.getBoardArray()[currentMove-1][i];
            }
        // Adds a space to separate horizontal, vertical and diagonal information
        letters += " ";

        // Horizontal check, operates similarily to the vertical check
        for(int i=0;i<=currentBoard.getBoardWidth()-1;i++){
            letters += currentBoard.getBoardArray()[i][currentBoard.getColumnCounter()[currentMove-1]-1];
        }
        letters += " ";
        /* For the diagonal check, the start and end of the diagonal are not instantly obvious.
         * The program needs to calculate where to start counting the diagonal from. This
         * is done by recognising that each diagonal has a corresponding square (see report
         * for more details), and moving to the corner of this square, counting to the end.
         */
        if (currentMove >= currentBoard.getColumnCounter()[currentMove-1]){
            for(int i=currentMove-currentBoard.getColumnCounter()[currentMove-1],j=0; i<=currentBoard.getBoardWidth()-1 && j<=currentBoard.getBoardHeight()-1;j++,i++){
                letters += currentBoard.getBoardArray()[i][j];
            }
        }
        else{
            for(int i=0,j=currentBoard.getColumnCounter()[currentMove-1]-currentMove;i<=currentBoard.getBoardWidth()-1 && j<=currentBoard.getBoardHeight()-1;j++, i++){
                letters += currentBoard.getBoardArray()[i][j];
            }
        }
        letters += " ";
        // Second diagonal, similar process as before
        if ((currentBoard.getBoardWidth()-currentMove)>=(currentBoard.getColumnCounter()[currentMove-1])){
            for(int i=((currentBoard.getColumnCounter()[currentMove-1]-1)+(currentMove-1)),j=0;i>=0 && j<=currentBoard.getBoardHeight()-1;i--,j++){
                letters += currentBoard.getBoardArray()[i][j];
            }
        }
        else{
            for(int i=currentBoard.getBoardWidth()-1,j=currentBoard.getColumnCounter()[currentMove-1]-(currentBoard.getBoardWidth()-currentMove)-1;i>=0 && j<=currentBoard.getBoardHeight()-1;i--,j++){
                letters += currentBoard.getBoardArray()[i][j];
            }
        }

        // Uncomment the next line to see what the checker does in real time
        // System.out.println(letters);

        // Decides whether there is a winner and the game is over
        if (letters.contains(player+player+player+player)){
            if(player == "R"){
                System.out.println( "\nGame over: red wins");
                System.exit(0);
            }
            else{
                System.out.println("\nGame over: yellow wins");
                System.exit(0);
            }
        }
        else if(!letters.contains(".")){
            System.out.println("\nDraw!");
            System.exit(0);

        }
}
    // The player needs to be switched after each move.
    void switchPlayer(){
        if(player == "R"){
            player = "Y";
        }
        else{
            player = "R";
        }
    }
    // Ensures the input is valid.
    boolean validateMove(int currentMove){
        if (currentMove == 0){
            currentBoard.displayBoard();
            
            System.out.println("\n\nGame over: user quit");
            System.exit(0);
            return false;
        }
        // This test also catches the invalid input error for non integer values
        else if (currentMove > currentBoard.getBoardWidth() || currentMove < 0){
            System.out.println("Illegal column");
            return false;
        }
        else if (currentBoard.getColumnCounter()[currentMove-1] == currentBoard.getBoardHeight()){
            System.out.println("Column is full");
            return false;
        }
        else{
            return true;
        }
    }
    // Takes a move as input, returning an "error code" if the input is not an integer
    int inputMove(){
        try{
            int move = reader.nextInt();
            return move;
        }
        catch(java.util.InputMismatchException e){
            //Resetting the scanner
            reader.next();
            return -1020304;
        }
    }
    /* Method contains the game loop and initialises the board.
     */
    public void playGame(){
        currentBoard.initialiseBoard(currentBoard.getBoardHeight(), currentBoard.getBoardWidth());
        while(true){
            System.out.print("\n\nEnter column number (1-7), or 0 to quit: ");
            currentMove = inputMove();
            if(validateMove(currentMove)){
                currentBoard.playMove(currentMove,player);
                currentBoard.displayBoard();
                checkWin();
                switchPlayer();
            }
            /* Displaying the board after a wrong move is helpful for the player
             * as it makes it easier to see where they could place a move.
             */
            else{
                currentBoard.displayBoard();
            }
            }
        }   
    }

