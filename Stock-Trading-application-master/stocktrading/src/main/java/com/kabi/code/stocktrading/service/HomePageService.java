package com.kabi.code.stocktrading.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kabi.code.stocktrading.dao.HomePageDAO;
import com.kabi.code.stocktrading.model.Stock;
import com.kabi.code.stocktrading.model.StockAPIBean;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.sf.json.JSONSerializer;

@Service
public class HomePageService
{
    @Autowired
    private HomePageDAO homePageDAO;

    private static final Logger logger = LoggerFactory.getLogger(HomePageService.class);

    @Autowired
    private RestTemplate restTemplate;

    // final String stockUri = "https://marketdata.websol.barchart.com/getQuote.json?apikey=b0ccbbb0dae9d39ce81f65718f0ecd10&symbols=AMZN,GOOG,AAPL,GOOG,NFLX,TSLA,FB,CSCO,ORCL,INTC,QCOM,EBAY,DELL,COST,MSFT,TWTR,AABA,SNAP,AMD,ATVI,ZNGA,WDC,BKNG&fields=fiftyTwoWkHigh%2CfiftyTwoWkHighDate%2CfiftyTwoWkLow%2CfiftyTwoWkLowDate";
    final String stockUri1 = "https://marketdata.websol.barchart.com/getQuote.json?apikey=b0ccbbb0dae9d39ce81f65718f0ecd10&symbols=AMZN,GOOG,AAPL,NFLX,TSLA,FB,CSCO,ORCL,INTC,QCOM,EBAY,DELL,COST,MSFT,TWTR,AABA,SNAP,AMD,ATVI,ZNGA&fields=fiftyTwoWkHigh%2CfiftyTwoWkHighDate%2CfiftyTwoWkLow%2CfiftyTwoWkLowDate";

    final String stockUri2 = "https://marketdata.websol.barchart.com/getQuote.json?apikey=b0ccbbb0dae9d39ce81f65718f0ecd10&symbols=WDC,BKNG,VZ,HPQ,SNE,W,BABA,JNJ,JPM,XOM,BAC,WMT,WFC,V,PG,BUD,T,UNH,HD,C&fields=fiftyTwoWkHigh%2CfiftyTwoWkHighDate%2CfiftyTwoWkLow%2CfiftyTwoWkLowDate";



    final String currencyUri = "https://www.worldtradingdata.com/api/v1/forex?base=USD&sort=newest&api_token=uRqeU8C651htqmF8iI5N6VvSBEIpTiQ5xvvKkz9Slsm1D9jFvIGdG97m0RpF";

