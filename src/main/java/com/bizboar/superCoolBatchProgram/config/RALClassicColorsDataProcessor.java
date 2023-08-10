package com.bizboar.superCoolBatchProgram.config;

import com.bizboar.superCoolBatchProgram.domain.RALClassicColors;
import org.springframework.batch.item.ItemProcessor;

public class RALClassicColorsDataProcessor implements ItemProcessor<RALClassicColors, RALClassicColors> {

    @Override
    public RALClassicColors process(RALClassicColors item) throws Exception {
        String processedColorName = processColorName(item.getEnglish());
        item.setEnglish(processedColorName);

//        double processedLRVCategory = Double.parseDouble(processLRVCategory(item.getLrv()));
//        item.setLrvCategory(String.valueOf(processedLRVCategory));
        return item;
    }

    private String processColorName(String originalColorName) {
        return originalColorName.toLowerCase();
    }

//    private String processLRVCategory(String lrv) {
//        double lrvValue = Double.parseDouble(lrv);
//
//        if (lrvValue >= 70.0) {
//            return "High";
//        } else if (lrvValue >= 50.0) {
//            return "Medium";
//        } else {
//            return "Low";
//        }
//    }

}
