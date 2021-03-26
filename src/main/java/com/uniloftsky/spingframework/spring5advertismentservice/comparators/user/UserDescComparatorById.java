package com.uniloftsky.spingframework.spring5advertismentservice.comparators.user;

import com.uniloftsky.spingframework.spring5advertismentservice.model.User;

import java.util.Comparator;

public class UserDescComparatorById implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        return o2.getId().compareTo(o1.getId());
    }
}
