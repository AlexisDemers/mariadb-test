package ade.practice.mariadb;

import ade.practice.mariadb.database.EmployeeDataAccess;
import ade.practice.mariadb.entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String input;

        do{
            input = in.nextLine();

            List<String> parameters = Arrays.asList(input.split(" "));

            switch (parameters.get(0)) {
                case "init":
                    init();
                    break;
                case "fetch-employees":
                    new EmployeeDataAccess().GetAll().forEach(x -> System.out.println(x.toString()));
                    break;
                case "fetch-employee":
                    if (parameters.size() < 2) break;

                    System.out.println(new EmployeeDataAccess().Get(Integer.parseInt(parameters.get(1))).toString());
                    break;
                case "insert-employee":
                    if (parameters.size() < 3) break;

                    Employee newEmployee = new Employee(parameters.get(1), parameters.get(2));

                    new EmployeeDataAccess().Insert(newEmployee);

                    System.out.println(newEmployee.toString());
                    break;
                default:
            }
            System.out.println(" ## Done ##");

        } while(!input.equals("exit"));
    }

    private static void init() {
        Configuration conf = new Configuration();
        conf.configure();
        SessionFactory factory;
        try {
            factory = conf.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        Employee e1=new Employee("Alexzis", "Deemers");

        session.save(e1);
        t.commit();
        System.out.println("successfully saved");
        factory.close();
        session.close();
    }
}
