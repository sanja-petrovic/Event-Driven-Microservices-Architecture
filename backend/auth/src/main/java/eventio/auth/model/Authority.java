package eventio.auth.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "authorities")
@NoArgsConstructor
public class Authority implements GrantedAuthority {
    @Id
    private UUID id = UUID.randomUUID();
    @Column
    @Enumerated(EnumType.STRING)
    private AuthorityType type;
    @Override
    public String getAuthority() {
        return type.toString();
    }

    public Authority(AuthorityType type) {
        this.type = type;
    }
}
