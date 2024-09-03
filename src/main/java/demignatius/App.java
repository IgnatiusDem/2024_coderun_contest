package demignatius;

import demignatius.coderun.ballsandbaskets.BallsAndBaskets;
import demignatius.coderun.gcd.GreatestCommonDivisor;
import demignatius.coderun.largestsquare.LargestSquare;
import demignatius.coderun.luckynumber.LuckyNumber;
import demignatius.coderun.pairedcodes.PairedCodes;
import demignatius.coderun.workschedule.WorkSchedule;

import java.util.Scanner;

public class App 
{
    private static final String[] tasks = {
            "Шары и корзины",
            "Наибольший общий делитель",
            "Счастливый номер",
            "График работ",
            "Парные коды",
            "Наибольший квадрат"
    };

    public static void main( String[] args ) throws Exception
    {
        String command = "";
        while (!command.equals("exit")){
            for (String task:tasks) {
                System.out.println(task);
            }
            System.out.println();
            System.out.println("Введите название задачи из списка ниже или exit: ");

            Scanner sc = new Scanner(System.in);
            command = sc.nextLine();

            if(command.equals(tasks[0])){
                System.out.println("Введите данные: ");
                BallsAndBaskets.findMethodsOfSelection();
            } else if (command.equalsIgnoreCase(tasks[1])) {
                System.out.println("Введите данные: ");
                GreatestCommonDivisor.findGCD();
            } else if (command.equalsIgnoreCase(tasks[2])) {
                System.out.println("Введите данные: ");
                LuckyNumber.solution();
            } else if (command.equalsIgnoreCase(tasks[3])) {
                System.out.println("Введите данные: ");
                WorkSchedule.solution();
            } else if (command.equalsIgnoreCase(tasks[4])) {
                System.out.println("Введите данные: ");
                PairedCodes.solution();
            } else if (command.equalsIgnoreCase(tasks[5])) {
                System.out.println("Введите данные: ");
                LargestSquare.findLargestSquare();
            } else if (command.equalsIgnoreCase("exit")) {
                break;
            } else {
                System.out.println("Неизвестная команда");
            }
            System.out.println();
        }
    }
}
