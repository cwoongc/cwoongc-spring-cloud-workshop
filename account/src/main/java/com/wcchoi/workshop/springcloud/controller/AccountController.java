package com.wcchoi.workshop.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @GetMapping("/{marketAccountNo}/{exchangeAccountNo}")
    public String getAccounts(@PathVariable Long marketAccountNo, @PathVariable Long exchangeAccountNo) {
        throw new RuntimeException("Test Exception");
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        return String.format("{\"marketAccount\":{\"no\":%d, \"currency\":%s}, \"exchangeAccount\":{\"no\":%d, \"currency\":%s}, \"time\":\"%s\"}}"
//                ,marketAccountNo
//                ,getCurrency(marketAccountNo)
//                ,exchangeAccountNo
//                ,getCurrency(exchangeAccountNo)
//                ,System.currentTimeMillis()
//        );
    }

    private String getCurrency(Long accountNo) {
        if(accountNo % 2 == 0) {
            return "BTC";
        } else {
            return "ETH";
        }
    }
}
