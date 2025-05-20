package com.kabi.code.stocktrading.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.kabi.code.stocktrading.model.StockAPIBean;
import com.kabi.code.stocktrading.service.HomePageService;
import com.kabi.code.stocktrading.util.SessionManagementUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageController {
    private static final Logger logger = LoggerFactory.getLogger(HomePageController.class);

    @Autowired
    private HomePageService homePageService;

    @Autowired
    private SessionManagementUtil sessionMgmtUtils;

    @GetMapping(value = "/")
    public ModelAndView home(HttpServletRequest request) {
        System.out.println("Inside homepage----------1-->");
       /* if (!this.sessionMgmtUtils.doesSessionExist(request))
        {

            logger.info("Session does not exist. Redirect to login page");
            ModelAndView mv = new ModelAndView("landing-page");
            return mv;
        }*/
        ModelAndView mv = new ModelAndView("home");

        try {
            // System.out.println("Inside homepage----------3-->"+request.getSession().getAttribute("isAdmin").toString());

            // if (request.getSession().getAttribute("isAdmin").equals("Yes"))
            // {
            System.out.println("Inside homepage----------2-->");
            List<StockAPIBean> stocks = this.homePageService.getTopStocks();

            mv.addObject("stocks", stocks);

            ArrayList<String> logos = new ArrayList<String>();
            logos.add("//logo.clearbit.com/amazon.com");
            logos.add("//logo.clearbit.com/google.com");
            logos.add("//logo.clearbit.com/apple.com");
            logos.add("//logo.clearbit.com/netflix.com");

            logos.add("//logo.clearbit.com/tesla.com");
            logos.add("//logo.clearbit.com/facebook.com");
            logos.add("//logo.clearbit.com/cisco.com?size=120");
            logos.add("//logo.clearbit.com/oracle.com");

            logos.add("//logo.clearbit.com/intel.com");
            logos.add("//logo.clearbit.com/qualcomm.com");
            logos.add("//logo.clearbit.com/ebay.com");
            logos.add("//logo.clearbit.com/dell.com?size=100");
            mv.addObject("logos", logos);

               /* Map<String, String> currencies = new HashMap<String , String> ();
                currencies = this.homePageService.getTopCurrencies();
                mv.addObject("currencies", currencies);*/

            System.out.println("Inside homepage----------3-->");
           // return mv;

            // }

        } catch (Exception e) {
            System.out.println("Inside homepage----------4-->" + e.getMessage());
            logger.error(e.toString());
        }


         /* System.out.println("Inside homepage----------5-->");
        List<StockAPIBean> stocks = null;
        try {
            stocks = this.homePageService.getTopStocks();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
      ModelAndView mv = new ModelAndView("home");
        mv.addObject("stocks", stocks);

        ArrayList<String> logos = new ArrayList<String>();
        logos.add("//logo.clearbit.com/amazon.com");
        logos.add("//logo.clearbit.com/google.com");
        logos.add("//logo.clearbit.com/apple.com");
        logos.add("//logo.clearbit.com/netflix.com");

        logos.add("//logo.clearbit.com/tesla.com");
        logos.add("//logo.clearbit.com/facebook.com");
        logos.add("//logo.clearbit.com/cisco.com?size=120");
        logos.add("//logo.clearbit.com/oracle.com");

        logos.add("//logo.clearbit.com/intel.com");
        logos.add("//logo.clearbit.com/qualcomm.com");
        logos.add("//logo.clearbit.com/ebay.com");
        logos.add("//logo.clearbit.com/dell.com?size=100");
        mv.addObject("logos", logos);*/


      /* Map<String, String> currencies = new HashMap<String , String> ();
        currencies = this.homePageService.getTopCurrencies();
        mv.addObject("currencies", currencies);
        System.out.println("Inside homepage----------6-->");
        return mv;*/
        return mv;

    }

}