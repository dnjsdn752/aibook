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
import lombok.RequiredArgsConstructor;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    LlmService llmService;
    
    @Autowired
    AiRepository aiRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='AiRequest'"
    )
    public void wheneverAiRequest_AiGenerate(@Payload AiRequest aiRequest) {
        AiRequest event = aiRequest;
        System.out.println(
            "\n\n##### listener AiGenerate : " + aiRequest + "\n\n"
        );

        // Sample Logic //
        //Ai.aiGenerate(event);
        llmService.callLlmAndSave(aiRequest);
    }
}
//>>> Clean Arch / Inbound Adaptor
