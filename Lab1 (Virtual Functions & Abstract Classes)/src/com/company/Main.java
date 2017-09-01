//Elisabeth Bruesewitz
//Programming Languages
//Lab 1: Virtual Functions & Abstract Classes
//Sept 1, 2017

import java.io.*;
import java.util.*;

abstract class Employee {
    String name;

    Employee() {}
    Employee (String nm) { name = nm; }
    abstract double computePay();
    void   display () {}
    void   setHours(double hrs) {}
    void   setSales(double sales) {}
    void   setSalary(double salary) { System.out.println("NO!"); }
}

class WageEmployee extends Employee{
    double rate;
    double hours;

    WageEmployee(String nm) { super(nm); }
    WageEmployee(String nm, double r) { super(nm); rate = r; }
    void setRate(double r)    { rate  = r; }
    void setHours(double hrs) { hours = hrs; }
    double computePay()       { return rate*hours; }
}

class Programmer extends WageEmployee {
    Programmer (String nm, double w) { super(nm, w); }
    void display() {
        System.out.println("Name: "+name+"\tHours: "+hours
                +"\tRate: "+rate);
    }
}

class SalesPerson extends WageEmployee {
    double commission;
    double SalesMade;

    SalesPerson (String nm, double c) {
        super(nm);
        commission = c;
    }

    void setCommission(double comm) { commission = comm; }

    void setSales(double sales) { SalesMade = sales; }

    double computePay() { return commission*SalesMade; }

    void display() {
        System.out.println("Name: "+name+"\tCommission: "+commission
                +"\tSales: "+SalesMade);
    }
}

class Manager extends Employee {
    double monthlysalary;

    Manager () { super(""); }

    Manager(String nm, double w)  { super(nm); monthlysalary = w; }

    void setSalary(double salary) { monthlysalary = salary; }

    double computePay()           { return monthlysalary; }

    void display() {
        System.out.println("Name: "+name+"\tMonthly Salary: "+
                monthlysalary);
    }
}

interface ManagerInterface {
    double managerComputePay();
    void managerDisplay();
}

class SalesManager extends SalesPerson implements ManagerInterface {
    double monthlysalary;

    SalesManager(String nm, double w) { super (nm, w); }

    public double managerComputePay() {
        return monthlysalary;
    }

    double computePay() {
        System.out.println("SalesManager: " + name + " " +
                (super.computePay()+managerComputePay()));
        return super.computePay() + managerComputePay();
    }

    void setSalary (double s) { monthlysalary = s; }

    public void managerDisplay() {
        System.out.println("Name: "+name+"\tMonthly Salary: "+monthlysalary);
    }

    void display() {
        super.display();
        managerDisplay();
    }
}

class EmployeeList{
    Queue<Employee> employees = new ArrayDeque<Employee>();

    void enqueue (Employee emp){
        employees.add(emp);
    }

    Employee find (String nm){
        for (Employee emp : employees) {
            if(emp.name == nm){
                return emp;
            }
        }

        return null;
    }

    void setHours(String nm, double hrs){
        Employee emp = find(nm);
        if(emp != null){
            emp.setHours(hrs);
        }
    }

    void setSalary(String nm, double salary){
        Employee emp = find(nm);
        if(emp != null){
            emp.setSalary(salary);
        }
    }

    void setSales(String nm, double sales){
        Employee emp = find(nm);
        if(emp != null ){
            emp.setSales(sales);
        }
    }

    double payroll(){
        double totalPayroll = 0;

        for(Employee emp : employees){
            totalPayroll += emp.computePay();
        }

        return totalPayroll;
    }

    void display(){
        for(Employee emp : employees){
            emp.display();
        }
    }

}


class Wages {

    public static void main(String[] args) {
	// write your code here
        EmployeeList emp = new EmployeeList();
        emp.enqueue(new SalesManager("Gee", 1000));
        emp.enqueue(new SalesManager("Gal", 1000));
        emp.enqueue(new SalesManager("Gem", 1000));
        emp.enqueue(new SalesPerson("John", 0.03));
        emp.enqueue(new SalesPerson("Joan", 0.04));
        emp.enqueue(new SalesPerson("Jack", 0.02));
        emp.enqueue(new Manager("Fred", 10000));
        emp.enqueue(new Manager("Frank", 5000));
        emp.enqueue(new Manager("Florence", 3000));
        emp.enqueue(new Programmer("Linda", 7));
        emp.enqueue(new Programmer("Larry", 5));
        emp.enqueue(new Programmer("Lewis", 3));

        emp.setHours("Linda", 35);
        emp.setHours("Larry", 23);
        emp.setHours("Lewis", 3);
        emp.setSales("John", 12000);
        emp.setSales("Joan", 10000);
        emp.setSales("Jack", 5000);
        emp.setSales("Gee", 4000);
        emp.setSales("Gal", 3000);
        emp.setSales("Gem", 2000);
        emp.setSalary("Gee", 1000);
        emp.setSalary("Gal", 2000);
        emp.setSalary("Gem", 3000);
        emp.display();
        System.out.println("Payroll: "+emp.payroll());
    }

}
