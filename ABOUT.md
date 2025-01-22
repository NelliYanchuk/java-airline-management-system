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
│       ├── UserValidationException.java
│
├── flight
│   ├── Flight.java
│   ├── FlightRepository.java
│   ├── FlightService.java
│   ├── FlightController.java
│   ├── FlightDTO.java
│   ├── exception
│       ├── FlightNotFoundException.java
│       ├── FlightValidationException.java
│
├── reservation
│   ├── Reservation.java
│   ├── ReservationRepository.java
│   ├── ReservationService.java
│   ├── ReservationController.java
│   ├── ReservationDTO.java
│   ├── exception
│       ├── ReservationNotFoundException.java
│       ├── ReservationValidationException.java
│
├── airport
│   ├── Airport.java
│   ├── AirportRepository.java
│   ├── AirportService.java
│   ├── AirportController.java
│   ├── AirportDTO.java
│   ├── exception
│       ├── AirportNotFoundException.java
│       ├── AirportValidationException.java
│
├── auth
│   ├── AuthController.java
│   ├── JwtUtil.java
│   ├── JwtRequestFilter.java
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
│   ├── CustomUserDetailsService.java
│   ├── JwtAuthenticationEntryPoint.java
│
├── home
│   ├── HomeController.java
│
└── AirlineApplication.java