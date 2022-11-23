package ru.tpu.andrew.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    private Long userId;
    private String text;
    private String sender;
    private String receiver;
}
