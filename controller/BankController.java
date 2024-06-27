package controller;

import entity.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repo.BankRepository;
import service.BankService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/api/v1")
public class BankController {
    @Autowired

    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/account")
    public ResponseEntity<BankAccount> saveAccount(@RequestBody BankAccount account) {
        BankAccount newAccount = bankService.saveBankAccount(account);
        return ResponseEntity.ok(newAccount);
    }

    @GetMapping("/accounts")
    public List<BankAccount> getAllAccounts() {
        return bankService.getAllAccounts();
    }

    @GetMapping("/accounts/{id}") // Changed the path to /accounts/{id}
    public ResponseEntity<BankAccount> getProductById(@PathVariable Long id) {
        Optional<BankAccount> account = bankService.getProductById(id);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/accounts/{id}") // Changed the path to /accounts/{id}
    public ResponseEntity<BankAccount> updateProduct(@PathVariable Long id, @RequestBody BankAccount account) {
        BankAccount updatedAccount = bankService.updateAccount(id, account);
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("/accounts/{id}") // Changed the path to /accounts/{id}
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        bankService.deleteProduct(id);
        return ResponseEntity.ok("Account deleted successfully");
    }
}