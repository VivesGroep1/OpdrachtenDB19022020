package main.java.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IoClass {

    public static String askForString(String prompt) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(prompt);
            String answ = br.readLine();
            return answ;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Vraag een Integer aan het toetsenbord
     * Blijft dit vragen zolang geen geldig getal of een lege string ingegeven wordt.
     * @param prompt
     * @return een integer getal
     *         Null als de gebruiker ENTER geeft.
     */
    public static Integer askForInt(String prompt) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.print(prompt);
                String answ = br.readLine();
                if (answ.isEmpty())
                    return null;
                try {
                    Integer intAnsw = Integer.parseInt(answ);
                    return intAnsw;
                } catch (NumberFormatException ex) {
                    System.out.println(answ + " is geen geldig geheel getal");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }

}
