package spring.hw1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Hw1Application implements CommandLineRunner {

    private final CakeBaker cakeBaker;

    public Hw1Application(CakeBaker cakeBaker) {
        this.cakeBaker = cakeBaker;
    }

    public static void main(String[] args) {
        SpringApplication.run(Hw1Application.class, args);
    }

    @Override
    public void run(String... args) {
        cakeBaker.bakeCake();
    }
}