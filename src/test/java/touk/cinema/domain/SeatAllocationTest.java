package touk.cinema.domain;

import com.google.common.collect.ImmutableSet;
import org.junit.jupiter.api.Test;
import touk.cinema.domain.screeningroom.ScreeningRoom;
import touk.cinema.domain.screeningroom.ScreeningRoomSchema;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SeatAllocationTest {

    @Test
    public void seatAllocationTest() {
        ScreeningRoom screeningRoom = new ScreeningRoom("Dummy", ScreeningRoomSchema.withFixedNumberOfSeatsPerRow(1, 6));

        Set<Integer> seatsInRow = ImmutableSet.of(1, 2, 3);
        assertTrue(screeningRoom.isAbleToAllocateSeats(seatsInRow));

        Set<Integer> oddSeats = ImmutableSet.of(1, 3, 5);
        assertFalse(screeningRoom.isAbleToAllocateSeats(oddSeats));

        Set<Integer> seatsWithOneLeftFromLeftEdge = ImmutableSet.of(2, 3);
        assertFalse(screeningRoom.isAbleToAllocateSeats(seatsWithOneLeftFromLeftEdge));

        Set<Integer> seatsWithOneLeftFromRightEdge = ImmutableSet.of(4, 5);
        assertFalse(screeningRoom.isAbleToAllocateSeats(seatsWithOneLeftFromRightEdge));
    }

}
