package org.hw16.connection;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hw16.model.*;

public class SessionFactorySingleton {

    private SessionFactorySingleton() {
    }

    private static class LazyHolder {
        static SessionFactory INSTANCE;

        static {
            var registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();

            INSTANCE = new MetadataSources(registry)
                    .addAnnotatedClass(Person.class)
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(Teacher.class)
                    .addAnnotatedClass(Employee.class)
                    .addAnnotatedClass(Course.class)
                    .addAnnotatedClass(Department.class)
                    .addAnnotatedClass(ReleasedCourse.class)
                    .addAnnotatedClass(Semester.class)
                    .addAnnotatedClass(StudentTakenCourse.class)

                    .buildMetadata()
                    .buildSessionFactory();
        }
    }

    public static SessionFactory getInstance() {
        return LazyHolder.INSTANCE;
    }
}