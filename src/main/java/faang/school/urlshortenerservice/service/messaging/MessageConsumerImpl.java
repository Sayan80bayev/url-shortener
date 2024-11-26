package faang.school.urlshortenerservice.service.messaging;

import faang.school.urlshortenerservice.config.messaging.RabbitMQConfig;
import faang.school.urlshortenerservice.service.UrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageConsumerImpl implements MessageConsumer {

    @Value("${base.url}")
    private String baseUrl;

    private final UrlService urlService;

    @Override
    @RabbitListener(queues = RabbitMQConfig.AD_CREATED_QUEUE)
    public String handleGetShortUrl(String message) {
        try {
            return baseUrl +"/short/" + urlService.getShortUrl(message);
        }catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
