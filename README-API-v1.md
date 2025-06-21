# 1. Register Doctor

**POST**

```bash
curl --location 'http://localhost:8080/doctor' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=E845980308D6A810927092C78AC4A036' \
--data '{
"name": "Dr. Natthawut",
"specialty": "Internal Medicine",
"department": "Cardiology",
"subSpecialty": "Arrhythmia",
"bankAccountName": "ธนาคารไทยพาณิชย์",
"bankAccountNumber": "1234567890",
"licenseIssuedDate": "2022-01-01",
"licenseExpiryDate": "2027-01-01",
"licenseIssuer": "แพทยสภา"
}'
```

*Result*
```json
{
    "id": 14,
    "phone": null,
    "subSpecialty": "Arrhythmia",
    "bankAccountNumber": "1234567890",
    "licenseIssuer": "แพทยสภา",
    "department": "Cardiology",
    "schedules": null,
    "specialty": "Internal Medicine",
    "name": "Dr. Natthawut",
    "licenseNumber": null,
    "licenseIssuedDate": {
        "chronology": {
            "calendarType": "iso8601",
            "id": "ISO"
        },
        "dayOfMonth": 1,
        "dayOfWeek": {
            "enumType": "java.time.DayOfWeek",
            "name": "SATURDAY"
        },
        "dayOfYear": 1,
        "era": {
            "enumType": "java.time.chrono.IsoEra",
            "name": "CE"
        },
        "leapYear": false,
        "month": {
            "enumType": "java.time.Month",
            "name": "JANUARY"
        },
        "monthValue": 1,
        "year": 2022
    },
    "email": null,
    "licenseExpiryDate": {
        "chronology": {
            "calendarType": "iso8601",
            "id": "ISO"
        },
        "dayOfMonth": 1,
        "dayOfWeek": {
            "enumType": "java.time.DayOfWeek",
            "name": "FRIDAY"
        },
        "dayOfYear": 1,
        "era": {
            "enumType": "java.time.chrono.IsoEra",
            "name": "CE"
        },
        "leapYear": false,
        "month": {
            "enumType": "java.time.Month",
            "name": "JANUARY"
        },
        "monthValue": 1,
        "year": 2027
    },
    "bankAccountName": "ธนาคารไทยพาณิชย์"
}
```

# 2. Update Doctor By Id
**PUT**
```sh
curl --location --request PUT 'http://localhost:8080/doctor/14' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=E845980308D6A810927092C78AC4A036' \
--data '{
"bankAccountName": "ธนาคารกสิกรไทย",
"bankAccountNumber": "01-123-456789",
"licenseIssuedDate": "2022-02-01",
"licenseExpiryDate": "2027-12-01",
"licenseIssuer": "แพทยสภา"
}'
```

*Result*
```json
{
    "id": 14,
    "name": "Dr. POP",
    "email": "pop.dr@gamil.com",
    "specialty": "Internal Medicine",
    "department": "Cardiology",
    "subSpecialty": "Arrhythmia",
    "licenseNumber": null,
    "licenseIssuedDate": "2022-02-01",
    "licenseExpiryDate": "2027-12-01",
    "licenseIssuer": "แพทยสภา",
    "bankAccountNumber": "01-123-456789",
    "bankAccountName": "ธนาคารกสิกรไทย"
}
```

# 3. Get Doctor By Id
**GET**

```shell
curl --location 'http://localhost:8080/doctor/show/14'
```

*Result*
```json
{
    "id": 14,
    "name": "Dr. POP",
    "email": "pop.dr@gamil.com",
    "specialty": "Internal Medicine",
    "department": "Cardiology",
    "subSpecialty": "Arrhythmia",
    "licenseNumber": null,
    "licenseIssuedDate": "2022-02-01",
    "licenseExpiryDate": "2027-12-01",
    "licenseIssuer": "แพทยสภา",
    "bankAccountNumber": "01-123-456789",
    "bankAccountName": "ธนาคารกสิกรไทย"
}
```

# 4. Get all doctors
**GET
```shell
curl --location 'http://localhost:8080/doctor' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=E845980308D6A810927092C78AC4A036'
```

