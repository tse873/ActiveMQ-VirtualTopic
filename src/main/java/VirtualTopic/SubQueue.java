package VirtualTopic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class SubQueue {
    public static void main(String[] args) throws Exception {
        ConnectionFactory cf = new ActiveMQConnectionFactory("tse","tse","tcp://localhost:61616");
        Connection connection = cf.createConnection();
        connection.start();
        final Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("Consumer.A.VirtualTopic.Mytopic");
        MessageConsumer consumer = session.createConsumer(destination);
        int i = 0;
        while (i < 3) {
            i++;
            TextMessage message = (TextMessage) consumer.receive();
            session.commit();
            System.out.println("收到消息：" + message.getText());
        }
        session.close();
        connection.close();
    }
}
