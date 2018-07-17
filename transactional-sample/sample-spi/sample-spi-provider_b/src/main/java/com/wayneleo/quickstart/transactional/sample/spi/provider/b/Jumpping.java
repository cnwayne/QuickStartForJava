package com.wayneleo.quickstart.transactional.sample.spi.provider.b;

import com.wayneleo.quickstart.transactional.sample.spi.api.Something;

public class Jumpping implements Something {
    @Override
    public String toString() {
        return "Hello, I am Jumpping !";
    }
}
