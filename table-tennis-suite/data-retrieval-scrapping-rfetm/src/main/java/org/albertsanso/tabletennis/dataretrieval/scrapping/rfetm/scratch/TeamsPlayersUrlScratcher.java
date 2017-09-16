package org.albertsanso.tabletennis.dataretrieval.scrapping.rfetm.scratch;

import org.albertsanso.tabletennis.dataretrieval.scrapping.SeasonUrlScratcherImpl;
import org.jsoup.select.Elements;

import javax.inject.Named;
import java.util.List;

@Named
public class TeamsPlayersUrlScratcher extends SeasonUrlScratcherImpl {
    @Override
    protected List<String> scratchFromRoot(Elements root) {
        return null;
    }

    @Override
    protected String getRootSelector() {
        return null;
    }
}
