package com.example.boot01web01.service;

import com.example.boot01web01.config.property.MyProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailService implements ApplicationEventPublisherAware {
	@Autowired
	private MyProperty myProperty;

	private List<String> emails;
	@Value("${mitre.name}")
	private String name;
	private ApplicationEventPublisher publisher;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	public void sendEmail(String address, String content) {
		emails = myProperty.getEmails();
		System.out.println(emails);
		System.out.println(name);
		if (emails.contains(address)) {
			System.out.println("blocked: " + address);
			publisher.publishEvent(new BlockedListEvent(this, address, content));
			System.out.println("blocked: end");
			return;
		}
		// send email...
		System.out.println("send to: " + address);
	}
}