package aibook.infra;

import aibook.config.kafka.KafkaProcessor;
import aibook.domain.*;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PointViewHandler {

    @Autowired
    PointViewRepository pointViewRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenRegisterPointGained_then_CREATE(
        @Payload RegisterPointGained event
    ) {
        if (!event.validate()) return;

        PointView view = new PointView();
        view.setId(event.getId());
        view.setUserId(event.getUserId()); // Long 타입이라고 가정
        view.setPoint(event.getPoint());
        view.setIsSubscription(event.getIsSubscribe());
        pointViewRepository.save(view);
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPointBought_then_UPDATE(
        @Payload PointBought event
    ) {
        if (!event.validate()) return;

        PointView view = pointViewRepository.findByUserId(event.getUserId());
        if (view != null) {
            view.setPoint(view.getPoint() + event.getBoughtAmount());
            pointViewRepository.save(view);
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPointDecreased_then_UPDATE(
        @Payload PointDecreased event
    ) {
        if (!event.validate()) return;

        PointView view = pointViewRepository.findByUserId(event.getUserId());
        if (view != null) {
            view.setPoint(event.getPoint());
            pointViewRepository.save(view);
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenSubscriptionUpdate_then_UPDATE(
        @Payload SubscriptionUpdate event
    ) {
        if (!event.validate()) return;

        PointView view = pointViewRepository.findByUserId(event.getUserId());
        if (view != null) {
            view.setIsSubscription(event.getIsSubscribe);
            pointViewRepository.save(view);
        }
    }
}
