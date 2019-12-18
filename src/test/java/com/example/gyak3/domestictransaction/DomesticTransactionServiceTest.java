package com.example.gyak3.domestictransaction;

import com.example.gyak3.exceptions.IdValidationException;
import com.example.gyak3.exceptions.MissingParameterException;
import com.example.gyak3.exceptions.ValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

public class DomesticTransactionServiceTest {

    @InjectMocks
    DomesticTransactionService transactionService;

    @Mock
    domesticTransactionRepository transactionRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getListOfTransactions() {
        when(transactionRepository.findAll()).thenReturn(List.of(new DomesticTransaction()));

        List<DomesticTransaction> list = transactionService.getListOfTransactions();
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void getTransaction() {
        var uuid = UUID.randomUUID();
        var t = getTransaction(uuid);
        when(transactionRepository.find(uuid)).thenReturn(t);

        Assert.assertEquals(transactionService.getTransaction(uuid), t);
    }

    @Test
    public void createTransaction() {
        var t = getTransaction(null);
        when(transactionRepository.save(t)).thenReturn(getTransaction(UUID.randomUUID()));
        var created = transactionService.createTransaction(t);
        Assert.assertNotNull(created);
        Assert.assertNotNull(created.getTransactionId());
        Assert.assertNotNull(created.getHostAccountNumber());
        Assert.assertNotNull(created.getSenderName());
        Assert.assertNotNull(created.getHostName());
    }

    @Test(expected = IdValidationException.class)
    public void createNegativeWithId() {
        var t = getTransaction(UUID.randomUUID());
        transactionService.createTransaction(t);
    }

    @Test(expected = MissingParameterException.class)
    public void createWithMissing() {
        var t = getTransaction(null);
        t.setSenderName(null);
        transactionService.createTransaction(t);
    }

    @Test(expected = ValidationException.class)
    public void createSenderEqualsHost() {
        var t = getTransaction(null);
        t.setSenderName(t.getHostName());
        transactionService.createTransaction(t);
    }

    @Test(expected = ValidationException.class)
    public void createHostTooLong() {
        var t = getTransaction(null);
        t.setHostName("a".repeat(21));
        transactionService.createTransaction(t);
    }

    @Test(expected = ValidationException.class)
    public void createAmmountIncorrect() {
        var t = getTransaction(null);
        t.setAmount(-1);
        transactionService.createTransaction(t);
    }

    @Test( expected = ValidationException.class)
    public void createWithShortAccountNumber() {
        var t = getTransaction(null);
        t.setHostAccountNumber("12345674");
        transactionService.createTransaction(t);
    }

    @Test( expected = ValidationException.class)
    public void createWithLongAccountNumber() {
        var t = getTransaction(null);
        t.setHostAccountNumber("12345674-12345674-12345674-12345674");
        transactionService.createTransaction(t);
    }

    @Test( expected = ValidationException.class)
    public void createWithInvalidAccountNumber() {
        var t = getTransaction(null);
        t.setHostAccountNumber("12345674");
        transactionService.createTransaction(t);
    }

    @Test
    public void deleteTransaction() {
        transactionService.delete(UUID.randomUUID());
    }

    @Test
    public void updateTransaction() {
        var t = getTransaction(UUID.randomUUID());
        when(transactionRepository.update(t)).thenReturn(t);
        Assert.assertEquals(t, transactionService.updateTransaction(t));
    }

    @Test
    public void updateCreateNew() {
        var t = getTransaction(null);
        var tWithId = getTransaction(UUID.randomUUID());
        when(transactionRepository.update(tWithId)).thenReturn(tWithId);
        when(transactionRepository.save(t)).thenReturn(tWithId);
        Assert.assertEquals(tWithId, transactionService.updateTransaction(t));
        Assert.assertEquals(tWithId, transactionService.updateTransaction(tWithId));
    }



    private DomesticTransaction getTransaction(UUID uuid) {
        return new DomesticTransaction(
                uuid, "jano", "jeno", "12345678-87654321", 34);
    }

}
