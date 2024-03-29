package com.webchat.webchat_be.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserfollowingUpdateVO extends UserfollowingVO implements Serializable {
    private static final long serialVersionUID = 1L;

}
