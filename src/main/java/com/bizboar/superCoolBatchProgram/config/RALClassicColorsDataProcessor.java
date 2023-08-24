package com.bizboar.superCoolBatchProgram.config;

import com.bizboar.superCoolBatchProgram.domain.RALClassicColors;
import org.springframework.batch.item.ItemProcessor;

public class RALClassicColorsDataProcessor implements ItemProcessor<RALClassicColors, RALClassicColors> {

    @Override
    public RALClassicColors process(RALClassicColors item) throws Exception {
        String processedColorName = processColorName(item.getEnglish());
        item.setEnglish(processedColorName);
        item.setFeatured(false);
        item.setLrvCategory(processLRVCategory(item.getLrv()));
        return item;
    }

    private String processColorName(String originalColorName) {
        return originalColorName.toLowerCase();
    }

    private String processLRVCategory(Double lrv) {

        if (lrv >= 70.0) {
            return "High";
        } else if (lrv >= 50.0) {
            return "Medium";
        } else {
            return "Low";
        }
    }

}
