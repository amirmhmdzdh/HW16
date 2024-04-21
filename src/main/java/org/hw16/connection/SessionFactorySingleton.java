package org.hw16.connection;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hw16.model.*;

public class SessionFactorySingleton {
    private SessionFactorySingleton() {
    }

    private static class SessionFactoryHelper {
        static org.hibernate.service.ServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        .configure()
                        .build();
        private static final SessionFactory INSTANCE =
                new MetadataSources(registry)
                        .addAnnotatedClass(Person.class)
                        .addAnnotatedClass(Student.class)
                        .addAnnotatedClass(Teacher.class)
                        .addAnnotatedClass(Employee.class)
                        .addAnnotatedClass(Course.class)
                        .addAnnotatedClass(ReleasedCourse.class)
                        .addAnnotatedClass(Semester.class)
                        .addAnnotatedClass(StudentTakenCourse.class)
                        .buildMetadata()
                        .buildSessionFactory();
    }

    public static SessionFactory getInstance() {
        return SessionFactoryHelper.INSTANCE;
    }
}