*Result*
```json
[
    {
        "id": 4,
        "name": "Dr. POP",
        "email": "dr-pop-vnurser-1@gmail.com",
        "specialty": "อยุกรรม",
        "department": null,
        "subSpecialty": null,
        "licenseNumber": null,
        "licenseIssuedDate": "2022-02-01",
        "licenseExpiryDate": "2027-12-01",
        "licenseIssuer": "แพทยสภา",
        "bankAccountNumber": "01-123-456789",
        "bankAccountName": "ธนาคารกสิกรไทย"
    },
    {
        "id": 5,
        "name": "Dr. POP",
        "email": "dr-pop-vnurser-1@gmail.com",
        "specialty": "อยุกรรม",
        "department": null,
        "subSpecialty": null,
        "licenseNumber": null,
        "licenseIssuedDate": null,
        "licenseExpiryDate": null,
        "licenseIssuer": null,
        "bankAccountNumber": null,
        "bankAccountName": null
    },
    {
        "id": 6,
        "name": "Dr. POP",
        "email": "dr-pop-vnurser-1@gmail.com",
        "specialty": "อยุกรรม",
        "department": null,
        "subSpecialty": null,
        "licenseNumber": null,
        "licenseIssuedDate": null,
        "licenseExpiryDate": null,
        "licenseIssuer": null,
        "bankAccountNumber": null,
        "bankAccountName": null
    },
    {
        "id": 7,
        "name": "Dr. POP",
        "email": "dr-pop-vnurser-1@gmail.com",
        "specialty": "อยุกรรม",
        "department": null,
        "subSpecialty": null,
        "licenseNumber": null,
        "licenseIssuedDate": null,
        "licenseExpiryDate": null,
        "licenseIssuer": null,
        "bankAccountNumber": null,
        "bankAccountName": null
    },
    {
        "id": 8,
        "name": "Dr. POP",
        "email": "dr-pop-vnurser-1@gmail.com",
        "specialty": "อยุกรรม",
        "department": null,
        "subSpecialty": null,
        "licenseNumber": null,
        "licenseIssuedDate": null,
        "licenseExpiryDate": null,
        "licenseIssuer": null,
        "bankAccountNumber": null,
        "bankAccountName": null
    },
    {
        "id": 9,
        "name": "Dr. POP",
        "email": "dr-pop-vnurser-1@gmail.com",
        "specialty": "อยุกรรม",
        "department": null,
        "subSpecialty": null,
        "licenseNumber": null,
        "licenseIssuedDate": null,
        "licenseExpiryDate": null,
        "licenseIssuer": null,
        "bankAccountNumber": null,
        "bankAccountName": null
    },
    {
        "id": 10,
        "name": "Dr. POP",
        "email": "dr-pop-vnurser-1@gmail.com",
        "specialty": "อยุกรรม",
        "department": null,
        "subSpecialty": null,
        "licenseNumber": null,
        "licenseIssuedDate": null,
        "licenseExpiryDate": null,
        "licenseIssuer": null,
        "bankAccountNumber": null,
        "bankAccountName": null
    },
    {
        "id": 11,
        "name": "Dr. POP",
        "email": "dr-pop-vnurser-1@gmail.com",
        "specialty": "อยุกรรม",
        "department": null,
        "subSpecialty": null,
        "licenseNumber": null,
        "licenseIssuedDate": "2022-01-31",
        "licenseExpiryDate": "2027-12-01",
        "licenseIssuer": "แพทยสภา",
        "bankAccountNumber": "1234567890",
        "bankAccountName": "ธนาคารไทยพาณิชย์"
    },
    {
        "id": 14,
        "name": "Dr. POP",
        "email": "pop.dr@gamil.com",
        "specialty": "Internal Medicine",
        "department": "Cardiology",
        "subSpecialty": "Arrhythmia",
        "licenseNumber": null,
        "licenseIssuedDate": "2022-02-01",
        "licenseExpiryDate": "2027-12-01",
        "licenseIssuer": "แพทยสภา",
        "bankAccountNumber": "01-123-456789",
        "bankAccountName": "ธนาคารกสิกรไทย"
    },
    {
        "id": 12,
        "name": "Dr. POP-2",
        "email": "dr-pop-vnurser-2@gmail.com",
        "specialty": "อยุกรรม",
        "department": null,
        "subSpecialty": null,
        "licenseNumber": null,
        "licenseIssuedDate": null,
        "licenseExpiryDate": null,
        "licenseIssuer": null,
        "bankAccountNumber": null,
        "bankAccountName": null
    },
    {
        "id": 13,
        "name": "Dr. POP-3",
        "email": "dr-pop-vnurser-3@gmail.com",
        "specialty": "อยุกรรม",
        "department": null,
        "subSpecialty": null,
        "licenseNumber": null,
        "licenseIssuedDate": null,
        "licenseExpiryDate": null,
        "licenseIssuer": null,
        "bankAccountNumber": null,
        "bankAccountName": null
    }
]
```


