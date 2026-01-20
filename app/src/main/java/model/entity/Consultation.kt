package model.entity

import java.io.Serializable
import java.time.LocalDate

class Consultation : Entity, Serializable {

    companion object {
        private const val serialVersionUID: Long = 1L
    }

    private var id: Int? = null
    private var doctor_id: Int? = null
    private var patient_id: Int? = null
    private var hour: String? = null
    private var date: LocalDate? = null
    private var duration: String? = null
    private var reason: String? = null

    constructor(
        id: Int?,
        doctor_id: Int?,
        patient_id: Int?,
        hour: String?,
        date: LocalDate?,
        reason: String?,
        duration: String?
    ) {
        setId(id)
        setDoctor_id(doctor_id)
        setPatient_id(patient_id)
        setHour(hour)
        setDate(date)
        setReason(reason)
        setDuration(duration)
    }

    constructor()

    fun getId(): Int? = id

    fun getDoctor_id(): Int? = doctor_id

    fun getPatient_id(): Int? = patient_id

    fun getHour(): String? = hour

    fun getDate(): LocalDate? = date

    fun getReason(): String? = reason

    fun getDuration(): String? = duration

    fun setId(id: Int?) {
        this.id = id
    }

    fun setDoctor_id(doctor_id: Int?) {
        this.doctor_id = doctor_id
    }

    fun setPatient_id(patient_id: Int?) {
        this.patient_id = patient_id
    }

    fun setHour(hour: String?) {
        this.hour = hour
    }

    fun setDate(date: LocalDate?) {
        this.date = date
    }

    fun setReason(reason: String?) {
        this.reason = reason
    }

    fun setDuration(duration: String?) {
        this.duration = duration
    }

    override fun toString(): String {
        return "Consultation{" +
                "id=$id, " +
                "doctor_id=$doctor_id, " +
                "patient_id=$patient_id, " +
                "hour='$hour', " +
                "date=$date, " +
                "reason='$reason'" +
                "}"
    }
}
