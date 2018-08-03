package victori.centuryai;

import java.util.ArrayList;
import java.util.List;

/**
 * Sorts incoming data and communicate with the relevant module
 */
public class Hub {
    //COMMANDS
    private String start = "start";
    private String help = "h";
    private String rest = "rest";
    private String acquire = "acquire";
    private String play = "play";
    private String claim = "claim";

    //ANSWERS
    private String inputNumberPlayers = "Please input how many human and AI players are going to be playing, in the format \"3,1\"";
    private String errorPlayerCount = "The maximum total number of players is five";
    private String inputStarterPointCards = "Please input the five point cards from left to right, one per line, in the format \"p21p4110\"";
    private String errorFormat = "Please use the right format";
    private String waitingCards = "Received. Waiting for more cards";
    private String inputStarterMerchantCards = "Please input the six merchant cards from left to right, one per line, in the formats \"s0012\", \"u2\" or \"t1000=0022\"";
    private String startDone = "All done, thank you. To continue, input the move (type \"h\" for a list of commands) of Player ";
    private String helpText = "Possible player moves:\n" +
            "\"rest\"" +
            "\"acquire\", followed by the index of the merchant card acquired, and the new card that enters at the end of the row, in the formats \"s0012\", \"u2\" or \"t1000=0022\"\n" +
            "\"play\", followed by the index of the merchant card played, and the new card that enters at the end of the row, in the formats \"s0012\", \"u2\" or \"t1000=0022\"\n" +
            "\"claim\", followed by the index of the point card claimed.\n";
    private String errorCommand = "Command not recognised. Try again.";

    //STARTER STUFF
    List<PointCard> startPointCards = new ArrayList<>();
    List<MerchantCard> startMerchantCards = new ArrayList<>();
    int numberPlayers;
    int numberAI;

    GameState gameState;

    private enum State {
        WAITING_TO_START(false),
        STARTING_PLAYERS(false),
        STARTING_POINT(false),
        STARTING_MERCHANT(false),
        STARTED(true);

        private boolean started;
        State(boolean started) {
            this.started = started;
        }

        boolean hasStarted() {
            return started;
        }
    }

    private State currentState = State.WAITING_TO_START;

    public String interpretLine(String line) {
        line = line.toLowerCase().trim();
        if (currentState.hasStarted()) {
            if (line.equals(help)) {
                return helpText;
            }
            else{
                String[] parsed = line.split(" ");
                String command = parsed[0];
                if (command.equals(rest)) {
                    //todo implement
                    return null;
                } else if (command.equals(acquire)) {
                    //todo implement
                    return null;
                }else if (command.equals(play)) {
                    //todo implement
                    return null;
                }else if (command.equals(claim)) {
                    //todo implement
                    return null;
                }else{
                    return errorCommand;
                }
            }

        }else {
            if (currentState == State.WAITING_TO_START) {
                if (line.equals(start)) {
                    currentState = State.STARTING_PLAYERS;
                    return inputNumberPlayers;
                } else {
                    return errorCommand;
                }
            } else if (currentState == State.STARTING_PLAYERS) {
                String[] numbers = line.split(",");
                try {
                    numberPlayers = Integer.parseInt(numbers[0]);
                    numberAI = Integer.parseInt(numbers[1]);
                } catch (NumberFormatException nfe) {
                    return errorFormat;
                }
                int total = numberAI + numberPlayers;
                if (total > 5) {
                    return errorPlayerCount;
                }
                else {
                    currentState = State.STARTING_POINT;
                    return inputStarterPointCards;
                }
            } else if (currentState == State.STARTING_POINT) {
                try {
                    startPointCards.add(PointCard.factory(line));
                } catch (NumberFormatException nfe) {
                    return errorFormat;
                }

                if (startPointCards.size() == 5) {
                    currentState = State.STARTING_MERCHANT;
                    return inputStarterMerchantCards;
                } else {
                    return waitingCards;
                }
            } else if (currentState == State.STARTING_MERCHANT){
                try{
                    startMerchantCards.add(MerchantCardFactory.importCard(line));
                }catch (NumberFormatException nfe) {
                    return errorFormat;
                }
                if (startMerchantCards.size() == 6) {
                    currentState = State.STARTED;
                    gameState = new GameState(startPointCards, startMerchantCards, numberPlayers, numberAI);

                    //cleanup
                    startPointCards = null;
                    startMerchantCards = null;

                    return startDone + gameState.getCurrentTurn();
                } else {
                    return waitingCards;
                }

            } else{
                throw new IllegalStateException("State not recognised");
            }
        }
    }
}