# 5. Set Doctor Schedule
**POST**
```shell
curl --location 'http://localhost:8080/schedules' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=E845980308D6A810927092C78AC4A036' \
--data '{
"doctorId": 4,
"dayOfWeek": "MONDAY",
"startTime": "09:00",
"endTime": "12:00"
}'
```

*Result*
```json
{
    "id": 10,
    "endTime": {
        "hour": 12,
        "minute": 0,
        "nano": 0,
        "second": 0
    },
    "startTime": {
        "hour": 9,
        "minute": 0,
        "nano": 0,
        "second": 0
    },
    "doctor": {
        "id": 4
    },
    "dayOfWeek": {
        "enumType": "java.time.DayOfWeek",
        "name": "MONDAY"
    }
}
```


# 6. Get Doctor Schedule 
**GET**

```shell
curl --location 'http://localhost:8080/schedules/doctor/14' 
```

*Result*
```json
[
    {
        "id": 17,
        "doctorId": 14,
        "doctorName": "Dr. POP",
        "dayOfWeek": "MONDAY",
        "startTime": "10:00",
        "endTime": "12:00"
    },
    {
        "id": 18,
        "doctorId": 14,
        "doctorName": "Dr. POP",
        "dayOfWeek": "MONDAY",
        "startTime": "13:00",
        "endTime": "17:00"
    },
    {
        "id": 26,
        "doctorId": 14,
        "doctorName": "Dr. POP",
        "dayOfWeek": "TUESDAY",
        "startTime": "10:00",
        "endTime": "12:00"
    },
    {
        "id": 20,
        "doctorId": 14,
        "doctorName": "Dr. POP",
        "dayOfWeek": "TUESDAY",
        "startTime": "13:00",
        "endTime": "14:00"
    },
    {
        "id": 21,
        "doctorId": 14,
        "doctorName": "Dr. POP",
        "dayOfWeek": "WEDNESDAY",
        "startTime": "09:00",
        "endTime": "12:00"
    },
    {
        "id": 22,
        "doctorId": 14,
        "doctorName": "Dr. POP",
        "dayOfWeek": "WEDNESDAY",
        "startTime": "13:00",
        "endTime": "17:00"
    },
    {
        "id": 23,
        "doctorId": 14,
        "doctorName": "Dr. POP",
        "dayOfWeek": "THURSDAY",
        "startTime": "09:00",
        "endTime": "12:00"
    },
    {
        "id": 19,
        "doctorId": 14,
        "doctorName": "Dr. POP",
        "dayOfWeek": "THURSDAY",
        "startTime": "10:00",
        "endTime": "12:00"
    },
    {
        "id": 24,
        "doctorId": 14,
        "doctorName": "Dr. POP",
        "dayOfWeek": "THURSDAY",
        "startTime": "13:00",
        "endTime": "18:00"
    },
    {
        "id": 25,
        "doctorId": 14,
        "doctorName": "Dr. POP",
        "dayOfWeek": "FRIDAY",
        "startTime": "13:00",
        "endTime": "19:00"
    }
]
```


# 7. Delete Doctor Schedule 

**DELETE**

```shell
curl --location --request DELETE 'http://localhost:8080/schedules/delete?doctorId=4&scheduleId=11' 

```

*Result 1:*
```json
{
"message": "Schedule deleted successfully"
}
```

*Result 2:*
```
Schedule not found for this doctor
```

# 8.  Update Doctor Schedule
**PUT**

