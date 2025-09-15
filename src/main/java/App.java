import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        HelloWorld hw1 = context.getBean("helloworld", HelloWorld.class);
        HelloWorld hw2 = context.getBean("helloworld", HelloWorld.class);

        Cat cat1 = context.getBean("cat", Cat.class);
        Cat cat2 = context.getBean("cat", Cat.class);

        System.out.println(hw1 == hw2);
        System.out.println(cat1 == cat2);

        context.close();
    }
}