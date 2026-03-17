package dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class ContactsDto {

    private List<Contact> contacts;
}
