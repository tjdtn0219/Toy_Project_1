package org.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileType {
    JSON("JSON"), CSV("CSV");

    private String type;
}
