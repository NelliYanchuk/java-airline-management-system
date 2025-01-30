```
dev.nyanchuk.airline
│
├── user
│   ├── User.java
│   ├── UserRepository.java
│   ├── UserService.java
│   ├── UserController.java
│   ├── UserDTO.java
│   ├── exception
│       ├── UserNotFoundException.java
│
├── flight
│   ├── Flight.java
│   ├── FlightRepository.java
│   ├── FlightService.java
│   ├── FlightController.java
│   ├── FlightDTO.java
│   ├── exception
│       ├── FlightNotFoundException.java
│
├── reservation
│   ├── Reservation.java
│   ├── ReservationRepository.java
│   ├── ReservationService.java
│   ├── ReservationController.java
│   ├── ReservationDTO.java
│   ├── exception
│       ├── ReservationNotFoundException.java
│
├── airport
│   ├── Airport.java
│   ├── AirportRepository.java
│   ├── AirportService.java
│   ├── AirportController.java
│   ├── AirportDTO.java
│   ├── exception
│       ├── AirportNotFoundException.java
│
├── config
│   ├── SecurityConfig.java
│   ├── WebConfig.java
│
├── profile
│   ├── ProfileController.java
│   ├── ProfileService.java
│
├── register
│   ├── RegisterController.java
│   ├── RegisterService.java
│
├── role
│   ├── Role.java
│   ├── RoleRepository.java
│
├── security
│   ├── SecurityConfig.java
│
├── common/
│   ├── GlobalExceptionHandler.java
│
└── AirlineApplication.java
```

Admin Users: 
Can access and modify any endpoint (/api/users/**, /api/airports/**, /api/flights/**, /api/reservations/**).

Regular Users:
Can read flights and airports (/api/flights/**, /api/airports/**).
Can read and update only their own reservations (/api/reservations/**).