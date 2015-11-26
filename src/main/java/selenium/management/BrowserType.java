package selenium.management;

import java.util.HashMap;
import java.util.Map;


public enum BrowserType {
    FIREFOX,
    IE,
    CHROME,
    OPERA;

    private static Map<String, BrowserType> browsersMap = new HashMap<String, BrowserType>();

    static {
        browsersMap.put("firefox", BrowserType.FIREFOX);
        browsersMap.put("ie", BrowserType.IE);
        browsersMap.put("chrome", BrowserType.CHROME);
        browsersMap.put("opera", BrowserType.OPERA);
    }

    public static BrowserType browser(String name) {
        return browsersMap.get(name.toLowerCase().trim());
    }
}
