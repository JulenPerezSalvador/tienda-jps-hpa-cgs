package es.iesclaradelrey.da2d1a.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RefreshRequestDTO {
    @NotBlank private String refreshToken;
}
