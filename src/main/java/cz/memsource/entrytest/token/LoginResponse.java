package cz.memsource.entrytest.token;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
class LoginResponse {

    private String token;
    private OffsetDateTime expires;
}
