package com.redcode.demo.fastJson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by zhiyu.zhou on 2017/9/10.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiverInfo {

    private String receiverId;

    private String receiverName;

    private String receiverJob;
}
