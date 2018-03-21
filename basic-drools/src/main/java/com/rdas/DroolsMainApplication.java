package com.rdas;

import com.rdas.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.drools.compiler.compiler.DroolsParserException;
import org.drools.compiler.compiler.PackageBuilder;
import org.drools.core.RuleBase;
import org.drools.core.RuleBaseFactory;
import org.drools.core.WorkingMemory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

@Slf4j
@SpringBootApplication
public class DroolsMainApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(DroolsMainApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        executeDrools();
    }

    public void executeDrools() throws DroolsParserException, IOException {

        final PackageBuilder packageBuilder = new PackageBuilder();

        String ruleFile = "/com/rule/Rules.drl";
        InputStream resourceAsStream = getClass().getResourceAsStream(ruleFile);

        Reader reader = new InputStreamReader(resourceAsStream);
        packageBuilder.addPackageFromDrl(reader);
        org.drools.core.rule.Package rulesPackage = packageBuilder.getPackage();
        RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        ruleBase.addPackage(rulesPackage);

        WorkingMemory workingMemory = ruleBase.newStatefulSession();

        Product goldProduct = Product.builder().type("gold").build();


        workingMemory.insert(goldProduct);
        workingMemory.fireAllRules();
        log.info("\nThe discount for the goldProduct {} is {}.\n", goldProduct.getType() , goldProduct.getDiscount());
    }

}