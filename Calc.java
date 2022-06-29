package calc;

/**
 *
 * @author VPechenin
 */
import java.util.*;

public class Calc {

    public static void main(String[] args) {
        System.out.print("Введите выражение: ");    // Выводим запрос данных

        Scanner scan = new Scanner(System.in);     // Создаем объект input класса Scanner
        System.out.println(calc(scan.nextLine()));
    }

    public static String calc(String input) {
        String userIn = "";
        String operator = "";
        String operand1 = "";
        String operand2 = "";
        int result = 0;
        List<String> romeList = Arrays.asList("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X");
        List<String> arabList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        String numSys1 = "a";

        userIn = input.replaceAll("\\s", "");    // Создаем переменную и считываем в нее выражение       Удаляем все пробелы
        operator = userIn.replaceAll("[^\\+\\-\\*\\/]", "");      // Создаем переменную для  оператора   и удаляем все остальное

        if (operator.length() != 1) {
            throw new NumberFormatException("//Авада Кедавра");
        }

        String[] expression = userIn.split("\\+|\\-|\\*|\\/", 2);  //Разделяем введенные данные на 2 строки по оператору

        if (!(expression.length == 2 && (romeList.contains(expression[0]) && romeList.contains(expression[1])) ^ (arabList.contains(expression[0]) && arabList.contains(expression[1])))) {
            throw new NumberFormatException("//Авада Кедавра1");
        }

        if (romeList.indexOf(expression[0]) > -1) {
            operand1 = String.valueOf(romeList.indexOf(expression[0]) + 1);
            operand2 = String.valueOf(romeList.indexOf(expression[1]) + 1);
            numSys1 = "Rome";
        } else {
            operand1 = String.valueOf(arabList.indexOf(expression[0]) + 1);
            operand2 = String.valueOf(arabList.indexOf(expression[1]) + 1);
            numSys1 = "Arrr";
        }

        if (operator.length() != 1) {     //  больше или меньше 1-го оператора
            throw new NumberFormatException("//Авада Кедавра2");
        }

        if (numSys1.equals("Rome") && operator.equals("-") && ((Integer.parseInt(operand1) - Integer.parseInt(operand2)) < 1)) {  //римскими     и   (1-е  -  2-е)    меньше чем 1
            throw new NumberFormatException("//Авада Кедавра3");
        }

        switch (operator) {
            case "+":
                result = Integer.parseInt(operand1) + Integer.parseInt(operand2);
                break;
            case "-":
                result = Integer.parseInt(operand1) - Integer.parseInt(operand2);
                break;
            case "*":
                result = Integer.parseInt(operand1) * Integer.parseInt(operand2);
                break;
            case "/":
                result = Integer.parseInt(operand1) / Integer.parseInt(operand2);
        }

        if (numSys1.equals("Rome")) {
            return rome(result);
        } else {
            return String.valueOf(result);
        }

    }

    static String rome(int dig) {
        List<String> romanList = Arrays.asList("I", "V", "X", "L", "C", "D", "M");
        String romeNum = "";
        String A = "";

        for (int i = String.valueOf(dig).length() - 1; i > -1; i--) {
            int num = dig / (int) Math.pow(10, i) - (dig / (int) Math.pow(10, i + 1)) * 10;

            if (num % 5 > 3) {
                A = romanList.get((i + 1) * 2 - 2) + romanList.get(i * 2 + 1 + num / 5);
            } else {
                A = strMulti(romanList.get(i * 2 + num / 5), num / 5) + strMulti(romanList.get((i + 1) * 2 - 2), num % 5);
            }

            romeNum = romeNum + A;
        }
        return romeNum;
    }

    static String strMulti(String str, int a) {
        String StrApp = "";
        for (int i = a; i > 0; i--) {
            StrApp = StrApp + str;
        }
        return StrApp;
    }
}