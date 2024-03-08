import gameplay_elements.*;
public class W09Practical {
    public static void main(String[] args) {
        /* Initialise a game with the desire board height and width
           as well as preffered colour of player 1
        */
        Game game = new Game(6,7,"Y");
        game.playGame();
    }
}
