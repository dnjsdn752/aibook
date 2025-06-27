package aibook.infra;

import aibook.config.kafka.KafkaProcessor;
import aibook.domain.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    ManuscriptRepository manuscriptRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='AiGenerated'"
    )
    public void wheneverAiGenerated_AiImage(@Payload AiGenerated aiGenerated) {
        AiGenerated event = aiGenerated;
        System.out.println(
            "\n\n##### listener AiImage : " + aiGenerated + "\n\n"
        );

        // Sample Logic //
        Manuscript.aiImage(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
