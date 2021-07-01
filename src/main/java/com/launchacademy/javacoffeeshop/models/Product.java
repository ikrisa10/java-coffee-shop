package com.launchacademy.javacoffeeshop.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter
@Setter
public class Product {

  private Integer id;
  private String name;
  private String description;
  private Double price;
  private Integer caffeineRating;

  public String getIndicator() {
    if (this.caffeineRating == 0) {
      return "caffeine-rating-0";
    } else if (this.caffeineRating == 1) {
      return "caffeine-rating-1";
    } else {
      return "caffeine-rating-2";
    }
  }

  public String validate() {
    String errors = "";
    if (this.name == "" || this.name == null) {
      errors += "!!! Name can't be blank !!!\n";
    }
    if (this.description == "" || this.description == null) {
      errors += "!!! Description can't be blank !!!\n";
    }
    if (this.price == null || this.price < 0) {
      errors += "!!! Price can't be blank or negative !!!\n";
    }
    if (this.caffeineRating == null || this.caffeineRating > 3) {
      errors += "!!! Please make a valid rating selection !!!\n";
    }

    return errors;
  }
}
