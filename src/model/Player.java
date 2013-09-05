/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import impl.QuestionAnswerConsequenceImpl;

/**
 *
 * @author lchastee
 */
public class Player {
    private String name;
    // resources = the men or other    
    private int age, amountOfGold, resources, health;
 
    //private QuestListener listener;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAmountOfGold() {
        return amountOfGold;
    }

    public void setAmountOfGold(int amountOfGold) {
        this.amountOfGold = amountOfGold;
    }

    public int getResources() {
        return resources;
    }

    public void setResources(int resources) {
        this.resources = resources;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

  
    
    
}
