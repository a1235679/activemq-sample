package bingyue.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 
 * @Description: Message Consumer
 * @author: Bing Yue
 */
public class MsgConsumer {
	
	 private static final String BROKER_URL="failover://tcp://192.168.106.128:61616";

	public static void main(String[] args) throws JMSException{
		
		//创建连接工厂
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(BROKER_URL);
		//获得连接
		Connection conn = connectionFactory.createConnection();
		//start
		conn.start();
				
		//创建Session，此方法第一个参数表示会话是否在事务中执行，第二个参数设定会话的应答模式
		Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		//下班了,待续...
	}
	
	public static void Receive(){
		
	}
}
