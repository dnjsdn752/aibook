package aibook.domain;

import aibook.infra.AbstractEvent;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Data;

@Data
public class ReadingApplied extends AbstractEvent {

    private Long id;
    private Long userId;
    private Long bookId;
    private Boolean isReading;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date startReading;
    
    private String webUrl;

    public boolean validate() {
        return this.id != null;
    }
}