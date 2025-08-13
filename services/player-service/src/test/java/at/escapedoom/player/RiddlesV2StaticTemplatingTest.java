package at.escapedoom.player;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RiddlesV2StaticTemplatingTest {

    public static void main(String[] args) {
        String code = """
                public class Main {
                
                     public static void main(String[] args) {
                         int result = addition({{a}}, new ComplexType({{e}}, {{f}}));
                         if(result != {{c}})
                             throw new IllegalArgEx;
                     }

                     public static int addition(int a, ComplexType b) {
                         return a + b.a + b.b;
                     }

                     public static class ComplexType {
                         int a;
                         int b;

                         public ComplexType(int a, int b) {
                             this.a = a;
                             this.b = b;
                         }
                     }

                 }
                """;

        List<Map<String, String>> inputs = new ArrayList<>();

        Map<String, String> map1 = new HashMap<>();
        map1.put("a", "1");
        map1.put("c", "6");
        map1.put("e", "2");
        map1.put("f", "3");
        inputs.add(map1);

        Map<String, String> map2 = new HashMap<>();
        map2.put("a", "10");
        map2.put("c", "16");
        map2.put("e", "2");
        map2.put("f", "4");
        inputs.add(map2);

        Map<String, String> map3 = new HashMap<>();
        map3.put("a", "1");
        map3.put("c", "9");
        map3.put("e", "6");
        map3.put("f", "2");
        inputs.add(map3);

        inputs.forEach(map -> {
            MustacheFactory mf = new DefaultMustacheFactory();
            Mustache mustache = mf.compile(new StringReader(code), "code-template");
            StringWriter writer = new StringWriter();
            mustache.execute(writer, map);
            String renderedCode = writer.toString();
            System.out.println(renderedCode);
            System.out.println("------------------------------------");
        });
    }
}
