package spring.hw1.Syrup;

import org.springframework.stereotype.Component;

@Component
public class StrawberrySyrup implements Syrup {

    @Override
    public String getSyrupType() {
        return "Strawberry Syrup";
    }
}
