package com.webchat.webchat_be.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class ChatmessageUpdateVO extends ChatmessageVO implements Serializable {
    private static final long serialVersionUID = 1L;

}
