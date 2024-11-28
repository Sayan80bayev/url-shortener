package faang.school.urlshortenerservice.service.messaging;

public interface MessageConsumer {
    String handleGetShortUrl(String message);
}
