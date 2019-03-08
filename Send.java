package main;

import java.io.IOException;

import org.apache.commons.lang.SerializationUtils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {



	public static void main(String[] args) throws IOException, InterruptedException {
		//set các thông số kết nối tới rabbit mq
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(Constant.RABBIT_HOST);
		factory.setPort(Constant.RABBIT_PORT);
		factory.setUsername(Constant.RABBIT_USERNAME);
		factory.setPassword(Constant.RABBIT_PASSWORD);
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		//connect tới rabbitmq
		channel.queueDeclare(Constant.QUEUE_NAME, false, false, false, null);

		//Thưc hiện đẩy message và  queue
		Message message;
		for (int i = 0; i < 10; i ++) {
			Thread.sleep(500);
			message = new Message(i, "Hello RabbitMQ - Request: " + (i + 1));
			
			channel.basicPublish("", Constant.QUEUE_NAME, null, SerializationUtils.serialize(message));
			System.out.println(" [x] Sent : " + message);
		}
		//Đóng kết nối
		channel.close();
		connection.close();
	}
}
