package io.getboosted.livecoding.backend.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {
    @GetMapping("/")
    public Map<String, String> health() {
        var res = new HashMap<String, String>();
        res.put("status", "ok");
        res.put("timestamp", new Date().toString());
        return res;
    }
}