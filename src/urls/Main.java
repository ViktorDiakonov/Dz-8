package urls;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<String> fullSite = Urls.readingAndAddUrls();

        Urls.cuttingSiteName(fullSite);

        HashMap<String, Integer> domainRepeat = Urls.calcRepeatDomains(fullSite);

        Urls.print10Urls(domainRepeat);
    }
}
