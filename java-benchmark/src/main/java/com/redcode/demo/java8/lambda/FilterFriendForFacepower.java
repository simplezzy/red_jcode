package com.redcode.demo.java8.lambda;

/**
 * Created by zhiyu.zhou on 2017/9/4.
 */
public class FilterFriendForFacepower implements MyOption<Friend> {

    @Override
    public boolean test(Friend friend) {
        return friend.getFacepower() > 90;
    }
}
