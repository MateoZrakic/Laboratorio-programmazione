//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        if(args.length == 1) {
            System.out.println("Non puo' essere vuoto e min 2 parole");
            return;
        }
        String word1 = args[0];
        String word2 = args[1];

        if (word1.equals(word2)) {
            System.out.println(word1 + " and " + word2 + " are equals");
        }else{
            System.out.println(word1 + " and " + word2 + " are not equals");
        }
    }
}