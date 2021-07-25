package com.maingocdieu.SportShop.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserDto extends AbstractDTO<UserDto> {

  /**
	 * 
	 */
  private static final long serialVersionUID = -7308784974421033086L;

  private String username;

  private String password;

  private String fullName;

  private String email;

  private Integer status;

  private String address;

  private String phone;

  private List<Long> idRole;

  private int page;

  private int pageSize;
}
