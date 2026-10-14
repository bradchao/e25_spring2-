package tw.brad.spinga.dto.response;

import java.util.List;

public record AskResponse(
        String answer,
        String dataSource,
        List<ToolTrace> toolCalls
) {
}
