package org.maxsoft.test;

import java.util.Timer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxsoft.activemq.service.producer.queue.QueueSender;
import org.maxsoft.activemq.service.producer.topic.TopicSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:ActiveMQ.xml"})
public class TestQueueSender {

	@Autowired
	QueueSender queueSender;
	@Autowired
	TopicSender topicSender;
	
	@Test
	public void test_01(){
		System.out.println("------------->��ʼ����Ϣ�ˡ�");
		queueSender.send("test.queue1", "Hello World ... ");
		try {
			//������߳�˯��������JMS MessageListener ���������ں͸ò�������ͬ������
			//����Ϊ�˽�����Ϣ�Ĳ��ԣ�Ҫ�Ѹ��̷߳�����Ϣ��˯��һ��ʱ��ȿ��Կ�����Ϣ����������Ϣ��
			Thread.sleep(1000 * 3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_02(){
		System.out.println("------------->��ʼ����Ϣ�ˡ�");
		topicSender.send("test.topic", "Hello World ... ");
		try {
			Thread.sleep(1000 * 3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_03(){
		Timer timer = new Timer();
		//timer.schedule(new TimeTask(), new Date());
		//timer.schedule(new TimeTask(), new Date(), 5000);//�ӵ�ǰʱ�俪ʼÿ��5��ִ��һ��
		timer.schedule(new TimeTask(), 5000);
		try {
			Thread.sleep(1000 * 300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
