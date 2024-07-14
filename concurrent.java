
    import java.util.ArrayList;
    import java.util.ConcurrentModificationException;
    import java.util.Iterator;
import java.util.List;

    public class concurrent {
        public static void main(String[] args) {
            List<String> fruits = new ArrayList<>();
            fruits.add("Apple");
            fruits.add("Banana");
            fruits.add("Cherry");
            fruits.add("Date");

            // This will throw ConcurrentModificationException
            try {
                for (String fruit : fruits) {
                    if (fruit.equals("Banana")) {
                        fruits.remove(fruit);
                    }
                }
            } catch (ConcurrentModificationException e) {
                System.out.println("Caught ConcurrentModificationException: " + e.getMessage());
            }

            // Proper way to remove elements using iterator
            Iterator<String> iterator = fruits.iterator();
            while (iterator.hasNext()) {
                String fruit = iterator.next();
                if (fruit.equals("Cherry")) {
                    iterator.remove();
                }
            }

            // Print the remaining elements in the list
            System.out.println("Remaining fruits: " + fruits);
        }
    }


