package com.redcode.demo.java8.lambda;

/**
 * Created by zhiyu.zhou on 2017/9/4.
 */
public class FilterFriendForHeight implements MyOption<Friend> {
    @Override
    public boolean test(Friend friend) {
        return friend.getHeight() > 185;
    }
}
