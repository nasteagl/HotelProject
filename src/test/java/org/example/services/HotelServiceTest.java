package org.example.services;

import org.example.dto.HotelDto;
import org.example.models.Hotel;
import org.example.repositories.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HotelServiceTest {

    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private HotelService hotelService;

    private Hotel initialHotel;
    private HotelDto initialHotelDto;

    @BeforeEach
    void setUp() {
        initialHotel = Hotel.builder()
                .hotel_id(1)
                .hotelCountry("Country")
                .hotelCity("City")
                .hotelAddress("Address")
                .hotelEmail("email@example.com")
                .hotelPhone("060000000")
                .build();

        initialHotelDto = HotelDto.fromHotel(initialHotel);
    }


    @Test
    void getHotelsTest() {
        // when
        hotelService.getHotels()    ;

        // then
        verify(hotelRepository).findAllHotels();
    }

    @Test
    void getHotelTest() {
        // given
        given(hotelRepository.findByIdHotel(anyInt())).willReturn(
                Hotel.builder()
                        .hotel_id(1)
                        .hotelCountry("Country")
                        .hotelCity("City")
                        .hotelAddress("Address")
                        .hotelEmail("email@example.com")
                        .hotelPhone("060000000")
                        .build()
        );

        // when
        hotelService.getHotel(1);

        // then
        verify(hotelRepository).findByIdHotel(1);
    }

    @Test
    void getHotelAsNullTest() {

        // given
        given(hotelRepository.findByIdHotel(1)).willReturn(null);

        // when
        hotelService.getHotel(1);

        // then
        verify(hotelRepository).findByIdHotel(1);
    }

    @Test
    void addHotelTest() {
        // when
        hotelService.addHotel(initialHotelDto);

        // then
        ArgumentCaptor<Hotel> hotelCaptor = ArgumentCaptor.forClass(Hotel.class);
        verify(hotelRepository).saveHotel(hotelCaptor.capture());
        Hotel capturedHotel = hotelCaptor.getValue();
        assertEquals(initialHotel, capturedHotel);
    }

    @Test
    void updateHotelTest() {
        // given
        Hotel actualHotel = Hotel.builder()
                .hotel_id(1)
                .hotelCountry("Country")
                .hotelCity("City")
                .hotelAddress("Address")
                .hotelEmail("email@example.com")
                .hotelPhone("060000000")
                .build();

        // when (save initialHotel)
        hotelService.addHotel(initialHotelDto);

        // then (save initialHotel)
        ArgumentCaptor<Hotel> hotelCaptor = ArgumentCaptor.forClass(Hotel.class);
        verify(hotelRepository).saveHotel(hotelCaptor.capture());
        Hotel capturedHotel = hotelCaptor.getValue();
        assertEquals(initialHotel, capturedHotel);

        // when (update actualHotel)
        hotelService.updateHotel(HotelDto.fromHotel(actualHotel));

// then (update actualHotel)
        ArgumentCaptor<Hotel> hotelCaptor2 = ArgumentCaptor.forClass(Hotel.class);
        verify(hotelRepository).saveHotel(hotelCaptor2.capture());
        Hotel capturedHotel2 = hotelCaptor2.getValue();
        assertEquals(actualHotel, capturedHotel2);
    }

    @Test
    void deleteHotelTest() {
        // given
        Hotel actualHotel = Hotel.builder()
                .hotel_id(1)
                .hotelCountry("Country")
                .hotelCity("City")
                .hotelAddress("Address")
                .hotelEmail("email@example.com")
                .hotelPhone("060000000")
                .build();

        //when save actualHotel
        hotelService.addHotel(HotelDto.fromHotel(actualHotel));

        // then (save actualHotel)
        ArgumentCaptor<Hotel> hotelCaptor = ArgumentCaptor.forClass(Hotel.class);
        verify(hotelRepository).saveHotel(hotelCaptor.capture());
        Hotel capturedHotel = hotelCaptor.getValue();
        assertEquals(initialHotel, capturedHotel);

        //when delete hotel
        hotelService.deleteHotel(1);

        //then delete hotel
        verify(hotelRepository).deleteById(1);
    }

    @Test
    void patchHotelTest() {
        //given
        given(hotelRepository.findByIdHotel(anyInt())).willReturn(
                Hotel.builder()
                        .hotel_id(1)
                        .hotelCountry("Country")
                        .hotelCity("City")
                        .hotelAddress("Address")
                        .hotelEmail("email@example.com")
                        .hotelPhone("060000000")
                        .build()
        );

        Hotel actualHotelSameVals = Hotel.builder()
                .hotel_id(1)
                .hotelCountry("Country")
                .hotelCity("City")
                .hotelAddress("Address")
                .hotelEmail("email@example.com")
                .hotelPhone("060000000")
                .build();

        Hotel actualHotelNullVals = Hotel.builder()
                .hotel_id(null)
                .hotelCountry(null)
                .hotelCity(null)
                .hotelAddress(null)
                .hotelEmail(null)
                .hotelPhone(null)
                .build();

        Hotel actualHotelAllWrongVals = Hotel.builder()
                .hotel_id(1)
                .hotelCountry("")
                .hotelCity("")
                .hotelAddress("")
                .hotelEmail("")
                .hotelPhone("")
                .build();

        Hotel actualHotelSomeWrongVals = Hotel.builder()
                .hotel_id(1)
                .hotelCountry("null")
                .hotelCity("null")
                .hotelAddress("Address")
                .hotelEmail("email@example.com")
                .hotelPhone("060000000")
                .build();

        //when save initialhotel
        hotelService.addHotel(initialHotelDto);

        //then save initialhotel
        ArgumentCaptor<Hotel> hotelCaptor = ArgumentCaptor.forClass(Hotel.class);
        verify(hotelRepository).saveHotel(hotelCaptor.capture());
        Hotel capturedHotel = hotelCaptor.getValue();
        assertEquals(initialHotel, capturedHotel);

        //when patch hotel with same vals
        hotelService.patchHotel(1, HotelDto.fromHotel(actualHotelSameVals));

        //then patch hotel with same vals
        assertAll(
                () -> assertEquals(1,actualHotelSameVals.getHotel_id(), "Incorrect hotel ID"),
                () -> assertEquals("Country",actualHotelSameVals.getHotelCountry(), "incorrect name"),
                () -> assertEquals("City",actualHotelSameVals.getHotelCity(), "incorrect CITY"),
                () -> assertEquals("Address",actualHotelSameVals.getHotelAddress(), "incorrect address"),
                () -> assertEquals("email@example.com",actualHotelSameVals.getHotelEmail(), "incorrect email"),
                () -> assertEquals("060000000",actualHotelSameVals.getHotelPhone(), "incorrect phone")
        );

// when patch hotel with null vals
        hotelService.patchHotel(1, HotelDto.fromHotel(actualHotelNullVals));

//then patch hotel with null vals
        assertAll(
                () -> assertNull(actualHotelSameVals.getHotel_id(), "Incorrect hotel ID"),
                () -> assertNull(actualHotelSameVals.getHotelCountry(), "incorrect name"),
                () -> assertNull(actualHotelSameVals.getHotelCity(), "incorrect CITY"),
                () -> assertNull(actualHotelSameVals.getHotelAddress(), "incorrect address"),
                () -> assertNull(actualHotelSameVals.getHotelEmail(), "incorrect email"),
                () -> assertNull(actualHotelSameVals.getHotelPhone(), "incorrect phone")
        );

        //when patch with wrong values
        hotelService.patchHotel(1, HotelDto.fromHotel(actualHotelAllWrongVals));
        //then patch with wrong values
        assertAll(() -> assertEquals(1,actualHotelSameVals.getHotel_id(), "Incorrect hotel ID"),
                () -> assertEquals("",actualHotelSameVals.getHotelCountry(), "incorrect name"),
                () -> assertEquals("",actualHotelSameVals.getHotelCity(), "incorrect CITY"),
                () -> assertEquals("",actualHotelSameVals.getHotelAddress(), "incorrect address"),
                () -> assertEquals("",actualHotelSameVals.getHotelEmail(), "incorrect email"),
                () -> assertEquals("",actualHotelSameVals.getHotelPhone(), "incorrect phone")
        );

        //when patch hotel with some wrong vals
        hotelService.patchHotel(1, HotelDto.fromHotel(actualHotelAllWrongVals));
        //then patch hotel with some wrong vals
        assertAll(
                () -> assertEquals("",actualHotelSameVals.getHotelCountry(), "incorrect name"),
                () -> assertEquals("",actualHotelSameVals.getHotelCity(), "incorrect CITY")
        );

    }

    @Test
    void patchHotelAsNullTest() {
        given(hotelRepository.findByIdHotel(anyInt())).willReturn(null);

        hotelService.addHotel(initialHotelDto);
        ArgumentCaptor<Hotel> hotelCaptor = ArgumentCaptor.forClass(Hotel.class);
        verify(hotelRepository).saveHotel(hotelCaptor.capture());
        Hotel capturedHotel = hotelCaptor.getValue();
        assertEquals(initialHotel, capturedHotel);

        assertThrows(NullPointerException.class, () -> hotelService.patchHotel(1, initialHotelDto), "Hotel not found");
        verify(hotelRepository, never()).updateHotel(initialHotel);
    }
}