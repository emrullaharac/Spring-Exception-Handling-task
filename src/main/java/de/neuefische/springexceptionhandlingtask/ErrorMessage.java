package de.neuefische.springexceptionhandlingtask;

import java.time.Instant;

public record ErrorMessage(String message, Instant timestamp) {
}
