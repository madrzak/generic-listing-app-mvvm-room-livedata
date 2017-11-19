package com.madrzak.mygenericlistingapp.util;

import android.support.annotation.NonNull;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class ExclusionEliminationStrategy implements ExclusionStrategy {

    public enum Direction {
        SERIALIZATION,
        DESERIALIZATION
    }

    private Direction direction;

    public ExclusionEliminationStrategy(@NonNull Direction direction) {
        this.direction = direction;
    }

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return validateAnnotation(f.getAnnotation(Eliminated.class));
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(Eliminated.class)) return false;
        Eliminated annotation = clazz.getAnnotation(Eliminated.class);
        return validateAnnotation(annotation);
    }

    private boolean validateAnnotation(Eliminated annotation) {
        return annotation != null
                && (annotation.eliminateDeserialization() && direction == Direction.DESERIALIZATION
                || annotation.eliminateSerialization() && direction == Direction.SERIALIZATION);
    }
}