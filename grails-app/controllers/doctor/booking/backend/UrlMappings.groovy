package doctor.booking.backend

class UrlMappings {
    static mappings = {
        
        "/doctor"(resources: 'doctor')
        get "/doctor/show/$id"(controller: 'doctor', action: 'show')


        "/schedules"(resources: 'doctorSchedule')
        get "/schedules/doctor/$id"(controller: "doctorSchedule", action: "listByDoctorId")
        delete "/schedules/delete"(controller: "doctorSchedule", action: "deleteByDoctorAndSchedule")


        /*ยกเลิก ไปใช้ GO*/
        // "/slots/generate"(controller: 'appointmentSlot', action: 'generate')
        // get "/slots/show"(controller: 'appointmentSlot', action: 'showAllSlots')
        // get "/slots/available"(controller: "appointmentSlot", action: "available")

        /*ยกเลิก ไปใช้ GO*/
        //"/appointments"(resources: 'appointment')
        //post "/appointments/book"(controller: 'appointment', action: 'book')

        post "/auth/register"(controller: 'auth', action: 'register')
        post "/auth/login"(controller: 'auth', action: 'login')

        "/"(view: "/index")
        "500"(view: '/error')
        "404"(view: '/notFound')

    }
}
