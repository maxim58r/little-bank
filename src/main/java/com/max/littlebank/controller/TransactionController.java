package com.max.littlebank.controller;

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


    @GetMapping("/{startDate}/{endDate}") //"yyyy-MM-ddThh:mm:ss"  2007-12-03T10:15:30
    public List<Transaction> showAllTransactionsByDate(@PathVariable String startDate,
                                                 @PathVariable String endDate ) {
       return transactionService.findByTransactionBetweenDate(startDate, endDate);
    }

    @PostMapping
    public String saveTransaction(@RequestBody Transaction transaction) {
        transactionService.saveTransaction(transaction);
        return "Transaction with id = " + transaction.getId() + " created!";
    }
}
