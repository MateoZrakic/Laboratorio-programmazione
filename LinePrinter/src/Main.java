//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int length = 0;
        int direction = 0;
        if (args.length <2) {
            System.out.println("You must enter two arguments!");
            return;
        }
        try {
            length = Integer.parseInt(args[0]);
            direction = Integer.parseInt(args[1]);
        }catch (NumberFormatException e){
            System.out.println("You must enter a number!");
        }


        for (int i = 1; i <= length; i++) {
            if (direction == 1) {
                System.out.print("*\t");
            } else if(direction == 0) {
                System.out.println("*");
            }else{
                System.out.print("The second must be between 1 and 0 !");
                return;
            }

        }
    }
}