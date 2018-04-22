package com.ping23.builder;

public class JavaNutritionFactsClient {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        final JavaNutritionFacts cocaCola =
            new JavaNutritionFacts.Builder("Coca Cola", 240, 8).calories(100).sodium(35)
                .carbohydrate(27).build();
        
        System.out.println(cocaCola.toString());
        
    } // main()

}
