package cz.memsource.entrytest.util;

import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.temporal.TemporalAmount;

@Service
public class TimeUtilImpl implements TimeUtil {

    @Override
    public boolean isTimestampInFarEnoughFuture(OffsetDateTime timestamp, TemporalAmount offset) {
        return OffsetDateTime.now().plus(offset).isBefore(timestamp);
    }
}
