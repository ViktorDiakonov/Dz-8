package urls;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Urls {

    // Здесь считываю сканером файл и использую Exception с описанием.
    public static ArrayList<String> readingAndAddUrls() {
        File file = new File("urls.txt");
        Scanner in = null;
        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("It is not possible to read the file, " +
                    "the path to the file is incorrect or the file is missing!");
        }

        // Здесь добавляю строки из файла "в виде стринги" в List.
        ArrayList<String> fullSite = new ArrayList<>();
        do {
            assert in != null;
            if (!in.hasNextLine()) break;
            fullSite.add(in.nextLine());
        } while (true);
        return fullSite;
    }


    // Здесь разделяю каждую строку на части, используя в качестве разделителя метод split
    // и избавляюсь от "www." и "m.".
    public static void cuttingSiteName(ArrayList<String> fullSite) {
        for (int i = 0; i < fullSite.size(); i++) {
            String shortDomain = fullSite.get(i).split("/")[0];
            if (shortDomain.startsWith("m.")) {
                shortDomain = shortDomain.substring(2);
            } else if (shortDomain.startsWith("www.")) {
                shortDomain = shortDomain.substring(4);
            }
            fullSite.set(i, shortDomain);
        }
    }


    // Здесь я создаю Мар, в которой ключом является домен,
    // а "значением" - количество его упоминаний в файле (для дальнейшей сортировки по "значению").
    public static HashMap<String, Integer> calcRepeatDomains(ArrayList<String> fullSite) {
        HashMap<String, Integer> domainRepeat = new HashMap<>();
        for (String s : fullSite) {
            String key = String.valueOf(s);
            if (!domainRepeat.containsKey(key)) {
                domainRepeat.put(key, 1);
            } else {
                int n = domainRepeat.get(key);
                domainRepeat.replace(key, n + 1);
            }
        }
        return domainRepeat;
    }


    // Здесь производится вывод 10-ти самых часто встречающихся доменов в консоль,
    // основа кода взята на ru.stackoverflow.com
    public static void print10Urls(HashMap<String, Integer> domainRepeat) {
        System.out.println("Top 10 most repeated domains in a file:");
        List<Map.Entry<String, Integer>> toSort = new ArrayList<>(domainRepeat.entrySet());
        toSort.sort(HashMap.Entry.<String, Integer>comparingByValue().reversed());
        long limit = 10;
        for (Map.Entry<String, Integer> stringIntegerEntry : toSort) {
            if (limit-- == 0) break;
            System.out.println(stringIntegerEntry + " iterations");
        }
    }
}

