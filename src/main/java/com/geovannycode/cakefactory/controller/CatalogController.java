package com.geovannycode.cakefactory.controller;

import com.geovannycode.cakefactory.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService){
        this.catalogService = catalogService;
    }

    @GetMapping("/")
    ModelAndView index(){
        return new ModelAndView("catalog", Map.of("items", this.catalogService.getItems()));
    }
}
