package com.kabi.code.stocktrading.service;

import java.util.Set;

import com.kabi.code.stocktrading.dao.WatchListDAO;
import com.kabi.code.stocktrading.model.Stock;
import com.kabi.code.stocktrading.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WatchListService {
    @Autowired
    private WatchListDAO watchListDAO;

    public User getProfileAttributes(String email) {
        return this.watchListDAO.getProfileAttributes(email);
    }

    public boolean addStockToWatchList(User user, String stockSymbol) {
        return this.watchListDAO.addStockToWatchList(user, stockSymbol);
    }

    public Set<Stock> retrieveWatchList(User user)
    {
        return this.watchListDAO.retrieveWatchList(user);
    }
  


}