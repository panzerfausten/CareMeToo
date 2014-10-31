package mx.edu.cicese.caremetoo.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mx.edu.cicese.caremetoo.config.CareMeTooConfig;
import mx.edu.cicese.caremetoo.dummy.VoiceItemList;

/**
 * Created by dmiranda on 10/31/14.
 */
public class VoiceLogsRetreiver {
    public static List<VoiceItemList.VoiceItem> retreiveVoiceItemsList(){
        List<VoiceItemList.VoiceItem> itemsToReturn = new ArrayList<VoiceItemList.VoiceItem>();


        //readfolder
        File files = new File(CareMeTooConfig.externalVoiceLogsPath);
        File[] filteredFiles = files.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.getAbsolutePath().contains(".m4a")) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        if(filteredFiles != null) {
            for (File f : filteredFiles) {
                try {
                    VoiceItemList.VoiceItem vi = new VoiceItemList.VoiceItem(f.getAbsolutePath(), f.getCanonicalPath());
                    itemsToReturn.add(vi);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return itemsToReturn;
    }
}
