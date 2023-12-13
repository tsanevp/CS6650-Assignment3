package Util;

public class Constants {

    // S3 Bucket Name
    public final static String BUCKET_NAME = "cs6650-imagebucket";

    // RabbitMQ Constants
    public final static String RABBITMQ_HOST = "ec2-54-245-48-188.us-west-2.compute.amazonaws.com";
    public final static String EXCHANGE_NAME = "REVIEW_EXCHANGE";
    public final static String EXCHANGE_TYPE = "direct";
    public final static Integer CHANNEL_POOL_SIZE = 200;

    // MySQL Constants
    public static final String MYSQL_DB_URL = "jdbc:mysql://db1.cklnkwnnivsg.us-west-2.rds.amazonaws.com:3306/a3db1";
    public static final String MYSQL_DB_USER = "admin";
    public static final String MYSQL_DB_PASSWORD = "adminpassword";
    public final static int MIN_MYSQL_CONNECTIONS = 100;
    public final static int MAX_MYSQL_CONNECTIONS = 190;
}
