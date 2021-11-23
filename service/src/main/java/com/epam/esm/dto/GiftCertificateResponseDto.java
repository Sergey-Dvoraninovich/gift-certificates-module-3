package com.epam.esm.dto;

import com.epam.esm.dto.mapping.DurationDeserializer;
import com.epam.esm.dto.mapping.DurationSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GiftCertificateResponseDto {
    @ApiModelProperty(notes = "The database generated GiftCertificate ID")
    private long id;

    @ApiModelProperty(notes = "The GiftCertificate name")
    private String name;

    @ApiModelProperty(notes = "The GiftCertificate description")
    private String description;

    @ApiModelProperty(notes = "The GiftCertificate price")
    private BigDecimal price;

    @JsonSerialize(using = DurationSerializer.class)
    @JsonDeserialize(using = DurationDeserializer.class)
    @ApiModelProperty(notes = "The GiftCertificate duration")
    private Duration duration;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "Europe/Minsk")
    @ApiModelProperty(notes = "The date GiftCertificate got created")
    private Instant createDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone = "Europe/Minsk")
    @ApiModelProperty(notes = "The last date GiftCertificate got updated")
    private Instant lastUpdateDate;

    @ApiModelProperty(notes = "The Tags related to GiftCertificate")
    private List<TagDto> tagsDto;
}