    public List<StockAPIBean> getTopStocks ()
    {
        List<StockAPIBean> stocks = new ArrayList<StockAPIBean>();
        System.out.println("The given URL is ----1-->"+stockUri1);
        //String result = this.restTemplate.getForObject(stockUri1, String.class);
        String result ="{\n" +
                "    \"status\": {\n" +
                "        \"code\": 200,\n" +
                "        \"message\": \"Success.\"\n" +
                "    },\n" +
                "    \"results\": [\n" +
                "        {\n" +
                "            \"symbol\": \"AAPL\",\n" +
                "\t\t\t\"exchange\": \"No\",\n" +
                "            \"name\": \"Apple Inc\",\n" +
                "            \"dayCode\": \"F\",\n" +
                "            \"serverTimestamp\": \"2025-05-17T11:35:00-05:00\",\n" +
                "            \"mode\": \"i\",\n" +
                "            \"lastPrice\": 211.26,\n" +
                "            \"tradeTimestamp\": \"2025-05-16T16:15:00-05:00\",\n" +
                "            \"netChange\": -0.19,\n" +
                "            \"percentChange\": -0.09,\n" +
                "            \"unitCode\": \"2\",\n" +
                "            \"open\": 212.36,\n" +
                "            \"high\": 212.57,\n" +
                "            \"low\": 209.77,\n" +
                "            \"flag\": \"s\",\n" +
                "            \"volume\": 54737800,\n" +
                "            \"fiftyTwoWkHigh\": 260.1,\n" +
                "            \"fiftyTwoWkHighDate\": \"2024-12-26\",\n" +
                "            \"fiftyTwoWkLow\": 169.21,\n" +
                "            \"fiftyTwoWkLowDate\": \"2025-04-08\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"symbol\": \"GOOG\",\n" +
                "\t\t\t\"exchange\": \"Yes\",\n" +
                "            \"name\": \"Alphabet Cl C\",\n" +
                "            \"dayCode\": \"F\",\n" +
                "            \"serverTimestamp\": \"2025-05-17T11:35:00-05:00\",\n" +
                "            \"mode\": \"i\",\n" +
                "            \"lastPrice\": 167.43,\n" +
                "            \"tradeTimestamp\": \"2025-05-16T16:00:00-05:00\",\n" +
                "            \"netChange\": 2.03,\n" +
                "            \"percentChange\": 1.23,\n" +
                "            \"unitCode\": \"2\",\n" +
                "            \"open\": 168.93,\n" +
                "            \"high\": 170.65,\n" +
                "            \"low\": 166.95,                        \n" +
                "            \"flag\": \"s\",\n" +
                "            \"volume\": 36271300,            \n" +
                "            \"fiftyTwoWkHigh\": 208.7,\n" +
                "            \"fiftyTwoWkHighDate\": \"2025-02-04\",\n" +
                "            \"fiftyTwoWkLow\": 142.66,\n" +
                "            \"fiftyTwoWkLowDate\": \"2025-04-07\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        System.out.println("The given URL is -----2->"+result);
        Object obj = null;
        JSONObject jo = null;
        try {
            obj = new JSONParser().parse(result);
            System.out.println("The given URL is ----3-->"+obj);
            jo = (JSONObject) obj;
            System.out.println("The given URL is ----4-->"+obj);
        } catch (ParseException e) {
            System.out.println("The given URL is ----5-->"+e.getMessage());
            logger.error(e.toString());
            obj = null;
            jo = null;
        }

        if (obj != null && jo != null) {
            JSONArray ja = (JSONArray) jo.get("results");
            System.out.println("The given URL is ----6-->"+ja);
            Iterator itr2 = ja.iterator();
            System.out.println("The given URL is ----7-->"+itr2.next());
            while (itr2.hasNext()) {

                Map map = (Map) itr2.next();
                ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
                StockAPIBean pojo = mapper.convertValue(map, StockAPIBean.class);
                System.out.println("The given URL is ----8-->"+pojo);
                stocks.add(pojo);
            }
        }

        //result = this.restTemplate.getForObject(stockUri2, String.class);
       /* result ="{\n" +
                "    \"status\": {\n" +
                "        \"code\": 200,\n" +
                "        \"message\": \"Success.\"\n" +
                "    },\n" +
                "    \"results\": [\n" +
                "        {\n" +
                "            \"symbol\": \"AAPL\",\n" +
                "\t\t\t\"exchange\": \"No\",\n" +
                "            \"name\": \"Apple Inc\",\n" +
                "            \"dayCode\": \"F\",\n" +
                "            \"serverTimestamp\": \"2025-05-17T11:35:00-05:00\",\n" +
                "            \"mode\": \"i\",\n" +
                "            \"lastPrice\": 211.26,\n" +
                "            \"tradeTimestamp\": \"2025-05-16T16:15:00-05:00\",\n" +
                "            \"netChange\": -0.19,\n" +
                "            \"percentChange\": -0.09,\n" +
                "            \"unitCode\": \"2\",\n" +
                "            \"open\": 212.36,\n" +
                "            \"high\": 212.57,\n" +
                "            \"low\": 209.77,\n" +
                "            \"flag\": \"s\",\n" +
                "            \"volume\": 54737800,\n" +
                "            \"fiftyTwoWkHigh\": 260.1,\n" +
                "            \"fiftyTwoWkHighDate\": \"2024-12-26\",\n" +
                "            \"fiftyTwoWkLow\": 169.21,\n" +
                "            \"fiftyTwoWkLowDate\": \"2025-04-08\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"symbol\": \"GOOG\",\n" +
                "\t\t\t\"exchange\": \"Yes\",\n" +
                "            \"name\": \"Alphabet Cl C\",\n" +
                "            \"dayCode\": \"F\",\n" +
                "            \"serverTimestamp\": \"2025-05-17T11:35:00-05:00\",\n" +
                "            \"mode\": \"i\",\n" +
                "            \"lastPrice\": 167.43,\n" +
                "            \"tradeTimestamp\": \"2025-05-16T16:00:00-05:00\",\n" +
                "            \"netChange\": 2.03,\n" +
                "            \"percentChange\": 1.23,\n" +
                "            \"unitCode\": \"2\",\n" +
                "            \"open\": 168.93,\n" +
                "            \"high\": 170.65,\n" +
                "            \"low\": 166.95,                        \n" +
                "            \"flag\": \"s\",\n" +
                "            \"volume\": 36271300,            \n" +
                "            \"fiftyTwoWkHigh\": 208.7,\n" +
                "            \"fiftyTwoWkHighDate\": \"2025-02-04\",\n" +
                "            \"fiftyTwoWkLow\": 142.66,\n" +
                "            \"fiftyTwoWkLowDate\": \"2025-04-07\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        obj = null;
        jo = null;
        try {
            obj = new JSONParser().parse(result);
            jo = (JSONObject) obj;
        } catch (ParseException e) {
            logger.error(e.toString());
            obj = null;
            jo = null;
        }

        if (obj != null && jo != null)
        {
            JSONArray ja = (JSONArray) jo.get("results");

            Iterator itr2 = ja.iterator();

            while (itr2.hasNext()) {

                Map map = (Map) itr2.next();
                ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
                StockAPIBean pojo = mapper.convertValue(map, StockAPIBean.class);
                stocks.add(pojo);
            }
        }*/

        saveAllStocks(stocks);

        logger.info("size::--------------9-->" + stocks.size());
        return stocks;

    }

    public void saveAllStocks(List<StockAPIBean> stocks)
    {

        logger.info("Saving stock object to database");
        for (StockAPIBean stock : stocks) {
            Stock temp = null;
            try {
                temp = this.homePageDAO.checkIfStockExists(stock.getSymbol());
            } catch (Exception e) {
                temp = null;
            }
            if (temp == null)
            {
                //this.homePageDAO.saveStock(stock);
            }
            else {
                this.homePageDAO.updateStock(stock, temp.getId());

            }

        }
        logger.info("Done updating stock table");

    }

    public Map<String , String> getTopCurrencies ()
    {
        String result = this.restTemplate.getForObject(currencyUri, String.class);

        net.sf.json.JSONObject json = (net.sf.json.JSONObject) JSONSerializer.toJSON( result );
        net.sf.json.JSONObject data = json.getJSONObject("data");

        Map<String,String> map = new HashMap<String,String>();
        Iterator iter = data.keys();

        while(iter.hasNext())
        {
            String key = (String)iter.next();
            String value = data.getString(key);
            map.put(key,value);
        }

        Map<String,String> resultMap = new HashMap<String,String>();

        String eur = map.get("EUR");
        String jpy = map.get("JPY");
        String inr = map.get("INR");

        resultMap.put("Euro", eur);
        resultMap.put("Yen", jpy);
        resultMap.put("Rupee", inr);

        return resultMap;

    }



}