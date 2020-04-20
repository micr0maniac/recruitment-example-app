package touk.cinema.domain.screeningroom;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ScreeningRoomSchema {

    private List<ScreeningRoomSchemaRow> rows;

    public ScreeningRoomSchema(List<ScreeningRoomSchemaRow> rows) {
        this.rows = rows;
    }

    public static ScreeningRoomSchema withFixedNumberOfSeatsPerRow(int rowsCount, int seatsPerRow) {
        List<ScreeningRoomSchemaRow> rows = IntStream.rangeClosed(1, rowsCount).mapToObj(rowIndex -> {
            int lowRange = ((rowIndex-1) * seatsPerRow) + 1;
            int highRange = lowRange + seatsPerRow;

            List<Seat> seats = IntStream.range(lowRange, highRange)
                    .mapToObj(Seat::new)
                    .collect(Collectors.toList());

            return new ScreeningRoomSchemaRow(rowIndex, seats);
        }).collect(Collectors.toList());

        return new ScreeningRoomSchema(rows);
    }

    public List<ScreeningRoomSchemaRow> rows() {
        return rows;
    }
}
