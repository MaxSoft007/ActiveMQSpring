package org.maxsoft.activemq.service.producer.topic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

/**
 * 订阅消息发送器
 * @author zhaoyl
 * @date May 3, 2015
 */
@Component("topicSender")
public class TopicSender {
	
	@Autowired
	@Qualifier("jmsTopicTemplate")
	private JmsTemplate jmsTemplate;

	/**
	 * 发送消息
	 * @param topicName
	 * @param message
	 */
	public void send(String topicName,final String message){
		jmsTemplate.send(topicName, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}
}
