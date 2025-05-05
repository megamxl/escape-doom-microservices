package at.escapedoom.player.data.code_exec;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class PistonRequest {
    private String language;
    private String version;
    private List<CodeFile> files;

    public PistonRequest(String language, String version, List<CodeFile> files) {
        this.language = language;
        this.version = version;
        this.files = files;
    }

    @Builder
    @Data
    public static class CodeFile {
        private String name;
        private String content;

        public CodeFile(String name, String content) {
            this.name = name;
            this.content = content;
        }
    }
}
