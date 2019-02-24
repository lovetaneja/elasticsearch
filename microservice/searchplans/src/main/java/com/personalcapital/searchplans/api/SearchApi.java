package com.personalcapital.searchplans.api;

import com.personalcapital.searchplans.exception.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author love_taneja
 * This is an interface for searchPlans API
 */
@FunctionalInterface
public interface SearchApi {
    @RequestMapping(value = "/plans", produces = {"application/json"}, method = RequestMethod.GET)
    ResponseEntity<List> searchPlans(@RequestParam(value = "planName", required = false) String planName, @RequestParam(value = "sponserName", required = false) String sponserName, @RequestParam(value = "sponserState", required = false) String sponserState) throws ApiException;
}