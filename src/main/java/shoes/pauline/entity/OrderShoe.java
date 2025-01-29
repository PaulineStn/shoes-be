package shoes.pauline.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders_shoe")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderShoe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderShoeId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "shoe_id", nullable = false)
    private Shoe shoe;

    @Column(nullable = false)
    private Integer quantity;
}