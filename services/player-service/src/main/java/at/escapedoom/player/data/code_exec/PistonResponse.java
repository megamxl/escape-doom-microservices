package at.escapedoom.player.data.code_exec;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PistonResponse {
    public String language;
    public String version;
    public Run run;

    @Builder
    @Data
    public static class Run {
        public String stdout;
        public String stderr;
        public int code;
        public String signal;
        public String output;
    }
}
