package touk.cinema.domain.screeningroom;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ScreeningRoom {

    private ScreeningRoomId id;

    private String name;

    private ScreeningRoomSchema schema;

    public ScreeningRoom() {
    }

    public ScreeningRoom(String name, ScreeningRoomSchema schema) {
        this.id = new ScreeningRoomId();
        this.name = name;
        this.schema = schema;
    }

    public ScreeningRoomSchema schema() {
        return schema;
    }

    public boolean isAbleToAllocateSeats(Set<Integer> seats) {
        List<String> binary = schema.rows().stream()
            .map(row -> {
                return row.seats().stream()
                        .map(seat -> seats.contains(seat.number()) ? "1" : "0")
                        .collect(Collectors.joining());
            })
            .collect(Collectors.toList());

        String hasWidowPattern = "01.+|.+10|.+101.+";
        long rowsContainingWidows = binary.stream()
                .filter(s -> s.matches(hasWidowPattern))
                .count();

        return rowsContainingWidows == 0;
    }
}
