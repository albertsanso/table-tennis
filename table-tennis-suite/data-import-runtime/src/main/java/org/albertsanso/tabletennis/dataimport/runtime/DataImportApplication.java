package org.albertsanso.tabletennis.dataimport.runtime;

import org.albertsanso.tabletennis.dataimport.Importer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.inject.Inject;

@SpringBootApplication(scanBasePackages = { "org.albertsanso.tabletennis" })
public class DataImportApplication implements CommandLineRunner {

    @Inject
    Importer importer;

    public static void main(String[] args) {
        SpringApplication.run(DataImportApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        try {
            //importer.doImportOrganizations();
            //importer.doTests();

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