```sh
curl --location --request PUT 'http://localhost:8080/schedules/20' \
--header 'Content-Type: application/json' \
--data '{
"doctorId": 14,
"dayOfWeek": "TUESDAY",
"startTime": "13:00",
"endTime": "14:00"
}'
```

*Result*

```json
{
    "id": 20,
    "endTime": {
        "hour": 14,
        "minute": 0,
        "nano": 0,
        "second": 0
    },
    "startTime": {
        "hour": 13,
        "minute": 0,
        "nano": 0,
        "second": 0
    },
    "doctor": {
        "id": 14
    },
    "dayOfWeek": {
        "enumType": "java.time.DayOfWeek",
        "name": "TUESDAY"
    }
}
```

# 9. Get Appointment Slot
**GET**

```sh
curl --location 'http://localhost:8080/slots/show?doctorId=14&date=2025-06-24'
```

*Result*
```json
[
    {
        "id": 818,
        "doctorId": 14,
        "startTime": "2025-06-24T08:00:00",
        "startTime_DayOfWeek": "TUESDAY",
        "endTime": "2025-06-24T08:15:00",
        "endTime_DayOfWeek": "TUESDAY",
        "booked": true
    },
    {
        "id": 938,
        "doctorId": 14,
        "startTime": "2025-06-24T13:00:00",
        "startTime_DayOfWeek": "TUESDAY",
        "endTime": "2025-06-24T13:15:00",
        "endTime_DayOfWeek": "TUESDAY",
        "booked": false
    },
    {
        "id": 939,
        "doctorId": 14,
        "startTime": "2025-06-24T13:20:00",
        "startTime_DayOfWeek": "TUESDAY",
        "endTime": "2025-06-24T13:35:00",
        "endTime_DayOfWeek": "TUESDAY",
        "booked": false
    },
    {
        "id": 940,
        "doctorId": 14,
        "startTime": "2025-06-24T13:40:00",
        "startTime_DayOfWeek": "TUESDAY",
        "endTime": "2025-06-24T13:55:00",
        "endTime_DayOfWeek": "TUESDAY",
        "booked": false
    },
    {
        "id": 941,
        "doctorId": 14,
        "startTime": "2025-06-24T10:00:00",
        "startTime_DayOfWeek": "TUESDAY",
        "endTime": "2025-06-24T10:15:00",
        "endTime_DayOfWeek": "TUESDAY",
        "booked": false
    },
    {
        "id": 942,
        "doctorId": 14,
        "startTime": "2025-06-24T10:20:00",
        "startTime_DayOfWeek": "TUESDAY",
        "endTime": "2025-06-24T10:35:00",
        "endTime_DayOfWeek": "TUESDAY",
        "booked": false
    },
    {
        "id": 943,
        "doctorId": 14,
        "startTime": "2025-06-24T10:40:00",
        "startTime_DayOfWeek": "TUESDAY",
        "endTime": "2025-06-24T10:55:00",
        "endTime_DayOfWeek": "TUESDAY",
        "booked": false
    },
    {
        "id": 944,
        "doctorId": 14,
        "startTime": "2025-06-24T11:00:00",
        "startTime_DayOfWeek": "TUESDAY",
        "endTime": "2025-06-24T11:15:00",
        "endTime_DayOfWeek": "TUESDAY",
        "booked": false
    },
    {
        "id": 945,
        "doctorId": 14,
        "startTime": "2025-06-24T11:20:00",
        "startTime_DayOfWeek": "TUESDAY",
        "endTime": "2025-06-24T11:35:00",
        "endTime_DayOfWeek": "TUESDAY",
        "booked": false
    },
    {
        "id": 946,
        "doctorId": 14,
        "startTime": "2025-06-24T11:40:00",
        "startTime_DayOfWeek": "TUESDAY",
        "endTime": "2025-06-24T11:55:00",
        "endTime_DayOfWeek": "TUESDAY",
        "booked": false
    }
]

```


# 10. Make Appointment

**POST**

```shell
curl --location 'http://localhost:8080/appointments/book' \
--header 'Content-Type: application/json' \
--data '{
"doctorId": 14,
"slotId": 818,
"username": "testpatient"
}'
```

*Result :Successful*
```json
{
    "message": "Appointment booked",
    "id": 3
}
```

*Result: Unsuccessful*
```
Slot already booked
```
