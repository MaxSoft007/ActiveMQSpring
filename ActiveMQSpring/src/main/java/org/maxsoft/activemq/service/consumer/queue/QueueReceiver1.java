package org.maxsoft.activemq.service.consumer.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

/**
 * 队列消息监听器1
 * @author zhaoyl
 * @date May 3, 2015
 */
@Component("queueReceiver_1")//如果不写，则默认为类名,将类名的首字母小写
public class QueueReceiver1 implements MessageListener{

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("QueueReceiver1接收到消息:"+((TextMessage)message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
