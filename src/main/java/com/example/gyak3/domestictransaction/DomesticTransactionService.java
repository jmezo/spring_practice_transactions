package com.example.gyak3.domestictransaction;

import com.example.gyak3.exceptions.IdValidationException;
import com.example.gyak3.exceptions.MissingParameterException;
import com.example.gyak3.exceptions.TooLongException;
import com.example.gyak3.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DomesticTransactionService {

    @Autowired
    private domesticTransactionRepository repository;

    public DomesticTransaction getTransaction(UUID id) {
        return repository.find(id);
    }

    public List<DomesticTransaction> getListOfTransactions() {
        return repository.findAll();
    }

    public DomesticTransaction createTransaction(DomesticTransaction transaction) {
        if (transaction.getTransactionId() != null) {
            throw new IdValidationException("ID has to be null");
        }

        validateTransaction(transaction);
        return repository.save(transaction);
    }

    public DomesticTransaction updateTransaction(DomesticTransaction transaction) {
        if (transaction.getTransactionId() == null) {
            return createTransaction(transaction);
        } else {
            return repository.update(transaction);
        }
    }

    public void delete(UUID id) {
        repository.delete(id);
    }

    private void validateTransaction(DomesticTransaction t) {
        if (t.getHostName() == null || t.getSenderName() == null || t.getHostAccountNumber() == null) {
            throw new MissingParameterException("missing parameter");
        }
        if (t.getSenderName().equals(t.getHostName())) {
            throw new ValidationException("sender can't be same as host");
        }
        if (t.getHostName().length() > 20) {
            throw new ValidationException("host name too long");
        }
        validateHostAccountNum(t.getHostAccountNumber());

        if (t.getAmount() < 1 || t.getAmount() > 999999999999L) {
            throw new TooLongException("ammount too long");
        }
    }

    private void validateHostAccountNum(String num) {
        String[] arr = num.split("-");
        if (arr.length > 3) {
            throw new TooLongException("too long");
        } else if (arr.length < 2) {
            throw new ValidationException("too short");
        }
        for(String s : arr) {
            if (!s.matches("[0-9]{8}")) {
                throw new ValidationException("wrong format");
            }
        }
    }

}
