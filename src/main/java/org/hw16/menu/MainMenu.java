package org.hw16.menu;

import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
            System.out.println("Enter your choice: ");
            System.out.println("1. Employee Menu ");
            System.out.println("2. Teacher Menu ");
            System.out.println("3. Student Menu ");


            String Input = scanner.nextLine();
            switch (Input) {
                case "1" -> EmployeeMenu.menu();
                case "2" -> TeacherMenu.menu();
                case "3" -> StudentMenu.menu();
                case "4" -> loop = false;
                default -> System.out.println("This choice does not exist.");
            }
        }
    }
}