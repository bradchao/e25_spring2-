package tw.brad.springc.dto.response;

import java.util.List;

public record TableResult(
        List<String> columns,
        List<List<Object>> rows,
        String note
) {
}
