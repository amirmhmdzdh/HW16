package org.hw16.util;

import org.hibernate.SessionFactory;
import org.hw16.connection.SessionFactorySingleton;
import org.hw16.repository.*;
import org.hw16.repository.impel.*;
import org.hw16.service.*;
import org.hw16.service.impel.*;

public class ApplicationContext {

    private static final SessionFactory SESSION_FACTORY;

    //==================================================================================================================


    private static final CourseRepository COURSE_REPOSITORY;
    private static final CourseService COURSE_SERVICE;

    //==================================================================================================================

    private static final EmployeeRepository EMPLOYEE_REPOSITORY;
    private static final EmployeeService EMPLOYEE_SERVICE;

    //==================================================================================================================


    private static final TeacherRepository TEACHER_REPOSITORY;
    private static final TeacherService TEACHER_SERVICE;

    //==================================================================================================================


    private static final StudentRepository STUDENT_REPOSITORY;
    private static final StudentService STUDENT_SERVICE;

    //==================================================================================================================


    private static final SemesterRepository SEMESTER_REPOSITORY;
    private static final SemesterService SEMESTER_SERVICE;

    //==================================================================================================================


    private static final ReleasedCourseRepository RELEASED_COURSE_REPOSITORY;
    private static final ReleasedCourseService RELEASED_COURSE_SERVICE;

    //==================================================================================================================


    private static final StudentTakenCourseRepository STUDENT_TAKEN_COURSE_REPOSITORY;
    private static final StudentTakenCourseService STUDENT_TAKEN_COURSE_SERVICE;

    //==================================================================================================================

    static {
        SESSION_FACTORY = SessionFactorySingleton.getInstance();

        //--------------------------------------------------------------------------------------------------------------


        COURSE_REPOSITORY = new CourseRepositoryImpel(SESSION_FACTORY);
        COURSE_SERVICE = new CourseServiceImpel(COURSE_REPOSITORY, SESSION_FACTORY);

        //--------------------------------------------------------------------------------------------------------------

        EMPLOYEE_REPOSITORY = new EmployeeRepositoryImpel(SESSION_FACTORY);
        EMPLOYEE_SERVICE = new EmployeeServiceImpel(EMPLOYEE_REPOSITORY, SESSION_FACTORY);

        //--------------------------------------------------------------------------------------------------------------


        TEACHER_REPOSITORY = new TeacherRepositoryImpel(SESSION_FACTORY);
        TEACHER_SERVICE = new TeacherServiceImpel(TEACHER_REPOSITORY, SESSION_FACTORY);

        //--------------------------------------------------------------------------------------------------------------


        STUDENT_REPOSITORY = new StudentRepositoryImpel(SESSION_FACTORY);
        STUDENT_SERVICE = new StudentServiceImpl(STUDENT_REPOSITORY, SESSION_FACTORY);

        //--------------------------------------------------------------------------------------------------------------


        SEMESTER_REPOSITORY = new SemesterRepositoryImpel(SESSION_FACTORY);
        SEMESTER_SERVICE = new SemesterServiceImpel(SEMESTER_REPOSITORY, SESSION_FACTORY);

        //--------------------------------------------------------------------------------------------------------------


        RELEASED_COURSE_REPOSITORY = new ReleasedCourseRepositoryImpel(SESSION_FACTORY);
        RELEASED_COURSE_SERVICE = new ReleasedCourseServiceImpel(RELEASED_COURSE_REPOSITORY, SESSION_FACTORY);

        //--------------------------------------------------------------------------------------------------------------


        STUDENT_TAKEN_COURSE_REPOSITORY = new StudentTakenCourseRepositoryImpel(SESSION_FACTORY);
        STUDENT_TAKEN_COURSE_SERVICE = new StudentTakenCourseServiceImpel(STUDENT_TAKEN_COURSE_REPOSITORY, SESSION_FACTORY);

    }

    public static CourseService getCourseService() {
        return COURSE_SERVICE;
    }

    public static EmployeeService getEmployeeService() {
        return EMPLOYEE_SERVICE;
    }

    public static TeacherService getTeacherService() {
        return TEACHER_SERVICE;
    }

    public static StudentService getStudentService() {
        return STUDENT_SERVICE;
    }

    public static SemesterService getSemesterService() {
        return SEMESTER_SERVICE;
    }

    public static ReleasedCourseService getReleasedCourseService() {
        return RELEASED_COURSE_SERVICE;
    }

    public static StudentTakenCourseService getStudentTakenCourseService() {
        return STUDENT_TAKEN_COURSE_SERVICE;
    }
}

