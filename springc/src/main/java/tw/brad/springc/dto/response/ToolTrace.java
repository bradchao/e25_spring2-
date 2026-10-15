package tw.brad.springc.dto.response;

public record ToolTrace(String tool, Object arguments, TableResult result) {
}