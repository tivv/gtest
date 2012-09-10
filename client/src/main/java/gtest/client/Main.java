package gtest.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Vitalii Tymchyshyn
 */
public class Main {
    public static void main(String... args) throws InterruptedException {
        if (args.length != 2) {
            System.out.println("Usage: java -jar gtest-client-1.0.jar <Server URL> <User name>");
            System.exit(1);
        }
        Args.setServerLocation(args[0]);
        Args.setUserName(args[1]);
        new ClassPathXmlApplicationContext("gtest/client/client-context.xml").getBean(Client.class).run();
    }
}
