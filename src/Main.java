import java.util.Scanner;

import methods.CheckINN;

public class Main {
    public static void main(String[] args) {
        String INN;
        String KPP;

        while (true)
        {
            Scanner inn = new Scanner(System.in);
            System.out.println("Введите Ваш ИНН: ");
            INN = inn.nextLine();
            System.out.println(INN.replaceAll("[\\-\\s]", "").toUpperCase());

            if (INN.equals("") || INN.equals("end") || INN == null) break;

            Scanner kpp = new Scanner(System.in);
            System.out.println("Введите Ваш КПП или оставтье строку пустой: ");
            KPP = kpp.nextLine();
            System.out.println(KPP.replaceAll("[\\-\\s]", "").toUpperCase());

            CheckINN man = new CheckINN(INN, KPP);
            System.out.println(man.getResponse());
        }
        System.out.println("Program exit...");
    }
}
