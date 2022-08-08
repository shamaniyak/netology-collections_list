package src;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    private static final String[] operations = {"Выход", "Добавить", "Показать", "Удалить"};

    private static ArrayList<String> products = new ArrayList<>();

    public static void main(String[] args) {
        while(true) {
            printOperations();
            int op = selectOperation();
            runOperation(op);
            if(op == 0)
                break;
        }
    }

    private static void printOperations() {
        System.out.println("Введите номер операции:");
        for(int i = 0; i < operations.length; ++i)
            System.out.print((i) + ". " + operations[i] + " ");
        System.out.println("");
    }

    private static int selectOperation() {
        int op = -1;
        if(scanner.hasNextInt()) {
            op = scanner.nextInt();
        }
        else {
            System.out.println("Нужно ввести число.");
        }
        scanner.nextLine();
        return op;
    }

    private static void runOperation(int op) {
        switch(op) {
            case 0: {
                exitOperation();
                break;
            }
            case 1: {
                addOperation();
                break;
            }
            case 2: {
                showOperation();
                break;
            }
            case 3: {
                removeOperation();
                break;
            }
            default: System.out.println("Операция " + op + " не найдена.");
        }
    }

    private static void exitOperation() {
        System.out.println("Выход из приложения.");
    }

    private static void addOperation() {
        System.out.println("Какую покупку хотите добавить?");
        String s = scanner.nextLine();
        products.add(s);
        System.out.println("Итого в списке покупок: " + products.size());
    }

    private static void showOperation() {
        System.out.println("Список покупок:");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i+1) + ". " + products.get(i));
        }
    }

    private static void removeOperation() {
        showOperation();
        System.out.println("Какую покупку хотите удалить? Введите номер или название.");
        String s = scanner.nextLine();
        // Если ввели число пробуем удалить по номеру продукта.
        try {
            int i = Integer.parseInt(s) -1;
            s = products.remove(i);
            System.out.print("Покупка " + s + " удалена. ");
            showOperation();
            return;
        }
        catch (NumberFormatException e) {
            //System.out.println(e.toString());
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Введенный индекс вне диапазона.");
            return;
        }
        // Удалить по имени продукта
        if(products.remove(s)) {
            System.out.print("Покупка " + s + " удалена. ");
            showOperation();
        }
        else {
            System.out.println("Покупка с таким названием не найден.");
        }
    }
}
