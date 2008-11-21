package org.eclipse.swordfish.samples.cxf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.eclipse.swordfish.samples.cxf.domain.Flight;
import org.eclipse.swordfish.samples.cxf.domain.Passenger;
import org.eclipse.swordfish.samples.cxf.domain.Reservation;




/**
 *
 * The class is not threadsafe
 * @author vzhabiuk
 *
 */
@WebService(endpointInterface = "org.eclipse.swordfish.samples.cxf.BookingService",
        serviceName = "BookingServiceImpl" )
public class BookingServiceImpl implements BookingService {
	private static int passengerId;
	private static int flightId;
	private static int bookingId;
	private static Map<Integer, Reservation> reservationStorage = new HashMap<Integer, Reservation>();
	public int createReservation(@WebParam(name = "passengers") List<Passenger> passengers, @WebParam(name = "flight") Flight flight) {
		if (flight == null) {
			throw new IllegalArgumentException("The supplied flight is null");
		}

		int reservationId = ++bookingId;
		Reservation reservation = new Reservation();
		reservation.setId(reservationId);
		reservation.setFlight(flight);
		reservation.setPassengers(passengers);
		reservationStorage.put(reservationId, reservation);
		return reservationId;
	}

	public Reservation findReservation(int reservationId) {
		return reservationStorage.get(reservationId);
	}

}
