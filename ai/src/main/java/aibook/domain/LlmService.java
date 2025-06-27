package aibook.infra.service;

import aibook.domain.Ai;
import aibook.domain.AiRequest;
import aibook.infra.dto.OpenAiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LlmService {

    @Value("${llm.api.url}")
    private String llmApiUrl;

    @Value("${llm.api.key}")
    private String llmApiKey;

    public void callLlmAndSave(AiRequest request) {
        WebClient.create()
                .post()
                .uri(llmApiUrl)
                .header("Authorization", "Bearer " + llmApiKey)
                .bodyValue(Map.of(
                    "model", "gpt-3.5-turbo",
                    "messages", List.of(
                        Map.of("role", "user", "content", request.getContent())
                    )
                ))
                .retrieve()
                .bodyToMono(OpenAiResponse.class)
                .subscribe(response -> {
                    String summary = response.getChoices().get(0).getMessage().getContent();
                    request.setAiSummary(summary);
                    request.setAiImage("https://dummy.image/generated.png");
                    Ai.aiGenerate(request); // 저장 + 이벤트 발행
                });
    }
}
