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

classDiagram
    class Airport {
        Long id
        String name
        String location
        +getId(): Long
        +setId(Long): void
        +getName(): String
        +setName(String): void
        +getLocation(): String
        +setLocation(String): void
    }

    class Flight {
        Long id
        Airport origin
        Airport destination
        LocalDateTime departureDate
        LocalDateTime arrivalDate
        int availableSeats
        boolean status
        +getId(): Long
        +setId(Long): void
        +getOrigin(): Airport
        +setOrigin(Airport): void
        +getDestination(): Airport
        +setDestination(Airport): void
        +getDepartureDate(): LocalDateTime
        +setDepartureDate(LocalDateTime): void
        +getArrivalDate(): LocalDateTime
        +setArrivalDate(LocalDateTime): void
        +getAvailableSeats(): int
        +setAvailableSeats(int): void
        +isStatus(): boolean
        +setStatus(boolean): void
    }

    class Reservation {
        Long id
        User user
        Flight flight
        LocalDateTime reservationDate
        String status
        +getId(): Long
        +setId(Long): void
        +getUser(): User
        +setUser(User): void
        +getFlight(): Flight
        +setFlight(Flight): void
        +getReservationDate(): LocalDateTime
        +setReservationDate(LocalDateTime): void
        +getStatus(): String
        +setStatus(String): void
    }

    class User {
        Long id
        String username
        String password
        Role role
        String profileImageUrl
        +getId(): Long
        +setId(Long): void
        +getUsername(): String
        +setUsername(String): void
        +getPassword(): String
        +setPassword(String): void
        +getRole(): Role
        +setRole(Role): void
        +getProfileImageUrl(): String
        +setProfileImageUrl(String): void
    }

    class Role {
        <<enumeration>>
        USER
        ADMIN
    }

    Flight "1" --> "1" Airport : origin
    Flight "1" --> "1" Airport : destination
    Reservation "1" --> "1" Flight
    Reservation "1" --> "1" User
    User "1" --> "1" Role
