package ua.edu.ukma.event_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("manage")
public class ManagingController {

    private CacheManager cacheManager;

    @Autowired
    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @GetMapping
    public String getManagingPage(){
        return "manager_chooser";
    }

    @PostMapping("/cache")
    public String cleanBuildingCache(){
        cacheManager.getCache("building").clear();
        cacheManager.getCache("buildings").clear();
        return "manager_chooser";
    }
}
