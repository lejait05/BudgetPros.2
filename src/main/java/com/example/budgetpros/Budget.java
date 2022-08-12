package com.example.budgetpros;

import javax.persistence.*;

@Entity
@Table(name = "budget")

public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn("user_id")
    private User user;
    @Column(nullable = false, length = 240)
    private String title;
    @Column(columnDefinition = "INT")
    private int amount;

    public Budget(long id, User user, String title, int amount) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
