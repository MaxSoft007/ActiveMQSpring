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
//在配置文件中组件(bean)的作用域范围默认值是单例模式;使用注解@Scope("prototype")来设置其他的值
/**
 *1、singleton:当一个bean的作用域为singleton, 那么Spring IoC容器中只会存在一个共享的bean实例，
 *            并且所有对bean的请求，只要id与该bean定义相匹配，则只会返回bean的同一实例。
 *2、prototype：一个bean定义对应多个对象实例。Prototype作用域的bean会导致在每次对该bean请求（将其
 *            注入到另一个bean中，或者以程序的方式调用容器的getBean()方法）时都会创建一个新的bean实
 *            例。根据经验，对有状态的bean应该使用prototype作用域，而对无状态的bean则应该使用
 *            singleton作用域。
 *3、request：在一次HTTP请求中，一个bean定义对应一个实例；即每次HTTP请求将会有各自的bean实例， 它们依据
 *          某个bean定义创建而成。该作用域仅在基于web的Spring ApplicationContext情形下有效。
 *4、session：在一个HTTP Session中，一个bean定义对应一个实例。该作用域仅在基于web的Spring 
 *           ApplicationContext情形下有效。
 *5、global session：在一个全局的HTTP Session中，一个bean定义对应一个实例。典型情况下，仅在使用portlet
 *                  context的时候有效。该作用域仅在基于web的Spring ApplicationContext情形下有效。
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
	 * 发送消息到队列
	 * Queue队列：仅有一个订阅者会收到消息，消息一旦被处理就不会存在队列中
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
	 * 发送消息到主题
	 * Topic主题 ：放入一个消息，所有订阅者都会收到 
	 * 这个是主题目的地是一对多的
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
