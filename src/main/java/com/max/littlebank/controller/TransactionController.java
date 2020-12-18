package com.max.littlebank.controller;

import com.max.littlebank.DTO.TransactionDTO;
import com.max.littlebank.models.Transaction;
import com.max.littlebank.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Serov Maxim
 */

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> showAllTransactions() {
        return transactionService.showAllTransactions();
    }

    @GetMapping("/{id}")
    public List<Transaction> showAllTransactions(@PathVariable long id) {
        return transactionService.findAllByAccount_AccountNumber(id);
    }


    @GetMapping("/{startDate}/{endDate}") //"yyyy-MM-ddThh:mm:ss"  2007-12-03T10:15:30
    public List<Transaction> showAllTransactionsByDate(@PathVariable String startDate,
                                                       @PathVariable String endDate) {
        return transactionService.findByTransactionBetweenDate(startDate, endDate);
    }

    @PostMapping
    public String saveTransaction(@RequestBody TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction(transactionDTO);
        transactionService.saveTransaction(transaction);
        return "Transaction with id = " + transaction.getId() + " created!";
    }

    @DeleteMapping("/{id}")
    public String deleteAllByAccount_AccountNumber(@PathVariable long id) {
        transactionService.deleteAllByAccount_AccountNumber(id);
        return "Deleted ok transactions with account number" + id;
    }

}
