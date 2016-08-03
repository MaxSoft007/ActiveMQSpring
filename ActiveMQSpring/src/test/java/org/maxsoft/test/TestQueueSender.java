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
		System.out.println("------------->开始发信息了。");
		queueSender.send("test.queue1", "Hello World ... ");
		try {
			//加入该线程睡眠是由于JMS MessageListener 的生命周期和该测试用例同生死，
			//所以为了接受消息的测试，要把该线程发送消息后，睡眠一段时间既可以看到消息监听器的消息了
			Thread.sleep(1000 * 3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_02(){
		System.out.println("------------->开始发信息了。");
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
		//timer.schedule(new TimeTask(), new Date(), 5000);//从当前时间开始每隔5秒执行一次
		timer.schedule(new TimeTask(), 5000);
		try {
			Thread.sleep(1000 * 300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
