package org.maxsoft.activemq.controller;

import org.maxsoft.activemq.service.producer.queue.QueueSender;
import org.maxsoft.activemq.service.producer.topic.TopicSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/activemqcontroller")
//�������ļ������(bean)��������ΧĬ��ֵ�ǵ���ģʽ;ʹ��ע��@Scope("prototype")������������ֵ
/**
 *1��singleton:��һ��bean��������Ϊsingleton, ��ôSpring IoC������ֻ�����һ�������beanʵ����
 *            �������ж�bean������ֻҪid���bean������ƥ�䣬��ֻ�᷵��bean��ͬһʵ����
 *2��prototype��һ��bean�����Ӧ�������ʵ����Prototype�������bean�ᵼ����ÿ�ζԸ�bean���󣨽���
 *            ע�뵽��һ��bean�У������Գ���ķ�ʽ����������getBean()������ʱ���ᴴ��һ���µ�beanʵ
 *            �������ݾ��飬����״̬��beanӦ��ʹ��prototype�����򣬶�����״̬��bean��Ӧ��ʹ��
 *            singleton������
 *3��request����һ��HTTP�����У�һ��bean�����Ӧһ��ʵ������ÿ��HTTP���󽫻��и��Ե�beanʵ���� ��������
 *          ĳ��bean���崴�����ɡ�����������ڻ���web��Spring ApplicationContext��������Ч��
 *4��session����һ��HTTP Session�У�һ��bean�����Ӧһ��ʵ��������������ڻ���web��Spring 
 *           ApplicationContext��������Ч��
 *5��global session����һ��ȫ�ֵ�HTTP Session�У�һ��bean�����Ӧһ��ʵ������������£�����ʹ��portlet
 *                  context��ʱ����Ч������������ڻ���web��Spring ApplicationContext��������Ч��
 * @author zhaoyl
 * @date May 3, 2015
 */
public class ActivemqController {
	
	public ThreadLocal<String> local = new ThreadLocal<String>();
	
	
	public String a = "AAA";
	
	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}
	
	@Autowired
	QueueSender queueSender;
	@Autowired
	TopicSender topicSender;
	
	/**
	 * ������Ϣ������
	 * Queue���У�����һ�������߻��յ���Ϣ����Ϣһ��������Ͳ�����ڶ�����
	 * @param message
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("queueSender")
	public String queueSender(@RequestParam("message")String message){
		String opt = "";
		try {
			System.out.println(a);
			String s = local.get();
			if(s==null){
				s = message;
				System.out.println("-------------->" + s);
				a = s;
			}
			queueSender.send("test.queue", message);
			this.setA(message);
			opt = "suc";
		} catch (Exception e) {
			opt = e.getMessage().toString();
		}
		return opt;
	}

	/**
	 * ������Ϣ������
	 * Topic���� ������һ����Ϣ�����ж����߶����յ� 
	 * ���������Ŀ�ĵ���һ�Զ��
	 * @param message
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("topicSender")
	public String topicSender(@RequestParam("message")String message){
		String opt = "";
		try {
			topicSender.send("test.topic", message);
			opt = "suc";
		} catch (Exception e) {
			opt = e.getMessage().toString();
		}
		return opt;
	}
}
