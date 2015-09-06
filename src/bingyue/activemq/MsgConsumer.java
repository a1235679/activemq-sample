package bingyue.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @Description: Message Consumer
 * @author: Bing Yue
 */
public class MsgConsumer implements MessageListener  {
	
	private static final String BROKER_URL="failover://tcp://192.168.106.128:61616";
	private static final String SUBJECT = "test-queue";

	public static void main(String[] args) throws JMSException{
		
		//创建连接工厂
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(BROKER_URL);
		//获得连接
		Connection conn = connectionFactory.createConnection();
		//start
		conn.start();
				
		//创建Session，此方法第一个参数表示会话是否在事务中执行，第二个参数设定会话的应答模式
		Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//创建队列
        Destination dest = session.createQueue(SUBJECT);
        //创建消息生产者
        MessageConsumer  consumer  = session.createConsumer(dest);
        
        //初始化MessageListener
        MsgConsumer msgConsumer = new MsgConsumer();

        //给消费者设定监听对象
        consumer.setMessageListener(msgConsumer);
        
	}

	@Override
	/**
	 * 消费者需要实现MessageListener接口
	 * 接口有一个onMessage(Message message)需要在此方法中做消息的处理
	 */
	public void onMessage(Message msg) {
		 TextMessage txtMessage = (TextMessage)msg;
		 try {
			System.out.println("get message:" + txtMessage.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
    
	}
}
