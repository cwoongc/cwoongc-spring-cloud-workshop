package com.wcchoi.workshop.springcloud.order.controller;

import com.wcchoi.workshop.springcloud.order.client.AccountRemoteClient;
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

    @Autowired private AccountRemoteClient accountRemoteClient;

    @RequestMapping(path="/{orderNo}/accounts", method = RequestMethod.GET)
    public String getOrderAccounts(@PathVariable Long orderNo) {

        Long[] accountsNo = getOrderAccountsNo(orderNo);
//        String accounts = accountRemoteService.getOrderAccounts(accountsNo[0], accountsNo[1]);
        String accounts = accountRemoteClient.getOrderAccounts(accountsNo[0], accountsNo[1]);

        return String.format("[%s]\"orderNo\":%d, \"accounts\": %s", System.currentTimeMillis(),orderNo, accounts);
    }

    private Long[] getOrderAccountsNo(Long orderNo) {

        Long[] accountsNo = {12345L, 67890L};

        return accountsNo;
    }

}
