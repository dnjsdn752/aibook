/*
package aibook.infra;

import aibook.domain.Ai;
import aibook.domain.AiRequested;
import aibook.domain.OpenAiResponse;
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

    @Value("${llm.api.url.chat}")
    private String chatApiUrl;

    @Value("${llm.api.url.image}")
    private String imageApiUrl;

    @Value("${llm.api.key}")
    private String apiKey;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void callLlmAndSave(AiRequested request) {
        try {
            System.out.println("🟡 [AI 처리 시작] manuscriptId=" + request.getId() + ", title=" + request.getTitle());
            // ✅ 1. 요약 생성
            System.out.println("📄 [요약 생성 요청 시작]");
            OpenAiResponse chatResponse = WebClient.create()
                .post()
                .uri(chatApiUrl)
                .header("Authorization", "Bearer " + apiKey)
                .bodyValue(Map.of(
                    "model", "gpt-4",
                    "messages", List.of(
                        Map.of("role", "system", "content",
                            "너는 책 내용을 분석해서 aiSummary를 작성하는 AI야. 아래 JSON 형식으로만 응답해.\n\n" +
                            "{ \"aiSummary\": \"...\" }"),
                        Map.of("role", "user", "content",
                            String.format("책 제목: %s\n작가: %s\n내용: %s",
                                request.getTitle(), request.getAuthorName(), request.getContent())
                        )
                    )
                ))
                .retrieve()
                .bodyToMono(OpenAiResponse.class)
                .block(); // ✅ 동기 처리

            String content = chatResponse.getChoices().get(0).getMessage().getContent();
            Map<String, String> parsed = objectMapper.readValue(content, new TypeReference<>() {});
            request.setAiSummary(parsed.get("aiSummary"));
            System.out.println("✅ [요약 생성 완료]");

            // ✅ 2. 이미지 생성
            System.out.println("🖼️ [이미지 생성 요청 시작]");

            String prompt = String.format(
                    "Create a cinematic and detailed fantasy book cover illustration based on this story summary: \"%s\". " +
                    "Avoid any text or title. Use rich, thematic visuals and dramatic lighting."
                    ,request.getAiSummary()
            );
            Map imageResponse = WebClient.create()
                .post()
                .uri(imageApiUrl)
                .header("Authorization", "Bearer " + apiKey)
                .bodyValue(Map.of(
                    "prompt", prompt,
                    "n", 1,
                    "size", "512x512"
                ))
                .retrieve()
                .bodyToMono(Map.class)
                .block(); // ✅ 동기 처리

            List<Map<String, String>> data = (List<Map<String, String>>) imageResponse.get("data");
            String imageUrl = data.get(0).get("url");
            request.setAiImage(imageUrl);
            System.out.println("✅ [이미지 생성 완료]");

            // ✅ 3. DB 저장 + Kafka 이벤트 발행
            System.out.println("📦 [DB 저장 + Kafka 이벤트 발행]");
            Ai.aiGenerate(request);
            System.out.println("✅ [AI 처리 전체 완료]");

        } catch (Exception e) {
            System.out.println("[ERROR] LlmService 실패 : " +  e.getMessage());
            e.printStackTrace();
        }
    }
}
*/
package aibook.infra;

import aibook.domain.Ai;
import aibook.domain.AiRequested;
import aibook.domain.OpenAiResponse;
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

    @Value("${llm.api.url.chat}")
    private String chatApiUrl;

    @Value("${llm.api.url.image}") // ✅ 공식 DALL·E 3 이미지 생성용 API
    private String imageApiUrl;

    @Value("${llm.api.key}")
    private String apiKey;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void callLlmAndSave(AiRequested request) {
        try {
            System.out.println("🟡 [AI 처리 시작] manuscriptId=" + request.getId() + ", title=" + request.getTitle());

            // ✅ 1. 요약 생성
            System.out.println("📄 [요약 생성 요청 시작]");
            OpenAiResponse chatResponse = WebClient.create()
                .post()
                .uri(chatApiUrl)
                .header("Authorization", "Bearer " + apiKey)
                .bodyValue(Map.of(
                    "model", "gpt-4",
                    "messages", List.of(
                        Map.of("role", "system", "content",
                            "너는 책 내용을 분석해서 aiSummary를 작성하는 AI야. 아래 JSON 형식으로만 응답해.\n\n" +
                            "{ \"aiSummary\": \"...\" }"),
                        Map.of("role", "user", "content",
                            String.format("책 제목: %s\n작가: %s\n내용: %s",
                                request.getTitle(), request.getAuthorName(), request.getContent())
                        )
                    )
                ))
                .retrieve()
                .bodyToMono(OpenAiResponse.class)
                .block();

            String content = chatResponse.getChoices().get(0).getMessage().getContent();
            Map<String, String> parsed = objectMapper.readValue(content, new TypeReference<>() {});
            request.setAiSummary(parsed.get("aiSummary"));
            System.out.println("✅ [요약 생성 완료]");

            // ✅ 2. 이미지 생성 (공식 DALL·E 3 API 사용)
            System.out.println("🖼️ [DALL·E 3 이미지 생성 요청 시작 - 공식 API]");

            String prompt = String.format(
                "A cinematic and detailed fantasy book cover illustration based on the story: \"%s\". " +
                "Avoid text. Use rich visuals, dramatic lighting, and fantasy aesthetics.",
                request.getAiSummary()
            );

            Map<String, Object> imageRequestBody = Map.of(
                "model", "dall-e-3", // ✅ 핵심: 공식 DALL·E 3 모델
                "prompt", prompt,
                "n", 1,
                "size", "1024x1024"
            );

            Map imageResponse = WebClient.create()
                .post()
                .uri(imageApiUrl) // ex: https://api.openai.com/v1/images/generations
                .header("Authorization", "Bearer " + apiKey)
                .bodyValue(imageRequestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

            List<Map<String, String>> data = (List<Map<String, String>>) imageResponse.get("data");
            String imageUrl = data.get(0).get("url");
            request.setAiImage(imageUrl);

            System.out.println("✅ [이미지 생성 완료] imageUrl=" + imageUrl);

            // ✅ 3. DB 저장 + Kafka 이벤트 발행
            System.out.println("📦 [DB 저장 + Kafka 이벤트 발행]");
            Ai.aiGenerate(request);
            System.out.println("✅ [AI 처리 전체 완료]");

        } catch (Exception e) {
            System.out.println("[ERROR] LlmService 실패 : " +  e.getMessage());
            e.printStackTrace();
        }
    }
}
