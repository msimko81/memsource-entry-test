package cz.memsource.entrytest.request;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "request_kinds")
public class RequestKind {

    @Id
    private Long id;

    private String messageCode;
}
