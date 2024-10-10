package procheck.cih.support.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Authorities")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Authorities {

	@Id
	private String  authority;
}
