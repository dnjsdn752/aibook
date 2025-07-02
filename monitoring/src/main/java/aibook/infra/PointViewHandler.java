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
        view.setUserId(event.getUserId()); 
        view.setPoint(event.getPoint());
        view.setIsSubscription(event.getIsSubscribe());
        pointViewRepository.save(view);
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPointBought_then_UPDATE_1(
        @Payload PointBought event
    ) {
        if (!event.validate()) return;
        
        Optional<PointView> viewOptional = pointViewRepository.findById(
                event.getId()
        );
        if (viewOptional.isPresent()) {
            PointView view = viewOptional.get();
            view.setPoint(event.getTotalPoint());
            pointViewRepository.save(view);
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPointDecreased_then_UPDATE_2(
        @Payload PointDecreased event
    ) {
        if (!event.validate()) return;

        Optional<PointView> viewOptional = pointViewRepository.findById(
                event.getId()
        );
        if (viewOptional.isPresent()) {
            PointView view = viewOptional.get();
            view.setPoint(event.getPoint());
            pointViewRepository.save(view);
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenReadingCanceled_then_UPDATE_3(
        @Payload ReadingCanceled event
    ) {
        
        if (!event.validate()) return;

        Optional<PointView> viewOptional = pointViewRepository.findById(
                event.getId()
        );
        if (viewOptional.isPresent()) {
            PointView view = viewOptional.get();
            view.setPoint(view.getPoint() + 500);
            pointViewRepository.save(view);
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenSubscriptionUpdate_then_UPDATE_4(
        @Payload SubscriptionUpdate event
    ) {
        if (!event.validate()) return;

        Optional<PointView> viewOptional = pointViewRepository.findById(
                event.getId()
        );
        if (viewOptional.isPresent()) {
            PointView view = viewOptional.get();
            view.setIsSubscription(event.getIsSubscribe());
        pointViewRepository.save(view);
        }
    }
}
