package spring.hw1.Frosting;

import org.springframework.stereotype.Component;

@Component
public class ChocolateFrosting implements Frosting{

    @Override
    public String getFrostingType()
    {
        return"Chocolate Frosting";
    }
}
