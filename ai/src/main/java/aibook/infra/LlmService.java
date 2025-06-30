package aibook.infra;

import aibook.domain.Ai;
import aibook.domain.AiRequested;

//import aibook.infra.dto.OpenAiResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public void callLlmAndSave(AiRequested request) {
        WebClient.create()
            .post()
            .uri(llmApiUrl)
            .header("Authorization", "Bearer " + llmApiKey)
            .bodyValue(Map.of(
                "model", "gpt-3.5-turbo",
                "messages", List.of(
                    Map.of("role", "system", "content",
                        "너는 책 내용을 요약하고 표지 이미지 URL도 생성하는 AI야. 다음 요청에 대해 JSON 형식으로 응답해. 예: {\"summary\": \"...\", \"image_url\": \"...\"}"),
                    Map.of("role", "user", "content",
                        String.format("책 제목: %s\n작가: %s\n내용: %s",
                            request.getTitle(), request.getAuthorName(), request.getContent())
                    )
                )
            ))
            .retrieve()
            .bodyToMono(aibook.domain.OpenAiResponse.class)
            .subscribe(response -> {
                try {
                    String content = response.getChoices().get(0).getMessage().getContent();

                    ObjectMapper mapper = new ObjectMapper();
                    Map<String, String> parsed = mapper.readValue(content, new TypeReference<>() {});
                    String summary = parsed.get("summary");
                    String imageUrl = parsed.get("image_url");

                    request.setAiSummary(summary);
                    request.setAiImage(imageUrl);

                    Ai.aiGenerate(request); // 저장 + 이벤트 발행

                } catch (Exception e) {
                    e.printStackTrace(); // 예외 로그 출력
                }
            });
    }
}
