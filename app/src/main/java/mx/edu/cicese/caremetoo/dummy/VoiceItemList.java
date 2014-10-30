package mx.edu.cicese.caremetoo.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class VoiceItemList {

    /**
     * An array of sample (dummy) items.
     */
    public static List<VoiceItem> ITEMS = new ArrayList<VoiceItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, VoiceItem> ITEM_MAP = new HashMap<String, VoiceItem>();

    static {
        // Add 3 sample items.
        addItem(new VoiceItem("1", "Item 1"));
        addItem(new VoiceItem("2", "Item 2"));
        addItem(new VoiceItem("3", "Item 3"));
    }

    private static void addItem(VoiceItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class VoiceItem {
        public String id;
        public String content;

        public VoiceItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
