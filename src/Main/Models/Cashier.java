package Main.Models;

import Main.Common.Exceptions.NegativeSalaryException;
import Main.Models.Contracts.ICashier;
import Main.Models.Contracts.IProduct;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Cashier implements ICashier {
    private String name;
    private int id;
    private double salary;

    public Cashier(String _name, int _id, double _salary){
        this.name = _name;
        this.id = _id;
        setSalary(_salary);
    }

    private void setSalary(double _salary){
        if (_salary < 0){
            throw new NegativeSalaryException("Salary Cannot be Negative!");
        }

        this.salary = _salary;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public double getSalary() {
        return this.salary;
    }
}
