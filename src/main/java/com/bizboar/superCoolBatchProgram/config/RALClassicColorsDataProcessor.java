package com.bizboar.superCoolBatchProgram.config;

import com.bizboar.superCoolBatchProgram.domain.RALClassicColors;
import org.springframework.batch.item.ItemProcessor;

public class RALClassicColorsDataProcessor implements ItemProcessor<RALClassicColors, RALClassicColors> {

//    @Override
//    public RALClassicColors process(RALClassicColors item) throws Exception {
//        return null;
//    }
    @Override
    public RALClassicColors process(RALClassicColors item) throws Exception {
        // Perform processing logic
        String originalColorName = item.getEnglish();
        String uppercaseColorName = originalColorName.toUpperCase();
        String originalHex = item.getHex();


        // Create a new RALClassicColors instance with the processed color name
        RALClassicColors processedItem = new RALClassicColors();
        processedItem.setRal(item.getRal());
        processedItem.setEnglish(uppercaseColorName);
        processedItem.setHex(originalHex);

        return processedItem;
    }



//    @Override
//    public RALClassicColors process(RALClassicColors RALClassicColors) throws Exception {
//        if (RALClassicColors.getRal().equals("1000")) {
//            return RALClassicColors;
//        } else {
//            return null;
//        }
//
//    }
}
