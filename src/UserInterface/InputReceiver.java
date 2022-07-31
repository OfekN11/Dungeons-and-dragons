package UserInterface;

import Backend.Player;

import java.util.List;
import java.util.Scanner;

public class InputReceiver {
    private Scanner sc =new Scanner(System.in);
    String[] actionOptions;
    String[] characterOptions;

    public InputReceiver(){
        actionOptions = new String[]{"w", "s", "a", "d", "e", "q"};
        characterOptions = new String[]{"1", "2", "3","4","5","6","7"};
    }

    public int chooseCharacter(List<Player> players){
        System.out.println("Please choose the number of the character you would like to play with");
        for (int i =0; i<players.size();i++) {
            System.out.println(String.format("%d. %s",i+1,players.get(i).describe()));
        }
        String input = sc.next();

        while (!isInputValid(input,characterOptions)){
            System.out.println("Please choose a valid number");
            input = sc.next();
        }
        return Integer.parseInt(input)-1;
    }
    public char chooseAction(){
        System.out.println("Choose your action\nw->up\nd->right\ns->down\na->left\ne->cast ability\nq->nothing");
        String input = sc.next();

        while (!isInputValid(input,actionOptions)){
            input = sc.next();
        }
        return input.charAt(0);
    }

    public String receivePath(){
        return sc.next();
    }


    public boolean isInputValid(String input, String[] inputOptions){

        boolean isValid = false;
        for (String str : inputOptions){
            if (input.equals(str)){
                isValid = true;
                break;
            }
        }
        return isValid;
    }
}
