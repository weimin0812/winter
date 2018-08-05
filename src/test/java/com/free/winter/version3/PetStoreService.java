package com.free.winter.version3;

import com.free.winter.version3.dao.AccountDAO;
import com.free.winter.version3.dao.ItemDAO;

/**
 * @author weimin02
 * @date 2018/8/3
 * @project winter
 */
public class PetStoreService {
    private AccountDAO accountDAO;
    private ItemDAO itemDAO;
    private int version;

    public PetStoreService(AccountDAO accountDAO, ItemDAO itemDAO, int version) {
        this.accountDAO = accountDAO;
        this.itemDAO = itemDAO;
        this.version = version;
    }

    public PetStoreService(AccountDAO accountDAO, ItemDAO itemDAO) {
        this.accountDAO = accountDAO;
        this.itemDAO = itemDAO;
        this.version = -1;
    }

    public AccountDAO getAccountDAO() {
        return accountDAO;
    }

    public ItemDAO getItemDAO() {
        return itemDAO;
    }

    public int getVersion() {
        return version;
    }
}
