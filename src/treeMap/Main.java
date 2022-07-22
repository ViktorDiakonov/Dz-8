package treeMap;

public class Main {
    public static void main(String[] args) {
        TreeMap<Integer, String> newTreeMap = new TreeMap<>();
        newTreeMap.put(1, "one");
        newTreeMap.put(4, "four");
        newTreeMap.put(3, "three");
        newTreeMap.put(2, "two");
        newTreeMap.put(5, "five");
        System.out.println("Проверяю наличие ключа 4: " + newTreeMap.containsKey(4));
        System.out.println("Размер Map: " + newTreeMap.size());
        System.out.println("Вывод значения по ключу 2: " + newTreeMap.get(2));

        System.out.print("Вывод значений в консоль: ");
        for (int i = 1; i < newTreeMap.size() + 1; i++) {
            System.out.print(newTreeMap.get(i) + " ");
        }
    }
}
