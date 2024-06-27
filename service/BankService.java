package service;

import entity.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import repo.BankRepository;

import java.util.List;
import java.util.Optional;

@Service
//Service class for managing BankAccount entities.
//handle business logic for our REST API
public class BankService {
    private  final BankRepository bankRepository;
    @Autowired

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }
    public BankAccount saveBankAccount(BankAccount bankAccount){
        return  bankRepository.save(bankAccount);
    }
    public  List<BankAccount> getAllAccounts() {
        return bankRepository.findAll();
    }
    public Optional<BankAccount> getProductById(Long id) {
        return bankRepository.findById(id);
    }
    public BankAccount updateAccount(Long id, BankAccount updatedAccount) {
        Optional<BankAccount> existingAccount = bankRepository.findById(id);
        if (existingAccount.isPresent()) {
            BankAccount product = existingAccount.get();
            product.setCustomerName(updatedAccount.getCustomerName());
            return bankRepository.save(product);
        } else {
            throw new RuntimeException("Product not found");
        }
    }
    public void deleteProduct(Long id) {
        bankRepository.deleteById(id);
    }
    // Save Product
    public ResponseEntity<BankAccount> saveProduct(@RequestBody BankAccount product)
    {
        BankAccount newAccount = bankRepository.save(product);
        return ResponseEntity.ok(newAccount);
    }
    // Get all products
    public ResponseEntity<List<BankAccount> > fetchAllProducts()
    {
        return ResponseEntity.ok(bankRepository.findAll());
    }
    // Get an account by ID
    public ResponseEntity<Optional<BankAccount> >
    fetchAccountById(Long id)
    {
        Optional<BankAccount> account
                = bankRepository.findById(id);
        if (account.isPresent()) {
            return ResponseEntity.ok(account);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    public ResponseEntity<String> deleteAccount(Long id)
    {
        bankRepository.deleteById(id);
        return ResponseEntity.ok(
                "Product Deleted Successfully");
    }

}
