package ua.edu.ukma.event_management_system.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

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
        Objects.requireNonNull(cacheManager.getCache("building")).clear();
        Objects.requireNonNull(cacheManager.getCache("buildings")).clear();
        return "manager_chooser";
    }
}
