package cz.memsource.entrytest.util;

import java.time.OffsetDateTime;
import java.time.temporal.TemporalAmount;

public interface TimeUtil {

    /**
     * Checks whether the given timestamp is in far enough future.
     * Useful for token expiration check.
     *
     * @param timestamp timestamp to check
     * @param offset    time offset
     * @return whether the currentTimestamp plus the given offset is still before the given timestamp
     */
    boolean isTimestampInFarEnoughFuture(OffsetDateTime timestamp, TemporalAmount offset);
}
