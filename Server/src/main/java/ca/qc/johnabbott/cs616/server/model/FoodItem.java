package ca.qc.johnabbott.cs616.server.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="fooditem")
public class FoodItem {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name="type")
    private String type;

    @Column(name = "servingsize")
    private int servingSize;

    @Column(name = "calories")
    private int calories;

    @OneToOne
    @JoinColumn(name = "macronutrient")
    private MacroNutrient macroNutrient;

    @Column(name="category")
    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToMany(mappedBy = "foodItems")
    private List<UserDailyConsumption> userDailyConsumptions;

    @OneToMany(mappedBy = "foodItem")
    List<UserFoodItem> userFoodItems;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getServingSize() {
        return servingSize;
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public MacroNutrient getMacroNutrient() {
        return macroNutrient;
    }

    public void setMacroNutrient(MacroNutrient macroNutrient) {
        this.macroNutrient = macroNutrient;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<UserDailyConsumption> getUserDailyConsumptions() {
        return userDailyConsumptions;
    }

    public void setUserDailyConsumptions(List<UserDailyConsumption> userDailyConsumptions) {
        this.userDailyConsumptions = userDailyConsumptions;
    }

    public List<UserFoodItem> getUserFoodItems() {
        return userFoodItems;
    }

    public void setUserFoodItems(List<UserFoodItem> userFoodItems) {
        this.userFoodItems = userFoodItems;
    }
}
