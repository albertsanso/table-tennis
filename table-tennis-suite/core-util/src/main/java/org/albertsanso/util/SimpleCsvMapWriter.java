package org.albertsanso.util;

import com.opencsv.CSVWriter;

import javax.inject.Named;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@Named
public class SimpleCsvMapWriter {

    private File csvFile;
    private CSVWriter writer;

    public SimpleCsvMapWriter() {}

    public void setCsvFile(File csvFile) throws IOException {
        this.csvFile = csvFile;
        this.writer = new CSVWriter(new FileWriter(csvFile), ',', '"', '"', "\n");
    }

    public void writeValues(Map<String, String> dataRow) throws IOException {
        Object[] objArr = dataRow.values().toArray();
        String[] strArr = Arrays.asList(objArr).toArray(new String[objArr.length]);
        writeData(strArr);
    }

    public void writeHeader(Map<String, String> data) throws IOException {
        Object[] objArr = data.keySet().toArray();
        String[] strArr = Arrays.asList(objArr).toArray(new String[objArr.length]);
        writeData(strArr);
    }

    private void writeData(String[] values) throws IOException {
        this.writer.writeNext(values);
        this.writer.flush();
    }

    public void close() throws IOException {
        this.writer.close();
    }
}
