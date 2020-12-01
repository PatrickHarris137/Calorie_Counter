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



}
