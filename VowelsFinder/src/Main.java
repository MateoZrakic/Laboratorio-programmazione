//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String parola = args[0];
        String s = "";
        for (int i = 0; i < parola.length(); i++) {
            char c = parola.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' ||
                    c == 'O' || c == 'U') {
                if(s.equals("")) {
                    s = s + "" +c;
                }else {
                    s = s + ";" + c;
                }

            }
        }
        System.out.println(parola + " contains the following vowels: " +s);
    }
}