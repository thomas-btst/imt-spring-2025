package org.imt.tournamentmaster.service.reporting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.imt.tournamentmaster.model.match.Match;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsonReportingService implements ReportingService {

    // ObjectMapper configuré pour supporter les types Java 8 date/time (LocalDateTime, etc.)
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    @Override
    public String report(Match match) throws IOException {
        return objectMapper.writeValueAsString(match);
    }
}
