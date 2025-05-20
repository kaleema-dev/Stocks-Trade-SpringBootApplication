package com.kabi.code.stocktrading.service;

import com.kabi.code.stocktrading.dao.UserAccountDAO;
import com.kabi.code.stocktrading.model.User;
import com.kabi.code.stocktrading.model.UserBankDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService
{
    @Autowired
    private UserAccountDAO userAccountDAO;

    public User getProfileAttributes (String email)
    {
        return this.userAccountDAO.getProfileAttributes(email);
    }

    public User updateProfile (String  email , String phoneNumber)
    {
        return this.userAccountDAO.updateProfileAttributes(email , phoneNumber);
    }

    public User updateBankDetails (User user, UserBankDetails userbank)
    {
        return this.userAccountDAO.updateBankDetails(user , userbank);
    }

}