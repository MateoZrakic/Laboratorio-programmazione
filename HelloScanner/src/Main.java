import java.util.InputMismatchException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int eta = 0;
        String nome = "Nome";
        String cognome = "Cognome";
        Scanner sc = new Scanner(System.in);
        while(true) {
            try {

                System.out.print("Inserisci il tuo nome: ");
                nome = sc.nextLine();

                System.out.print("Inserisci il tuo cognome: ");
                cognome = sc.nextLine();

                System.out.print("Inserisci la tua eta: ");
                eta = sc.nextInt();

                System.out.println("Ciao " + nome + " " + cognome + " " + eta);
                break;

            } catch (InputMismatchException ime) {
                System.out.println("Inserisci l eta in formato numerico: ");
            }
        }


    }
}