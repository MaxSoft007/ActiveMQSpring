package org.maxsoft.activemq.service.consumer.topic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

/**
 * Topic��Ϣ������2
 * @author zhaoyl
 * @date May 3, 2015
 */
@Component("topicReceiver_2")//�����д����Ĭ��Ϊ����,������������ĸСд
public class TopicReceiver2 implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("TopicReceiver2���յ���Ϣ:"+((TextMessage)message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
