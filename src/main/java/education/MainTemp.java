package education;

import java.util.*;

public class MainTemp {

    public static void main(String[] args) {
        List<Apple> list = new ArrayList<>(){{add(new Apple(1)); add(new Apple(2));
            add(new Apple(8)); add(new Apple(4));
            add(new Apple(3)); add(new Apple(7));}};
        list.forEach(System.out::println);
        /*Collections.sort(list, new Comparator<Apple>(){
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });*/

        list.sort(Comparator.comparing(Apple::getWeight));

        list.forEach(System.out::println);

        //
        /*final List<String> list = new ArrayList() {{add("1"); add("2"); add("3");}};

        for (Iterator<String> iter = list.iterator(); iter.hasNext();){
            if (iter.next().equals("2")) {iter.remove();}
        }

        System.out.println(list.size());*/
    }
}
