package com.example.demo.util;

import com.example.demo.exception.BadRequestException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public final class DateUtil {

    private DateUtil() {
        // Prevent instantiation
    }

    // ------------------ BASIC DATE CHECKS ------------------

    public static void requireNotNull(LocalDate date, String fieldName) {
        if (date == null) {
            throw new BadRequestException(fieldName + " is required");
        }
    }

    public static void requireNotFuture(LocalDate date, String fieldName) {
        requireNotNull(date, fieldName);
        if (date.isAfter(LocalDate.now())) {
            throw new BadRequestException(fieldName + " cannot be in the future");
        }
    }

    public static void requirePast(LocalDate date, String fieldName) {
        requireNotNull(date, fieldName);
        if (!date.isBefore(LocalDate.now())) {
            throw new BadRequestException(fieldName + " must be in the past");
        }
    }

    public static void requirePastOrToday(LocalDate date, String fieldName) {
        requireNotNull(date, fieldName);
        if (date.isAfter(LocalDate.now())) {
            throw new BadRequestException(fieldName + " cannot be after today");
        }
    }

    // ------------------ DATE RANGE CHECK ------------------

    public static void requireValidRange(LocalDate start,
                                         LocalDate end,
                                         String startName,
                                         String endName) {

        requireNotNull(start, startName);
        requireNotNull(end, endName);

        if (end.isBefore(start)) {
            throw new BadRequestException(endName + " cannot be before " + startName);
        }
    }

    // ------------------ TIME UTILITIES ------------------

    public static LocalDate today() {
        return LocalDate.now();
    }

    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    // ------------------ SAFE CONVERSIONS ------------------

    public static LocalDate toLocalDate(LocalDateTime dateTime) {
        if (dateTime == null) {
            throw new BadRequestException("DateTime cannot be null");
        }
        return dateTime.toLocalDate();
    }

    public static LocalDateTime atStartOfDay(LocalDate date) {
        requireNotNull(date, "Date");
        return date.atStartOfDay();
    }
}
