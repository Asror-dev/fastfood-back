package com.example.demo.projection;

import java.util.UUID;

public interface ProductProjection {

    UUID getId();

    String getName();

    String getDescription();

    Double getPrice();
}
