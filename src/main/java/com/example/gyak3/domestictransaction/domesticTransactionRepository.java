package com.example.gyak3.domestictransaction;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class domesticTransactionRepository {

    private Map<UUID, DomesticTransaction> transactions = new HashMap<>();

    public List<DomesticTransaction> findAll() {
        return new ArrayList<>(transactions.values());
    }

    public DomesticTransaction find(UUID id) {
        return transactions.get(id);
    }

    public DomesticTransaction save(DomesticTransaction transaction) {
        UUID id = UUID.randomUUID();
        transaction.setTransactionId(id);
        transactions.put(id, transaction);
        return transaction;
    }

    public DomesticTransaction update(DomesticTransaction transaction) {
        transactions.put(transaction.getTransactionId(), transaction);
        return transaction;
    }

    public void delete(UUID id) {
        transactions.remove(id);
    }

}
