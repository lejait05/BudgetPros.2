package com.example.budgetpros.model;



import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "goals")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn (name="user_id")
    private User user;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 100, nullable = false)
    private String dateCreated;

    @Column(length = 100, nullable = false)
    private String endDate;

    @Column(columnDefinition = "INT", nullable = false)
    private int goalAmount;

    @Column(columnDefinition = "INT", nullable = false)
    private int currentAmount;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "goal")
    private List<Transaction> transactions;

    public Goal() {
    }

    public Goal(User user, String title, String dateCreated, String endDate, int goalAmount, int currentAmount, List<Transaction> transactions) {
        this.user = user;
        this.title = title;
        this.dateCreated = dateCreated;
        this.endDate = endDate;
        this.goalAmount = goalAmount;
        this.currentAmount = currentAmount;
        this.transactions = transactions;
    }

    public Goal(long id, User user, String title, String dateCreated, String endDate, int goalAmount, int currentAmount, List<Transaction> transactions) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.dateCreated = dateCreated;
        this.endDate = endDate;
        this.goalAmount = goalAmount;
        this.currentAmount = currentAmount;
        this.transactions = transactions;
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

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getGoalAmount() {
        return goalAmount;
    }

    public void setGoalAmount(int goalAmount) {
        this.goalAmount = goalAmount;
    }

    public int getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(int currentAmount) {
        this.currentAmount = currentAmount;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", dateCreated=" + dateCreated +
                ", endDate='" + endDate + '\'' +
                ", goalAmount=" + goalAmount +
                ", currentAmount=" + currentAmount +
                ", transactions=" + transactions +
                '}';
    }
}
