# 1. Register User

**POST**

```bash
curl --location 'http://localhost:8080/auth/register' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=E845980308D6A810927092C78AC4A036' \
--data '{
"username": "testdoc1",
"password": "1234",
"role": "DOCTOR"
}'
```

# 2. Register Patient

**POST**
```bash
curl --location 'http://localhost:8080/auth/register' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=E845980308D6A810927092C78AC4A036' \
--data '{
"username": "testpatient",
"password": "test123",
"role": "PATIENT"
}'
```

*Result*
```json
{
"message": "Registered successfully"
}
```

# 3. Register Doctor
**POST**

```bash
curl --location 'http://localhost:8080/doctor' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=E845980308D6A810927092C78AC4A036' \
--data-raw '{
  "name": "Dr. POP-3",
  "specialty": "อยุกรรม",
  "phone": "081-xxx-xxxx",
  "email": "dr-pop-vnurser-3@gmail.com"
}'
```

*Result*
```json
{
    "id": 13,
    "phone": "081-xxx-xxxx",
    "schedules": null,
    "specialty": "อยุกรรม",
    "name": "Dr. POP-3",
    "licenseNumber": null,
    "email": "dr-pop-vnurser-3@gmail.com"
}
```


# 4. Get Doctor By Id
**GET**

```shell
curl --location 'http://localhost:8080/doctor/show/13'
```

*Result*
```json
{
    "id": 13,
    "phone": "081-xxx-xxxx",
    "schedules": [],
    "specialty": "อยุกรรม",
    "name": "Dr. POP-3",
    "licenseNumber": null,
    "email": "dr-pop-vnurser-3@gmail.com"
}
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
curl --location 'http://localhost:8080/schedules/doctor/4' \
--header 'Content-Type: application/json' 
```

