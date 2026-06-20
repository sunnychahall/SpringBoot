package spring.hw1;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import spring.hw1.Frosting.Frosting;
import spring.hw1.Syrup.Syrup;

@Component
public class CakeBaker {

    public Frosting frosting;
    public Syrup syrup;



            public CakeBaker(
                    @Qualifier("chocolateFrosting") Frosting frosting,
            @Qualifier("strawberrySyrup") Syrup syrup) {

        this.frosting = frosting;
        this.syrup = syrup;
    }

        public void bakeCake() {
        System.out.println("Cake is being baked with "
                + frosting.getFrostingType()
                + " and "
                + syrup.getSyrupType());
    }

}
