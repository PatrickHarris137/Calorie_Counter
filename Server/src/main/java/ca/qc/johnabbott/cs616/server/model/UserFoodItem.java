package ca.qc.johnabbott.cs616.server.model;

import javax.persistence.*;

@Entity
@Table(name = "userfooditem")
public class UserFoodItem {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(targetEntity = FoodItem.class)
    @JoinColumn(name = "fooditem")
    private FoodItem foodItem;

    @ManyToOne(targetEntity = UserDailyConsumption.class)
    @JoinColumn(name = "userdailyconsumption")
    private UserDailyConsumption userDailyConsumption;



}
