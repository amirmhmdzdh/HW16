package org.hw16.menu;

import org.hw16.model.ReleasedCourse;
import org.hw16.model.Student;
import org.hw16.model.StudentTakenCourse;
import org.hw16.model.enums.StudentState;
import org.hw16.service.ReleasedCourseService;
import org.hw16.service.StudentService;
import org.hw16.service.StudentTakenCourseService;
import org.hw16.utility.ApplicationContext;

import java.util.*;

public class StudentMenu {
    static Scanner scanner = new Scanner(System.in);
    static StudentService studentService = ApplicationContext.getStudentService();
    static ReleasedCourseService releasedCourseService = ApplicationContext.getReleasedCourseService();
    static StudentTakenCourseService studentTakenCourseService = ApplicationContext.getStudentTakenCourseService();


    public static void menu() {
        boolean loop = true;

        while (loop) {

            System.out.println("***** Student Menu ***** ");
            System.out.println("1. SignIn AND show Profile ");
            System.out.println("2. Show Cource AND SELECT unite ");
            System.out.println("3. Get a report ");
            System.out.println("4. EXIT ");
            System.out.println();
            System.out.println("Enter your choice: ");

            String input = scanner.nextLine();
            switch (input) {
                case "1" -> studentSignIn();
                case "2" -> showTakenCourse();
                case "4" -> loop = false;
                default -> System.out.println("This choice does not exist.");
            }
        }
    }

    private static void studentSignIn() {
        System.out.println("Welcome to the User Login Menu");
        System.out.println("--------------------------------");

        System.out.print("Enter your nationalCode: ");
        String nationalCode = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        Student student = studentService.signIn(nationalCode, password);

        if (student != null) {
            System.out.println("Login successful!");
            System.out.println("Your profile is: \n" + student.toString());
        } else {
            System.out.println("Login failed. Invalid username or password.");
        }

        List<ReleasedCourse> releasedCourseList = releasedCourseService.showAll();
        List<StudentTakenCourse> studentTakenCourses = new ArrayList<>();
        Set<ReleasedCourse> selectedCourses = new HashSet<>();

        if (student.getGpa() >= 18) {
            int totalCredit = 0;
            int maxCourses = 24;
            int selectedCoursesCount = 0;

            while (selectedCoursesCount < maxCourses) {
                for (int i = 0; i < releasedCourseList.size(); i++) {
                    System.out.println(i + ": " + releasedCourseList.get(i));
                }
                System.out.print("Choose an item by entering its index: ");
                int selectedIndex = -1;
                try {
                    student.setStudentState(StudentState.ENROLLED);
                    selectedIndex = scanner.nextInt();
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid index.");
                    scanner.nextLine();
                    continue;
                }

                if (selectedIndex >= 0 && selectedIndex < releasedCourseList.size()) {
                    ReleasedCourse chosenItem = releasedCourseList.get(selectedIndex);

                    if (selectedCourses.contains(chosenItem)) {
                        System.out.println("This course has already been selected. Please choose another course.");
                        continue;
                    }
                    totalCredit += chosenItem.getCourse().getCredit();
                    studentTakenCourses.add(studentTakenCourseService.addCourseByGpa(student, chosenItem));
                    System.out.println("Chosen item: " + chosenItem);
                    selectedCoursesCount += chosenItem.getCourse().getCredit();
                    selectedCourses.add(chosenItem);
                } else {
                    System.out.println("Invalid index. Please choose a valid index.");
                }
            }
        } else if (student.getGpa() < 18) {
            int totalCredit = 0;
            int maxCourses = 20;
            int selectedCoursesCount = 0;

            while (selectedCoursesCount < maxCourses) {
                for (int i = 0; i < releasedCourseList.size(); i++) {
                    System.out.println(i + ": " + releasedCourseList.get(i));
                }
                System.out.print("Choose an item by entering its index: ");
                int selectedIndex = -1;
                try {
                    student.setStudentState(StudentState.ENROLLED);
                    selectedIndex = scanner.nextInt();
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid index.");
                    scanner.nextLine();
                    continue;
                }

                if (selectedIndex >= 0 && selectedIndex < releasedCourseList.size()) {
                    ReleasedCourse chosenItem = releasedCourseList.get(selectedIndex);

                    if (selectedCourses.contains(chosenItem)) {
                        System.out.println("This course has already been selected. Please choose another course.");
                        continue;
                    }
                    totalCredit += chosenItem.getCourse().getCredit();
                    studentTakenCourses.add(studentTakenCourseService.addCourseByGpa(student, chosenItem));
                    System.out.println("Chosen item: " + chosenItem);
                    selectedCoursesCount += chosenItem.getCourse().getCredit();
                    selectedCourses.add(chosenItem);
                } else {
                    System.out.println("Invalid index. Please choose a valid index.");
                }
            }
        }
    }

    private static void showTakenCourse() {

        studentTakenCourseService.showAll();

    }

}
