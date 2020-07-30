package com.ex.config;

import com.ex.constants.RabbitConsts;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * 配置类
 */
@Configuration
@Slf4j
public class RabbitMqConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory factory){
        factory.setPublisherConfirms(true);
        factory.setPublisherConfirms(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> log.info("消息发送成功:correlationData({}),ack({}),cause({})",correlationData,ack,cause));
        rabbitTemplate.setReturnCallback(((message, replyCode, replyText, exchange, routingKey) -> log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}",exchange,routingKey,replyCode,replyText,message)));
        return rabbitTemplate;
    }

    /**
     * 直接模式队列一
     * @return
     */
    @Bean
    public Queue directOneQueue(){ return new Queue(RabbitConsts.DIRECT_MODE_QUEUE_ONE); }

    /**
     * 队列2
     */
    @Bean
    public Queue queueTwo(){return new Queue(RabbitConsts.QUEUE_TWO);}

    /**
     * 队列3
     */
    @Bean
    public Queue queueThree(){return new Queue(RabbitConsts.QUEUE_THREE);}

    /**
     * 分列模式队列
     */
    @Bean
    public FanoutExchange fanoutExchange(){return new FanoutExchange(RabbitConsts.FANOUT_MODE_QUEUE);}

    /**
     * 分列模式绑定队列1
     */
    @Bean
    public Binding fanoutBinding1(Queue directOneQueue,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(directOneQueue).to(fanoutExchange);
    }

    /**
     * 分列模式绑定队列2
     */
    @Bean
    public Binding fanoutBinding2(Queue queueTwo,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queueTwo).to(fanoutExchange);
    }

    /**
     * 主题模式队列
     */
    @Bean
    public TopicExchange topicExchange(){return new TopicExchange(RabbitConsts.TOPIC_MODE_QUEUE);}

    /**
     * 主题模式绑定分列模式
     */
    @Bean
    public Binding topicBinding1(FanoutExchange fanoutExchange,TopicExchange topicExchange){
        return BindingBuilder.bind(fanoutExchange).to(topicExchange).with(RabbitConsts.TOPIC_ROUTING_KEY_ONE);
    }

    /**
     * 主题模式绑定队列2
     */
    @Bean
    public Binding topicBinding2(Queue queueTwo,TopicExchange topicExchange){
        return BindingBuilder.bind(queueTwo).to(topicExchange).with(RabbitConsts.TOPIC_ROUTING_KEY_TWO);
    }

    /**
     * 主题模式绑定队列3
     */
    @Bean
    public Binding topicBinding3(Queue queueThree,TopicExchange topicExchange){
        return BindingBuilder.bind(queueThree).to(topicExchange).with(RabbitConsts.TOPIC_ROUTING_KEY_THREE);
    }

    /**
     * 延迟队列交换器
     */
    @Bean
    public CustomExchange delayExchange(){
        Map<String,Object> args = Maps.newHashMap();
        args.put("x-delay-type","direct");
        return new CustomExchange(RabbitConsts.DELAY_MODE_QUEUE,"x-delayed-mesage",true,false,args);
    }

    /**
     * 延迟队列绑定自定义交换器
     */
//    @Bean
//    public Binding delayBinding(@Qualifier("") Queue delayQueue, CustomExchange delayExchange){
//        return BindingBuilder.bind(delayQueue).to(delayExchange).with(RabbitConsts.DELAY_MODE_QUEUE).noargs();
//    }

}
