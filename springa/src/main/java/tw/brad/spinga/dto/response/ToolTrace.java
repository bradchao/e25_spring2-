package tw.brad.spinga.dto.response;

public record ToolTrace(
        String tool,
        Object args,
        ProductLookup result
) {
}
