package com.maingocdieu.SportShop.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class AbstractDTO<T> implements Serializable {
	private static final long serialVersionUID = 2924702747727365997L;

    private Long id;
    private Date createdDate;
    private String createdBy;
    private Date modifiedDate;
    private String modifiedBy;
    private Boolean isDeleted;
    private int page = 1;
    private int maxPageItems = 5;
    private int totalItems = 0;
    private int totalPages=0;
    private List<T> listResult = new ArrayList<>();
    private String tableId = "tableList";

    
}
