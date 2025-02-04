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
        //given
        given(hotelRepository.saveHotel(initialHotel)).willReturn(initialHotel);

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
        given(hotelRepository.saveHotel(initialHotel)).willReturn(initialHotel);
        Hotel actualHotel = Hotel.builder()
                .hotel_id(1)
                .hotelCountry("Country")
                .hotelCity("City")
                .hotelAddress("Address")
                .hotelEmail("email@example.com")
                .hotelPhone("060000000")
                .build();
        given(hotelRepository.updateHotel(actualHotel)).willReturn(actualHotel);

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
        given(hotelRepository.saveHotel(initialHotel)).willReturn(initialHotel);

        //when save Hotel
        hotelService.addHotel(HotelDto.fromHotel(initialHotel));

        //then (save Hotel)
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
        given(hotelRepository.saveHotel(initialHotel)).willReturn(initialHotel);
        given(hotelRepository.findByIdHotel(anyInt())).willReturn(initialHotel);

        Hotel actualHotelSameVals = initialHotel;

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
                .hotelCountry("")
                .hotelCity("")
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
                () -> assertNull(actualHotelNullVals.getHotelCountry(), "incorrect name"),
                () -> assertNull(actualHotelNullVals.getHotelCity(), "incorrect CITY"),
                () -> assertNull(actualHotelNullVals.getHotelAddress(), "incorrect address"),
                () -> assertNull(actualHotelNullVals.getHotelEmail(), "incorrect email"),
                () -> assertNull(actualHotelNullVals.getHotelPhone(), "incorrect phone")
        );

        //when patch with wrong values
        hotelService.patchHotel(1, HotelDto.fromHotel(actualHotelAllWrongVals));
        //then patch with wrong values
        assertAll(
                () -> assertEquals("",actualHotelAllWrongVals.getHotelCountry(), "incorrect name"),
                () -> assertEquals("",actualHotelAllWrongVals.getHotelCity(), "incorrect CITY"),
                () -> assertEquals("",actualHotelAllWrongVals.getHotelAddress(), "incorrect address"),
                () -> assertEquals("",actualHotelAllWrongVals.getHotelEmail(), "incorrect email"),
                () -> assertEquals("",actualHotelAllWrongVals.getHotelPhone(), "incorrect phone")
        );

        //when patch hotel with some wrong vals
        hotelService.patchHotel(1, HotelDto.fromHotel(actualHotelSomeWrongVals));
        //then patch hotel with some wrong vals
        assertAll(
                () -> assertEquals("",actualHotelSomeWrongVals.getHotelCountry(), "incorrect name"),
                () -> assertEquals("",actualHotelSomeWrongVals.getHotelCity(), "incorrect CITY")
        );

    }

    @Test
    void patchHotelAsNullTest() {
        given(hotelRepository.saveHotel(initialHotel)).willReturn(initialHotel);
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