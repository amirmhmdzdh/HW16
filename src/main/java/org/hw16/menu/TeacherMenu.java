package org.hw16.menu;

import org.hw16.model.*;
import org.hw16.model.enums.CourseState;
import org.hw16.model.enums.TeacherLevel;
import org.hw16.service.StudentTakenCourseService;
import org.hw16.service.TeacherService;
import org.hw16.utility.ApplicationContext;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

import static org.hw16.menu.EmployeeMenu.releasedCourseService;
import static org.hw16.menu.EmployeeMenu.studentService;

public class TeacherMenu {
    static Scanner scanner = new Scanner(System.in);
    static TeacherService teacherService = ApplicationContext.getTeacherService();
    static StudentTakenCourseService studentTakenCourseService = ApplicationContext.getStudentTakenCourseService();

    public static void menu() {

        boolean loop = true;

        while (loop) {

            System.out.println("***** Teacher MENU *****");
            System.out.println("Enter your choice: ");
            System.out.println("1. SIGNIN TEACHER AND SHOW PROFILE : ");
            System.out.println("2. RECORD STUDENTS GRADE: ");

            String input = scanner.nextLine();
            switch (input) {
                case "1" -> teacherSignIn();
                case "2" -> recordStudentsGrades();
                case "3" -> {
                    return;
                }
                case "4" -> loop = false;
                default -> System.out.println("This choice does not exist.");
            }
        }
    }

    private static void teacherSignIn() {
        System.out.println("Welcome to the User Login Menu");
        System.out.println("--------------------------------");

        System.out.print("Enter your nationalCode: ");
        String nationalCode = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        Teacher teacher = teacherService.signIn(nationalCode, password);

        if (teacher != null) {
            System.out.println("Login successful!");
            System.out.println("Your profile is:");
            System.out.printf("FirstName : %s, LastName : %s, NationalCode: %s , Teacher Level: %s", teacher.getFirstName(), teacher.getLastName(), teacher.getNationalCode(), teacher.getTeacherLevel());

            if (teacher.getTeacherLevel() == TeacherLevel.TENURED) {
                long totalSalary = teacher.getBaseSalary() + teacher.getTotalCredit() * 1_000_000L;
                System.out.println(" Pay slip " + totalSalary);
            } else {
                System.out.println("You do not receive a salary.");
            }
        } else {
            System.out.println("Login failed. Invalid username or password.");
        }
    }

    private static void recordStudentsGrades() {
        try {
            System.out.println("Please Enter Student Grade:\n");
            System.out.println("Enter Mark:");
            double mark = scanner.nextDouble();


            System.out.println("Enter Course ID:");
            long courseId = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter Student ID:");
            long studentId = scanner.nextLong();
            scanner.nextLine();
            Optional<Student> optionalStudent = studentService.findById(studentId);
            if (optionalStudent.isPresent()) {
                Student student = optionalStudent.get();
                Optional<ReleasedCourse> optionalCourse = releasedCourseService.findById(courseId);
                if (optionalCourse.isPresent()) {
                    ReleasedCourse releasedCourse = optionalCourse.get();
                    StudentTakenCourse studentTakenCourse = new StudentTakenCourse(releasedCourse, mark, student);
                    if (mark >= 10) {
                        studentTakenCourse.setCourseState(CourseState.PASSED);
                    } else
                        studentTakenCourse.setCourseState(CourseState.FAILED);
                    studentTakenCourseService.saveOrUpdate(studentTakenCourse);
                    System.out.println("Grade Saved...");
                    student.setGpa(student);
                } else {
                    System.out.println("ReleasedCourse not found.");
                }
            } else {
                System.out.println("Student not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter numeric values for the mark, course ID, and student ID.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}