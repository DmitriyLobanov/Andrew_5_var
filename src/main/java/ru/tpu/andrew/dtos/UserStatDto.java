package ru.tpu.andrew.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStatDto {
    private String receiverUsername;
    private Long messagesReceived;
}
