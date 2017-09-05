package org.albertsanso.tabletennis.dataretrieval.runtime;

import org.albertsanso.tabletennis.dataretrieval.scrapping.rfetm.OrganizationRetriever;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.inject.Inject;

@SpringBootApplication(scanBasePackages = { "org.albertsanso.tabletennis" })
public class DataRetrievalApplication implements CommandLineRunner {

    @Inject
    OrganizationRetriever organizationRetriever;

    public static void main(String[] args) {
        SpringApplication.run(DataRetrievalApplication.class, args).close();
    }

    @Override
    public void run(String... strings) throws Exception {

        organizationRetriever.retrieveOrganizations();
        System.out.println("DONE.");
    }
}