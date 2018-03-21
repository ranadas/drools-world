package com.rdas;

import com.rdas.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class KieDroolsMain implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(KieDroolsMain.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        //Get the session named kseesion-rule that we defined in kmodule.xml above.
        //Also by default the session returned is always stateful.
        KieSession kSession = kContainer.newKieSession("ksession-rule");

        Product goldProduct = Product.builder().type("gold").build();

        FactHandle factHandle = kSession.insert(goldProduct);
        kSession.fireAllRules();

        //System.out.println("The discount for the jewellery product " + product.getType() + " is " + product.getDiscount());
        log.info("\nThe discount for the Product type {} is {}.\n", goldProduct.getType() , goldProduct.getDiscount());
    }

}