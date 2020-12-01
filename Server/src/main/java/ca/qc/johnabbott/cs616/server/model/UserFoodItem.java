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

    @Column(name = "numberofserving")
    private int numberOfServing;

    @Column(name = "meal")
    @Enumerated(EnumType.STRING)
    private Meal meal;

    @Column(name = "totalcalories")
    private int totalCalories;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    public UserDailyConsumption getUserDailyConsumption() {
        return userDailyConsumption;
    }

    public void setUserDailyConsumption(UserDailyConsumption userDailyConsumption) {
        this.userDailyConsumption = userDailyConsumption;
    }

    public int getNumberOfServing() {
        return numberOfServing;
    }

    public void setNumberOfServing(int numberOfServing) {
        this.numberOfServing = numberOfServing;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(int totalCalories) {
        this.totalCalories = totalCalories;
    }
}
