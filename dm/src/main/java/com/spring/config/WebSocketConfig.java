package com.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import com.spring.dm.UserHandshakeHandler;

//import com.spring.stomp.UserHandshakeHandler;

@Configuration // Spring Configuration class
@EnableWebSocketMessageBroker // WebScoket message handling을 허용해준다 
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	
  @Override // MessageBroker는 송신자에게 수신자의 이전 메세지 프로토콜로 변환해주는 모듈 중 하나
            // 요청이 오면 그에 해당하는 통신 채널로 전송, 응답 또한 같은 경로로 가서 응답한다.
  public void configureMessageBroker(MessageBrokerRegistry config) {
    config.enableSimpleBroker("/queue"); // 해당 주소를 구독하고 있는 클라이언트들에게 메시지 전달 //단일로 연결된 대상에게 통신을 전달할 /queue를 구독할 수 있도록 설정
    config.setApplicationDestinationPrefixes("/send"); // 클라이언트에서 보낸 메시지를 받을 prefix
    config.setUserDestinationPrefix("/user");
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {// 최초 소켓 연결 시 endpoint
    registry.addEndpoint("/ws-dm")  //SocketJs 연결 주소
//    client.send(`/dm/notice/보낼주소`,{},JSON.stringify(보낼데이터))
    		.setAllowedOrigins("http://localhost:3000")
    		.setAllowedOrigins("http://3.39.189.222")
    		.setHandshakeHandler(new UserHandshakeHandler())
    		.withSockJS(); // javascript에서 SockJS생성자를 통해 연결.
    
  
  }
  
  @Override
  public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
      registration.setMessageSizeLimit(160 * 64 * 1024);    // Max incoming message size, default : 64 * 1024
      registration.setSendTimeLimit(20 * 10000);            // default : 10 * 10000
      registration.setSendBufferSizeLimit(10 * 512 * 1024); // Max outgoing buffer size, default : 512 * 1024
  }


}

