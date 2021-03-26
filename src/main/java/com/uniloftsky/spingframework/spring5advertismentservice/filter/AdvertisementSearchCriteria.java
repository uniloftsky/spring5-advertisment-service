package com.uniloftsky.spingframework.spring5advertismentservice.filter;

import com.uniloftsky.spingframework.spring5advertismentservice.model.Category;
import com.uniloftsky.spingframework.spring5advertismentservice.model.City;
import com.uniloftsky.spingframework.spring5advertismentservice.model.Region;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AdvertisementSearchCriteria {

    private Category category;
    private City city;
    private Region region;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String checkSalary;
    private String keyword;

}
