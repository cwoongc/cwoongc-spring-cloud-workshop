package com.wcchoi.workshop.springcloud.order.controller;

import com.wcchoi.workshop.springcloud.order.service.AccountRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired private AccountRemoteService accountRemoteService;

    @RequestMapping(path="/{orderNo}/accounts", method = RequestMethod.GET)
    public String getOrderAccounts(@PathVariable Long orderNo) {
        String accounts = accountRemoteService.getOrderAccounts(orderNo);

        return String.format("[%s]\"orderNo\":%d, \"accounts\": %s", System.currentTimeMillis(),orderNo, accounts);
    }

}
