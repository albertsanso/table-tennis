package org.albertsanso.tabletennis.dataretrieval.runtime;

public class RetrievalEntityConfiguration {

    private String entity;
    private String outputCsv;
    private String urls;
    private String seasons;

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getOutputCsv() {
        return outputCsv;
    }

    public void setOutputCsv(String outputCsv) {
        this.outputCsv = outputCsv;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public String getSeasons() {
        return seasons;
    }

    public void setSeasons(String seasons) {
        this.seasons = seasons;
    }
}
