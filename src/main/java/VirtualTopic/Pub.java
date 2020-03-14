package VirtualTopic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Pub {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tse","tse","tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createTopic("VirtualTopic.Mytopic");
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);//设置传送模式为持久模式，默认为非持久
        connection.start();
        for (int i = 0; i < 3; i++) {
            TextMessage message = session.createTextMessage("message--" + i);
            Thread.sleep(1000);
            //通过消息生产者发出消息
            producer.send(message);
        }
        session.commit();
        session.close();
        connection.close();
    }
}