import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numero = new Random().nextInt(100);
        int numeroIndovina = 0;
        int contaTentativi = 0;
        boolean continua = true;
        ArrayList<Integer> listaDiInteri = new ArrayList<>();
        while(continua) {


            while (numero != numeroIndovina) {
                while (true) {
                    try {
                        System.out.print("Indovina il numero casuale: ");
                        numeroIndovina = sc.nextInt();
                        contaTentativi++;
                        if (numero > numeroIndovina) {
                            System.out.println("Troppo basso");
                        } else if (numero < numeroIndovina) {
                            System.out.println("Troppo alto");
                        }

                        break;
                    } catch (InputMismatchException ime) {
                        sc.nextLine();
                        System.out.println("Immettere un numero intero");
                    }

                }

            }
            System.out.println("Hai indovinato il numero in " + contaTentativi + " tentativi");
            numero = new Random().nextInt(100);
            listaDiInteri.add(contaTentativi);
            contaTentativi = 0;
            double somma = 0;
            for (int n : listaDiInteri) {
                somma += n;
            }

            // 3. Calcolare la media
            double media = 0;
            if (!listaDiInteri.isEmpty()) { // Controlla che la lista non sia vuota per evitare divisione per zero
                media = somma / listaDiInteri.size();
            }
            int media1 = (int) media;

            // 4. Stampare il risultato
            System.out.println("La media è: " + media1 + " tentativi");
            System.out.println("Continuare? ");
            continua = sc.nextBoolean();
        }




    }
}