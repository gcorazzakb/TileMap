package company.Spring;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
class CoolCarController {

    @GetMapping("/cool-cars")
    public Collection<String> coolCars() {
        return Arrays.asList(new String[]{"Tada", "Todo"});
    }

    private boolean isCool(String car) {
        return !car.equals("AMC Gremlin") &&
                !car.equals("Triumph Stag");
    }
}
