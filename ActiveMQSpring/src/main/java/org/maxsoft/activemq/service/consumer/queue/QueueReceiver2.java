package org.maxsoft.activemq.service.consumer.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

/**
 * ������Ϣ������2
 * @author zhaoyl
 * @date May 3, 2015
 */
@Component("queueReceiver_2")//�����д����Ĭ��Ϊ����,������������ĸСд
public class QueueReceiver2 implements MessageListener{

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("QueueReceiver2���յ���Ϣ:"+((TextMessage)message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
