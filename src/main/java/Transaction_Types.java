import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "transaction_types")

public class Transaction_Types {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(length = 120, nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transaction_type")
    private List<Transaction>transactions;

    public Transaction_Types() {

    }

    public Transaction_Types(String name) {
        this.name = name;
    }

    public Transaction_Types(long id, String name, List<Transaction> transactions) {
        this.id = id;
        this.name = name;
        this.transactions = transactions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
