package org.hw16.menu;

import org.hw16.model.*;
import org.hw16.model.enums.TeacherLevel;
import org.hw16.service.*;
import org.hw16.utility.ApplicationContext;

import java.util.*;

public class EmployeeMenu {
    static Scanner scanner = new Scanner(System.in);

    static EmployeeService employeeService = ApplicationContext.getEmployeeService();
    static TeacherService teacherService = ApplicationContext.getTeacherService();
    static StudentService studentService = ApplicationContext.getStudentService();
    static CourseService courseService = ApplicationContext.getCourseService();
    static ReleasedCourseService releasedCourseService = ApplicationContext.getReleasedCourseService();
    static SemesterService semesterService = ApplicationContext.getSemesterService();


    public static void menu() {

        boolean loop = true;

        while (loop) {

            System.out.println("***** options ***** ");
            System.out.println("1. Employee Options ");
            System.out.println("2. Teacher Options ");
            System.out.println("3. Student Options ");
            System.out.println("4. Course Options ");
            System.out.println("5. EXIT ");
            System.out.println();
            System.out.println("Enter your choice: ");
            String input = scanner.nextLine();
            switch (input) {
                case "1" -> employeeOptions();
                case "2" -> teacherOptions();
                case "3" -> studentOptions();
                case "4" -> courseOptions();
                case "5" -> loop = false;
                default -> System.out.println("This choice does not exist.");
            }
        }
    }

    private static void employeeOptions() {
        boolean loop = true;

        while (loop) {
            System.out.println("***** Employee MENU *****");
            System.out.println("Enter your choice: ");
            System.out.println("1. SIGNUP NEW Employee ");
            System.out.println("2. SIGNIN Employee AND SHOW PROFILE ");
            System.out.println("3. UPDATE PROFILE");
            System.out.println("4. DELETE ACCOUNT");
            System.out.println("5. EXIT");


            String input = scanner.nextLine();
            switch (input) {
                case "1" -> employeeSignUp();
                case "2" -> employeeSignIn();
                case "3" -> employeeUpdate();
                case "4" -> employeeDelete();
                case "5" -> {
                    return;
                }
                case "6" -> loop = false;
                default -> System.out.println("This choice does not exist.");
            }
        }
    }


    private static void employeeSignUp() {
        System.out.println("Enter Firstname: ");
        String firstName = scanner.nextLine();
        System.out.println("Enter Lastname: ");
        String lastName = scanner.nextLine();
        System.out.println("Enter NationalCode: ");
        String nationalCode = scanner.nextLine();
        System.out.println("Enter Password: ");
        String password = scanner.nextLine();
        System.out.println("Enter email: ");
        String email = scanner.nextLine();
        System.out.println("Enter the Base Salary: ");
        long baseSalary = getLongNum();
        Employee employee = new Employee(firstName, lastName, nationalCode, password, email, baseSalary);
        Employee newEmployee = employeeService.signUp(employee);
        if (newEmployee != null) {
            System.out.println("The Employee has been signed up:");
            System.out.println(newEmployee);
        } else
            return;
    }

    private static Long getLongNum() {
        Long num = null;
        try {
            num = scanner.nextLong();
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
        return num;
    }

    private static void employeeSignIn() {
        System.out.println("Welcome to the User Login Menu");
        System.out.println("--------------------------------");

        System.out.print("Enter your nationalCode: ");
        String nationalCode = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        Employee employee = employeeService.signIn(nationalCode, password);

        if (employee != null) {
            System.out.println("Login successful!");
            System.out.println(" Your profile is : " + "\n" + employee);
        } else {
            System.out.println("Login failed. Invalid username or password.");
        }
    }

    private static void employeeUpdate() {
        System.out.println("Enter Employee id to update:");
        long id = scanner.nextLong();
        scanner.nextLine();
        Optional<Employee> optionalEmployee = employeeService.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            System.out.println("Enter new Firstname: ");
            String newfirstname = scanner.nextLine();
            System.out.println("Enter Lastname: ");
            String lastname = scanner.nextLine();
            System.out.println("Enter NationalCode: ");
            String nationalCode = scanner.nextLine();
            System.out.println("Enter Password: ");
            String password = scanner.nextLine();
            System.out.println("Enter email: ");
            String email = scanner.nextLine();
            System.out.println("Enter the Base Salary: ");
            long baseSalary = getLongNum();
            Employee updatedEmployee = new Employee(id, newfirstname, lastname, nationalCode, password, email, baseSalary);
            employeeService.saveOrUpdate(updatedEmployee);
            if (updatedEmployee != null) {
                System.out.println("The Employee has been updated:");
                System.out.println(updatedEmployee);
            } else {
                System.out.println("Failed to update the Employee.");
            }
        } else {
            System.out.println("Employee not found.");
        }
        System.out.println("Employee information updated successfully.");
    }

