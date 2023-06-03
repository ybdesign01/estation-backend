package com.app.estation.controller;

import com.app.estation.dto.DebitRequest;
import com.app.estation.dto.EncaissementProduitRequest;
import com.app.estation.dto.EncaissementRequest;
import com.app.estation.service.implementation.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private TransactionServiceImpl transactionService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(transactionService.getAll());
    }

    @PostMapping(value = "/encaissementPompeUser", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> encaissementPompeUser(@Validated @RequestBody EncaissementRequest request) {
        if (transactionService.addEncaissementPompeUser(request)){
         return ResponseEntity.ok(Map.of("msg", "transactions_added"));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("msg", "transactions_not_added"));
        }
    }

    @PostMapping(value = "/encaissementProduit", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> encaissementProduit(@Validated @RequestBody EncaissementProduitRequest request) {
        if (transactionService.addEncaissementProduit(request)){
            return ResponseEntity.ok(Map.of("msg", "transactions_added"));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("msg", "transactions_not_added"));
        }
    }

    @PostMapping(value = "/debit", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> debit(@Validated @RequestBody DebitRequest request) {
        if (transactionService.debitTransaction(request)){
            return ResponseEntity.ok(Map.of("msg", "transactions_added"));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("msg", "transactions_not_added"));
        }
    }

}
