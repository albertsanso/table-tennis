package org.albertsanso.tabletennis.dataretrieval.scrapping.rfetm.scratch;

import org.albertsanso.tabletennis.dataretrieval.scrapping.SeasonInfoScratcherImpl;
import org.albertsanso.tabletennis.dataretrieval.scrapping.rfetm.mapper.QualificationMapper;
import org.jsoup.select.Elements;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
public class QualificationsInfoScratcher extends SeasonInfoScratcherImpl {

    private static final String ROOT_SELECTOR = "#table1 > tbody > tr:nth-child(3) > td:nth-child(1) > table > tbody > tr:nth-child(2) > td:nth-child(2) > table:nth-child(4) > tbody > tr:nth-child(1) > td > table > tbody > tr > td:nth-child(4) > table > tbody > tr:nth-child(1) > td > table > tbody > tr > td > strong > h2";

    @Override
    protected String getRootSelector() {
        return ROOT_SELECTOR;
    }

    @Override
    protected List<Map<String, String>> scratchFromRoot(Elements root) {

        String textContent = root.text();
        Map<String, String> info = new HashMap<String, String>();
        info.put(QualificationMapper.GROUP_DESCRIPTION.key, textContent);
        List<Map<String, String>> infos = new ArrayList<Map<String, String>>();
        infos.add(info);
        return infos;
    }
}
