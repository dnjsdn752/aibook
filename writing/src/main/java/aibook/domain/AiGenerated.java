package aibook.domain;

import aibook.domain.*;
import aibook.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
public class AiGenerated {
    private Long manuscriptId;
    private String aiImage;
    private String aiSummary;

    public AiGenerated() {}

    // getter/setter
}