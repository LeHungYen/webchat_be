package com.webchat.webchat_be.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class UservideoUpdateVO extends UservideoVO implements Serializable {
    private static final long serialVersionUID = 1L;

}
