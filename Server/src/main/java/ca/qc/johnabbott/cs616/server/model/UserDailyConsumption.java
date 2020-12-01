package ca.qc.johnabbott.cs616.server.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "userdailyconsumption")
public class UserDailyConsumption {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user")
    private User user;

    @OneToMany(mappedBy = "userDailyConsumption")
    private List<UserFoodItem> userFoodItems;

    @Column(name="date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToMany
    @JoinTable(
            name = "userfooditem",
            joinColumns = @JoinColumn(name = "userdailyconsumption"),
            inverseJoinColumns = @JoinColumn(name = "fooditem")
    )
    private List<FoodItem> foodItems;

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

    public List<UserFoodItem> getUserFoodItems() {
        return userFoodItems;
    }

    public void setUserFoodItems(List<UserFoodItem> userFoodItems) {
        this.userFoodItems = userFoodItems;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
