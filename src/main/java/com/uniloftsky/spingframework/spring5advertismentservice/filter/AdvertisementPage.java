package com.uniloftsky.spingframework.spring5advertismentservice.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class AdvertisementPage {

    private int pageNumber = 0;
    private int pageSize = 6;
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sortBy = "id";

}
