package org.albertsanso.util;

import com.opencsv.CSVReader;

import javax.inject.Named;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
public class SimpleCsvMapReader {

    private File csvFile;
    private CSVReader reader;
    private String[] header;
    private List<Map<String, String>> lines;

    public void setCsvFile(File csvFile) throws IOException {
        this.csvFile = csvFile;
        this.reader = new CSVReader(new FileReader(csvFile));
    }

    public void read() throws IOException {
        this.lines = new ArrayList<Map<String, String>>();

        List<String[]> allLines = this.reader.readAll();
        if (allLines != null && allLines.size()>0) {
            this.header = allLines.get(0);
            for (int i=1; i<allLines.size()-1; i++) {
                Map<String, String> map = new HashMap<String, String>();
                String[] line = allLines.get(i);
                if (!"".equals(line)){
                    for (int j = 0; j < this.header.length; j++) {
                        map.put(this.header[j], line[j]);
                    }
                }
                this.lines.add(map);
            }
        }
    }

    public String[] getHeader() {
        return header;
    }

    public List<Map<String, String>> getLines() {
        return lines;
    }

    public static void main(String[] args) throws Throwable{

        SimpleCsvMapReader r = new SimpleCsvMapReader();
        r.setCsvFile(new File("c:/tmp/orgs_data_1.csv"));
        r.read();

        List<Map<String, String>> os = r.getLines();

    }
}
