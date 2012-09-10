package gtest.client;

/**
 * @author Vitalii Tymchyshyn
 */
public class Args {
    private static String serverLocation;
    private static String userName;

    public String getServerLocation() {
        return serverLocation;
    }

    public static void setServerLocation(String serverLocation) {
        Args.serverLocation = serverLocation;
    }

    public String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        Args.userName = userName;
    }
}
