package Data;

import entity.BankAccount;
import org.hibernate.sql.ast.tree.expression.Over;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import service.BankService;

public class DataLoader implements CommandLineRunner {
    private  final BankService bankService;
    @Autowired
    public  DataLoader(BankService bankService){
        this.bankService=bankService;
    }
    @Override
    public  void run(String...args) throws Exception
    {
        //add initial data here
        bankService.saveBankAccount(new BankAccount(1254413,"Jon doe"));
    }}
