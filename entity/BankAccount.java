package entity;

import jakarta.persistence.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
@Entity
@Table(name="bank")

public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private int balance = 100;
    @Column(nullable = false)
    private int dailyLimit = 3000;

    @Column(nullable = false)
    private int dailyWithdrawn = 0;
    @Column(nullable = false)
    private LocalDate lastWithdrawnDate = LocalDate.now();

    public BankAccount(){

    }
    public BankAccount(long id, String customerName) {
        this.id = id;
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public long getId() {
        return id;
    }

    public int getBalance() {
        return balance;

    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int deposit(int amount) {
        if (amount <= 0) {
            System.out.println("Deposit must be higher than 0");
            throw new IllegalArgumentException("Deposit must be higher than 0");
        } else {
            return balance += amount;
        }
    }

    public int withdraw(int amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds");
            throw new IllegalArgumentException("Insufficient funds");
        }
        if (amount <= 0) {
            System.out.println("Unsupported operation");
            throw new UnsupportedOperationException("Unsupported operation");
        } else {

            LocalDate currentDate = LocalDate.now();

            //if the current date is different from last withdrawn date then
            //update daily withdrawn to 0 and set last withdrawn date to current date
            if (!currentDate.isEqual(lastWithdrawnDate)) {
                dailyWithdrawn = 0;
                lastWithdrawnDate = currentDate;

            }
            dailyWithdrawn += amount;
            if (dailyWithdrawn > dailyLimit) {
                System.out.println("Daily limit is exceeded.Try again tomorrow.");
                throw new IllegalArgumentException("Daily limit is exceeded");
            }
        }
        return balance -= amount;
    }
}


