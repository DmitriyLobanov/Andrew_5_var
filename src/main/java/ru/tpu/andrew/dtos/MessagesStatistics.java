package ru.tpu.andrew.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import ru.tpu.andrew.models.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessagesStatistics {
    private String sender;
    private String receiver;
    private Long count;

}
