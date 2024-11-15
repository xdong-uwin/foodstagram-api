package org.scraper.foodstagram.config;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GlobalError {
    private String error;
    private int status;
}
