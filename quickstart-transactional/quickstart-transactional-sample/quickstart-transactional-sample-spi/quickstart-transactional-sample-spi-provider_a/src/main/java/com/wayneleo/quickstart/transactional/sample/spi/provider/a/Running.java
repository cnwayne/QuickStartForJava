package com.wayneleo.quickstart.transactional.sample.spi.provider.a;

import com.wayneleo.quickstart.transactional.sample.spi.api.Something;

public class Running implements Something {
    @Override
    public String toString() {
        return "Hello, I am Running !";
    }
}
