import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.*;
public class QWERTYKing
{
    public static final int     GAME_SIZE    =  10;
    public static final String  DEFAULT_FILE = "3000-Words.txt";
    public static final boolean DEFAULT_CAPS =  false;

    public static void main(String[] args)
    {
        try
        {
            java.util.List<String> argsList = java.util.Arrays.asList(args);
            String  customFile = (argsList.size() > 1 && argsList.contains("-f")) ? (argsList.get(argsList.indexOf("-f") + 1)) : DEFAULT_FILE;
            int     gameSize   = (argsList.size() > 1 && argsList.contains("-s")) ? (Integer.parseInt(argsList.get(argsList.indexOf("-s") + 1))) : GAME_SIZE;
            boolean randomCaps = (argsList.size() > 0 && argsList.contains("-C")) ? !DEFAULT_CAPS : DEFAULT_CAPS;
            System.out.printf("Type quit to end the game at any time.\nDuplicate the following words.\n\n");

            boolean    continueGame = true;
            FileReader wordsFile    = new FileReader(customFile);
            Words      gameWords    = new Words(wordsFile, gameSize, randomCaps);

            // Game loop logic
            while (continueGame)
            {
                gameWords.fillAndPrintRandomWords();
                gameWords.getUserEntry();

                if (gameWords.validInput())
                    gameWords.printAnyErrors();
                else if (gameWords.quitGame())
                    continueGame = false;
                else
                    System.err.printf("You did not type the same number of words as provided. "
                                   + "There should be %d words.\n\n", gameSize);

                gameWords.clear();
            } // End game loop            
        }
        catch (FileNotFoundException ex)
        {
            System.err.println("Error locating file. Terminating.");
            System.exit(1);
        }
        catch (NumberFormatException ex)
        {
            System.err.println("Error parsing argument(s). Terminating");
            System.exit(1);
        }
    } // End main()
} // End class QWERTYKing