package com.chalnakchalnak.wishlistservice.application;

import com.chalnakchalnak.wishlistservice.common.exception.BaseException;
import com.chalnakchalnak.wishlistservice.common.response.BaseResponseStatus;
import com.chalnakchalnak.wishlistservice.dto.WishlistEventDto;
import com.chalnakchalnak.wishlistservice.dto.in.AddWishlistRequestDto;
import com.chalnakchalnak.wishlistservice.dto.in.RemoveWishlistRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class WishlistEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    private final String TOPIC_NAME = "wishlist.event";

    public void publishAddWishlistEvent(AddWishlistRequestDto addWishlistRequestDto) {
        final WishlistEventDto event = WishlistEventDto.fromAddRequest(addWishlistRequestDto);

        final String payload = toJson(event);

        kafkaTemplate.send(TOPIC_NAME, event.getPostUuid(), payload);
    }

    public void publishRemoveWishlistEvent(RemoveWishlistRequestDto removeWishlistRequestDto) {
        final WishlistEventDto event = WishlistEventDto.fromRemoveRequest(removeWishlistRequestDto);

        final String payload = toJson(event);

        kafkaTemplate.send(TOPIC_NAME, event.getPostUuid(), payload);
    }



    public String toJson(WishlistEventDto wishlistEventDto) {
        try {
            return objectMapper.writeValueAsString(wishlistEventDto);
        } catch (JsonProcessingException e) {
            log.error("Kafka 메시지 직렬화 실패: {}", e);
            throw new BaseException(BaseResponseStatus.FAILED_SERIALIZE_MESSAGE);
        }
    }
}
