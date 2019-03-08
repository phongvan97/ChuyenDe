package main;

import java.io.IOException;

import org.apache.commons.lang.SerializationUtils;

import com.rabbitmq.client.*;

public class Recieve {
	
	public static void main(String[] args) throws IOException {
		//set các thông số kết nối tới rabbit mq
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(Constant.RABBIT_HOST);
		factory.setPort(Constant.RABBIT_PORT);
		factory.setUsername(Constant.RABBIT_USERNAME);
		factory.setPassword(Constant.RABBIT_PASSWORD);

		//connect tới rabbitmq
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		//khỏi tạo queue với  QUEUE_NAME = Nhom11K57TH4
		channel.queueDeclare(Constant.QUEUE_NAME, false, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL + C");


		//Khởi tạo một consumer tiếp nhận message từ queue QUEUE_NAME = Nhom11K57TH4
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope env, AMQP.BasicProperties props, byte[] body)
				throws IOException {
				Message message = (Message)SerializationUtils.deserialize(body);
				System.out.println(" [x] Recieved: " + message);
			}
		};
		//Đăng ký consumer.
		channel.basicConsume(Constant.QUEUE_NAME, true, consumer);
		
	}
}
