package cz.lundegaard.entrytest.request;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "requests")
public class Request {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne
	private RequestKind requestKind;

	@NotBlank
	@Pattern(regexp = "^[a-zA-Z0-9]*$")
	private String policyNumber;

	@NotBlank
	//TODO: pattern pre meno a priezvisko povoli len ASCII znaky, nepusti diakritiku ani mena ako Mathias d'Arras, Hector Sausage-Hausen, Martin Luther King, Jr. 
	@Pattern(regexp = "^[a-zA-Z]*$")
	private String name;

	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]*$")
	private String surname;

	//TODO: pre testovacie ucely nastavene na 5 znakov
	@Size(max=5)
	private String text;
}
