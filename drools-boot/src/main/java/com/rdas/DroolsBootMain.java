package com.rdas;

import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootApplication
public class DroolsBootMain implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DroolsBootMain.class, args);
    }

    @Bean
    public KieContainer kieContainer() {
        KieServices kieServices = KieServices.Factory.get();
        return kieServices.getKieClasspathContainer();
    }


    @Autowired
    private KieContainer kieContainer;

    @Override
    public void run(String... args) throws Exception {
        StatelessKieSession kSession = kieContainer.newStatelessKieSession();

        Applicant youngApplicant = new Applicant( "John Smith Jr", 16 );
        Applicant adultApplicant = new Applicant("Mr John Smith", 30);

        List<Applicant> applicants = Arrays.asList(youngApplicant, adultApplicant);

        System.out.println( "before: " + applicants );

        kSession.execute(applicants);

        System.out.println( "after: " + applicants );
    }
}