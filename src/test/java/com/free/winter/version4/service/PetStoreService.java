package com.free.winter.version4.service;

import com.free.winter.beans.factory.annotation.Autowired;
import com.free.winter.stereotype.Component;
import com.free.winter.version4.dao.AccountDAO;
import com.free.winter.version4.dao.ItemDAO;

/**
 * @author weimin02
 * @date 2018/8/14
 * @project winter
 */
@Component(value = "petStore")
public class PetStoreService {
    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private ItemDAO itemDAO;

    public AccountDAO getAccountDAO() {
        return accountDAO;
    }

    public ItemDAO getItemDAO() {
        return itemDAO;
    }
}
