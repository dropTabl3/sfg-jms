package guru.springframework.sfgjms.listener;

import guru.springframework.sfgjms.config.JMSConfig;
import guru.springframework.sfgjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class HelloMessageListener {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JMSConfig.MY_QUEUE)
    public void listen(@Payload HelloWorldMessage helloWorldMessage,
                       @Headers MessageHeaders headers, Message message){

        //System.out.println("message in");

        //System.out.println(helloWorldMessage);

        //the queue will retry to send message that are not correctly sent(exception thrown?)

        //throw new RuntimeException("test");
    }

    @JmsListener(destination = JMSConfig.SEND_RECEIVE_MY_QUEUE)
    public void listenForHello(@Payload HelloWorldMessage helloWorldMessage,
                       @Headers MessageHeaders headers, Message message) throws JMSException {
        HelloWorldMessage hwmsg = HelloWorldMessage.builder()
                .id(UUID.randomUUID())
                .message("World!")
                .build();



        jmsTemplate.convertAndSend(message.getJMSReplyTo(), hwmsg);


//        throw new RuntimeException("test");
    }
}
