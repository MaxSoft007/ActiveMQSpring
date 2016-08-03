package org.maxsoft.activemq.service.consumer.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

/**
 * ������Ϣ������1
 * @author zhaoyl
 * @date May 3, 2015
 */
@Component("queueReceiver_1")//�����д����Ĭ��Ϊ����,������������ĸСд
public class QueueReceiver1 implements MessageListener{

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("QueueReceiver1���յ���Ϣ:"+((TextMessage)message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