*Result*
```json
[
    {
        "id": 10,
        "doctorId": 4,
        "doctorName": "Dr. POP",
        "dayOfWeek": "MONDAY",
        "startTimeX": "09:00",
        "endTimeX": "12:00"
    },
    {
        "id": 11,
        "doctorId": 4,
        "doctorName": "Dr. POP",
        "dayOfWeek": "THURSDAY",
        "startTimeX": "09:00",
        "endTimeX": "12:00"
    },
    {
        "id": 12,
        "doctorId": 4,
        "doctorName": "Dr. POP",
        "dayOfWeek": "THURSDAY",
        "startTimeX": "13:00",
        "endTimeX": "17:00"
    },
    {
        "id": 13,
        "doctorId": 4,
        "doctorName": "Dr. POP",
        "dayOfWeek": "FRIDAY",
        "startTimeX": "09:00",
        "endTimeX": "12:00"
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



# 8. Generate Appointment Slot 

**POST**

```shell
curl --location 'http://localhost:8080/slots/generate?doctorId=4&date=2025-06-20' \
--header 'Content-Type: application/json' \
--data '{
  "doctorId": 1,
  "date": "2025-06-15"
}'
```

*Result:*

```json
[
    {
        "id": 22,
        "doctorId": 4,
        "startTime": "2025-06-20T09:00:00",
        "startTime_DayOfWeek": "FRIDAY",
        "endTime": "2025-06-20T09:15:00",
        "endTime_DayOfWeek": "FRIDAY",
        "booked": false
    },
    {
        "id": 23,
        "doctorId": 4,
        "startTime": "2025-06-20T09:20:00",
        "startTime_DayOfWeek": "FRIDAY",
        "endTime": "2025-06-20T09:35:00",
        "endTime_DayOfWeek": "FRIDAY",
        "booked": false
    },
    {
        "id": 24,
        "doctorId": 4,
        "startTime": "2025-06-20T09:40:00",
        "startTime_DayOfWeek": "FRIDAY",
        "endTime": "2025-06-20T09:55:00",
        "endTime_DayOfWeek": "FRIDAY",
        "booked": false
    },
    {
        "id": 25,
        "doctorId": 4,
        "startTime": "2025-06-20T10:00:00",
        "startTime_DayOfWeek": "FRIDAY",
        "endTime": "2025-06-20T10:15:00",
        "endTime_DayOfWeek": "FRIDAY",
        "booked": false
    },
    {
        "id": 26,
        "doctorId": 4,
        "startTime": "2025-06-20T10:20:00",
        "startTime_DayOfWeek": "FRIDAY",
        "endTime": "2025-06-20T10:35:00",
        "endTime_DayOfWeek": "FRIDAY",
        "booked": false
    },
    {
        "id": 27,
        "doctorId": 4,
        "startTime": "2025-06-20T10:40:00",
        "startTime_DayOfWeek": "FRIDAY",
        "endTime": "2025-06-20T10:55:00",
        "endTime_DayOfWeek": "FRIDAY",
        "booked": false
    },
    {
        "id": 28,
        "doctorId": 4,
        "startTime": "2025-06-20T11:00:00",
        "startTime_DayOfWeek": "FRIDAY",
        "endTime": "2025-06-20T11:15:00",
        "endTime_DayOfWeek": "FRIDAY",
        "booked": false
    },
    {
        "id": 29,
        "doctorId": 4,
        "startTime": "2025-06-20T11:20:00",
        "startTime_DayOfWeek": "FRIDAY",
        "endTime": "2025-06-20T11:35:00",
        "endTime_DayOfWeek": "FRIDAY",
        "booked": false
    },
    {
        "id": 30,
        "doctorId": 4,
        "startTime": "2025-06-20T11:40:00",
        "startTime_DayOfWeek": "FRIDAY",
        "endTime": "2025-06-20T11:55:00",
        "endTime_DayOfWeek": "FRIDAY",
        "booked": false
    }
]
```


# 9. Get Doctor Appointment Slot in Selected Date 

**GET**

```shell
curl --location --request GET 'http://localhost:8080/slots/show?doctorId=4&date=2025-06-20' \
--header 'Content-Type: application/json'
```

```json
[
    {
        "id": 689,
        "startTime": "2025-06-20T09:00:00",
        "startTime_DayOfWeek": "FRIDAY",
        "endTime": "2025-06-20T09:15:00",
        "endTime_DayOfWeek": "FRIDAY",
        "doctorId": 4,
        "booked": false,
        "available": true
    },
    {
        "id": 690,
        "startTime": "2025-06-20T09:20:00",
        "startTime_DayOfWeek": "FRIDAY",
        "endTime": "2025-06-20T09:35:00",
        "endTime_DayOfWeek": "FRIDAY",
        "doctorId": 4,
        "booked": false,
        "available": true
    },
    {
        "id": 691,
        "startTime": "2025-06-20T09:40:00",
        "startTime_DayOfWeek": "FRIDAY",
        "endTime": "2025-06-20T09:55:00",
        "endTime_DayOfWeek": "FRIDAY",
        "doctorId": 4,
        "booked": false,
        "available": true
    },
    {
        "id": 692,
        "startTime": "2025-06-20T10:00:00",
        "startTime_DayOfWeek": "FRIDAY",
        "endTime": "2025-06-20T10:15:00",
        "endTime_DayOfWeek": "FRIDAY",
        "doctorId": 4,
        "booked": false,
        "available": true
    },
    {
        "id": 693,
        "startTime": "2025-06-20T10:20:00",
        "startTime_DayOfWeek": "FRIDAY",
        "endTime": "2025-06-20T10:35:00",
        "endTime_DayOfWeek": "FRIDAY",
        "doctorId": 4,
        "booked": false,
        "available": true
    },
    {
        "id": 694,
        "startTime": "2025-06-20T10:40:00",
        "startTime_DayOfWeek": "FRIDAY",
        "endTime": "2025-06-20T10:55:00",
        "endTime_DayOfWeek": "FRIDAY",
        "doctorId": 4,
        "booked": false,
        "available": true
    },
    {
        "id": 695,
        "startTime": "2025-06-20T11:00:00",
        "startTime_DayOfWeek": "FRIDAY",
        "endTime": "2025-06-20T11:15:00",
        "endTime_DayOfWeek": "FRIDAY",
        "doctorId": 4,
        "booked": false,
        "available": true
    },
    {
        "id": 696,
        "startTime": "2025-06-20T11:20:00",
        "startTime_DayOfWeek": "FRIDAY",
        "endTime": "2025-06-20T11:35:00",
        "endTime_DayOfWeek": "FRIDAY",
        "doctorId": 4,
        "booked": false,
        "available": true
    },
    {
        "id": 697,
        "startTime": "2025-06-20T11:40:00",
        "startTime_DayOfWeek": "FRIDAY",
        "endTime": "2025-06-20T11:55:00",
        "endTime_DayOfWeek": "FRIDAY",
        "doctorId": 4,
        "booked": false,
        "available": true
    }
]
```



# 10. Make Appointment

**POST**

```shell
curl --location 'http://localhost:8080/appointments/book' \
--header 'Content-Type: application/json' \
--data '{
"doctorId": 4,
"slotId": 22,
"username": "testpatient"
}'
```

*Result :Successful*
```json
{
    "message": "Appointment booked",
    "id": 2
}
```

*Result: Unsuccessful*
```
Slot already booked
```
