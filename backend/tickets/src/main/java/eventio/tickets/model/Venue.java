package eventio.tickets.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "venues")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Venue {
    @Id
    UUID id;
    @Column
    int capacity;
}
