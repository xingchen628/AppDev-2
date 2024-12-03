package com.example.groupproject.entities;

public class Statistics {
  private Double averageAge;
  private Integer oldestAge;

  public Statistics(Double averageAge, Integer oldestAge) {
    this.averageAge = averageAge;
    this.oldestAge = oldestAge;
  }

  // Getters and Setters (if needed)
  public Double getAverageAge() {
    return averageAge;
  }

  public void setAverageAge(Double averageAge) {
    this.averageAge = averageAge;
  }

  public Integer getOldestAge() {
    return oldestAge;
  }

  public void setOldestAge(Integer oldestAge) {
    this.oldestAge = oldestAge;
  }
}

