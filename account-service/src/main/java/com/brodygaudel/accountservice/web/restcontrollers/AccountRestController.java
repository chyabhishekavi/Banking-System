package com.brodygaudel.accountservice.web.restcontrollers;

import com.brodygaudel.accountservice.dtos.AccountDTO;
import com.brodygaudel.accountservice.exceptions.AccountNotFoundException;
import com.brodygaudel.accountservice.exceptions.CustomerNotFoundException;
import com.brodygaudel.accountservice.service.AccountService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2/accounts")
public class AccountRestController {

    private final AccountService accountService;

    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/get/{id}")
    public AccountDTO getById(@PathVariable String id) throws AccountNotFoundException{
        return accountService.getById(id);
    }

    @GetMapping("/find/{customerId}")
    public AccountDTO getByCustomerId(@PathVariable String customerId) throws AccountNotFoundException{
        return accountService.getByCustomerId(customerId);
    }

    @PostMapping("/save")
    public AccountDTO save(@RequestBody AccountDTO accountDTO) throws CustomerNotFoundException{
        return accountService.save(accountDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable String id){
        accountService.deleteById(id);
    }

    @PutMapping("/update")
    public AccountDTO updateStatus(@RequestBody AccountDTO accountDTO) throws AccountNotFoundException{
        return accountService.updateStatus(accountDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(@NotNull Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
