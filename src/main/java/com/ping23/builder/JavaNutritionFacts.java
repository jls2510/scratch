package com.ping23.builder;

public class JavaNutritionFacts {
    private final String name;
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public static class Builder {
        // Required parameters
        private final int servingSize;
        private final int servings;

        // Optional parameters - initialized to default values
        private String name = "";
        private int calories      = 0;
        private int fat           = 0;
        private int carbohydrate  = 0;
        private int sodium        = 0;

        public Builder(String name, int servingSize, int servings) {
            this.name = name;
            this.servingSize = servingSize;
            this.servings    = servings;
        }

        public Builder calories(int val)
        { calories = val;      return this; }
        public Builder fat(int val)
        { fat = val;           return this; }
        public Builder carbohydrate(int val)
        { carbohydrate = val;  return this; }
        public Builder sodium(int val)
        { sodium = val;        return this; }

        public JavaNutritionFacts build() {
            return new JavaNutritionFacts(this);
        }
    }

    private JavaNutritionFacts(Builder builder) {
        name         = builder.name;
        servingSize  = builder.servingSize;
        servings     = builder.servings;
        calories     = builder.calories;
        fat          = builder.fat;
        sodium       = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }
    
    @Override
    public String toString() {
        return "JavaNutritionFacts for " + name + ": calories: " + calories;
    }
}
