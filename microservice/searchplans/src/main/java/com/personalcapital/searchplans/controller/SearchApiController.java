package com.personalcapital.searchplans.controller;

import com.personalcapital.searchplans.api.SearchApi;
import com.personalcapital.searchplans.exception.ApiException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @author love_taneja
 * This is a controller class for searchPlans API
 */
@Controller
public class SearchApiController implements SearchApi {

    private static final Logger log = LogManager.getLogger(SearchApiController.class);

    @Override
    public ResponseEntity<List> searchPlans(@RequestParam(value = "planName", required = false) String planName, @RequestParam(value = "sponserName", required = false) String sponserName, @RequestParam(value = "sponserState", required = false) String sponserState) throws ApiException{
        if (log.isDebugEnabled()){
            log.debug("planName " + planName);
            log.debug("sponserName " + sponserName);
            log.debug("sponserState " + sponserState);
        }
        List list = new ArrayList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