    private static void employeeDelete() {
        try {
            System.out.println("Please enter the ID of the employee to delete:");
            long id = scanner.nextLong();
            scanner.nextLine();

            if (id > 0) {
                employeeService.deleteById(id);
                System.out.println("SUCCESSFULLY DELETED.");
            } else {
                System.out.println("Invalid ID. Please enter a positive integer for the ID.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer for the ID.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
//-==============================================================================================-------------------

    private static void teacherOptions() {
        boolean loop = true;

        while (loop) {
            System.out.println("***** Teacher MENU *****");
            System.out.println("Enter your choice: ");
            System.out.println("1. SIGNUP NEW Teacher");
            System.out.println("2. UPDATE PROFILE Teacher");
            System.out.println("3. DELETE ACCOUNT Teacher");
            System.out.println("4. EXIT");

            String input = scanner.nextLine();
            switch (input) {
                case "1" -> teacherSignUp();
                case "2" -> teacherUpdate();
                case "3" -> teacherDelete();
                case "4" -> loop = false;
                default -> System.out.println("This choice does not exist.");
            }
        }
    }

    private static void teacherSignUp() {

        System.out.println("Enter First Name: ");
        String firstname = scanner.nextLine();
        System.out.println("Enter Last Name: ");
        String lastname = scanner.nextLine();
        System.out.println("Enter NationalCode: ");
        String nationalCode = scanner.nextLine();
        System.out.println("Enter Password: ");
        String password = scanner.nextLine();
        System.out.println("Enter Email: ");
        String email = scanner.nextLine();
        System.out.println("Enter the Base Salary: ");
        long baseSalary = getLongNum();

        String[] level = new String[2];
        for (int i = 0; i < 2; i++) {
            level[i] = TeacherLevel.values()[i].toString();
        }
        System.out.println(Arrays.toString(level));
        System.out.println("Choose a Level:");
        TeacherLevel teacherLevel = null;
        try {
            teacherLevel = TeacherLevel.values()[scanner.nextInt() - 1];
        } catch (Exception e) {
            System.out.println("Invalid input for teacher level. Please enter a valid number.");
        }
        scanner.nextLine();
        Teacher teacher = new Teacher(firstname, lastname, nationalCode, password, email, baseSalary, teacherLevel);
        Teacher newTeacher = teacherService.signUp(teacher);
        System.out.println("The Teacher has been signed up: ");
        System.out.println(newTeacher);
    }

    private static void teacherUpdate() {
        System.out.println("Enter Teacher id to update:");
        long id = scanner.nextLong();
        scanner.nextLine();
        Optional<Teacher> byId = teacherService.findById(id);
        Teacher teacher = byId.get();

        if (byId.isPresent()) {
            System.out.println("Enter new Firstname: ");
            String newFirstName = scanner.nextLine();
            System.out.println("Enter Lastname: ");
            String lastName = scanner.nextLine();
            System.out.println("Enter NationalCode: ");
            String newNationalCode = scanner.nextLine();
            System.out.println("Enter Password: ");
            String newPassword = scanner.nextLine();
            System.out.println("Enter email: ");
            String email = scanner.nextLine();
            System.out.println("Enter the Base Salary: ");

            long baseSalary = 0L;
            try {
                baseSalary = scanner.nextLong();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input for base salary.");
                scanner.close();
                return;
            }
            scanner.nextLine();

            String[] level = new String[2];
            for (int i = 0; i < 2; i++) {
                level[i] = TeacherLevel.values()[i].toString();
            }
            System.out.println(Arrays.toString(level));
            System.out.println("Choose a Level:");
            TeacherLevel teacherLevel = null;
            try {
                teacherLevel = TeacherLevel.values()[scanner.nextInt() - 1];
            } catch (Exception e) {
                System.out.println("Invalid input for teacher level. Please enter a valid number.");
            }
            scanner.nextLine();

            Teacher updatedTeacher = new Teacher(id, newFirstName, lastName, newNationalCode, newPassword, email, baseSalary, teacherLevel);
            teacherService.saveOrUpdate(updatedTeacher);

            if (updatedTeacher != null) {
                System.out.println("The Teacher has been updated:");
                System.out.println(updatedTeacher);
            } else {
                System.out.println("Failed to update the Teacher.");
            }
        } else {
            System.out.println("Teacher not found.");
        }
        System.out.println("Teacher information updated successfully.");
    }

    private static void teacherDelete() {
        try {
            System.out.println("Please enter the ID of the Teacher to delete:");
            long id = scanner.nextLong();
            scanner.nextLine();

            if (id > 0) {
                teacherService.deleteById(id);
                System.out.println("SUCCESSFULLY DELETED.");
            } else {
                System.out.println("Invalid ID. Please enter a positive integer for the ID.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer for the ID.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
//======================================================================================================================

    private static void studentOptions() {

        boolean loop = true;

        while (loop) {
            System.out.println("***** STUDENT MENU *****");
            System.out.println("Enter your choice: ");
            System.out.println("1. SIGNUP NEW Student");
            System.out.println("2. UPDATE PROFILE");
            System.out.println("3. DELETE ACCOUNT");
            System.out.println("4. EXIT");


            String input = scanner.nextLine();
            switch (input) {
                case "1" -> studentSignUp();
                case "2" -> studentUpdate();
                case "3" -> studentDelete();
                case "4" -> loop = false;
                default -> System.out.println("This choice does not exist.");
            }
        }
    }

    private static void studentSignUp() {
        try {
            System.out.println("Enter Firstname: ");
            String firstname = scanner.nextLine();
            System.out.println("Enter Lastname: ");
            String lastname = scanner.nextLine();
            System.out.println("Enter NationalCode: ");
            String nationalCode = scanner.nextLine();
            System.out.println("Enter Password: ");
            String password = scanner.nextLine();
            System.out.println("Enter email: ");
            String email = scanner.nextLine();

            Student newStudent = new Student(firstname, lastname, nationalCode, password, email);
            Student student = studentService.signUp(newStudent);
            if (student != null) {
                System.out.println("The Student has been signed up:");
                System.out.println(student);
            } else {
                System.out.println("An error occurred while signing up the student.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void studentUpdate() {
        try {
            System.out.println("Enter Student id to update:");
            long id = scanner.nextLong();
            scanner.nextLine();
            Optional<Student> optionalStudent = studentService.findById(id);
            if (optionalStudent.isPresent()) {
                Student studentToUpdate = optionalStudent.get();

                System.out.println("Enter new Firstname : ");
                String newFirstname = scanner.nextLine();

                System.out.println("Enter Lastname: ");
                String lastname = scanner.nextLine();
                System.out.println("Enter NationalCode: ");
                String nationalCode = scanner.nextLine();
                System.out.println("Enter Password: ");
                String password = scanner.nextLine();
                System.out.println("Enter email: ");
                String email = scanner.nextLine();

                studentToUpdate.setFirstName(newFirstname);
                studentToUpdate.setLastName(lastname);
                studentToUpdate.setNationalCode(nationalCode);
                studentToUpdate.setPassword(password);
                studentToUpdate.setEmail(email);

                Student updatedStudent = studentService.saveOrUpdate(studentToUpdate);

                if (updatedStudent != null) {
                    System.out.println("The Student has been updated:");
                    System.out.println(updatedStudent);
                } else {
                    System.out.println("Failed to update the Student.");
                }
            } else {
                System.out.println("Student not found.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        System.out.println("Student information updated successfully.");
    }

    private static void studentDelete() {
        try {
            System.out.println("Please enter the ID of the Student to delete:");
            long id = scanner.nextLong();
            scanner.nextLine();

            if (id > 0) {
                studentService.deleteById(id);
                System.out.println("SUCCESSFULLY DELETED.");
            } else {
                System.out.println("Invalid ID. Please enter a positive integer for the ID.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer for the ID.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
//======================================================================================================================

    private static void courseOptions() {
        courseService.showAll().forEach(System.out::println);

        boolean loop = true;

        while (loop) {

            System.out.println("***** COURSES MENU *****");
            System.out.println("Enter your choice: ");
            System.out.println("1. SAVE New Course");
            System.out.println("2. UPDATE Existing Course");
            System.out.println("3. DELETE Existing Course");
            System.out.println("4. New Released Course");
            System.out.println("5. Create new Semester");
            System.out.println("6. EXIT");


            String input = scanner.nextLine();
            switch (input) {
                case "1" -> saveNewCourse();
                case "2" -> updateExistingCourse();
                case "3" -> deleteExistingCourse();
                case "4" -> saveNewReleasedCourse();
                case "5" -> createSemester();
                case "6" -> loop = false;
                default -> System.out.println("This choice does not exist.");
            }
        }
    }

    private static void saveNewCourse() {
        System.out.println("Enter the Course title: ");
        String title = scanner.nextLine();

        System.out.println("Enter the Course Credit: ");
        int credit = 0;
        try {
            credit = Integer.parseInt(scanner.nextLine());
            if (credit <= 0) {
                System.out.println("Invalid credit value. Please enter a positive number.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid credit value. Please enter a valid number.");
            return;
        }

        Course newCourse = new Course(title, credit);
        courseService.saveOrUpdate(newCourse);
    }

    private static void updateExistingCourse() {

        try {
            System.out.println("Enter Course id to update:");
            long id = scanner.nextLong();
            scanner.nextLine();

            Optional<Course> optionalCourse = courseService.findById(id);
            if (optionalCourse.isPresent()) {
                Course courseUpdate = optionalCourse.get();
                System.out.println("Enter the NEW Course title: ");
                String title = scanner.nextLine();
                System.out.println("Enter the Course Credit: ");
                int credit = scanner.nextInt();
                scanner.nextLine();

                courseUpdate.setTitle(title);
                courseUpdate.setCredit(credit);

                courseService.saveOrUpdate(courseUpdate);
            } else {
                System.out.println("Course not found with the given ID.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while updating the course: " + e.getMessage());
        }
    }

    private static void deleteExistingCourse() {

        try {
            System.out.println("Please enter the ID of the Course to delete:");
            long id = scanner.nextLong();
            scanner.nextLine();

            if (id > 0) {
                courseService.deleteById(id);
                System.out.println("SUCCESSFULLY DELETED.");
            } else {
                System.out.println("Invalid ID. Please enter a positive integer for the ID.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer for the ID.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void saveNewReleasedCourse() {
        List<Teacher> teacherList = teacherService.showAll().stream().toList();
        List<Course> courseList = courseService.showAll().stream().toList();
        List<Semester> semestersList = semesterService.showAll().stream().toList();


        System.out.println("Available Semester:");
        for (int i = 0; i < semestersList.size(); i++) {
            System.out.println((i + 1) + ". " + semestersList.get(i));
        }
        System.out.print("Select a semester (enter the number): ");
        int selectedSemesterIndex = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Available Teachers:");
        for (int i = 0; i < teacherList.size(); i++) {
            System.out.println((i + 1) + ". " + teacherList.get(i));
        }
        System.out.print("Select a teacher (enter the number): ");
        int selectedTeacherIndex = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\nAvailable Courses:");
        for (int i = 0; i < courseList.size(); i++) {
            System.out.println((i + 1) + ". " + courseList.get(i));
        }
        System.out.print("Select a course (enter the number): ");
        int selectedCourseIndex = Integer.parseInt(scanner.nextLine());


        ReleasedCourse newReleasedCourse = new ReleasedCourse();

        newReleasedCourse.setTeacher(teacherList.get(selectedTeacherIndex - 1));
        newReleasedCourse.setCourse(courseList.get(selectedCourseIndex - 1));
        newReleasedCourse.setSemester(semestersList.get(selectedSemesterIndex - 1));
        releasedCourseService.saveOrUpdate(newReleasedCourse);

        Teacher selectedTeacher = teacherList.get(selectedTeacherIndex - 1);
        Course selectedCourse = courseList.get(selectedCourseIndex - 1);
        int credits = selectedCourse.getCredit();
        selectedTeacher.setTotalCredit(credits);
        teacherService.saveOrUpdate(selectedTeacher);


    }

    private static Semester createSemester() {

        System.out.println("Please enter year ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please enter seoson ");
        int seoson = scanner.nextInt();
        scanner.nextLine();
        Semester semester = new Semester(seoson, year);
        semesterService.saveOrUpdate(semester);
        return semester;

    }
}
