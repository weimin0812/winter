package com.free.winter.version2;

import com.free.winter.version2.dao.AccountDAO;
import com.free.winter.version2.dao.ItemDAO;

/**
 * @author weimin02
 * @date 2018/7/28
 * @project winter
 */
public class PetStoreService {
    private AccountDAO accountDAO;
    private ItemDAO itemDAO;
    private String owner;
    private int version;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public AccountDAO getAccountDAO() {
        return accountDAO;
    }

    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public ItemDAO getItemDAO() {
        return itemDAO;
    }

    public void setItemDAO(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }
}
