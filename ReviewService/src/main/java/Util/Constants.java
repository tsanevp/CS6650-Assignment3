package Util;

public class Constants {

    // RabbitMQ Constants
    public final static String HOST = "ec2-54-245-48-188.us-west-2.compute.amazonaws.com";
    public final static String EXCHANGE_NAME = "REVIEW_EXCHANGE";
    public final static String EXCHANGE_TYPE = "direct";
    public final static String LIKE_QUEUE = "like";
    public final static String DISLIKE_QUEUE = "dislike";
    public final static int NUM_CONSUMERS_EACH_QUEUE = 100;

    // MySQL Constants
    public static final String DB_URL = "jdbc:mysql://db1.cklnkwnnivsg.us-west-2.rds.amazonaws.com:3306/a3db1";
    public static final String DB_USER = "admin";
    public static final String DB_PASSWORD = "adminpassword";
    public final static int MIN_NUM_CONNECTIONS = 50;
    public final static int MAX_NUM_CONNECTIONS = 75;
}
