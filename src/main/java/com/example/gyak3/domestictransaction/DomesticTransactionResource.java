package com.example.gyak3.domestictransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class DomesticTransactionResource {

    @Autowired
    private DomesticTransactionService service;

    @GetMapping("/transaction/{id}")
    public DomesticTransaction getTransaction(@PathVariable(name = "id")UUID id) {
        return service.getTransaction(id);
    }

    @GetMapping("/transactions")
    public List<DomesticTransaction> getTransactions() {
        return service.getListOfTransactions();
    }

    @PostMapping("/transaction")
    public DomesticTransaction createTransaction(@RequestBody DomesticTransaction transaction) {
        return service.createTransaction(transaction);
    }

    @PutMapping("/transaction")
    public DomesticTransaction updateTransaction(@RequestBody DomesticTransaction transaction) {
        return service.updateTransaction(transaction);
    }

    @DeleteMapping("/transaction/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable(name = "id")UUID id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
