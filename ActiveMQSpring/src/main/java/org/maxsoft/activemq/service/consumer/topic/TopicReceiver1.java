package org.maxsoft.activemq.service.consumer.topic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

/**
 * Topic��Ϣ������1
 * @author zhaoyl
 * @date May 3, 2015
 */
@Component("topicReceiver_1")//�����д����Ĭ��Ϊ����,������������ĸСд
public class TopicReceiver1 implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("TopicReceiver1���յ���Ϣ:"+((TextMessage)message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
