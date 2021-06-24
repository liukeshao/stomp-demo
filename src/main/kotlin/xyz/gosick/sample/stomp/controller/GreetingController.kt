package xyz.gosick.sample.stomp.controller

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Controller
import org.springframework.web.util.HtmlUtils
import xyz.gosick.sample.stomp.Greeting
import xyz.gosick.sample.stomp.message.HelloMessage
import javax.annotation.Resource


/**
 *
 * @author liukeshao
 * @date 2021-06-24 13:44
 */
@Controller
class GreetingController {

    @Resource
    lateinit var simpMessagingTemplate: SimpMessagingTemplate

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    fun greeting(message: HelloMessage): Greeting {
        Thread.sleep(1000) // simulated delay
        println(message)
        return Greeting("Hello, " + HtmlUtils.htmlEscape(message.name) + "!")
    }

    @MessageMapping("/hi")
    fun greetingSingle(message: HelloMessage) {
        Thread.sleep(1000) // simulated delay
        println(message)
        simpMessagingTemplate.convertAndSend("/topic/greetings", "test")
    }
}

